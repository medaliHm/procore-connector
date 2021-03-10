package com.procore.connector.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class FolderDataUpload {
	@JsonInclude(Include.NON_NULL)
	private String explicit_permissions;

	private Long parent_id;

	private String name;
	@JsonInclude(Include.NON_NULL)
	private String is_tracked;

	public String getExplicit_permissions() {
		return explicit_permissions;
	}

	public void setExplicit_permissions(String explicit_permissions) {
		this.explicit_permissions = explicit_permissions;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
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

}
