/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Tong Duy Hai
 */
public interface ObjectDAO {
    public boolean themTaiKhoan(Object obj);
    public boolean xoaTaiKhoan(Object obj);
    public boolean kiemTraDangNhap(String username,String password);
}
