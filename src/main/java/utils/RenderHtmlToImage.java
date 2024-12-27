package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Dự án: tau-viet-express
 * @Class: RenderHtmlToImage
 * @Tạo vào ngày: 12/12/2024
 * @Tác giả: Thai
 */
public class RenderHtmlToImage {
    public static BufferedImage renderHtmlToImage(String htmlContent) {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(htmlContent);
        editorPane.setSize(1100, 900); // Kích thước hình ảnh mong muốn

        BufferedImage image = new BufferedImage(editorPane.getWidth(), editorPane.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        editorPane.printAll(g);
        g.dispose();

        return image;
    }
}
