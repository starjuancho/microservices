package com.globant.pruebas.event;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class MfDateTest {

	
	private static final String FORMATTED_DATE = "1999/10/01";
	private static final int HASHED_DATE = 19991001;
	private static final int YEAR = 1999;
	private static final int MONTH = 10;
	private static final int DATE = 1;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testHashCode() {
		MfDate date = new MfDate(YEAR,MONTH, DATE);
		assertTrue("HashDate must be" + HASHED_DATE, HASHED_DATE==date.hashCode());		
	}

	@Test
	public void testToString() {
		MfDate date = new MfDate(YEAR,MONTH, DATE); 
		assertTrue(date.toString() + " must be " + FORMATTED_DATE , FORMATTED_DATE.equals(date.toString()));
	}

	@Test
	public void testBefore() {
		MfDate date = new MfDate(YEAR,MONTH, DATE);
		MfDate beforeDate = new MfDate(YEAR,MONTH-1, DATE);
		assertTrue(beforeDate.toString() + " is before than "+ date.toString(),beforeDate.before(date));
	}

	@Test
	public void testAfter() {
		MfDate date = new MfDate(YEAR,MONTH, DATE);
		MfDate afterDate = new MfDate(YEAR,MONTH, DATE+1);
		assertTrue(afterDate.toString() + " is after than "+ date.toString(),afterDate.after(date));
	}

	@Test
	public void testCompareTo() {
		MfDate beforedate = new MfDate(YEAR,MONTH, DATE);
		MfDate afterDate = new MfDate(YEAR,MONTH, DATE+1);
		assertTrue(beforedate.compareTo(afterDate)<0);
	}

}
