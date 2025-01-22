package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ETrangThaiVe
 * @Tạo vào ngày: 4/10/2024
 * @Tác giả: Huy
 */
public enum ETrangThaiVe {
    DA_BAN("Đã bán"),
    DA_DOI("Đã đổi"),
    DA_TRA("Đã trả"),
    VE_DUOC_DOI("Vé được đổi");
    private String trangThai;

    private ETrangThaiVe(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }
}
