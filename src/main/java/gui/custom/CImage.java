package gui.custom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Dự án: tau-viet-express
 * @Class: ImageScale
 * @Tạo vào ngày: 3/10/2024
 * @Tác giả: Huy
 *
 * Lớp tiện ích để tải và thay đổi kích thước hình ảnh từ tài nguyên (resources).
 */
public class CImage extends ImageIcon {
    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 100;

    /**
     * Tải và thay đổi kích thước hình ảnh với kích thước mặc định (100x100).
     *
     * @param path Đường dẫn tới tài nguyên hình ảnh (bắt đầu từ thư mục resources).
     */
    public CImage(String path) {
        this(path, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Tải và thay đổi kích thước hình ảnh với kích thước tùy chỉnh.
     *
     * @param path   Đường dẫn tới tài nguyên hình ảnh (bắt đầu từ thư mục resources).
     * @param width  Chiều rộng mong muốn.
     * @param height Chiều cao mong muốn.
     */
    public CImage(String path, int width, int height) {
        Image image = loadImage(path);
        if (image != null) {
            this.setImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } else {
            // Nếu không tìm thấy hình ảnh, hiển thị log và sử dụng hình ảnh mặc định
            System.err.println("Không tìm thấy hình ảnh tại: " + path);
            this.setImage(createDefaultImage(width, height));
        }
    }

    /**
     * Tải hình ảnh từ tài nguyên (resources).
     *
     * @param path Đường dẫn tới hình ảnh.
     * @return Đối tượng Image nếu tải thành công, hoặc null nếu thất bại.
     */
    private Image loadImage(String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                return null;
            }
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Tạo một hình ảnh mặc định (nền xám) khi không tìm thấy hình ảnh yêu cầu.
     *
     * @param width  Chiều rộng của hình ảnh mặc định.
     * @param height Chiều cao của hình ảnh mặc định.
     * @return Hình ảnh mặc định.
     */
    private Image createDefaultImage(int width, int height) {
        Image defaultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) defaultImage.getGraphics();
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.drawString("No Image", width / 4, height / 2);
        g2d.dispose();
        return defaultImage;
    }
}
