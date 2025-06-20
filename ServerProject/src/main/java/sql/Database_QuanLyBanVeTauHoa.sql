USE [master]
GO
/****** Object:  Database [QuanLyBanVeTauHoa]    Script Date: 12/13/2024 2:59:54 PM ******/
CREATE DATABASE [QuanLyBanVeTauHoa]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyBanVeTauHoa', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\QuanLyBanVeTauHoa.mdf' , SIZE = 73728KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLyBanVeTauHoa_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\QuanLyBanVeTauHoa_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyBanVeTauHoa].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET AUTO_CLOSE ON 
GO
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
USE [QuanLyBanVeTauHoa]
GO
/****** Object:  Table [dbo].[CaLam]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLam](
	[maCL] [varchar](3) NOT NULL,
	[tenCL] [nvarchar](20) NOT NULL,
	[gioBD] [time](7) NOT NULL,
	[gioKetThuc] [time](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maCL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChoNgoi]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChoNgoi](
	[maCho] [varchar](20) NOT NULL,
	[maloaiCho] [varchar](10) NOT NULL,
	[maToa] [varchar](20) NOT NULL,
	[trangThai] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maCho] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[maCV] [varchar](2) NOT NULL,
	[tenCV] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maCV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChuyenTau]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChuyenTau](
	[maChuyen] [varchar](20) NOT NULL,
	[maTau] [char](3) NOT NULL,
	[maGaDi] [int] NOT NULL,
	[maGaDen] [int] NOT NULL,
	[macTau] [varchar](20) NULL,
	[ngayGioKhoiHanh] [datetime] NOT NULL,
	[ngayGioDen] [datetime] NOT NULL,
	[trangThai] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maChuyen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ga]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ga](
	[maGa] [int] IDENTITY(1,1) NOT NULL,
	[tenGa] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maGa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [varchar](20) NOT NULL,
	[ngayGioLapHD] [datetime] NULL,
	[maNhanVien] [varchar](11) NOT NULL,
	[maKhachHang] [varchar](20) NOT NULL,
	[soLuong] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [varchar](20) NOT NULL,
	[tenKH] [nvarchar](50) NOT NULL,
	[CCCD] [char](12) NULL,
	[sdt] [char](10) NOT NULL,
	[email] [varchar](100) NULL,
	[ngaySinh] [date] NULL,
	[doiTuong] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMai](
	[maKM] [varchar](15) NOT NULL,
	[ngayApDung] [date] NOT NULL,
	[ngayKetThuc] [date] NOT NULL,
	[doiTuong] [nvarchar](30) NOT NULL,
	[phanTramKM] [float] NULL,
	[daGuiThongBao] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[maKM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiCho]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiCho](
	[maLC] [varchar](10) NOT NULL,
	[tenLC] [nvarchar](50) NOT NULL,
	[giaCho] [float] NULL,
	[moTa] [nvarchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maLC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiToa]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiToa](
	[maLoai] [varchar](10) NOT NULL,
	[tenLoai] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiVe]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiVe](
	[maLoai] [varchar](10) NOT NULL,
	[tenLoai] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [varchar](11) NOT NULL,
	[tenNV] [nvarchar](100) NOT NULL,
	[duongDanAnh] [varchar](355) NULL,
	[gioiTinh] [nvarchar](3) NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[ngayVaoLam] [date] NOT NULL,
	[CCCD] [varchar](12) NOT NULL,
	[sdt] [varchar](12) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[diaChi] [nvarchar](255) NOT NULL,
	[trangThai] [nvarchar](20) NOT NULL,
	[macaLam] [varchar](3) NOT NULL,
	[maTaiKhoan] [varchar](11) NOT NULL,
	[maChucVu] [varchar](2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maTK] [varchar](11) NOT NULL,
	[matKhau] [varchar](60) NOT NULL,
	[trangThai] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tau]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tau](
	[maTau] [char](3) NOT NULL,
	[tenTau] [nvarchar](50) NOT NULL,
	[trangThai] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maTau] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Toa]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Toa](
	[maToa] [varchar](20) NOT NULL,
	[soLuongCho] [int] NULL,
	[maTau] [char](3) NULL,
	[maloaiToa] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maToa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ve]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ve](
	[maVe] [varchar](20) NOT NULL,
	[maHoaDon] [varchar](20) NOT NULL,
	[maLoaiVe] [varchar](10) NOT NULL,
	[ngayGioXuatVe] [datetime] NOT NULL,
	[maChoNgoi] [varchar](20) NOT NULL,
	[maChuyenTau] [varchar](20) NOT NULL,
	[maKhachHang] [varchar](20) NOT NULL,
	[trangThai] [nvarchar](20) NOT NULL,
	[thue] [float] NULL,
	[maKhuyenMai] [varchar](15) NULL,
PRIMARY KEY CLUSTERED 
(
	[maVe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[View_HoaDon_NgayLap]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[View_HoaDon_NgayLap] AS
SELECT 
    hd.maHD,
    hd.maNhanVien,
    hd.maKhachHang,
    hd.soLuong,
    ISNULL(MIN(v.ngayGioXuatVe), hd.ngayGioLapHD) AS ngayLapHD
FROM 
    HoaDon hd
LEFT JOIN 
    Ve v ON hd.maHD = v.maHoaDon
GROUP BY 
    hd.maHD, hd.maNhanVien, hd.maKhachHang, hd.soLuong, hd.ngayGioLapHD;

GO
/****** Object:  View [dbo].[View_VeTongTien]    Script Date: 12/13/2024 2:59:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[View_VeTongTien] AS
SELECT 
      v.maVe,
      v.maHoaDon,
	  v.[maLoaiVe],
      v.ngayGioXuatVe,
	  v.[maChoNgoi],
	  v.[maChuyenTau],
	  v.[maKhachHang],
      v.[trangThai],
      c.maToa,
      lc.tenLC,
      lc.giaCho,
      v.thue,
      km.phanTramKM,
      lc.giaCho + lc.giaCho * v.thue / 100 - (lc.giaCho + lc.giaCho * v.thue / 100) * ISNULL(km.phanTramKM, 0) AS tongTien
FROM [QuanLyBanVeTauHoa].[dbo].[Ve] v
INNER JOIN [QuanLyBanVeTauHoa].[dbo].[ChoNgoi] c ON v.maChoNgoi = c.maCho
INNER JOIN [QuanLyBanVeTauHoa].[dbo].[LoaiCho] lc ON c.maloaiCho = lc.maLC
LEFT JOIN [QuanLyBanVeTauHoa].[dbo].[KhuyenMai] km ON v.maKhuyenMai = km.maKM;
GO
INSERT [dbo].[CaLam] ([maCL], [tenCL], [gioBD], [gioKetThuc]) VALUES (N'CA1', N'Ca làm sáng', CAST(N'07:30:00' AS Time), CAST(N'11:30:00' AS Time))
INSERT [dbo].[CaLam] ([maCL], [tenCL], [gioBD], [gioKetThuc]) VALUES (N'CA2', N'Ca làm chiều', CAST(N'13:00:00' AS Time), CAST(N'17:00:00' AS Time))
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0801', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0802', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0803', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0804', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0805', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0806', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0807', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0808', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0809', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0810', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0811', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0812', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0813', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0814', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0815', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0816', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0817', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0818', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0819', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0820', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0821', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0822', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0823', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0824', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0825', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0826', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0827', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0828', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0829', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEANL0830', N'ANL', N'SEANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0901', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0902', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0903', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0904', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0905', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0906', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0907', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0908', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0909', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0910', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0911', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0912', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0913', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0914', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0915', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0916', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0917', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0918', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0919', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0920', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0921', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0922', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0923', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0924', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0925', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0926', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0927', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0928', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0929', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0930', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0931', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0932', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0933', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0934', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0935', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0936', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0937', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0938', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0939', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEBNL0940', N'BNL', N'SEBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0101', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0102', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0103', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0104', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0105', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0106', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0107', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0108', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0109', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0110', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0111', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0112', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0113', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0114', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0115', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0116', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0117', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0118', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0119', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0120', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0121', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0122', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0123', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0124', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0125', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0126', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0127', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0128', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0129', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0130', N'NC', N'SENC01', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0131', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0132', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0133', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0134', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0135', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0136', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0137', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0138', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0139', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0140', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0141', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0142', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0143', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0144', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0145', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0146', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0147', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0148', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0149', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0150', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0151', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0152', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0153', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0154', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0155', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0156', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0157', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0158', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0159', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0160', N'NC', N'SENC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0201', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0202', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0203', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0204', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0205', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0206', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0207', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0208', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0209', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0210', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0211', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0212', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0213', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0214', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0215', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0216', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0217', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0218', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0219', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0220', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0221', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0222', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0223', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0224', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0225', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0226', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0227', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0228', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0229', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0230', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0231', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0232', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0233', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0234', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0235', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0236', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0237', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0238', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0239', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0240', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0241', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0242', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0243', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0244', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0245', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0246', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0247', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0248', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0249', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0250', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0251', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0252', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0253', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0254', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0255', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0256', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0257', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0258', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0259', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENC0260', N'NC', N'SENC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0301', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0302', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0303', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0304', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0305', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0306', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0307', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0308', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0309', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0310', N'NCL', N'SENCL03', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0311', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0312', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0313', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0314', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0315', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0316', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0317', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0318', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0319', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0320', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0321', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0322', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0323', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0324', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0325', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0326', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0327', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0328', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0329', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0330', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0331', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0332', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0333', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0334', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0335', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0336', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0337', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0338', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0339', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0340', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0341', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0342', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0343', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0344', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0345', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0346', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0347', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0348', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0349', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0350', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0351', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0352', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0353', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0354', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0355', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0356', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0357', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0358', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0359', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0360', N'NCL', N'SENCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0401', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0402', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0403', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0404', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0405', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0406', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0407', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0408', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0409', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0410', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0411', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0412', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0413', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0414', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0415', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0416', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0417', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0418', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0419', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0420', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0421', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0422', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0423', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0424', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0425', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0426', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0427', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0428', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0429', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0430', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0431', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0432', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0433', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0434', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0435', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0436', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0437', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0438', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0439', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0440', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0441', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0442', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0443', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0444', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0445', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0446', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0447', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0448', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0449', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0450', N'NCL', N'SENCL04', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0451', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0452', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0453', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0454', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0455', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0456', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0457', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0458', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0459', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENCL0460', N'NCL', N'SENCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0501', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0502', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0503', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0504', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0505', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0506', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0507', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0508', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0509', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0510', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0511', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0512', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0513', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0514', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0515', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0516', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0517', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0518', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0519', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0520', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0521', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0522', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0523', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0524', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0525', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0526', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0527', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0528', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0529', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0530', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0531', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0532', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0533', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0534', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0535', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0536', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0537', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0538', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0539', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0540', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0541', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0542', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0543', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0544', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0545', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0546', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0547', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0548', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0549', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0550', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0551', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0552', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0553', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0554', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0555', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0556', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0557', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0558', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0559', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0560', N'NM', N'SENM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0601', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0602', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0603', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0604', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0605', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0606', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0607', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0608', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0609', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0610', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0611', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0612', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0613', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0614', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0615', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0616', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0617', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0618', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0619', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0620', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0621', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0622', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0623', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0624', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0625', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0626', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0627', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0628', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0629', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0630', N'NM', N'SENM06', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0631', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0632', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0633', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0634', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0635', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0636', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0637', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0638', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0639', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0640', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0641', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0642', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0643', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0644', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0645', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0646', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0647', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0648', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0649', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0650', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0651', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0652', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0653', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0654', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0655', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0656', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0657', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0658', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0659', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENM0660', N'NM', N'SENM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0701', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0702', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0703', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0704', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0705', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0706', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0707', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0708', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0709', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0710', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0711', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0712', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0713', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0714', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0715', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0716', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0717', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0718', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0719', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0720', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0721', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0722', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0723', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0724', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0725', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0726', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0727', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0728', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0729', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0730', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0731', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0732', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0733', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0734', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0735', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0736', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0737', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0738', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0739', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0740', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0741', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0742', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0743', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0744', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0745', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0746', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0747', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0748', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0749', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0750', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0751', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0752', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0753', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0754', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0755', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0756', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0757', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0758', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0759', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SENML0760', N'NML', N'SENML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1001', N'VIP', N'SEVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1002', N'VIP', N'SEVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1003', N'VIP', N'SEVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1004', N'VIP', N'SEVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1005', N'VIP', N'SEVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1006', N'VIP', N'SEVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1007', N'VIP', N'SEVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1008', N'VIP', N'SEVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1009', N'VIP', N'SEVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SEVIP1010', N'VIP', N'SEVIP10', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0801', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0802', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0803', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0804', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0805', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0806', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0807', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0808', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0809', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0810', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0811', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0812', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0813', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0814', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0815', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0816', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0817', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0818', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0819', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0820', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0821', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0822', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0823', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0824', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0825', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0826', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0827', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0828', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0829', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTANL0830', N'ANL', N'SNTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0901', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0902', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0903', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0904', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0905', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0906', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0907', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0908', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0909', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0910', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0911', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0912', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0913', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0914', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0915', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0916', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0917', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0918', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0919', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0920', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0921', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0922', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0923', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0924', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0925', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0926', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0927', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0928', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0929', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0930', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0931', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0932', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0933', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0934', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0935', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0936', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0937', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0938', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0939', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTBNL0940', N'BNL', N'SNTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0101', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0102', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0103', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0104', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0105', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0106', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0107', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0108', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0109', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0110', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0111', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0112', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0113', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0114', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0115', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0116', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0117', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0118', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0119', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0120', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0121', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0122', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0123', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0124', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0125', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0126', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0127', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0128', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0129', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0130', N'NC', N'SNTNC01', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0131', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0132', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0133', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0134', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0135', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0136', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0137', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0138', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0139', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0140', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0141', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0142', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0143', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0144', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0145', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0146', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0147', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0148', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0149', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0150', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0151', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0152', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0153', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0154', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0155', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0156', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0157', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0158', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0159', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0160', N'NC', N'SNTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0201', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0202', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0203', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0204', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0205', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0206', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0207', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0208', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0209', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0210', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0211', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0212', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0213', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0214', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0215', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0216', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0217', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0218', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0219', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0220', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0221', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0222', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0223', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0224', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0225', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0226', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0227', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0228', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0229', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0230', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0231', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0232', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0233', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0234', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0235', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0236', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0237', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0238', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0239', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0240', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0241', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0242', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0243', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0244', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0245', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0246', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0247', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0248', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0249', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0250', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0251', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0252', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0253', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0254', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0255', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0256', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0257', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0258', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0259', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNC0260', N'NC', N'SNTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0301', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0302', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0303', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0304', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0305', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0306', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0307', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0308', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0309', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0310', N'NCL', N'SNTNCL03', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0311', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0312', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0313', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0314', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0315', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0316', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0317', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0318', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0319', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0320', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0321', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0322', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0323', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0324', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0325', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0326', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0327', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0328', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0329', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0330', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0331', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0332', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0333', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0334', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0335', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0336', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0337', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0338', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0339', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0340', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0341', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0342', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0343', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0344', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0345', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0346', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0347', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0348', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0349', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0350', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0351', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0352', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0353', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0354', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0355', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0356', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0357', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0358', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0359', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0360', N'NCL', N'SNTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0401', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0402', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0403', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0404', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0405', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0406', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0407', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0408', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0409', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0410', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0411', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0412', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0413', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0414', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0415', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0416', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0417', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0418', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0419', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0420', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0421', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0422', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0423', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0424', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0425', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0426', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0427', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0428', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0429', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0430', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0431', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0432', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0433', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0434', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0435', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0436', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0437', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0438', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0439', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0440', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0441', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0442', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0443', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0444', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0445', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0446', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0447', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0448', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0449', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0450', N'NCL', N'SNTNCL04', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0451', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0452', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0453', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0454', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0455', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0456', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0457', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0458', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0459', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNCL0460', N'NCL', N'SNTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0501', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0502', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0503', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0504', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0505', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0506', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0507', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0508', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0509', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0510', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0511', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0512', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0513', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0514', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0515', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0516', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0517', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0518', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0519', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0520', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0521', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0522', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0523', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0524', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0525', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0526', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0527', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0528', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0529', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0530', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0531', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0532', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0533', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0534', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0535', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0536', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0537', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0538', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0539', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0540', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0541', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0542', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0543', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0544', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0545', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0546', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0547', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0548', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0549', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0550', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0551', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0552', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0553', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0554', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0555', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0556', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0557', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0558', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0559', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0560', N'NM', N'SNTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0601', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0602', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0603', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0604', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0605', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0606', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0607', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0608', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0609', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0610', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0611', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0612', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0613', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0614', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0615', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0616', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0617', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0618', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0619', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0620', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0621', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0622', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0623', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0624', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0625', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0626', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0627', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0628', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0629', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0630', N'NM', N'SNTNM06', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0631', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0632', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0633', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0634', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0635', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0636', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0637', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0638', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0639', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0640', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0641', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0642', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0643', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0644', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0645', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0646', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0647', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0648', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0649', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0650', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0651', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0652', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0653', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0654', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0655', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0656', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0657', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0658', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0659', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNM0660', N'NM', N'SNTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0701', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0702', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0703', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0704', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0705', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0706', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0707', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0708', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0709', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0710', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0711', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0712', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0713', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0714', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0715', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0716', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0717', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0718', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0719', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0720', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0721', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0722', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0723', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0724', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0725', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0726', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0727', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0728', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0729', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0730', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0731', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0732', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0733', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0734', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0735', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0736', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0737', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0738', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0739', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0740', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0741', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0742', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0743', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0744', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0745', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0746', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0747', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0748', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0749', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0750', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0751', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0752', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0753', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0754', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0755', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0756', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0757', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0758', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0759', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTNML0760', N'NML', N'SNTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1001', N'VIP', N'SNTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1002', N'VIP', N'SNTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1003', N'VIP', N'SNTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1004', N'VIP', N'SNTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1005', N'VIP', N'SNTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1006', N'VIP', N'SNTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1007', N'VIP', N'SNTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1008', N'VIP', N'SNTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1009', N'VIP', N'SNTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SNTVIP1010', N'VIP', N'SNTVIP10', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0801', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0802', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0803', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0804', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0805', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0806', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0807', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0808', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0809', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0810', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0811', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0812', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0813', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0814', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0815', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0816', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0817', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0818', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0819', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0820', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0821', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0822', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0823', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0824', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0825', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0826', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0827', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0828', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0829', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPANL0830', N'ANL', N'SPANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0901', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0902', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0903', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0904', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0905', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0906', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0907', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0908', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0909', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0910', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0911', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0912', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0913', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0914', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0915', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0916', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0917', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0918', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0919', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0920', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0921', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0922', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0923', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0924', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0925', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0926', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0927', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0928', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0929', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0930', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0931', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0932', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0933', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0934', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0935', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0936', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0937', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0938', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0939', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPBNL0940', N'BNL', N'SPBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0101', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0102', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0103', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0104', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0105', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0106', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0107', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0108', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0109', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0110', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0111', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0112', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0113', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0114', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0115', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0116', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0117', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0118', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0119', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0120', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0121', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0122', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0123', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0124', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0125', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0126', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0127', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0128', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0129', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0130', N'NC', N'SPNC01', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0131', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0132', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0133', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0134', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0135', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0136', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0137', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0138', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0139', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0140', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0141', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0142', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0143', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0144', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0145', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0146', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0147', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0148', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0149', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0150', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0151', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0152', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0153', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0154', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0155', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0156', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0157', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0158', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0159', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0160', N'NC', N'SPNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0201', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0202', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0203', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0204', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0205', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0206', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0207', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0208', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0209', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0210', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0211', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0212', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0213', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0214', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0215', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0216', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0217', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0218', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0219', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0220', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0221', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0222', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0223', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0224', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0225', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0226', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0227', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0228', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0229', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0230', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0231', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0232', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0233', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0234', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0235', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0236', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0237', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0238', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0239', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0240', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0241', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0242', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0243', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0244', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0245', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0246', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0247', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0248', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0249', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0250', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0251', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0252', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0253', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0254', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0255', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0256', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0257', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0258', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0259', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNC0260', N'NC', N'SPNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0301', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0302', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0303', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0304', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0305', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0306', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0307', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0308', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0309', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0310', N'NCL', N'SPNCL03', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0311', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0312', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0313', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0314', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0315', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0316', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0317', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0318', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0319', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0320', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0321', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0322', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0323', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0324', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0325', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0326', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0327', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0328', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0329', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0330', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0331', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0332', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0333', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0334', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0335', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0336', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0337', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0338', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0339', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0340', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0341', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0342', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0343', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0344', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0345', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0346', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0347', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0348', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0349', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0350', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0351', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0352', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0353', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0354', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0355', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0356', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0357', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0358', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0359', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0360', N'NCL', N'SPNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0401', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0402', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0403', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0404', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0405', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0406', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0407', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0408', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0409', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0410', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0411', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0412', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0413', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0414', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0415', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0416', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0417', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0418', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0419', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0420', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0421', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0422', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0423', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0424', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0425', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0426', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0427', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0428', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0429', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0430', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0431', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0432', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0433', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0434', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0435', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0436', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0437', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0438', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0439', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0440', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0441', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0442', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0443', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0444', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0445', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0446', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0447', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0448', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0449', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0450', N'NCL', N'SPNCL04', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0451', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0452', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0453', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0454', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0455', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0456', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0457', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0458', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0459', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNCL0460', N'NCL', N'SPNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0501', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0502', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0503', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0504', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0505', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0506', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0507', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0508', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0509', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0510', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0511', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0512', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0513', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0514', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0515', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0516', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0517', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0518', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0519', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0520', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0521', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0522', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0523', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0524', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0525', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0526', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0527', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0528', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0529', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0530', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0531', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0532', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0533', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0534', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0535', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0536', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0537', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0538', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0539', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0540', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0541', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0542', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0543', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0544', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0545', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0546', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0547', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0548', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0549', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0550', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0551', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0552', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0553', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0554', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0555', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0556', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0557', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0558', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0559', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0560', N'NM', N'SPNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0601', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0602', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0603', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0604', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0605', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0606', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0607', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0608', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0609', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0610', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0611', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0612', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0613', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0614', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0615', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0616', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0617', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0618', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0619', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0620', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0621', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0622', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0623', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0624', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0625', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0626', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0627', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0628', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0629', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0630', N'NM', N'SPNM06', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0631', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0632', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0633', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0634', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0635', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0636', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0637', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0638', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0639', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0640', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0641', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0642', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0643', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0644', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0645', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0646', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0647', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0648', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0649', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0650', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0651', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0652', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0653', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0654', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0655', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0656', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0657', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0658', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0659', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNM0660', N'NM', N'SPNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0701', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0702', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0703', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0704', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0705', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0706', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0707', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0708', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0709', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0710', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0711', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0712', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0713', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0714', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0715', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0716', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0717', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0718', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0719', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0720', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0721', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0722', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0723', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0724', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0725', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0726', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0727', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0728', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0729', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0730', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0731', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0732', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0733', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0734', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0735', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0736', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0737', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0738', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0739', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0740', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0741', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0742', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0743', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0744', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0745', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0746', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0747', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0748', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0749', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0750', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0751', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0752', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0753', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0754', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0755', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0756', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0757', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0758', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0759', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPNML0760', N'NML', N'SPNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0801', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0802', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0803', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0804', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0805', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0806', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0807', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0808', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0809', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0810', N'ANL', N'SPTANL08', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0811', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0812', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0813', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0814', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0815', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0816', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0817', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0818', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0819', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0820', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0821', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0822', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0823', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0824', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0825', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0826', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0827', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0828', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0829', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTANL0830', N'ANL', N'SPTANL08', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0901', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0902', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0903', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0904', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0905', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0906', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0907', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0908', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0909', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0910', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0911', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0912', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0913', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0914', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0915', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0916', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0917', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0918', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0919', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0920', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0921', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0922', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0923', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0924', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0925', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0926', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0927', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0928', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0929', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0930', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0931', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0932', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0933', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0934', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0935', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0936', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0937', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0938', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0939', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTBNL0940', N'BNL', N'SPTBNL09', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0101', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0102', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0103', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0104', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0105', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0106', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0107', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0108', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0109', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0110', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0111', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0112', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0113', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0114', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0115', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0116', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0117', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0118', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0119', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0120', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0121', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0122', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0123', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0124', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0125', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0126', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0127', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0128', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0129', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0130', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0131', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0132', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0133', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0134', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0135', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0136', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0137', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0138', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0139', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0140', N'NC', N'SPTNC01', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0141', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0142', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0143', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0144', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0145', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0146', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0147', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0148', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0149', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0150', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0151', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0152', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0153', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0154', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0155', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0156', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0157', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0158', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0159', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0160', N'NC', N'SPTNC01', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0201', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0202', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0203', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0204', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0205', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0206', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0207', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0208', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0209', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0210', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0211', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0212', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0213', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0214', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0215', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0216', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0217', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0218', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0219', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0220', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0221', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0222', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0223', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0224', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0225', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0226', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0227', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0228', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0229', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0230', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0231', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0232', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0233', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0234', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0235', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0236', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0237', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0238', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0239', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0240', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0241', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0242', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0243', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0244', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0245', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0246', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0247', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0248', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0249', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0250', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0251', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0252', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0253', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0254', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0255', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0256', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0257', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0258', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0259', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNC0260', N'NC', N'SPTNC02', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0301', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0302', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0303', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0304', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0305', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0306', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0307', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0308', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0309', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0310', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0311', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0312', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0313', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0314', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0315', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0316', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0317', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0318', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0319', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0320', N'NCL', N'SPTNCL03', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0321', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0322', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0323', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0324', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0325', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0326', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0327', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0328', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0329', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0330', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0331', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0332', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0333', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0334', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0335', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0336', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0337', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0338', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0339', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0340', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0341', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0342', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0343', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0344', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0345', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0346', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0347', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0348', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0349', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0350', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0351', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0352', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0353', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0354', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0355', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0356', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0357', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0358', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0359', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0360', N'NCL', N'SPTNCL03', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0401', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0402', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0403', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0404', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0405', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0406', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0407', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0408', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0409', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0410', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0411', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0412', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0413', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0414', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0415', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0416', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0417', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0418', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0419', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0420', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0421', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0422', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0423', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0424', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0425', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0426', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0427', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0428', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0429', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0430', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0431', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0432', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0433', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0434', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0435', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0436', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0437', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0438', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0439', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0440', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0441', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0442', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0443', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0444', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0445', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0446', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0447', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0448', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0449', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0450', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0451', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0452', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0453', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0454', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0455', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0456', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0457', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0458', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0459', N'NCL', N'SPTNCL04', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNCL0460', N'NCL', N'SPTNCL04', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0501', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0502', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0503', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0504', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0505', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0506', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0507', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0508', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0509', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0510', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0511', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0512', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0513', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0514', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0515', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0516', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0517', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0518', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0519', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0520', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0521', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0522', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0523', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0524', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0525', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0526', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0527', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0528', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0529', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0530', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0531', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0532', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0533', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0534', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0535', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0536', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0537', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0538', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0539', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0540', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0541', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0542', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0543', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0544', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0545', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0546', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0547', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0548', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0549', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0550', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0551', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0552', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0553', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0554', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0555', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0556', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0557', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0558', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0559', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0560', N'NM', N'SPTNM05', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0601', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0602', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0603', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0604', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0605', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0606', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0607', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0608', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0609', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0610', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0611', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0612', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0613', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0614', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0615', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0616', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0617', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0618', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0619', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0620', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0621', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0622', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0623', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0624', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0625', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0626', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0627', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0628', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0629', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0630', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0631', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0632', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0633', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0634', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0635', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0636', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0637', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0638', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0639', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0640', N'NM', N'SPTNM06', N'Còn trống')
GO
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0641', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0642', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0643', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0644', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0645', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0646', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0647', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0648', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0649', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0650', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0651', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0652', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0653', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0654', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0655', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0656', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0657', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0658', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0659', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNM0660', N'NM', N'SPTNM06', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0701', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0702', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0703', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0704', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0705', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0706', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0707', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0708', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0709', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0710', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0711', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0712', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0713', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0714', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0715', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0716', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0717', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0718', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0719', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0720', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0721', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0722', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0723', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0724', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0725', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0726', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0727', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0728', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0729', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0730', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0731', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0732', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0733', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0734', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0735', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0736', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0737', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0738', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0739', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0740', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0741', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0742', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0743', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0744', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0745', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0746', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0747', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0748', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0749', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0750', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0751', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0752', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0753', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0754', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0755', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0756', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0757', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0758', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0759', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTNML0760', N'NML', N'SPTNML07', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1001', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1002', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1003', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1004', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1005', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1006', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1007', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1008', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1009', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPTVIP1010', N'VIP', N'SPTVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1001', N'VIP', N'SPVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1002', N'VIP', N'SPVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1003', N'VIP', N'SPVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1004', N'VIP', N'SPVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1005', N'VIP', N'SPVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1006', N'VIP', N'SPVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1007', N'VIP', N'SPVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1008', N'VIP', N'SPVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1009', N'VIP', N'SPVIP10', N'Còn trống')
INSERT [dbo].[ChoNgoi] ([maCho], [maloaiCho], [maToa], [trangThai]) VALUES (N'SPVIP1010', N'VIP', N'SPVIP10', N'Còn trống')
GO
INSERT [dbo].[ChucVu] ([maCV], [tenCV]) VALUES (N'NV', N'Nhân viên')
INSERT [dbo].[ChucVu] ([maCV], [tenCV]) VALUES (N'QL', N'Quản lý')
GO
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24102401', N'SE ', 21, 1, N'SE 02', CAST(N'2024-10-24T06:00:00.000' AS DateTime), CAST(N'2024-10-25T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24102402', N'SE ', 21, 1, N'SE 04', CAST(N'2024-10-24T12:00:00.000' AS DateTime), CAST(N'2024-10-25T18:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24102403', N'SE ', 21, 1, N'SE 06', CAST(N'2024-10-24T17:00:00.000' AS DateTime), CAST(N'2024-10-25T21:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112701', N'SE ', 21, 1, N'SE 02', CAST(N'2024-11-27T06:00:00.000' AS DateTime), CAST(N'2024-11-29T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112702', N'SE ', 21, 1, N'SE 04', CAST(N'2024-11-27T12:00:00.000' AS DateTime), CAST(N'2024-11-29T18:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112703', N'SE ', 21, 1, N'SE 06', CAST(N'2024-11-27T17:00:00.000' AS DateTime), CAST(N'2024-11-29T21:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112704', N'SE ', 21, 1, N'SE 08', CAST(N'2024-11-27T21:00:00.000' AS DateTime), CAST(N'2024-11-29T03:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112705', N'SE ', 1, 21, N'SE 03', CAST(N'2024-11-27T06:00:00.000' AS DateTime), CAST(N'2024-11-29T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112706', N'SE ', 1, 21, N'SE 05', CAST(N'2024-11-27T17:00:00.000' AS DateTime), CAST(N'2024-11-29T21:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112801', N'SE ', 21, 1, N'SE 02', CAST(N'2024-11-28T06:00:00.000' AS DateTime), CAST(N'2024-11-29T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112802', N'SE ', 21, 1, N'SE 04', CAST(N'2024-11-28T12:00:00.000' AS DateTime), CAST(N'2024-11-29T18:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112803', N'SE ', 21, 1, N'SE 06', CAST(N'2024-11-28T17:00:00.000' AS DateTime), CAST(N'2024-11-29T21:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112804', N'SE ', 21, 1, N'SE 08', CAST(N'2024-11-28T21:00:00.000' AS DateTime), CAST(N'2024-11-29T03:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112805', N'SE ', 1, 21, N'SE 03', CAST(N'2024-11-28T06:00:00.000' AS DateTime), CAST(N'2024-11-29T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112806', N'SE ', 1, 21, N'SE 05', CAST(N'2024-11-28T17:00:00.000' AS DateTime), CAST(N'2024-11-29T21:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112807', N'SE ', 21, 1, N'SE 10', CAST(N'2024-11-28T06:00:00.000' AS DateTime), CAST(N'2024-11-29T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112808', N'SE ', 21, 1, N'SE 12', CAST(N'2024-11-28T12:00:00.000' AS DateTime), CAST(N'2024-11-29T18:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112809', N'SE ', 21, 1, N'SE 14', CAST(N'2024-11-28T17:00:00.000' AS DateTime), CAST(N'2024-11-29T21:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24112810', N'SE ', 21, 1, N'SE 16', CAST(N'2024-11-28T21:00:00.000' AS DateTime), CAST(N'2024-11-29T03:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24113001', N'SE ', 1, 21, N'SE 03', CAST(N'2024-11-30T06:00:00.000' AS DateTime), CAST(N'2024-12-01T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24113002', N'SE ', 1, 21, N'SE 05', CAST(N'2024-11-30T17:00:00.000' AS DateTime), CAST(N'2024-12-01T21:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24120601', N'SE ', 1, 21, N'SE 03', CAST(N'2024-12-06T21:30:00.000' AS DateTime), CAST(N'2024-12-07T21:30:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24121101', N'SE ', 1, 21, N'SE 03', CAST(N'2024-12-11T21:30:00.000' AS DateTime), CAST(N'2024-12-12T21:30:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24121201', N'SE ', 21, 1, N'SE 02', CAST(N'2024-12-12T12:00:00.000' AS DateTime), CAST(N'2024-12-13T13:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24121301', N'SE ', 21, 1, N'SE 02', CAST(N'2024-12-13T21:00:00.000' AS DateTime), CAST(N'2024-12-15T21:00:00.000' AS DateTime), N'Hoạt động')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24121601', N'SE ', 1, 21, N'SE 03', CAST(N'2024-12-16T21:00:00.000' AS DateTime), CAST(N'2024-12-18T21:00:00.000' AS DateTime), N'Hoạt động')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24121801', N'SE ', 1, 21, N'SE 03', CAST(N'2024-12-18T21:00:00.000' AS DateTime), CAST(N'2024-12-20T03:00:00.000' AS DateTime), N'Hoạt động')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SE24122601', N'SE ', 21, 1, N'SE 02', CAST(N'2024-12-26T06:00:00.000' AS DateTime), CAST(N'2024-12-28T14:00:00.000' AS DateTime), N'Hoạt động')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SNT24112701', N'SNT', 21, 16, N'SNT02', CAST(N'2024-11-27T06:00:00.000' AS DateTime), CAST(N'2024-11-29T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SNT24112702', N'SNT', 21, 16, N'SNT04', CAST(N'2024-11-27T08:00:00.000' AS DateTime), CAST(N'2024-11-29T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SNT24112801', N'SNT', 21, 16, N'SNT02', CAST(N'2024-11-28T06:00:00.000' AS DateTime), CAST(N'2024-11-29T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SNT24112802', N'SNT', 21, 16, N'SNT04', CAST(N'2024-11-28T06:00:00.000' AS DateTime), CAST(N'2024-11-29T14:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SP24112701', N'SP ', 1, 21, N'SP 03', CAST(N'2024-11-27T12:00:00.000' AS DateTime), CAST(N'2024-11-29T18:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SP24112702', N'SP ', 1, 21, N'SP 05', CAST(N'2024-11-27T21:00:00.000' AS DateTime), CAST(N'2024-11-29T03:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SP24112801', N'SP ', 1, 21, N'SP 03', CAST(N'2024-11-28T12:00:00.000' AS DateTime), CAST(N'2024-11-29T18:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SP24112802', N'SP ', 1, 21, N'SP 05', CAST(N'2024-11-28T21:00:00.000' AS DateTime), CAST(N'2024-11-29T03:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SP24113001', N'SP ', 1, 21, N'SP 03', CAST(N'2024-11-30T12:00:00.000' AS DateTime), CAST(N'2024-12-01T18:00:00.000' AS DateTime), N'Đã dừng')
INSERT [dbo].[ChuyenTau] ([maChuyen], [maTau], [maGaDi], [maGaDen], [macTau], [ngayGioKhoiHanh], [ngayGioDen], [trangThai]) VALUES (N'SP24113002', N'SP ', 1, 21, N'SP 05', CAST(N'2024-11-30T21:00:00.000' AS DateTime), CAST(N'2024-12-01T03:00:00.000' AS DateTime), N'Đã dừng')
GO
SET IDENTITY_INSERT [dbo].[Ga] ON 

INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (1, N'Ga Tàu Hà Nội')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (2, N'Ga Tàu Đồng Văn')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (3, N'Ga Tàu Nam Định')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (4, N'Ga Tàu Ninh Bình')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (5, N'Ga Tàu Thanh Hóa')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (6, N'Ga Tàu Vinh')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (7, N'Ga Tàu Yên Trung')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (8, N'Ga Tàu Đồng Hới')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (9, N'Ga Tàu Đông Hà')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (10, N'Ga Tàu Huế')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (11, N'Ga Tàu Đà Nẵng')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (12, N'Ga Tàu Tam Kỳ')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (13, N'Ga Tàu Quảng Ngãi')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (14, N'Ga Tàu Diêu Trì')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (15, N'Ga Tàu Tuy Hòa')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (16, N'Ga Tàu Nha Trang')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (17, N'Ga Tàu Tháp Chàm')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (18, N'Ga Tàu Bình Thuận (Mương Mán)')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (19, N'Ga Tàu Biên Hòa')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (20, N'Ga Tàu Dĩ An')
INSERT [dbo].[Ga] ([maGa], [tenGa]) VALUES (21, N'Ga Tàu Sài Gòn')
SET IDENTITY_INSERT [dbo].[Ga] OFF
GO
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000001', CAST(N'2024-10-23T21:38:50.453' AS DateTime), N'NV230110001', N'KH241023000001', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000002', CAST(N'2024-10-23T21:38:50.480' AS DateTime), N'NV230110001', N'KH241023000002', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000003', CAST(N'2024-10-23T21:38:50.480' AS DateTime), N'NV230110001', N'KH241023000003', 4)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000004', CAST(N'2024-10-23T21:38:50.480' AS DateTime), N'NV230110001', N'KH241023000004', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000005', CAST(N'2024-10-23T21:38:50.483' AS DateTime), N'NV230110001', N'KH241023000005', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000006', CAST(N'2024-10-23T21:38:50.483' AS DateTime), N'NV230110001', N'KH241023000006', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000007', CAST(N'2024-10-23T21:38:50.483' AS DateTime), N'NV230110001', N'KH241023000007', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000008', CAST(N'2024-10-23T21:38:50.483' AS DateTime), N'NV230110001', N'KH241023000008', 4)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000009', CAST(N'2024-10-23T21:38:50.487' AS DateTime), N'NV230110001', N'KH241023000009', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000010', CAST(N'2024-10-23T21:38:50.487' AS DateTime), N'NV230110001', N'KH241023000010', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000011', CAST(N'2024-10-23T21:38:50.487' AS DateTime), N'NV230110001', N'KH241023000011', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000012', CAST(N'2024-10-23T21:38:50.487' AS DateTime), N'NV230110001', N'KH241023000012', 4)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000013', CAST(N'2024-10-23T21:38:50.490' AS DateTime), N'NV230110001', N'KH241023000013', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000014', CAST(N'2024-10-23T21:38:50.490' AS DateTime), N'NV230110001', N'KH241023000014', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000015', CAST(N'2024-10-23T21:38:50.490' AS DateTime), N'NV230110001', N'KH241023000015', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000016', CAST(N'2024-10-23T21:38:50.490' AS DateTime), N'NV230110001', N'KH241023000016', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000017', CAST(N'2024-10-23T21:38:50.490' AS DateTime), N'NV230110001', N'KH241023000017', 4)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000018', CAST(N'2024-10-23T21:38:50.493' AS DateTime), N'NV230110001', N'KH241023000018', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000019', CAST(N'2024-10-23T21:38:50.493' AS DateTime), N'NV230110001', N'KH241023000019', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000020', CAST(N'2024-10-23T21:38:50.493' AS DateTime), N'NV230110001', N'KH241023000020', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000021', CAST(N'2024-10-23T21:38:50.497' AS DateTime), N'NV230110001', N'KH241023000021', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000022', CAST(N'2024-10-23T21:38:50.497' AS DateTime), N'NV230110001', N'KH241023000022', 4)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000023', CAST(N'2024-10-23T21:38:50.497' AS DateTime), N'NV230110001', N'KH241023000023', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000024', CAST(N'2024-10-23T21:38:50.497' AS DateTime), N'NV230110001', N'KH241023000024', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000025', CAST(N'2024-10-23T21:38:50.500' AS DateTime), N'NV230110001', N'KH241023000025', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000026', CAST(N'2024-10-23T21:38:50.500' AS DateTime), N'NV230110001', N'KH241023000026', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000027', CAST(N'2024-10-23T21:38:50.500' AS DateTime), N'NV230110001', N'KH241023000027', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000028', CAST(N'2024-10-23T21:38:50.500' AS DateTime), N'NV230110001', N'KH241023000028', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000029', CAST(N'2024-10-23T21:38:50.500' AS DateTime), N'NV230110001', N'KH241023000029', 4)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000030', CAST(N'2024-10-23T21:38:50.500' AS DateTime), N'NV230110001', N'KH241023000030', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000031', CAST(N'2024-10-23T21:38:50.503' AS DateTime), N'NV230110001', N'KH241023000031', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000032', CAST(N'2024-10-23T21:38:50.503' AS DateTime), N'NV230110001', N'KH241023000032', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000033', CAST(N'2024-10-23T21:38:50.503' AS DateTime), N'NV230110001', N'KH241023000033', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000034', CAST(N'2024-10-23T21:38:50.503' AS DateTime), N'NV230110001', N'KH241023000034', 4)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000035', CAST(N'2024-10-23T21:38:50.503' AS DateTime), N'NV230110001', N'KH241023000035', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000036', CAST(N'2024-10-23T21:38:50.507' AS DateTime), N'NV230110001', N'KH241023000036', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000037', CAST(N'2024-10-23T21:38:50.507' AS DateTime), N'NV230110001', N'KH241023000037', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000038', CAST(N'2024-10-23T21:38:50.507' AS DateTime), N'NV230110001', N'KH241023000038', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000039', CAST(N'2024-10-23T21:38:50.507' AS DateTime), N'NV230110001', N'KH241023000039', 4)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000040', CAST(N'2024-10-23T21:38:50.507' AS DateTime), N'NV230110001', N'KH241023000040', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000041', CAST(N'2024-10-23T21:38:50.510' AS DateTime), N'NV230110001', N'KH241023000041', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000042', CAST(N'2024-10-23T21:38:50.510' AS DateTime), N'NV230110001', N'KH241023000042', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000043', CAST(N'2024-10-23T21:38:50.510' AS DateTime), N'NV230110001', N'KH241023000043', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000044', CAST(N'2024-10-23T21:38:50.510' AS DateTime), N'NV230110001', N'KH241023000044', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000045', CAST(N'2024-10-23T21:38:50.510' AS DateTime), N'NV230110001', N'KH241023000045', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000046', CAST(N'2024-10-23T21:38:50.510' AS DateTime), N'NV230110001', N'KH241023000046', 4)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000047', CAST(N'2024-10-23T21:38:50.513' AS DateTime), N'NV230110001', N'KH241023000047', 3)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000048', CAST(N'2024-10-23T21:38:50.513' AS DateTime), N'NV230110001', N'KH241023000048', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000049', CAST(N'2024-10-23T21:38:50.513' AS DateTime), N'NV230110001', N'KH241023000049', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000050', CAST(N'2024-10-23T21:38:50.513' AS DateTime), N'NV230110001', N'KH241023000050', 5)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000051', CAST(N'2024-10-23T21:38:50.513' AS DateTime), N'NV230110001', N'KH241023000001', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241023000052', CAST(N'2024-10-23T21:38:50.517' AS DateTime), N'NV230110001', N'KH241023000002', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241024000001', CAST(N'2024-10-24T18:53:16.197' AS DateTime), N'NV230110001', N'KH241024000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241024000002', CAST(N'2024-10-24T18:56:19.290' AS DateTime), N'NV230110001', N'KH241024000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241024000003', CAST(N'2024-10-24T19:00:28.533' AS DateTime), N'NV230110001', N'KH241024000003', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241030000001', CAST(N'2024-10-30T23:00:01.820' AS DateTime), N'QL230101001', N'KH241030000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241030000002', CAST(N'2024-10-30T23:03:12.137' AS DateTime), N'QL230101001', N'KH241030000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241205000001', CAST(N'2024-12-05T14:03:47.997' AS DateTime), N'QL230101001', N'KH241205000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241205000002', CAST(N'2024-12-05T14:05:45.950' AS DateTime), N'QL230101001', N'KH241205000003', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241205000003', CAST(N'2024-12-05T14:07:13.617' AS DateTime), N'QL230101001', N'KH241205000006', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241205000004', CAST(N'2024-12-05T14:08:04.040' AS DateTime), N'QL230101001', N'KH241205000008', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241205000005', CAST(N'2024-12-05T14:09:45.130' AS DateTime), N'QL230101001', N'KH241205000009', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241206000001', CAST(N'2024-12-06T08:29:54.963' AS DateTime), N'QL230101001', N'KH241206000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241206000002', CAST(N'2024-12-06T09:52:52.533' AS DateTime), N'QL230101001', N'KH241206000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241206000003', CAST(N'2024-12-06T09:52:56.343' AS DateTime), N'QL230101001', N'KH241206000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241206000004', CAST(N'2024-12-06T09:52:58.413' AS DateTime), N'QL230101001', N'KH241206000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241206000005', CAST(N'2024-12-06T09:53:20.820' AS DateTime), N'QL230101001', N'KH241206000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241206000006', CAST(N'2024-12-06T09:53:32.477' AS DateTime), N'QL230101001', N'KH241206000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241206000007', CAST(N'2024-12-06T09:53:35.613' AS DateTime), N'QL230101001', N'KH241206000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241207000001', CAST(N'2024-12-07T08:09:45.633' AS DateTime), N'QL230101001', N'KH241207000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241207000002', CAST(N'2024-12-07T08:18:32.300' AS DateTime), N'QL230101001', N'KH241207000003', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241207000003', CAST(N'2024-12-07T08:21:40.693' AS DateTime), N'QL230101001', N'KH241207000005', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241207000004', CAST(N'2024-12-07T23:11:22.420' AS DateTime), N'QL230101001', N'KH241207000008', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241207000005', CAST(N'2024-12-07T23:14:19.007' AS DateTime), N'QL230101001', N'KH241207000009', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241208000001', CAST(N'2024-12-08T01:02:41.850' AS DateTime), N'QL230101001', N'KH241208000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241208000002', CAST(N'2024-12-08T10:39:45.117' AS DateTime), N'QL230101001', N'KH241208000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241208000003', CAST(N'2024-12-08T10:41:34.583' AS DateTime), N'QL230101001', N'KH241208000003', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241208000004', CAST(N'2024-12-08T10:42:24.770' AS DateTime), N'QL230101001', N'KH241208000004', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241208000005', CAST(N'2024-12-08T10:43:13.243' AS DateTime), N'QL230101001', N'KH241208000005', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241208000006', CAST(N'2024-12-08T10:43:27.120' AS DateTime), N'QL230101001', N'KH241208000005', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241208000007', CAST(N'2024-12-08T10:43:46.910' AS DateTime), N'QL230101001', N'KH241208000005', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241208000008', CAST(N'2024-12-08T11:10:46.670' AS DateTime), N'QL230101001', N'KH241208000006', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241208000009', CAST(N'2024-12-08T11:11:03.450' AS DateTime), N'QL230101001', N'KH241208000006', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241209000001', CAST(N'2024-12-09T09:17:27.880' AS DateTime), N'NV230110001', N'KH241209000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241209000002', CAST(N'2024-12-09T09:17:48.733' AS DateTime), N'NV230110001', N'KH241209000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241209000003', CAST(N'2024-12-09T10:11:26.507' AS DateTime), N'NV230110001', N'KH241209000003', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241209000004', CAST(N'2024-12-09T10:17:12.730' AS DateTime), N'NV230110001', N'KH241209000004', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241210000001', CAST(N'2024-12-10T07:51:07.120' AS DateTime), N'NV230110001', N'KH241210000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241210000002', CAST(N'2024-12-10T07:51:20.070' AS DateTime), N'NV230110001', N'KH241210000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241210000003', CAST(N'2024-12-10T07:51:57.720' AS DateTime), N'NV230110001', N'KH241210000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241210000004', CAST(N'2024-12-10T07:52:39.617' AS DateTime), N'NV230110001', N'KH241210000002', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241210000005', CAST(N'2024-12-10T15:15:56.373' AS DateTime), N'NV230110001', N'KH241210000003', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241210000006', CAST(N'2024-12-10T15:16:54.163' AS DateTime), N'NV230110001', N'KH241210000003', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241210000007', CAST(N'2024-12-10T15:17:15.077' AS DateTime), N'NV230110001', N'KH241210000004', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241210000008', CAST(N'2024-12-10T15:17:37.190' AS DateTime), N'NV230110001', N'KH241210000004', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241211000001', CAST(N'2024-12-11T21:43:57.657' AS DateTime), N'QL230101001', N'KH241210000003', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241211000002', CAST(N'2024-12-11T21:45:01.047' AS DateTime), N'QL230101001', N'KH241210000004', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241211000003', CAST(N'2024-12-11T21:45:27.323' AS DateTime), N'QL230101001', N'KH241210000005', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241212000001', CAST(N'2024-12-12T01:23:05.087' AS DateTime), N'QL230101001', N'KH241212000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241212000002', CAST(N'2024-12-12T01:23:15.557' AS DateTime), N'QL230101001', N'KH241212000001', 10)
GO
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241212000003', CAST(N'2024-12-12T01:23:33.097' AS DateTime), N'QL230101001', N'KH241212000001', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241212000004', CAST(N'2024-12-12T13:29:30.907' AS DateTime), N'QL230101001', N'KH241210000003', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241212000005', CAST(N'2024-12-12T13:29:58.760' AS DateTime), N'QL230101001', N'KH241210000004', 10)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241212000006', CAST(N'2024-12-12T23:29:36.727' AS DateTime), N'QL230101001', N'KH241212000002', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241213000001', CAST(N'2024-12-13T13:38:02.623' AS DateTime), N'NV230110001', N'KH241210000003', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241213000002', CAST(N'2024-12-13T13:38:13.603' AS DateTime), N'NV230110001', N'KH241210000004', 1)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241213000003', CAST(N'2024-12-13T13:38:53.973' AS DateTime), N'NV230110001', N'KH241212000001', 2)
INSERT [dbo].[HoaDon] ([maHD], [ngayGioLapHD], [maNhanVien], [maKhachHang], [soLuong]) VALUES (N'HD241213000004', CAST(N'2024-12-13T13:40:47.013' AS DateTime), N'NV230110001', N'KH241210000003', 3)
GO
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000001', N'Nguyễn Văn A', N'123456789012', N'0901234567', N'nguyena@gmail.com', CAST(N'1990-01-01' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000002', N'Trần Thị B', N'223456789012', N'0902234567', N'tranb@gmail.com', CAST(N'2005-02-02' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000003', N'Hoàng Văn C', N'323456789012', N'0903234567', N'hoangc@gmail.com', CAST(N'2003-03-03' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000004', N'Phạm Thị D', N'423456789012', N'0904234567', N'phamD@gmail.com', CAST(N'1995-04-04' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000005', N'Lê Văn E', N'523456789012', N'0905234567', N'levane@gmail.com', CAST(N'1980-05-05' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000006', N'Nguyễn Thị F', N'623456789012', N'0906234567', N'nguyenf@gmail.com', CAST(N'2001-06-06' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000007', N'Trần Văn G', N'723456789012', N'0907234567', N'trang@gmail.com', CAST(N'2000-07-07' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000008', N'Hoàng Thị H', N'823456789012', N'0908234567', N'hoangh@gmail.com', CAST(N'1975-08-08' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000009', N'Phạm Văn I', N'923456789012', N'0909234567', N'phami@gmail.com', CAST(N'1985-09-09' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000010', N'Lê Thị J', N'123456789013', N'0900134567', N'lej@gmail.com', CAST(N'1992-10-10' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000011', N'Nguyễn Văn K', N'123456789014', N'0901114567', N'nguyenk@gmail.com', CAST(N'1988-11-11' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000012', N'Trần Thị L', N'223456789014', N'0902114567', N'tranl@gmail.com', CAST(N'2006-12-12' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000013', N'Hoàng Văn M', N'323456789014', N'0903114567', N'hoangm@gmail.com', CAST(N'2004-01-01' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000014', N'Phạm Thị N', N'423456789014', N'0904114567', N'phamN@gmail.com', CAST(N'1994-02-02' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000015', N'Lê Văn O', N'523456789014', N'0905114567', N'levo@gmail.com', CAST(N'1982-03-03' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000016', N'Nguyễn Thị P', N'623456789014', N'0906114567', N'nguyenp@gmail.com', CAST(N'1998-04-04' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000017', N'Trần Văn Q', N'723456789014', N'0907114567', N'tranq@gmail.com', CAST(N'2000-05-05' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000018', N'Hoàng Thị R', N'823456789014', N'0908114567', N'hoangr@gmail.com', CAST(N'1977-06-06' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000019', N'Phạm Văn S', N'923456789014', N'0909114567', N'phams@gmail.com', CAST(N'1986-07-07' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000020', N'Lê Thị T', N'123456789015', N'0900154567', N'let@gmail.com', CAST(N'1993-08-08' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000021', N'Nguyễn Văn U', N'123456789016', N'0901164567', N'nguyenu@gmail.com', CAST(N'1989-09-09' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000022', N'Trần Thị V', N'223456789016', N'0902164567', N'tranv@gmail.com', CAST(N'2007-10-10' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000023', N'Hoàng Văn W', N'323456789016', N'0903164567', N'hoangw@gmail.com', CAST(N'2005-11-11' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000024', N'Phạm Thị X', N'423456789016', N'0904164567', N'phamx@gmail.com', CAST(N'1996-12-12' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000025', N'Lê Văn Y', N'523456789016', N'0905164567', N'ley@gmail.com', CAST(N'1984-01-01' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000026', N'Nguyễn Thị Z', N'623456789016', N'0906164567', N'nguyenz@gmail.com', CAST(N'2002-02-02' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000027', N'Trần Văn AA', N'723456789016', N'0907164567', N'tranaa@gmail.com', CAST(N'2001-03-03' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000028', N'Hoàng Thị BB', N'823456789016', N'0908164567', N'hoangbb@gmail.com', CAST(N'1974-04-04' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000029', N'Phạm Văn CC', N'923456789016', N'0909164567', N'phamcc@gmail.com', CAST(N'1987-05-05' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000030', N'Lê Thị DD', N'123456789017', N'0900174567', N'ledd@gmail.com', CAST(N'1991-06-06' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000031', N'Nguyễn Văn EE', N'123456789018', N'0901184567', N'nguyenee@gmail.com', CAST(N'1987-07-07' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000032', N'Trần Thị FF', N'223456789018', N'0902184567', N'tranff@gmail.com', CAST(N'2006-08-08' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000033', N'Hoàng Văn GG', N'323456789018', N'0903184567', N'hoangg@gmail.com', CAST(N'2004-09-09' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000034', N'Phạm Thị HH', N'423456789018', N'0904184567', N'phamhh@gmail.com', CAST(N'1995-10-10' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000035', N'Lê Văn II', N'523456789018', N'0905184567', N'lei@gmail.com', CAST(N'1981-11-11' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000036', N'Nguyễn Thị JJ', N'623456789018', N'0906184567', N'nguyenjj@gmail.com', CAST(N'2000-12-12' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000037', N'Trần Văn KK', N'723456789018', N'0907184567', N'trankk@gmail.com', CAST(N'2001-01-01' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000038', N'Hoàng Thị LL', N'823456789018', N'0908184567', N'hoangll@gmail.com', CAST(N'1973-02-02' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000039', N'Phạm Văn MM', N'923456789018', N'0909184567', N'phammu@gmail.com', CAST(N'1988-03-03' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000040', N'Lê Thị NN', N'123456789019', N'0900194567', N'lenn@gmail.com', CAST(N'1992-04-04' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000041', N'Nguyễn Văn OO', N'123456789020', N'0901204567', N'nguyeno@gmail.com', CAST(N'1989-05-05' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000042', N'Trần Thị PP', N'223456789020', N'0902204567', N'tranpp@gmail.com', CAST(N'2006-06-06' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000043', N'Hoàng Văn QQ', N'323456789020', N'0903204567', N'hoangq@gmail.com', CAST(N'2004-07-07' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000044', N'Phạm Thị RR', N'423456789020', N'0904204567', N'phamrr@gmail.com', CAST(N'1996-08-08' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000045', N'Lê Văn SS', N'523456789020', N'0905204567', N'less@gmail.com', CAST(N'1983-09-09' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000046', N'Nguyễn Thị TT', N'623456789020', N'0906204567', N'nguyentt@gmail.com', CAST(N'1999-10-10' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000047', N'Trần Văn UU', N'723456789020', N'0907204567', N'tranuu@gmail.com', CAST(N'2001-11-11' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000048', N'Hoàng Thị VV', N'823456789020', N'0908204567', N'hoangvv@gmail.com', CAST(N'1976-12-12' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000049', N'Phạm Văn WW', N'923456789020', N'0909204567', N'phamww@gmail.com', CAST(N'1986-01-01' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000050', N'Lê Thị XX', N'123456789021', N'0900214567', N'lexx@gmail.com', CAST(N'1990-02-02' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000051', N'Nguyễn Văn YY', N'123456789022', N'0901224567', N'nguyenyy@gmail.com', CAST(N'1988-03-03' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000052', N'Trần Thị ZZ', N'223456789022', N'0902224567', N'tranzz@gmail.com', CAST(N'2007-04-04' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000053', N'Hoàng Văn AAA', N'323456789022', N'0903224567', N'hoangaaa@gmail.com', CAST(N'2005-05-05' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000054', N'Phạm Thị BBB', N'423456789022', N'0904224567', N'phambbb@gmail.com', CAST(N'1997-06-06' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000055', N'Lê Văn CCC', N'523456789022', N'0905224567', N'leccc@gmail.com', CAST(N'1982-07-07' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000056', N'Nguyễn Thị DDD', N'623456789022', N'0906224567', N'nguyenddd@gmail.com', CAST(N'1993-08-08' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000057', N'Trần Văn EEE', N'723456789022', N'0907224567', N'tranee@gmail.com', CAST(N'2001-09-09' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000058', N'Hoàng Thị FFF', N'823456789022', N'0908224567', N'hoangfff@gmail.com', CAST(N'1974-10-10' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000059', N'Phạm Văn GGG', N'923456789022', N'0909224567', N'phamggg@gmail.com', CAST(N'1985-11-11' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000060', N'Lê Thị HHH', N'123456789023', N'0900234567', N'leh@gmail.com', CAST(N'1991-12-12' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000061', N'Nguyễn Văn III', N'123456789024', N'0901244567', N'nguyeni@gmail.com', CAST(N'1989-01-01' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000062', N'Trần Thị JJJ', N'223456789024', N'0902244567', N'tranj@gmail.com', CAST(N'2007-02-02' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000063', N'Hoàng Văn KKK', N'323456789024', N'0903244567', N'hoangkk@gmail.com', CAST(N'2004-03-03' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000064', N'Phạm Thị LLL', N'423456789024', N'0904244567', N'phaml@gmail.com', CAST(N'1995-04-04' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000065', N'Lê Văn MMM', N'523456789024', N'0905244567', N'lem@gmail.com', CAST(N'1980-05-05' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000066', N'Nguyễn Thị NNN', N'623456789024', N'0906244567', N'nguyenn@gmail.com', CAST(N'1998-06-06' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000067', N'Trần Văn OOO', N'723456789024', N'0907244567', N'tranoo@gmail.com', CAST(N'2000-07-07' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000068', N'Hoàng Thị PPP', N'823456789024', N'0908244567', N'hoangp@gmail.com', CAST(N'1977-08-08' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000069', N'Phạm Văn QQQ', N'923456789024', N'0909244567', N'phamqq@gmail.com', CAST(N'1986-09-09' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000070', N'Lê Thị RRR', N'123456789025', N'0900254567', N'ler@gmail.com', CAST(N'1993-10-10' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000071', N'Nguyễn Văn SSS', N'123456789026', N'0901264567', N'nguyens@gmail.com', CAST(N'1988-11-11' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000072', N'Trần Thị TTT', N'223456789026', N'0902264567', N'trant@gmail.com', CAST(N'2006-12-12' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000073', N'Hoàng Văn UUU', N'323456789026', N'0903264567', N'hoangu@gmail.com', CAST(N'2005-01-01' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000074', N'Phạm Thị VVV', N'423456789026', N'0904264567', N'phamv@gmail.com', CAST(N'1996-02-02' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000075', N'Lê Văn WWW', N'523456789026', N'0905264567', N'lew@gmail.com', CAST(N'1983-03-03' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000076', N'Nguyễn Thị XXX', N'623456789026', N'0906264567', N'nguyenx@gmail.com', CAST(N'1999-04-04' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000077', N'Trần Văn YYY', N'723456789026', N'0907264567', N'trany@gmail.com', CAST(N'2001-05-05' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000078', N'Hoàng Thị ZZZ', N'823456789026', N'0908264567', N'hoangz@gmail.com', CAST(N'1976-06-06' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000079', N'Phạm Văn AAAA', N'923456789026', N'0909264567', N'phama@gmail.com', CAST(N'1986-07-07' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000080', N'Lê Thị BBBB', N'123456789027', N'0900274567', N'lebb@gmail.com', CAST(N'1991-08-08' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000081', N'Nguyễn Văn CCCC', N'123456789028', N'0901284567', N'nguyenc@gmail.com', CAST(N'1989-09-09' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000082', N'Trần Thị DDDD', N'223456789028', N'0902284567', N'trand@gmail.com', CAST(N'2007-10-10' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000083', N'Hoàng Văn EEEE', N'323456789028', N'0903284567', N'hoange@gmail.com', CAST(N'2004-11-11' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000084', N'Phạm Thị FFFF', N'423456789028', N'0904284567', N'phamf@gmail.com', CAST(N'1995-12-12' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000085', N'Lê Văn GGGG', N'523456789028', N'0905284567', N'leg@gmail.com', CAST(N'1982-01-01' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000086', N'Nguyễn Thị HHHH', N'623456789028', N'0906284567', N'nguyenh@gmail.com', CAST(N'1993-02-02' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000087', N'Trần Văn IIII', N'723456789028', N'0907284567', N'trani@gmail.com', CAST(N'2001-03-03' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000088', N'Hoàng Thị JJJJ', N'823456789028', N'0908284567', N'hoangj@gmail.com', CAST(N'1974-04-04' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000089', N'Phạm Văn KKKK', N'923456789028', N'0909284567', N'phamk@gmail.com', CAST(N'1986-05-05' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000090', N'Lê Thị LLLL', N'123456789029', N'0900294567', N'lell@gmail.com', CAST(N'1990-06-06' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000091', N'Nguyễn Văn MMMM', N'123456789030', N'0901304567', N'nguyenm@gmail.com', CAST(N'1988-07-07' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000092', N'Trần Thị NNNN', N'223456789030', N'0902304567', N'trann@gmail.com', CAST(N'2006-08-08' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000093', N'Hoàng Văn OOOO', N'323456789030', N'0903304567', N'hoango@gmail.com', CAST(N'2004-09-09' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000094', N'Phạm Thị PPPP', N'423456789030', N'0904304567', N'phamp@gmail.com', CAST(N'1995-10-10' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000095', N'Lê Văn QQQQ', N'523456789030', N'0905304567', N'leq@gmail.com', CAST(N'1983-11-11' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000096', N'Nguyễn Thị RRRR', N'623456789030', N'0906304567', N'nguyenr@gmail.com', CAST(N'1999-12-12' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000097', N'Trần Văn SSSS', N'723456789030', N'0907304567', N'trans@gmail.com', CAST(N'2001-01-01' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000098', N'Hoàng Thị TTTT', N'823456789030', N'0908304567', N'hoangt@gmail.com', CAST(N'1976-02-02' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000099', N'Phạm Văn UUUU', N'923456789030', N'0909304567', N'phamu@gmail.com', CAST(N'1986-03-03' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000100', N'Lê Thị VVVV', N'123456789031', N'0900314567', N'levv@gmail.com', CAST(N'1991-04-04' AS Date), N'Người lớn')
GO
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000101', N'Nguyễn Văn WWWW', N'123456789032', N'0901324567', N'nguyenw@gmail.com', CAST(N'1988-05-05' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000102', N'Trần Thị XXXX', N'223456789032', N'0902324567', N'tranx@gmail.com', CAST(N'2007-06-06' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000103', N'Hoàng Văn YYYY', N'323456789032', N'0903324567', N'hoangy@gmail.com', CAST(N'2004-07-07' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000104', N'Phạm Thị ZZZZ', N'423456789032', N'0904324567', N'phamz@gmail.com', CAST(N'1995-08-08' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000105', N'Lê Văn AAAAA', N'523456789032', N'0905324567', N'leaaaa@gmail.com', CAST(N'1983-09-09' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000106', N'Nguyễn Thị BBBB', N'623456789032', N'0906324567', N'nguyenb@gmail.com', CAST(N'1999-10-10' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000107', N'Trần Văn CCCCC', N'723456789032', N'0907324567', N'tranc@gmail.com', CAST(N'2001-11-11' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000108', N'Hoàng Thị DDDDD', N'823456789032', N'0908324567', N'hoangd@gmail.com', CAST(N'1976-12-12' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000109', N'Phạm Văn EEEEE', N'923456789032', N'0909324567', N'phame@gmail.com', CAST(N'1986-01-01' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241023000110', N'Lê Thị FFFFF', N'123456789033', N'0900334567', N'lef@gmail.com', CAST(N'1991-02-02' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241024000001', N'Le Nguyen Phi Truong', N'828364765812', N'0977364587', N'dsfsad@gmail.com', CAST(N'2004-03-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241024000002', N'Le NGuyen pHi Trong', N'837484685732', N'0974382347', N'sjdfkfhk@gmail.com', CAST(N'2004-03-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241024000003', N'Le Truong', N'837485763421', N'0937485673', N'jsdhf@gmail.com', CAST(N'2003-12-22' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241030000001', N'LE Nguyen Phi Truong', N'231332123123', N'0987234564', N'dsasad@gmail.com', CAST(N'2003-10-21' AS Date), N'Thương binh')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241030000002', N'LE Nguyen Phi Truon', N'312234212345', N'0321232342', N'dsasdad@gmail.com', CAST(N'2003-03-21' AS Date), N'Thương binh')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241030000003', N'Nguyễn van test1', NULL, N'0901234507', N'nguyenat@gmail.com', CAST(N'1990-01-01' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000001', N'Le Nguyen Ohi Truong', N'283453134543', N'0934455321', N'phitruong@gmail.com', CAST(N'2004-03-21' AS Date), N'Học sinh, sinh viên')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000002', N'Le Nguyen Thanh Phu', N'283453134542', N'0934455324', N'phitruong1@gmail.com', CAST(N'2004-03-22' AS Date), N'Thương binh')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000003', N'Phat Chau Dat', N'348274837172', N'0374425741', N'2asd@gmail.com', CAST(N'2003-12-21' AS Date), N'Người hoạt động cách mạng')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000004', N'Phat Chau Ngan', N'348274837173', N'0375425741', N'2avd@gmail.com', CAST(N'2002-12-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000005', N'Phat Chau Nhyt', N'348274837543', N'0375425746', N'2afvd@gmail.com', CAST(N'2002-12-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000006', N'Ha Chau Vinh Phat', N'948573845921', N'0983748583', N'jlasd@gmail.com', CAST(N'2001-05-26' AS Date), N'Tất cả')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000007', N'Nhat Kim Anh', N'284927384921', N'0837485627', N'ffhjk@gmail.com', CAST(N'2004-03-24' AS Date), N'Trẻ em')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000008', N'Nhut Hai', N'387162738473', N'0738475837', N'aldj@gmail.com', CAST(N'2004-03-21' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000009', N'Le Nguc Anh', N'201938457312', N'0983748234', N'halsa@gmail.com', CAST(N'2004-08-09' AS Date), N'Thương binh')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241205000010', N'Ngoc Thi Anh', N'293049375823', N'0983457638', N'jlajd@gmail.com', CAST(N'2004-03-20' AS Date), N'Người cao tuổi')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241206000001', N'dasdad', N'234563234565', N'0987656787', N'fsdsad@gmail.com', CAST(N'2004-03-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241206000002', N'sadasdsad', N'234567897523', N'0945678456', N'dsadas@gmail.com', CAST(N'2004-09-23' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241206000003', N'sadasdsa2', N'234567897526', N'0945678452', N'dsafddas@gmail.com', CAST(N'2004-09-23' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241206000004', N'sadasdsa2', N'234578237523', N'0545678452', N'saddas@gmail.com', CAST(N'2004-09-23' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000001', N'LeNguyenPhiTruong', N'348458374212', N'0983243253', N'sajdlksaj@gmail.com', CAST(N'2004-03-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000002', N'LeNhatAnh', N'293847583721', N'0936574264', N'2934@gmail.com', CAST(N'2001-09-24' AS Date), N'Người hoạt động cách mạng')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000003', N'hghgjkhjk', N'832716273827', N'0972637462', N'djkf@gmail.com', CAST(N'2009-04-30' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000004', N'hghgjkhjk', N'832716273828', N'0972637466', N'djkkkf@gmail.com', CAST(N'2009-04-30' AS Date), N'Người hoạt động cách mạng')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000005', N'Le Nguyen Phi Truong', N'832716273856', N'0938273432', N'jads@gmail.com', CAST(N'2001-06-23' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000006', N'adjkashkjdhsakj', N'832776273827', N'0327162738', N'dsadsa@gmail.com', CAST(N'2003-09-08' AS Date), N'Người hoạt động cách mạng')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000007', N'ytutuyt', N'732716273827', N'0327162373', N'sdfdslfjlkl@gmail.com', CAST(N'2003-09-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000008', N'gretret', N'387253648272', N'0976257492', N'2hasd@gmail.com', CAST(N'2003-02-09' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000009', N'Trung Thanh Dao', N'092837483722', N'0928374838', N'sadasd@gmail.com', CAST(N'2003-03-09' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000010', N'Mai Nhat Thanh', N'092837483721', N'0928327483', N'hgrwe@gmail.com', CAST(N'2007-04-09' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241207000011', N'Trung Chi Thanh', N'082837483721', N'0828374837', N'tury@gmail.com', CAST(N'1997-08-09' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241208000001', N'Doan Di Bang', N'092738264723', N'0927382647', N'sadsd@gmail.com', CAST(N'2001-09-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241208000002', N'Nguyen Hu Sang', N'929403942854', N'0929403942', N'9294039428544@gmail.com', CAST(N'2001-08-09' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241208000003', N'Tran Nhon Loc', N'923424593234', N'0923424593', N'923424593234@gmail.com', CAST(N'2001-09-23' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241208000004', N'Loc Thanh Nien', N'923424593232', N'0923245939', N'0923245939@gmail.com', CAST(N'2000-10-09' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241208000005', N'Tran van manh', N'092183923267', N'0921839232', N'f0921839232@gmail.com', CAST(N'2000-03-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241208000006', N'Truong Quoc Tuan', N'039453456321', N'0394534563', N'039453456321@gmail.com', CAST(N'2000-09-24' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241209000001', N'Quach Huu Phuoc', N'093844534321', N'0938445343', N'093844534321@gmail.com', CAST(N'2000-10-10' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241209000002', N'Truong Quang Huu', N'093844534320', N'0938445345', N'09384453432@gmail.com', CAST(N'1999-01-01' AS Date), N'Người khuyết tật')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241209000003', N'Hong Ha Nhi', N'938475687323', N'0938475687', N'938475687323@gmail.com', CAST(N'2001-03-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241209000004', N'Ho Tai Tan', N'982839483423', N'0828394834', N'982839483423@gmail.com', CAST(N'2000-12-26' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241210000001', N'Tran Nhut Anh', N'384532463424', N'0982436587', N'asdsa@gmail.com', CAST(N'2005-04-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241210000002', N'Ngo Thua An', N'927485472342', N'0927485470', N'dasjk@gmail.com', CAST(N'1999-10-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241210000003', N'Le Nguyen Phi Truong', N'329375849563', N'0374425748', N'fjfksdljfs@gmail.com', CAST(N'2000-02-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241210000004', N'Nguyen Tan Loc', N'283563743854', N'0374425747', N'jkljdaslkjl@gmail.com', CAST(N'2000-02-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241210000005', N'Tan Quoc Tuan', N'283456784532', N'0374425746', N'dsahkj@gmail.com', CAST(N'2001-09-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241212000001', N'Le Trong Truong', N'273647263748', N'0704500355', N'dsah@gmail.com', CAST(N'2003-02-21' AS Date), N'Người lớn')
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [CCCD], [sdt], [email], [ngaySinh], [doiTuong]) VALUES (N'KH241212000002', N'Le Nguyen Phi Nhung', N'329375849562', N'0374425743', N'phitruong20192020@gmail.com', CAST(N'2000-02-21' AS Date), N'Học sinh, sinh viên')
GO
INSERT [dbo].[KhuyenMai] ([maKM], [ngayApDung], [ngayKetThuc], [doiTuong], [phanTramKM], [daGuiThongBao]) VALUES (N'KM241025001', CAST(N'2024-10-25' AS Date), CAST(N'2024-12-30' AS Date), N'Người hoạt động cách mạng', 0.9, NULL)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayApDung], [ngayKetThuc], [doiTuong], [phanTramKM], [daGuiThongBao]) VALUES (N'KM241025002', CAST(N'2024-10-25' AS Date), CAST(N'2024-12-30' AS Date), N'Thương binh', 0.3, NULL)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayApDung], [ngayKetThuc], [doiTuong], [phanTramKM], [daGuiThongBao]) VALUES (N'KM241025003', CAST(N'2024-10-25' AS Date), CAST(N'2024-12-30' AS Date), N'Người khuyết tật', 0.25, NULL)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayApDung], [ngayKetThuc], [doiTuong], [phanTramKM], [daGuiThongBao]) VALUES (N'KM241025004', CAST(N'2024-10-25' AS Date), CAST(N'2024-12-30' AS Date), N'Trẻ em', 0.05, NULL)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayApDung], [ngayKetThuc], [doiTuong], [phanTramKM], [daGuiThongBao]) VALUES (N'KM241025005', CAST(N'2024-10-25' AS Date), CAST(N'2024-12-30' AS Date), N'Người cao tuổi', 0.15, NULL)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayApDung], [ngayKetThuc], [doiTuong], [phanTramKM], [daGuiThongBao]) VALUES (N'KM241025006', CAST(N'2024-10-25' AS Date), CAST(N'2024-12-30' AS Date), N'Học sinh, sinh viên', 0.1, NULL)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayApDung], [ngayKetThuc], [doiTuong], [phanTramKM], [daGuiThongBao]) VALUES (N'KM241025007', CAST(N'2024-10-25' AS Date), CAST(N'2024-12-30' AS Date), N'Tất cả', 0.1, NULL)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayApDung], [ngayKetThuc], [doiTuong], [phanTramKM], [daGuiThongBao]) VALUES (N'KM241025008', CAST(N'2024-10-25' AS Date), CAST(N'2024-12-30' AS Date), N'Người lớn', 0, NULL)
GO
INSERT [dbo].[LoaiCho] ([maLC], [tenLC], [giaCho], [moTa]) VALUES (N'ANL', N'Khoang 4 giường', 250000, N'Giường nằm khoang 4 người')
INSERT [dbo].[LoaiCho] ([maLC], [tenLC], [giaCho], [moTa]) VALUES (N'BNL', N'Khoang 6 giường', 220000, N'Giường nằm khoang 6 người')
INSERT [dbo].[LoaiCho] ([maLC], [tenLC], [giaCho], [moTa]) VALUES (N'NC', N'Ghế cứng', 100000, N'Ghế ngồi cứng không điều hòa')
INSERT [dbo].[LoaiCho] ([maLC], [tenLC], [giaCho], [moTa]) VALUES (N'NCL', N'Ghế cứng điều hòa', 120000, N'Ghế ngồi cứng có điều hòa')
INSERT [dbo].[LoaiCho] ([maLC], [tenLC], [giaCho], [moTa]) VALUES (N'NM', N'Ghế mềm', 150000, N'Ghế ngồi mềm không điều hòa')
INSERT [dbo].[LoaiCho] ([maLC], [tenLC], [giaCho], [moTa]) VALUES (N'NML', N'Ghế mềm điều hòa', 170000, N'Ghế ngồi mềm có điều hòa')
INSERT [dbo].[LoaiCho] ([maLC], [tenLC], [giaCho], [moTa]) VALUES (N'VIP', N'Khoang VIP', 500000, N'Khoang 2 giường nằm VIP')
GO
INSERT [dbo].[LoaiToa] ([maLoai], [tenLoai]) VALUES (N'ANL', N'Giường nằm khoang 4')
INSERT [dbo].[LoaiToa] ([maLoai], [tenLoai]) VALUES (N'BNL', N'Giường nằm khoang 6')
INSERT [dbo].[LoaiToa] ([maLoai], [tenLoai]) VALUES (N'NC', N'Ghế ngồi cứng thường')
INSERT [dbo].[LoaiToa] ([maLoai], [tenLoai]) VALUES (N'NCL', N'Ghế ngồi cứng có điều hòa')
INSERT [dbo].[LoaiToa] ([maLoai], [tenLoai]) VALUES (N'NM', N'Ghế ngồi mềm thường')
INSERT [dbo].[LoaiToa] ([maLoai], [tenLoai]) VALUES (N'NML', N'Ghế ngồi mềm điều hòa')
INSERT [dbo].[LoaiToa] ([maLoai], [tenLoai]) VALUES (N'VIP', N'Giường nằm khoang 2 VIP')
GO
INSERT [dbo].[LoaiVe] ([maLoai], [tenLoai]) VALUES (N'LV1', N'Vé 1 chiều')
INSERT [dbo].[LoaiVe] ([maLoai], [tenLoai]) VALUES (N'LV2', N'Vé khứ hồi')
GO
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [duongDanAnh], [gioiTinh], [ngaySinh], [ngayVaoLam], [CCCD], [sdt], [email], [diaChi], [trangThai], [macaLam], [maTaiKhoan], [maChucVu]) VALUES (N'NV230110001', N'Hồ Quốc Huy', N'images/employees/nv-2.jpg', N'Nữ', CAST(N'1995-05-10' AS Date), CAST(N'2023-01-10' AS Date), N'234567890123', N'0987654321', N'tranthib@example.com', N'Hồ Chí Minh', N'Làm việc', N'CA1', N'NV230110001', N'NV')
INSERT [dbo].[NhanVien] ([maNV], [tenNV], [duongDanAnh], [gioiTinh], [ngaySinh], [ngayVaoLam], [CCCD], [sdt], [email], [diaChi], [trangThai], [macaLam], [maTaiKhoan], [maChucVu]) VALUES (N'QL230101001', N'Lê Nguyễn Phi Trường', N'images/employees/nv-1.jpg', N'Nam', CAST(N'1990-01-01' AS Date), CAST(N'2023-01-01' AS Date), N'123456789012', N'0912345678', N'nguyenvana@example.com', N'Hà Nội', N'Làm việc', N'CA1', N'QL230101001', N'QL')
GO
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau], [trangThai]) VALUES (N'NV230110001', N'$2a$10$hx.v7Xiy7I8Rpql8ONmMF.WZY3d6pfQmfpp2EgeXJajNJdUa9KVSa', N'Kích hoạt')
INSERT [dbo].[TaiKhoan] ([maTK], [matKhau], [trangThai]) VALUES (N'QL230101001', N'$2a$10$hx.v7Xiy7I8Rpql8ONmMF.WZY3d6pfQmfpp2EgeXJajNJdUa9KVSa', N'Kích hoạt')
GO
INSERT [dbo].[Tau] ([maTau], [tenTau], [trangThai]) VALUES (N'SE ', N'Tàu chạy tuyến Bắc - Nam', N'Hoạt động')
INSERT [dbo].[Tau] ([maTau], [tenTau], [trangThai]) VALUES (N'SNT', N'Tàu chạy tuyến Sài Gòn - Nha Trang', N'Hoạt động')
INSERT [dbo].[Tau] ([maTau], [tenTau], [trangThai]) VALUES (N'SP ', N'Tàu cao tốc', N'Đang chạy')
INSERT [dbo].[Tau] ([maTau], [tenTau], [trangThai]) VALUES (N'SPT', N'Tàu Phan Thiết - Sài Gòn', N'Đã dừng')
GO
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SEANL08', 30, N'SE ', N'ANL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SEBNL09', 40, N'SE ', N'BNL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SENC01', 60, N'SE ', N'NC')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SENC02', 60, N'SE ', N'NC')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SENCL03', 60, N'SE ', N'NCL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SENCL04', 60, N'SE ', N'NCL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SENM05', 60, N'SE ', N'NM')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SENM06', 60, N'SE ', N'NM')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SENML07', 60, N'SE ', N'NML')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SEVIP10', 10, N'SE ', N'VIP')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTANL08', 30, N'SNT', N'ANL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTBNL09', 40, N'SNT', N'BNL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTNC01', 60, N'SNT', N'NC')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTNC02', 60, N'SNT', N'NC')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTNCL03', 60, N'SNT', N'NCL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTNCL04', 60, N'SNT', N'NCL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTNM05', 60, N'SNT', N'NM')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTNM06', 60, N'SNT', N'NM')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTNML07', 60, N'SNT', N'NML')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SNTVIP10', 10, N'SNT', N'VIP')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPANL08', 30, N'SP ', N'ANL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPBNL09', 40, N'SP ', N'BNL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPNC01', 60, N'SP ', N'NC')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPNC02', 60, N'SP ', N'NC')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPNCL03', 60, N'SP ', N'NCL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPNCL04', 60, N'SP ', N'NCL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPNM05', 60, N'SP ', N'NM')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPNM06', 60, N'SP ', N'NM')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPNML07', 60, N'SP ', N'NML')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTANL08', 30, N'SPT', N'ANL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTBNL09', 40, N'SPT', N'BNL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTNC01', 60, N'SPT', N'NC')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTNC02', 60, N'SPT', N'NC')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTNCL03', 60, N'SPT', N'NCL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTNCL04', 60, N'SPT', N'NCL')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTNM05', 60, N'SPT', N'NM')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTNM06', 60, N'SPT', N'NM')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTNML07', 60, N'SPT', N'NML')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPTVIP10', 10, N'SPT', N'VIP')
INSERT [dbo].[Toa] ([maToa], [soLuongCho], [maTau], [maloaiToa]) VALUES (N'SPVIP10', 10, N'SP ', N'VIP')
GO
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000001', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0801', N'SE24112701', N'KH241023000003', N'Đã đổi', 0.1, N'KM241025003')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000002', N'HD241023000002', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0802', N'SE24112701', N'KH241023000004', N'Đã bán', 0.1, N'KM241025004')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000003', N'HD241023000003', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0803', N'SE24112701', N'KH241023000005', N'Đã bán', 0.1, N'KM241025005')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000004', N'HD241023000004', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0804', N'SE24112701', N'KH241023000005', N'Đã bán', 0.1, N'KM241025005')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000005', N'HD241023000005', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0815', N'SE24112701', N'KH241023000006', N'Đã bán', 0.1, N'KM241025006')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000006', N'HD241023000003', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0806', N'SE24112701', N'KH241023000003', N'Đã đổi', 0.1, N'KM241025003')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000007', N'HD241023000004', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0807', N'SE24112701', N'KH241023000004', N'Đã bán', 0.1, N'KM241025004')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000008', N'HD241023000005', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0808', N'SE24112701', N'KH241023000005', N'Đã bán', 0.1, N'KM241025005')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000009', N'HD241023000005', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0809', N'SE24112701', N'KH241023000005', N'Đã bán', 0.1, N'KM241025005')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000010', N'HD241023000006', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0810', N'SE24112701', N'KH241023000006', N'Đã bán', 0.1, N'KM241025006')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000011', N'HD241023000007', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0811', N'SE24112701', N'KH241023000007', N'Vé được trả', 0.1, N'KM241025007')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000012', N'HD241023000008', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0812', N'SE24112701', N'KH241023000008', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000013', N'HD241023000009', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0813', N'SE24112701', N'KH241023000009', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000014', N'HD241023000010', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0814', N'SE24112701', N'KH241023000010', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000015', N'HD241023000011', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0815', N'SE24112701', N'KH241023000011', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000016', N'HD241023000012', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0816', N'SE24112701', N'KH241023000012', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000017', N'HD241023000012', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0817', N'SE24112701', N'KH241023000012', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000018', N'HD241023000013', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0818', N'SE24112701', N'KH241023000013', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000019', N'HD241023000013', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0819', N'SE24112701', N'KH241023000013', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000020', N'HD241023000013', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0820', N'SE24112701', N'KH241023000013', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000021', N'HD241023000014', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0821', N'SE24112701', N'KH241023000014', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000022', N'HD241023000015', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0822', N'SE24112701', N'KH241023000015', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000023', N'HD241023000015', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0823', N'SE24112701', N'KH241023000015', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000024', N'HD241023000016', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0824', N'SE24112701', N'KH241023000016', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000025', N'HD241023000017', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0825', N'SE24112701', N'KH241023000017', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000026', N'HD241023000018', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0826', N'SE24112701', N'KH241023000018', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000027', N'HD241023000019', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0827', N'SE24112701', N'KH241023000019', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000028', N'HD241023000020', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0828', N'SE24112701', N'KH241023000020', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000029', N'HD241023000021', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0829', N'SE24112701', N'KH241023000021', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000030', N'HD241023000022', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SEANL0830', N'SE24112701', N'KH241023000022', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000031', N'HD241023000023', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0101', N'SE24112702', N'KH241023000023', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000032', N'HD241023000024', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0102', N'SE24112702', N'KH241023000024', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000033', N'HD241023000025', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0103', N'SE24112702', N'KH241023000025', N'Đã bán', 0.1, N'KM241025005')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000034', N'HD241023000025', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0104', N'SE24112702', N'KH241023000025', N'Đã bán', 0.1, N'KM241025003')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000035', N'HD241023000026', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0105', N'SE24112702', N'KH241023000026', N'Đã bán', 0.1, N'KM241025002')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000036', N'HD241023000027', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0106', N'SE24112702', N'KH241023000027', N'Đã bán', 0.1, N'KM241025006')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000037', N'HD241023000028', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0107', N'SE24112702', N'KH241023000028', N'Đã bán', 0.1, N'KM241025007')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000038', N'HD241023000029', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0108', N'SE24112702', N'KH241023000029', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000039', N'HD241023000030', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0109', N'SE24112702', N'KH241023000030', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000040', N'HD241023000031', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0110', N'SE24112702', N'KH241023000031', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000041', N'HD241023000032', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0111', N'SE24112702', N'KH241023000032', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000042', N'HD241023000033', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0112', N'SE24112702', N'KH241023000033', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000043', N'HD241023000034', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0113', N'SE24112702', N'KH241023000034', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000044', N'HD241023000035', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0114', N'SE24112702', N'KH241023000035', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000045', N'HD241023000036', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0115', N'SE24112702', N'KH241023000036', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000046', N'HD241023000037', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0116', N'SE24112702', N'KH241023000037', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000047', N'HD241023000038', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0117', N'SE24112702', N'KH241023000038', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000048', N'HD241023000039', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0118', N'SE24112702', N'KH241023000039', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000049', N'HD241023000040', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0119', N'SE24112702', N'KH241023000040', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000050', N'HD241023000041', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0120', N'SE24112702', N'KH241023000041', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000051', N'HD241023000042', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0121', N'SE24112702', N'KH241023000042', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000052', N'HD241023000043', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0122', N'SE24112702', N'KH241023000043', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000053', N'HD241023000044', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0123', N'SE24112702', N'KH241023000044', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000054', N'HD241023000045', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0124', N'SE24112702', N'KH241023000045', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000055', N'HD241023000046', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0125', N'SE24112702', N'KH241023000046', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000056', N'HD241023000047', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0126', N'SE24112702', N'KH241023000047', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000057', N'HD241023000048', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0127', N'SE24112702', N'KH241023000048', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000058', N'HD241023000049', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0128', N'SE24112702', N'KH241023000049', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000059', N'HD241023000050', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0129', N'SE24112702', N'KH241023000050', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000060', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0130', N'SE24112702', N'KH241023000051', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000061', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0131', N'SE24112702', N'KH241023000052', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000062', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0132', N'SE24112702', N'KH241023000053', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000063', N'HD241023000002', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0133', N'SE24112703', N'KH241023000054', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000064', N'HD241023000002', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0134', N'SE24112703', N'KH241023000055', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000065', N'HD241023000002', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0135', N'SE24112703', N'KH241023000056', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000066', N'HD241023000002', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0136', N'SE24112703', N'KH241023000057', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000067', N'HD241023000003', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0137', N'SE24112703', N'KH241023000058', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000068', N'HD241023000003', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0138', N'SE24112703', N'KH241023000059', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000069', N'HD241023000003', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0139', N'SE24112703', N'KH241023000060', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000070', N'HD241023000003', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0140', N'SE24112703', N'KH241023000061', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000071', N'HD241023000004', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0141', N'SE24112703', N'KH241023000062', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000072', N'HD241023000004', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0142', N'SE24112703', N'KH241023000063', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000073', N'HD241023000004', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0143', N'SE24112703', N'KH241023000064', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000074', N'HD241023000004', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0144', N'SE24112703', N'KH241023000065', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000075', N'HD241023000004', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0145', N'SE24112703', N'KH241023000066', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000076', N'HD241023000007', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0146', N'SE24112703', N'KH241023000067', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000077', N'HD241023000007', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0147', N'SE24112703', N'KH241023000068', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000078', N'HD241023000007', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0148', N'SE24112703', N'KH241023000069', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000079', N'HD241023000007', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0149', N'SE24112703', N'KH241023000070', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000080', N'HD241023000006', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0150', N'SE24112703', N'KH241023000071', N'Đã đổi', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000081', N'HD241023000006', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0151', N'SE24112703', N'KH241023000072', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000082', N'HD241023000006', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0152', N'SE24112703', N'KH241023000073', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000083', N'HD241023000006', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0153', N'SE24112703', N'KH241023000074', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000084', N'HD241023000010', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0154', N'SE24112703', N'KH241023000075', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000085', N'HD241023000010', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0155', N'SE24112703', N'KH241023000076', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000086', N'HD241023000010', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0156', N'SE24112703', N'KH241023000077', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000087', N'HD241023000011', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0157', N'SE24112703', N'KH241023000078', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000088', N'HD241023000012', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0158', N'SE24112703', N'KH241023000079', N'Đã trả', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000089', N'HD241023000013', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0159', N'SE24112703', N'KH241023000080', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000090', N'HD241023000016', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0160', N'SE24112703', N'KH241023000081', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000091', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0201', N'SE24112703', N'KH241023000082', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000092', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0202', N'SE24112703', N'KH241023000083', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000093', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0203', N'SE24112703', N'KH241023000084', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000094', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0204', N'SE24112703', N'KH241023000085', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000095', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0205', N'SE24112703', N'KH241023000086', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000096', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0206', N'SE24112703', N'KH241023000087', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000097', N'HD241023000017', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0207', N'SE24112703', N'KH241023000088', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000098', N'HD241023000018', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0208', N'SE24112703', N'KH241023000089', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000099', N'HD241023000020', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0209', N'SE24112703', N'KH241023000090', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000100', N'HD241023000021', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0210', N'SE24112703', N'KH241023000091', N'Đã bán', 0.1, N'KM241025001')
GO
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000101', N'HD241023000022', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0211', N'SE24112703', N'KH241023000092', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000102', N'HD241023000023', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0212', N'SE24112703', N'KH241023000093', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000103', N'HD241023000024', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0213', N'SE24112703', N'KH241023000094', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000104', N'HD241023000025', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0214', N'SE24112703', N'KH241023000095', N'Vé được đổi', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000105', N'HD241023000026', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0215', N'SE24112703', N'KH241023000096', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000106', N'HD241023000027', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0216', N'SE24112703', N'KH241023000097', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000107', N'HD241023000028', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0217', N'SE24112703', N'KH241023000098', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000108', N'HD241023000029', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0218', N'SE24112703', N'KH241023000099', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000109', N'HD241023000030', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0219', N'SE24112803', N'KH241023000100', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000110', N'HD241023000001', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0220', N'SE24112803', N'KH241023000101', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000111', N'HD241023000002', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0221', N'SE24112803', N'KH241023000102', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000112', N'HD241023000003', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0222', N'SE24112803', N'KH241023000103', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000113', N'HD241023000004', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0223', N'SE24112803', N'KH241023000104', N'Vé được trả', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000114', N'HD241023000005', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0224', N'SE24112803', N'KH241023000105', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000115', N'HD241023000006', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0225', N'SE24112803', N'KH241023000106', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000116', N'HD241023000007', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0226', N'SE24112803', N'KH241023000107', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000117', N'HD241023000008', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0227', N'SE24112803', N'KH241023000108', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000118', N'HD241023000009', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0228', N'SE24112803', N'KH241023000109', N'Vé được trả', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000119', N'HD241023000010', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0229', N'SE24112803', N'KH241023000110', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000120', N'HD241023000051', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0228', N'SE24102402', N'KH241023000001', N'Đã bán', 0.1, NULL)
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000121', N'HD241023000052', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0226', N'SE24102401', N'KH241023000001', N'Đã bán', 0.1, NULL)
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241023000122', N'HD241023000052', N'LV1', CAST(N'2024-10-23T07:48:20.000' AS DateTime), N'SENC0229', N'SE24102401', N'KH241023000001', N'Đã bán', 0.1, NULL)
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241024000001', N'HD241024000003', N'LV1', CAST(N'2024-10-24T19:00:28.553' AS DateTime), N'SENCL0441', N'SE24112701', N'KH241024000003', N'Đã bán', 10, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241030000001', N'HD241030000001', N'LV1', CAST(N'2024-10-30T23:00:01.840' AS DateTime), N'SENC0121', N'SE24112706', N'KH241030000001', N'Đã bán', 0.1, N'KM241025002')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241030000002', N'HD241030000001', N'LV1', CAST(N'2024-10-30T23:00:01.857' AS DateTime), N'SENC0125', N'SE24112706', N'KH241030000001', N'Đã bán', 0.1, N'KM241025002')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241030000003', N'HD241030000002', N'LV1', CAST(N'2024-10-30T23:03:12.150' AS DateTime), N'SENC0126', N'SE24112706', N'KH241030000002', N'Đã bán', 0.1, N'KM241025002')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000001', N'HD241205000001', N'LV1', CAST(N'2024-12-05T14:03:48.020' AS DateTime), N'SENC0109', N'SE24120601', N'KH241205000001', N'Đã bán', 0.1, N'KM241025006')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000002', N'HD241205000001', N'LV1', CAST(N'2024-12-05T14:03:48.040' AS DateTime), N'SENC0113', N'SE24120601', N'KH241205000002', N'Đã bán', 0.1, N'KM241025002')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000003', N'HD241205000002', N'LV1', CAST(N'2024-12-05T14:05:45.967' AS DateTime), N'SENC0111', N'SE24120601', N'KH241205000003', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000004', N'HD241205000002', N'LV1', CAST(N'2024-12-05T14:05:45.983' AS DateTime), N'SENC0115', N'SE24120601', N'KH241205000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000005', N'HD241205000002', N'LV1', CAST(N'2024-12-05T14:05:45.983' AS DateTime), N'SENC0119', N'SE24120601', N'KH241205000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000006', N'HD241205000003', N'LV1', CAST(N'2024-12-05T14:07:13.630' AS DateTime), N'SEVIP1003', N'SE24120601', N'KH241205000006', N'Đã bán', 0.1, N'KM241025007')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000007', N'HD241205000003', N'LV1', CAST(N'2024-12-05T14:07:13.640' AS DateTime), N'SEVIP1004', N'SE24120601', N'KH241205000007', N'Đã bán', 0.1, N'KM241025004')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000008', N'HD241205000004', N'LV1', CAST(N'2024-12-05T14:08:04.063' AS DateTime), N'SEBNL0901', N'SE24120601', N'KH241205000008', N'Đã bán', 0.1, N'KM241025003')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000009', N'HD241205000005', N'LV1', CAST(N'2024-12-05T14:09:45.143' AS DateTime), N'SEANL0821', N'SE24120601', N'KH241205000009', N'Đã bán', 0.1, N'KM241025002')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241205000010', N'HD241205000005', N'LV1', CAST(N'2024-12-05T14:09:45.160' AS DateTime), N'SEANL0822', N'SE24120601', N'KH241205000010', N'Đã bán', 0.1, N'KM241025005')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241206000001', N'HD241206000001', N'LV1', CAST(N'2024-12-06T08:29:54.993' AS DateTime), N'SENC0121', N'SE24120601', N'KH241206000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241206000002', N'HD241206000002', N'LV1', CAST(N'2024-12-06T09:52:52.553' AS DateTime), N'SEVIP1007', N'SE24120601', N'KH241206000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241206000003', N'HD241206000003', N'LV1', CAST(N'2024-12-06T09:52:56.357' AS DateTime), N'SEVIP1007', N'SE24120601', N'KH241206000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241206000004', N'HD241206000004', N'LV1', CAST(N'2024-12-06T09:52:58.420' AS DateTime), N'SEVIP1007', N'SE24120601', N'KH241206000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241206000005', N'HD241206000005', N'LV1', CAST(N'2024-12-06T09:53:20.833' AS DateTime), N'SEVIP1007', N'SE24120601', N'KH241206000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241206000006', N'HD241206000006', N'LV1', CAST(N'2024-12-06T09:53:32.500' AS DateTime), N'SEVIP1007', N'SE24120601', N'KH241206000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241206000007', N'HD241206000006', N'LV1', CAST(N'2024-12-06T09:53:32.517' AS DateTime), N'SEVIP1008', N'SE24120601', N'KH241206000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241206000008', N'HD241206000006', N'LV1', CAST(N'2024-12-06T09:53:32.520' AS DateTime), N'SEVIP1009', N'SE24120601', N'KH241206000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000001', N'HD241207000001', N'LV1', CAST(N'2024-12-07T08:09:45.670' AS DateTime), N'SENC0121', N'SE24121101', N'KH241207000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000002', N'HD241207000001', N'LV1', CAST(N'2024-12-07T08:09:45.713' AS DateTime), N'SENC0118', N'SE24121101', N'KH241207000002', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000003', N'HD241207000002', N'LV1', CAST(N'2024-12-07T08:18:32.327' AS DateTime), N'SENC0109', N'SE24121101', N'KH241207000003', N'Đã bán', 0.1, N'KM241025003')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000004', N'HD241207000002', N'LV1', CAST(N'2024-12-07T08:18:32.347' AS DateTime), N'SENC0113', N'SE24121101', N'KH241207000004', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000005', N'HD241207000003', N'LV1', CAST(N'2024-12-07T08:21:40.723' AS DateTime), N'SEVIP1005', N'SE24121101', N'KH241207000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000006', N'HD241207000003', N'LV1', CAST(N'2024-12-07T08:21:40.750' AS DateTime), N'SEVIP1007', N'SE24121101', N'KH241207000006', N'Đã bán', 0.1, N'KM241025001')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000007', N'HD241207000003', N'LV1', CAST(N'2024-12-07T08:21:40.777' AS DateTime), N'SEVIP1009', N'SE24121101', N'KH241207000007', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000008', N'HD241207000004', N'LV1', CAST(N'2024-12-07T23:11:22.447' AS DateTime), N'SEVIP1002', N'SE24121101', N'KH241207000008', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000009', N'HD241207000005', N'LV1', CAST(N'2024-12-07T23:14:19.027' AS DateTime), N'SEVIP1001', N'SE24121101', N'KH241207000009', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000010', N'HD241207000005', N'LV1', CAST(N'2024-12-07T23:14:19.040' AS DateTime), N'SEVIP1003', N'SE24121101', N'KH241207000010', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241207000011', N'HD241207000005', N'LV1', CAST(N'2024-12-07T23:14:19.040' AS DateTime), N'SEVIP1004', N'SE24121101', N'KH241207000011', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000001', N'HD241208000001', N'LV1', CAST(N'2024-12-08T01:02:41.873' AS DateTime), N'SENC0126', N'SE24121101', N'KH241208000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000002', N'HD241208000002', N'LV1', CAST(N'2024-12-08T10:39:45.130' AS DateTime), N'SEVIP1006', N'SE24121101', N'KH241208000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000003', N'HD241208000003', N'LV1', CAST(N'2024-12-08T10:41:34.597' AS DateTime), N'SEANL0809', N'SE24121101', N'KH241208000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000004', N'HD241208000004', N'LV1', CAST(N'2024-12-08T10:42:24.787' AS DateTime), N'SEANL0809', N'SE24121101', N'KH241208000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000005', N'HD241208000005', N'LV1', CAST(N'2024-12-08T10:43:13.260' AS DateTime), N'SEANL0811', N'SE24121101', N'KH241208000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000006', N'HD241208000006', N'LV1', CAST(N'2024-12-08T10:43:27.130' AS DateTime), N'SEANL0812', N'SE24121101', N'KH241208000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000007', N'HD241208000007', N'LV1', CAST(N'2024-12-08T10:43:46.927' AS DateTime), N'SEANL0823', N'SE24121101', N'KH241208000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000008', N'HD241208000007', N'LV1', CAST(N'2024-12-08T10:43:46.937' AS DateTime), N'SEANL0824', N'SE24121101', N'KH241208000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000009', N'HD241208000007', N'LV1', CAST(N'2024-12-08T10:43:46.940' AS DateTime), N'SEANL0825', N'SE24121101', N'KH241208000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000010', N'HD241208000008', N'LV1', CAST(N'2024-12-08T11:10:46.700' AS DateTime), N'SENC0107', N'SE24121101', N'KH241208000006', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000011', N'HD241208000008', N'LV1', CAST(N'2024-12-08T11:10:46.727' AS DateTime), N'SENC0111', N'SE24121101', N'KH241208000006', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000012', N'HD241208000009', N'LV1', CAST(N'2024-12-08T11:11:03.470' AS DateTime), N'SENC0142', N'SE24121101', N'KH241208000006', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241208000013', N'HD241208000009', N'LV1', CAST(N'2024-12-08T11:11:03.480' AS DateTime), N'SENC0141', N'SE24121101', N'KH241208000006', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241209000001', N'HD241209000001', N'LV1', CAST(N'2024-12-09T09:17:27.920' AS DateTime), N'SENC0133', N'SE24121101', N'KH241209000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241209000002', N'HD241209000001', N'LV1', CAST(N'2024-12-09T09:17:28.367' AS DateTime), N'SENC0134', N'SE24121101', N'KH241209000002', N'Đã bán', 0.1, N'KM241025003')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241209000003', N'HD241209000002', N'LV1', CAST(N'2024-12-09T09:17:48.747' AS DateTime), N'SEVIP1008', N'SE24121101', N'KH241209000002', N'Đã bán', 0.1, N'KM241025003')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241209000004', N'HD241209000002', N'LV1', CAST(N'2024-12-09T09:17:48.757' AS DateTime), N'SEVIP1010', N'SE24121101', N'KH241209000002', N'Đã bán', 0.1, N'KM241025003')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241209000005', N'HD241209000003', N'LV1', CAST(N'2024-12-09T10:11:26.530' AS DateTime), N'SEVIP1005', N'SE24121301', N'KH241209000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241209000006', N'HD241209000004', N'LV1', CAST(N'2024-12-09T10:17:12.760' AS DateTime), N'SENC0117', N'SE24121301', N'KH241209000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241209000007', N'HD241209000004', N'LV2', CAST(N'2024-12-09T10:17:12.763' AS DateTime), N'SENC0133', N'SE24121601', N'KH241209000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000001', N'HD241210000001', N'LV1', CAST(N'2024-12-10T07:51:07.147' AS DateTime), N'SENC0139', N'SE24121101', N'KH241210000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000002', N'HD241210000001', N'LV1', CAST(N'2024-12-10T07:51:07.183' AS DateTime), N'SENC0143', N'SE24121101', N'KH241210000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000003', N'HD241210000002', N'LV1', CAST(N'2024-12-10T07:51:20.083' AS DateTime), N'SENC0153', N'SE24121101', N'KH241210000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000004', N'HD241210000002', N'LV1', CAST(N'2024-12-10T07:51:20.093' AS DateTime), N'SENC0154', N'SE24121101', N'KH241210000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000005', N'HD241210000003', N'LV1', CAST(N'2024-12-10T07:51:57.737' AS DateTime), N'SEVIP1003', N'SE24121301', N'KH241210000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000006', N'HD241210000003', N'LV1', CAST(N'2024-12-10T07:51:57.750' AS DateTime), N'SEVIP1004', N'SE24121301', N'KH241210000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000007', N'HD241210000004', N'LV1', CAST(N'2024-12-10T07:52:39.630' AS DateTime), N'SEVIP1007', N'SE24121301', N'KH241210000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000008', N'HD241210000004', N'LV2', CAST(N'2024-12-10T07:52:39.630' AS DateTime), N'SENML0729', N'SE24121601', N'KH241210000002', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000009', N'HD241210000005', N'LV1', CAST(N'2024-12-10T15:15:56.393' AS DateTime), N'SENC0129', N'SE24121301', N'KH241210000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000010', N'HD241210000005', N'LV1', CAST(N'2024-12-10T15:15:56.417' AS DateTime), N'SENC0130', N'SE24121301', N'KH241210000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000011', N'HD241210000006', N'LV1', CAST(N'2024-12-10T15:16:54.197' AS DateTime), N'SENC0141', N'SE24121301', N'KH241210000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000012', N'HD241210000006', N'LV1', CAST(N'2024-12-10T15:16:54.230' AS DateTime), N'SEBNL0910', N'SE24121301', N'KH241210000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000013', N'HD241210000006', N'LV1', CAST(N'2024-12-10T15:16:54.250' AS DateTime), N'SEBNL0913', N'SE24121301', N'KH241210000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000014', N'HD241210000007', N'LV1', CAST(N'2024-12-10T15:17:15.083' AS DateTime), N'SEVIP1001', N'SE24121301', N'KH241210000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000015', N'HD241210000008', N'LV1', CAST(N'2024-12-10T15:17:37.213' AS DateTime), N'SENC0101', N'SE24121301', N'KH241210000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241210000016', N'HD241210000008', N'LV1', CAST(N'2024-12-10T15:17:37.223' AS DateTime), N'SENC0102', N'SE24121301', N'KH241210000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241211000001', N'HD241211000001', N'LV2', CAST(N'2024-12-11T21:43:57.720' AS DateTime), N'SEVIP1006', N'SE24121301', N'KH241210000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241211000002', N'HD241211000001', N'LV2', CAST(N'2024-12-11T21:43:57.720' AS DateTime), N'SEBNL0910', N'SE24121801', N'KH241210000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241211000003', N'HD241211000002', N'LV2', CAST(N'2024-12-11T21:45:01.067' AS DateTime), N'SENC0114', N'SE24121301', N'KH241210000004', N'Đã trả', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241211000004', N'HD241211000002', N'LV2', CAST(N'2024-12-11T21:45:01.067' AS DateTime), N'SENC0118', N'SE24121801', N'KH241210000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241211000005', N'HD241211000003', N'LV2', CAST(N'2024-12-11T21:45:27.337' AS DateTime), N'SENC0127', N'SE24121301', N'KH241210000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241211000006', N'HD241211000003', N'LV2', CAST(N'2024-12-11T21:45:27.337' AS DateTime), N'SENC0111', N'SE24121801', N'KH241210000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000001', N'HD241212000001', N'LV1', CAST(N'2024-12-12T01:23:05.110' AS DateTime), N'SENC0139', N'SE24121301', N'KH241212000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000002', N'HD241212000002', N'LV1', CAST(N'2024-12-12T01:23:15.573' AS DateTime), N'SENC0127', N'SE24121801', N'KH241212000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000003', N'HD241212000003', N'LV1', CAST(N'2024-12-12T01:23:33.113' AS DateTime), N'SEANL0801', N'SE24121801', N'KH241212000001', N'Vé được đổi', 0.1, N'KM241025008')
GO
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000004', N'HD241212000004', N'LV1', CAST(N'2024-12-12T13:29:30.927' AS DateTime), N'SENC0111', N'SE24121301', N'KH241210000003', N'Đã trả', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000005', N'HD241212000005', N'LV1', CAST(N'2024-12-12T13:29:58.777' AS DateTime), N'SENC0119', N'SE24121601', N'KH241210000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000006', N'HD241212000005', N'LV1', CAST(N'2024-12-12T13:29:58.787' AS DateTime), N'SENC0115', N'SE24121601', N'KH241210000005', N'Vé được đổi', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000007', N'HD241212000005', N'LV1', CAST(N'2024-12-12T13:29:58.790' AS DateTime), N'SENC0111', N'SE24121601', N'KH241212000001', N'Vé được đổi', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000008', N'HD241212000005', N'LV1', CAST(N'2024-12-12T13:29:58.787' AS DateTime), N'SENC0109', N'SE24121601', N'KH241210000005', N'Đã đổi', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000009', N'HD241212000003', N'LV1', CAST(N'2024-12-12T01:23:33.113' AS DateTime), N'SENC0133', N'SE24121801', N'KH241212000001', N'Đã đổi', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241212000010', N'HD241212000006', N'LV1', CAST(N'2024-12-12T23:29:36.770' AS DateTime), N'SENC0119', N'SE24121301', N'KH241212000002', N'Đã bán', 0.1, N'KM241025006')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000001', N'HD241213000001', N'LV1', CAST(N'2024-12-13T13:38:02.670' AS DateTime), N'SENCL0301', N'SE24121601', N'KH241210000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000002', N'HD241213000002', N'LV1', CAST(N'2024-12-13T13:38:13.620' AS DateTime), N'SENCL0333', N'SE24121601', N'KH241210000004', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000003', N'HD241213000003', N'LV1', CAST(N'2024-12-13T13:38:53.990' AS DateTime), N'SENM0623', N'SE24121601', N'KH241212000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000004', N'HD241213000003', N'LV1', CAST(N'2024-12-13T13:38:54.000' AS DateTime), N'SENM0620', N'SE24121601', N'KH241212000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000005', N'HD241213000004', N'LV2', CAST(N'2024-12-13T13:40:47.037' AS DateTime), N'SENCL0301', N'SE24121301', N'KH241210000003', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000006', N'HD241213000004', N'LV2', CAST(N'2024-12-13T13:40:47.037' AS DateTime), N'SEANL0811', N'SE24121601', N'KH241210000003', N'Đã trả', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000007', N'HD241213000004', N'LV2', CAST(N'2024-12-13T13:40:47.050' AS DateTime), N'SENCL0302', N'SE24121301', N'KH241210000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000008', N'HD241213000004', N'LV2', CAST(N'2024-12-13T13:40:47.050' AS DateTime), N'SEANL0810', N'SE24121601', N'KH241210000005', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000009', N'HD241213000004', N'LV2', CAST(N'2024-12-13T13:40:47.050' AS DateTime), N'SENCL0303', N'SE24121301', N'KH241212000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000010', N'HD241213000004', N'LV2', CAST(N'2024-12-13T13:40:47.050' AS DateTime), N'SEANL0809', N'SE24121601', N'KH241212000001', N'Đã bán', 0.1, N'KM241025008')
INSERT [dbo].[Ve] ([maVe], [maHoaDon], [maLoaiVe], [ngayGioXuatVe], [maChoNgoi], [maChuyenTau], [maKhachHang], [trangThai], [thue], [maKhuyenMai]) VALUES (N'VE241213000011', N'HD241212000005', N'LV1', CAST(N'2024-12-12T13:29:58.790' AS DateTime), N'SENM0637', N'SE24121601', N'KH241212000001', N'Đã đổi', 0.1, N'KM241025008')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__A955A0AA35ED66D0]    Script Date: 12/13/2024 2:59:55 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[CCCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__AB6E616468660F83]    Script Date: 12/13/2024 2:59:55 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__DDDFB4830FEDF2AB]    Script Date: 12/13/2024 2:59:55 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[sdt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__LoaiCho__FB74D8E48F978EC2]    Script Date: 12/13/2024 2:59:55 PM ******/
ALTER TABLE [dbo].[LoaiCho] ADD UNIQUE NONCLUSTERED 
(
	[tenLC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__A955A0AA1B20AB34]    Script Date: 12/13/2024 2:59:55 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD UNIQUE NONCLUSTERED 
(
	[CCCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChoNgoi] ADD  DEFAULT (N'Còn trống') FOR [trangThai]
GO
ALTER TABLE [dbo].[ChuyenTau] ADD  DEFAULT (N'Hoạt động') FOR [trangThai]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (getdate()) FOR [ngayGioLapHD]
GO
ALTER TABLE [dbo].[KhuyenMai] ADD  DEFAULT ((0)) FOR [daGuiThongBao]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD  CONSTRAINT [DF_TaiKhoan_matKhau]  DEFAULT ('$2a$10$hx.v7Xiy7I8Rpql8ONmMF.WZY3d6pfQmfpp2EgeXJajNJdUa9KVSa') FOR [matKhau]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD  DEFAULT (N'Kích hoạt') FOR [trangThai]
GO
ALTER TABLE [dbo].[Ve] ADD  DEFAULT ((0.1)) FOR [thue]
GO
ALTER TABLE [dbo].[ChoNgoi]  WITH CHECK ADD FOREIGN KEY([maloaiCho])
REFERENCES [dbo].[LoaiCho] ([maLC])
GO
ALTER TABLE [dbo].[ChoNgoi]  WITH CHECK ADD FOREIGN KEY([maToa])
REFERENCES [dbo].[Toa] ([maToa])
GO
ALTER TABLE [dbo].[ChuyenTau]  WITH CHECK ADD FOREIGN KEY([maGaDi])
REFERENCES [dbo].[Ga] ([maGa])
GO
ALTER TABLE [dbo].[ChuyenTau]  WITH CHECK ADD FOREIGN KEY([maGaDen])
REFERENCES [dbo].[Ga] ([maGa])
GO
ALTER TABLE [dbo].[ChuyenTau]  WITH CHECK ADD FOREIGN KEY([maTau])
REFERENCES [dbo].[Tau] ([maTau])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([macaLam])
REFERENCES [dbo].[CaLam] ([maCL])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([maChucVu])
REFERENCES [dbo].[ChucVu] ([maCV])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([maTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([maTK])
GO
ALTER TABLE [dbo].[Toa]  WITH CHECK ADD FOREIGN KEY([maloaiToa])
REFERENCES [dbo].[LoaiToa] ([maLoai])
GO
ALTER TABLE [dbo].[Toa]  WITH CHECK ADD FOREIGN KEY([maTau])
REFERENCES [dbo].[Tau] ([maTau])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([maChoNgoi])
REFERENCES [dbo].[ChoNgoi] ([maCho])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([maChuyenTau])
REFERENCES [dbo].[ChuyenTau] ([maChuyen])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHD])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([maKhuyenMai])
REFERENCES [dbo].[KhuyenMai] ([maKM])
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD FOREIGN KEY([maLoaiVe])
REFERENCES [dbo].[LoaiVe] ([maLoai])
GO
ALTER TABLE [dbo].[CaLam]  WITH CHECK ADD  CONSTRAINT [CK_maCL_Format] CHECK  (([maCL]='CA2' OR [maCL]='CA1'))
GO
ALTER TABLE [dbo].[CaLam] CHECK CONSTRAINT [CK_maCL_Format]
GO
ALTER TABLE [dbo].[ChoNgoi]  WITH CHECK ADD CHECK  (([trangThai]=N'Đã đặt' OR [trangThai]=N'Còn trống'))
GO
ALTER TABLE [dbo].[ChucVu]  WITH CHECK ADD  CONSTRAINT [CK_maCV_Format] CHECK  (([maCV]='NV' OR [maCV]='QL'))
GO
ALTER TABLE [dbo].[ChucVu] CHECK CONSTRAINT [CK_maCV_Format]
GO
ALTER TABLE [dbo].[ChuyenTau]  WITH CHECK ADD  CONSTRAINT [CK_ngayGioDen_Format] CHECK  (([ngayGioDen]>[ngayGioKhoiHanh]))
GO
ALTER TABLE [dbo].[ChuyenTau] CHECK CONSTRAINT [CK_ngayGioDen_Format]
GO
ALTER TABLE [dbo].[ChuyenTau]  WITH CHECK ADD  CONSTRAINT [CK_trangThaiChuyen_Format] CHECK  (([trangThai]=N'Đã dừng' OR [trangThai]=N'Tạm ngưng' OR [trangThai]=N'Hoạt động'))
GO
ALTER TABLE [dbo].[ChuyenTau] CHECK CONSTRAINT [CK_trangThaiChuyen_Format]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD CHECK  (([soLuong]>(0)))
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD CHECK  (([email] like '%@%.%' OR [email] IS NULL))
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD CHECK  (([ngaySinh]<getdate()))
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD CHECK  (([sdt] like '0%' AND len([sdt])=(10)))
GO
ALTER TABLE [dbo].[KhuyenMai]  WITH CHECK ADD CHECK  (([ngayKetThuc]>[ngayApDung]))
GO
ALTER TABLE [dbo].[KhuyenMai]  WITH CHECK ADD CHECK  (([ngayApDung]>=CONVERT([date],getdate())))
GO
ALTER TABLE [dbo].[KhuyenMai]  WITH CHECK ADD CHECK  (([phanTramKM]>=(0) AND [phanTramKM]<=(1)))
GO
ALTER TABLE [dbo].[LoaiCho]  WITH CHECK ADD CHECK  (([giaCho]>(0)))
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [CK_CCCD_Length] CHECK  ((len([CCCD])=(12)))
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [CK_CCCD_Length]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [CK_gioiTinh_Format] CHECK  (([gioiTinh]=N'Nữ' OR [gioiTinh]=N'Nam'))
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [CK_gioiTinh_Format]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [CK_maNV_Format] CHECK  (((left([maNV],(2))='QL' OR left([maNV],(2))='NV') AND len(substring([maNV],(3),(6)))=(6) AND NOT substring([maNV],(3),(6)) like '%[^0-9]%' AND len(right([maNV],(3)))=(3) AND isnumeric(right([maNV],(3)))=(1)))
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [CK_maNV_Format]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [CK_ngaySinh_Format] CHECK  ((datediff(year,[ngaySinh],getdate())>=(18)))
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [CK_ngaySinh_Format]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [CK_sdt_Format] CHECK  (([sdt] like '0%'))
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [CK_sdt_Format]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [CK_trangThaiNV] CHECK  (([trangThai]=N'Nghỉ làm' OR [trangThai]=N'Làm việc'))
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [CK_trangThaiNV]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [CK_trangThaiTK] CHECK  (([trangThai]=N'Bị khóa' OR [trangThai]=N'Kích hoạt'))
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [CK_trangThaiTK]
GO
ALTER TABLE [dbo].[Tau]  WITH CHECK ADD  CONSTRAINT [CK_maTau_Format] CHECK  (([maTau]='SNT' OR [maTau]='SPT' OR [maTau]='SP' OR [maTau]='SE'))
GO
ALTER TABLE [dbo].[Tau] CHECK CONSTRAINT [CK_maTau_Format]
GO
ALTER TABLE [dbo].[Tau]  WITH CHECK ADD  CONSTRAINT [CK_trangThai_Format] CHECK  (([trangThai]=N'Đã dừng' OR [trangThai]=N'Đang chạy' OR [trangThai]=N'Hoạt động'))
GO
ALTER TABLE [dbo].[Tau] CHECK CONSTRAINT [CK_trangThai_Format]
GO
ALTER TABLE [dbo].[Toa]  WITH CHECK ADD CHECK  (([soLuongCho]>(0)))
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD CHECK  (([trangThai]=N'Vé được trả' OR [trangThai]=N'Vé được đổi' OR [trangThai]=N'Đã trả' OR [trangThai]=N'Đã đổi' OR [trangThai]=N'Đã bán'))
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeVeDoiTheoNgay]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[sp_ThongKeVeDoiTheoNgay]
    @StartDate DATE,
    @EndDate DATE
AS
BEGIN
    WITH DateSeries AS (
        SELECT @StartDate AS DateValue
        UNION ALL
        SELECT DATEADD(day, 1, DateValue)
        FROM DateSeries
        WHERE DateValue < @EndDate
    ),
    VeDoiTheoNgay AS (
        SELECT
            CAST(ngayGioXuatVe AS DATE) AS NgayDoiVe,
            COUNT(*) AS SoLuongVeDaDoi
        FROM
            Ve
        WHERE
            trangThai IN (N'Đã đổi', N'Vé được đổi')
            AND CAST(ngayGioXuatVe AS DATE) BETWEEN @StartDate AND @EndDate
        GROUP BY
            CAST(ngayGioXuatVe AS DATE)
    )
    SELECT
        ds.DateValue AS Ngay,
        ISNULL(vdtn.SoLuongVeDaDoi, 0) AS SoLuongVeDoi
    FROM
        DateSeries ds
    LEFT JOIN
        VeDoiTheoNgay vdtn ON ds.DateValue = vdtn.NgayDoiVe
    ORDER BY
        ds.DateValue;
END;
GO
/****** Object:  Trigger [dbo].[trg_generate_maCho]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



-- Trigger tự động sinh mã Chỗ Ngồi
CREATE TRIGGER [dbo].[trg_generate_maCho]
    ON [dbo].[ChoNgoi]
    INSTEAD OF INSERT
AS
BEGIN
    DECLARE @maToa VARCHAR(20), @maloaiCho VARCHAR(10), @trangThai NVARCHAR(20);
    DECLARE @new_maCho VARCHAR(20), @max_seat_num INT;

    DECLARE insert_cursor CURSOR FOR
SELECT maToa, maloaiCho, trangThai FROM INSERTED;

OPEN insert_cursor;
FETCH NEXT FROM insert_cursor INTO @maToa, @maloaiCho, @trangThai;

WHILE @@FETCH_STATUS = 0
BEGIN
SELECT @max_seat_num = COALESCE(MAX(CAST(RIGHT(maCho, 2) AS INT)), 0)
FROM ChoNgoi
WHERE maToa = @maToa;

SET @new_maCho = CONCAT(LTRIM(RTRIM(@maToa)), RIGHT('00' + CAST(@max_seat_num + 1 AS VARCHAR(2)), 2));

INSERT INTO ChoNgoi (maCho, maloaiCho, maToa, trangThai)
VALUES (@new_maCho, @maloaiCho, @maToa, @trangThai);

FETCH NEXT FROM insert_cursor INTO @maToa, @maloaiCho, @trangThai;
END

CLOSE insert_cursor;
DEALLOCATE insert_cursor;
END;
GO
ALTER TABLE [dbo].[ChoNgoi] ENABLE TRIGGER [trg_generate_maCho]
GO
/****** Object:  Trigger [dbo].[Trigger_maChuyen]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
---- Trigger tự động sinh mã Chuyến Tàu
CREATE TRIGGER [dbo].[Trigger_maChuyen]
    ON [dbo].[ChuyenTau]
    INSTEAD OF INSERT
AS
BEGIN
    DECLARE @maTau CHAR(10), @ngayGioKhoiHanh DATETIME, @ngayKhoiHanh VARCHAR(6);
    DECLARE @soThuTu INT, @maChuyen VARCHAR(20);
    DECLARE @gaDi int, @maGaDen int, @ngayGioDen DATETIME;

    -- Thêm câu lệnh để chèn tất cả các bản ghi từ bảng INSERTED
    DECLARE inserted_cursor CURSOR FOR
    SELECT maTau, ngayGioKhoiHanh, maGaDi, maGaDen, ngayGioDen FROM INSERTED;

    OPEN inserted_cursor;
    FETCH NEXT FROM inserted_cursor INTO @maTau, @ngayGioKhoiHanh, @gaDi, @maGaDen, @ngayGioDen;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        SET @ngayKhoiHanh = FORMAT(@ngayGioKhoiHanh, 'yyMMdd');

        SET @soThuTu = (SELECT COUNT(*) + 1 FROM ChuyenTau
                        WHERE maTau = @maTau AND FORMAT(ngayGioKhoiHanh, 'yyMMdd') = @ngayKhoiHanh);

        SET @maChuyen = LTRIM(RTRIM(@maTau)) + @ngayKhoiHanh + RIGHT('00' + CAST(@soThuTu AS VARCHAR), 2);

        WHILE EXISTS (SELECT 1 FROM ChuyenTau WHERE maChuyen = @maChuyen)
        BEGIN
            SET @soThuTu = @soThuTu + 1;
            SET @maChuyen = LTRIM(RTRIM(@maTau)) + @ngayKhoiHanh + RIGHT('00' + CAST(@soThuTu AS VARCHAR), 2);
        END;

        INSERT INTO ChuyenTau (maChuyen, maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen)
        VALUES (@maChuyen, @maTau, @gaDi, @maGaDen, @ngayGioKhoiHanh, @ngayGioDen);

        FETCH NEXT FROM inserted_cursor INTO @maTau, @ngayGioKhoiHanh, @gaDi, @maGaDen, @ngayGioDen;
    END;

    CLOSE inserted_cursor;
    DEALLOCATE inserted_cursor;
END;

GO
ALTER TABLE [dbo].[ChuyenTau] ENABLE TRIGGER [Trigger_maChuyen]
GO
/****** Object:  Trigger [dbo].[Trigger_macTau]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Trigger để tự động tạo macTau chẵn hoặc lẻ dựa trên ga đi, ga đến, và ngày khởi hành
CREATE TRIGGER [dbo].[Trigger_macTau]
ON [dbo].[ChuyenTau]
AFTER INSERT
AS
BEGIN
    DECLARE @maChuyen VARCHAR(20), @maTau CHAR(4), @maGaDi INT, @maGaDen INT, @ngayGioKhoiHanh DATE, @nextMacTau INT;

    -- Lấy dữ liệu từ hàng được chèn vào
    DECLARE macTau_cursor CURSOR FOR
        SELECT maChuyen, maTau, maGaDi, maGaDen, CAST(ngayGioKhoiHanh AS DATE) FROM INSERTED;

    OPEN macTau_cursor;
    FETCH NEXT FROM macTau_cursor INTO @maChuyen, @maTau, @maGaDi, @maGaDen, @ngayGioKhoiHanh;
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Xác định prefix từ mã tàu
        DECLARE @prefix VARCHAR(4) = LEFT(@maTau, 3);  -- Giữ nguyên 3 ký tự đầu
        SET @nextMacTau = 1;  -- Khởi tạo số tiếp theo

        -- Lấy số tiếp theo cho mác tàu trong cùng 1 ngày khởi hành
        IF @maGaDi = 21 -- Nếu ga đi là 21, tìm số chẵn
        BEGIN
            SET @nextMacTau = (SELECT ISNULL(MAX(CAST(SUBSTRING(macTau, LEN(@prefix) + 1, LEN(macTau) - LEN(@prefix)) AS INT)), 0) + 2 
                                        FROM ChuyenTau 
                                        WHERE macTau LIKE @prefix + '%' 
                                        AND CAST(ngayGioKhoiHanh AS DATE) = @ngayGioKhoiHanh
                                        AND CAST(SUBSTRING(macTau, LEN(@prefix) + 1, LEN(macTau) - LEN(@prefix)) AS INT) % 2 = 0);
        END
        ELSE IF @maGaDi = 1 -- Nếu ga đi là 1, tìm số lẻ
        BEGIN
            SET @nextMacTau = (SELECT ISNULL(MAX(CAST(SUBSTRING(macTau, LEN(@prefix) + 1, LEN(macTau) - LEN(@prefix)) AS INT)), 1) + 2 
                                        FROM ChuyenTau 
                                        WHERE macTau LIKE @prefix + '%' 
                                        AND CAST(ngayGioKhoiHanh AS DATE) = @ngayGioKhoiHanh
                                        AND CAST(SUBSTRING(macTau, LEN(@prefix) + 1, LEN(macTau) - LEN(@prefix)) AS INT) % 2 = 1);
        END

        -- Cập nhật giá trị macTau với định dạng "maTau + số"
        UPDATE ChuyenTau
        SET macTau = @prefix + RIGHT('0' + CAST(@nextMacTau AS VARCHAR), 2)
        WHERE maChuyen = @maChuyen;

        FETCH NEXT FROM macTau_cursor INTO @maChuyen, @maTau, @maGaDi, @maGaDen, @ngayGioKhoiHanh;
    END;

    CLOSE macTau_cursor;
    DEALLOCATE macTau_cursor;
END;
GO
ALTER TABLE [dbo].[ChuyenTau] ENABLE TRIGGER [Trigger_macTau]
GO
/****** Object:  Trigger [dbo].[trg_generate_maHD]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Trigger tự động sinh mã Hóa Đơn
CREATE TRIGGER [dbo].[trg_generate_maHD]
    ON [dbo].[HoaDon]
    INSTEAD OF INSERT
AS
BEGIN
    DECLARE @current_date VARCHAR(8) = FORMAT(GETDATE(), 'yyMMdd');
    DECLARE @new_maHD VARCHAR(20), @max_seq INT;

SELECT @max_seq = COALESCE(MAX(CAST(RIGHT(maHD, 6) AS INT)), 0) + 1
FROM HoaDon
WHERE LEFT(maHD, 8) = 'HD' + @current_date;

SET @new_maHD = 'HD' + @current_date + RIGHT('000000' + CAST(@max_seq AS VARCHAR), 6);

INSERT INTO HoaDon (maHD, ngayGioLapHD, maNhanVien, maKhachHang, soLuong)
SELECT @new_maHD, GETDATE(), maNhanVien, maKhachHang, soLuong
FROM INSERTED;
END;
GO
ALTER TABLE [dbo].[HoaDon] ENABLE TRIGGER [trg_generate_maHD]
GO
/****** Object:  Trigger [dbo].[trg_generate_maKH]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Trigger tự động sinh mã Khách Hàng
CREATE TRIGGER [dbo].[trg_generate_maKH]
    ON [dbo].[KhachHang]
    INSTEAD OF INSERT
AS
BEGIN
    DECLARE @current_date VARCHAR(8) = FORMAT(GETDATE(), 'yyMMdd');
    DECLARE @new_maKH VARCHAR(20), @max_seq INT;

SELECT @max_seq = COALESCE(MAX(CAST(RIGHT(maKH, 6) AS INT)), 0) + 1
FROM KhachHang
WHERE LEFT(maKH, 8) = 'KH' + @current_date;

SET @new_maKH = 'KH' + @current_date + RIGHT('000000' + CAST(@max_seq AS VARCHAR), 6);

INSERT INTO KhachHang (maKH, tenKH, CCCD, sdt, email, ngaySinh, doiTuong)
SELECT @new_maKH, tenKH, CCCD, sdt, email, ngaySinh, doiTuong
FROM INSERTED;
END;
GO
ALTER TABLE [dbo].[KhachHang] ENABLE TRIGGER [trg_generate_maKH]
GO
/****** Object:  Trigger [dbo].[trg_generate_maKM]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Trigger tự động sinh mã Khuyến Mãi
CREATE TRIGGER [dbo].[trg_generate_maKM]
    ON [dbo].[KhuyenMai]
    INSTEAD OF INSERT
AS
BEGIN
    DECLARE @ngayApDung DATE, @ngayKetThuc DATE, @phanTramKM FLOAT, @doiTuong NVARCHAR(30);
    DECLARE @new_maKM VARCHAR(15), @max_num INT;

    -- Cursor lấy dữ liệu từ bảng INSERTED
    DECLARE insert_cursor CURSOR FOR
    SELECT ngayApDung, ngayKetThuc, phanTramKM, doiTuong FROM INSERTED;

    OPEN insert_cursor;
    FETCH NEXT FROM insert_cursor INTO @ngayApDung, @ngayKetThuc, @phanTramKM, @doiTuong;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Tạo mã khuyến mãi với định dạng KM + yyMMdd + số thứ tự
        SET @new_maKM = 'KM' + FORMAT(@ngayApDung, 'yyMMdd');

        -- Lấy giá trị số thứ tự lớn nhất và tăng nó lên 1
        SELECT @max_num = COALESCE(MAX(CAST(RIGHT(maKM, 3) AS INT)), 0)
        FROM KhuyenMai
        WHERE LEFT(maKM, 8) = @new_maKM;

        -- Tạo mã khuyến mãi mới
        SET @new_maKM = @new_maKM + RIGHT('000' + CAST(@max_num + 1 AS VARCHAR(3)), 3);

        -- Chèn bản ghi mới vào bảng KhuyenMai
        INSERT INTO KhuyenMai (maKM, ngayApDung, ngayKetThuc, phanTramKM, doiTuong)
        VALUES (@new_maKM, @ngayApDung, @ngayKetThuc, @phanTramKM, @doiTuong);

        FETCH NEXT FROM insert_cursor INTO @ngayApDung, @ngayKetThuc, @phanTramKM, @doiTuong;
    END

    CLOSE insert_cursor;
    DEALLOCATE insert_cursor;
END;
GO
ALTER TABLE [dbo].[KhuyenMai] ENABLE TRIGGER [trg_generate_maKM]
GO
/****** Object:  Trigger [dbo].[trg_AutoMaNV]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Trigger tự động sinh mã Nhân Viên
CREATE TRIGGER [dbo].[trg_AutoMaNV]
    ON [dbo].[NhanVien]
    INSTEAD OF INSERT
AS
BEGIN
    DECLARE @maChucVu VARCHAR(2), @ngayVaoLam DATE, @maNV VARCHAR(11);
    DECLARE @currentYear CHAR(2), @currentMonth CHAR(2), @currentDay CHAR(2);
    DECLARE @nextID INT, @prefix VARCHAR(2);

    DECLARE new_cursor CURSOR FOR
SELECT maChucVu, ngayVaoLam FROM INSERTED;

OPEN new_cursor;
FETCH NEXT FROM new_cursor INTO @maChucVu, @ngayVaoLam;

WHILE @@FETCH_STATUS = 0
BEGIN
        SET @currentYear = RIGHT(YEAR(@ngayVaoLam), 2);
        SET @currentMonth = RIGHT('0' + CAST(MONTH(@ngayVaoLam) AS VARCHAR), 2);
        SET @currentDay = RIGHT('0' + CAST(DAY(@ngayVaoLam) AS VARCHAR), 2);
        SET @prefix = @maChucVu;

SELECT @nextID = ISNULL(MAX(CAST(RIGHT(maNV, 3) AS INT)), 0) + 1
FROM NhanVien
WHERE maNV LIKE @prefix + @currentYear + @currentMonth + @currentDay + '%';

SET @maNV = @prefix + @currentYear + @currentMonth + @currentDay + RIGHT('000' + CAST(@nextID AS VARCHAR), 3);

INSERT INTO TaiKhoan (maTK)
VALUES (@maNV);

INSERT INTO NhanVien (maNV, tenNV, gioiTinh, ngaySinh, ngayVaoLam, CCCD, sdt, email, diaChi, trangThai, macaLam, maTaiKhoan, maChucVu, duongDanAnh)
SELECT @maNV, tenNV, gioiTinh, ngaySinh, ngayVaoLam, CCCD, sdt, email, diaChi, trangThai, macaLam, @maNV, maChucVu, duongDanAnh
FROM INSERTED;

FETCH NEXT FROM new_cursor INTO @maChucVu, @ngayVaoLam;
END;

CLOSE new_cursor;
DEALLOCATE new_cursor;
END;
GO
ALTER TABLE [dbo].[NhanVien] ENABLE TRIGGER [trg_AutoMaNV]
GO
/****** Object:  Trigger [dbo].[trg_UpdateTaiKhoanOnNhanVienStatusChange]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Trigger cập nhật trạng thái Tài Khoản khi Nhân Viên thay đổi trạng thái
CREATE TRIGGER [dbo].[trg_UpdateTaiKhoanOnNhanVienStatusChange]
    ON [dbo].[NhanVien]
    AFTER UPDATE
              AS
BEGIN
UPDATE TaiKhoan
SET trangThai = N'Bị khóa'
WHERE maTK IN (
    SELECT maTaiKhoan FROM INSERTED
    WHERE trangThai = N'Nghỉ làm'
);

UPDATE TaiKhoan
SET trangThai = N'Kích hoạt'
WHERE maTK IN (
    SELECT maTaiKhoan FROM INSERTED
    WHERE trangThai = N'Làm việc'
);
END;

GO
ALTER TABLE [dbo].[NhanVien] ENABLE TRIGGER [trg_UpdateTaiKhoanOnNhanVienStatusChange]
GO
/****** Object:  Trigger [dbo].[trg_generate_maToa]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



-- Trigger tự động sinh mã Toa
CREATE TRIGGER [dbo].[trg_generate_maToa]
    ON [dbo].[Toa]
    INSTEAD OF INSERT
AS
BEGIN
    -- Khai báo biến đúng kiểu dữ liệu
    DECLARE @maTau CHAR(12), @maloaiToa VARCHAR(10), @soLuongCho INT;
    DECLARE @new_maToa VARCHAR(20), @max_toa_num INT;

    -- Lấy dữ liệu từ INSERTED
    SELECT @maTau = maTau, @maloaiToa = maloaiToa, @soLuongCho = soLuongCho FROM INSERTED;

    -- Kiểm tra xem mã tàu có tồn tại trong bảng Tau không
    IF NOT EXISTS (SELECT 1 FROM Tau WHERE maTau = @maTau)
    BEGIN
        RAISERROR('Mã tàu không tồn tại trong bảng Tau.', 16, 1);
        RETURN;
    END

    -- Khởi tạo biến đếm mã toa
    SET @max_toa_num = (SELECT COUNT(*) FROM Toa WHERE maTau = @maTau) + 1; -- Tính số toa hiện có của mã tàu

    -- Tạo mã toa theo định dạng mong muốn
    SET @new_maToa = CONCAT(LTRIM(RTRIM(@maTau)), LTRIM(RTRIM(@maloaiToa)), RIGHT('00' + CAST(@max_toa_num AS VARCHAR(2)), 2));

    -- Nếu mã toa đã tồn tại, tăng số thứ tự cho tới khi tìm thấy mã chưa tồn tại
    WHILE EXISTS (SELECT 1 FROM Toa WHERE maToa = @new_maToa)
    BEGIN
        SET @max_toa_num = @max_toa_num + 1;
        SET @new_maToa = CONCAT( LTRIM(RTRIM(@maTau)), LTRIM(RTRIM(@maloaiToa)), RIGHT('00' + CAST(@max_toa_num AS VARCHAR(2)), 2));
    END

    -- Chèn dữ liệu mới vào bảng Toa
    INSERT INTO Toa (maToa, soLuongCho, maTau, maloaiToa)
    VALUES (@new_maToa, @soLuongCho, @maTau, @maloaiToa);
END;
GO
ALTER TABLE [dbo].[Toa] ENABLE TRIGGER [trg_generate_maToa]
GO
/****** Object:  Trigger [dbo].[trg_generate_maVe]    Script Date: 12/13/2024 2:59:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

------------------------
-- TRIGGERS
------------------------

-- Trigger tự động sinh mã Vé
CREATE TRIGGER [dbo].[trg_generate_maVe]
    ON [dbo].[Ve]
    INSTEAD OF INSERT
AS
BEGIN
    DECLARE @ngayGio DATETIME = GETDATE();
    DECLARE @prefix VARCHAR(10) = 'VE' + FORMAT(@ngayGio, 'yyMMdd');
    DECLARE @max_ve_num INT;

SELECT @max_ve_num = COALESCE(MAX(CAST(SUBSTRING(maVe, 9, 6) AS INT)), 0)
FROM Ve
WHERE LEFT(maVe, 8) = @prefix;

DECLARE @new_maVe VARCHAR(20) = @prefix + RIGHT('000000' + CAST(@max_ve_num + 1 AS VARCHAR(6)), 6);

INSERT INTO Ve (maVe, maHoaDon, maLoaiVe, ngayGioXuatVe, maChoNgoi, maChuyenTau, maKhachHang, trangThai, thue, maKhuyenMai)
SELECT @new_maVe, maHoaDon, maLoaiVe, ngayGioXuatVe, maChoNgoi, maChuyenTau, maKhachHang, trangThai, thue, maKhuyenMai
FROM INSERTED;
END;
GO
ALTER TABLE [dbo].[Ve] ENABLE TRIGGER [trg_generate_maVe]
GO
USE [master]
GO
ALTER DATABASE [QuanLyBanVeTauHoa] SET  READ_WRITE 
GO
