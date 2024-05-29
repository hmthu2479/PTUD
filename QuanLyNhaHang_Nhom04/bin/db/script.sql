USE [master]
GO

CREATE DATABASE [QuanLyNhaHang]
GO

USE [QuanLyNhaHang]
GO

CREATE TABLE dbo.KhuVuc(
    maKhuVuc nvarchar(10) NOT NULL PRIMARY KEY,
    tenKhuVuc nvarchar(30) NULL
)


CREATE TABLE dbo.Phong(
    maPhong nvarchar(10) NOT NULL PRIMARY KEY,
    tenPhong nchar(30) NULL,
    maKhuVuc nvarchar(10) NULL,
    soGhe int NULL,
    FOREIGN KEY (maKhuVuc) REFERENCES dbo.KhuVuc(maKhuVuc)
)

CREATE TABLE dbo.Ban(
    maBan nvarchar(30) NOT NULL,
    soBan nvarchar(30) NULL,
    soGhe int NULL,
    maKhuVuc nvarchar(10) NULL,
    maPhong nvarchar(10) NULL,
    PRIMARY KEY CLUSTERED (maBan),
    FOREIGN KEY (maKhuVuc) REFERENCES dbo.KhuVuc(maKhuVuc),
    FOREIGN KEY (maPhong) REFERENCES dbo.Phong(maPhong)
)
CREATE TABLE dbo.MonAn (
    maMonAn VARCHAR(10) PRIMARY KEY,
    LoaiMonAn NVARCHAR(50) NOT NULL,
    TenMonAn NVARCHAR(100) NOT NULL,
    donGia float NOT NULL
);

CREATE TABLE dbo.MonNuoc (
    maMonNuoc VARCHAR(10) PRIMARY KEY, 
    loaiMonNuoc NVARCHAR(50) NOT NULL,
    tenMonNuoc NVARCHAR(100) NOT NULL,
    donGia float NOT NULL
);
CREATE TABLE dbo.KhachHang(
    maKH nvarchar(10) NOT NULL,
    tenKH nvarchar(50) NULL,
    phai nchar(10) NULL,
    SDT nvarchar(20) NULL,
    diaChi nvarchar(100) NULL,
    PRIMARY KEY CLUSTERED (maKH)
)


CREATE TABLE dbo.NhanVien(
    maNV nvarchar(10) NOT NULL PRIMARY KEY,
    tenNV nvarchar(20) NULL,
    phai nvarchar(10) NULL,
    tuoi int NULL,
    SDT nvarchar(20) NULL
)


CREATE TABLE dbo.PhieuDatBan(
    maPhieuDatBan nvarchar(10) NOT NULL PRIMARY KEY,
    maKhuVuc nvarchar(10) NULL,
    maPhong nvarchar(10) NULL,
    maBan nvarchar(30) NULL,
    soNguoi int NULL,
    ngayDat date NULL,
    ngayLap date NULL,
    gioDat nchar(10) NULL,
    maKH nvarchar(10) NULL,
    maNV nvarchar(10) NULL,
    FOREIGN KEY (maKhuVuc) REFERENCES dbo.KhuVuc(maKhuVuc),
    FOREIGN KEY (maPhong) REFERENCES dbo.Phong(maPhong),
    FOREIGN KEY (maBan) REFERENCES dbo.Ban(maBan),
    FOREIGN KEY (maKH) REFERENCES dbo.KhachHang(maKH),
    FOREIGN KEY (maNV) REFERENCES dbo.NhanVien(maNV)
)

CREATE TABLE dbo.ChiTietPhieuDatBan(
    maPhieuDatBan nvarchar(10) NOT NULL,
    soLuongMon int NULL,
    donGia float NULL,
    maBan nvarchar(30) NOT NULL,
    PRIMARY KEY CLUSTERED (maPhieuDatBan, maBan),
    FOREIGN KEY (maPhieuDatBan) REFERENCES dbo.PhieuDatBan(maPhieuDatBan),
    FOREIGN KEY (maBan) REFERENCES dbo.Ban(maBan)
)
CREATE TABLE dbo.HoaDon(
    maHoaDon nvarchar(10) NOT NULL,
	tongTien float NULL,
	maKhuVuc nvarchar(10) NULL,
	maPhong nvarchar(10) NULL,
	maBan nvarchar(30) NULL,
	maNV nvarchar(10) NULL,
    ngayLap datetime NULL,
	maKH nvarchar(10) NULL,
	ngayDat date NULL,
    PRIMARY KEY CLUSTERED (maHoaDon),
    FOREIGN KEY (maKH) REFERENCES dbo.KhachHang(maKH),
    FOREIGN KEY (maKhuVuc) REFERENCES dbo.KhuVuc(maKhuVuc),
    FOREIGN KEY (maPhong) REFERENCES dbo.Phong(maPhong),
    FOREIGN KEY (maBan) REFERENCES dbo.Ban(maBan),
    FOREIGN KEY (maNV) REFERENCES dbo.NhanVien(maNV)
)

