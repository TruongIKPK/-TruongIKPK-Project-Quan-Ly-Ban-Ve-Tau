package utils;

/**
 * @Dự án: tau-viet-express
 * @Class: FormatNumber
 * @Tạo vào ngày: 10/23/2024
 * @Tác giả: Huy
 */
public class FormatNumber {
    // Format từ số (xx.xx) thành số có % ở sau (xx% không có đuôi .) ( nhân với 100)
    public static String formatNumberToPercent(double number) {
        return String.format("%.0f", number * 100) + "%";
    }

    // Format từ số có % ở sau (xx% không có đuôi .) thành số (xx.xx) (chia cho 100)
    public static double formatPercentToNumber(String percent) {
        return Double.parseDouble(percent.substring(0, percent.length() - 1)) / 100;
    }
}
