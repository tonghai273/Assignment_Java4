<%-- 
    Document   : QuyenAdmin
    Created on : Sep 26, 2019, 2:45:24 PM
    Author     : Tong Duy Hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <title>Admin</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    </head>
    <body>
        <div class="container">
        <h1>Đăng nhập với tư cách quản trị viên</h1>
        <form class="form-horizontal" action="XuLyAdmin" method="post">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Tài khoản:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="email" name="username" placeholder="ví dụ: admin">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Mật khẩu:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="pwd" name="password" placeholder="Nhập mật khẩu : abcd">
      </div>
    </div>
<!--    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Tên</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="pwd" name="ten" placeholder="Nhập tên của bạn...">
      </div>
    </div>-->
<p align="center" color="red"><%
    	ServletContext context = getServletContext();
    		  String er =(String) context.getAttribute("error");
    		  if(er !=null){
    			  out.print(er);
    		  }
    %></p>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-danger" name="action" value="dn">Đăng nhập</button>
      </div>
    </div>
  </form>
    </div>
    </body>
</html>
