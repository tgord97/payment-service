package com.iprody.paymentservice.service;

import com.iprody.paymentservice.entity.Payment;
import com.iprody.paymentservice.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;


    @Transactional
    public Mono<Payment> createPayment(Payment payment) {
        return Mono.just(paymentRepository.save(payment));
    }

    @Transactional
    public Mono<Payment> findPaymentById(Long id) {
        var foundedPayment = paymentRepository.findById(id);
        return foundedPayment.map(Mono::just).orElseGet(() ->
                Mono.error(new NoSuchElementException("No payment with id: " + id)));
    }

    @Transactional
    public Mono<Payment> updatePayment(Long id, Payment updatedPayment) {
        var paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            var existingPayment = paymentOptional.get();
            existingPayment.setAmount(updatedPayment.getAmount());
            existingPayment.setCurrency(updatedPayment.getCurrency());
            existingPayment.setStatus(updatedPayment.getStatus());
            existingPayment.setCreatedAt(updatedPayment.getCreatedAt());
            existingPayment.setUpdatedAt(updatedPayment.getUpdatedAt());
            return Mono.just(paymentRepository.save(existingPayment));
        } else {
            return Mono.error(new NoSuchElementException("No payment with id: " + id));
        }
    }
}
