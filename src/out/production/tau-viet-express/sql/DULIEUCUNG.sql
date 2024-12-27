USE QuanLyBanVeTauHoa
GO

-- CaLam
INSERT INTO CaLam(maCL, tenCL, gioBD, gioKetThuc) VALUES 
	('CA1', N'Ca làm sáng', '07:30:00', '11:30:00' ),
	('CA2', N'Ca làm chiều', '13:00:00', '17:00:00');
-- ChucVu
INSERT INTO ChucVu(maCV, tenCV) VALUES ('NV', N'Nhân viên');
INSERT INTO ChucVu(maCV, tenCV) VALUES ('QL', N'Quản lý');

-- Ga
-- Hà Nội
INSERT INTO Ga (tenGa) VALUES 
	(N'Ga Tàu Hà Nội'),
	(N'Ga Tàu Đồng Văn'),
	(N'Ga Tàu Nam Định'),
	(N'Ga Tàu Ninh Bình'),
	(N'Ga Tàu Thanh Hóa'),
	(N'Ga Tàu Vinh'),
	(N'Ga Tàu Yên Trung'),
	(N'Ga Tàu Đồng Hới'),
	(N'Ga Tàu Đông Hà'),
	(N'Ga Tàu Huế'),
	(N'Ga Tàu Đà Nẵng'),
	(N'Ga Tàu Tam Kỳ'),
	(N'Ga Tàu Quảng Ngãi'),
	(N'Ga Tàu Diêu Trì'),
	(N'Ga Tàu Tuy Hòa'),
	(N'Ga Tàu Nha Trang'),
	(N'Ga Tàu Tháp Chàm'),
	(N'Ga Tàu Bình Thuận (Mương Mán)'),
	(N'Ga Tàu Biên Hòa'),
	(N'Ga Tàu Dĩ An'),
	(N'Ga Tàu Sài Gòn');
                                                         
-- LoaiCho
INSERT INTO LoaiCho (maLC, tenLC, giaCho, moTa) VALUES
	('NC', N'Ghế cứng', 100000, N'Ghế ngồi cứng không điều hòa'),
	('NCL', N'Ghế cứng điều hòa', 120000, N'Ghế ngồi cứng có điều hòa'),
	('NM', N'Ghế mềm', 150000, N'Ghế ngồi mềm không điều hòa'),
	('NML', N'Ghế mềm điều hòa', 170000, N'Ghế ngồi mềm có điều hòa'),
	('ANL', N'Khoang 4 giường', 250000, N'Giường nằm khoang 4 người'),
	('BNL', N'Khoang 6 giường', 220000, N'Giường nằm khoang 6 người'),
	('VIP', N'Khoang VIP', 500000, N'Khoang 2 giường nằm VIP');
-- LoaiToa
INSERT INTO LoaiToa (maLoai, tenLoai) VALUES
	('NC', N'Ghế ngồi cứng thường'),
	('NCL', N'Ghế ngồi cứng có điều hòa'),
	('NM', N'Ghế ngồi mềm thường'),
	('NML', N'Ghế ngồi mềm điều hòa'),
	('ANL', N'Giường nằm khoang 4'),
	('BNL', N'Giường nằm khoang 6'),
	('VIP', N'Giường nằm khoang 2 VIP');
-- LoaiVe
INSERT INTO LoaiVe (maLoai, tenLoai)
VALUES
    ('LV1', N'Vé 1 chiều'),
    ('LV2', N'Vé khứ hồi');

-- Tau
INSERT INTO Tau (maTau, tenTau, trangThai) VALUES 
    ('SE', N'Tàu chạy tuyến Bắc - Nam', N'Hoạt động'),
    ('SP', N'Tàu cao tốc', N'Đang chạy'),
    ('SPT', N'Tàu Phan Thiết - Sài Gòn', N'Đã dừng'),
    ('SNT', N'Tàu chạy tuyến Sài Gòn - Nha Trang', N'Hoạt động');
-- Toa

INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SE', 'NC');   -- Toa 1: Ghế ngồi cứng thường
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SE', 'NC');   -- Toa 2: Ghế ngồi cứng thường
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SE', 'NCL');  -- Toa 3: Ghế ngồi cứng có điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SE', 'NCL');  -- Toa 4: Ghế ngồi cứng có điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SE', 'NM');   -- Toa 5: Ghế ngồi mềm
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SE', 'NM');   -- Toa 6: Ghế ngồi mềm
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SE', 'NML');  -- Toa 7: Ghế ngồi mềm điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (30, 'SE', 'ANL');  -- Toa 8: Giường nằm khoang 4
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (40, 'SE', 'BNL');  -- Toa 9: Giường nằm khoang 6
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (10, 'SE', 'VIP');   -- Toa 10: Giường nằm khoang 2 VIP

-- Tàu SP
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SP', 'NC');   -- Toa 1: Ghế ngồi cứng thường
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SP', 'NC');   -- Toa 2: Ghế ngồi cứng thường
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SP', 'NCL');  -- Toa 3: Ghế ngồi cứng có điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SP', 'NCL');  -- Toa 4: Ghế ngồi cứng có điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SP', 'NM');   -- Toa 5: Ghế ngồi mềm
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SP', 'NM');   -- Toa 6: Ghế ngồi mềm
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SP', 'NML');  -- Toa 7: Ghế ngồi mềm điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (30, 'SP', 'ANL');  -- Toa 8: Giường nằm khoang 4
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (40, 'SP', 'BNL');  -- Toa 9: Giường nằm khoang 6
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (10, 'SP', 'VIP');   -- Toa 10: Giường nằm khoang 2 VIP

