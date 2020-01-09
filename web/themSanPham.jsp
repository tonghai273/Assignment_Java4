<%-- 
    Document   : themSanPham
    Created on : Oct 5, 2019, 12:28:09 PM
    Author     : Tong Duy Hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm sản phẩm</title>
    </head>
    <body>
        <a class="navbar-brand" href="#">Xin chào,${sessionScope.ADMIN} </a>
        <%
            Object kh = session.getAttribute("ADMIN");
            if (kh == null) {
                response.sendRedirect("QuyenAdmin.jsp");
            }
        %>
    <center>
        <h2>Thêm sản phẩm mới</h2>
        <form action="theSanPham" method="get" enctype="multipart/form-data">
            <table border="1">
                <tr>
                    <td>Mã Sản Phẩm</td>
                    <td><input type="text" name="masp"></td>
                </tr>
                <tr>
                    <td>Tên Sản Phẩm</td>
                    <td><input type="text" name="tensp"></td>
                </tr>
                <tr>
                    <td>Đơn giá</td>
                    <td><input type="text" name="gia"></td>
                </tr>
                <tr>
                    <td>Số lượng</td>
                    <td><input type="text" name="soluong"></td>
                </tr>
                <tr>
                    <td>Link ảnh</td>
                    <td><input type="file" name="hinhanh"></td> 
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="action" value="Save"></td>
                </tr>
            </table>
            
        </form>
    </center>
    </body>
</html>
