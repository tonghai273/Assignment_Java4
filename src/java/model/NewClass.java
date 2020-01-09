/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.gioHangDao;
import static DAO.gioHangDao.dsSanPham;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;

/**
 *
 * @author Tong Duy Hai
 */
public class NewClass {
     public static void main(String args[]) {
         try{
             String abb = "";
             ArrayList<sanPham> dsSanPham = new ArrayList<>();
            Connection con = connect.getConnection();
            String sql = "select * from SANPHAM";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                sanPham sp = new sanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
                dsSanPham.add(sp);
            }
//            for(int i=0;i<dsSanPham.size();i++){
//                String ma= dsSanPham.get(i).getMasanpham();
//                String ten= dsSanPham.get(i).getTensanpham();
//                String gia= dsSanPham.get(i).getGia();
//                String sl= dsSanPham.get(i).getSoluong();
//                String slc= dsSanPham.get(i).getSoluongmua();
//                String anh= dsSanPham.get(i).getHinhanh();
                
//                JSONObject obj = new JSONObject();
//                obj.put("ma", ma);
//                obj.put("ten", ten);
//                obj.put("gia", gia);
//                obj.put("sl", sl);
//                obj.put("slc", slc);
//                obj.put("anh", anh);
//                abb +=  JSONValue.toJSONString(obj);
//       
//                  System.out.print(abb);
//            }
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(dsSanPham);
            System.out.println(jsonInString);
//             System.out.println(abb);


//                //giai ma JSON
                String a = "[{\"masanpham\":\"ip\",\"tensanpham\":\"Iphone7\",\"gia\":\"1000.0\",\"soluong\":\"11\",\"soluongmua\":\"1\",\"hinhanh\":\"ip7.jpg\"},{\"masanpham\":\"op\",\"tensanpham\":\"oppo\",\"gia\":\"750.0\",\"soluong\":\"10\",\"soluongmua\":\"1\",\"hinhanh\":\"oppo.jpg\"},{\"masanpham\":\"ss\",\"tensanpham\":\"samsung s8\",\"gia\":\"900.0\",\"soluong\":\"10\",\"soluongmua\":\"1\",\"hinhanh\":\"ss8.jpg\"}]";
                Object objj = JSONValue.parse(a);
                JSONArray jsonArray = (JSONArray) objj;
//                System.out.println(jsonArray);
                for(int i=0;i<jsonArray.size();i++){
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    System.out.println(object.get("masanpham"));
                    System.out.println(object.get("tensanpham"));
                    System.out.println(object.get("gia"));
                    System.out.println(object.get("soluong"));
                    System.out.println(object.get("soluongmua"));
                    System.out.println(object.get("hinhanh"));
                    System.out.println("-----------");
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
 
         }
     
    }
