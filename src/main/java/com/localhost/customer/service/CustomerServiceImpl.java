package com.localhost.customer.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.localhost.customer.entity.CustomerEntity;
import com.localhost.customer.model.Customer;
import com.localhost.customer.model.CustomerFetch;

import com.localhost.customer.repository.CustomerRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Singleton
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		log.info("Received request for customer create with email [{}] and customer id [{}]",
				customer.getEmail(), customer.getCustomerId());
		if (StringUtils.isAllBlank(customer.getCustomerId())) {
			customer.setCustomerId(UUID.randomUUID().toString());
			log.info("Received request with null customerId for customer create with email [{}] and customer id [{}]",
					customer.getEmail(), customer.getCustomerId());
		}
		customerRepository.save(new CustomerEntity(customer.getCustomerId(), customer.getFirstName(),
				customer.getLastName(), customer.getPhoneNumber(), customer.getEmail()));
		return customer;
	}

	@Override
	public List<Customer> fetchCustomers(CustomerFetch customerFetch) {
		if (!StringUtils.isAllBlank(customerFetch.getCustomerId())) {
			return customerRepository.findByCustomerId(customerFetch.getCustomerId()).map(List::of)
					.orElse(Collections.emptyList()).stream().map(it ->
							new Customer(it.getFirstName(), it.getLastName(), it.getPhoneNumber(), it.getEmail(),
									it.getCustomerId())).toList();
		}

		if (!StringUtils.isAllBlank(customerFetch.getEmail())) {
			return customerRepository.findByEmail(customerFetch.getEmail()).stream().map(it ->
					new Customer(it.getFirstName(), it.getLastName(), it.getPhoneNumber(), it.getEmail(),
							it.getCustomerId())).toList();
		}

		if (!StringUtils.isAllBlank(customerFetch.getPhoneNumber())) {
			return customerRepository.findByPhoneNumber(customerFetch.getPhoneNumber()).stream().map(it ->
					new Customer(it.getFirstName(), it.getLastName(), it.getPhoneNumber(), it.getEmail(),
							it.getCustomerId())).toList();
		}

		return Collections.emptyList();
	}

}
