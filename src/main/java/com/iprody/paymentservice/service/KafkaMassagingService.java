package com.iprody.paymentservice.service;

import com.iprody.paymentservice.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMassagingService {

    @Value("${topic.send-payment}")
    private String sendPaymentTopic;

    private final KafkaTemplate<String, PaymentDto> kafkaTemplate;

    public void sendPayment(PaymentDto paymentDto) {
        kafkaTemplate.send(sendPaymentTopic, String.valueOf(paymentDto.getTransactionRefId()), paymentDto);
    }
}
