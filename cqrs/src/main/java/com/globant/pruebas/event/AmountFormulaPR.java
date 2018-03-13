package com.globant.pruebas.event;

public class AmountFormulaPR extends PostingRule {

	protected AmountFormulaPR(AccountType type, boolean isTaxable) {
		super(type, isTaxable);
	}

	@Override
	protected Money calculateAmount(AccountingEvent evt) {
		return null;
	}

}
