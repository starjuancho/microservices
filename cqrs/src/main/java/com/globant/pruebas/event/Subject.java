package com.globant.pruebas.event;

public interface Subject {
	
	void addEntry(Entry arg, AccountType type) ;
	 
	Money balanceFor(AccountType key);

	void process(AccountingEvent accountingEvent) throws MissingPostingRuleException;

}
