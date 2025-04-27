package utils;

import entity.HoaDon;
import entity.Ve;

import java.util.ArrayList;

/**
 * @Dự án: tau-viet-express
 * @Class: GenHTML
 * @Tạo vào ngày: 12/12/2024
 * @Tác giả: Thai
 */
public class GenHTML {
    public static String generateHtmlTicket(Ve ve) {
        String html = "<!DOCTYPE html>" +
                "<html lang='vi'>" +
                "<head>" +
                "<meta charset='UTF-8'/>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'/>" +
                "<style>" +
                "body { background-color: #ffffff; margin: 0; padding: 0; font-family: 'Arial', sans-serif;; color: #333; font-size: 12px; }" +
                ".container { display: flex; justify-content: center; padding: 10px; }" +
                ".content {  background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 15px; margin: 0 auto; }" +
                ".header { display: flex; justify-content: space-between; align-items: center; background-color: #000000; color: #ffffff; padding: 10px 15px; border-radius: 8px; }" +
                ".header h1 { font-size: 14px; font-weight: bold; margin: 0; }" +
                ".header p { font-size: 12px; font-weight: bold; margin: 0; text-align: right; }" +
                ".main-content { font-size: 12px; line-height: 1.4; margin: 10px 0; }" +
                ".ticket-details { background-color: #f9f9f9; border: 1px solid #dddddd; padding: 10px; border-radius: 8px; margin-top: 15px; font-size: 12px; }" +
                ".ticket-details span { display: block; margin-bottom: 8px; }" +
                ".ticket-details .highlight { color: #0056b3; font-weight: bold; }" +
                ".ticket-details .important { color: #d700df; font-weight: bold; }" +
                ".ticket-details .italic { font-style: italic; color: #777; }" +
                ".footer { text-align: center; font-size: 12px; font-weight: bold; padding: 10px; background-color: #f5f5f5; border-radius: 8px; margin-top: 15px; }" +

                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div class='content'>" +
                "<div class='header'>" +
                "<div><h1>Tàu Việt Express</h1></div>" +
                "<div><p>THẺ LÊN TÀU HỎA<br />BOARDING PASS</p></div>" +
                "</div>" +
                "<div class='main-content'>" +
                "<p>Kính gửi quý khách hàng!<br />Xin trân trọng cảm ơn quý khách đã lựa chọn sử dụng dịch vụ vận tải hành khách của Tổng công ty Đường sắt Tàu Việt Express. Quý khách đã thực hiện mua vé thành công với thông tin như sau:</p>" +
                "</div>" +
                "<div class='ticket-details'>" +
                "<span class='highlight'>Mã đặt chỗ: </span><span>" + ve.getMaVe() + "</span><br />" +
                "<span class='important'>Thông tin hành trình:</span><br />" +
                "<span>Ga đi - Ga đến: " + ve.getChuyenTau().getGaDi().getTenGa() + " --> " + ve.getChuyenTau().getGaDen().getTenGa() + "</span><br />" +
                "<span>Tàu &ndash;&nbsp;Khi mua v&eacute; cần cung cấp đủ th&ocirc;ng tin về người mua v&eacute;, th&ocirc;ng tin về h&agrave;nh kh&aacute;ch đi t&agrave;u bao gồm: Họ v&agrave; t&ecirc;n đầy đủ, giấy tờ t&ugrave;y th&acirc;n c&oacute; ảnh hợp lệ được ph&aacute;p luật c&ocirc;ng nhận (Số chứng minh thư nh&acirc;n d&acirc;n | Số bằng l&aacute;i xe được ph&aacute;p luật c&ocirc;ng nhận | Số hộ chiếu): " + ve.getChuyenTau().getMaChuyen() + "</span><br />" +
                "<span>Ngày đi: " + ve.getNgayGioXuatVe().toLocalDate() + "</span><br />" +
                "<span>Giờ đi: " + ve.getNgayGioXuatVe().toLocalTime() + "</span><br />" +
                "<span>Toa: " + ve.getChoNgoi().getToa().getMaToa() + "</span><br />" +
                "<span>Chỗ: " + ve.getChoNgoi().getMaCho() + " (Ghế mềm điều hóa)</span><br />" +
                "</div><br />" +
                "<div class='ticket-details'>" +
                "<span class='important'>Thông tin hành khách:</span><br />" +
                "<span>Họ tên: " + ve.getKhachHang().getTenKH() + "</span><br />" +
                "<span>Giấy tờ: " + ve.getKhachHang().getCCCD() + "</span><br />" +
                "<span>Loại vé: " + ve.getLoaiVe().getTenLV() + "</span><br />" +
                "<span>Giá vé: " + ve.getThanhTien() + " VNĐ</span><br />" +
                "<span class='italic'>(Giá vé trên đã có bảo hiểm và 10% thuế GTGT)</span><br />" +
                "</div>" +
                "<p>- Mã số tra cứu hóa đơn: " + ve.getHoaDon().getMaHD() + " ( Mã hóa đơn chứa vé này )</p><br />" +
                "<p>Để đảm bảo quyền lợi của mình, quý khách vui lòng mang theo vé điện tử hoặc vé giấy cùng với giấy tờ tùy thân ghi trong vé điện tử trong suốt thời gian hành trình và xuất trình cho nhân viên soát vé khi có yêu cầu.</p><br />" +
                "<div class='footer'><p>Chúc quý khách có một chuyến đi vui vẻ!!!</p></div>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
        return html;
    }

