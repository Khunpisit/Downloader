package com.agoda.downloader.service;

import com.agoda.downloader.model.FileURL;

public class DownloadContext {
	private DownloadService service;
	
	public DownloadContext(DownloadService service) {
		this.service = service;
	}
	
	public boolean download(FileURL fileURL, String savePath) {
		return this.service.download(fileURL, savePath);
	}
}