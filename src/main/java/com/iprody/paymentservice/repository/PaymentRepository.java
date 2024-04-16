package com.iprody.paymentservice.repository;

import com.iprody.paymentservice.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment,Long> {
}
