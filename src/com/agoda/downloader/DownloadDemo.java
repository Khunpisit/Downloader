package com.agoda.downloader;

import com.agoda.downloader.model.FileURL;
import com.agoda.downloader.service.DownloadContext;
import com.agoda.downloader.service.impl.FTPDownloader;
import com.agoda.downloader.service.impl.HttpDownloader;

public class DownloadDemo {

	public static void main(String[] args) {
		String savePath = "/home/deuce/Downloads/downloader";
		
		FileURL fileHttp = new FileURL();
		fileHttp.setFullPath("https://i.ytimg.com/vi/_jBfu0FHzjU/hqdefault.jpg");
		fileHttp.setFileName("hqdefault.jpg");
		DownloadContext context = new DownloadContext(new HttpDownloader());
		context.download(fileHttp, savePath);
		
		FileURL fileFtp = new FileURL();	
		fileFtp.setFullPath("ftp://demo.wftpserver.com/download/BMW_Quickguide_7series_en.pdf");
		fileFtp.setHost("demo.wftpserver.com");
		fileFtp.setFileName("/download/BMW_Quickguide_7series_en.pdf");
		fileFtp.setPort("21");
		fileFtp.setUserName("demo-user");
		fileFtp.setPassword("demo-user");
		context = new DownloadContext(new FTPDownloader());
		context.download(fileFtp, savePath);
	}

}
