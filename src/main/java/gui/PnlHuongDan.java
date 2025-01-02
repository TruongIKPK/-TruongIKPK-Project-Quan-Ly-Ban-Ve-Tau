package gui;

import enums.EColor;
import gui.custom.CImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class PnlHuongDan extends JPanel {

    private JTextPane textPane;

    public PnlHuongDan() {
        setBackground(EColor.BG_COLOR.getColor());
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Create title
        JLabel lblTieuDe = new JLabel("Hướng dẫn sử dụng");
        lblTieuDe.setFont(lblTieuDe.getFont().deriveFont(30.0f));
        lblTieuDe.setForeground(Color.BLUE);
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTieuDe, BorderLayout.NORTH);

        // Table of Contents
        JPanel tocPanel = new JPanel();
        tocPanel.setLayout(new BoxLayout(tocPanel, BoxLayout.Y_AXIS));
        tocPanel.setBackground(EColor.BG_COLOR.getColor());

        String[] tocItems = {"Đăng Nhập", "Tìm Kiếm Lịch Trình Tàu", "Đặt Vé", "Quản Lý Vé", "Thanh Toán", "Quản Lý Người Dùng (Admin)", "Thống Kê", "Khuyến Mãi", "Quản Lý Nhân Viên", "Xử Lý Sự Cố", "Thoát Ứng Dụng", "Liên Hệ Hỗ Trợ", "Đăng Xuất", "Đổi Mật Khẩu", "Tra Cứu Vé", "Xem Lại Quy Định", "Xem Lại Hướng Dẫn Sử Dụng Ứng Dụng"};

        // tôi không muốn dùng btn mà muốn dùng label
        int cnt = 1;
        for (String item : tocItems) {
            JLabel label = new JLabel(item);
            String fileName = "/images/iconPnlGuide/cn-" + cnt + ".png";
            ++cnt;//tan so hien thi icon
            Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource(fileName))).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(image));
            label.setFont(label.getFont().deriveFont(16.0f));
            label.setForeground(EColor.TEXT_COLOR.getColor());

            label.setAlignmentX(LEFT_ALIGNMENT);
//            tạo viền cho các label
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    scrollToSection(item);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setForeground(Color.BLUE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    label.setForeground(EColor.TEXT_COLOR.getColor());
                }
            });
            tocPanel.add(label);
        }

    // tao viền cho panel

        tocPanel.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, Color.WHITE));
        add(tocPanel, BorderLayout.WEST);

        // Content of the guide
