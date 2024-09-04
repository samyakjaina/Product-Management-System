package com.example.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.product.domain.JwtResponse;
import com.example.product.domain.SignInRequest;
import com.example.product.security.TokenManager;
import com.example.product.security.UserDetailsImpl;
import com.example.product.service.LoginService;

/**
 * Service Implementaion Class for Login
 * 
 * @author SAMYAK JAIN
 *
 */
@Component
public class LoginServiceImpl implements LoginService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	TokenManager jwtUtils;

	static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

	@Override
	public ResponseEntity<JwtResponse> signIn(@Valid SignInRequest signInRequest) {
		LOGGER.info("Sign in Process Started ");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		LOGGER.info("Sign in Process Completed " + userDetails.getUsername());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

}
