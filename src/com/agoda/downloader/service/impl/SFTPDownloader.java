package com.agoda.downloader.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import com.agoda.downloader.model.URLInfo;
import com.agoda.downloader.service.DownloadManager;
import com.agoda.downloader.util.Logger;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPDownloader implements DownloadManager {

	@Override
	public boolean download(URLInfo urlInfo, String savePath) {
		long startTime = System.currentTimeMillis();
		JSch jsch = new JSch();
		boolean result = false;
		
		try {
			Logger.info(">> Start download file:" + urlInfo.getFullPath());
			Session session = jsch.getSession(urlInfo.getUserName(), urlInfo.getHost(), Integer.parseInt(urlInfo.getPort()));
			session.setPassword(urlInfo.getPassword());
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			
			ChannelSftp sftp = (ChannelSftp) channel;
			sftp.cd(urlInfo.getFilepath());
			byte[] buffer = new byte[1024];
			BufferedInputStream bis = new BufferedInputStream(sftp.get(urlInfo.getFileName()));
			File file = new File(savePath + File.separator + urlInfo.getFileName());
			OutputStream outputStream = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(outputStream);
			
			int readCount;
			while((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}
			
			bis.close();
			bos.close();
			result = true;
			Logger.info(">> File has been downloaded successfully.");
			
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
		}finally {
			Logger.info(">> Elapsed time:{0} ms.", (System.currentTimeMillis() - startTime) );
		}
		
		return result;
	}

}
