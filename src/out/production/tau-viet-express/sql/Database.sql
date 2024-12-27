USE master;
GO

CREATE DATABASE QuanLyBanVeTauHoa;
GO

USE QuanLyBanVeTauHoa;
GO

------------------------
-- BẢNG CHỨC VỤ
------------------------

CREATE TABLE ChucVu (
    maCV VARCHAR(2) PRIMARY KEY NOT NULL,
    tenCV NVARCHAR(20) NOT NULL
);

ALTER TABLE ChucVu
    ADD CONSTRAINT CK_maCV_Format CHECK (maCV IN ('QL', 'NV'));
GO

------------------------
-- BẢNG TÀI KHOẢN
------------------------


CREATE TABLE TaiKhoan (
    maTK VARCHAR(11) PRIMARY KEY NOT NULL,
    matKhau VARCHAR(60) NOT NULL,
    trangThai NVARCHAR(20) DEFAULT N'Kích hoạt' NOT NULL
);

-- ALTER TABLE TaiKhoan
--     ADD CONSTRAINT CK_matKhau_Format CHECK (
--         LEN(matKhau) >= 8 AND
--         (PATINDEX('%[a-z]%', matKhau) > 0 OR PATINDEX('%[A-Z]%', matKhau) > 0) AND
--         PATINDEX('%[0-9]%', matKhau) > 0 AND
--         PATINDEX('%[!@#$%^&*.]%', matKhau) > 0
--     );

ALTER TABLE TaiKhoan
    ADD CONSTRAINT DF_TaiKhoan_matKhau DEFAULT N'$2a$10$hx.v7Xiy7I8Rpql8ONmMF.WZY3d6pfQmfpp2EgeXJajNJdUa9KVSa' FOR matKhau;
GO
ALTER TABLE TaiKhoan
    ADD CONSTRAINT CK_trangThaiTK CHECK (trangThai IN (N'Kích hoạt', N'Bị khóa'));
GO
------------------------
-- BẢNG CA LÀM
------------------------

CREATE TABLE CaLam (
    maCL VARCHAR(3) PRIMARY KEY NOT NULL,
    tenCL NVARCHAR(20) NOT NULL,
    gioBD TIME NOT NULL,
    gioKetThuc TIME NOT NULL
);

ALTER TABLE CaLam
    ADD CONSTRAINT CK_maCL_Format CHECK (maCL IN ('CA1', 'CA2'));
GO

------------------------
-- BẢNG GA
------------------------

CREATE TABLE Ga (
    maGa int Identity(1,1)  PRIMARY KEY NOT NULL,
    tenGa NVARCHAR(100) NOT NULL
);
GO

------------------------
-- BẢNG NHÂN VIÊN
------------------------

CREATE TABLE NhanVien (
    maNV VARCHAR(11) PRIMARY KEY NOT NULL,
    tenNV NVARCHAR(100) NOT NULL,
	duongDanAnh VarCHAR(355),
    gioiTinh NVARCHAR(3) NOT NULL,
    ngaySinh DATE NOT NULL,
    ngayVaoLam DATE NOT NULL,
    CCCD VARCHAR(12) UNIQUE NOT NULL,
    sdt VARCHAR(12) NOT NULL,
    email VARCHAR(255) NOT NULL,
    diaChi NVARCHAR(255) NOT NULL,
    trangThai NVARCHAR(20) NOT NULL,
    macaLam VARCHAR(3) NOT NULL,
    maTaiKhoan VARCHAR(11) NOT NULL,
    maChucVu VARCHAR(2) NOT NULL,
    FOREIGN KEY (macaLam) REFERENCES CaLam(maCL),
    FOREIGN KEY (maTaiKhoan) REFERENCES TaiKhoan(maTK),
    FOREIGN KEY (maChucVu) REFERENCES ChucVu(maCV)
);

ALTER TABLE NhanVien
    ADD CONSTRAINT CK_maNV_Format CHECK (
        (LEFT(maNV, 2) IN ('NV', 'QL')) AND
        LEN(SUBSTRING(maNV, 3, 6)) = 6 AND
        SUBSTRING(maNV, 3, 6) NOT LIKE '%[^0-9]%' AND
        LEN(RIGHT(maNV, 3)) = 3 AND
        ISNUMERIC(RIGHT(maNV, 3)) = 1
    );

ALTER TABLE NhanVien
    ADD CONSTRAINT CK_gioiTinh_Format CHECK (gioiTinh IN (N'Nam', N'Nữ'));

