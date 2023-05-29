package com.oose.jobportal.services;

import com.oose.jobportal.models.entities.PaymentMethod;

public interface PaymentMethodService {
    PaymentMethod findByID(int id);
}
