package com.agoda.downloader.model;

public class FileURL {
	private String fullPath;
	private String protocol;
	private String host;
	private String port;
	private String fileName;
	private String userName;
	private String password;
	
	public FileURL() {
	}
	
	public FileURL(String fullPath, String protocol, String host, String port, String fileName, String userName,
			String password) {
		super();
		this.fullPath = fullPath;
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.fileName = fileName;
		this.userName = userName;
		this.password = password;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "URL [protocol=" + protocol + ", host=" + host + ", port=" + port + ", fileName=" + fileName
				+ ", userName=" + userName + ", password=" + password + "]";
	}
	
}
