<%-- 
    Document   : hangDaMua
    Created on : Oct 23, 2019, 10:51:17 PM
    Author     : Tong Duy Hai
--%>

<%@page import="model.tinhTien"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.Set"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="model.gioHang"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.gioHangDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Đơn hàng đã mua</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="" crossorigin="anonymous"></script>
    </head>
    <body>
        <h3 style="color: red;" align="left">Đơn hàng đã mua</h3>
    <center>
        <div class="container" id="">
            <div class="row">
		<p style="text-align: center">
		<div class="col-lg-6 col-lg-push-3">
                    <div class="input-group">
			<span class="input-group-btn">
			<a href="trangChu.jsp">
                            <button type="button" class="btn btn-primary" aria-label="Right Align">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Tiếp tục mua hàng
                            </button></a>
			</span>
                    </div>
		</div>
            </p><br>
            <%
                ArrayList<gioHang>  list = (ArrayList<gioHang>)session.getAttribute("sp");%>
            <table class="table table-hover">
                <thead>
		    <tr>
			<th>STT</th>
			<th>Mã sản phẩm</th>
			<th>Tên sản phẩm</th>
			<th>Giá</th>
			<th>Số lượng</th>
                    </tr>
		</thead>
                <tbody>
                    <%int count=0;
                    for(int i = 0;i<list.size();i++){ 
                        count++;%>
			<tr>
                            <th scope="row"><% out.print(count);%></th>
                            <td><%out.print(list.get(i).getMasp()); %></td>
                            <td><%out.print(list.get(i).getTen()); %></td>
                            <td><%out.print(list.get(i).getGia()); %></td>
                            <td><%out.print(list.get(i).getSoluong()); %></td>
			</tr>
                    <%} %>
		</tbody>
            </table>
                    <%ArrayList<tinhTien> ds = (ArrayList<tinhTien>)session.getAttribute("tt"); %>
                    <%for(int i = 0;i<ds.size();i++){ %>
                    <h2 style="color: red;" align="right">Tổng tiền <%out.print(ds.get(i).getTien()); %>$</h2>
                    <%} %>
            </div>
        </div>
    </center>
    </body>
</html>
