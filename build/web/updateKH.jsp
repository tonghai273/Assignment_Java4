<%-- 
    Document   : updateKH
    Created on : Oct 7, 2019, 5:06:14 PM
    Author     : Tong Duy Hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Object kh = session.getAttribute("ADMIN");
            if (kh == null) {
                response.sendRedirect("QuyenAdmin.jsp");
            }
        %>
    <center>
        <h2>Cập nhật thông tin khách hàng</h2>
        <form action="hoanThanhUpDateKH" method="get">
            <table border="1">
                <input type="hidden" name="user" value="${sessionScope.user}" />
                <tr>
                    <td>Tên khách hàng</td>
                    <td><input type="text" name="tenkh"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="pass"></td>
                </tr>
                <tr>
                    <td>Tuổi</td>
                    <td><input type="text" name="tuoi" /></td>
                </tr>
                <tr>
                    <td>Số điện thoại</td>
                    <td><input type="text" name="sdt" /></td>
                </tr>
                <tr>
                    <td>Địa chỉ</td>
                    <td><input type="text" name="diachi" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="action" value="Save"></td>
                </tr>
            </table>
        </form>
        </center>
    </body>
</html>
