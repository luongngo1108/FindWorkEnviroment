package com.oose.jobportal.services.impls;

import com.oose.jobportal.models.entities.Payment;
import com.oose.jobportal.repositories.PaymentRepo;
import com.oose.jobportal.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public Payment save(Payment payment) {
        return paymentRepo.save(payment);
    }
    @Override
    public Payment findByID(int id) {
        return paymentRepo.findById(id).orElseThrow();
    }
}
