package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ELoaiVe
 * @Tạo vào ngày: 10/23/2024
 * @Tác giả: Huy
 */
public enum ELoaiVe {
    MOT_CHIEU("Một chiều"),
    KHU_HOI("Khứ hồi");

    private final String value;

    ELoaiVe(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
