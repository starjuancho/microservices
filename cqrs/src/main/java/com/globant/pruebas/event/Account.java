package com.globant.pruebas.event;

import java.util.Collection;
import java.util.Currency;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Account {

	private static final Logger log = LoggerFactory.getLogger(Account.class);

	private Collection<Entry> entries = new HashSet<Entry>();
	private Currency currency;
	private AccountType accountType;

	void addEntry(Money amount, MfDate date) {
		assert (currency.equals(amount.getCurrency()));
		entries.add(EntryImpl.newEntry(amount, date));
	}

	public Account(Currency currency, AccountType type) {
		this.currency = currency;
		this.accountType = type;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public void post(Entry entry) {
		entries.add(entry);
	}

	public Money balance() {
		log.info("Getting balance for " + entries.stream().count() + " entries");
		Money balance = entries.stream().map(Entry::getAmount).reduce((x, y) -> x.add(y)).get();
		log.info("Returning balance for " + this + ": " + balance);
		return balance;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.currency.toString());
		sb.append(" - ").append(this.accountType);
		return sb.toString();
	}

}
