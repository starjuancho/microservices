package com.globant.pruebas.event;

public class EntryImpl implements Entry {

	private final Money amount;
	private final MfDate bookingDate;

	private EntryImpl(Money amount, MfDate date) {
		this.amount = amount;
		this.bookingDate = date;
	}

	public static Entry newEntry(Money amount, MfDate date) {
		return new EntryImpl(amount, date);
	}

	@Override
	public Money getAmount() {
		return this.amount;
	}

	@Override
	public MfDate getBookingDate() {
		return this.bookingDate;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(bookingDate.toString()).append(" - ").append(amount);
		return sb.toString();
	}
}