ALTER TABLE NhanVien
    ADD CONSTRAINT CK_ngaySinh_Format CHECK (DATEDIFF(YEAR, ngaySinh, GETDATE()) >= 18);

ALTER TABLE NhanVien
    ADD CONSTRAINT CK_CCCD_Length CHECK (LEN(CCCD) = 12);

ALTER TABLE NhanVien
    ADD CONSTRAINT CK_sdt_Format CHECK (sdt LIKE '0%');

ALTER TABLE NhanVien
    ADD CONSTRAINT CK_trangThaiNV CHECK (trangThai IN (N'Làm việc', N'Nghỉ làm'));
GO

------------------------
-- BẢNG TÀU
------------------------

CREATE TABLE Tau (
    maTau CHAR(3) PRIMARY KEY,
    tenTau NVARCHAR(50) NOT NULL,
    trangThai NVARCHAR(20)
);

ALTER TABLE Tau
	ADD CONSTRAINT CK_maTau_Format CHECK (maTau IN ('SE', 'SP', 'SPT', 'SNT'));

ALTER TABLE Tau
    ADD CONSTRAINT CK_trangThai_Format CHECK (trangThai IN (N'Hoạt động', N'Đang chạy', N'Đã dừng'));

GO

------------------------
-- BẢNG CHUYẾN TÀU
------------------------

CREATE TABLE ChuyenTau (
    maChuyen VARCHAR(20) PRIMARY KEY,
    maTau CHAR(3) NOT NULL,
    maGaDi int NOT NULL,
    maGaDen int NOT NULL,
    macTau VARCHAR(20),
    ngayGioKhoiHanh DATETIME NOT NULL,
    ngayGioDen DATETIME NOT NULL,
	trangThai NVARCHAR(50) DEFAULT N'Hoạt động',
	FOREIGN KEY (maGaDi) REFERENCES Ga(maGa),
	FOREIGN KEY (maGaDen) REFERENCES Ga(maGa),
    FOREIGN KEY (maTau) REFERENCES Tau(maTau)
);

ALTER TABLE ChuyenTau
    ADD CONSTRAINT CK_ngayGioKhoiHanh_Format CHECK (ngayGioKhoiHanh > getDate());

ALTER TABLE ChuyenTau
    ADD CONSTRAINT CK_ngayGioDen_Format CHECK (ngayGioDen > ngayGioKhoiHanh);

ALTER TABLE ChuyenTau
    ADD CONSTRAINT CK_trangThaiChuyen_Format CHECK (trangThai IN (N'Hoạt động', N'Tạm ngưng', N'Đã dừng'));
GO

------------------------
-- BẢNG LOẠI TOA
------------------------

CREATE TABLE LoaiToa (
    maLoai VARCHAR(10) PRIMARY KEY,
    tenLoai NVARCHAR(50) NOT NULL
);
GO

------------------------
-- BẢNG TOA
------------------------

CREATE TABLE Toa (
    maToa VARCHAR(20) PRIMARY KEY,
    soLuongCho INT CHECK (soLuongCho > 0),
    maTau CHAR(3),
    maloaiToa VARCHAR(10) NOT NULL,
	FOREIGN KEY (maTau) REFERENCES Tau(maTau),
    FOREIGN KEY (maloaiToa) REFERENCES LoaiToa(maLoai)
);

------------------------
-- BẢNG LOẠI CHỖ
------------------------

CREATE TABLE LoaiCho (
    maLC VARCHAR(10) PRIMARY KEY,
    tenLC NVARCHAR(50) NOT NULL UNIQUE,
    giaCho FLOAT CHECK (giaCho > 0),
    moTa NVARCHAR(200) NOT NULL
);
GO

------------------------
-- BẢNG CHỖ NGỒI
------------------------

CREATE TABLE ChoNgoi (
    maCho VARCHAR(20) PRIMARY KEY,
    maloaiCho VARCHAR(10) NOT NULL,
    maToa VARCHAR(20) NOT NULL,
    trangThai NVARCHAR(20) CHECK (trangThai IN (N'Còn trống', N'Đã đặt')) NOT NULL DEFAULT N'Còn trống',
    FOREIGN KEY (maloaiCho) REFERENCES LoaiCho(maLC),
    FOREIGN KEY (maToa) REFERENCES Toa(maToa)
);
GO

------------------------
-- BẢNG KHUYẾN MÃI
------------------------

CREATE TABLE KhuyenMai (
    maKM VARCHAR(15) PRIMARY KEY,
    ngayApDung DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
	doiTuong NVARCHAR(30) NOT NULL,
    phanTramKM FLOAT CHECK (phanTramKM >= 0 AND phanTramKM <= 1),
    CHECK (ngayApDung >= CONVERT(DATE, GETDATE())),
    CHECK (ngayKetThuc > ngayApDung)
);
GO

