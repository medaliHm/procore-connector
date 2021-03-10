package com.procore.connector.service.fromProcore;

import java.util.List;

import com.procore.connector.models.Company;
import com.procore.connector.models.Files;
import com.procore.connector.models.Project;
import com.procore.connector.models.RootFolder;
import com.procore.connector.models.dto.AccessToken;



public interface FromProcoreToSharedService {

	public List<Project> getAllProjectByCompany(String id_company);
	public List<Company> getAllCompanies();

	public RootFolder getRootFolderById(String idFolder, String projectId);

	public RootFolder getRootFolderByProjectId(String project_id);
	public Files getFilesById(String id,String projectId);

	public AccessToken getOauth2AccessTokenAccountService();

}
