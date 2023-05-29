package com.oose.jobportal.services.impls;

import com.oose.jobportal.models.entities.PaymentMethod;
import com.oose.jobportal.repositories.PaymentMethodRepo;
import com.oose.jobportal.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Override
    public PaymentMethod findByID(int id) {
        return paymentMethodRepo.findById(id).orElseThrow();
    }
}
