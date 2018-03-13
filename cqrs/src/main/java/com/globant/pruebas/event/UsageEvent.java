package com.globant.pruebas.event;

public class UsageEvent extends AccountingEvent {

	private Double amount;

	public UsageEvent(Double amount, MfDate whenOccurred, MfDate whenNoticed, Customer customer) {
		super(EventType.USAGE, whenOccurred, whenNoticed, customer);
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}
	
}
