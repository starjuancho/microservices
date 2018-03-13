package com.globant.pruebas.profileservice.dao;

import java.util.Random;

import com.globant.pruebas.profileservice.model.Entity;

public abstract class AbstractDao<T extends Entity> implements Dao<T>{
	
	protected Random random = new Random(System.currentTimeMillis());

}
