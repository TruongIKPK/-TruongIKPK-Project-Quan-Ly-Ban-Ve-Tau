package enums;

import java.awt.*;

/**
 * @Dự án: tau-viet-express
 * @Enum: EColor
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public enum EColor {
    TITLE_BAR_COLOR(153, 224, 244), // Màu xanh dương

    // GHE
    GHE_TRONG(255, 255, 255), // Trắng
    GHE_DANG_CHON(115, 251, 253), // Màu xanh dương
    GHE_DA_BAN(235, 51, 36), // Màu đỏ
    GHE_VE_TAM(255, 127, 39), // Màu cam

    // TOA
    SL_CHO_TRONG(31, 158, 68), // Màu xanh lá
    IT_CHO_TRONG(255, 127, 39), // Màu cam

    BG_COLOR(255, 255, 255), // Màu trắng
    BG_COLOR_2(115, 251, 253), // Màu xanh dương
    BG_COLOR_3(128, 128, 128), // Màu xám
    TITLE_COLOR(0, 162, 232), // Màu xanh dương
    TEXT_COLOR(0, 0, 0), // Màu đen

    BTN_BG_COLOR(30, 174, 253), // Màu xanh dương
    BTN_SEC_COLOR(255, 255, 255),
    BTN_TEXT_COLOR(255, 255, 255),
    BTN_ACTIVE_BG_COLOR(173, 173, 173),

    ACTIVE_BG_COLOR(44, 230, 99), // Màu xanh lá

    BORDER_COLOR(189, 189, 189),
    TAB_BG_COLOR(234, 234, 234),

    WARNING_COLOR(255, 88, 88), // Màu đỏ
    DISABLED_COLOR(189, 189, 189);

    private int r;
    private int g;
    private int b;

    EColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public Color getColor() {
        return new Color(r, g, b);
    }

    public Color getDecodeColor() {
        return Color.decode("#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b));
    }
}
