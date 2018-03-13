package com.globant.pruebas.event;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer implements Subject {

	private static final Logger log = LoggerFactory.getLogger(Customer.class);

	private ServiceAgreement serviceAgreement;
	private Map<AccountType, Account> accounts, savedRealAccounts;
	private String name;

	public Customer(String name) {
		this.name = name;
		setUpAccounts();
	}

	private void setUpAccounts() {
		accounts = new HashMap<AccountType, Account>();
		for (AccountType type : AccountType.values())
			accounts.put(type, new Account(Currency.getInstance("USD"), type));
	}

	public Account accountFor(AccountType type) {
		assert accounts.containsKey(type);
		return accounts.get(type);
	}

	public void addEntry(Entry arg, AccountType type) {
		log.debug("addEntry " +  arg + " to " + this);
		accountFor(type).post(arg);
	}

	public Money balanceFor(AccountType key) {
		return accountFor(key).balance();
	}

	public ServiceAgreement getServiceAgreement() {
		return serviceAgreement;
	}

	public void setServiceAgreement(ServiceAgreement serviceAgreement) {
		this.serviceAgreement = serviceAgreement;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public void process(AccountingEvent accountingEvent) throws MissingPostingRuleException {
		log.debug("Starting process for accountingEvent " + accountingEvent);
		this.serviceAgreement.process(accountingEvent);
		log.debug("Finished process for accountingEvent " + accountingEvent);
	}

}
