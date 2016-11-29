package com.agoda.downloader.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.agoda.downloader.model.URLInfo;

public class Configuration {
	
	public static List<URLInfo> loadURLs(String filePath) {
		long startTime = System.currentTimeMillis();
		List<URLInfo> list = new ArrayList<>();
		
		try(Stream<String> stream = Files.lines(Paths.get(filePath))) {
			list = stream.parallel()
						 .map(line -> {
							String username = "";
							String password = "";
							String remainsUrl = "";
							String host = "";
							String port = "";
							
							int atSignLastIndex = line.lastIndexOf("@");
							if(atSignLastIndex > -1) {
								String userPass = line.substring(0, atSignLastIndex);
								String[] userPassArr = userPass.split(":");
								username = userPassArr[0];
								password = userPassArr[1];
								remainsUrl = line.substring(atSignLastIndex + 1);
							} else {
								remainsUrl = line;
							}
							
							int colonIndex = remainsUrl.indexOf(":");
							String protocol = remainsUrl.substring(0, colonIndex);
							String url = remainsUrl.substring(colonIndex + 3); // Remove double slash before host name.
							int pathSlashIndex = url.indexOf("/"); // Find a first slash index between host and file path
							String hostAndPort = url.substring(0, pathSlashIndex); 
							if(hostAndPort.indexOf(":") > -1) {
								String[] hostAndPortArr = hostAndPort.split(":");
								host = hostAndPortArr[0];
								port = hostAndPortArr[1];
							} else {
								host = hostAndPort;
							}
							
							String fullFilePath = url.substring(pathSlashIndex + 1); // e.g. files/img/test.jpg
							int filePathIndex = fullFilePath.lastIndexOf("/");
							String path = fullFilePath.substring(0, filePathIndex);
							String fileName = fullFilePath.substring(filePathIndex + 1);
							
							URLInfo urlInfo = new URLInfo(line, protocol, host, port, path, fileName, username, password);
							Logger.info(urlInfo.toString());
							return urlInfo;
						}).collect(Collectors.toList());
				 
		} catch(IOException e) {
			Logger.error(e.getMessage(), e);
		} finally {
			Logger.info("load url.conf completed. Elapse time:{0}", (System.currentTimeMillis() - startTime) + " ms." );
		}
		
		return list;
	}
	
	public static String getSavePath(String filePath) {
		Properties prop = new Properties();
		InputStream input;
		String savePath = "";
		
		try{
			input = new FileInputStream(filePath);
			prop.load(input);
			savePath = prop.getProperty("save_path");
			Logger.info("Save file path:{0}", savePath);
			
		} catch (IOException e) {
			Logger.error(e.getMessage(), e);
		}
		
		return savePath;
	}

}
