package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dto.PaymentRequestDto;
import org.example.dto.PaymentResponseDto;
import org.example.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-base-url}/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> findAll(){
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> add(@RequestBody PaymentRequestDto paymentRequestDto){
        return ResponseEntity.ok(paymentService.add(paymentRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> change(@PathVariable Long id, @RequestBody PaymentRequestDto paymentRequestDto){
        return ResponseEntity.ok(paymentService.change(id, paymentRequestDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        paymentService.delete(id);
    }

    @Operation(summary = "Проверка оплачен ли проект")
    @GetMapping("/isPaid/{idProject}")
    public ResponseEntity<Boolean> isPaid(@PathVariable Long idProject) {
        return ResponseEntity.ok(paymentService.isPaid(idProject));
    }
}
