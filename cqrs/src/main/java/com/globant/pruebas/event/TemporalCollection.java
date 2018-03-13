package com.globant.pruebas.event;

public interface TemporalCollection<T> {
	// get and put at a supplied date
	T get(MfDate when);

	void put(MfDate at, T item);

	T get(int year, int month, int date);

	// get and put at today's date
	T get();

	void put(T item);
}
