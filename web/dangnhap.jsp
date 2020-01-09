<%-- 
    Document   : dangnhap
    Created on : Sep 21, 2019, 11:04:36 AM
    Author     : Tong Duy Hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng nhập</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
  <h2>Nhập thông tin đăng nhập</h2>
  <form class="form-horizontal" action="XuLyDangNhap" method="get">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Tài khoản:</label>
      <div class="col-sm-10">
        <input type="text" id="email" name="username" class="form-control" placeholder="ví dụ: admin">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Mật khẩu:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="pwd" name="password" placeholder="Nhập mật khẩu : abcd">
      </div>
    </div>
      <p style="color: red;font-size: small" align="center">${sessionScope.LOI} </p>
<p><%
//    	ServletContext context = getServletContext();
//    		  String er =(String) context.getAttribute("error");
//    		  if(er != null){
//    			  out.print(er);
//    		  }
    %></p>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-danger" name="action" value="dn">Đăng nhập</button>
        <button type="submit" class="btn btn-danger" name="action" value="dk">Đăng Kí Tài Khoản</button>
      </div>
    </div>
  </form>
</div>
    </body>
</html>
