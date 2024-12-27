package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: EChucVu
 * @Tạo vào ngày: 4/10/2024
 * @Tác giả: Huy
 */
public enum EChucVu {
    NV("Nhân viên"),
    QL("Quản lý");

    private final String chucVu;

    private EChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getChucVu() {
        return chucVu;
    }
}
