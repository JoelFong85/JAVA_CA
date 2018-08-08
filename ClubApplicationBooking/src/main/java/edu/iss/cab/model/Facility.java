package edu.iss.cab.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "facility")
public class Facility {

	@Id
	@Column(name = "facility_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer facilityId;
	@Basic(optional = false)
	@Column(name = "facility_name")
	private String facilityName;
	@Basic(optional = false)
	private String description;
	@Basic(optional = false)
	@Range(min = 1, max = 100000)
	private Integer price;
	private boolean status;
	
	@OneToMany(mappedBy="facility", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Booking> bookings = new ArrayList<Booking>();

	public Integer getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facility_name) {
		this.facilityName = facility_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStaus(boolean status) {
		this.status = status;
	}
}
