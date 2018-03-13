package com.globant.pruebas.java;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class TestBasicThings {

	@Test
	public void testGenerics() {
		List<Float> myList = Arrays.asList(1.0f, 3.0f, 5.0f, 7.0f);
		System.out.print(sum(myList));
	}

	private double sum(Collection<? extends Number> col) {
		double sum = 0;
		for (Number num : col) {
			sum += num.doubleValue();
		}
		return sum;
	}

}
