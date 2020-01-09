<%-- 
    Document   : upDateSP
    Created on : Oct 5, 2019, 11:35:13 PM
    Author     : Tong Duy Hai
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <%--<c:if test="${update != null}">--%>
        <form action="hoanThanhUpDate" method="get">
            <table border="1">
                <tr>
                    <td>Mã Sản Phẩm</td>
                    <td><input type="text" name="masp" value="${sessionScope.update}" readonly=""/></td>
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
                    <td><input type="text" name="soluong" /></td>
                </tr>
                <tr>
                    <td>Hình ảnh</td>
                    <td><input type="file" name="hinhanh" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="action" value="Save"></td>
                </tr>
            </table>
        </form>
        <%--</c:if>--%>
    </body>
</html>
