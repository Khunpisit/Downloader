package com.agoda.downloader;

import com.agoda.downloader.model.FileURL;
import com.agoda.downloader.service.DownloadContext;
import com.agoda.downloader.service.impl.HttpDownloader;

public class DownloadDemo {

	public static void main(String[] args) {
		String savePath = "/home/deuce/Downloads/downloader";
		FileURL file = new FileURL();
		file.setFullPath("https://i.ytimg.com/vi/_jBfu0FHzjU/hqdefault.jpg");
		file.setFileName("hqdefault.jpg");
		DownloadContext context = new DownloadContext(new HttpDownloader());
		context.download(file, savePath);
	}

}
