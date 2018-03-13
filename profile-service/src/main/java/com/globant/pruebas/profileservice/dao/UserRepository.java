package com.globant.pruebas.profileservice.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.globant.pruebas.profileservice.model.User;

public interface UserRepository extends CassandraRepository<User, String> {

}
