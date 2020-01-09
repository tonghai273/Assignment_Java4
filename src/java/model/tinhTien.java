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
public class tinhTien implements Serializable{
    String tien;

    public tinhTien(String tien) {
        this.tien = tien;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }
    
}
