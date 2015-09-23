/**
 * 
 */
package com.compton.pizza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Pizza POJO, just the basics.
 * 
 * @author mike
 *
 */
@Entity
public class Pizza {
	private Long id;
	private String size;
	private Double price;
	private Double toppingPrice;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(nullable = false)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(nullable = false)
	public Double getToppingPrice() {
		return toppingPrice;
	}

	public void setToppingPrice(Double toppingPrice) {
		this.toppingPrice = toppingPrice;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((toppingPrice == null) ? 0 : toppingPrice.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pizza))
			return false;
		Pizza other = (Pizza) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (toppingPrice == null) {
			if (other.toppingPrice != null)
				return false;
		} else if (!toppingPrice.equals(other.toppingPrice))
			return false;
		return true;
	}
}
