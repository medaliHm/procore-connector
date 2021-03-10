package com.procore.connector.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RootFolder {

	@Id
	private String id;

	@OneToMany(mappedBy = "parentFolder", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubFolders> folders;

	private String has_children;

	private String is_deleted;

	private String updated_at;
	@JsonIgnore
	private String project_id;

	private boolean has_children_folders;

	private String read_only;

	private String parent_id;

	private String name;

	private String is_tracked;

	private boolean has_children_files;

	@OneToMany(mappedBy = "parentFolder", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Files> files;

	private String name_with_path;

	private String is_recycle_bin;

	@JsonIgnore
	private String localPath;

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

	public String getIs_recycle_bin() {
		return is_recycle_bin;
	}

	public void setIs_recycle_bin(String is_recycle_bin) {
		this.is_recycle_bin = is_recycle_bin;
	}

	public List<SubFolders> getFolders() {
		return folders;
	}

	public void setFolders(List<SubFolders> folders) {
		this.folders = folders;
	}

	public String getHas_children() {
		return has_children;
	}

	public void setHas_children(String has_children) {
		this.has_children = has_children;
	}

	public String getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public boolean getHas_children_folders() {
		return has_children_folders;
	}

	public void setHas_children_folders(boolean has_children_folders) {
		this.has_children_folders = has_children_folders;
	}

	public String getRead_only() {
		return read_only;
	}

	public void setRead_only(String read_only) {
		this.read_only = read_only;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
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

	public boolean getHas_children_files() {
		return has_children_files;
	}

	public void setHas_children_files(boolean has_children_files) {
		this.has_children_files = has_children_files;
	}

	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
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
