package enums;

/**
 * @Dự án: tau-viet-express
 * @Enum: ELoaiToa
 * @Tạo vào ngày: 4/10/2024
 * @Tác giả: Huy
 */
public enum ELoaiToa {
    //-NC: Ghế ngồi cứng thường
    //-NCL: Ghế ngồi cứng có điều hoà
    //-NM: Ghế ngồi mềm thường (NM)
    //-NML: Ghế ngồi mềm điều hòa.
    //-ANL: Giường nằm khoang 4 ( ANL)
    //-BNL: Giường nằm khoang 6
    //-VIP: Giường nằm khoang 2 VIP
    NC("Ghế ngồi cứng thường"),
    NCL("Ghế ngồi cứng có điều hòa"),
    NM("Ghế ngồi mềm thường"),
    NML("Ghế ngồi mềm điều hòa"),
    ANL("Giường nằm khoang 4"),
    BNL("Giường nằm khoang 6"),
    VIP("Giường nằm khoang 2 VIP");

    private final String loaiToa;

    private ELoaiToa(String loaiToa) {
        this.loaiToa = loaiToa;
    }

    public String getLoaiToa() {
        return loaiToa;
    }
}
