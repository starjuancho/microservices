package com.globant.pruebas.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventList<T extends AccountingEvent> {
	private static final Logger log= LoggerFactory.getLogger(EventList.class);

	List<T> eventList = new ArrayList<>();

	public void add(T event) {
		this.eventList.add(event);
	}

	public void process() {
		log.debug("Starting process for event list size: " +eventList.size());
		for (AccountingEvent event : unprocessedEvents()) {
			try {
				event.process();
			} catch (Exception e) {
				logProcessingError(event, e);
				throw new RuntimeException(e);
			}
		}
		log.debug("Finished process for event list size: " +eventList.size());
	}

	private void logProcessingError(AccountingEvent event, Exception e) {
		System.out.println(e);
	}

	private Collection<T> unprocessedEvents() {
		return eventList;
	}
}
