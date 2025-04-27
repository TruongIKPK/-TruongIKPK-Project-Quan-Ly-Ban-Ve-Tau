package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Dự án: tau-viet-express
 * @Class: Validation
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class Validation {
    public static boolean maTK(String maTK) { return maTK.matches(Regex.MA_TK.getRegex()); }
    public static boolean maNV(String maNV) { return maNV.matches(Regex.MA_NV.getRegex()); }
    public static boolean maKM(String maKM) { return maKM.matches(Regex.MA_KM.getRegex()); }
    public static boolean maVe(String maVe) { return maVe.matches(Regex.MA_VE.getRegex()); }
    public static boolean maLV(String maLV) { return maLV.matches(Regex.MA_LV.getRegex()); }
    public static boolean maCho(String maCho) { return maCho.matches(Regex.MA_CHO.getRegex()); }
    public static boolean maToa(String maToa) { return maToa.matches(Regex.MA_TOA.getRegex()); }
    public static boolean maHD(String maHD) { return maHD.matches(Regex.MA_HD.getRegex()); }
    public static boolean maKH(String maKH) { return maKH.matches(Regex.MA_KH.getRegex()); }
    public static boolean maChuyen(String maChuyen) { return maChuyen.matches(Regex.MA_CHUYEN.getRegex()); }
    public static boolean email(String email) { return email.matches(Regex.EMAIL.getRegex()); }
    public static boolean password(String password) {
        return password.matches(Regex.PASSWORD.getRegex());
    }
    public static boolean sdt(String sdt) { return sdt.matches(Regex.SDT.getRegex()); }
    public static boolean CCCD(String CCCD) { return CCCD.matches(Regex.CCCD.getRegex()); }
    public static boolean hoTen(String hoTen) { return hoTen.matches(Regex.HOTEN.getRegex()); }
    // Kiểm tra ngày đi
    public static boolean ngayGioDi(LocalDateTime ngayGioDi) {
        if (ngayGioDi.isAfter(LocalDateTime.now())) {
            return false;
        }

        return true;
    }
    // ngay sinh tuoi phai lon hon 18
    public static boolean validateAge(LocalDate ngaySinh) {
        if (ngaySinh.isAfter(LocalDate.now().minusYears(18))) {
            return false;
        }

        return true;
    }

}

