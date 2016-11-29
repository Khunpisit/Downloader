package com.agoda.downloader;

import java.io.File;
import java.util.List;

import com.agoda.downloader.constant.DownloadConstant;
import com.agoda.downloader.model.URLInfo;
import com.agoda.downloader.service.DownloadContext;
import com.agoda.downloader.service.impl.FTPDownloader;
import com.agoda.downloader.service.impl.HttpDownloader;
import com.agoda.downloader.service.impl.SFTPDownloader;
import com.agoda.downloader.util.Logger;
import com.agoda.downloader.util.Configuration;

public class DownloadRunner {

	public static void main(String[] args) {
		Logger.info(">> Hi every body, This is Downloader in text mode.");
		Logger.info(">> Just enjoy to download files :)");
		String projectPath = System.getProperty("user.dir");
		
		String savePath = Configuration.getSavePath(projectPath + File.separator + "path.conf");
		List<URLInfo> urlList = Configuration.loadURLs(projectPath + File.separator + "url.conf");
		
		urlList.parallelStream().forEach(url -> {
			switch(url.getProtocol().toUpperCase()) {
				case DownloadConstant.HTTP:
				case DownloadConstant.HTTPS:
					DownloadContext http = new DownloadContext(new HttpDownloader());
					http.download(url, savePath);
					break;
				case DownloadConstant.FTP:
				case DownloadConstant.FTPS:
					DownloadContext ftp = new DownloadContext(new FTPDownloader());
					ftp.download(url, savePath);
					break;
				case DownloadConstant.SFTP:
					DownloadContext sftp = new DownloadContext(new SFTPDownloader());
					sftp.download(url, savePath);
					break;
				default:
					throw new IllegalArgumentException("!!! no protocal support:" + url.getProtocol());
			}
		});
	}

}
