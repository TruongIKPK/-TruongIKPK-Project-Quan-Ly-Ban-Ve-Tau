package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ETrangThaiTau
 * @Tạo vào ngày: 4/10/2024
 * @Tác giả: Huy
 */
public enum ETrangThaiTau {
    HOAT_DONG("Hoạt động"),
    DANG_CHAY("Đang chạy"),
    DA_DUNG("Đã dừng");

    private String trangThai;

    ETrangThaiTau(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }
}
