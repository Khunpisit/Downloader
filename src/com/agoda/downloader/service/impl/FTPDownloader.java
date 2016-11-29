package com.agoda.downloader.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.agoda.downloader.constant.DownloadConstant;
import com.agoda.downloader.model.URLInfo;
import com.agoda.downloader.service.DownloadManager;
import com.agoda.downloader.util.Logger;
import com.agoda.downloader.util.ProgressBar;

public class FTPDownloader implements DownloadManager{

	@Override
	public boolean download(URLInfo urlInfo, String savePath) {
		long startTime = System.currentTimeMillis();
		boolean result = false;
		FTPClient ftp = new FTPClient();
		
        try {
        	String server = urlInfo.getHost();
            ftp.connect(server, Integer.parseInt(urlInfo.getPort()));
            ftp.login( urlInfo.getUserName(), urlInfo.getPassword());
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            
            String remoteFile = File.separator + urlInfo.getFilepath() + File.separator + urlInfo.getFileName();
            FTPFile file = ftp.mlistFile(remoteFile);
            File downloadFile = new File(savePath + File.separator + urlInfo.getFileName());
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            InputStream inputStream = ftp.retrieveFileStream(remoteFile);
           
            byte[] bytesArray = new byte[DownloadConstant.BUFFER_SIZE];
            long size = file.getSize();
            int fileSize = (int)size;
            int bytesRead = -1;
            int progressVolume = 0;
            ProgressBar bar = new ProgressBar();
            bar.update(progressVolume, fileSize);

            while((bytesRead = inputStream.read(bytesArray)) != -1) {
            	bar.update((progressVolume += bytesRead), fileSize);
            	outputStream.write(bytesArray, 0, bytesRead);
            }
            if(ftp.completePendingCommand()) {
            	Logger.debug("download {0} completed, Elapsed time:{1} ms. size:{2}", urlInfo.getFileName(), (System.currentTimeMillis() - startTime), progressVolume);
            	result = true;
            }
            
            outputStream.close();
            inputStream.close();
           
        } catch (IOException e) {
            Logger.error(e.getMessage());
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException e) {
                Logger.error(e.getMessage());
            }
        }
        
		return result;
	}

}
