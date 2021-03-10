package com.procore.connector.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.procore.connector.models.Files;
import com.procore.connector.models.RootFolder;
import com.procore.connector.models.webhook.Event;
import com.procore.connector.repository.FileRepository;
import com.procore.connector.repository.RootFolderRepository;
import com.procore.connector.service.fromProcore.FromProcoreToSharedService;
import com.procore.connector.utils.FileUtils;

@Component
public class SyncService {

	@Value("${shared.drive}")
	private String shareDrive;
	@Autowired
	FromProcoreToSharedService procoreService;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private RootFolderRepository folderRepository;

	public void processEvent(Event body) throws IOException {
		try {
			System.err.println(body.getResource_name());
			if (body.getResource_name().contains("Files")) {
				Files webhookFile = procoreService.getFilesById(body.getResource_id() + "", body.getProject_id() + "");
				System.err.println(webhookFile.getName());
				String filePath = webhookFile.getName_with_path();
				if (body.getEvent_type().equals("create") || body.getEvent_type().equals("update")) {
					if (fileRepository.findById(webhookFile.getId()).isPresent()) {
						Files updated = fileRepository.findById(webhookFile.getId()).get();
						File f = new File(
								shareDrive + "/" + updated.getName_with_path());
						f.delete();
						FileUtils.downloadUsingStream(webhookFile.getFile_versions().get(0).getUrl(),
								shareDrive + "/" + filePath);

					} else {
						System.err.println(shareDrive);
						System.err.println(webhookFile.getFile_versions().get(0).getUrl());
						java.nio.file.Files
								.createDirectories(new File(shareDrive + "/" + webhookFile.getName_with_path()
										.replace(webhookFile.getName(), "")).toPath());
						FileUtils.downloadUsingStream(webhookFile.getFile_versions().get(0).getUrl(),
								shareDrive + "/" + filePath);

					}
					webhookFile.setProject_id(body.getProject_id() + "");
					fileRepository.save(webhookFile);
				} else {
					File f = new File(
							shareDrive + "/" + webhookFile.getName_with_path());
					f.delete();
					fileRepository.delete(webhookFile);
				}
			} else if (body.getResource_name().contains("Folders")) {
				RootFolder webhookfolder = procoreService.getRootFolderById(body.getResource_id() + "",
						body.getProject_id() + "");
				String pathFolder = webhookfolder.getName_with_path();
				if (body.getEvent_type().equals("create")) {
					java.nio.file.Files.createDirectories(new File(shareDrive + "/" + pathFolder).toPath());

				} else if (body.getEvent_type().equals("update")) {
					if (folderRepository.findById(webhookfolder.getId()).isPresent()) {
						File updateFolder = new File(shareDrive + "/" + folderRepository
								.findById(webhookfolder.getId()).get().getName_with_path());
						updateFolder.renameTo(new File(shareDrive + "/" + pathFolder));
					}

					webhookfolder.setProject_id(body.getProject_id() + "");
					folderRepository.save(webhookfolder);
				} else {
					if (folderRepository.findById(webhookfolder.getId()).isPresent()) {
						File updateFolder = new File(shareDrive + "/" + folderRepository
								.findById(webhookfolder.getId()).get().getName_with_path());
						updateFolder.delete();
						folderRepository.delete(folderRepository.findById(webhookfolder.getId()).get());
					}
				}
			}
		}catch (Exception e) {
		System.err.println(e.getMessage());
		
		System.err.println(e);

		}
	
	}
}
