package com.iprody.paymentservice.dto;

import com.iprody.paymentservice.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDto {


    private Long inquiryRefId;

    private BigDecimal amount;

    private String currency;

    private Long transactionRefId;

    private PaymentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
