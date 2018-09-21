package com.rentalbike.challenge.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentalbike.challenge.constant.Constant;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Price implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 331675843653973543L;
	@Id
    @GeneratedValue
	private Long id;
	@ApiModelProperty(example = Constant.PRICE_DAILY+","+Constant.PRICE_HOUR+","+Constant.PRICE_WEEKLY)
    private String type;
	private Float price;
	@ManyToMany(mappedBy = "prices")
	@JsonIgnore
    private Set<Bike> bikes = new HashSet<>();
	
	public Price(){};
	
	public Price(String type, Float price) {
		this.setPrice(price);
		this.setType(type);
	}
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Bike> getBikes() {
		return bikes;
	}
	public void setBikes(Set<Bike> bikes) {
		this.bikes = bikes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bikes == null) ? 0 : bikes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (bikes == null) {
			if (other.bikes != null)
				return false;
		} else if (!bikes.equals(other.bikes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
	
	

}
