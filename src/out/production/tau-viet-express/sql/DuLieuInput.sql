use [QuanLyBanVeTauHoa]
-- NhanVien
INSERT INTO NhanVien (tenNV, gioiTinh, ngaySinh, ngayVaoLam, CCCD, sdt, email, diaChi, trangThai, macaLam, maChucVu, duongDanAnh) VALUES
    (N'Lê Nguyễn Phi Trường', N'Nam', '1990-01-01', '2023-01-01', '123456789012', '0912345678', 'nguyenvana@example.com', N'Hà Nội', N'Làm việc', 'CA1', 'QL', 'images/employees/nv-1.jpg');
INSERT INTO NhanVien (tenNV, gioiTinh, ngaySinh, ngayVaoLam, CCCD, sdt, email, diaChi, trangThai, macaLam, maChucVu, duongDanAnh) VALUES
    (N'Hồ Quốc Huy', N'Nữ', '1995-05-10', '2023-01-10', '234567890123', '0987654321', 'tranthib@example.com', N'Hồ Chí Minh', N'Làm việc', 'CA2', 'NV', 'images/employees/nv-2.jpg');

-- ChuyenTau
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-27 06:00:00', '2024-11-29 14:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-27 12:00:00', '2024-11-29 18:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-27 17:00:00', '2024-11-29 21:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-27 21:00:00', '2024-11-29 03:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SNT', 21, 16, '2024-11-27 06:00:00', '2024-11-29 14:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SNT', 21, 16, '2024-11-27 08:00:00', '2024-11-29 14:00:00')

INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 1, 21, '2024-11-27 06:00:00', '2024-11-29 14:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SP', 1, 21, '2024-11-27 12:00:00', '2024-11-29 18:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 1, 21, '2024-11-27 17:00:00', '2024-11-29 21:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SP', 1, 21, '2024-11-27 21:00:00', '2024-11-29 03:00:00')

INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-28 06:00:00', '2024-11-29 14:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-28 12:00:00', '2024-11-29 18:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-28 17:00:00', '2024-11-29 21:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-28 21:00:00', '2024-11-29 03:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SNT', 21, 16, '2024-11-28 06:00:00', '2024-11-29 14:00:00')

INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 1, 21, '2024-11-28 06:00:00', '2024-11-29 14:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SP', 1, 21, '2024-11-28 12:00:00', '2024-11-29 18:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 1, 21, '2024-11-28 17:00:00', '2024-11-29 21:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SP', 1, 21, '2024-11-28 21:00:00', '2024-11-29 03:00:00')

INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-28 06:00:00', '2024-11-29 14:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-28 12:00:00', '2024-11-29 18:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-28 17:00:00', '2024-11-29 21:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-11-28 21:00:00', '2024-11-29 03:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SNT', 21, 16, '2024-11-28 06:00:00', '2024-11-29 14:00:00')

INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 1, 21, '2024-11-30 06:00:00', '2024-12-01 14:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SP', 1, 21, '2024-11-30 12:00:00', '2024-12-01 18:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 1, 21, '2024-11-30 17:00:00', '2024-12-01 21:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SP', 1, 21, '2024-11-30 21:00:00', '2024-12-01 03:00:00')

INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-10-24 06:00:00', '2024-10-25 14:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-10-24 12:00:00', '2024-10-25 18:00:00')
INSERT INTO ChuyenTau (maTau, maGaDi, maGaDen, ngayGioKhoiHanh, ngayGioDen) VALUES 
	('SE', 21, 1, '2024-10-24 17:00:00', '2024-10-25 21:00:00')

-- KhuyenMai
INSERT INTO KhuyenMai (ngayApDung, ngayKetThuc,doiTuong, phanTramKM) VALUES
	('2024-10-25', '2024-12-30', N'Người hoạt động cách mạng', 0.9),  -- Giảm 10%
    ('2024-10-25', '2024-12-30', N'Thương binh', 0.3), -- Giảm 15%
	('2024-10-25', '2024-12-30', N'Người khuyết tật', 0.25),  -- Giảm 20%
	('2024-10-25', '2024-12-30', N'Trẻ em',0.05), -- Giảm 5%
	('2024-10-25', '2024-12-30', N'Người cao tuổi',0.15),  -- Giảm 30%    
	('2024-10-25', '2024-12-30', N'Học sinh, sinh viên',0.1),  -- Giảm 30%  
	('2024-10-25', '2024-12-30', N'Tất cả',0.1);  -- Giảm 30% 
