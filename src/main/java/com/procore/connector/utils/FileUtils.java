package com.procore.connector.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class FileUtils {

	public static void downloadUsingNIO(String urlStr, String file) throws IOException {
		URL url = new URL(urlStr);
		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		FileOutputStream fos = new FileOutputStream(file);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		rbc.close();
	}

	public static void downloadUsingStream(String urlStr, String file) throws IOException {
		URL url = new URL(urlStr);
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		FileOutputStream fis = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = bis.read(buffer, 0, 1024)) != -1) {
			fis.write(buffer, 0, count);
		}
		fis.close();
		bis.close();

	}

	public static void findAllSubFolder(Path folder, List<Path> paths) {

		for (File file : folder.toFile().listFiles()) {
			if (file.isDirectory()) {
				paths.add(file.toPath());
				findAllSubFolder(file.toPath(), paths);
			}
		}
	}

	public static void findAllFiles(Path folder, List<Path> paths) {

		for (File file : folder.toFile().listFiles()) {
			if (!file.isDirectory()) {
				paths.add(file.toPath());
			} else {

				findAllFiles(file.toPath(), paths);

			}
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Path path = Paths.get(System.getProperty("user.home"));

		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
			}
			key.reset();
		}

	}

}
