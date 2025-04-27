package utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Dự án: tau-viet-express
 * @Class: ImageToPdf
 * @Tạo vào ngày: 12/12/2024
 * @Tác giả: Thai
 */
public class ImageToPdf {
    public static File convertImageToPdf(BufferedImage image) throws IOException, DocumentException {
        File tempFile = File.createTempFile("ticket", ".pdf");

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();

            PdfWriter.getInstance(document, fos);
            document.open();

            // Kích thước trang PDF
            float pdfWidth = com.itextpdf.text.PageSize.A4.getWidth();
            float pdfHeight = com.itextpdf.text.PageSize.A4.getHeight();

            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();

            // Tính toán tỉ lệ để giữ nguyên tỉ lệ ảnh
            float scaleWidth = pdfWidth / imgWidth;
            float scaleHeight = pdfHeight / imgHeight;
            float scale = Math.min(scaleWidth, scaleHeight);

            int newWidth = Math.round(imgWidth * scale);
            int newHeight = Math.round(imgHeight * scale);

            // Resize ảnh với chất lượng cao
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(image, 0, 0, newWidth, newHeight, null);
            g.dispose();

            // Chuyển BufferedImage thành byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            // Thêm ảnh vào PDF
            com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(imageBytes);
            pdfImage.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            document.add(pdfImage);

            document.close();
        }

        return tempFile;
    }
    public static File ImageHDToPdf(BufferedImage image) throws IOException, DocumentException {
        File tempFile = File.createTempFile("bill", ".pdf");

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();

            PdfWriter.getInstance(document, fos);
            document.open();

            // Kích thước trang PDF
            float pdfWidth = com.itextpdf.text.PageSize.A4.getWidth();
            float pdfHeight = com.itextpdf.text.PageSize.A4.getHeight();

            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();

            // Tính toán tỉ lệ để giữ nguyên tỉ lệ ảnh
            float scaleWidth = pdfWidth / imgWidth;
            float scaleHeight = pdfHeight / imgHeight;
            float scale = Math.min(scaleWidth, scaleHeight);

            int newWidth = Math.round(imgWidth * scale);
            int newHeight = Math.round(imgHeight * scale);

            // Resize ảnh với chất lượng cao
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(image, 0, 0, newWidth, newHeight, null);
            g.dispose();

            // Chuyển BufferedImage thành byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            // Thêm ảnh vào PDF
            com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(imageBytes);
            pdfImage.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
            document.add(pdfImage);
            document.close();
        }

        return tempFile;
    }
}
