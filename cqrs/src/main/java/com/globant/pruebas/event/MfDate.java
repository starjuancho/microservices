package com.globant.pruebas.event;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MfDate implements Comparable<MfDate>{
	
	private final int date;
	private final int month;
	private final int year;
	
	public MfDate() {
		Calendar cal = GregorianCalendar.getInstance();
		this.date = cal.get(Calendar.DATE);		
		this.month = cal.get(Calendar.MONTH);		
		this.year = cal.get(Calendar.YEAR);		
	}

	public MfDate(int year, int month, int date) {
		this.year = year;
		this.month = month;
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return String.format("%04d/%02d/%02d",year,month,date);  
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(String.format("%04d%02d%02d",year,month,date));
	}
	
	@Override
	public boolean equals(Object obj) { 
		if(obj!=null && obj instanceof MfDate) {
			return this.hashCode()==obj.hashCode();
		}
		return false;
	}

	public boolean before(MfDate when) {
		return this.compareTo(when)<0;
	}

	public boolean after(MfDate when) {
		return this.compareTo(when)>0;
	}

	@Override
	public int compareTo(MfDate o) {
		return this.hashCode()-o.hashCode();
	}
}
