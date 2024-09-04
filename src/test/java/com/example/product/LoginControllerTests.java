package com.example.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import com.example.product.controller.LoginController;
import com.example.product.domain.JwtResponse;
import com.example.product.domain.SignInRequest;
import com.example.product.service.LoginService;

/**
 * @author SAMYAK JAIN
 *
 */
public class LoginControllerTests {
	@InjectMocks
	private LoginController loginController;

	@Mock
	private LoginService loginService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSignIn() {
		SignInRequest signInRequest = new SignInRequest("username", "password");
		List<String> roles = new ArrayList<>();
		JwtResponse jwtResponse = new JwtResponse("ejlkldlsa", 1L, "username", "password", roles);
		ResponseEntity<JwtResponse> expectedResponse = ResponseEntity.ok(jwtResponse);
		when(loginService.signIn(any(SignInRequest.class))).thenReturn(expectedResponse);

		// Act
		ResponseEntity<?> response = loginController.signIn(signInRequest);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(jwtResponse, response.getBody());
	}

	@Test
	public void testSignInWithException() {
		SignInRequest signInRequest = new SignInRequest("username", "wrong_password");
		when(loginService.signIn(any(SignInRequest.class))).thenThrow(new BadCredentialsException("Bad Credentials"));

		BadCredentialsException exception = org.junit.jupiter.api.Assertions.assertThrows(BadCredentialsException.class,
				() -> {
					loginController.signIn(signInRequest);
				});
		assertEquals("Bad Credentials", exception.getMessage());
	}

}
