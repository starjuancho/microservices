package com.globant.pruebas.profileservice.dao.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Repository;

import com.globant.pruebas.profileservice.dao.AbstractDao;
import com.globant.pruebas.profileservice.model.Address;
import com.globant.pruebas.profileservice.model.PhoneNumber;
import com.globant.pruebas.profileservice.model.Profile;

@Repository
public class MockProfileDao extends AbstractDao<Profile> {

	@Override
	public Profile getById(String id) {
		Profile profile = new Profile();
		profile.setUserId(id);
		profile.setGivenName("John");
		profile.setPaternalName("");
		profile.setMaternalName("Connor");
		Calendar calendar = new GregorianCalendar(random.nextInt(118) + 1900, random.nextInt(12), random.nextInt(30));
		profile.setBirthDate(calendar.getTime());
		Collection<PhoneNumber> phoneList = new ArrayList<>(1);
		phoneList.add(randomPhoneNumber());
		phoneList.add(randomPhoneNumber());
		profile.setPhoneNumbers(phoneList);
		Collection<Address> addressList = new ArrayList<>(1);
		addressList.add(randomAddress());
		profile.setAddressess(addressList);
		return profile;
	}

	private PhoneNumber randomPhoneNumber() {
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setMain(true);
		char[] phone = new char[10];
		for (int i = 0; i < 10; i++) {
			phone[i] = (char) (48 + random.nextInt(10));
		}
		phoneNumber.setPhoneNumber(new String(phone));
		phoneNumber.setMain(true);
		return phoneNumber;
	}

	private Address randomAddress() {
		Address address = new Address();
		char[][] charArray = new char[2][random.nextInt(10) + 10];

		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < charArray[j].length; i++) {
				charArray[j][i] = (char) (48 + random.nextInt(30));
			}
		}
		address.setFirstLine(new String(charArray[0]));
		address.setSecondLine(new String(charArray[1]));
		address.setPostalCode("12345");
		return address;
	}

	@Override
	public Profile update(Profile t) {
		return null;
	}

}
