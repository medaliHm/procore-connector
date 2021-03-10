package com.procore.connector.service;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class Watcher {


	@Autowired
	FileAlterationListenerImpl watcherOtherService;

	


	public void watch() throws Exception {
		
			System.out.println("Begin Watch");
			final File directory = new File("Q:\\");
			FileAlterationObserver fao = new FileAlterationObserver(directory);
			fao.addListener(watcherOtherService);
			final FileAlterationMonitor monitor = new FileAlterationMonitor(0);
			monitor.addObserver(fao);
			monitor.start();
		
		
	}
}
