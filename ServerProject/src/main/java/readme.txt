# Tàu Việt Express

## Giới thiệu Dự án
**Tàu Việt Express** là một ứng dụng quản lý vé tàu được phát triển bằng Java, giúp hỗ trợ việc bán vé, đổi vé, và thực hiện các chức năng liên quan khác. Ứng dụng cung cấp các tính năng như quản lý khách hàng, nhân viên, lịch trình tàu, cùng các công cụ hỗ trợ thống kê và khuyến mãi.

## Tính năng nổi bật
- **Bán vé tàu**: Quản lý việc bán vé tàu một cách nhanh chóng và tiện lợi.
- **Đổi vé**: Hỗ trợ xử lý các yêu cầu đổi vé.
- **Hoàn vé**: Xử lý các trường hợp hoàn tiền vé.
- **Quản lý lịch trình**: Xem và cập nhật thông tin về lịch trình tàu chạy.
- **Khuyến mãi**: Quản lý và áp dụng các chương trình khuyến mãi cho khách hàng.
- **Quản lý khách hàng**: Theo dõi và cập nhật thông tin khách hàng.
- **Quản lý nhân viên**: Quản lý thông tin và phân công công việc cho nhân viên.
- **Thống kê**: Hiển thị các báo cáo và số liệu thống kê liên quan đến hoạt động vận hành tàu.
- **Quy định**: Hiển thị và cập nhật các quy định liên quan đến hoạt động vận tải.
- **Hướng dẫn sử dụng**: Cung cấp hướng dẫn chi tiết để người dùng dễ dàng làm quen với ứng dụng.

## Công nghệ sử dụng
- **Java**: Ngôn ngữ lập trình chính của ứng dụng.
- **Swing**: Xây dựng giao diện đồ họa người dùng (GUI).
- **SQL**: Quản lý cơ sở dữ liệu.
- **SendGrid API**: Gửi email tự động.
- **Twilio API**: Gửi tin nhắn SMS tự động.

## Hướng dẫn cài đặt
1. Clone dự án về máy:
    ```sh
    git clone https://github.com/imthaivu/tau-viet-express.git
    ```
2. Mở dự án bằng **IntelliJ IDEA**.
3. Cài đặt các thư viện cần thiết (nếu chưa có) như SendGrid, Twilio.
4. Cấu hình kết nối cơ sở dữ liệu trong tệp cấu hình của dự án.
5. Chạy ứng dụng trực tiếp từ **IntelliJ IDEA**.

## Hướng dẫn sử dụng
1. Đăng nhập vào ứng dụng với tài khoản đã được cấp quyền.
2. Sử dụng thanh điều hướng để truy cập các chức năng như bán vé, đổi vé, quản lý khách hàng,...
3. Thực hiện các thao tác như tạo vé mới, chỉnh sửa thông tin khách hàng hoặc xem báo cáo thống kê.

## Cấu hình
- **API SendGrid**: Thêm khóa API SendGrid vào lớp `EmailService`.
- **API Twilio**: Thêm khóa API Twilio vào lớp liên quan để gửi tin nhắn SMS.
- **Cơ sở dữ liệu**: Cập nhật thông tin kết nối cơ sở dữ liệu trong tệp cấu hình (bao gồm địa chỉ máy chủ, tên người dùng, mật khẩu).

## Góp ý và đóng góp
1. Fork dự án từ GitHub.
2. Tạo nhánh mới:
    ```sh
    git checkout -b feature-branch
    ```
3. Thực hiện thay đổi và commit:
    ```sh
    git commit -m "Thêm tính năng mới"
    ```
4. Đẩy nhánh mới lên repository:
    ```sh
    git push origin feature-branch
    ```
5. Tạo Pull Request để nhóm phát triển xem xét.

## Bản quyền
Dự án này được cấp phép theo giấy phép MIT. Vui lòng xem chi tiết trong tệp `LICENSE`.

## Liên hệ
Mọi thắc mắc hoặc đóng góp ý kiến xin vui lòng liên hệ:
- **Tên**: Tàu Việt Express
- **Email**: tauvietexpress@gmail.com
- **SĐT hỗ trợ**: 0377180010
