package com.bhargo.service.impl;

import java.io.File;
import java.lang.management.ThreadMXBean;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.bhargo.service.FileService;

public class FileServiceImpl implements FileService {

	@Override
	public void readFiles(Set<File> fileSet) {
		// TODO Auto-generated method stub
		int numOfFiles = fileSet.size();
		ExecutorService service = Executors.newCachedThreadPool(customThreadFactory());
		service.execute(() -> System.out.println(""));

	}
	
	static class customThreadFactory implements ThreadFactory {
		
		String threadNamePrefix;
		
		public customThreadFactory(String threadNamePrefix) {
			threadNamePrefix = this.threadNamePrefix;
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setName(threadNamePrefix + new Date());			
			return thread;
		}
		
	}
	
	private ThreadFactory customThreadFactory() {
		return new customThreadFactory("fileReader");
		
	}

}
