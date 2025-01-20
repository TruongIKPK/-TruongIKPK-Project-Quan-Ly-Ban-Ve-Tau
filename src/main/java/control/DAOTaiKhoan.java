package control;

import connectDB.ConnectDB;
import entity.TaiKhoan;
import enums.ETrangThaiTaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class DAOTaiKhoan {
    /*Bảng Tài Khoản
Tên cột	Kiểu dữ liệu	Ràng buộc
maTK	VARCHAR(11)	PRIMARY KEY
matKhau	VARCHAR(50)	Min length 8, chứa số, ký tự đặc biệt
trangThai	NVARCHAR(20)	DEFAULT 'Kích hoạt', IN ('Kích hoạt', 'Bị khóa')*/
    //them tai khoan
//    public static boolean themTaiKhoan(TaiKhoan tk) {
//        String sql = "INSERT INTO TaiKhoan VALUES(?, ?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, tk.getMaTK());
//            stmt.setString(2, tk.getMatKhau());
//            stmt.setString(3, tk.getTrangThai());
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    private static EntityManager em;

    public DAOTaiKhoan(EntityManager entityManager) {
        this.em = entityManager;
    }



    public static TaiKhoan login(String maTK, String matKhau) {
        String jpql = "SELECT tk FROM TaiKhoan tk WHERE tk.maTK = :maTK AND tk.trangThai = :trangThai";
        TypedQuery<TaiKhoan> query = em.createQuery(jpql, TaiKhoan.class);
        query.setParameter("maTK", maTK);
        query.setParameter("trangThai", ETrangThaiTaiKhoan.KICH_HOAT.getTrangThai());

        try {
            TaiKhoan tk = query.getSingleResult();
            System.out.println("Mật khẩu DB: " + tk.getMatKhauHash());

            if (BCrypt.checkpw(matKhau, tk.getMatKhauHash())) {
                return tk;
            } else {
                System.out.println("Mật khẩu không đúng!");
            }
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy tài khoản!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //chuyen trang thai tai khoan
    public static boolean chuyenTrangThaiTaiKhoan(String maTK, String trangThai) {
        String sql = "UPDATE TaiKhoan SET trangThai = ? WHERE maTK = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, trangThai);
            stmt.setString(2, maTK);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // get ds tai khoan
    public static ArrayList<TaiKhoan> getDSTaiKhoan() {
        //code
        ArrayList<TaiKhoan> dsTaiKhoan = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";

        try (
                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            //code
            while (rs.next()) {
                String maTK = rs.getString("maTK");
                String matKhau = rs.getString("matKhau");
                String trangThai = rs.getString("trangThai");

                // Tạo đối tượng TaiKhoan và thêm vào danh sách
                TaiKhoan tk = new TaiKhoan(maTK, matKhau, trangThai);
                dsTaiKhoan.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTaiKhoan;
    }


    // get tai khoan theo maTK
    public static TaiKhoan getTaiKhoanTheoMa(String maTK) {
        TaiKhoan tk = null;
        String sql = "SELECT * FROM TaiKhoan WHERE maTK = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maTK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String ma = rs.getString("maTK");
                    String matKhau = rs.getString("matKhau");
                    String trangThai = rs.getString("trangThai");
                    tk = new TaiKhoan(ma, matKhau, trangThai);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }

    //login check tk mat khau va trang thai lam viec khong cho dang nhap neu trang thai bi khoa
//    public static TaiKhoan login(String maTK, String matKhau) {
//        String sql = "SELECT * FROM TaiKhoan WHERE maTK = ? AND trangThai = ?";
//        try (
//                PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//        ) {
//            stmt.setString(1, maTK);
//            stmt.setString(2, ETrangThaiTaiKhoan.KICH_HOAT.getTrangThai());
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                String matKhauHash = rs.getString("matKhau");
//                // Kiểm tra mật khẩu
//                if (BCrypt.checkpw(matKhau, matKhauHash)) {
//                    // Nếu đúng mật khẩu, trả về đối tượng TaiKhoan
//                    return new TaiKhoan(
//                            rs.getString("maTK"),
//                            rs.getString("matKhau"),
//                            rs.getString("trangThai")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null; // Trả về null nếu không đăng nhập được
//    }





    // sua tai khoan
    public static TaiKhoan suaTaiKhoan(TaiKhoan tk) {
        String sql = "UPDATE TaiKhoan SET matKhau = ?, trangThai = ? WHERE maTK = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, tk.getMatKhauHash());
            stmt.setString(2, tk.getTrangThai());
            stmt.setString(3, tk.getMaTK());
            if (stmt.executeUpdate() > 0) {
                return tk;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean saveResetToken(String maTK, String resetToken) {
        String sql = "UPDATE TaiKhoan SET resetToken = ? WHERE maTK = ?";
        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, resetToken);
            stmt.setString(2, maTK);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

// test cac ham get data (read) trong main

