/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.connect;

/**
 *
 * @author Tong Duy Hai
 */
public class hoanThanhUpDateKH extends HttpServlet {

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
            String user = request.getParameter("user");
            String tenkh = request.getParameter("tenkh");
            String pass = request.getParameter("pass");
            String tuoi = request.getParameter("tuoi");
            String sdt = request.getParameter("sdt");
            String diachi = request.getParameter("diachi");
            try{
                Connection con = connect.getConnection();
                String sql = "update KHACHHANG set tenKH=?,pass=?,tuoi=?,sdt=?,diachi=? where username=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, tenkh);
                ps.setString(2, pass);
                ps.setString(3, tuoi);
                ps.setString(4, sdt);
                ps.setString(5, diachi);
                ps.setString(6, user);
                ps.executeUpdate();
                out.print("<h2 style=\"text-align: center\" >Update successful</h2>");
                out.print("<center><a href=\"capNhatThanhVien.jsp\">Display</a></center>");
            }
            catch(Exception e){
                e.printStackTrace();
            }
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */


}
