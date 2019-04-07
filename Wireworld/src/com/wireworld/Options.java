package com.wireworld;

public class Options {
    private String filepath;
    private String dirpath;
    private boolean txt;
    private boolean png;
    private boolean stop;
    private boolean moore;
    private Coordinates size;
    private static Options SingletonOptions;
    private long interval;

    private Options(String filepath, String dirpath, boolean txt, boolean png, boolean stop, boolean moore, long interval) {
        this.filepath = filepath;
        this.dirpath = dirpath;
        this.txt = txt;
        this.png = png;
        this.stop = stop;
        this.interval = interval;
    }

    public static Options getInstanceOf() {
        if (SingletonOptions == null) {
            SingletonOptions = new Options("DefaultFile", "DefaultDir", false, false, false, true, 1000);
        }
        return SingletonOptions;
    }

    public void PrintOptions() {
        System.out.println("Input File Path = " + filepath);
        System.out.println("Output Directory Path = " + dirpath);
        System.out.println("Saving to TXT = " + txt);
        System.out.println("Saving to PNG = " + png);
        System.out.println("Moore = " + moore);
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getDirpath() {
        return dirpath;
    }

    public void setDirpath(String dirpath) {
        this.dirpath = dirpath;
    }

    public boolean isTxt() {
        return txt;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setTxt(boolean txt) {
        this.txt = txt;
    }

    public boolean isPng() {
        return png;
    }

    public void setPng(boolean png) {
        this.png = png;
    }

    public boolean isMoore() {
        return moore;
    }

    public void setMoore(boolean moore) {
        this.moore = moore;
    }

    public Coordinates getSize() {
        return size;
    }

    public void setSize(Coordinates size) {
        this.size = size;
    }
}
