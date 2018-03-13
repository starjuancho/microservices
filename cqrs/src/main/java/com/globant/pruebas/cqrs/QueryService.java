package com.globant.pruebas.cqrs;

import java.util.Collection;

import com.globant.pruebas.cqrs.model.Customer;

public interface QueryService {

	Customer GetCustomer(String customerId);
	Collection<Customer>GetCustomersWithName(String name); 
	Collection<Customer>GetPreferredCustomers() ;

}
