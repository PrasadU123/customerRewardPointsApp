package com.java.webapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.webapi.Models.Customer;
import com.java.webapi.Models.Transaction;
import com.java.webapi.Repository.CustomerRepository;
import com.java.webapi.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class CustomerRewardsApplication {
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerRewardsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository,TransactionRepository transactionRepository) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
//		read and parse the JSON data to Java Object
			List<Customer> customers = mapper.readValue(new File("/Users/venkt/eclipse-workspace/customerrewards/src/main/resources/customers_Data.json"), new TypeReference<List<Customer>>() {
			});

		List<Transaction> transactions= (List<Transaction>) mapper.readValue(new File("/Users/venkt/eclipse-workspace/customerrewards/src/main/resources/Rewards_Data.json"), new TypeReference<List<Transaction>>() {});
			try {
				customerRepository.saveAll(customers);
			transactionRepository.saveAll(transactions);
			} catch (Exception e) {
				System.out.println("Error while loading the file objects");
			}

		};
	}
}
