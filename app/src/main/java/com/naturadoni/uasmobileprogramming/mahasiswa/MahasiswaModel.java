package com.naturadoni.uasmobileprogramming.mahasiswa;

public class MahasiswaModel {
    private int id;
    private String firstname;
    private String lastname;
    private String nim;
    private String password;

    public MahasiswaModel() {}

    public MahasiswaModel(String firstname, String lastname, String nim, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nim = nim;
        this.password = password;
    }

    public MahasiswaModel(int id, String firstname, String lastname, String nim, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nim = nim;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
