package com.agoda.downloader.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import com.agoda.downloader.constant.DownloadConstant;
import com.agoda.downloader.model.URLInfo;
import com.agoda.downloader.service.DownloadManager;
import com.agoda.downloader.util.Logger;
import com.agoda.downloader.util.ProgressBar;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class SFTPDownloader implements DownloadManager {

	@Override
	public boolean download(URLInfo urlInfo, String savePath) {
		long startTime = System.currentTimeMillis();
		JSch jsch = new JSch();
		boolean result = false;
		
		try {
			Session session = jsch.getSession(urlInfo.getUserName(), urlInfo.getHost(), Integer.parseInt(urlInfo.getPort()));
			session.setPassword(urlInfo.getPassword());
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			
			ChannelSftp sftp = (ChannelSftp) channel;
			SftpATTRS sttr = sftp.lstat(urlInfo.getFilepath() + File.separator + urlInfo.getFileName());
			long fileSize = sttr.getSize();
			sftp.cd(urlInfo.getFilepath());
			
			BufferedInputStream bis = new BufferedInputStream(sftp.get(urlInfo.getFileName()));
			File file = new File(savePath + File.separator + urlInfo.getFileName());
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(outputStream);
			
			byte[] buffer = new byte[DownloadConstant.BUFFER_SIZE];
			ProgressBar bar = new ProgressBar();
			int bytesRead = -1;
			int progressVolume = 0;
			bar.update(progressVolume, (int)fileSize);
			
			while((bytesRead = bis.read(buffer)) != -1) {
				bar.update(progressVolume += bytesRead, (int)fileSize);
				bos.write(buffer, 0, bytesRead);
			}
	      	Logger.debug("download {0} completed, Elapsed time:{1} ms. size:{2}", urlInfo.getFileName(), (System.currentTimeMillis() - startTime), progressVolume);
	                 			
			bis.close();
			bos.close();
			result = true;
			
		} catch (NumberFormatException e) {
			Logger.error(e.getMessage(), e);
		} catch (JSchException e) {
			Logger.error(e.getMessage(), e);
		} catch (SftpException e) {
			Logger.error(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			Logger.error(e.getMessage(), e);
		} catch (IOException e) {
			Logger.error(e.getMessage(), e);
		}
		
		return result;
	}

}
