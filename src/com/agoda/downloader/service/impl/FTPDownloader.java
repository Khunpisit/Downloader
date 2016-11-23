package com.agoda.downloader.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.agoda.downloader.model.FileURL;
import com.agoda.downloader.service.DownloadService;
import com.agoda.downloader.util.Logger;

public class FTPDownloader implements DownloadService{

	@Override
	public boolean download(FileURL fileURL, String savePath) {
		long startTime = System.currentTimeMillis();
		boolean result = false;
		FTPClient ftpClient = new FTPClient();
		
        try {
        	Logger.info(">> Start download file:" + fileURL.getFullPath());
        	String server = fileURL.getHost();
            ftpClient.connect(server, Integer.parseInt(fileURL.getPort()));
            ftpClient.login( fileURL.getUserName(), fileURL.getPassword());
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            String[] fileName = fileURL.getFileName().split("\\/");
            File downloadFile1 = new File(savePath + File.separator + fileName[fileName.length - 1]);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(fileURL.getFileName(), outputStream1);
            outputStream1.close();
 
            if (success) {
                Logger.info(">> File has been downloaded successfully.");
                result = true;
            } else {
            	result = false;
            	Logger.info("No file to download.");
            }

        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                Logger.error(e.getMessage(), e);
            }
            
            Logger.info(">> Elapsed time:{0} ms.", (System.currentTimeMillis() - startTime) );
        }
        
		return result;
	}

}
