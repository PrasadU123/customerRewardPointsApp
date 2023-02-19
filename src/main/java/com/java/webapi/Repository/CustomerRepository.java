package com.java.webapi.Repository;

import com.java.webapi.Models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

        public Customer findByCustomerId(Long customerId);

}
