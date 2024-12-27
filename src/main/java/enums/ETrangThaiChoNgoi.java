package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ETrangThaiChoNgoi
 * @Tạo vào ngày: 4/10/2024
 * @Tác giả: Huy
 */
public enum ETrangThaiChoNgoi {
    CON_TRONG("Còn trống"),
    DA_DAT("Đã đặt"),
    DANG_CHON("Đang chọn"),
    VE_TAM("Vé tạm");

    private String trangThai;

    ETrangThaiChoNgoi(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }
}
