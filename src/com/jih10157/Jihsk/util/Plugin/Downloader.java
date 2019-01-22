package com.jih10157.Jihsk.util.Plugin;

import java.io.*;
import java.net.URL;

public class Downloader {

    private String pluginF;
    private String url;
    private String filename;

    public Downloader(String file, String url, String filename) {
        this.pluginF = file;
        this.url = url;
        this.filename = filename;
    }

    public boolean download() {
        InputStream in;
        FileOutputStream out;
        try {
            in = new URL(this.url).openStream();
            out = new FileOutputStream(this.pluginF+File.separator+this.filename);
            byte[] buf = new byte[1024];
            int r;
            while (-1 != (r = in.read(buf))) { out.write(buf, 0, r); }
            in.close();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
