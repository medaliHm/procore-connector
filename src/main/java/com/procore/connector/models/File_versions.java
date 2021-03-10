package com.procore.connector.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class File_versions {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prostore_file_id", referencedColumnName = "id")
	private ProstoreFile prostore_file;

	private String notes;

	private String size;

	private String file_id;

	private String created_at;

	@Id
	private String id;


    
	private String url;
	private String number;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "files_identifier")
	private Files file;

	public Files getFile() {
		return file;
	}

	public void setFile(Files file) {
		this.file = file;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ProstoreFile getProstore_file() {
		return prostore_file;
	}

	public void setProstore_file(ProstoreFile prostore_file) {
		this.prostore_file = prostore_file;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public CreatedBy getCreated_by() {
//		return created_by;
//	}
//
//	public void setCreated_by(CreatedBy created_by) {
//		this.created_by = created_by;
//	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
