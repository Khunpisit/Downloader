package com.agoda.downloader.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.agoda.downloader.model.FileURL;
import com.agoda.downloader.service.DownloadService;
import com.agoda.downloader.util.Logger;

public class HttpDownloader implements DownloadService {
	private static final int BUFFER_SIZE = 4096;
	
	@Override
	public boolean download(FileURL fileURL, String savePath) {
		long startTime = System.currentTimeMillis();
		boolean result = false;
		
		try {
			Logger.info(">> Start download file:" + fileURL.getFullPath());
			URL url = new URL(fileURL.getFullPath());
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			
			if(HttpURLConnection.HTTP_OK == httpCon.getResponseCode()) {
	            InputStream inputStream = httpCon.getInputStream();
	            String saveFilePath = savePath + File.separator + fileURL.getFileName();
	            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
	            
	            int bytesRead = -1;
	            byte[] buffer = new byte[BUFFER_SIZE];
	            while((bytesRead = inputStream.read(buffer)) != -1) {
	            	outputStream.write(buffer, 0, bytesRead);
	            }
	            
	            outputStream.close();
	            inputStream.close();
	            
	            Logger.info("!!! Download file finish.");
	            result = true;
			} else {
				Logger.info("No file to download. Server replied HTTP code:" + httpCon.getResponseCode());
			}
		
			httpCon.disconnect();
		} catch (MalformedURLException e) {
			Logger.error(e.getMessage(), e);
		} catch (IOException e) {
			Logger.error(e.getMessage(), e);
		} finally {
			Logger.info(">> Elapsed time:{0} ms.", (System.currentTimeMillis() - startTime) );
		}
		
		return result;
	}
	
}
