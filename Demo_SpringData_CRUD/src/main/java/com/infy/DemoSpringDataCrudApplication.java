package com.infy;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.dto.CustomerDTO;
import com.infy.service.CustomerServiceImpl;

@SpringBootApplication
public class DemoSpringDataCrudApplication implements CommandLineRunner {
	public static final Logger LOGGER = LogManager.getLogger(DemoSpringDataCrudApplication.class);
	@Autowired
	CustomerServiceImpl customerService;
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		addCustomer();
		// getCustomer();
		findAllCustomers();
		updateCustomer();
		findAllCustomers();
	}

	private void findAllCustomers() {
		// TODO Auto-generated method stub
		try {
			customerService.findAll().forEach(LOGGER::info);
		} catch (Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}

	private void getCustomer() {
		// TODO Auto-generated method stub
		try {
			CustomerDTO customer = customerService.getCustomer(4);
			LOGGER.info(customer);
		} catch (Exception e) {
			if (e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
	}

	private void updateCustomer() {
		// TODO Auto-generated method stub
		try {
			customerService.updateCustomer(4, "Vicky@infy.com");
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	private void addCustomer() {
		// TODO Auto-generated method stub
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(4);
		customer.setEmailId("harry@infy.com");
		customer.setName("Harry");
		customer.setDateOfBirth(LocalDate.now());
		try {
			customerService.addCustomer(customer);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

}
