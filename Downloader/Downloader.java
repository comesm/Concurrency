package Downloader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;


class Downloader extends Thread {
    private InputStream in;
    private OutputStream out;
    private ArrayList<ProgressListener> listeners;

    public Downloader(URL url, String outputFilename) throws IOException {
        in = url.openConnection().getInputStream();
        out = new FileOutputStream(outputFilename);
        listeners = new ArrayList<ProgressListener>();
    }

    public synchronized void addListener(ProgressListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(ProgressListener listener) {
        listeners.remove(listener);
    }

    private synchronized void updateProgress(int n) {
       // System.out.println(" listeners length " + listeners.size());
        for(ProgressListener listener: listeners) {
            System.out.println(28 + " " + listener);
            listener.onProgress(n);
        }
    }
//
//    private void updateProgress(int n) {
//        ArrayList<ProgressListener> listenersCopy;
//        synchronized (this) {
//            listenersCopy = (ArrayList<ProgressListener>) listeners.clone();
//        }
//        for(ProgressListener listener: listenersCopy) {
//            listener.onProgress(n);
//        }
//    }


    public void run() {
        int n, total = 0;
        byte[] buffer = new byte[1024];
        System.out.println("run");
        try {
            while((n = in.read(buffer)) != -1) {
             //   System.out.println("reading in data" + buffer);
               // System.out.println("n : " + n);
                out.write(buffer, 0, n);
                total+= n;
               // System.out.println("total: " + total);
                updateProgress(total);
            }
            out.flush();
        } catch(IOException e) { }
    }

}
