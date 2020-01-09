/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.khachHangDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import model.khachHang;

/**
 *
 * @author Tong Duy Hai
 */
public class XuLyDangKy extends HttpServlet {
    
private static final long serialVersionUID = 1L;
    public XuLyDangKy() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//        processRequest(request, response);
          request.setCharacterEncoding("utf-8");
          response.setContentType("text/html;charset=UTF-8");
            String ten= request.getParameter("ten");
              String username= request.getParameter("username");
              String pass= request.getParameter("password");
              String tuoi= request.getParameter("tuoi");
              int tuoii = Integer.parseInt(tuoi);
              String sdt= request.getParameter("sdt");
              String diachi= request.getParameter("diachi");
              if(username.length()==0||pass.length()==0||tuoi.length()==0||sdt.length()==0|| diachi.length()==0 || ten.length()==0){
                  response.sendRedirect("dangki.jsp");
              }
              else{
                  if(new khachHangDAO().kiemTraDangKi(username)==true){
                      HttpSession sesion = request.getSession();
                      sesion.setAttribute("dk", "Tài khoản này đã tồn tại,vui lòng chọn tài khoản khác !");
                      response.sendRedirect("dangki.jsp");
                  }
                  else{
                        khachHang kh = new khachHang(ten, username, pass, tuoii, sdt, diachi);
                        if(new khachHangDAO().themTaiKhoan(kh)){
                        response.sendRedirect("dangnhap.jsp");
                        }
                        else{
                            System.out.println("Lỗi đăng kí!");
                        }
                }
            }
        }

}
