package com.naturadoni.uasmobileprogramming.mahasiswa;

public class MahasiswaModel {
    private int id;
    private String username;
    private String fullname;
    private String password;
    private String nim;

    public MahasiswaModel() {}

    public MahasiswaModel(String username, String fullname, String password, String nim) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.nim = nim;
    }

    public MahasiswaModel(int id, String username, String fullname, String password, String nim) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.nim = nim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