    public static String generateHtmlTickets(ArrayList<Ve> danhSachVe) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>")
                .append("<html lang='vi'>")
                .append("<head>")
                .append("<meta charset='UTF-8'/>")
                .append("<meta name='viewport' content='width=device-width, initial-scale=1.0'/>")
                .append("<style>")
                .append("body { background-color: #ffffff; margin: 0; padding: 0; font-family: 'Arial', sans-serif; color: #333; font-size: 12px; }")
                .append(".container { display: flex; justify-content: center; padding: 10px; }")
                .append(".content { background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 15px; margin: 0 auto; }")
                .append(".page-break { page-break-before: always; }")
                .append(".header { display: flex; justify-content: space-between; align-items: center; background-color: #000000; color: #ffffff; padding: 10px 15px; border-radius: 8px; }")
                .append(".header h1 { font-size: 14px; font-weight: bold; margin: 0; }")
                .append(".header p { font-size: 12px; font-weight: bold; margin: 0; text-align: right; }")
                .append(".main-content { font-size: 12px; line-height: 1.4; margin: 10px 0; }")
                .append(".ticket-details { background-color: #f9f9f9; border: 1px solid #dddddd; padding: 10px; border-radius: 8px; margin-top: 15px; font-size: 12px; }")
                .append(".ticket-details span { display: block; margin-bottom: 8px; }")
                .append(".footer { text-align: center; font-size: 12px; font-weight: bold; padding: 10px; background-color: #f5f5f5; border-radius: 8px; margin-top: 15px; }")
                // CSS cho chế độ in
                .append("@media print { ")
                .append("    body { font-size: 12px; margin: 0; } ")
                .append("    .container { width: 100%; padding: 0; } ")
                .append("    .content { width: 100%; padding: 20px; box-shadow: none; } ")
                .append("    .page-break { page-break-before: always; } ")
                .append("} ")
                .append("</style>")
                .append("</head>")
                .append("<body>");

        int width = 100; // Khởi tạo giá trị ban đầu cho width
        int maxWidth = 100; // Giới hạn width tối đa

