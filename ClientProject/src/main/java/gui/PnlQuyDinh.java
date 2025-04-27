package gui;

import enums.EColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class PnlQuyDinh extends JPanel {

    private JTextPane textPane;

    public PnlQuyDinh() {
        setBackground(EColor.BG_COLOR.getColor());
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Create title
        JLabel lblTieuDe = new JLabel("Quy Định nhà ga");
        lblTieuDe.setFont(lblTieuDe.getFont().deriveFont(30.0f));
        lblTieuDe.setForeground(Color.BLUE);
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTieuDe, BorderLayout.NORTH);

        // Table of contents
        JPanel tocPanel = new JPanel();
        tocPanel.setLayout(new BoxLayout(tocPanel, BoxLayout.Y_AXIS));
        tocPanel.setBackground(EColor.BG_COLOR.getColor());

        String[] tocItems = {"Hành khách xếp hàng mua vé, quẹt thẻ qua cổng kiểm soát", "Quy định mua vé", "Giữ gìn trật tự, vệ sinh chung", "Giữ gìn tài sản tại nhà ga và trên tàu", "Không mang hàng hóa cồng kềnh, động vật, thực phẩm có mùi", "Không hút thuốc trong nhà ga và trên tàu", "Không mang vũ khí, hàng cấm", "Tuân thủ chỉ dẫn và phòng dịch", "Quy định về trẻ em", "Nhà ga từ chối phục vụ"};

        int cnt = 1;
        for (String item : tocItems) {
            JLabel label = new JLabel(item);
            String fileName = "/images/iconPnlQuyDinh/qd-" + cnt + ".png";
            ++cnt;//tan so hien thi icon
            Image img = new ImageIcon(Objects.requireNonNull(getClass().getResource(fileName))).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
            label.setFont(label.getFont().deriveFont(16.0f));
            label.setForeground(EColor.TEXT_COLOR.getColor());
            label.setAlignmentX(LEFT_ALIGNMENT);
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

        tocPanel.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, Color.WHITE));
        add(tocPanel, BorderLayout.WEST);

