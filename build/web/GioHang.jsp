<%-- 
    Document   : GioHang
    Created on : Oct 9, 2019, 9:10:15 PM
    Author     : Tong Duy Hai
--%>

<%@page import="org.json.simple.JSONValue"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.sanPham"%>
<%@page import="DAO.gioHangDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Giỏ hàng của bạn</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="" crossorigin="anonymous"></script>
    </head>
    <body>
         <a class="navbar-brand" href="#">Giỏ hàng của ${sessionScope.USER} </a>
        <%
            Object kh = session.getAttribute("USER");
            if (kh == null) {
                response.sendRedirect("dangnhap.jsp");
            }
        %>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Xin chào,${sessionScope.USER} </a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="trangChu.jsp">Trang chủ</a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="hangDaMua">Đã mua<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Page 1-2</a></li>
                            <li><a href="#">Page 1-3</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="dangki.jsp"><span class="glyphicon glyphicon-user"></span> Đăng kí</a></li>
                    <li><a href="dangnhap.jsp"><span class="glyphicon glyphicon-log-in"></span> Đăng nhập</a></li>
                    <li><a href="DangXuat"><span class="glyphicon glyphicon-user"></span> Đăng xuất</a></li>
                </ul>
            </div>
        </nav>
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
            <% gioHangDao cart = new gioHangDao();
                ArrayList<sanPham>  gioHang = new gioHangDao().getGioHang();
            %>
            <table class="table table-hover">
                <thead>
		    <tr>
			<th>STT</th>
			<th>Mã sản phẩm</th>
			<th>Tên sản phẩm</th>
			<th>Giá</th>
			<th>Số lượng</th>
			<th>Xóa sản phẩm</th>
                    </tr>
		</thead>
                <tbody>
                    <%int count=0;
                    for(int i = 0;i<gioHang.size();i++){ 
                        count++;%>
			<tr>
                            <th scope="row"><% out.print(count);%></th>
                            <td><%out.print(gioHang.get(i).getMasanpham()); %></td>
                            <td><%out.print(gioHang.get(i).getTensanpham()); %></td>
                            <td><%out.print(gioHang.get(i).getGia()); %></td>
                            <td><%out.print(gioHang.get(i).getSoluongmua()); %></td>

                            <td>
                                <a href="XuLyXoaSP?maSanPham=<%=gioHang.get(i).getMasanpham() %>">
                                    <button type="button" class="btn btn-danger" aria-label="Right Align">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </button>
                                </a>
                            </td>
			</tr>
                    <%} %>
		</tbody>
            </table>
                    <h2 style="color: red;" align="right">Tổng tiền :<%=cart.tongTien()%>$</h2>
            </div>
        </div>
            <div class="mua">
                <form action="luuGioHang" method="post" >
                <button class="btn btn-success">Mua hàng</button>
                </form>
            </div>
    </center>
    </body>
</html>
