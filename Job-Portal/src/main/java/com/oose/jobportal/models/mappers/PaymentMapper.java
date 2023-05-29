package com.oose.jobportal.models.mappers;

import com.oose.jobportal.models.dtos.PaymentDto;
import com.oose.jobportal.models.entities.Payment;
import com.oose.jobportal.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    private static PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMapper(PaymentMethodService paymentMethodService) {
        PaymentMapper.paymentMethodService = paymentMethodService;
    }

    public static PaymentDto mappingToDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();

        if (payment != null)
        {
            paymentDto.setPaymentID(payment.getPaymentID());
            paymentDto.setTime(payment.getTime());
            paymentDto.setPaymentmethodID(payment.getPayment_method().getPaymentmethodID());
        } else {
            paymentDto.setPaymentID(-1);
            paymentDto.setTime(null);
            paymentDto.setPaymentmethodID(-1);
        }


        return paymentDto;
    }

    public static Payment mappingToEntity(PaymentDto paymentDto) {
        Payment payment = new Payment();

        if(paymentDto != null) {
            payment.setPaymentID(paymentDto.getPaymentID());
            payment.setTime(paymentDto.getTime());
            payment.setPayment_method(paymentMethodService.findByID(paymentDto.getPaymentmethodID()));
        } else  {
            payment.setPaymentID(-1);
            payment.setTime(null);
            payment.setPayment_method(null);
        }

        return payment;
    }
}
