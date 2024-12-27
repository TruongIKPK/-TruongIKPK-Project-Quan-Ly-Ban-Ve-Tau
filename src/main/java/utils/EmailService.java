package utils;

import com.itextpdf.text.DocumentException;
import entity.HoaDon;
import entity.Ve;

import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static utils.GenHTML.generateHtmlBill;
import static utils.GenHTML.generateHtmlTicket;
import static utils.ImageToPdf.convertImageToPdf;
import static utils.RenderHtmlToImage.renderHtmlToImage;

public class EmailService {

    private final String username = "tauvietexpress@gmail.com"; // Địa chỉ email của bạn
    private final String password = "ldqi howc kcqu xjvc"; // Mật khẩu ứng dụng Gmail

    public boolean sendOTPEmail(String recipient, int otp) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Mã OTP xác thực");
            message.setText("Mã OTP của bạn là: " + otp);

            // Gửi email
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailWithAttachment(String recipient, String subject, String body, String filePath) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);

            // Tạo nội dung email
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);

            // Đính kèm file
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(filePath));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to send ticket via email
    public static void sendTicketByEmail(Ve ve) {
        String email = ve.getKhachHang().getEmail();
        if (email != null && !email.trim().isEmpty()) {
            try {
                String htmlContent = generateHtmlTicket(ve);
                BufferedImage image = renderHtmlToImage(htmlContent);

                // Create a PDF file from the HTML content
                File tempFile = convertImageToPdf(image);


                // Use the EmailService to send the email with the PDF attachment
                EmailService emailService = new EmailService();
                String content = "Xác nhận đặt vé thành công - " + ve.getMaVe() +"\n" +
                        "\n" +
                        "Kính gửi " + ve.getKhachHang().getTenKH() + ",  \n" +
                        "\n" +
                        "Chúng tôi xin xác nhận rằng bạn đã đặt vé thành công. Dưới đây là thông tin chi tiết vé tàu của bạn:  \n" +
                        "\n" +
                        "Thông tin hành trình:  \n" +
                        "- Mã đặt vé:" + ve.getMaVe() +"  \n" +
                        "- Họ và tên hành khách: " + ve.getKhachHang().getTenKH() +  "\n" +
                        "- Ngày khởi hành: " + FormatDate.formatLocalDateTimeToHM(ve.getChuyenTau().getNgayGioDi()) + "\n" +
                        "- Ga đi: " + ve.getChuyenTau().getGaDi().getTenGa() + "\n" +
                        "- Ga đến: " + ve.getChuyenTau().getGaDen().getTenGa() + "\n" +
                        "Cảm ơn quý khách đã lựa chọn dịch vụ của chúng tôi. Chúng tôi chúc bạn một chuyến đi an lành và thú vị!  \n" +
                        "Trân trọng,  \n" +
                        "Công Ty Tàu Việt Express \n";
                boolean success = emailService.sendEmailWithAttachment(
                        email, "Xác nhận đặt vé thành công " + ve.getMaVe(), content, tempFile.getAbsolutePath());
            } catch (IOException | DocumentException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Method to send bill via email
    public static void sendBillByEmail(HoaDon hoaDon) {
        String email = hoaDon.getKhachHang().getEmail();
        if (email != null && !email.trim().isEmpty()) {
            try {
                String htmlContent = generateHtmlBill(hoaDon);
                BufferedImage image = renderHtmlToImage(htmlContent);

                // Create a PDF file from the HTML content
                File tempFile = ImageToPdf.ImageHDToPdf(image);

                String content = "Hóa đơn điện tử - " + hoaDon.getMaHD() + "\n\n" +
                        "Kính gửi " + hoaDon.getKhachHang().getTenKH() + ",\n\n" +
                        "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Dưới đây là thông tin hóa đơn của bạn:\n\n" +
                        "Thông tin hóa đơn:\n" +
                        "- Mã hóa đơn: " + hoaDon.getMaHD() + "\n" +
                        "- Ngày lập hóa đơn: " + FormatDate.formatLocalDateTimeToHM(hoaDon.getNgayGioLapHD()) + "\n" +
                        "- Nhân viên lập hóa đơn: " + hoaDon.getNhanVien().getTenNV() + "\n" +
                        "- Tên khách hàng: " + hoaDon.getKhachHang().getTenKH() + "\n" +
                        "- Số lượng vé: " + hoaDon.getSoLuong() + "\n\n" +
                        "\nCảm ơn quý khách đã lựa chọn dịch vụ của chúng tôi. Chúng tôi hy vọng sẽ được phục vụ bạn trong những lần tới!\n\n" +
                        "Trân trọng,\n" +
                        "Công Ty Tàu Việt Express\n";

                // Use the EmailService to send the email with the PDF attachment
                EmailService emailService = new EmailService();
                boolean success = emailService.sendEmailWithAttachment(
                        email, "Hóa đơn điện tử - " + hoaDon.getMaHD(), content, tempFile.getAbsolutePath());
            } catch (IOException | DocumentException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // ham nay de lam gi: gui email cho khach hang
    public boolean sendPromotionNotification(String email, String content) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Tau Viet Express Khuyến mãi");
            message.setText(content);

            // Gửi email
            Transport.send(message);
            return true;


        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}