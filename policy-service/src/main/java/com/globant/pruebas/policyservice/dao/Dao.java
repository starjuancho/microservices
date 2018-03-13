package com.globant.pruebas.policyservice.dao;

import com.globant.pruebas.policyservice.model.Entity;

public interface Dao<T extends Entity> {
	
	T getById(String id);
	
	T update(T t);

}
