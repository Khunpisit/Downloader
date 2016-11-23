package com.agoda.downloader.service;

import com.agoda.downloader.model.FileURL;

public interface DownloadService {
  public void donwload(FileURL fileURL, String savePath);
}