//        String noiDung = """
//                <html>
//                <ol>
//                <a name="index-1"></a>
//                <li style="font-size: 20px"><strong> Đăng Nhập</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Khi mở ứng dụng, người d&ugrave;ng sẽ được chuyển đến m&agrave;n h&igrave;nh Đăng Nhập.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Nhập <strong>t&ecirc;n t&agrave;i khoản</strong> v&agrave; <strong>mật khẩu</strong>.</li>
//                <li style="font-size: 18px">Nhấn n&uacute;t <strong>Đăng Nhập</strong>.</li>
//                <li style="font-size: 18px">Nếu th&ocirc;ng tin ch&iacute;nh x&aacute;c, hệ thống sẽ chuyển v&agrave;o <strong>giao diện ch&iacute;nh</strong>.</li>
//                <li style="font-size: 18px">Nếu th&ocirc;ng tin sai, hệ thống hiển thị <strong>th&ocirc;ng b&aacute;o lỗi</strong> y&ecirc;u cầu nhập lại.</li>
//                </ol>
//                </ul>
//                <a name="index-2"></a>
//                <ol start="2">
//                <li style="font-size: 20px"><strong> T&igrave;m Kiếm Lịch Tr&igrave;nh T&agrave;u</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Người d&ugrave;ng truy cập giao diện để t&igrave;m kiếm c&aacute;c chuyến t&agrave;u.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Nhập <strong>ga đi</strong>, <strong>ga đến</strong>, v&agrave; <strong>ng&agrave;y đi</strong>.</li>
//                <li style="font-size: 18px">Nhấn n&uacute;t <strong>T&igrave;m Kiếm</strong>.</li>
//                <li style="font-size: 18px">Kết quả sẽ hiển thị <strong>danh s&aacute;ch c&aacute;c chuyến t&agrave;u</strong> ph&ugrave; hợp với th&ocirc;ng tin đ&atilde; nhập.</li>
//                </ol>
//                </ul>
//                <a name="index-3"></a>
//                <ol start="3">
//                <li style="font-size: 20px"><strong> Đặt V&eacute;</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Người d&ugrave;ng chọn chuyến t&agrave;u v&agrave; đặt v&eacute; trực tuyến.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Chọn <strong>chuyến t&agrave;u</strong> từ kết quả t&igrave;m kiếm.</li>
//                <li style="font-size: 18px">Nhập <strong>th&ocirc;ng tin kh&aacute;ch h&agrave;ng</strong> (t&ecirc;n, số điện thoại, CMND).</li>
//                <li style="font-size: 18px">Nhấn <strong>X&aacute;c Nhận</strong> để ho&agrave;n tất đặt v&eacute;.</li>
//                <li style="font-size: 18px">Hệ thống sẽ th&ocirc;ng b&aacute;o <strong>đặt v&eacute; th&agrave;nh c&ocirc;ng</strong> v&agrave; hiển thị <strong>m&atilde; v&eacute;</strong>.</li>
//                </ol>
//                </ul>
//                <a name="index-4"></a>
//                <ol start="4">
//                <li style="font-size: 20px"><strong> Quản L&yacute; V&eacute;</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Người d&ugrave;ng c&oacute; thể xem hoặc hủy v&eacute; đ&atilde; đặt.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Nhập <strong>m&atilde; v&eacute;</strong> để t&igrave;m kiếm th&ocirc;ng tin v&eacute;.</li>
//                <li style="font-size: 18px">Chọn <strong>Hủy V&eacute;</strong> nếu cần hủy.</li>
//                <li style="font-size: 18px">Hệ thống sẽ y&ecirc;u cầu <strong>x&aacute;c nhận</strong> trước khi hủy v&eacute;.</li>
//                </ol>
//                </ul>
//                <a name="index-5"></a>
//                <ol start="5">
//                <li style="font-size: 20px"><strong> Thanh To&aacute;n</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Hỗ thống hỗ trợ nhiều phương thức thanh to&aacute;n.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Chọn <strong>phương thức thanh to&aacute;n</strong> (Momo, thẻ ng&acirc;n h&agrave;ng, tiền mặt).</li>
//                <li style="font-size: 18px">Nhập <strong>th&ocirc;ng tin thanh to&aacute;n</strong> v&agrave; nhấn <strong>X&aacute;c Nhận</strong>.</li>
//                <li style="font-size: 18px">Hệ thống sẽ gửi th&ocirc;ng b&aacute;o <strong>thanh to&aacute;n th&agrave;nh c&ocirc;ng</strong> v&agrave; cung cấp <strong>h&oacute;a đơn</strong>.</li>
//                </ol>
//                </ul>
//                <a name="index-6"></a>
//                <ol start="6">
//                <li style="font-size: 20px"><strong> Quản L&yacute; Người D&ugrave;ng (Admin)</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Admin c&oacute; quyền thực hiện c&aacute;c thao t&aacute;c quản trị.</li>
//                <ul>
//                <li style="font-size: 18px">Quản l&yacute; <strong>danh s&aacute;ch người d&ugrave;ng</strong>.</li>
//                <li style="font-size: 18px">Th&ecirc;m, sửa, x&oacute;a <strong>lịch tr&igrave;nh t&agrave;u</strong>.</li>
//                <li style="font-size: 18px">Xem v&agrave; quản l&yacute; <strong>to&agrave;n bộ v&eacute; đ&atilde; đặt</strong>.</li>
//                </ul>
//                </ul>
//                <a name="index-7"></a>
//                <ol start="7">
//                <li style="font-size: 20px"><strong> Thống K&ecirc;</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Hiển thị c&aacute;c b&aacute;o c&aacute;o về doanh thu v&agrave; giao dịch.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Truy cập giao diện <strong>Thống K&ecirc;</strong>.</li>
//                <li style="font-size: 18px">Chọn <strong>khoảng thời gian</strong> hoặc <strong>loại b&aacute;o c&aacute;o</strong>.</li>
//                <li style="font-size: 18px">Kết quả hiển thị dưới dạng <strong>bảng hoặc biểu đồ</strong>.</li>
//                </ol>
//                </ul>
//                <a name="index-8"></a>
//                <ol start="8">
//                <li style="font-size: 20px"><strong> Khuyến M&atilde;i</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Quản l&yacute; c&aacute;c chương tr&igrave;nh khuyến m&atilde;i.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Th&ecirc;m hoặc chỉnh sửa <strong>chương tr&igrave;nh khuyến m&atilde;i</strong>.</li>
//                <li style="font-size: 18px">Thiết lập <strong>điều kiện v&agrave; thời hạn</strong> &aacute;p dụng.</li>
//                </ol>
//                </ul>
//                <a name="index-9"></a>
//                <ol start="9">
//                <li style="font-size: 20px"><strong> Quản L&yacute; Nh&acirc;n Vi&ecirc;n</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Quản l&yacute; th&ocirc;ng tin v&agrave; quyền hạn nh&acirc;n vi&ecirc;n.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Th&ecirc;m, sửa hoặc x&oacute;a <strong>th&ocirc;ng tin nh&acirc;n vi&ecirc;n</strong>.</li>
//                <li style="font-size: 18px">Ph&acirc;n quyền theo vai tr&ograve;.</li>
//                </ol>
//                </ul>
//                <a name="index-10"></a>
//                <ol start="10">
//                <li style="font-size: 20px"><strong> Xử L&yacute; Sự Cố</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>Kh&ocirc;ng đăng nhập được</strong>: Kiểm tra t&agrave;i khoản hoặc li&ecirc;n hệ quản trị.</li>
//                <li style="font-size: 18px"><strong>Kh&ocirc;ng t&igrave;m thấy chuyến t&agrave;u</strong>: X&aacute;c minh lại th&ocirc;ng tin t&igrave;m kiếm.</li>
//                </ul>
//                <a name="index-11"></a>
//                <ol start="11">
//                <li style="font-size: 20px"><strong> Tho&aacute;t Ứng Dụng</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Y&ecirc;u cầu x&aacute;c nhận trước khi tho&aacute;t ứng dụng.</li>
//                </ul>
//                <a name="index-12"></a>
//                <ol start="12">
//                <li style="font-size: 20px"><strong> Li&ecirc;n Hệ Hỗ Trợ</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>Email</strong>: tauvietdev@gmail.com</li>
//                </ul>
//                <a name="index-13"></a>
//                <ol start="13">
//                <li style="font-size: 20px"><strong> Đăng Xuất</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Đăng xuất khỏi t&agrave;i khoản.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Nhấn n&uacute;t <strong>Đăng Xuất</strong>.</li>
//                <li style="font-size: 18px">Hệ thống sẽ chuyển về <strong>m&agrave;n h&igrave;nh Đăng Nhập</strong>.</li>
//                </ol>
//                </ul>
//                <a name="index-14"></a>
//                <ol start="14">
//                <li style="font-size: 20px"><strong> Đổi Mật Khẩu</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Người d&ugrave;ng c&oacute; thể đổi mật khẩu t&agrave;i khoản.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Nhập <strong>mật khẩu cũ</strong> v&agrave; <strong>mật khẩu mới</strong>.</li>
//                <li style="font-size: 18px">Nhấn <strong>X&aacute;c Nhận</strong> để ho&agrave;n tất.</li>
//                </ol>
//                </ul>
//                <a name="index-15"></a>
//                <ol start="15">
//                <li style="font-size: 20px"><strong> Tra Cứu V&eacute;</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: T&igrave;m lại th&ocirc;ng tin v&eacute; đ&atilde; đặt.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Nhập <strong>m&atilde; v&eacute;</strong> hoặc <strong>th&ocirc;ng tin kh&aacute;ch h&agrave;ng</strong>.</li>
//                <li style="font-size: 18px">Hệ thống hiển thị th&ocirc;ng tin chi tiết v&eacute;.</li>
//                </ol>
//                </ul>
//                <a name="index-16"></a>
//                <ol start="16">
//                <li style="font-size: 20px"><strong> Xem Lại Quy Định</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Truy cập c&aacute;c quy định li&ecirc;n quan đến v&eacute; v&agrave; dịch vụ.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">V&agrave;o mục <strong>Quy Định</strong> từ giao diện ch&iacute;nh.</li>
//                <li style="font-size: 18px">Đọc c&aacute;c điều khoản v&agrave; điều kiện được liệt k&ecirc;.</li>
//                </ol>
//                </ul>
//                <a name="index-17"></a>
//                <ol start="17">
//                <li style="font-size: 20px"><strong> Xem Lại Hướng Dẫn Sử Dụng Ứng Dụng</strong></li>
//                </ol>
//                <ul>
//                <li style="font-size: 18px"><strong>M&ocirc; tả</strong>: Cung cấp hướng dẫn sử dụng đầy đủ của ứng dụng.</li>
//                <li style="font-size: 18px"><strong>C&aacute;ch sử dụng</strong>:</li>
//                <ol>
//                <li style="font-size: 18px">Truy cập mục <strong>Hướng Dẫn Sử Dụng</strong>.</li>
//                <li style="font-size: 18px">M&agrave;n h&igrave;nh sẽ hiển thị <strong>mục lục</strong> b&ecirc;n tr&aacute;i v&agrave; <strong>nội dung chi tiết</strong> b&ecirc;n phải.</li>
//                <li style="font-size: 18px">Khi nhấp v&agrave;o một mục trong mục lục, nội dung b&ecirc;n phải sẽ <strong>tự động cuộn</strong> đến phần tương ứng.</li>
//                </ol>
//                </ul>
//                </html>
//                """;

        textPane = new JTextPane();
        textPane.setContentType("text/html");
