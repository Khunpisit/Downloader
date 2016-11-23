package com.agoda.downloader.service;

import com.agoda.downloader.model.FileURL;

public interface DownloadService {
  public boolean download(FileURL fileURL, String savePath);
}
