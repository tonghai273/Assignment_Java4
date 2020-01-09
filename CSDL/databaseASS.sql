USE master					
	GO					
	IF  EXISTS (SELECT * FROM SYSdatabases  WHERE name = N'QLBH')					
	Drop Database QLBH					
	Go

create database QLBH
go
use QLBH
go


---bảng khách hàng
if exists (select * from sysobjects where name = N'KHACHHANG')
drop table KHACHHANG
go
create table KHACHHANG
(
	MAKH int identity not null,
	tenKH nvarchar(255) null,
	username nvarchar(50) null,
	pass nvarchar(50) null,
	tuoi int null,
	sdt nvarchar(15) null,
	diachi nvarchar(MAx) null,
	giohang nvarchar(Max) null,
	constraint PK_KHACHHANG priMAry key (MAKH),
	constraint uk_username unique (username)
)
go
---bảng sản phẩm
if exists (select * from sysobjects where name = N'SANPHAM')
drop table SANPHAM
go
create table SANPHAM
(
	MASP nvarchar(50) not null,
	tenSP nvarchar(255) null,
	gia float null,
	soLuong int null,
	soLuongchon int null,
	hinhanh nvarchar(max) null,
	constraint PK_SANPHAM priMAry key (MASP),
	constraint ck_gia check (gia >0),
	constraint ck_soLuong check (soLuong >=0),
)
go
---bảng hóa đơn
if exists (select * from sysobjects where name = N'HOADON')
drop table HOADON
go
create table HOADON
(
	MAHD int identity not null,
	MAKH int not null,
	ngaymua date null,
	trangthai nvarchar(200) null,
	constraint PK_HOADON priMAry key (MAHD),
	constraint FK_HOADON_KHACHHANG foreign key (MAKH) references KHACHHANG(MAKH),
)
go
---bảng hóa đơn chi tiết
if exists (select * from sysobjects where name = N'HOADONCHITIET')
drop table HOADONCHITIET
go
create table HOADONCHITIET
(
	MAHDCT int identity not null,
	MAHD int not null,
	MASP nvarchar(50) null,
	soLuongMua int null,
	constraint PK_HOADONCHITIET priMAry key (MAHDCT),
	constraint FK_HOADONCHITIET_HOADON foreign key (MAHD) references HOADON(MAHD),
	constraint FK_HOADONCHITIET_SANPHAM foreign key (MASP) references SANPHAM(MASP),
	constraint ck_soLuongMua check (soLuongMua >0),
)
go
---giỏ hàng

if exists (select * from sysobjects where name = N'GIOHANG')
drop table GIOHANG
go
create table GIOHANG
(
	masanpham nvarchar(50) null,
	tensanpham  nvarchar(50) null,
	dongia  float null,
	soluong int null,
	makhachhang  nvarchar(50) null
	--constraint PK_GIOHANG priMAry key (masanpham)
)
go
select sum(dongia*soluong) as thanhtien from giohang where makhachhang = N'anh' group by makhachhang 

-----tạo thủ tục chèn dữ liệu vào bảng khách hàng
if OBJECT_ID('spKH') is not null
drop proc spKH
go
create proc spKH
	@tenKH nvarchar(255)= null,
	@username nvarchar(50)= null,
	@pass nvarchar(50)=null, 
	@tuoi int= null,
	@sdt nvarchar(15)= null,
	@diachi nvarchar(MAx)= null,
	@giohang  nvarchar(MAx)= null
as
begin

	begin
	insert into KHACHHANG
	values (@tenKH,@username,@pass,@tuoi,@sdt,@diachi,@giohang)
	print 'chen du lieu thanh cong'
	end
end
go
-----tạo thủ tục chèn dữ liệu vào bảng sản phẩm
if OBJECT_ID('spSP') is not null
drop proc spSP
go
create proc spSP
	@maSP nvarchar(50) = null,
	@tenSP nvarchar(255)= null,
	@gia float= null,
	@soLuong int= null,
	@soLuongchon int= null,
	@hinhanh nvarchar(max)= null
as
begin
	if(@maSP is null)
	print 'Yeu cau nhap lieu day du'
	else if exists (select * from SANPHAM where @maSP=MASP)
	print 'Khoa chinh trung, khong them duoc'
	else
	begin
	insert into SANPHAM
	values (@maSP,@tenSP,@gia,@soLuong,@soLuongchon,@hinhanh)
	print 'chen du lieu thanh cong'
	end
end
go

----tạo thủ tục chèn dữ liệu vào bảng hóa đơn
if OBJECT_ID('spHD') is not null
drop proc spHD
go
create proc spHD
	
	@maKH int = null,
	@ngaymua date= null,
	@trangthai nvarchar(200)= null
as
begin
	if(@maKH is null)
	print 'Yeu cau nhap lieu day du'
	else if not exists (select * from KHACHHANG where @maKH=MAKH)
	print 'khong co ma nay, khong them duoc'
	else
	begin
	insert into HOADON
	values (@maKH,@ngaymua,@trangthai)
	print 'chen du lieu thanh cong'
	end
end
go
----tạo thủ tục chèn dữ liệu vào bảng hóa đơn chi tiết
if OBJECT_ID('spHDCT') is not null
drop proc spHDCT
go
create proc spHDCT
	@maHD int= null,
	@maSP nvarchar(50)= null,
	@soLuongMua int =null
as
begin
	if(@maHD is null)
	print 'Yeu cau nhap lieu day du'
	else if not exists (select * from HOADON where @maHD=MAHD)
	print 'khong co ma nay, khong them duoc'
	else if not exists (select * from SANPHAM where @maSP=MASP)
	print 'khong co ma san pham nay'
	else
	begin
	insert into HOADONCHITIET
	values (@maHD,@maSP,@soLuongMua)
	print 'chen du lieu thanh cong'
	end
end
go
----thêm dữ liệu vào bảng khách hàng
delete from KHACHHANG
exec spKH N'Nguyễn Thị Phương Anh','anh','anh',16,'0321456987','BG'
exec spKH N'Đặng Thanh Bình','binh','binh',20,'0147852369','HN'
exec spKH N'Tống Duy Hải','hai','hai',19,'06987856541','Thanh Hóa'


---thủ tục delete khách hàng
if OBJECT_ID('spXoaKH') is not null
drop proc spXoaKH
go
create proc spXoaKH
@user nvarchar(50)
as
BEGIN TRY
	 BEGIN TRAN
	 if OBJECT_ID('tempdb..#bang') is not null drop table #bang
	 select KHACHHANG.MAKH,MAHD
	 into #bang
	 from KHACHHANG full outer join HOADON on KHACHHANG.MAKH=HOADON.MAKH
	 where @user=username
	 --xóa bảng HOADONCHITIET
	 delete from HOADONCHITIET where MAHD IN (select MAHD from #bang)
	 --xóa bảng HOADON
	 delete from HOADON where MAKH IN (select MAKH from #bang)
	 --xóa bảng KHACHHANG
	 delete from KHACHHANG where MAKH IN (select MAKH from #bang)
	 COMMIT TRAN
	 print 'xoa thanh cong'
END TRY
BEGIN CATCH
print 'Gap loi: '+ error_message()
 ROLLBACK TRAN
END CATCH
go

exec spXoaKH anh