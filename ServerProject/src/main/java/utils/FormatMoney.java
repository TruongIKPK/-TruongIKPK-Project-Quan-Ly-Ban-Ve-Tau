package utils;

import java.text.DecimalFormat;

/**
 * @Dự án: tau-viet-express
 * @Class: FormatMoney
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 */
public class FormatMoney {
    public static String format(double money) {
        DecimalFormat df = new DecimalFormat("#,###đ");
        return df.format(money);
    }

    // Format ngược từ String sang double
    public static double format(String money) {
        return Double.parseDouble(money.replaceAll("[^\\d.]", ""));
    }

    // Format double to String
    public static String formatDouble(double money) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(money);
    }
}