//        // Nội dung quy định
//        String noiDung = """
//                <html>
//                <ol>
//                                  <a name="index-1"></a>
//                                <li style="font-size: 20px"><strong>H&agrave;nh kh&aacute;ch xếp h&agrave;ng mua v&eacute;, quẹt thẻ khi qua cổng kiểm so&aacute;t:</strong></li>
//                                </ol>
//                                <p style="font-size: 18px">&ndash; Kh&aacute;ch h&agrave;ng đi t&agrave;u tr&ecirc;n tuyến phải mua v&eacute; theo đ&uacute;ng quy định của C&ocirc;ng ty, quẹt thẻ v&eacute; khi qua cổng kiểm so&aacute;t v&eacute;; xuất tr&igrave;nh đầy đủ, v&eacute; hợp lệ cho C&ocirc;ng ty khi c&oacute; y&ecirc;u cầu kiểm tra khi ra, v&agrave;o nh&agrave; ga v&agrave; l&ecirc;n t&agrave;u.</p>
//                                <p style="font-size: 18px">&ndash; Khi mua v&eacute; nhầm hoặc thiếu so với quy định, h&agrave;nh kh&aacute;ch c&oacute; tr&aacute;ch nhiệm b&aacute;o lại cho nh&acirc;n vi&ecirc;n Quầy v&eacute;, nộp đủ số tiền b&ugrave; th&ecirc;m gi&aacute; trị của v&eacute; (nếu c&oacute;) của h&agrave;nh tr&igrave;nh theo quy định của C&ocirc;ng ty.</p>
//                                <p style="font-size: 18px">&ndash; V&eacute; hợp lệ l&agrave; v&eacute; do C&ocirc;ng ty hoặc cơ quan chức năng ban h&agrave;nh c&ograve;n đủ c&aacute;c th&ocirc;ng tin, thời hạn sử dụng, ph&ugrave; hợp với quy định của từng loại v&eacute;.</p>
//                                <p style="font-size: 18px">&ndash; H&agrave;nh kh&aacute;ch sẽ coi l&agrave; vi phạm quy định về v&eacute; khi kh&ocirc;ng c&oacute; v&eacute; hoặc mang v&eacute; kh&ocirc;ng hợp lệ, h&igrave;nh thức xử l&yacute; vi phạm như sau: Y&ecirc;u cầu mua v&eacute; mới c&oacute; gi&aacute; trị cao nhất của tuyến hoặc bị C&ocirc;ng ty từ chối cung cấp dịch vụ.</p>
//                                <p style="font-size: 18px">&ndash; H&agrave;nh kh&aacute;ch được đi t&agrave;u miễn ph&iacute; theo Nghị quyết của HĐND Th&agrave;nh phố đến quầy b&aacute;n v&eacute; (BOM) nhận thẻ 0 đồng để quẹt thẻ qua cửa so&aacute;t v&eacute; v&agrave; trả lại v&eacute; khi qua cửa ra ga</p>
//                                <ol start="2">
//                                <a name="index-2"></a>
//                                <li style="font-size: 20px"><strong>Quy định mua v&eacute;:</strong></li>
//                                </ol>
//                                <p style="font-size: 18px">&ndash;&nbsp;Khi mua v&eacute; cần cung cấp đủ th&ocirc;ng tin về người mua v&eacute;, th&ocirc;ng tin về h&agrave;nh kh&aacute;ch đi t&agrave;u bao gồm: Họ v&agrave; t&ecirc;n đầy đủ, giấy tờ t&ugrave;y th&acirc;n c&oacute; ảnh hợp lệ được ph&aacute;p luật c&ocirc;ng nhận (Số chứng minh thư nh&acirc;n d&acirc;n | Số bằng l&aacute;i xe được ph&aacute;p luật c&ocirc;ng nhận | Số hộ chiếu).</p>
//                                <p style="font-size: 18px">&nbsp;</p>
//                                <ol start="3">
//                                <a name="index-3"></a>
//                                <li style="font-size: 20px"><strong>Giữ g&igrave;n trật tự, vệ sinh chung tại nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u.</strong></li>
//                                </ol>
//                                <p style="font-size: 18px">&ndash; H&agrave;nh kh&aacute;ch xếp h&agrave;ng khi mua v&eacute; v&agrave; qua cổng so&aacute;t v&eacute;.</p>
//                                <p style="font-size: 18px">&ndash; Nhường ghế cho trẻ em, người gi&agrave;, phụ nữ c&oacute; thai v&agrave; người khuyết tật.</p>
//                                <p style="font-size: 18px">&ndash; Kh&ocirc;ng n&oacute;i to, c&atilde;i, đ&aacute;nh nhau, g&acirc;y mất trật tự tại nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u.</p>
//                                <p style="font-size: 18px">&ndash; Tuyệt đối kh&ocirc;ng di chuyển qua vạch an to&agrave;n tr&ecirc;n ke ga khi t&agrave;u chưa đến.</p>
//                                <p style="font-size: 18px">&ndash; Tuyệt đối kh&ocirc;ng di chuyển tới c&aacute;c khu vực kỹ thuật, nghiệp vụ tại nh&agrave; ga v&agrave; khu vực l&aacute;i t&agrave;u.</p>
//                                <p style="font-size: 18px">&ndash; H&agrave;nh kh&aacute;ch bỏ r&aacute;c đ&uacute;ng nơi quy định, kh&ocirc;ng được vứt r&aacute;c ra ga, s&agrave;n t&agrave;u.</p>
//                                <ol start="4">
//                                <a name="index-4"></a>
//                                <li style="font-size: 20px"><strong>Giữ g&igrave;n t&agrave;i sản thiết bị nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u.</strong></li>
//                                </ol>
//                                <p style="font-size: 18px">&ndash; Chấp h&agrave;nh quy định, nội quy về ph&ograve;ng ch&aacute;y v&agrave; chữa ch&aacute;y tại c&aacute;c nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u.</p>
//                                <p style="font-size: 18px">&ndash; Ngăn chặn nguy cơ trực tiếp ph&aacute;t sinh ch&aacute;y v&agrave; những h&agrave;nh vi vi phạm quy định an to&agrave;n về ch&aacute;y, nổ tại c&aacute;c nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u.</p>
//                                <p style="font-size: 18px">&ndash; Kh&ocirc;ng ph&aacute; hoại t&agrave;i sản tại c&aacute;c nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u.</p>
//                                <p style="font-size: 18px">&ndash; Tuyệt đối kh&ocirc;ng được vất r&aacute;c, vật cứng xuống khu vực ke ga, đường t&agrave;u.</p>
//                                <p style="font-size: 18px">&ndash; Tuyệt đối kh&ocirc;ng được trộm cắp t&agrave;i sản, vật dụng tại c&aacute;c nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u.</p>
//                                <ol start="5">
//                                <a name="index-5"></a>
//                                <li style="font-size: 20px"><strong>Kh&ocirc;ng mang h&agrave;ng h&oacute;a, h&agrave;nh l&yacute; cồng kềnh, động vật v&agrave; đồ tươi sống c&oacute; m&ugrave;i v&agrave;o c&aacute;c nh&agrave; ga v&agrave; l&ecirc;n t&agrave;u.</strong></li>
//                                </ol>
//                                <p style="font-size: 18px">&ndash; H&agrave;nh l&yacute; cồng kềnh l&agrave; h&agrave;nh l&yacute; c&oacute; k&iacute;ch thước vượt qu&aacute; 56cm x 36cm x 23cm; trọng lượng vượt qu&aacute; 18kg;</p>
//                                <p style="font-size: 18px">&ndash; Vật nu&ocirc;i như ch&oacute;, m&egrave;o,&hellip;; đồ tươi sống c&oacute; m&ugrave;i như thịt, c&aacute;,&hellip;;</p>
//                                <p style="font-size: 18px">&ndash; Những chất g&acirc;y mất vệ sinh, l&agrave;m bẩn nh&agrave; ga, toa xe;</p>
//                                <p style="font-size: 18px">&ndash; Thi h&agrave;i, h&agrave;i cốt.</p>
//                                <ol start="6">
//                                <a name="index-6"></a>
//                                <li style="font-size: 20px"><strong>Kh&ocirc;ng được h&uacute;t thuốc trong nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u.</strong></li>
//                                <a name="index-7"></a>
//                                <li style="font-size: 20px"><strong>Tuyệt đối kh&ocirc;ng mang vũ kh&iacute;, vật liệu ch&aacute;y, nổ v&agrave; h&agrave;ng h&oacute;a bị ph&aacute;p luật cấm v&agrave;o c&aacute;c nh&agrave; ga v&agrave; l&ecirc;n t&agrave;u.</strong></li>
//                                </ol>
//                                <p style="font-size: 18px">&ndash; Vũ kh&iacute;, c&ocirc;ng cụ hỗ trợ m&agrave; kh&ocirc;ng c&oacute; giấy ph&eacute;p sử dụng hợp lệ;</p>
//                                <p style="font-size: 18px">&ndash; H&agrave;ng nguy hiểm: Như chấy g&acirc;y ch&aacute;y, nổ, chất ph&oacute;ng xạ, m&aacute;y ph&aacute;t s&oacute;ng tần xuất cao&hellip;</p>
//                                <p style="font-size: 18px">&ndash; H&agrave;ng h&oacute;a cấm lưu th&ocirc;ng: Như ma t&uacute;y; h&agrave;ng nhập lậu,&hellip;</p>
//                                <ol start="8">
//                                <a name="index-8"></a>
//                                <li style="font-size: 20px"><strong>Tu&acirc;n thủ c&aacute;c chỉ dẫn trong nh&agrave; ga, chấp h&agrave;nh sự sắp xếp v&agrave; điều h&agrave;nh của nh&acirc;n vi&ecirc;n phục vụ; Tu&acirc;n thủ c&aacute;c quy định ph&ograve;ng chống dịch bệnh tại c&aacute;c nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u.</strong></li>
//                                </ol>
//                                <p style="font-size: 18px">&ndash; Tuyệt đối tu&acirc;n thủ c&aacute;c hiệu lệnh, sự sắp xếp v&agrave; điều h&agrave;nh của nh&acirc;n vi&ecirc;n phục vụ tại c&aacute;c nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u; Tuyệt đối tu&acirc;n thủ c&aacute;c chỉ dẫn tại c&aacute;c nh&agrave; ga v&agrave; tr&ecirc;n t&agrave;u; kh&ocirc;ng ấn c&aacute;c n&uacute;t b&aacute;o nguy hiểm, b&aacute;o ch&aacute;y, sự cố khẩn cấp tại nh&agrave; ga, tr&ecirc;n t&agrave;u khi kh&ocirc;ng c&oacute; c&aacute;c trường hợp khẩn cấp xảy ra.</p>
//                                <p style="font-size: 18px">&ndash; Thực hiện đ&uacute;ng c&aacute;c quy định, hướng dẫn sử dụng t&agrave;i sản tại c&aacute;c ga, t&agrave;u.</p>
//                                <p style="font-size: 18px">&ndash; Tu&acirc;n thủ c&aacute;c c&aacute;c quy định về ph&ograve;ng chống dịch bệnh của c&aacute;c Cơ quan chức năng v&agrave; Đơn vị vận h&agrave;nh (Thực hiện th&ocirc;ng điệp 5K về ph&ograve;ng chống Covid-19).</p>
//                                <ol start="9">
//                                <a name="index-9"></a>
//                                <li style="font-size: 20px"><strong>Quy định về trẻ em đi t&agrave;u.</strong></li>
//                                </ol>
//                                <p style="font-size: 18px">&ndash; Trẻ em từ 6 tuổi trở xuống bắt buộc phải c&oacute; người lớn đi k&egrave;m, trường hợp kh&ocirc;ng c&oacute; người lớn đi k&egrave;m C&ocirc;ng ty c&oacute; thể từ chối cung cấp&nbsp; dịch vụ.</p>
//                                <p style="font-size: 18px">&ndash; Người hạn chế khả năng di chuyển n&ecirc;n c&oacute; người hỗ trợ đi theo</p>
//                                <ol start="10">
//                                <a name="index-10"></a>
//                                <li style="font-size: 20px"><strong>Nh&agrave; ga từ chối phục vụ:</strong></li>
//                                </ol>
//                                <p style="font-size: 18px">&ndash;&nbsp;Vụ người say rượu; người mất tr&iacute;; người c&oacute; bệnh truyền nhiễm; người c&oacute; bệnh tật m&agrave; b&aacute;c sỹ chỉ định kh&ocirc;ng di chuyển.</p>
//                                <p style="font-size: 18px">&nbsp;</p>
//                                <p style="font-size: 18px">&nbsp;</p>
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
            case "Hành khách xếp hàng mua vé, quẹt thẻ qua cổng kiểm soát":
                anchor = "index-1";
                break;
            case "Quy định mua vé":
                anchor = "index-2";
                break;
            case "Giữ gìn trật tự, vệ sinh chung":
                anchor = "index-3";
                break;
            case "Giữ gìn tài sản tại nhà ga và trên tàu":
                anchor = "index-4";
                break;
            case "Không mang hàng hóa cồng kềnh, động vật, thực phẩm có mùi":
                anchor = "index-5";
                break;
            case "Không hút thuốc trong nhà ga và trên tàu":
                anchor = "index-6";
                break;
            case "Không mang vũ khí, hàng cấm":
                anchor = "index-7";
                break;
            case "Tuân thủ chỉ dẫn và phòng dịch":
                anchor = "index-8";
                break;
            case "Quy định về trẻ em":
                anchor = "index-9";
                break;
            case "Nhà ga từ chối phục vụ":
                anchor = "index-10";
                break;
        }

        try {
            textPane.scrollToReference(anchor);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}