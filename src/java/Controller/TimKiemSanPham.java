/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.gioHangDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
public class TimKiemSanPham extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tenSP = request.getParameter("ten");
        if(tenSP.length()==0){
            response.sendRedirect("trangChu.jsp");
        }
        else{
            ArrayList<sanPham> ds = new gioHangDao().timKiemSanPham(tenSP);
            HttpSession session = request.getSession();
            session.setAttribute("ten", ds);
            RequestDispatcher rs = request.getRequestDispatcher("KetQuaTimKiem.jsp");
            rs.forward(request, response);
        }
    }

}
