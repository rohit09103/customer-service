package com.localhost.customer.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFetch implements Serializable {

	private static final long serialVersionUID = 216199318723656854L;
	
	private String phoneNumber;
	private String email;
	private String customerId;

}
