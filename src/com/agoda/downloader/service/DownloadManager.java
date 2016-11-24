package com.agoda.downloader.service;

import com.agoda.downloader.model.URLInfo;

public interface DownloadManager {
  public boolean download(URLInfo urlInfo, String savePath);
}
