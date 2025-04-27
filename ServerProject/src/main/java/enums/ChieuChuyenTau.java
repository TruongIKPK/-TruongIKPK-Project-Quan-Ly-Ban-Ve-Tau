package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ChieuChuyenTau
 * @Tạo vào ngày: 10/25/2024
 * @Tác giả: Huy
 */
public enum ChieuChuyenTau {
    CHIEU_DI("Chiều đi"),
    CHIEU_VE("Chiều về");

    private final String value;

    ChieuChuyenTau(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
