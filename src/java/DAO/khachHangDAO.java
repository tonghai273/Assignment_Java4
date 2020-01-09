/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.connect;
import model.khachHang;

/**
 *
 * @author Tong Duy Hai
 */
public class khachHangDAO implements ObjectDAO{
    @Override
    public boolean themTaiKhoan(Object obj) {
        try{
            Connection con= connect.getConnection();
            khachHang kh = (khachHang) obj;
            String sql = "exec spKH N'"+kh.getTen()+"','"+kh.getUsername()+"','"+kh.getPassword()+"','"+kh.getTuoi()+"','"+kh.getSdt()+"','"+kh.getDiachi()+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean kiemTraDangNhap(String username, String password) {
        try {
            Connection con = connect.getConnection();
            String sql ="select * from KHACHHANG where username= '"+username+"' and pass = '"+password+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
	}
        return false;
    }
    public khachHang layThongTinTaiKhoan(String username){
		try {
                    Connection con = connect.getConnection();
                    String sql = "select * from KHACHHANG where username = '"+username+"'";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String ten = rs.getString(1);
                        String user = rs.getString(2);
                        String pass = rs.getString(3);
                        int tuoi = rs.getInt(4);
                        String sdt = rs.getString(5);
                        String diachi = rs.getString(6);
                        return new khachHang(ten, username, pass, tuoi, sdt, diachi);
                    }
		}
                catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
    }
    @Override
    public boolean xoaTaiKhoan(Object obj) {
        try{
            khachHang kh = (khachHang) obj;
            new connect().thucThiSQL("exec spXoaKH '"+kh.getUsername()+"' ");
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean kiemTraDangKi(String username) {
        try {
            Connection con = connect.getConnection();
            String sql ="select * from KHACHHANG where username= '"+username+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
	}
        return false;
    }
}
