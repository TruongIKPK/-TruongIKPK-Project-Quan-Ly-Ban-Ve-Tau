package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ETrangThaiChuyenTau
 * @Tạo vào ngày: 10/16/2024
 * @Tác giả: Huy
 */
public enum ETrangThaiChuyenTau {
    HOAT_DONG("Hoat dong"),
    TAM_NGUNG("Tam ngung"),
    DA_DUNG("Da dung");

    private final String trangThai;

    ETrangThaiChuyenTau(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }
}
