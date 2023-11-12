package com.api.email.models.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "verificaciones")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class VerificationCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email",length = 100,unique = true)
	private String email;
	
	@Column(name="codigo")
	private Integer codigo;
	
	@Column(name = "expiracion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiracion;

	
	public VerificationCode(String email,Integer code) {
		this.codigo =code;
		this.email=email;
	}
	
}
