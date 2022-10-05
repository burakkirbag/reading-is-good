package com.burakkirbag.readingisgood.adapters.customer.mongodb.repository;

import com.burakkirbag.readingisgood.adapters.customer.mongodb.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    CustomerEntity findByEmail(String email);
}
