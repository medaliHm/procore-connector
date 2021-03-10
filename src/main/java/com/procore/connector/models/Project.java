package com.procore.connector.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Project {

	@Id
	private String id;

	private String total_value;

	private String photo_id;

	private String city;

	private String latitude;

	private String county;

	private String created_at;

	private String origin_id;

	private String project_region_id;

	private String updated_at;

	private String project_bid_type_id;

	private String owners_project_id;

	private String accounting_project_number;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
	private Company company;

	private String estimated_value;

	private String longitude;

	private String start_date;

	private String zip;

	private String address;

	private String designated_market_area;

	private String active;

	private String display_name;

	private String project_owner_type_id;

	private String country_code;

	private String origin_code;

	private String project_number;

	private String stage;

	private String phone;

	private String origin_data;

	private String name;

	private String store_number;

	private String completion_date;

	private String state_code;
	
	

	public String getTotal_value() {
		return total_value;
	}

	public void setTotal_value(String total_value) {
		this.total_value = total_value;
	}

	public String getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(String photo_id) {
		this.photo_id = photo_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getOrigin_id() {
		return origin_id;
	}

	public void setOrigin_id(String origin_id) {
		this.origin_id = origin_id;
	}

	public String getProject_region_id() {
		return project_region_id;
	}

	public void setProject_region_id(String project_region_id) {
		this.project_region_id = project_region_id;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getProject_bid_type_id() {
		return project_bid_type_id;
	}

	public void setProject_bid_type_id(String project_bid_type_id) {
		this.project_bid_type_id = project_bid_type_id;
	}

	public String getOwners_project_id() {
		return owners_project_id;
	}

	public void setOwners_project_id(String owners_project_id) {
		this.owners_project_id = owners_project_id;
	}

	public String getAccounting_project_number() {
		return accounting_project_number;
	}

	public void setAccounting_project_number(String accounting_project_number) {
		this.accounting_project_number = accounting_project_number;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEstimated_value() {
		return estimated_value;
	}

	public void setEstimated_value(String estimated_value) {
		this.estimated_value = estimated_value;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignated_market_area() {
		return designated_market_area;
	}

	public void setDesignated_market_area(String designated_market_area) {
		this.designated_market_area = designated_market_area;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getProject_owner_type_id() {
		return project_owner_type_id;
	}

	public void setProject_owner_type_id(String project_owner_type_id) {
		this.project_owner_type_id = project_owner_type_id;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getOrigin_code() {
		return origin_code;
	}

	public void setOrigin_code(String origin_code) {
		this.origin_code = origin_code;
	}

	public String getProject_number() {
		return project_number;
	}

	public void setProject_number(String project_number) {
		this.project_number = project_number;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrigin_data() {
		return origin_data;
	}

	public void setOrigin_data(String origin_data) {
		this.origin_data = origin_data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStore_number() {
		return store_number;
	}

	public void setStore_number(String store_number) {
		this.store_number = store_number;
	}

	public String getCompletion_date() {
		return completion_date;
	}

	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

}