------------------------
-- BẢNG KHÁCH HÀNG
------------------------
CREATE TABLE KhachHang (
    maKH VARCHAR(20) PRIMARY KEY,
    tenKH NVARCHAR(50) NOT NULL,
    CCCD CHAR(12) NULL UNIQUE,
    sdt CHAR(10) CHECK (sdt LIKE '0%' AND LEN(sdt) = 10) NOT NULL UNIQUE,
    email VARCHAR(100) NULL UNIQUE CHECK (email LIKE '%@%.%' OR email IS NULL),
    ngaySinh DATE CHECK (ngaySinh < GETDATE()) NULL,
    doiTuong NVARCHAR(30) NOT NULL
);
GO

------------------------
-- BẢNG HÓA ĐƠN
------------------------

CREATE TABLE HoaDon (
    maHD VARCHAR(20) PRIMARY KEY,
    ngayGioLapHD DATETIME DEFAULT GETDATE(),
    maNhanVien VARCHAR(11) NOT NULL,
    maKhachHang VARCHAR(20) NOT NULL,
    soLuong INT CHECK (soLuong > 0),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKH)
);
GO

------------------------
-- BẢNG LOẠI VÉ
------------------------

CREATE TABLE LoaiVe (
    maLoai VARCHAR(10) PRIMARY KEY,
    tenLoai NVARCHAR(50) NOT NULL
);
GO

------------------------
-- BẢNG VÉ
------------------------

CREATE TABLE Ve (
    maVe VARCHAR(20) PRIMARY KEY,
    maHoaDon VARCHAR(20) NOT NULL,
    maLoaiVe VARCHAR(10) NOT NULL,
    ngayGioXuatVe DATETIME NOT NULL,
    maChoNgoi VARCHAR(20) NOT NULL,
    maChuyenTau VARCHAR(20) NOT NULL,
    maKhachHang VARCHAR(20) NOT NULL,
    trangThai NVARCHAR(20) CHECK (trangThai IN (N'Đã bán', N'Đã đổi', N'Đã trả', N'Vé được đổi', N'Vé được trả')) NOT NULL,
    thue FLOAT DEFAULT 0.1,
    maKhuyenMai VARCHAR(15),
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHD),
    FOREIGN KEY (maLoaiVe) REFERENCES LoaiVe(maLoai),
    FOREIGN KEY (maChoNgoi) REFERENCES ChoNgoi(maCho),
    FOREIGN KEY (maChuyenTau) REFERENCES ChuyenTau(maChuyen),
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maKhuyenMai) REFERENCES KhuyenMai(maKM)
);
GO

------------------------
-- TRIGGERS
------------------------

-- Trigger tự động sinh mã Vé
CREATE TRIGGER trg_generate_maVe
    ON Ve
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

-- Trigger tự động sinh mã Nhân Viên
CREATE TRIGGER trg_AutoMaNV
    ON NhanVien
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

-- Trigger cập nhật trạng thái Tài Khoản khi Nhân Viên thay đổi trạng thái
CREATE TRIGGER trg_UpdateTaiKhoanOnNhanVienStatusChange
    ON NhanVien
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
---- Trigger tự động sinh mã Chuyến Tàu
CREATE TRIGGER Trigger_maChuyen
    ON ChuyenTau
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
 
-- Kiểm tra và xóa trigger cũ nếu tồn tại
IF OBJECT_ID('Trigger_macTau', 'TR') IS NOT NULL
    DROP TRIGGER Trigger_macTau;
GO

-- Trigger để tự động tạo macTau chẵn hoặc lẻ dựa trên ga đi, ga đến, và ngày khởi hành
CREATE TRIGGER Trigger_macTau
ON ChuyenTau
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



-- Trigger tự động sinh mã Toa
CREATE TRIGGER trg_generate_maToa
    ON Toa
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



-- Trigger tự động sinh mã Chỗ Ngồi
CREATE TRIGGER trg_generate_maCho
    ON ChoNgoi
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

-- Trigger tự động sinh mã Khuyến Mãi
CREATE TRIGGER trg_generate_maKM
    ON KhuyenMai
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

-- Trigger tự động sinh mã Khách Hàng
CREATE TRIGGER trg_generate_maKH
    ON KhachHang
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

-- Trigger tự động sinh mã Hóa Đơn
CREATE TRIGGER trg_generate_maHD
    ON HoaDon
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
