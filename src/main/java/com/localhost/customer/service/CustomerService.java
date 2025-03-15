/**
 * 
 */
package com.localhost.customer.service;

import java.util.List;

import com.localhost.customer.model.Customer;
import com.localhost.customer.model.CustomerFetch;

/**
 * 
 */
public interface CustomerService {
	/**
	 * To create a new instance of a customer
	 * @param customer
	 * @return
	 */
	Customer createCustomer(Customer customer);
	
	/**
	 * To fetch customer based on phoneNumber, email, customerId
	 * @param customerFetch
	 * @return
	 */
	List<Customer> fetchCustomers(CustomerFetch customerFetch);
}
