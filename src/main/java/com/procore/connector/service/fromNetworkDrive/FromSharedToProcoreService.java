package com.procore.connector.service.fromNetworkDrive;

import java.io.File;

import com.procore.connector.models.Files;
import com.procore.connector.models.RootFolder;


public interface FromSharedToProcoreService {

	public RootFolder eventCreateFolder(String localPath,String nameFolder);

	public RootFolder eventUpdateFolder();

	public void eventDeleteFolder(String localPath,RootFolder folder);

	public Files eventCreateFile(File file, String localPath, String fileName)  ;

	public Files eventUpdateFile(File file, String localPath, String fileName);

	public void eventDeleteFile(String localPath, Files files);

}
