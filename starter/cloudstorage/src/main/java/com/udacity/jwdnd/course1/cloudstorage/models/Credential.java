package com.udacity.jwdnd.course1.cloudstorage.models;

import java.security.SecureRandom;
import java.util.Base64;

public class Credential {

    private int credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private int userid;

    public Credential() {
    }

    public Credential(String url, String username, String password, int userid) {

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        this.url = url;
        this.username = username;
        this.key = encodedKey;
        this.password = password;
        this.userid = userid;
    }

    public int getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(int credentialid) {
        this.credentialid = credentialid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
