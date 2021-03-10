package com.procore.connector.models.webhook;

import java.math.BigInteger;

public class Event {

	    private BigInteger user_id;

	    private String ulid;

	    private String timestamp;

	    private String resource_name;

	    private BigInteger resource_id;

	    private BigInteger project_id;

	    private Metadata metadata;

	    private BigInteger id;

	    private String event_type;

	    private BigInteger company_id;

	    private String api_version;

	    public void setUser_id(BigInteger user_id){
	        this.user_id = user_id;
	    }
	    public BigInteger getUser_id(){
	        return this.user_id;
	    }
	    public void setUlid(String ulid){
	        this.ulid = ulid;
	    }
	    public String getUlid(){
	        return this.ulid;
	    }
	    public void setTimestamp(String timestamp){
	        this.timestamp = timestamp;
	    }
	    public String getTimestamp(){
	        return this.timestamp;
	    }
	    public void setResource_name(String resource_name){
	        this.resource_name = resource_name;
	    }
	    public String getResource_name(){
	        return this.resource_name;
	    }
	    public void setResource_id(BigInteger resource_id){
	        this.resource_id = resource_id;
	    }
	    public BigInteger getResource_id(){
	        return this.resource_id;
	    }
	    public void setProject_id(BigInteger project_id){
	        this.project_id = project_id;
	    }
	    public BigInteger getProject_id(){
	        return this.project_id;
	    }
	    public void setMetadata(Metadata metadata){
	        this.metadata = metadata;
	    }
	    public Metadata getMetadata(){
	        return this.metadata;
	    }
	    public void setId(BigInteger id){
	        this.id = id;
	    }
	    public BigInteger getId(){
	        return this.id;
	    }
	    public void setEvent_type(String event_type){
	        this.event_type = event_type;
	    }
	    public String getEvent_type(){
	        return this.event_type;
	    }
	    public void setCompany_id(BigInteger company_id){
	        this.company_id = company_id;
	    }
	    public BigInteger getCompany_id(){
	        return this.company_id;
	    }
	    public void setApi_version(String api_version){
	        this.api_version = api_version;
	    }
	    public String getApi_version(){
	        return this.api_version;
	    }
	}
