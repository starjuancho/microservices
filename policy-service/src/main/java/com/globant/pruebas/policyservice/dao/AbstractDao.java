package com.globant.pruebas.policyservice.dao;


import java.util.Random;

import com.globant.pruebas.policyservice.model.Entity;

public class AbstractDao<T extends Entity> implements Dao<T>{

	protected Random random = new Random(System.currentTimeMillis());

	@Override
	public T getById(String id) {
		return null;
	}
	
	@Override
	public T update(T t) {
		return null;
	}
	
}
