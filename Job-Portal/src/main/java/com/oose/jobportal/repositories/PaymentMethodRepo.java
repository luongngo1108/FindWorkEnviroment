package com.oose.jobportal.repositories;

import com.oose.jobportal.models.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Integer> {

}
