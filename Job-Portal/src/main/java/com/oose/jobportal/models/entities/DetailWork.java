package com.oose.jobportal.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "detailwork")
public class DetailWork {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "detailworkID", nullable = false)
	private int detailworkID;

	@OneToOne(targetEntity = Payment.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "paymentID", nullable = true, unique = true)
	private Payment payment;

	@Column(name = "income", nullable = true)
	private int income;

	@Column(name = "hours", nullable = true)
	private int hours;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "contact", nullable = true, length = 20)
	private String contact;
}
