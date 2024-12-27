package utils;

/**
 * @Dự án: tau-viet-express
 * @Class: FormatString
 * @Tạo vào ngày: 12/6/2024
 * @Tác giả: Huy
 */
public class FormatString {
    // Phương thức tóm tắt tên
    public static String tomTatTen(String tenDayDu) {
        // Tách tên đầy đủ thành các phần
        String[] cacPhan = tenDayDu.trim().split("\\s+");
        if (cacPhan.length < 2) {
            return tenDayDu; // Trả về tên nếu chỉ có một phần
        }

        // Lấy tên cuối cùng (họ chính)
        String tenChinh = cacPhan[cacPhan.length - 1];

        // Lấy chữ cái đầu của các tên trước đó
        StringBuilder tomTat = new StringBuilder();
        for (int i = 0; i < cacPhan.length - 1; i++) {
            tomTat.append(cacPhan[i].charAt(0)).append(".");
        }

        // Ghép chữ tắt với tên chính
        return tomTat.toString() + " " + tenChinh;
    }
}
