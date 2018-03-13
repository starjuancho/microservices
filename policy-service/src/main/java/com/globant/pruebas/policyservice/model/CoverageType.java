package com.globant.pruebas.policyservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CoverageType implements Entity {
	String coverageID;
	String coverageCode;
	String coverageDescription;
	
}
