package com.agoda.downloader;

import java.io.File;
import java.util.List;

import com.agoda.downloader.constant.Protocol;
import com.agoda.downloader.model.URLInfo;
import com.agoda.downloader.service.DownloadContext;
import com.agoda.downloader.service.impl.FTPDownloader;
import com.agoda.downloader.service.impl.HttpDownloader;
import com.agoda.downloader.service.impl.SFTPDownloader;
import com.agoda.downloader.util.Logger;
import com.agoda.downloader.util.Util;

public class DownloadRunner {

	public static void main(String[] args) {
		String savePath = "/home/deuce/Downloads/downloader";
		
		String projectPath = System.getProperty("user.dir");
		Logger.info("projectPath={0}", projectPath);
		
		List<URLInfo> urlList = Util.loadURLs(projectPath + File.separator + "conf/url.conf");
		
		urlList.parallelStream().forEach(url -> {
			switch(url.getProtocol().toUpperCase()) {
				case Protocol.HTTP:
				case Protocol.HTTPS:
					DownloadContext http = new DownloadContext(new HttpDownloader());
					http.download(url, savePath);
					break;
				case Protocol.FTP:
				case Protocol.FTPS:
					DownloadContext ftp = new DownloadContext(new FTPDownloader());
					ftp.download(url, savePath);
					break;
				case Protocol.SFTP:
					DownloadContext sftp = new DownloadContext(new SFTPDownloader());
					sftp.download(url, savePath);
					break;
				default:
					throw new IllegalArgumentException("no protocal support:" + url.getProtocol());
			}
		});
	}

}
