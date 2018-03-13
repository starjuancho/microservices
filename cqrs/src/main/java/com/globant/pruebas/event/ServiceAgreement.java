package com.globant.pruebas.event;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceAgreement {

	private static final Logger log = LoggerFactory.getLogger(ServiceAgreement.class);

	private Map<EventType, TemporalCollection<PostingRule>> postingRules = new HashMap<>();
	
	public void process(AccountingEvent e) throws MissingPostingRuleException {
		
		getPostingRule(e).process(e);
	}

	private PostingRule getPostingRule(AccountingEvent event) throws MissingPostingRuleException {
		log.debug("getPostingRule for event "+ event);
		PostingRule rule = null;
		final TemporalCollection<PostingRule> rules = getRulesTemporalCollectionFor(event.getEventType());
		if (rules == null) {
			throw new MissingPostingRuleException(this, event);
		}
		try {
			rule =rules.get(event.getWhenOccurred());
		} catch (IllegalArgumentException e) {
			throw new MissingPostingRuleException(this, event);
		}
		log.debug("getPostingRule for event "+ event+": " + rule);
		return rule;
	}

	public void addPostingRule(EventType eventType, PostingRule rule, MfDate date) {
		if (postingRules.get(eventType) == null) {
			postingRules.put(eventType, new SingleTemporalCollection<PostingRule>());
		}
		getRulesTemporalCollectionFor(eventType).put(date, rule);
	}

	private TemporalCollection<PostingRule> getRulesTemporalCollectionFor(EventType eventType) {
		TemporalCollection<PostingRule> result = (TemporalCollection<PostingRule>) postingRules.get(eventType);
		assert result != null;
		return result;
	}
			
}
