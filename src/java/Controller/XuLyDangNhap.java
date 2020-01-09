/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.gioHangDao;
import DAO.khachHangDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.Alert;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.khachHang;

/**
 *
 * @author Tong Duy Hai
 */
public class XuLyDangNhap extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String username= request.getParameter("username");
		String pass = request.getParameter("password");
		String action = request.getParameter("action");
                khachHangDAO khDAO = new khachHangDAO();
                if(action.equals("dn")){
                    if(khDAO.kiemTraDangNhap(username, pass)){
			HttpSession session = request.getSession();
			session.setAttribute("USER", username);
                        new gioHangDao().hienThiGioHangDaChon(username);
			RequestDispatcher rd = request.getRequestDispatcher("trangChu.jsp");
                        rd.forward(request, response);
		}
                else{
                    HttpSession session = request.getSession();
                    session.setAttribute("LOI", "Sai thông tin tài khoản");
                    response.sendRedirect("dangnhap.jsp");
		}
                }
                else if(action.equals("dk")){
                    RequestDispatcher rd = request.getRequestDispatcher("dangki.jsp");
                    rd.forward(request, response);
                }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
//

}