        for (Ve ve : danhSachVe) {
            // Kiểm tra dữ liệu null để tránh NullPointerException
            if (ve == null || ve.getChuyenTau() == null || ve.getKhachHang() == null || ve.getChoNgoi() == null) {
                continue; // Bỏ qua vé nếu thiếu dữ liệu quan trọng
            }

            // Tăng width mỗi lần, nhưng đảm bảo không vượt quá 100%
            if (width + 10 <= maxWidth) {
                width += 10; // Tăng lên 10% cho mỗi vé
            } else {
                width = maxWidth; // Đảm bảo width không vượt quá 100%
            }

            html.append("<div class='container' style='width: ").append(width).append("%;'>")
                    .append("<div class='content'>")
                    .append("<div class='header'>")
                    .append("<div><h1>Tàu Việt Express</h1></div>")
                    .append("<div><p>THẺ LÊN TÀU HỎA<br />BOARDING PASS</p></div>")
                    .append("</div>")
                    .append("<div class='main-content'>")
                    .append("<p>Kính gửi quý khách hàng!<br />Xin trân trọng cảm ơn quý khách đã lựa chọn sử dụng dịch vụ vận tải hành khách của Tổng công ty Đường sắt Tàu Việt Express. Quý khách đã thực hiện mua vé thành công với thông tin như sau:</p>")
                    .append("</div>")
                    .append("<div class='ticket-details'>")
                    .append("<span class='highlight'>Mã đặt chỗ: </span><span>").append(ve.getMaVe()).append("</span><br />")
                    .append("<span class='important'>Thông tin hành trình:</span><br />")
                    .append("<span>Ga đi - Ga đến: ").append(ve.getChuyenTau().getGaDi().getTenGa()).append(" --> ").append(ve.getChuyenTau().getGaDen().getTenGa()).append("</span><br />")
                    .append("<span>Tàu: ").append(ve.getChuyenTau().getMaChuyen()).append("</span><br />")
                    .append("<span>Ngày đi: ").append(ve.getNgayGioXuatVe().toLocalDate()).append("</span><br />")
                    .append("<span>Giờ đi: ").append(ve.getNgayGioXuatVe().toLocalTime()).append("</span><br />")
                    .append("<span>Toa: ").append(ve.getChoNgoi().getToa().getMaToa()).append("</span><br />")
                    .append("<span>Chỗ: ").append(ve.getChoNgoi().getMaCho()).append(" (Ghế mềm điều hóa)</span><br />")
                    .append("</div><br />")
                    .append("<div class='ticket-details'>")
                    .append("<span class='important'>Thông tin hành khách:</span><br />")
                    .append("<span>Họ tên: ").append(ve.getKhachHang().getTenKH()).append("</span><br />")
                    .append("<span>Giấy tờ: ").append(ve.getKhachHang().getCCCD()).append("</span><br />")
                    .append("<span>Loại vé: ").append(ve.getLoaiVe().getTenLV()).append("</span><br />")
                    .append("<span>Giá vé: ").append(ve.getThanhTien()).append(" VNĐ</span><br />")
                    .append("<span class='italic'>(Giá vé trên đã có bảo hiểm và 10% thuế GTGT)</span><br />")
                    .append("</div>")
                    .append("<div class='page-break'></div>");
        }
        html.append("</body></html>");
        return html.toString();
    }



    public static String generateHtmlBill(HoaDon hoaDon) {
        // Lấy danh sách vé từ hóa đơn
        ArrayList<Ve> dsVe = hoaDon.getDanhSachVe();

        // Tạo HTML cho hóa đơn
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>")
                .append("<html lang='vi'>")
                .append("<head>")
                .append("<meta charset='UTF-8'/>")
                .append("<meta name='viewport' content='width=device-width, initial-scale=1.0'/>")
                .append("<style>")
                .append("body { background-color: #ffffff; margin: 0; padding: 0; font-family: 'Arial', sans-serif; color: #333; font-size: 12px; }")
                .append(".container { width: 90%; margin: 0 auto; padding: 20px; }")
                .append(".content { background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 20px; margin-top: 20px; }")
                .append(".header { display: flex; justify-content: space-between; align-items: center; background-color: #000000; color: #ffffff; padding: 10px 15px; border-radius: 8px; }")
                .append(".header h1 { font-size: 18px; font-weight: bold; margin: 0; }")
                .append(".header p { font-size: 14px; font-weight: bold; margin: 0; text-align: right; }")
                .append(".main-content { font-size: 14px; line-height: 1.6; margin-top: 15px; }")
                .append(".item-table { width: 100%; border-collapse: collapse; margin-top: 20px; }")
                .append(".item-table th, .item-table td { padding: 10px; text-align: left; border: 1px solid #ddd; }")
                .append(".item-table th { background-color: #f2f2f2; }")
                .append(".total { font-size: 14px; font-weight: bold; text-align: right; margin-top: 20px; }")
                .append(".footer { text-align: center; font-size: 12px; font-weight: bold; padding: 10px; background-color: #f5f5f5; border-radius: 8px; margin:auto }")
                .append("</style>")
                .append("</head>")
                .append("<body>")//body start
                .append("<div class='container'>")//container start
                .append("<div class='content'>")//content start
                .append("<div class='header'>")
                .append("<div><h1>Tàu Việt Express</h1></div>")
                .append("<div><p>HÓA ĐƠN MUA VÉ</p></div>")
                .append("</div>")

                .append("<div class='main-content'>")//main-content start
                .append("<p><strong>Ngày: </strong>").append(hoaDon.getNgayGioLapHD()).append("</p>")
                .append("<p><strong>Thông tin Công ty: </strong>Tàu việt Express</p>")
                .append("<p><strong>Mã hóa đơn: </strong>").append(hoaDon.getMaHD()).append("</p>")
                .append("<p><strong>Họ tên: </strong>").append(hoaDon.getKhachHang().getTenKH()).append("</p>")
                .append("<p><strong>Đối tượng: </strong>").append(hoaDon.getKhachHang().getDoiTuong()).append("</p>")
                .append("<p><strong>Số điện thoại: </strong>").append(hoaDon.getKhachHang().getSdt()).append("</p>")
                .append("</div>")// kết thúc main-content

                .append("<table class='item-table'>")
                .append("<thead>")
                .append("<tr>")
                .append("<th>Mã vé</th>")
                .append("<th>Loại vé</th>")
                .append("<th>Chỗ ngồi</th>")
                .append("<th>Chuyến tàu</th>")
                .append("<th>Ga đi</th>")
                .append("<th>Ga đến</th>")
                .append("<th>Giá</th>")
                .append("<th>Khuyến mãi</th>")
                .append("<th>Thành tiền</th>")
                .append("</tr>")
                .append("</thead>")
                .append("<tbody>");

        // Duyệt qua danh sách vé và hiển thị thông tin vé trong bảng
        double totalAmount = 0;
        for (Ve ve : dsVe) {
            double thanhTien = ve.getThanhTien(); // Assuming getThanhTien() returns the total price for the ticket
            totalAmount += thanhTien;

            htmlContent.append("<tr>")
                    .append("<td>").append(ve.getMaVe()).append("</td>")
                    .append("<td>").append(ve.getLoaiVe().getTenLV()).append("</td>")
                    .append("<td>").append(ve.getChoNgoi().getMaCho()).append("</td>")
                    .append("<td>").append(ve.getChuyenTau().getMaChuyen()).append("</td>")
                    .append("<td>").append(ve.getChuyenTau().getGaDi().getTenGa()).append("</td>")
                    .append("<td>").append(ve.getChuyenTau().getGaDen().getTenGa()).append("</td>")
                    .append("<td>").append(ve.getThanhTien()).append(" VND</td>")
                    .append("<td>").append(ve.getKhuyenMai().getPhanTramKM()).append("%</td>")
                    .append("<td>").append(thanhTien).append(" VND</td>")
                    .append("</tr>");
        }

        // Hiển thị tổng tiền
        htmlContent.append("</tbody>")
                .append("</table>")// kết thúc table
                .append("<div class='total'>")
                .append("<p><strong>Tổng tiền: </strong>").append(totalAmount).append(" VND</p>")
                .append("</div>");


        // thêm cái số tiền bằng chữ tiếng việt bằng hàm convertNumberToWords
        htmlContent.append("<p><strong>Tổng tiền bằng chữ: </strong>").append(NumberToWords.convertNumberToWords((long) totalAmount)).append(" đồng</p>");

        //ghi chú ,:..............
        // ký tến người bán bên trái
        // ký tên người mua bên phải
        htmlContent.append("<p><strong>Ghi chú:</strong> .......................................</p>")
                .append("<table style='width: 100%; border-collapse: collapse;'>")
                .append("<tr>")
                .append("<td style='text-align: center; width: 50%; padding: 20px;'>")
                .append("<p><strong>Ký tên người bán</strong></p>")
                .append("<p>(Ký và ghi rõ họ tên)</p>")
                .append("</td>")
                .append("<td style='text-align: center; width: 50%; padding: 20px;'>")
                .append("<p><strong>Ký tên người mua</strong></p>")
                .append("<p>(Ký và ghi rõ họ tên)</p>")
                .append("</td>")
                .append("</tr>")
                .append("</table>")


                .append("</div>") // Kết thúc content
                .append("</div>") // Kết thúc container

                .append("<p style='text-align:center;'>Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!</p>")

                .append("</body>")
                .append("</html>");

        return htmlContent.toString();
    }
    // tai sao nguoi ban va nguoi mua lai ở gần nhâu mà float không có tác dungj?


}
