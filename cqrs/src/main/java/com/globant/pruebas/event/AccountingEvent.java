package com.globant.pruebas.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountingEvent {
	
	private static final Logger log = LoggerFactory.getLogger(AccountingEvent.class);

	protected EventType eventType;
	protected MfDate whenOccurred;
	protected MfDate whenNoticed;
	protected Subject subject;

	protected boolean processed;

	public AccountingEvent(EventType eventType, MfDate whenOccurred, MfDate whenNoticed, Subject subject) {
		super();
		this.eventType = eventType;
		this.whenOccurred = whenOccurred;
		this.whenNoticed = whenNoticed;
		this.setSubject(subject);
	}

	public EventType getEventType() {
		return this.eventType;
	}

	public MfDate getWhenOccurred() {
		return whenOccurred;
	}

	public MfDate getWhenNoticed() {
		return whenNoticed;
	}

	public void setWhenNoticed(MfDate whenNoticed) {
		this.whenNoticed = whenNoticed;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public void process() throws MissingPostingRuleException {
		log.debug("Starting process for " + this);
		//if (adjustedEvent != null)
			//adjustedEvent.reverse();
		subject.process(this);
		markProcessed();
		log.debug("Finished process for "+ this);
	}

	private void markProcessed() {
		this.processed = true;
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.eventType.toString()).append(" - "); 
		sb.append(this.subject).append(" - "); 
		sb.append(this.whenNoticed).append(" - "); 
		sb.append(processed?"Processed":"Not Processed");
		return sb.toString();
	}
}
