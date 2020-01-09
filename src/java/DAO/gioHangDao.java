/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.gioHangDao.gioHang;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import model.connect;
import model.gioHang;
import model.sanPham;
import model.tinhTien;
import net.sf.ehcache.store.compound.factories.AATreeSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Tong Duy Hai
 */
public class gioHangDao {
    public static ArrayList<sanPham> gioHang= new ArrayList<>();
    public static ArrayList<sanPham> dsSanPham = new ArrayList<>(); //load toàn bộ sp từ db lên trang chủ
    public static ArrayList<gioHang> list = new ArrayList<>(); //giỏ hàng đã mua
    public static ArrayList<tinhTien> ls = new ArrayList<>(); // tiền trong giỏ hàng đã mua
    public static Set<String> dsSanPhamTheoTen = new AATreeSet<>(); // dùng để add vào ô tìm tiếm, gợi ý tìm kiếm
    public static ArrayList<sanPham> getDsSanPham() {
        try{
            dsSanPham.removeAll(dsSanPham);
            Connection con = connect.getConnection();
            String sql = "select * from SANPHAM";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                sanPham sp = new sanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
                dsSanPham.add(sp);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dsSanPham;
    }
    public static Set<String> getDsSanPhamTheoTen() {
        try{
            dsSanPhamTheoTen.removeAll(dsSanPhamTheoTen);
            Connection con = connect.getConnection();
            String sql = "select * from SANPHAM";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                dsSanPhamTheoTen.add(rs.getString("tenSP"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dsSanPhamTheoTen;
    }
    public static ArrayList<sanPham> timKiemSanPham(String tensp) {
        ArrayList<sanPham> ds = new ArrayList<>();
        try{
            Connection con = connect.getConnection();
            String sql = "select * from SANPHAM where tenSP = '"+tensp+"'";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                sanPham sp = new sanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
                ds.add(sp);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ds;
    }
    public static void xoaSP(String masp){
        try{
            Connection con = connect.getConnection();
            String sql = "Delete from SANPHAM where MASP = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, masp);
            ps.executeUpdate();
        }
        catch(Exception e){
            
        }
    }
    public static void xoaKH(String user){
        try{
            Connection con = connect.getConnection();
            String sql = "Delete from KHACHHANG where username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<sanPham> getGioHang() {
        return gioHang;
    }
    public static void removeGioHang(){ // xóa toàn bộ giỏ hàng từ list
        gioHang.removeAll(gioHang);
    }
    public  void setGioHang(ArrayList<sanPham> gioHang) {
        gioHangDao.gioHang = gioHang;
    }
    
    public boolean kiemTraSanPhamCoTrongGioHangChua(String maSanPham) {
        for (int i = 0; i < gioHang.size(); i++) {
            if (gioHang.get(i).getMasanpham().equals(maSanPham)) {
                return true;
            }
        }
        return false;
    }

    public boolean themVaoGioHang(String maSanPham) {
//        ArrayList<sanPham> dsSanPham = new ArrayList<>();
        boolean kiemTra = kiemTraSanPhamCoTrongGioHangChua(maSanPham);
        for (int i = 0; i < dsSanPham.size(); i++) {
            if (dsSanPham.get(i).getMasanpham().equals(maSanPham) && kiemTra == false) {
                gioHang.add(dsSanPham.get(i));
                return true;
            }
        }
        if (kiemTra == true) {
            int index = gioHang.size();
            for (int i = 0; i < index; i++) {
                if (gioHang.get(i).getMasanpham().equals(maSanPham)) {
                    gioHang.get(i).setSoluongmua(Integer.parseInt(gioHang.get(i).getSoluongmua()) + 1 +"");
                    index = i;
                }
            }
        }
        return false;
    }
    public double tongTien(){ // tính tiền trong giỏ hàng từ list gioHang
        double tien=0;
        for(sanPham sp : gioHang){
            tien += Double.parseDouble(sp.getGia())* Double.parseDouble(sp.getSoluongmua());
        }
        return tien;
    }
    public boolean xoaSanPhamRaKhoiGioHang(String maSanPham) {
        for (int i = 0; i < gioHang.size(); i++) {
            if (gioHang.get(i).getMasanpham().equals(maSanPham)) {
                gioHang.remove(i);
                return true;
            }
        }
        return false;
    }
    public void luuGioHangVaoDB(String masp,String tensp,String gia,String soluong,String makh){
        try{
            Connection con = connect.getConnection();
            String sql = "insert into GIOHANG(masanpham,tensanpham,dongia,soluong,makhachhang) values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, masp);
            ps.setString(2, tensp);
            ps.setString(3, gia);
            ps.setString(4, soluong);
            ps.setString(5, makh);
            ps.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<gioHang> xemHangDaMua(String makh){
        try{
            list.removeAll(list);
            Connection con = connect.getConnection();
            String sql= "select * from GIOHANG where makhachhang = '"+makh+"' ";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                gioHang spp = new gioHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(spp);
            }
        }
        catch(Exception e){
            
        }
        return list;
    }
    public ArrayList<tinhTien> hienThiTongTien(String makh){ //tính tổng tiền giỏ hàng đã mua từ db
        try{
            ls.removeAll(ls);
            Connection con = connect.getConnection();
            String sql = "select sum(dongia*soluong) as thanhtien from giohang where makhachhang = N'"+makh+"' group by makhachhang ";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                tinhTien tt = new tinhTien(rs.getString("thanhtien"));
                ls.add(tt);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ls;
    }
    public boolean luuJSON(String username) throws SQLException{ // lưu JSON để lưu giỏ hàng đã chọn
        Connection con = connect.getConnection();
        try{
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(gioHang);
//            System.out.println(jsonInString);
                String sql = "update KHACHHANG set giohang = '"+jsonInString+"' where username = '"+username+"' ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.execute();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean hienThiGioHangDaChon(String username){ // chuyển đổi JSON sang list gioHang
        try{
            String abb = "";
            Connection con = connect.getConnection();
            String sql = "select giohang from KHACHHANG where username = '"+username+"' ";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                abb += rs.getString("giohang");
            }
            Object objj = JSONValue.parse(abb);
            JSONArray jsonArray = (JSONArray) objj;
            System.out.println(jsonArray);
            for(int i=0;i<jsonArray.size();i++){
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    String masanpham = (String) object.get("masanpham");
                    String tensanpham = (String) object.get("tensanpham");
                    String gia = (String) object.get("gia");
                    String soluong = (String) object.get("soluong");
                    String soluongmua = (String) object.get("soluongmua");
                    String hinhanh = (String) object.get("hinhanh");
                    sanPham sp = new sanPham(masanpham, tensanpham, gia, soluongmua);
                    gioHang.add(sp);
                }
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
}
