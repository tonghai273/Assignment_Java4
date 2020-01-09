/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.gioHangDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.sanPham;
import static org.apache.tomcat.jni.User.username;

/**
 *
 * @author Tong Duy Hai
 */
public class DangXuat extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                HttpSession sessionn = request.getSession();
                String usename = (String) sessionn.getAttribute("USER");
                if(usename != null){
                    try {
                        new gioHangDao().luuJSON(usename);
                    } catch (SQLException ex) {
                        Logger.getLogger(DangXuat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    HttpSession session = request.getSession(false);
                    session.invalidate();
                    new gioHangDao().removeGioHang();
                    response.sendRedirect("dangnhap.jsp");
                }
                else{
                    response.sendRedirect("trangChu.jsp");
                }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
