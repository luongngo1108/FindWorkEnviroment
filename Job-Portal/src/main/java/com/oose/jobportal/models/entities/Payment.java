package com.oose.jobportal.models.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "payment")
public class Payment {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "paymentID", nullable = false)
	private int paymentID;

	@ManyToOne
	@JoinColumn(name = "paymentmethodID")
	private PaymentMethod payment_method;
	
	@Column(name = "time", nullable = true)
	private Date time;

}
