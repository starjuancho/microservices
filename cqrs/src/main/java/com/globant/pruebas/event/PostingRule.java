package com.globant.pruebas.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PostingRule {

	private static final Logger log = LoggerFactory.getLogger(PostingRule.class);

	private final AccountType type;
	private final boolean isTaxable;

	protected PostingRule(AccountType type, boolean isTaxable) {
		this.type = type;
		this.isTaxable = isTaxable;
	}

	abstract protected Money calculateAmount(AccountingEvent evt);

	public void process(AccountingEvent evt) {
		log.debug("Starting proceess for " + evt);
		Money amount = calculateAmount(evt); 
		makeEntry(evt, amount);
		if (isTaxable) {
			generateTax(evt);
		}
		log.debug("Finished proceess for " + evt);
	}

	private void generateTax(AccountingEvent evt) {
	}

	private void makeEntry(AccountingEvent evt, Money amount) {
		log.debug("Starting makeEntry for " + evt +", amount: " + amount);
		Entry newEntry = EntryImpl.newEntry(amount, evt.getWhenNoticed());
		evt.getSubject().addEntry(newEntry, type);
		log.debug("Finished makeEntry for " + evt +", amount: " + amount);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(type.name()).append(" - ");
		sb.append("taxable: ").append(isTaxable);
		return sb.toString();
	}
}
