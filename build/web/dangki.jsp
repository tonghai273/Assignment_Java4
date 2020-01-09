<%-- 
    Document   : dangki
    Created on : Sep 21, 2019, 10:40:45 AM
    Author     : Tong Duy Hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng ký</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
            function valuedate(){
                if(document.getElementById("user").value.length==0 || document.getElementById("pass").value.length==0 || document.getElementById("name").value.length==0 || document.getElementById("tuoi").value.length==0  || document.getElementById("phone").value.length==0 || document.getElementById("diachi").value.length==0){
                    alert("Vui lòng nhập thông tin đầy đủ!");
                }
            }
        </script>
    </head> 
    <body>
        <div class="container">
            <h2>Nhập thông tin để đăng kí </h2>
            <form class="form-horizontal" action="XuLyDangKy" method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Tài khoản:</label>
                <div class="col-sm-10">
                <input type="text" class="form-control" id="user" name="username" placeholder="ví dụ: tonghai273">
            </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Mật khẩu:</label>
                <div class="col-sm-10">          
                <input type="password" class="form-control" id="pass" name="password" placeholder="Nhập mật khẩu ...">
            </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Tên của bạn:</label>
                <div class="col-sm-10">          
                <input type="text" class="form-control" id="name" name="ten" placeholder="Nhập tên của bạn ...">
            </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Tuổi của bạn:</label>
                <div class="col-sm-10">          
                <input type="text" class="form-control" id="tuoi" name="tuoi" placeholder="Nhập tuổi của bạn ...">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Số điện thoại của bạn:</label>
                <div class="col-sm-10">          
                <input type="text" class="form-control" id="phone" name="sdt" placeholder="Nhập số điện thoại của bạn ...">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Địa chỉ của bạn:</label>
                <div class="col-sm-10">          
                <input type="text" class="form-control" id="diachi" name="diachi" placeholder="Nhập địa chỉ của bạn ...">
                </div>
            </div>
                <p style="color: red;font-size: small" align="center">${sessionScope.dk} </p>
            <div class="form-group">     
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Đăng kí</button>
            </div>
            </div>
                 </form>
            </div>
    </body>
</html>
