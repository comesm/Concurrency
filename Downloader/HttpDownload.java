package Downloader;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpDownload {




    public static void main(String[] args) throws Exception {
        URL from = new URL("https://www.tutorialspoint.com/java/java_thread_deadlock.htm");
        Downloader downloader = new Downloader(from, "download.out");

        downloader.start();
        downloader.addListener(new ProgressListener() {
            public void onProgress(int n) {
                   System.out.print("\r" + n);
                   System.out.flush();
                   something(downloader);

            }
            public void onComplete(boolean success) {}

            public void something(Downloader downloader) {
                ExecutorService xc = Executors.newFixedThreadPool(1);
                xc.execute(new Runnable() {
                    @Override
                    public void run() {
                        downloader.start();

                    }

                });
            }
        });



        downloader.join();

    }
}
