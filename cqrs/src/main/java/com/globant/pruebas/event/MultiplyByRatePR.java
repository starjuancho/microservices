package com.globant.pruebas.event;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiplyByRatePR extends PostingRule {
	
	private static final Logger log = LoggerFactory.getLogger(PostingRule.class);
	private final double rate;
	
	public MultiplyByRatePR(AccountType type, boolean isTaxable, double rate) {
		super(type, isTaxable);
		this.rate = rate;
	}

	@Override
	protected Money calculateAmount(AccountingEvent evt) {
		log.debug("calculateAmount for "+evt);
		UsageEvent usageEvent = (UsageEvent) evt;
		Money amount = Money.dollars(BigDecimal.valueOf(usageEvent.getAmount() * rate)); 
		log.debug("calculateAmount for "+evt + ":  " + amount);
        return amount;
	}


}
