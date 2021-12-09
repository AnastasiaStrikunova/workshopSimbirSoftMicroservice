package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dto.CustomerRequestDto;
import org.example.dto.CustomerResponseDto;
import org.example.dto.HistoryResponseDto;
import org.example.dto.PurseResponseDto;
import org.example.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api-base-url}/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable UUID id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> add(@RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.ok(customerService.add(customerRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> change(@PathVariable UUID id, @RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.ok(customerService.change(id, customerRequestDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        customerService.delete(id);
    }

    @Operation(summary = "Посмотреть историю баланса")
    @GetMapping("/history/{id}")
    public ResponseEntity<List<HistoryResponseDto>> seeHistory(@PathVariable UUID id){
        return ResponseEntity.ok(customerService.seeHistory(id));
    }

    @Operation(summary = "Пополнить баланс")
    @PutMapping("/{id}/upBalance")
    public ResponseEntity<PurseResponseDto> topUpBalance(@PathVariable UUID id, @RequestParam Long balance){
        return ResponseEntity.ok(customerService.topUpBalance(id, balance));
    }

    @Operation(summary = "Оплатить проект")
    @PutMapping("/payProject/{id}")
    public ResponseEntity<PurseResponseDto> payProject(@PathVariable Long id){
        return ResponseEntity.ok(customerService.payProject(id));
    }
}
