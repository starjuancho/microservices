package com.globant.example.dynamotest.repositories;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.document.Table;

public class MoviesCreateTableTest {

	private static final Logger LOG = LoggerFactory.getLogger(MoviesCreateTableTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	MoviesCreateTable creator = new MoviesCreateTable();

	@Test
	public void testCreateTable() {
		LOG.info("Starting testCreateTable");
		Table table = creator.getTable();
		if(table!=null && table.getDescription()!=null) {
			LOG.info("table.info() " + table.getDescription());
			try {
				if(creator.deleteTable()==false) {
					fail("Couldn't delete existing table");
				}
			} catch (InterruptedException e) {
				fail("Couldn't delete existing table: " + e.getMessage());
				
			}
		}
		try {
			table = creator.createTable();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertTrue("table mustn't be null", table != null);
		LOG.info("Finished  testCreateTable");
	}

	@Test
	public void testDeleteTable() {
		Table table = creator.getTable();
		if(table == null && table.getDescription()==null) {
			try {
				creator.createTable();
			}catch (Exception e) {
				fail(e.getMessage());
			}
		}
		boolean deleted = false;
		try {
			deleted = creator.deleteTable();
		} catch (InterruptedException e) {
			fail("Couldn't delete table: "+ e);
			
		}
		assertTrue("table was deleted", deleted);
	}

}