-- Tàu SPT
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SPT', 'NC');   -- Toa 1: Ghế ngồi cứng thường
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SPT', 'NC');   -- Toa 2: Ghế ngồi cứng thường
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SPT', 'NCL');  -- Toa 3: Ghế ngồi cứng có điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SPT', 'NCL');  -- Toa 4: Ghế ngồi cứng có điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SPT', 'NM');   -- Toa 5: Ghế ngồi mềm
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SPT', 'NM');   -- Toa 6: Ghế ngồi mềm
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SPT', 'NML');  -- Toa 7: Ghế ngồi mềm điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (30, 'SPT', 'ANL');  -- Toa 8: Giường nằm khoang 4
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (40, 'SPT', 'BNL');  -- Toa 9: Giường nằm khoang 6
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (10, 'SPT', 'VIP');   -- Toa 10: Giường nằm khoang 2 VIP

-- Tàu SNT
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SNT', 'NC');   -- Toa 1: Ghế ngồi cứng thường
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SNT', 'NC');   -- Toa 2: Ghế ngồi cứng thường
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SNT', 'NCL');  -- Toa 3: Ghế ngồi cứng có điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SNT', 'NCL');  -- Toa 4: Ghế ngồi cứng có điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SNT', 'NM');   -- Toa 5: Ghế ngồi mềm
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SNT', 'NM');   -- Toa 6: Ghế ngồi mềm
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (60, 'SNT', 'NML');  -- Toa 7: Ghế ngồi mềm điều hòa
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (30, 'SNT', 'ANL');  -- Toa 8: Giường nằm khoang 4
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (40, 'SNT', 'BNL');  -- Toa 9: Giường nằm khoang 6
INSERT INTO Toa(soLuongCho, maTau, maloaiToa) VALUES (10, 'SNT', 'VIP');  

--Cho Ngoi

DECLARE @i INT = 1;

WHILE @i <= 60
BEGIN
    INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NC', 'SENC01');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NC', 'SENC02');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NCL', 'SENCL03');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NCL', 'SENCL04');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NM', 'SENM05');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NM', 'SENM06');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NML', 'SENML07');

	IF(@i <= 40) BEGIN 
		INSERT INTO ChoNgoi (maloaiCho, maToa) 
		VALUES ('BNL', 'SEBNL09');
		IF(@i <= 30) BEGIN 
			INSERT INTO ChoNgoi (maloaiCho, maToa) 
			VALUES ('ANL', 'SEANL08');
			IF(@i <= 10) BEGIN 
				INSERT INTO ChoNgoi (maloaiCho, maToa) 
				VALUES ('VIP', 'SEVIP10');
			END
		END
	END
    SET @i = @i + 1; -- T?ng bi?n ??m lên 1
END  

------------------------------------------------------------------------------

set @i = 1;

WHILE @i <= 60
BEGIN
    INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NC', 'SPNC01');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NC', 'SPNC02');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NCL', 'SPNCL03');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NCL', 'SPNCL04');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NM', 'SPNM05');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NM', 'SPNM06');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NML', 'SPNML07');

	IF(@i <= 40) BEGIN 
		INSERT INTO ChoNgoi (maloaiCho, maToa) 
		VALUES ('BNL', 'SPBNL09');
		IF(@i <= 30) BEGIN 
			INSERT INTO ChoNgoi (maloaiCho, maToa) 
			VALUES ('ANL', 'SPANL08');
			IF(@i <= 10) BEGIN 
				INSERT INTO ChoNgoi (maloaiCho, maToa) 
				VALUES ('VIP', 'SPVIP10');
			END
		END
	END
    SET @i = @i + 1; -- T?ng bi?n ??m lên 1
END  

---------------------------------------------------------------------------------------
set @i = 1;

WHILE @i <= 60
BEGIN
    INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NC', 'SPTNC01');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NC', 'SPTNC02');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NCL', 'SPTNCL03');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NCL', 'SPTNCL04');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NM', 'SPTNM05');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NM', 'SPTNM06');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NML', 'SPTNML07');

	IF(@i <= 40) BEGIN 
		INSERT INTO ChoNgoi (maloaiCho, maToa) 
		VALUES ('BNL', 'SPTBNL09');
		IF(@i <= 30) BEGIN 
			INSERT INTO ChoNgoi (maloaiCho, maToa) 
			VALUES ('ANL', 'SPTANL08');
			IF(@i <= 10) BEGIN 
				INSERT INTO ChoNgoi (maloaiCho, maToa) 
				VALUES ('VIP', 'SPTVIP10');
			END
		END
	END
    SET @i = @i + 1; -- T?ng bi?n ??m lên 1
END  
----------------------------------------------------------------------
set @i = 1;

WHILE @i <= 60
BEGIN
    INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NC', 'SNTNC01');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NC', 'SNTNC02');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NCL', 'SNTNCL03');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NCL', 'SNTNCL04');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NM', 'SNTNM05');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NM', 'SNTNM06');

	INSERT INTO ChoNgoi (maloaiCho, maToa) 
    VALUES ('NML', 'SNTNML07');

	IF(@i <= 40) BEGIN 
		INSERT INTO ChoNgoi (maloaiCho, maToa) 
		VALUES ('BNL', 'SNTBNL09');
		IF(@i <= 30) BEGIN 
			INSERT INTO ChoNgoi (maloaiCho, maToa) 
			VALUES ('ANL', 'SNTANL08');
			IF(@i <= 10) BEGIN 
				INSERT INTO ChoNgoi (maloaiCho, maToa) 
				VALUES ('VIP', 'SNTVIP10');
			END
		END
	END
    SET @i = @i + 1; -- T?ng bi?n ??m lên 1
END  

--sp_who
--kill 74