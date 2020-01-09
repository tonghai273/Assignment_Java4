<%-- 
    Document   : welcome
    Created on : Sep 24, 2019, 3:05:30 PM
    Author     : Tong Duy Hai
--%>
<%@page import="model.khachHang"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>welcome</title>
    </head>
    <body>
        <font>wellcome, ${sessionScope.USER}</font>
    </body>
</html>
