package com.procore.connector.service.fromProcore;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.procore.connector.models.Company;
import com.procore.connector.models.Files;
import com.procore.connector.models.Project;
import com.procore.connector.models.RootFolder;
import com.procore.connector.models.dto.AccessToken;
import com.procore.connector.models.dto.Credentials;
import com.procore.connector.repository.UsersRepository;


@Service
public class FromProcoreToSharedServiceImpl implements FromProcoreToSharedService {
	Logger logger = LogManager.getLogger(FromProcoreToSharedServiceImpl.class);

	@Autowired
	@Qualifier("json-rest")
	RestTemplate webClient;
	@Autowired
	@Qualifier("login-rest")
	RestTemplate loginClient;

	@Value("${endpoint.allprojects}")
	private String allprojects;
	@Value("${endpoint.folders}")
	private String showFolder;
	@Value("${endpoint.baseUri}")
	private String baseUri;
	@Value("${endpoint.loginUri}")
	private String loginUri;

	@Autowired
	private UsersRepository usersRepo;

	@Value("${clientId}")
	private String clientId;
	@Value("${clientSecret}")
	private String clientSecret;
	@Value("${redirectUri}")
	private String redirectUri;

	@Override
	public List<Project> getAllProjectByCompany(String id_company) {
		// TODO Auto-generated method stub
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(getOauth2AccessTokenAccountService().getAccess_token());

			HttpEntity<?> entity = new HttpEntity<>(headers);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + allprojects)
					.queryParam("company_id", id_company);
			ResponseEntity<Project[]> projects = webClient.exchange(builder.toUriString(), HttpMethod.GET, entity,
					Project[].class);
			logger.warn(projects.getHeaders().get("x-rate-limit-remaining"));

			return Arrays.asList(projects.getBody());

		} catch (HttpStatusCodeException ex) {
			// raw http status code e.g `404`
			System.out.println(ex.getRawStatusCode());

			// http status code e.g. `404 NOT_FOUND`
			System.out.println(ex.getStatusCode().toString());

			// get response body
			System.out.println(ex.getResponseBodyAsString());

			// get http headers
			HttpHeaders headers = ex.getResponseHeaders();
			System.out.println(headers.get("Content-Type"));
			System.out.println(headers.get("Server"));
		}
		return null;
	}
	public static void browse(String url) throws IOException {
		 Runtime rt = Runtime.getRuntime();
	       rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
	}

	@Override
	public RootFolder getRootFolderById(String idFolder, String projectId) {
		// TODO Auto-generated method stub

		try {

			HttpHeaders headers = new HttpHeaders();
			
			headers.setBearerAuth(getOauth2AccessTokenAccountService().getAccess_token());
			HttpEntity<?> entity = new HttpEntity<>(headers);
			System.err.println(baseUri + showFolder + "/" + idFolder);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + showFolder + "/" + idFolder)
					.queryParam("project_id", projectId);
			ResponseEntity<RootFolder> response = webClient.exchange(builder.toUriString(), HttpMethod.GET, entity,
					RootFolder.class);
		
			
			RootFolder folder = response.getBody();
			folder.setProject_id(projectId);
			return response.getBody();

		} catch (HttpStatusCodeException ex) {

			// raw http status code e.g `404`
			System.out.println(ex.getRawStatusCode());

			// http status code e.g. `404 NOT_FOUND`
			System.out.println(ex.getStatusCode().toString());

			// get response body
			System.out.println(ex.getResponseBodyAsString());

			// get http headers
			HttpHeaders headers = ex.getResponseHeaders();
			System.out.println(headers.get("Content-Type"));
			System.out.println(headers.get("Server"));
		}
		return null;

	}

	@Override
	public RootFolder getRootFolderByProjectId(String project_id) {
		// TODO Auto-generated method stub
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(getOauth2AccessTokenAccountService().getAccess_token());
			HttpEntity<?> entity = new HttpEntity<>(headers);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + showFolder)
					.queryParam("project_id", project_id);
			ResponseEntity<RootFolder> response = webClient.exchange(builder.build().encode().toUri(), HttpMethod.GET,
					entity, RootFolder.class);

			logger.warn(response.getHeaders().get("x-rate-limit-remaining"));
			RootFolder folder = response.getBody();
			folder.setProject_id(project_id);
			return response.getBody();

		} catch (HttpStatusCodeException ex) {
			// raw http status code e.g `404`
			System.out.println(ex.getRawStatusCode());

			// http status code e.g. `404 NOT_FOUND`
			System.out.println(ex.getStatusCode().toString());

			// get response body
			System.out.println(ex.getResponseBodyAsString());

			// get http headers
			HttpHeaders headers = ex.getResponseHeaders();
			System.out.println(headers.get("Content-Type"));
			System.out.println(headers.get("Server"));
		}
		return null;
	}

	
	@Override
	public AccessToken getOauth2AccessTokenAccountService() {
		Credentials credentials = new Credentials();
		credentials.setClient_id(clientId);
		credentials.setClient_secret(clientSecret);
		credentials.setGrant_type("client_credentials");

		HttpEntity<?> entity = new HttpEntity<>(credentials);

		ResponseEntity<AccessToken> response = loginClient.postForEntity(loginUri, entity, AccessToken.class);
		AccessToken result = response.getBody();

		return result;
	}

	@Override
	public Files getFilesById(String id, String projectId) {
		try {
			HttpHeaders headers = new HttpHeaders();
		
			headers.setBearerAuth(getOauth2AccessTokenAccountService().getAccess_token());
			HttpEntity<?> entity = new HttpEntity<>(headers);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + "/files/" + id)
					.queryParam("project_id", projectId);
			ResponseEntity<Files> response = webClient.exchange(builder.toUriString(), HttpMethod.GET, entity,
					Files.class);
			logger.warn(response.getHeaders().get("x-rate-limit-remaining"));
			Files folder = response.getBody();

			folder.setProject_id(projectId);
			return response.getBody();

		} catch (HttpStatusCodeException ex) {
			// raw http status code e.g `404`
			System.out.println(ex.getRawStatusCode());

			// http status code e.g. `404 NOT_FOUND`
			System.out.println(ex.getStatusCode().toString());

			// get response body
			System.out.println(ex.getResponseBodyAsString());

			// get http headers
			HttpHeaders headers = ex.getResponseHeaders();
			System.out.println(headers.get("Content-Type"));
			System.out.println(headers.get("Server"));
		}
		return null;
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(getOauth2AccessTokenAccountService().getAccess_token());
			HttpEntity<?> entity = new HttpEntity<>(headers);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + "/companies");
			ResponseEntity<Company[]> projects = webClient.exchange(builder.toUriString(), HttpMethod.GET, entity,
					Company[].class);
			logger.warn(projects.getHeaders().get("x-rate-limit-remaining"));
			return Arrays.asList(projects.getBody());

		} catch (HttpStatusCodeException ex) {
			// raw http status code e.g `404`
			System.out.println(ex.getRawStatusCode());

			// http status code e.g. `404 NOT_FOUND`
			System.out.println(ex.getStatusCode().toString());

			// get response body
			System.out.println(ex.getResponseBodyAsString());

			// get http headers
			HttpHeaders headers = ex.getResponseHeaders();
			System.out.println(headers.get("Content-Type"));
			System.out.println(headers.get("Server"));
		}
		return null;
	}
	
	

}
