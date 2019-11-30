package com.lyh.dto;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/27 20:56
 */
public class FileInfo {
  private String path;

  public FileInfo(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
