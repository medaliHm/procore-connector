package com.procore.connector.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.procore.connector.models.Files;
import com.procore.connector.models.RootFolder;
import com.procore.connector.repository.FileRepository;
import com.procore.connector.repository.FilesVersionsRepository;
import com.procore.connector.repository.RootFolderRepository;
import com.procore.connector.service.fromNetworkDrive.FromSharedToProcoreService;
import com.procore.connector.service.fromProcore.FromProcoreToSharedService;

@Component
public class FileAlterationListenerImpl implements FileAlterationListener {

	@Value("${shared.url}")
	private String sharedNetwork;
	@Autowired
	FromSharedToProcoreService serviceToProcore;
	@Autowired
	FromProcoreToSharedService serviceFromProcore;
	@Autowired
	private RootFolderRepository folderRepository;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private FilesVersionsRepository versionRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStart(final FileAlterationObserver observer) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDirectoryCreate(final File directory) {

		serviceToProcore.eventCreateFolder(directory.getParent().toString(), directory.getName().toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDirectoryChange(final File directory) {
		System.out.println(directory.getAbsolutePath() + " wa modified");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDirectoryDelete(final File directory) {
		RootFolder folder = folderRepository.getRootFolderByLocalPath(directory.getAbsolutePath().toString());
		if(folder !=null) {
			serviceToProcore.eventDeleteFolder(folder.getLocalPath().toString(), folder);
			folderRepository.delete(folder);

		}

		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onFileCreate(final File file) {
		Files fileCreated = fileRepository.getFilesByLocalPath(file.toPath().toString());

		if (fileCreated == null) {

			serviceToProcore.eventCreateFile(file, file.getParent().toString(), file.getName().toString());

		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onFileChange(final File file) {
		Files files = fileRepository.getFilesByLocalPath(file.getAbsolutePath());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());

		if (files != null) {
			try {
				Files fileProcore = serviceFromProcore.getFilesById(files.getId(), files.getProject_id());
				if (fileProcore.getFile_versions() != null && fileProcore.getFile_versions().size() > 0) {

					if (sdf.parse(sdf.format(file.lastModified()))
							.after(f.parse(fileProcore.getFile_versions().get(0).getCreated_at())))
						serviceToProcore.eventUpdateFile(file, file.getParent().toString(), file.getName().toString());
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.err.println("Error Parsing");
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onFileDelete(final File file) {

			String localPath = file.getAbsolutePath().toString();
			Files deletedFiles = fileRepository.getFilesByLocalPath(localPath);
			RootFolder folder = folderRepository.getRootFolderByLocalPath(localPath);
			if (deletedFiles != null) {
				serviceToProcore.eventDeleteFile(deletedFiles.getLocalPath(), deletedFiles);
				fileRepository.delete(deletedFiles);

			} else {
				serviceToProcore.eventDeleteFolder(folder.getLocalPath(), folder);
				folderRepository.delete(folder);
			}
		

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStop(final FileAlterationObserver observer) {
	}
}