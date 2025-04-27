package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Dự án: tau-viet-express
 * @Class: FormatDate
 * @Tạo vào ngày: 30/9/2024
 * @Tác giả: Huy
 *
 * Class cung cấp các phương thức để định dạng và chuyển đổi dữ liệu ngày tháng.
 */
public class FormatDate {

    // Định dạng ngày mặc định: dd-MM-yyyy
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Chuyển đổi chuỗi ngày từ định dạng yyyy-MM-dd sang dd/MM/yyyy.
     * @param date Chuỗi ngày định dạng yyyy-MM-dd.
     * @return Chuỗi ngày định dạng dd/MM/yyyy.
     */
    public static String formatYMDToDMY(String date) {
        String[] arr = date.split("-");
        return arr[2] + "/" + arr[1] + "/" + arr[0];
    }

    /**
     * Chuyển đổi chuỗi ngày từ định dạng dd/MM/yyyy sang LocalDate (yyyy-MM-dd).
     * @param date Chuỗi ngày định dạng dd/MM/yyyy.
     * @return Đối tượng LocalDate.
     */
    public static LocalDate formatDMYtoYMD(String date) {
        String[] arr = date.split("/");
        return LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));
    }

    /**
     * Chuyển đổi chuỗi ngày từ định dạng dd-MM-yyyy sang LocalDate.
     * @param date Chuỗi ngày định dạng dd-MM-yyyy.
     * @return Đối tượng LocalDate.
     */
    public static LocalDate formatStrToLocalDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    /**
     * Chuyển đổi chuỗi ngày từ định dạng dd/MM/yyyy sang LocalDate.
     * @param date Chuỗi ngày định dạng dd/MM/yyyy.
     * @return Đối tượng LocalDate.
     */
    public static LocalDate formatStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Chuyển đổi chuỗi ngày từ định dạng yyyy-MM-dd sang LocalDate.
     * @param date Chuỗi ngày định dạng yyyy-MM-dd.
     * @return Đối tượng LocalDate.
     */
    public static LocalDate formatYMDToLocalDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * Chuyển đổi chuỗi ngày từ định dạng yyyy-MM-dd sang dd-MM-yyyy.
     * @param date Chuỗi ngày định dạng yyyy-MM-dd.
     * @return Chuỗi ngày định dạng dd-MM-yyyy.
     */
    public static String formatYMDtoDMY(String date) {
        return date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date.substring(0, 4);
    }

    /**
     * Chuyển đổi chuỗi ngày từ định dạng yyyy/MM/dd sang LocalDate.
     * @param date Chuỗi ngày định dạng yyyy/MM/dd.
     * @return Đối tượng LocalDate.
     */
    public static LocalDate formatYMDtoLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Chuyển đổi LocalDate sang chuỗi định dạng dd/MM/yyyy.
     * @param date Đối tượng LocalDate.
     * @return Chuỗi ngày định dạng dd/MM/yyyy.
     */
    public static String formatLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    /**
     * Chuyển đổi LocalDateTime sang chuỗi định dạng HH:mm dd/MM/yyyy.
     * @param time Đối tượng LocalDateTime.
     * @return Chuỗi định dạng HH:mm dd/MM/yyyy.
     */
    public static String formatLocaldatetimeToString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        return time.format(formatter);
    }

    /**
     * Chuyển đổi LocalDateTime sang chuỗi định dạng HH:mm dd/MM.
     * @param time Đối tượng LocalDateTime.
     * @return Chuỗi định dạng HH:mm dd/MM.
     */
    public static String formatLocalDateTimeToHM(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM");
        return time.format(formatter);
    }

    /**
     * Chuyển đổi chuỗi định dạng HH:mm:ss dd/MM/yyyy sang LocalDateTime.
     * @param time Chuỗi định dạng HH:mm:ss dd/MM/yyyy.
     * @return Đối tượng LocalDateTime.
     */
    public static LocalDateTime formatStringToLocaldatetime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Chuyển đổi chuỗi định dạng HH:mm:ss dd-MM-yyyy sang LocalDateTime.
     * @param time Chuỗi định dạng HH:mm:ss dd-MM-yyyy.
     * @return Đối tượng LocalDateTime.
     */
    public static LocalDateTime formatStringToLocaldatetime2(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Chuyển đổi chuỗi định dạng HH:mm dd-MM-yyyy sang LocalDateTime.
     * @param time Chuỗi định dạng HH:mm dd-MM-yyyy.
     * @return Đối tượng LocalDateTime.
     */
    public static LocalDateTime formatHhMmToLocalDateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Chuyển đổi LocalDate sang chuỗi định dạng yyyy-MM-dd.
     * @param date Đối tượng LocalDate.
     * @return Chuỗi định dạng yyyy-MM-dd.
     */
    public static String formatLocalDateToYMD(LocalDate date) {
        return date.toString();
    }

    /**
     * Chuyển đổi LocalDate sang chuỗi định dạng dd-MM-yyyy.
     * @param date Đối tượng LocalDate.
     * @return Chuỗi định dạng dd-MM-yyyy.
     */
    public static String formatLocalDateToDMY(LocalDate date) {
        return date.format(formatter);
    }

    /**
     * Chuyển đổi LocalDateTime sang chuỗi định dạng dd-MM-yyyy.
     * @param date Đối tượng LocalDateTime.
     * @return Chuỗi định dạng dd-MM-yyyy.
     */
    public static String formatLocalDateTimeToDMY(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }
}
