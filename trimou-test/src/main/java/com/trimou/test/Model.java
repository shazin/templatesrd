package com.trimou.test;

public class Model {

	private Integer propertyId;	
	
	private Double propertyPrice;
		
	private String type;	

	public Model(Integer propertyId, Double propertyPrice, String type) {
		super();
		this.propertyId = propertyId;
		this.propertyPrice = propertyPrice;
		this.type = type;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Double getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(Double propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
