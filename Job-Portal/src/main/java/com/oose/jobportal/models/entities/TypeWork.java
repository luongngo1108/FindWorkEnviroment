package com.oose.jobportal.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "typework")
public class TypeWork {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "typeworkID", nullable = false)
	private int typeworkID;
	
	@Column(name = "nametypework", nullable = true)
	private String nametypework;

	@OneToMany(mappedBy = "type_work", cascade = CascadeType.ALL)
	private List<Work> workList;

	@Column(name = "price", nullable = true)
	private int price;
}