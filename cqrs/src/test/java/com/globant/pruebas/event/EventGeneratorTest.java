package com.globant.pruebas.event;


import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class EventGeneratorTest {

	@Test
	public void test () {
		Customer customer = new Customer("John Connor");
		ServiceAgreement serviceAgreement = new ServiceAgreement();
		customer.setServiceAgreement(serviceAgreement);
		
		serviceAgreement.addPostingRule(EventType.USAGE, new MultiplyByRatePR(AccountType.BASE_USAGE, false, 10), new MfDate(1999, 10, 1));
		serviceAgreement.addPostingRule(EventType.USAGE, new MultiplyByRatePR(AccountType.BASE_USAGE, false, 15), new MfDate(2000, 1, 1));
		//serviceAgreement.addPostingRule(EventType.SERVICE_CALL, ), new MfDate(1999, 10, 1));


		EventList<AccountingEvent>events = new EventList<>();
		
		events.add(new UsageEvent(50D, new MfDate(1999, 11, 1), new MfDate(1999, 10, 15), customer)); //500
		events.add(new UsageEvent(45D, new MfDate(1999, 12, 1), new MfDate(1999, 10, 15), customer)); //450
		events.add(new UsageEvent(55D, new MfDate(2000, 01, 1), new MfDate(1999, 10, 15), customer)); //825
		events.add(new UsageEvent(40D, new MfDate(2000, 02, 1), new MfDate(1999, 10, 15), customer)); //600
				
		events.process();
		Money expected = Money.dollars(BigDecimal.valueOf(2375.0));
		 assertEquals(expected, customer.balanceFor(AccountType.BASE_USAGE));
	}

}
