package com.jsanchezc.examples.holaspringboot;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class GreeterRestControllerTest {

	private HashMap<String, String> emptyMap;
	private HashMap<String, String> oneParameterMap;
	private HashMap<String, String> twoParametersMap;
	private HashMap<String, String> threeParametersMap;
	private HashMap<String, String> nullMap = null;
	
	private static final String keys []= {"Key1","Key2","Key3"};
	private static final String values []= {"value1","value2","value3"};
	
	@Before
	public void setUp(){
		emptyMap = new HashMap<>();
		
		oneParameterMap = new HashMap<>();
		oneParameterMap.put(keys[0], values[0]);
		
		twoParametersMap = new HashMap<>();
		twoParametersMap.put(keys[0], values[0]);
		twoParametersMap.put(keys[1], values[1]);

		threeParametersMap = new HashMap<>();
		threeParametersMap.put(keys[0], values[0]);
		threeParametersMap.put(keys[1], values[1]);
		threeParametersMap.put(keys[2], values[2]);		
	}
	
	@Test
	public void testparseParams() {
		
		GreeterRestController object = new GreeterRestController();
		
		String params = object.parseParams(nullMap);
		
		assertNotNull("String must not be null, but empty string",params);
		assertEquals("result must be emptu String","",params);

		params = object.parseParams(emptyMap);
		
		assertNotNull("String must not be null, but empty string",params);
		assertEquals("result must be emptu String","",params);

		params = object.parseParams(oneParameterMap);
		assertNotNull("String must not be null, but key-value length1",params);
		assertEquals("result must be "+keys[0]+"="+values[0],keys[0]+"="+values[0],params);

		
		params = object.parseParams(twoParametersMap);
		assertNotNull("String must not be null, but key-value length2",params);
		assertTrue("result must contain "+keys[0]+"="+values[0],params.contains(keys[0]+"="+values[0]));
		assertTrue("result must contain "+keys[1]+"="+values[1],params.contains(keys[1]+"="+values[1]));
		assertTrue("result must contain 2 pairs separated by ','",params.split(",").length==2);

		params = object.parseParams(threeParametersMap);
		assertNotNull("String must not be null, but key-value length3",params);
		assertTrue("result must contain "+keys[0]+"="+values[0],params.contains(keys[0]+"="+values[0]));
		assertTrue("result must contain "+keys[1]+"="+values[1],params.contains(keys[1]+"="+values[1]));
		assertTrue("result must contain "+keys[2]+"="+values[2],params.contains(keys[2]+"="+values[2]));
		assertTrue("result must contain 3 pairs separated by ','",params.split(",").length==3);
	}

}
