package com.api.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.email.models.entity.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>{

	VerificationCode findByEmail(String email);
	boolean existsByEmail(String email);
	void deleteByEmail(String email);
	
}
