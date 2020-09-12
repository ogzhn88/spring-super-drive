package com.udacity.jwdnd.course1.cloudstorage.models;


public class File {

    private int fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private int userid;
    private byte[] filedata;

    public File() {
    }

    public File(String filename, String contenttype, String filesize, int userid, byte[] filedata) {
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileid=" + fileid +
                ", filename='" + filename + '\'' +
                ", contenttype='" + contenttype + '\'' +
                ", filesize='" + filesize + '\'' +
                ", userid=" + userid +
                ", filedata=" + filedata +
                '}';
    }

    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
}
