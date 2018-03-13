package com.globant.pruebas.profileservice.dao.cassandra;

import com.globant.pruebas.profileservice.dao.AbstractDao;
import com.globant.pruebas.profileservice.model.Entity;

public class CassandraDAO<T extends Entity> extends AbstractDao<T>{

	@Override
	public T getById(String id) {
		return null;
	}

	@Override
	public T update(T t) {
		return null;
	}

}
