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

/**
 *
 * @author Tong Duy Hai
 */
public class XuLyMuaSanPham extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String maSP  = request.getParameter("masanpham");
		HttpSession session = request.getSession();
		String kh=  (String) session.getAttribute("USER");
		if(kh!=null){
			new gioHangDao().themVaoGioHang(maSP);
			response.sendRedirect("trangChu.jsp");
                    try {
                        new gioHangDao().luuJSON(kh);
                    } catch (SQLException ex) {
                        Logger.getLogger(XuLyMuaSanPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}else{
			response.sendRedirect("dangnhap.jsp");
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
