package com.rentalbike.challenge.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bike implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3595041486367367276L;
	@Id
    @GeneratedValue
	private Long id;
	private String description;
	private String status;
	@OneToMany (fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "bike_id")
	private Set<Image> images = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "bike_prices", joinColumns = { @JoinColumn(name = "bike_id") }, inverseJoinColumns = {
			@JoinColumn(name = "price_id") })
	private Set<Price> prices = new HashSet<>();
	@OneToMany(mappedBy = "bike")
	@JsonIgnore
	private Set<Booking> rentOrders = new HashSet<Booking>(0);
	
	public Bike() {}
	
	public Bike(String description, String status) {
		this.setDescription(description);
		this.setStatus(status);
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<Image> getImages() {
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Price> getPrices() {
		return prices;
	}
	public void setPrices(Set<Price> prices) {
		this.prices = prices;
	}
	public Set<Booking> getRentOrders() {
		return rentOrders;
	}
	public void setRentOrders(Set<Booking> rentOrders) {
		this.rentOrders = rentOrders;
	}
	
	
	
	
	
	

}
