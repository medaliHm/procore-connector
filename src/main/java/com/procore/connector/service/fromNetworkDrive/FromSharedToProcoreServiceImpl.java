package com.procore.connector.service.fromNetworkDrive;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.procore.connector.models.Files;
import com.procore.connector.models.RootFolder;
import com.procore.connector.models.dto.FolderDataUpload;
import com.procore.connector.models.dto.FolderToUploadDto;
import com.procore.connector.repository.FileRepository;
import com.procore.connector.repository.RootFolderRepository;
import com.procore.connector.service.fromProcore.FromProcoreToSharedService;


@Service
public class FromSharedToProcoreServiceImpl implements FromSharedToProcoreService {

	@Autowired
	private RootFolderRepository folderRepository;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private FromProcoreToSharedService procoreService;

	@Autowired
	@Qualifier("json-rest")
	private RestTemplate webClient;

	@Autowired
	@Qualifier("multipart-rest")
	private RestTemplate multiPartWebClient;

	@Value("${endpoint.baseUri}")
	private String baseUri;
	@Value("${endpoint.folders}")
	private String foldersUri;

	@Override
	public RootFolder eventCreateFolder(String localPath, String nameFolder) {
	
		RootFolder folder = folderRepository.getRootFolderByLocalPath(localPath);
		if (folder != null) {
			try {
				FolderDataUpload dataUpload = new FolderDataUpload();
				dataUpload.setName(nameFolder);
				dataUpload.setParent_id(Long.valueOf(folder.getId()));
				FolderToUploadDto dto = new FolderToUploadDto();
				dto.setFolder(dataUpload);
				HttpHeaders headers = new HttpHeaders();
				headers.setBearerAuth(procoreService.getOauth2AccessTokenAccountService().getAccess_token());
				HttpEntity<?> entity = new HttpEntity<>(dto, headers);

				UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + foldersUri)
						.queryParam("project_id", folder.getProject_id());
				ResponseEntity<RootFolder> response = webClient.postForEntity(builder.toUriString(), entity,
						RootFolder.class);
				RootFolder createdFolder = response.getBody();
				createdFolder.setLocalPath(localPath + File.separator + createdFolder.getName());
				System.err.println(builder.toUriString());
				createdFolder.setProject_id(folder.getProject_id());
				folderRepository.save(createdFolder);

				return createdFolder;
			} catch (HttpStatusCodeException ex) {
				if(!ex.getResponseBodyAsString().contains("has already bean")) {
					System.err.println("Fail Create Folder "+localPath);

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
				
			}
		}
		return folder;

	}

	@Override
	public RootFolder eventUpdateFolder() {

		return null;
	}

	@Override
	public void eventDeleteFolder(String localPath, RootFolder folder) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(procoreService.getOauth2AccessTokenAccountService().getAccess_token());
			HttpEntity<?> entity = new HttpEntity<>(headers);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + foldersUri + "/" + folder.getId())
					.queryParam("project_id", folder.getProject_id());
			webClient.exchange(builder.toUriString(), HttpMethod.DELETE, entity, Void.class);
			folderRepository.deleteById(folder.getId());
		} catch (HttpStatusCodeException ex) {
			System.err.println("Fail Delete Folder "+localPath);

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

	}

	@Override
	public Files eventCreateFile(File file, String localPath, String fileName) {
		RootFolder folder = folderRepository.getRootFolderByLocalPath(localPath);
		
		try {

			if (file.exists()) {
				HttpHeaders headers = new HttpHeaders();
			    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.getDefault());
				String hostname ="localPC";
				try {
				
				 InetAddress	addr = InetAddress.getLocalHost();
					 hostname = addr.getHostName();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					
				}
				headers.setBearerAuth(procoreService.getOauth2AccessTokenAccountService().getAccess_token());
				MultiValueMap<String, Object> postParams1 = new LinkedMultiValueMap<>();
				postParams1.add("file[data]", new FileSystemResource(file.toPath()));
				postParams1.add("file[name]", fileName);
				postParams1.add("file[parent_id]", folder.getId());
				postParams1.add("file[description]", "The laptop  "+hostname+" with the username "+System.getProperty("user.name")+" has created this file");

				UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + "/files")
						.queryParam("project_id", folder.getProject_id());
				HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(postParams1, headers);

				ResponseEntity<Files> response = multiPartWebClient.postForEntity(builder.toUriString(), requestEntity,
						Files.class);
				Files createdFile = response.getBody();
				createdFile.setProject_id(folder.getProject_id());
				createdFile.setLocalPath(file.getAbsolutePath().toString());
				fileRepository.save(createdFile);
				file.setLastModified(f.parse(createdFile.getFile_versions().get(0).getCreated_at()).getTime());

				return createdFile;
			}

		} catch (HttpStatusCodeException ex) {
			System.err.println("Fail Create File "+localPath);

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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("Error parsing date");
		}
		return null;

	}

	@Override
	public Files eventUpdateFile(File file, String localPath, String fileName) {
		RootFolder folder = folderRepository.getRootFolderByLocalPath(localPath);

		try {
System.err.println(fileName);
			if (file.exists()) {
				Files files = fileRepository.getFilesByLocalPath(file.getAbsolutePath());
				if (files != null) {
					HttpHeaders headers = new HttpHeaders();
			
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.getDefault());
					String hostname ="localPC";
					try {
					
					 InetAddress	addr = InetAddress.getLocalHost();
						 hostname = addr.getHostName();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						
					}
					
				
					headers.setBearerAuth(procoreService.getOauth2AccessTokenAccountService().getAccess_token());
					MultiValueMap<String, Object> postParams1 = new LinkedMultiValueMap<>();
					postParams1.add("file[data]", new FileSystemResource(file.toPath()));
					postParams1.add("file[name]", fileName);
					postParams1.add("file[description]", "The laptop  "+hostname+" with the username "+System.getProperty("user.name")+" has edited the file");

					postParams1.add("file[parent_id]", folder.getId());
					UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + "/files/" + files.getId())
							.queryParam("project_id", folder.getProject_id());
					HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(postParams1, headers);

					Files response = multiPartWebClient.patchForObject(builder.toUriString(), requestEntity,
							Files.class);
					Files updatedFiles = response;
					updatedFiles.setProject_id(folder.getProject_id());
					updatedFiles.setLocalPath(file.getAbsolutePath().toString());
					fileRepository.save(updatedFiles);
					file.setLastModified(f.parse(response.getFile_versions().get(0).getCreated_at()).getTime());
					return updatedFiles;
				}

			}

		} catch (HttpStatusCodeException ex) {
			System.err.println("Fail Update File "+localPath);

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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("Error Parsing Date");
		}
		return null;

	}

	@Override
	public void eventDeleteFile(String localPath, Files files) {
		try {
			
			
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(procoreService.getOauth2AccessTokenAccountService().getAccess_token());
			HttpEntity<?> entity = new HttpEntity<>(headers);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + "/files/" + files.getId())
					.queryParam("project_id", files.getProject_id());
			webClient.exchange(builder.toUriString(), HttpMethod.DELETE, entity, Void.class);
			fileRepository.deleteById(files.getId());
		} catch (HttpStatusCodeException ex) {
			System.err.println("Fail Delete File "+localPath);
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

	}

}