INSERT INTO KhuyenMai (ngayApDung, ngayKetThuc,doiTuong, phanTramKM) VALUES
	('2024-10-25', '2024-12-30', N'Người lớn', 0)
----Khach Hang
--INSERT INTO KhachHang(tenKH, CCCD, sdt, email, ngaySinh, doiTuong) VALUES  -- Đầy đủ thông tin
--(N'Lưu Văn Đạc', '498745245699', '0375869274', 'Dat@gmail.com', '2004-03-29', N'Học sinh, sinh viên')
--INSERT INTO KhachHang(tenKH, sdt, email, ngaySinh, doiTuong) VALUES  -- Không có cccd
--(N'Nguyễn Trấn Gian', '0372569874', 'Giang@gmail.com', '2004-03-22', N'Trẻ em')
--INSERT INTO KhachHang(tenKH, CCCD, sdt, email, doiTuong) VALUES -- Không có ngày sinh
--(N'Huỳnh Yảo', '998742245692', '0275569874', 'Bao@gmail.com', N'Học sinh, sinh viên')
--INSERT INTO KhachHang(tenKH, CCCD, sdt, doiTuong) VALUES -- Không có ngày email
--(N'Huỳnh MiY', '198742245693', '0725569874', N'Học sinh, sinh viên')

----Hoa don
--INSERT INTO HoaDon(maNhanVien, maKhachHang, soLuong, thanhTien) VALUES
--('NV230110001', 'KH241023000001', '3', '742500')
----Ve
--INSERT INTO Ve(maHoaDon, maLoaiVe, ngayGioXuatVe, maChoNgoi, maChuyenTau, maKhachHang, trangThai, maKhuyenMai, thanhTien ) VALUES
--('HD241023000001', 'LV1', '2024-10-23 07:48:20', 'SEANL0801', 'SP24112701', 'KH241023000001', N'Đã bán', 'KM241025001', '247500')
--INSERT INTO Ve(maHoaDon, maLoaiVe, ngayGioXuatVe, maChoNgoi, maChuyenTau, maKhachHang, trangThai, maKhuyenMai, thanhTien ) VALUES
--('HD241023000001', 'LV1', '2024-10-23 07:48:20', 'SEANL0802', 'SP24112701', 'KH241023000003', N'Đã bán', 'KM241025001', '247500')
--INSERT INTO Ve(maHoaDon, maLoaiVe, ngayGioXuatVe, maChoNgoi, maChuyenTau, maKhachHang, trangThai, maKhuyenMai, thanhTien ) VALUES
--('HD241023000001', 'LV1', '2024-10-23 07:48:20', 'SEANL0803', 'SP24112701', 'KH241023000004', N'Đã bán', 'KM241025001', '247500')


--INSERT INTO HoaDon(maNhanVien, maKhachHang, soLuong, thanhTien) VALUES  -- Không có mã Khuyến Mãi
--('NV230110001', 'KH241023000001', '2', '481250')
--INSERT INTO Ve(maHoaDon, maLoaiVe, ngayGioXuatVe, maChoNgoi, maChuyenTau, maKhachHang, trangThai, thanhTien ) VALUES
--('HD241023000002', 'LV1', '2024-10-23 07:48:20', 'SEANL0804', 'SP24112701', 'KH241023000001', N'Đã bán', '275000')
--INSERT INTO Ve(maHoaDon, maLoaiVe, ngayGioXuatVe, maChoNgoi, maChuyenTau, maKhachHang, trangThai, thanhTien ) VALUES
--('HD241023000002', 'LV1', '2024-10-23 07:48:20', 'SEANL0805', 'SP24112701', 'KH241023000002', N'Đã bán', '206250')