//        textPane.setText(noiDung);
        textPane.setEditable(false);
        textPane.setBackground(EColor.BG_COLOR.getColor());
        textPane.setForeground(EColor.TEXT_COLOR.getColor());
        textPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void scrollToSection(String section) {
        String anchor = "";
        switch (section) {
            case "Đăng Nhập":
                anchor = "index-1";
                break;
            case "Tìm Kiếm Lịch Trình Tàu":
                anchor = "index-2";
                break;
            case "Đặt Vé":
                anchor = "index-3";
                break;
            case "Quản Lý Vé":
                anchor = "index-4";
                break;
            case "Thanh Toán":
                anchor = "index-5";
                break;
            case "Quản Lý Người Dùng (Admin)":
                anchor = "index-6";
                break;
            case "Thống Kê":
                anchor = "index-7";
                break;
            case "Khuyến Mãi":
                anchor = "index-8";
                break;
            case "Quản Lý Nhân Viên":
                anchor = "index-9";
                break;
            case "Xử Lý Sự Cố":
                anchor = "index-10";
                break;
            case "Thoát Ứng Dụng":
                anchor = "index-11";
                break;
            case "Liên Hệ Hỗ Trợ":
                anchor = "index-12";
                break;
            case "Đăng Xuất":
                anchor = "index-13";
                break;
            case "Đổi Mật Khẩu":
                anchor = "index-14";
                break;
            case "Tra Cứu Vé":
                anchor = "index-15";
                break;
            case "Xem Lại Quy Định":
                anchor = "index-16";
                break;
            case "Xem Lại Hướng Dẫn Sử Dụng Ứng Dụng":
                anchor = "index-17";
                break;
        }

        // Use the JTextPane's scroll to method
        try {
            textPane.scrollToReference(anchor);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new PnlHuongDan());
        frame.setVisible(true);
        // to hon
    }
}