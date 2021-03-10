package com.procore.connector.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SubFolders {

	@Id
	private String id;

	private String[] folders;

	private String has_children;

	private String is_deleted;

	private String updated_at;

	private boolean has_children_folders;

	private String read_only;

	private String parent_id;

	private String name;

	private String is_tracked;

	private boolean has_children_files;

	private String[] files;

	private String name_with_path;

	private String is_recycle_bin;

	@ManyToOne(fetch = FetchType.LAZY)
	private RootFolder parentFolder;

	public RootFolder getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(RootFolder parentFolder) {
		this.parentFolder = parentFolder;
	}

	public String getIs_recycle_bin() {
		return is_recycle_bin;
	}

	public void setIs_recycle_bin(String is_recycle_bin) {
		this.is_recycle_bin = is_recycle_bin;
	}

	public String[]  getFolders() {
		return folders;
	}

	public void setFolders(String[]  folders) {
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

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
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
