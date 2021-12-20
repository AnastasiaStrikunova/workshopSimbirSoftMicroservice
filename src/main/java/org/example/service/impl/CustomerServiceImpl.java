package org.example.service.impl;

import org.example.dto.CustomerRequestDto;
import org.example.dto.CustomerResponseDto;
import org.example.dto.HistoryResponseDto;
import org.example.dto.PurseResponseDto;
import org.example.entity.CustomerEntity;
import org.example.entity.HistoryEntity;
import org.example.entity.PaymentEntity;
import org.example.entity.PurseEntity;
import org.example.enums.Transaction;
import org.example.exception.NotFountException;
import org.example.mapper.CustomerMapper;
import org.example.mapper.HistoryMapper;
import org.example.mapper.PurseMapper;
import org.example.repository.CustomerRepository;
import org.example.repository.HistoryRepository;
import org.example.repository.PaymentRepository;
import org.example.repository.PurseRepository;
import org.example.service.CustomerService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PurseRepository purseRepository;
    private final PaymentRepository paymentRepository;
    private final HistoryRepository historyRepository;

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    private final HistoryMapper historyMapper = Mappers.getMapper(HistoryMapper.class);
    private final PurseMapper purseMapper = Mappers.getMapper(PurseMapper.class);

    public CustomerServiceImpl(CustomerRepository customerRepository, PurseRepository purseRepository, PaymentRepository paymentRepository, HistoryRepository historyRepository) {
        this.customerRepository = customerRepository;
        this.purseRepository = purseRepository;
        this.paymentRepository = paymentRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDto> findAll() {
        List<CustomerEntity> customerEntityList = new ArrayList<>(customerRepository.findAll());
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        customerEntityList.forEach(customerEntity -> customerResponseDtoList.add(customerMapper.CustomerEntityToCustomerResponseDto(customerEntity)));
        return customerResponseDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDto findById(UUID id) {
        return customerMapper.CustomerEntityToCustomerResponseDto(
                customerRepository.findById(id).orElseThrow(
                        () -> new NotFountException(
                                String.format("Could not find customer with id = %s", id)
                        )
                )
        );
    }

    @Override
    @Transactional
    public CustomerResponseDto add(CustomerRequestDto customerRequestDto) {
        CustomerEntity customerEntity = customerMapper.CustomerRequestDtoToCustomerEntity(customerRequestDto);
        customerRepository.save(customerEntity);
        return customerMapper.CustomerEntityToCustomerResponseDto(customerEntity);
    }

    @Override
    @Transactional
    public CustomerResponseDto change(UUID id, CustomerRequestDto customerRequestDto) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find customer with id = %s", id)
                )
        );
        if (customerRequestDto.getName() != null) customerEntity.setName(customerRequestDto.getName());
        if (customerRequestDto.getIdPurse() != null) customerEntity.setPurseEntity(new PurseEntity(customerRequestDto.getIdPurse()));
        return customerMapper.CustomerEntityToCustomerResponseDto(customerEntity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find customer with id = %s", id)
                )
        );
        customerRepository.delete(customerEntity);
    }

    @Override
    public List<HistoryResponseDto> seeHistory(UUID id) {
        List<HistoryEntity> historyEntityList = new ArrayList<>(customerRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find customer with id = %s", id)
                )
        ).getHistoryEntityList());
        List<HistoryResponseDto> historyResponseDtoList = new ArrayList<>();
        historyEntityList.forEach(historyEntity -> historyResponseDtoList.add(historyMapper.HistoryEntityToHistoryResponseDto(historyEntity)));
        return historyResponseDtoList;
    }

    @Override
    @Transactional
    public PurseResponseDto topUpBalance(UUID id, Long balance) {
        return purseMapper.PurseEntityToPurseResponseDto(changeBalance(id, balance, Transaction.ENROLLMENT.name()));
    }

    @Override
    public PurseResponseDto payProject(Long idPayment) {
        PaymentEntity paymentEntity = paymentRepository.findById(idPayment).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find payment with id = %d", idPayment)
                )
        );
        if (paymentEntity.getCustomerEntity().getPurseEntity().getBalance() < paymentEntity.getPrice()) {
            throw new NotFountException(
                    "There are not enough funds on the account"
            );
        }
        PurseEntity purseEntity = changeBalance(paymentEntity.getCustomerEntity().getPersonalAccount(), paymentEntity.getPrice() * (-1), Transaction.PAYMENT.name());
        paymentEntity.setIsPaid(true);
        paymentRepository.save(paymentEntity);
        return purseMapper.PurseEntityToPurseResponseDto(purseEntity);
    }

    private PurseEntity changeBalance(UUID id, Long balance, String transaction) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find customer with id = %s", id)
                )
        );
        PurseEntity purseEntity = customerEntity.getPurseEntity();
        PurseEntity purseWithNewBalance = purseRepository.findById(purseEntity.getIdPurse()).orElseThrow(
                () -> new NotFountException(
                        String.format("Could not find purse with id = %s", id)
                )
        );
        purseWithNewBalance.setBalance(purseEntity.getBalance() + balance);
        addHistory(customerEntity, transaction);
        return purseWithNewBalance;
    }

    private void addHistory(CustomerEntity customerEntity, String transaction) {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setCustomerEntity(customerEntity);
        historyEntity.setAmount(customerEntity.getPurseEntity().getBalance());
        historyEntity.setTypeOfTransaction(transaction);
        historyEntity.setTime(new Date());
        historyRepository.save(historyEntity);
    }
}
