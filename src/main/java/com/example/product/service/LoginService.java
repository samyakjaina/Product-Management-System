package com.example.product.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.product.domain.JwtResponse;
import com.example.product.domain.SignInRequest;

/**
 * Service Interface for Login
 * 
 * @author SAMYAK JAIN
 *
 */
@Service
public interface LoginService {

	/**
	 * @param signInRequest
	 * @return
	 */
	ResponseEntity<JwtResponse> signIn(@Valid @RequestBody SignInRequest signInRequest);
}