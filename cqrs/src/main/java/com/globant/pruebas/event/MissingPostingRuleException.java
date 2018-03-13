package com.globant.pruebas.event;

public class MissingPostingRuleException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private final AccountingEvent event;
	private final ServiceAgreement serviceAgreement;
	
	public MissingPostingRuleException(ServiceAgreement agreement, AccountingEvent event) {
		super();
		this.serviceAgreement = agreement;
		this.event = event;
	}

	public AccountingEvent getEvent() {
		return event;
	}

	public ServiceAgreement getServiceAgreement() {
		return serviceAgreement;
	}

}
