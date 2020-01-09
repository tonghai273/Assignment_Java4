<%-- 
    Document   : capNhatThanhVien
    Created on : Oct 7, 2019, 4:48:56 PM
    Author     : Tong Duy Hai
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">Quản lý thành viên</h1>
        <%
            Object kh = session.getAttribute("ADMIN");
            if (kh == null) {
                response.sendRedirect("QuyenAdmin.jsp");
            }
        %>
        <a class="navbar-brand" href="#">Xin chào,${sessionScope.ADMIN} </a>
        <sql:setDataSource driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://localhost:1433;databaseName=QLBH"
                           user="sa"
                           password="haiha"
                           var="con" />
        <sql:query dataSource="${con}" var="rs">
            select * from KHACHHANG
        </sql:query>
        <center>
            <table border="1">
                <tr>
                    <td>TT</td>
                    <td>Mã khách hàng</td>
                    <td>Tên khách hàng</td>
                    <td>Username</td>
                    <td>Password</td>
                    <td>Tuổi</td>
                    <td>Số điện thoại</td>
                    <td>Địa chỉ</td>
                </tr>
                <c:set var="count" value="0" />
                <c:forEach var="dong" items="${rs.rows}">
                    <tr>
                        <c:set var="count" value="${count+1}"/>
                        <td>${count}</td>
                        <td><c:out value="${dong.MAKH}"/></td>
                        <td><c:out value="${dong.tenKH}"/></td>
                        <td><c:out value="${dong.username}"/></td>
                        <td><c:out value="${dong.pass}"/></td>
                        <td><c:out value="${dong.tuoi}"/></td>
                        <td><c:out value="${dong.sdt}"/></td>
                        <td><c:out value="${dong.diachi}"/></td>
                        
                        <td><a href="updateKH?username=<c:out value='${dong.username}' />">Chỉnh sửa</a></td>
                        <td><a href="xoaKH?username=<c:out value='${dong.username}' />">Xóa</a></td>
                    </tr>
                </c:forEach>
            </table>
        </center>
    </body>
</html>
