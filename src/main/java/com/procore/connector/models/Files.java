package com.procore.connector.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Files {
	@Id
	private String id;
	@OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<File_versions> file_versions;

	private String description;

	private String created_at;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "check_out_id", referencedColumnName = "id")
	private CheckOutBy checked_out_by;

	private String is_deleted;

	private String size;

	private String updated_at;

	private String parent_id;

	private String checked_out_until;

	private String file_type;

	private String name;

	private String is_tracked;

	private String legacy_id;

	private String name_with_path;

	@JsonIgnore
	private String localPath;
	@ManyToOne(fetch = FetchType.LAZY)
	private RootFolder parentFolder;

	private String project_id;

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public RootFolder getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(RootFolder parentFolder) {
		this.parentFolder = parentFolder;
	}

	public List<File_versions> getFile_versions() {
		return file_versions;
	}

	public void setFile_versions(List<File_versions> file_versions) {
		this.file_versions = file_versions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public CheckOutBy getChecked_out_by() {
		return checked_out_by;
	}

	public void setChecked_out_by(CheckOutBy checked_out_by) {
		this.checked_out_by = checked_out_by;
	}

	public String getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getChecked_out_until() {
		return checked_out_until;
	}

	public void setChecked_out_until(String checked_out_until) {
		this.checked_out_until = checked_out_until;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIs_tracked() {
		return is_tracked;
	}

	public void setIs_tracked(String is_tracked) {
		this.is_tracked = is_tracked;
	}

	public String getLegacy_id() {
		return legacy_id;
	}

	public void setLegacy_id(String legacy_id) {
		this.legacy_id = legacy_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName_with_path() {
		return name_with_path;
	}

	public void setName_with_path(String name_with_path) {
		this.name_with_path = name_with_path;
	}
}
