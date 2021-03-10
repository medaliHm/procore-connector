package com.procore.connector.models.webhook;

public class Event {

	    private int user_id;

	    private String ulid;

	    private String timestamp;

	    private String resource_name;

	    private int resource_id;

	    private int project_id;

	    private Metadata metadata;

	    private int id;

	    private String event_type;

	    private int company_id;

	    private String api_version;

	    public void setUser_id(int user_id){
	        this.user_id = user_id;
	    }
	    public int getUser_id(){
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
	    public void setResource_id(int resource_id){
	        this.resource_id = resource_id;
	    }
	    public int getResource_id(){
	        return this.resource_id;
	    }
	    public void setProject_id(int project_id){
	        this.project_id = project_id;
	    }
	    public int getProject_id(){
	        return this.project_id;
	    }
	    public void setMetadata(Metadata metadata){
	        this.metadata = metadata;
	    }
	    public Metadata getMetadata(){
	        return this.metadata;
	    }
	    public void setId(int id){
	        this.id = id;
	    }
	    public int getId(){
	        return this.id;
	    }
	    public void setEvent_type(String event_type){
	        this.event_type = event_type;
	    }
	    public String getEvent_type(){
	        return this.event_type;
	    }
	    public void setCompany_id(int company_id){
	        this.company_id = company_id;
	    }
	    public int getCompany_id(){
	        return this.company_id;
	    }
	    public void setApi_version(String api_version){
	        this.api_version = api_version;
	    }
	    public String getApi_version(){
	        return this.api_version;
	    }
	}
