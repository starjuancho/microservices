package com.globant.pruebas.profileservice.dao.mock;

import org.springframework.stereotype.Repository;

import com.globant.pruebas.profileservice.dao.AbstractDao;
import com.globant.pruebas.profileservice.model.Status;
import com.globant.pruebas.profileservice.model.User;

@Repository
public class MockUserDao extends AbstractDao<User> {

	@Override
	public User getById(String id) {
		User user = new User();
		user.setUserID(id);
		user.setUsername("No Name");
		user.setStatus(new Status((short)1,"new","Just Created"));
		return user;
	}

	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

}
