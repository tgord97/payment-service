package com.iprody.paymentservice.controller;

import com.iprody.paymentservice.dto.PaymentDto;
import com.iprody.paymentservice.entity.Payment;
import com.iprody.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    private final ModelMapper modelMapper;

    @GetMapping()
    public Mono<ResponseEntity<PaymentDto>> findPaymentById(@RequestParam Long id) {
        var paymentMono = paymentService.findPaymentById(id);
        return paymentMono.flatMap(foundedUser -> Mono.just(ResponseEntity.ok(modelMapper.map(foundedUser, PaymentDto.class)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build())));
    }


    @PostMapping("/add")
    public Mono<ResponseEntity<PaymentDto>> savePayment(@RequestBody PaymentDto paymentDto) {
        var createdPayment = paymentService.createPayment(modelMapper.map(paymentDto, Payment.class));
        return createdPayment.flatMap(value ->
                        Mono.just(ResponseEntity.created(URI.create("/payments/" + value.getId()))
                                .body(modelMapper.map(value, PaymentDto.class))))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping("/update")
    public Mono<ResponseEntity<PaymentDto>> updatePayment(@RequestParam Long id, @RequestBody PaymentDto paymentDto) {
        var paymentMono = paymentService.updatePayment(id, modelMapper.map(paymentDto, Payment.class));
        return paymentMono.flatMap(updatedPayment -> Mono.just(ResponseEntity.ok(modelMapper.map(updatedPayment, PaymentDto.class)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build())));
    }

}