CREATE TABLE dbo.ChiTietHoaDon(
    maHoaDon nvarchar(10) NOT NULL,
	tenMon nvarchar(50) NULL,
    soLuongMon int NULL,
    donGia float NULL,
    thanhTien float NULL,
    FOREIGN KEY (maHoaDon) REFERENCES dbo.HoaDon(maHoaDon)

)
INSERT INTO dbo.KhuVuc (maKhuVuc, tenKhuVuc)
VALUES ('KV01', N'Ngoài trời'),
       ('KV02', N'Tầng 1'),
       ('KV03', N'Tầng 2');
INSERT INTO dbo.Phong (maPhong, tenPhong, maKhuVuc, soGhe)
VALUES (N'P004', N'Phòng Máy Lạnh', N'KV01', Null),
	   (N'P005', N'Phòng Vip 1', N'KV01', Null),
	   (N'P007', N'Phòng Vip 2', N'KV03', Null);
INSERT INTO dbo.Ban (maBan, soBan, soGhe, maKhuVuc, maPhong)
VALUES (N'B004', N'Bàn 4', 4, N'KV01', N'P004'),
       (N'B005', N'Bàn 5', 6, N'KV02', null),
       (N'B006', N'Bàn 1', 5, N'KV01', null),
	   (N'B007', N'Bàn 2', 4, N'KV02', null),
       (N'B008', N'Bàn 3', 5, N'KV03', null),
       (N'B009', N'Bàn 6', 6, N'KV03', null);
INSERT INTO dbo.MonAn (maMonAn, LoaiMonAn, TenMonAn, donGia)
VALUES 
    ('MA001',N'Cơm', N'Cơm tấm sườn',50000),
    ('MA002', N'Món Nước', N'Bún bò Huế',50000);


INSERT INTO dbo.KhachHang (maKH, tenKH, phai, SDT, diaChi)
VALUES  (N'KH001', N'Nguyễn A', N'Nam', N'0313579454', N'TPHCM'),
		(N'KH002', N'Trần A', N'Nữ', N'0278951333', N'TPHCM'),
		(N'KH003', N'Võ B', N'Nam', N'0916462564', N'TPHCM');

INSERT INTO dbo.NhanVien (maNV, tenNV, phai, tuoi, SDT)
VALUES (N'NV001', N'Trần Bảo N', N'Nữ', 22, N'09137984666'),
       (N'NV002', N'Huỳnh K', N'Nam', 23, N'0923498462'),
       (N'NV003', N'Lê Văn C', N'Nam', 25, N'0789354359');

INSERT INTO dbo.MonNuoc (maMonNuoc, loaiMonNuoc, tenMonNuoc, donGia)
VALUES 
    ('MN001',N'Nước ngọt', N'String',20000 ),
    ('MN002', N'Món nước khác', N'Trà tắc',15000);
INSERT INTO dbo.PhieuDatBan (maPhieuDatBan, maKhuVuc, maPhong, maBan, soNguoi, ngayDat, ngayLap, gioDat, maKH, maNV)
VALUES ('PH001', 'KV02', null, 'B005', 4, '2024-04-12', '2024-04-11', '10:00', N'KH001', N'NV001');

INSERT INTO dbo.HoaDon (maHoaDon, tongTien, maKhuVuc, maPhong,maBan,maNV,ngayLap,maKH,ngayDat)
VALUES 
	('HD014',50000,'KV01',null,'B005','NV001','2024-04-12T09:00:00','KH001','2024-04-12');
INSERT INTO dbo.ChiTietHoaDon (maHoaDon, tenMon, soLuongMon, donGia,thanhTien)
VALUES
	('HD014',N'Bún bò Huế',1,50000,50000);

