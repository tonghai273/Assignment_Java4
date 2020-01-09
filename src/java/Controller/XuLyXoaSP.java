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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tong Duy Hai
 */
public class XuLyXoaSP extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maSP = request.getParameter("maSanPham");
                HttpSession session = request.getSession();
                String kh= (String) session.getAttribute("USER");
		new gioHangDao().xoaSanPhamRaKhoiGioHang(maSP);
                try {
                    new gioHangDao().luuJSON(kh);
                }
                catch (SQLException ex) {
                    Logger.getLogger(XuLyXoaSP.class.getName()).log(Level.SEVERE, null, ex);
                }
		response.sendRedirect("GioHang.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
