package com.globant.pruebas.event;

import java.math.BigDecimal;
import java.util.Currency;

public class Money {

	private final Currency currency;
	private BigDecimal amount = BigDecimal.ZERO;
	
	private static final Currency USD = Currency.getInstance("USD"); 

	public Money() { 
		this.currency = USD;
		this.amount = BigDecimal.ZERO;
	}

	public Money(Currency currency) {
		this.currency = currency;
		this.amount = BigDecimal.ZERO;
	}
	public Money(Currency currency, BigDecimal amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if (amount == null) {
			throw new IllegalArgumentException("Cant set null to amount");
		}
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Money add(Money money) {
		if (money.getCurrency() != this.currency) {
			throw new IllegalArgumentException("Cant add money with different currencies");
		}
		if (money != null) {
			this.amount = this.amount.add(money.amount);
		}
		return this;
	}

	public Money add(BigDecimal amount) {
		if(amount != null) {
			this.amount.add(amount);
		}
		return this;
	}
	
	public static Money dollars(BigDecimal amount) {
		return new Money(USD, amount);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Money && ((Money)obj).getCurrency().equals(this.currency)) {
			return  amount.equals(((Money)obj).getAmount());
		}
		else return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(currency.toString());
		sb.append(amount);
		return sb.toString();
	}
}
