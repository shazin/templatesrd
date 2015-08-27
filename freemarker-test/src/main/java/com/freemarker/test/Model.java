package com.freemarker.test;

public class Model {

	private Integer propertyId;
	
	private String propertyName;
	
	private Double propertyPrice;
	
	public Model(Integer propertyId, String propertyName, Double propertyPrice) {
		super();
		this.propertyId = propertyId;
		this.propertyName = propertyName;
		this.propertyPrice = propertyPrice;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Double getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(Double propertyPrice) {
		this.propertyPrice = propertyPrice;
	}
	
	
}
