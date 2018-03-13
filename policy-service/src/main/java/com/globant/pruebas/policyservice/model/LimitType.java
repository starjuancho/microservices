package com.globant.pruebas.policyservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LimitType implements Entity{

	private String idType; 
	private String code; 
	private LimitTypeEnum type;
	
	public String getIdType() {
		return idType;
	}



	public void setIdType(String idType) {
		this.idType = idType;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public LimitTypeEnum getType() {
		return type;
	}



	public void setType(LimitTypeEnum type) {
		this.type = type;
	}



	public static enum LimitTypeEnum{
		LUC("Limite Unico Combinado"),
		LPE("Per Event Limit");
		
		LimitTypeEnum(String desc){
			this.description = desc;
		}

		private String description;
		
		public String getDescription() {
			return this.description;
		}
		
	}
}
