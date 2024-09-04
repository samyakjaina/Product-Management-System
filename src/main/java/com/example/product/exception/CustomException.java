package com.example.product.exception;

import com.example.product.constant.ResponseMessage;


/**
 * @author SAMYAK JAIN
 *
 */
public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomException(String message) {
		super(message);
	}

	public CustomException(ResponseMessage message) {
		super(message.getMessage());
	}
}
