package com.oose.jobportal.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "paymentmethod")
public class PaymentMethod {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "paymentmethodID", nullable = false)
	private int paymentmethodID;
	
	@Column(name = "namepaymentmethod", nullable = true)
	private String namepaymentmethod;

	@OneToMany(mappedBy = "payment_method", cascade = CascadeType.ALL)
	private List<Payment> paymentList;
}
