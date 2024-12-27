package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ECaLam
 * @Tạo vào ngày: 4/10/2024
 * @Tác giả: Huy
 */
public enum ECaLam {
    CA1("Ca làm sáng"),
    CA2("Ca làm chiều");

    private String caLam;

    private ECaLam(String caLam) {
        this.caLam = caLam;
    }

    public String getCaLam() {
        return caLam;
    }
}
