package com.localhost.customer.endpoint;

import java.util.List;

import com.localhost.customer.CustomerClientServiceGrpc.CustomerClientServiceImplBase;
import com.localhost.customer.CustomerCreateReply;
import com.localhost.customer.CustomerCreateRequest;
import com.localhost.customer.CustomerFetchReply;
import com.localhost.customer.CustomerFetchRequest;
import com.localhost.customer.model.Customer;
import com.localhost.customer.model.CustomerFetch;
import com.localhost.customer.service.CustomerService;

import io.grpc.stub.StreamObserver;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class CustomerEndpoint extends CustomerClientServiceImplBase {
	
	final CustomerService customerService;

	/**
     */
	public CustomerEndpoint(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void createCustomer(CustomerCreateRequest request, StreamObserver<CustomerCreateReply> responseObserver) {
		Customer customer = new Customer(request.getFirstName(), 
				request.getLastName(),
				request.getPhoneNumber(),
				request.getEmail(),
				request.getUserId()
				);
		log.info("Create customer request: [{}]", customer);
		customer = customerService.createCustomer(customer);
		log.info("Customer created successfully: [{}]", customer);
		CustomerCreateReply customerCreateReply = buildCustomerCreateReply(customer);
		responseObserver.onNext(customerCreateReply);
		responseObserver.onCompleted();
		log.info("Create customer end.");
	}

	/**
     */
	private CustomerCreateReply buildCustomerCreateReply(Customer customer) {
        return CustomerCreateReply.newBuilder()
        .setFirstName(customer.getFirstName())
        .setLastName(customer.getLastName())
        .setPhoneNumber(customer.getPhoneNumber())
        .setEmail(customer.getEmail())
        .setUserId(customer.getCustomerId())
        .build();
	}

	@Override
	public void fetchCustomer(CustomerFetchRequest request, StreamObserver<CustomerFetchReply> responseObserver) {
		CustomerFetch customerFetch = new CustomerFetch(
				request.getPhoneNumber(),
				request.getEmail(),
				request.getUserId()
				);
		log.info("Fetch customer request: [{}]", customerFetch);
		List<Customer> customers = customerService.fetchCustomers(customerFetch);
		log.info("Fetch customer successfull: [{}]", customers);
		responseObserver.onNext(buildCustomerFetchReply(customers));
		responseObserver.onCompleted();
		log.info("Fetch customer end.");
		
	}

	/**
     */
	private CustomerFetchReply buildCustomerFetchReply(List<Customer> customers) {
        return CustomerFetchReply.newBuilder()
        .addAllCustomers(customers.stream().map(it -> com.localhost.customer.Customer.newBuilder()
        .setFirstName(it.getFirstName())
        .setLastName(it.getLastName())
        .setPhoneNumber(it.getPhoneNumber())
        .setEmail(it.getEmail())
        .setUserId(it.getCustomerId())
        .build()).toList())
        .build();
	}

}
