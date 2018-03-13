package com.globant.pruebas.policyservice.dao.mock;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Repository;

import com.globant.pruebas.policyservice.dao.AbstractDao;
import com.globant.pruebas.policyservice.model.CollectionStatus;
import com.globant.pruebas.policyservice.model.Policy;

@Repository
public class MockPolicyDao extends AbstractDao<Policy>{
	
	private final GregorianCalendar calendar;  
	
	public MockPolicyDao() {
		this.calendar = new GregorianCalendar();
	}
	
	@Override
	public Policy getById(String id) {		
		Policy policy = new Policy();
		
		CollectionStatus collectionStatus  = new CollectionStatus();
		if(random.nextBoolean()) {
			collectionStatus.setStatusCode("PAID");
			collectionStatus.setStatusCode("PAGADO");
		}else {
			collectionStatus.setStatusCode("NOTPAID");
			collectionStatus.setStatusCode("NO PAGADO");
		}
		ValidityDates dates = randomValidity();

		policy.setCollectionStatu(collectionStatus);
		policy.setPolicyHolder("Juan Sanchez Carbajal");
		policy.setPolicyNumber(id);
		policy.setUserId("juanelo");
		policy.setValidityEndDate(dates.getEnd());
		policy.setValidityStartDate(dates.getStart());
		return policy;
	}
	
	private ValidityDates randomValidity() {
		int year = 2013+random.nextInt(5);
		int month = random.nextInt(11);
		int day = month==1?random.nextInt(29):random.nextInt(30); 
		calendar.set(year,month,day);
		Date start = calendar.getTime();
		calendar.set(year+1,month,day);
		Date end = calendar.getTime();
		return new ValidityDates(start,end);
	}
		
	private class ValidityDates{
		private Date start;
		private Date end;
		
		public ValidityDates(Date start, Date end) {
			super();
			this.start = start;
			this.end = end;
		}
		public Date getStart() {
			return start;
		}

		public Date getEnd() {
			return end;
		}
	}
}
