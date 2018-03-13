package com.globant.example.dynamotest.repositories;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.DeleteTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class MoviesCreateTable {
	private static final Logger LOG = LoggerFactory.getLogger(MoviesCreateTable.class);
	private static final String TABLE_NAME = "Movies";

	private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
			new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2")).build();

	private static DynamoDB dynamoDB = new DynamoDB(client);

	
	public Table createTable() throws Exception {
		LOG.info("Starting createTable");

		KeySchemaElement yearKey = new KeySchemaElement("year", KeyType.HASH);
		KeySchemaElement titleKey = new KeySchemaElement("title", KeyType.RANGE);

		AttributeDefinition yearAttrib = new AttributeDefinition("year", ScalarAttributeType.N);
		AttributeDefinition titleAttrib = new AttributeDefinition("title", ScalarAttributeType.S);
		
		Table table = null;
		try {
			System.out.println("Attempting to create table; please wait...");
			table = dynamoDB.createTable(TABLE_NAME, Arrays.asList(yearKey, titleKey),
					Arrays.asList(yearAttrib, titleAttrib), new ProvisionedThroughput(10L, 10L));
			table.waitForActive();
			System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

		} catch (Exception e) {
			System.err.println("Unable to create table: ");
			System.err.println(e.getMessage());
		}
		LOG.info("Finished createTable with table: " + table);
		return table;
	}
	
	
	public boolean deleteTable() throws InterruptedException {
		LOG.info("Starting deleteTable");
		Table table = dynamoDB.getTable(TABLE_NAME);
		DeleteTableResult result = table.delete();
		table.waitForDelete();
		LOG.info("Finished deleteTable with code: " + result.getSdkHttpMetadata().getHttpStatusCode());
		return result.getSdkHttpMetadata().getHttpStatusCode()==200;
	}
	
	public Table getTable() {
		LOG.info("Starting  getTable");
		Table  table =  dynamoDB.getTable(TABLE_NAME);
		LOG.info("FInished  getTable: " + table);
		return table; 
	}

	
}
