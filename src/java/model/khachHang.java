/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Tong Duy Hai
 */
public class khachHang implements Serializable{
    public String ten;
    public String username;
    public String password;
    public int tuoi;
    public String sdt;
    public String diachi;

    public khachHang() {
    }
    
    public khachHang(String ten, String username, String password, int tuoi, String sdt, String diachi) {
        this.ten = ten;
        this.username = username;
        this.password = password;
        this.tuoi = tuoi;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public khachHang(String username) {
        this.username = username;
    }

    public khachHang(String name, String pass, int tuoi, String sdt, String diachi) {
        super();
        this.ten = name;
        this.password = pass;
        this.tuoi = tuoi;
        this.sdt = sdt;
        this.diachi = diachi;
    }


    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    
}
