package com.agoda.downloader.tester;

import static org.junit.Assert.*;

import org.junit.Test;

import com.agoda.downloader.model.URLInfo;
import com.agoda.downloader.service.DownloadContext;
import com.agoda.downloader.service.impl.FTPDownloader;
import com.agoda.downloader.service.impl.HttpDownloader;

public class DownloadContextTest {
	
	private static final String SAVE_PATH = "/home/deuce/run/download";
	
	@Test
	public final void testHttpDownload() {
		URLInfo url = new URLInfo("https://cdn6.agoda.net/images/ABTest/ABTest5692/home-1920x590-liguria-italy.jpg", "https", "cdn6.agoda.net", "", "images/ABTest", "home-1920x590-liguria-italy.jpg","","");
		DownloadContext http = new DownloadContext(new HttpDownloader());
		assertTrue(http.download(url, SAVE_PATH));
	}
	
	@Test
	public final void testFtpDownload() {
		URLInfo url = new URLInfo("", "ftp", "demo.wftpserver.com", "21", "download", "BMW_Quickguide_7series_en.pdf","demo-user","demo-user");
		DownloadContext http = new DownloadContext(new FTPDownloader());
		assertTrue(http.download(url, SAVE_PATH));
	}
	
	@Test
	public final void testSftpDownload() {
		URLInfo url = new URLInfo("", "sftp", "demo.wftpserver.com", "2222", "download", "X6Hybrid_download_PC.wmv","demo-user","demo-user");
		DownloadContext http = new DownloadContext(new FTPDownloader());
		assertTrue(http.download(url, SAVE_PATH));
	}
	
}
