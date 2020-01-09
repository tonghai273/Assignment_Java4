/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.connect;

@MultipartConfig(fileSizeThreshold = 1024*1024*2, maxFileSize = 1024*1024*10,maxRequestSize = 1024*1024*50)
/**
 *
 * @author Tong Duy Hai
 */
public class theSanPham extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String masp = request.getParameter("masp");
        String tensp = request.getParameter("tensp");
        String gia = request.getParameter("gia");
        String soluong = request.getParameter("soluong");
        int soluongchon = 1;
        
        String part = request.getParameter("hinhanh");
//        File part = request.getPart("hinhanh");
//        String fileName = extracFileName(part);
//        String savePath = "E:\\JAVA\\ASSIGNMENT\\web\\img\\" + File.separator+fileName;
//        File fileSaveDir = new File(savePath);
//        part.write(savePath + File.separator);
        try{
            Connection con = connect.getConnection();
            String sql = "insert into SANPHAM(MASP,tenSP,gia,soLuong,soLuongchon,hinhanh) values(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, masp);
            ps.setString(2, tensp);
            ps.setString(3, gia);
            ps.setString(4, soluong);
            ps.setInt(5, 1);
            ps.setString(6, part);
            ps.executeUpdate();
            response.sendRedirect("capNhatSanPham.jsp");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

//    private String extracFileName(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        String[] items=contentDisp.split(";");
//        for(String s : items){
//            if(s.trim().startsWith("hinhanh")){
//                return s.substring(s.indexOf("=")+2 ,s.length()-1);
//            }
//        }
//        return "";
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

}
