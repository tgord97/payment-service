package com.iprody.paymentservice.service;

import com.iprody.paymentservice.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaMassagingService kafkaMassagingService;
    private final ModelMapper modelMapper;

    public void sendPayment(PaymentDto paymentDto) {
        kafkaMassagingService.sendPayment(modelMapper.map(paymentDto, PaymentDto.class));
        log.info("Payment sent succsesfully");
    }

}
