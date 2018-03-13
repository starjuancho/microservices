package com.globant.example.dynamotest.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.globant.example.dynamotest.Application;
import com.globant.example.dynamotest.model.ProductInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@TestPropertySource(properties = { "amazon.dynamodb.endpoint=http://localhost:8000/", "amazon.aws.accesskey=test1",
		"amazon.aws.secretkey=test231" })
public class ProductInfoRepositoryIntegrationTest {

	private DynamoDBMapper dynamoDBMapper;
	private static final Logger LOG = LoggerFactory.getLogger(ProductInfoRepositoryIntegrationTest.class);

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private ProductInfoRepository repository;

	private static final String EXPECTED_COST = "20";
	private static final String EXPECTED_PRICE = "50";

	@Before
	public void setUp() throws Exception {
		LOG.info("Starting @Before setUp");
		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		Object table = dynamoDBMapper.getTableModel(ProductInfo.class);

		LOG.info("table: " + table);

		if (table == null) {
			CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductInfo.class);
			tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
			amazonDynamoDB.createTable(tableRequest);
		}

		dynamoDBMapper.batchDelete((List<ProductInfo>) repository.findAll());
		LOG.info("Finished @Before setUp");

	}

//	@Test
	public void tesSave() {
		LOG.info("Starting @Test testSave");
		ProductInfo dave = new ProductInfo(EXPECTED_COST, EXPECTED_PRICE);
		repository.save(dave);

		List<ProductInfo> result = (List<ProductInfo>) repository.findAll();
		result.stream().forEach(product -> LOG.info(String.format("Product: %s%n", product)));

		assertTrue("Not empty", result.size() > 0);
		assertTrue("Contains item with expected cost", result.get(0).getCost().equals(EXPECTED_COST));
		
		LOG.info("Finished @Test testSave");
	}
	
//	@Test
	public void tesFindByMRSP() {
		LOG.info("Starting @tesFindByMRSP");
		ProductInfo dave = new ProductInfo(EXPECTED_COST, EXPECTED_PRICE);
		repository.save(dave);
		LOG.info(String.format("Saved %s" , dave));
		dave.setId(null);
		repository.save(dave);
		LOG.info(String.format("Saved %s" , dave));

		List<ProductInfo> result = repository.findByMsrp(EXPECTED_PRICE);
		assertTrue("Not size 2", result.size()==2);
		assertTrue("Contains item with expected cost", result.get(0).getMsrp().equals(EXPECTED_PRICE));
		
		LOG.info("Finished @Test tesFindByMRSP");
	}
	
		
	@Test
	public void tesFindBetweenCreateTime() {
		LOG.info("Starting @tesFindBetweenCreateTime");
		
		Long prefix = 151795954L;

		ProductInfo beforePeriod = new ProductInfo(EXPECTED_COST, EXPECTED_PRICE);
		repository.save(beforePeriod);
		
		Long beginTime = System.currentTimeMillis();
		LOG.info(String.format("Begin Time: %d",(beginTime - prefix)));
		
		ProductInfo between1 = new ProductInfo(EXPECTED_COST, EXPECTED_PRICE);
		repository.save(between1);

		ProductInfo between2 = new ProductInfo(EXPECTED_COST, EXPECTED_PRICE);
		repository.save(between2);


		Long endTime = System.currentTimeMillis();
		LOG.info(String.format("EndTime: %d",(endTime - prefix)));

		ProductInfo afeterPeriod = new ProductInfo(EXPECTED_COST, EXPECTED_PRICE);
		repository.save(afeterPeriod);

		List<ProductInfo> results = repository.findByCreateTimeBetween(beginTime, endTime);
		assertTrue("Not size 2", results.size()==2);

		assertFalse("Doesn't contain item created before period", results.contains(beforePeriod));
		
		results.forEach(result -> LOG.info(String.format("%s @%d",result.getId(),(result.getCreateTime() - prefix))));
		
		assertTrue("Contains item created between period", results.contains(between1));
		assertTrue("Contains item created between period", results.contains(between2));

		assertFalse("Doesn't contain item created after period", results.contains(afeterPeriod));
		
		LOG.info("Finished @Test tesFindBetweenCreateTime");
	}

}
