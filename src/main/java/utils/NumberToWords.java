package utils;

/**
 * @Dự án: tau-viet-express
 * @Class: Thai
 * @Tạo vào ngày: 12/13/2024
 * @Tác giả: Thai
 */
public class NumberToWords {

    private static final String[] UNITS = {"", "mươi", "trăm"};
    private static final String[] TENS = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    private static final String[] BIG_UNITS = {"", "nghìn", "triệu", "tỷ"};

    public static String convertNumberToWords(long number) {
        if (number == 0) {
            return "không";
        }

        StringBuilder words = new StringBuilder();
        int groupIndex = 0;
        boolean needConnector = false;

        while (number > 0) {
            int group = (int) (number % 1000);
            number /= 1000;

            if (group > 0) {
                String groupWords = convertGroupToWords(group);

                if (needConnector && groupWords.length() > 0) {
                    words.insert(0, " lẻ ");
                }

                if (groupIndex > 0 && groupWords.length() > 0) {
                    words.insert(0, " " + BIG_UNITS[groupIndex] + " ");
                }
                words.insert(0, groupWords);
            }

            groupIndex++;
            needConnector = group > 0 && number > 0;
        }
//        viet hoa chu cai dau
        words.setCharAt(0, Character.toUpperCase(words.charAt(0)));

        return words.toString().trim();
    }

    public static String convertGroupToWords(int group) {
        StringBuilder groupWords = new StringBuilder();

        int hundreds = group / 100;
        int tens = (group % 100) / 10;
        int units = group % 10;

        if (hundreds > 0) {
            groupWords.append(TENS[hundreds]).append(" ").append(UNITS[2]).append(" ");
        }

        if (tens > 0 || units > 0) {
            if (tens > 0) {
                if (tens == 1) {
                    groupWords.append(UNITS[1]).append(" ");
                } else {
                    groupWords.append(TENS[tens]).append(" ").append(UNITS[1]).append(" ");
                }
            } else if (hundreds > 0 && units > 0) {
                groupWords.append("lẻ ");
            }

            if (units > 0) {
                if (units == 1 && tens > 1) {
                    groupWords.append("mốt");
                } else if (units == 5 && tens > 0) {
                    groupWords.append("lăm");
                } else {
                    groupWords.append(TENS[units]);
                }
            }
        }

        return groupWords.toString().trim();
    }

    public static void main(String[] args) {
        long number = 123456789;
        String words = convertNumberToWords(number);
        System.out.println("Số " + number + " được đọc là: " + words);
    }
}

