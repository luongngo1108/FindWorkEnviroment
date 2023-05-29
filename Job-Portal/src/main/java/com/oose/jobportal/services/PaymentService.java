package com.oose.jobportal.services;

import com.oose.jobportal.models.entities.Payment;

public interface PaymentService {
    Payment save(Payment payment);

    Payment findByID(int id);
}
