package com.agoda.downloader.model;

public class URLInfo {
	private String fullPath;
	private String protocol;
	private String host;
	private String port;
	private String filepath;
	private String fileName;
	private String userName;
	private String password;
	
	public URLInfo() {
	}
	
	
	public URLInfo(String fullPath, String protocol, String host, String port, String filepath, String fileName,
			String userName, String password) {
		super();
		this.fullPath = fullPath;
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.filepath = filepath;
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
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
		return "URLInfo [fullPath=" + fullPath + ", protocol=" + protocol + ", host=" + host + ", port=" + port
				+ ", filepath=" + filepath + ", fileName=" + fileName + ", userName=" + userName + ", password="
				+ password + "]";
	}
	
}
