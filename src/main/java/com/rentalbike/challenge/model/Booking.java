package com.rentalbike.challenge.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Booking implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4122574146802062689L;
	@Id
    @GeneratedValue
	private Long id;
	@ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;
	@ManyToOne
    @JoinColumn(name = "rentorder_id")
    private RentOrder rentOrder;
	private LocalDate startRent;
	private LocalDate endRent;
	private Float price;
	private Date createdDate  = new Date();
	
	public Booking() {}
	
	 
    public Booking(Bike bike, RentOrder rentOrder) {
        this.bike = bike;
        this.rentOrder = rentOrder;
    }
	public LocalDate getStartRent() {
		return this.startRent;
	}
	public LocalDate getEndRent() {
		return endRent;
	}
	public void setEndRent(LocalDate endRent) {
		this.endRent = endRent;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	

	public Bike getBike() {
		return bike;
	}
	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public RentOrder getRentOrder() {
		return rentOrder;
	}
	public void setRentOrder(RentOrder rentOrder) {
		this.rentOrder = rentOrder;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public void setStartRent(LocalDate startRent) {
		this.startRent = startRent;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bike == null) ? 0 : bike.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((endRent == null) ? 0 : endRent.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((rentOrder == null) ? 0 : rentOrder.hashCode());
		result = prime * result + ((startRent == null) ? 0 : startRent.hashCode());
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
		Booking other = (Booking) obj;
		if (bike == null) {
			if (other.bike != null)
				return false;
		} else if (!bike.equals(other.bike))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (endRent == null) {
			if (other.endRent != null)
				return false;
		} else if (!endRent.equals(other.endRent))
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
		if (rentOrder == null) {
			if (other.rentOrder != null)
				return false;
		} else if (!rentOrder.equals(other.rentOrder))
			return false;
		if (startRent == null) {
			if (other.startRent != null)
				return false;
		} else if (!startRent.equals(other.startRent))
			return false;
		return true;
	}
	
	
	
	
	

}
