package com.agoda.downloader.service;

import com.agoda.downloader.model.FileURL;

public class DownloadContext {
	private DownloadService service;
	
	public DownloadContext(DownloadService service) {
		this.service = service;
	}
	
	public void download(FileURL fileURL, String savePath) {
		this.service.donwload(fileURL, savePath);
	}
}