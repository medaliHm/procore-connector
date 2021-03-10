package com.procore.connector.models.webhook;


public class Metadata
{
    private int source_user_id;

    private int source_project_id;

    private String source_operation_id;

    private int source_company_id;

    private String source_application_id;

    public void setSource_user_id(int source_user_id){
        this.source_user_id = source_user_id;
    }
    public int getSource_user_id(){
        return this.source_user_id;
    }
    public void setSource_project_id(int source_project_id){
        this.source_project_id = source_project_id;
    }
    public int getSource_project_id(){
        return this.source_project_id;
    }
    public void setSource_operation_id(String source_operation_id){
        this.source_operation_id = source_operation_id;
    }
    public String getSource_operation_id(){
        return this.source_operation_id;
    }
    public void setSource_company_id(int source_company_id){
        this.source_company_id = source_company_id;
    }
    public int getSource_company_id(){
        return this.source_company_id;
    }
    public void setSource_application_id(String source_application_id){
        this.source_application_id = source_application_id;
    }
    public String getSource_application_id(){
        return this.source_application_id;
    }
}
