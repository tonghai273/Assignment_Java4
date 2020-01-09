<%-- 
    Document   : trangChu
    Created on : Oct 3, 2019, 10:12:42 AM
    Author     : Tong Duy Hai
--%>

<%@page import="java.util.Set"%>
<%@page import="DAO.gioHangDao"%>
<%@page import="model.sanPham"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trang chủ</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style type="text/css">
            .khungsanpham{
                padding-top: 20px;
                padding-left: 30px;
                float: left;
            }
            .boder{
                border: solid;
                width: 200px;
                height: 300px;
            }
            .img{
                margin-left: 3px;
                margin-top: 3px;
                width: 190px;
                height: 180px;
            }
            .gia{
                text-align: center;
                color: red;
                margin-top: 10px;
                margin-bottom: 10px;
            }
            .ten{
                text-align: center;
                color: black;
                margin-top: 10px;
                margin-bottom: 10px;
            }
            .mua{
                color: red;
            }
        </style>
        <script>
            function valuedate(){
                if(document.getElementById("ten").value.length==0){
                    alert("Vui lòng nhập tên sản phẩm khi tìm kiếm ");
                }
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Xin chào,${sessionScope.USER} </a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="trangChu.jsp">Trang chủ</a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Quản lý <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="QuyenAdmin.jsp">Đăng nhập quản trị</a></li>
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
                <div class="container">
                    <form class="form-horizontal" action="TimKiemSanPham" method="post">
                        <%Set<String> dsTheoTen= new gioHangDao().getDsSanPhamTheoTen(); %>
                        <datalist id="dsTheoTen">
                            <%for(String a : dsTheoTen){%>
                            <option value="<%=a%>"></option>
                            <%}%>
                        </datalist>
                        <div class="form-group">
                        <label class="control-label col-sm-2">Tìm kiếm sản phẩm: </label>
                        <div>
                            <input type="text" id="ten" name="ten" list="dsTheoTen" >
                        </div>
                        </div>
                        <div class="form-group">        
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-success" onclick="valuedate()">Tìm kiếm</button>
                            </div>
                        </div>
                    </form>
                </div>
        <div class="container">
            <a href="GioHang.jsp"><button type="button" class="btn btn-danger" aria-label="Right Align">
                    <span class="glyphicon glyphicon-cart" aria-hidden="true"></span>Xem giỏ hàng
                </button></a>
            <h3>Các sản phẩm hiện có</h3>
            <%ArrayList<sanPham> dsSanPham = new gioHangDao().getDsSanPham();%>
            <%	for (int i = 0; i < dsSanPham.size(); i++) {%>
            <div class="khungsanpham">
                <div class="boder">
                    <div class="img">
                        <img src="img\<%=dsSanPham.get(i).getHinhanh()%>" width="190px" height="180px">
                    </div>
                    <div class="ten">
                        <%=dsSanPham.get(i).getTensanpham()%>
                    </div>
                    <div class="gia">
                        Giá:<%=dsSanPham.get(i).getGia()%>$ 
                    </div>
                    <div class="mua">
                        <center><a href="XuLyMuaSanPham?masanpham=<%=dsSanPham.get(i).getMasanpham()%>"><button class="btn btn-success">Mua ngay</button></a></center>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
    </body>
</html>
