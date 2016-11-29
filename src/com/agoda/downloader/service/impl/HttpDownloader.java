package com.agoda.downloader.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.agoda.downloader.constant.DownloadConstant;
import com.agoda.downloader.model.URLInfo;
import com.agoda.downloader.service.DownloadManager;
import com.agoda.downloader.util.Logger;
import com.agoda.downloader.util.ProgressBar;

public class HttpDownloader implements DownloadManager {
	
	@Override
	public boolean download(URLInfo urlInfo, String savePath) {
		long startTime = System.currentTimeMillis();
		boolean result = false;
		
		try {
			URL url = new URL(urlInfo.getFullPath());
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			
			if(HttpURLConnection.HTTP_OK == httpCon.getResponseCode()) {
	            InputStream inputStream = httpCon.getInputStream();
	            String saveFilePath = savePath + File.separator + urlInfo.getFileName();
	            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
	            
	            int fileSize = httpCon.getContentLength();
	            byte[] buffer = new byte[DownloadConstant.BUFFER_SIZE];
	            int progressVolume = 0;
	            int bytesRead = -1;
	            ProgressBar bar = new ProgressBar();
	            bar.update(progressVolume, fileSize);
	            
	            while((bytesRead = inputStream.read(buffer)) != -1) {
	            	bar.update((progressVolume += bytesRead), fileSize);
	            	outputStream.write(buffer, 0, bytesRead);
	            }
	            Logger.debug("download {0} completed, Elapsed time:{1} ms. size:{2}", urlInfo.getFileName(), (System.currentTimeMillis() - startTime), progressVolume);
	            
	            outputStream.close();
	            inputStream.close();
	            result = true;
			} else {
				Logger.info("Something goes wrong. Server replied HTTP code: {0}", httpCon.getResponseCode());
			}
		
			httpCon.disconnect();
		} catch (MalformedURLException e) {
			Logger.error(e.getMessage());
		} catch (IOException e) {
			Logger.error(e.getMessage());
		}
		
		return result;
	}
	
}
