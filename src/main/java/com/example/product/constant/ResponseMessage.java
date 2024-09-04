package com.example.product.constant;

/**
 * @author SAMYAK JAIN
 *
 */
public enum ResponseMessage {

	Null("No Data Found"), Delete("No Data Found for delete"), Update("No Data Found for udpate ");

	private String message;

	private ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
