# Downloader

 Downloader is a small program which can download multiple files from multiple URLs 
 and support multiple protocols (now support http, https, ftp, ftps and sftp).

### Setup environment
  - Install JRE 1.8 (required jre1.8.0_111 or above) [download jre 1.8] 
  - Update variable java_home in file run/downloader.sh (Linux) or run/downloader.cmd (Windows) to point to jre1.8/bin
 
### Configuration

define path to save file on local disk in file run/path.conf 
  > save_path=your local path

config URLs in file run/url.conf (Please use enter key as delimiter)

  > ftp://kfc.com/file/wing_zap_calories.pdf

  > user:pass@sftp://university.ac.th/download/final_exam.doc

### Run
Linux

```sh
$ cd run/
$ sh downloader.sh
```

Windows
 double click downloader.cmd
 
### Todos
 - Write more Tests
 - Check disk free space before save file
 - Fix bugs

### Note
Please ensure your network connection have no proxy setting

**Free Software, Hell Yeah!**

[download jre 1.8]: <http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html>