package com.localhost.customer.repository;

import com.localhost.customer.entity.CustomerEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCustomerId(String customerId);
    List<CustomerEntity> findByEmail(String email);
    List<CustomerEntity> findByPhoneNumber(String phoneNumber);
}
