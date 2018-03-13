package com.globant.pruebas.profileservice.dao;

import com.globant.pruebas.profileservice.model.Entity;

public interface Dao<T extends Entity> {
	
	T getById(String id);
	
	T update(T t);

}
