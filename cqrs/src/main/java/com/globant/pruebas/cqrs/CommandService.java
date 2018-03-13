package com.globant.pruebas.cqrs;

import java.util.Locale;

import com.globant.pruebas.cqrs.model.Customer;
import com.globant.pruebas.cqrs.model.CustomerDetails;

public interface CommandService {
	
	void mMakeCustomerPreferred(String customerId); 
	void changeCustomerLocale(String customerId, Locale newLocale); 
	void createCustomer(Customer customer);
	void editCustomerDetails(CustomerDetails details);

	

}
