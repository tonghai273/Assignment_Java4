<%-- 
    Document   : capNhatSanPham
    Created on : Oct 5, 2019, 11:20:43 PM
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
        <h1 align="center">Manage</h1>
        <a class="navbar-brand" href="#">Xin chào,${sessionScope.ADMIN} </a>
        <%
            Object kh = session.getAttribute("ADMIN");
            if (kh == null) {
                response.sendRedirect("QuyenAdmin.jsp");
            }
        %>
        <center><a href="capNhatThanhVien.jsp">Quản lý thành viên</a><br/></center>
        <h2 align="center">Danh sách sản phẩm</h2>
    <center><a href="themSanPham.jsp">Thêm sản phẩm</a><br/></center>
        <sql:setDataSource driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
                           url="jdbc:sqlserver://localhost:1433;databaseName=QLBH"
                           user="sa"
                           password="haiha"
                           var="con" />
        <sql:query dataSource="${con}" var="rs">
            select * from SANPHAM
        </sql:query>
        <center>
            <table border="1">
                <tr>
                    <td>TT</td>
                    <td>Mã Sản Phẩm</td>
                    <td>Tên Sản Phẩm</td>
                    <td>Giá</td>
                    <td>Số lượng</td>
                    <td>Hình ảnh</td>
                    <!--<td>File Ảnh</td>-->
                </tr>
                <c:set var="count" value="0" />
                <c:forEach var="dong" items="${rs.rows}">
                    <tr>
                        <c:set var="count" value="${count+1}"/>
                        <td>${count}</td>
                        <td><c:out value="${dong.MASP}"/></td>
                        <td><c:out value="${dong.tenSP}"/></td>
                        <td><c:out value="${dong.gia}"/></td>
                        <td><c:out value="${dong.soLuong}"/></td>
                        <td><image src="img\${dong.hinhanh}" with="30" height="30"/></td>

                        <td><a href="updateSP?masanpham=<c:out value='${dong.MASP}' />">Chỉnh sửa</a></td>
                        <td><a href="quanLySanPham?masanpham=<c:out value='${dong.MASP}' />">Xóa</a></td>
                    </tr>
                </c:forEach>
            </table>
        </center>
    </body>
</html>
