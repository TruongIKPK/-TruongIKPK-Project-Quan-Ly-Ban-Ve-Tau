package utils;

/**
 * @Dự án: tau-viet-express
 * @Class: Regex
 * @Tạo vào ngày: 10/4/2024
 * @Tác giả: Huy
 */
public enum Regex {
    // Mã tài khoản phải có 2 ký tự đầu chức vụ + ngày vào làm + %03d.
    MA_TK("^(QL|NV)\\d{9}$"),
    // Mã nhân viên phải có 2 ký tự đầu chức vụ + năm + tháng + ngày vào làm + %03d
    MA_NV("^(QL|NV)\\d{11}$"),
    // Mã khuyến mãi gồm KM+ năm + tháng + ngày + %03d
    MA_KM("^(KM)\\d{11}$"),
    // Mã vé gồm VE + năm + tháng + ngày + %06d
    MA_VE("^(VE)\\d{14}$"),
    // Mã loại vé LV + 1 số
    MA_LV("^(LV)\\d{1}$"),
    // Mã chỗ gồm mã toa ( nhiều ký tự bao gồm số) + 2 số
    MA_CHO("^[A-Z0-9]+\\d{2}$"),
    // Mã toa gồm 2 ký tự đầu + nhiều ký tự bao gồm số + 2 số
    MA_TOA("^[A-Z]{2}[A-Z0-9]+\\d{2}$"),
    // Mã hóa đơn gồm HD + năm + tháng + ngày + %06d
    MA_HD("^(HD)\\d{14}$"),
    // Mã khách hàng gồm KH + năm + tháng + ngày + %06d
    MA_KH("^(KH)\\d{12}$"),
    // Chuyến tàu Mã tàu + ngày + tháng + năm + %2d
    MA_CHUYEN("^[A-Z]{2,4}\\d{8}$"),
    // Định dạng email
    EMAIL("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"),
    // Số điện thoại Không được rỗng, phải bắt đầu bằng 0 và có 10 chữ số
    SDT("^0\\d{9}$"),

    // Ít nhất 8 ký tự, có ít nhất 1 chữ in hoa, có ít nhất 1 trong các ký tự đặt biệt, có ít nhất 1 chữ số.
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$"),

    // Định dạng CCCD phải có 12 số
    CCCD("^[0-9]{12}$"),

    HOTEN("^[a-zA-Z\\s]+");

    private final String regex;

    private Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
