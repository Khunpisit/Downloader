package com.agoda.downloader.service;

import com.agoda.downloader.model.URLInfo;

public class DownloadContext {
	private DownloadManager manager;
	
	public DownloadContext(DownloadManager service) {
		this.manager = service;
	}
	
	public boolean download(URLInfo fileURL, String savePath) {
		return this.manager.download(fileURL, savePath);
	}
}