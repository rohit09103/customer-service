package com.localhost.customer.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable{

	private static final long serialVersionUID = 5674469937290157030L;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String customerId;
}
