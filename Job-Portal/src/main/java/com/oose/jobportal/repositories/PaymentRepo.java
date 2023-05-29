package com.oose.jobportal.repositories;

import com.oose.jobportal.models.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
