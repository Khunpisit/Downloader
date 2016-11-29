package com.agoda.downloader.util;

public class ProgressBar {
    private StringBuilder progress;

    public ProgressBar() {
        init();
    }
    
    public void update(int done, int total) {
        char[] workchars = {'|', '/', '-', '\\'};
        String format = "\r%3d%% %s %c";

        int percent = (++done * 100) / total;
        int extrachars = (percent / 2) - this.progress.length();

        while (extrachars-- > 0) {
            progress.append('#');
        }

        System.out.printf(format, percent, progress,
         workchars[done % workchars.length]);

        if (done == total) {
            System.out.flush();
            System.out.println();
            init();
        }
    }

    private void init() {
        this.progress = new StringBuilder(60);
    }

    public static void main(String[] args) {
    	 ProgressBar bar = new ProgressBar();
         bar.update(0, 1000);
         for(int i=0;i<1000;i++) {
                         // do something!
             for(int j=0;j<10000000;j++)
                 for(int p=0;p<10000000;p++);
             // update the progress bar
             bar.update(i, 1000);
         }
         System.out.println("Process Completed!");
    }

}

