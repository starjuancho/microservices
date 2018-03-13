package com.globant.pruebas.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SingleTemporalCollection<T> implements TemporalCollection<T> {

	private Map<MfDate, T> contents = new HashMap<>();

	private List<MfDate> milestones;

	private List<MfDate> milestones() {
		if (milestones == null) {
			calculateMilestones();
		}
		return milestones;
	}
	
	private void calculateMilestones() {
		milestones = new ArrayList<MfDate>(contents.size());
		milestones.addAll(contents.keySet());
		Collections.sort(milestones, Collections.reverseOrder());
	}

	private void clearMilestoneCache() {
		milestones = null;
	}

	@Override
	public T get(MfDate when) {
		/** returns the value that was effective on the given date */
		Iterator<MfDate> it = milestones().iterator();
		while (it.hasNext()) {
			MfDate thisDate = (MfDate) it.next();
			if (thisDate.before(when) || thisDate.equals(when))
				return contents.get(thisDate);
		}
		throw new IllegalArgumentException("no records that early");
	}

	@Override
	public void put(MfDate at, T item) {
		contents.put(at, item);
		clearMilestoneCache();
	}

	@Override
	public T get(int year, int month, int date) {
		return get(new MfDate(year, month, date));
	}

	@Override
	public T get() {
		return this.get(new MfDate());
	}

	@Override
	public void put(T item) {
		put(new MfDate(),item);
	}

}
