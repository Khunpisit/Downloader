package com.agoda.downloader.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.agoda.downloader.constant.DownloadConstant;
import com.agoda.downloader.model.URLInfo;
import com.agoda.downloader.service.DownloadManager;
import com.agoda.downloader.util.Logger;

public class FTPDownloader implements DownloadManager{

	@Override
	public boolean download(URLInfo urlInfo, String savePath) {
		long startTime = System.currentTimeMillis();
		boolean result = false;
		FTPClient ftp = new FTPClient();
		
        try {
        	Logger.info(">> Start download file: {0}", urlInfo.getFullPath());
        	String server = urlInfo.getHost();
            ftp.connect(server, Integer.parseInt(urlInfo.getPort()));
            ftp.login( urlInfo.getUserName(), urlInfo.getPassword());
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            
            String remoteFile = urlInfo.getFilepath() + File.separator + urlInfo.getFileName();
            File downloadFile = new File(savePath + File.separator + urlInfo.getFileName());
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            InputStream inputStream = ftp.retrieveFileStream(remoteFile);
            byte[] bytesArray = new byte[DownloadConstant.BUFFER_SIZE];
            int bytesRead = -1;
            while((bytesRead = inputStream.read(bytesArray)) != -1) {
            	outputStream.write(bytesArray, 0, bytesRead);
            }
            
            if(ftp.completePendingCommand()) {
            	Logger.info(">> File {0} has been downloaded successfully.", urlInfo.getFileName());
            	result = true;
            }
            
            outputStream.close();
            inputStream.close();
           
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException e) {
                Logger.error(e.getMessage(), e);
            }
            
            Logger.info(">> Download {0}, Elapsed time:{1} ms.", urlInfo.getFileName(), (System.currentTimeMillis() - startTime) );
        }
        
		return result;
	}

}
