package com.globant.pruebas.policyservice.model;

import java.util.Currency;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AmountLimitedCoverage extends Coverage {

	Currency insuredAmount;
	LimitType limitType;
}
