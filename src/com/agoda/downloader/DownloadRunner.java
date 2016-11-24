package com.agoda.downloader;

import com.agoda.downloader.model.URLInfo;
import com.agoda.downloader.service.DownloadContext;
import com.agoda.downloader.service.impl.FTPDownloader;
import com.agoda.downloader.service.impl.HttpDownloader;
import com.agoda.downloader.service.impl.SFTPDownloader;

public class DownloadRunner {

	public static void main(String[] args) {
		String savePath = "/home/deuce/Downloads/downloader";
		
		URLInfo fileHttp = new URLInfo();
		fileHttp.setFullPath("https://i.ytimg.com/vi/_jBfu0FHzjU/hqdefault.jpg");
		fileHttp.setFileName("hqdefault.jpg");
		DownloadContext context = new DownloadContext(new HttpDownloader());
		context.download(fileHttp, savePath);
		
		URLInfo fileFtp = new URLInfo();	
		fileFtp.setFullPath("ftp://demo.wftpserver.com/download/BMW_Quickguide_7series_en.pdf");
		fileFtp.setHost("demo.wftpserver.com");
		fileFtp.setFileName("/download/BMW_Quickguide_7series_en.pdf");
		fileFtp.setPort("21");
		fileFtp.setUserName("demo-user");
		fileFtp.setPassword("demo-user");
		context = new DownloadContext(new FTPDownloader());
		context.download(fileFtp, savePath);
		
		URLInfo fileSftp = new URLInfo();
		fileSftp.setFullPath("sftp://demo.wftpserver.com/download/X6Hybrid_download_PC.wmv");
		fileSftp.setHost("demo.wftpserver.com");
		fileSftp.setPort("2222");
		fileSftp.setUserName("demo-user");
		fileSftp.setPassword("demo-user");
		fileSftp.setFilepath("download");
		fileSftp.setFileName("X6Hybrid_download_PC.wmv");
		context = new DownloadContext(new SFTPDownloader());
		context.download(fileSftp, savePath);
	}

}
