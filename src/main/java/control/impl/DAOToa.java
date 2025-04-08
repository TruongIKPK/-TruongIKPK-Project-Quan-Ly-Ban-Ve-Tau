package control.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import control.IDAOToa;
import entity.ChoNgoi;
import entity.LoaiToa;
import entity.Tau;
import entity.Toa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import service.ToaService;

/**
 * @Dự án: tau-viet-express
 * @Class: DAOToa
 * @Tạo vào ngày: 10/15/2024
 * @Tác giả: Thai
 */
public class DAOToa extends UnicastRemoteObject implements IDAOToa {

    private  ArrayList<Toa> danhSachToa;

//    /**
//     * Lấy danh sách tất cả các toa.
//     * @return danh sách các toa.
//     */
//    public static ArrayList<Toa> getDanhSachToa() {
//        if (danhSachToa != null) {
//            return danhSachToa;
//        }
//
//        danhSachToa = new ArrayList<>();
//        String sql = "SELECT * FROM Toa ORDER BY RIGHT(maToa, 2)";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                Toa toa = taoToaTuResultSet(rs);
//                danhSachToa.add(toa);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//
//        return danhSachToa;
//    }
    /**
     * Lấy danh sách tất cả các toa.
     * @return danh sách các toa.
     */

    @PersistenceContext
    private  EntityManager em1;

    private ToaService toaService;

    public DAOToa(EntityManager em) throws RemoteException {
        this.em1 = em;
    }

    /**

//    public static boolean themToa(Toa toa) {
//        try {
//            ToaService toaService = new ToaService();
//            toaService.persistToa(toa);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static ArrayList<Toa> getDanhSachToa() {
        if (danhSachToa != null) {
            return danhSachToa;
        }

        danhSachToa = new ArrayList<>();
        String jpql = "SELECT t FROM Toa t ORDER BY t.maToa";  // Sắp xếp theo mã toa

        try {
            TypedQuery<Toa> query = em1.createQuery(jpql, Toa.class);
            danhSachToa = new ArrayList<>(query.getResultList());

            // Sắp xếp lại danh sách theo yêu cầu nếu cần (tương tự như RIGHT(maToa, 2) trong SQL)
            danhSachToa.sort(Comparator.comparing(toa -> toa.getMaToa().substring(toa.getMaToa().length() - 2)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSachToa;
    }

//    /**
//     * Thêm một toa mới vào cơ sở dữ liệu.
//     * @param toa đối tượng toa cần thêm.
//     * @return true nếu thêm thành công, ngược lại false.
//     */
//    public static boolean themToa(Toa toa) {
//        String sql = "INSERT INTO Toa(maToa, soLuongCho, maTau, maLoaiToa) VALUES(?, ?, ?, ?)";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, toa.getMaToa());
//            stmt.setInt(2, toa.getSoLuongCho());
//            stmt.setString(3, toa.getTau().getMaTau());
//            stmt.setString(4, toa.getLoaiToa().getMaLT());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

//    /**
//     * Xóa toa khỏi cơ sở dữ liệu theo mã.
//     * @param maToa mã của toa cần xóa.
//     * @return true nếu xóa thành công, ngược lại false.
//     */
//    public static boolean xoaToa(String maToa) {
//        String sql = "DELETE FROM Toa WHERE maToa = ?";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maToa);
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
    /**
     * Xóa toa khỏi cơ sở dữ liệu theo mã.
     * @param maToa mã của toa cần xóa.
     * @return true nếu xóa thành công, ngược lại false.
     */
    @Override
    public boolean xoaToa(String maToa) throws RemoteException{
        try {
            em1.getTransaction().begin();
            Toa toa = em1.find(Toa.class, maToa);
            if (toa != null) {
                em1.remove(toa);
                em1.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (em1.getTransaction().isActive()) {
                em1.getTransaction().rollback();
            }
        }
        return false;
    }

//    /**
//     * Sửa thông tin một toa trong cơ sở dữ liệu.
//     * @param toa đối tượng toa chứa thông tin cần cập nhật.
//     * @return đối tượng toa đã được sửa nếu thành công, ngược lại null.
//     */
//    public static Toa suaToa(Toa toa) {
//        String sql = "UPDATE Toa SET soLuongCho = ?, maTau = ?, maLoaiToa = ? WHERE maToa = ?";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setInt(1, toa.getSoLuongCho());
//            stmt.setString(2, toa.getTau().getMaTau());
//            stmt.setString(3, toa.getLoaiToa().getMaLT());
//            stmt.setString(4, toa.getMaToa());
//
//            if (stmt.executeUpdate() > 0) {
//                return toa;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
    /**
     * Sửa thông tin một toa trong cơ sở dữ liệu.
     * @param toa đối tượng toa chứa thông tin cần cập nhật.
     * @return đối tượng toa đã được sửa nếu thành công, ngược lại null.
     */
    @Override
    public Toa suaToa(Toa toa) throws RemoteException{
        try {
            em1.getTransaction().begin();
            Toa existingToa = em1.find(Toa.class, toa.getMaToa());
            if (existingToa != null) {
                existingToa.setSoLuongCho(toa.getSoLuongCho());
                existingToa.setTau(toa.getTau());
                existingToa.setLoaiToa(toa.getLoaiToa());
                em1.merge(existingToa);
                em1.getTransaction().commit();
                return existingToa;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (em1.getTransaction().isActive()) {
                em1.getTransaction().rollback();
            }
        }
        return null;
    }
//    /**
//     * Lấy danh sách toa theo mã tàu.
//     * @param maTau mã tàu cần lấy danh sách toa.
//     * @return danh sách các toa của tàu.
//     */
//    public static ArrayList<Toa> getToaTheoTau(String maTau) {
//        ArrayList<Toa> dsToaTheoTau = new ArrayList<>();
//
//        for (Toa t : getDanhSachToa()) {
//            if (t.getTau().getMaTau().equals(maTau)) {
//                dsToaTheoTau.add(t);
//            }
//        }
//
//        return dsToaTheoTau;
//    }
    /**
     * Lấy danh sách toa theo mã tàu.
     * @param maTau mã tàu cần lấy danh sách toa.
     * @return danh sách các toa của tàu.
     */
    @Override
    public ArrayList<Toa> getToaTheoTau(String maTau) throws RemoteException{
        ArrayList<Toa> dsToaTheoTau = new ArrayList<>();
        try {
            String jpql = "SELECT t FROM Toa t WHERE t.tau.maTau = :maTau";
            TypedQuery<Toa> query = em1.createQuery(jpql, Toa.class);
            query.setParameter("maTau", maTau);
            dsToaTheoTau = new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsToaTheoTau;
    }
//    /**
//     * Lấy danh sách toa theo mã loại toa.
//     * @param maLoaiToa mã loại toa cần lấy danh sách toa.
//     * @return danh sách các toa thuộc loại toa đó.
//     */
//    public static ArrayList<Toa> getToaTheoLoaiToa(String maLoaiToa) {
//        ArrayList<Toa> dsToa = new ArrayList<>();
//        String sql = "SELECT * FROM Toa WHERE maLoaiToa = ?";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maLoaiToa);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Toa toa = taoToaTuResultSet(rs);
//                dsToa.add(toa);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return dsToa;
//    }

    /**
     * Lấy danh sách toa theo mã loại toa.
     * @param maLoaiToa mã loại toa cần lấy danh sách toa.
     * @return danh sách các toa thuộc loại toa đó.
     */
    @Override
    public ArrayList<Toa> getToaTheoLoaiToa(String maLoaiToa)throws RemoteException {
        ArrayList<Toa> dsToa = new ArrayList<>();
        try {
            String jpql = "SELECT t FROM Toa t WHERE t.loaiToa.maLT = :maLoaiToa";
            TypedQuery<Toa> query = em1.createQuery(jpql, Toa.class);
            query.setParameter("maLoaiToa", maLoaiToa);
            dsToa = new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsToa;
    }


//    /**
//     * Lấy toa theo mã toa.
//     * @param maToa mã toa cần tìm.
//     * @return đối tượng toa nếu tìm thấy, ngược lại null.
//     */
//    public static Toa getToaTheoMa(String maToa) {
//        String sql = "SELECT * FROM Toa WHERE maToa = ?";
//
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maToa);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                return taoToaTuResultSet(rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    /**
     * Lấy toa theo mã toa.
     * @param maToa mã toa cần tìm.
     * @return đối tượng toa nếu tìm thấy, ngược lại null.
     */
    @Override
    public Toa getToaTheoMa(String maToa) throws RemoteException{
        try {
            String jpql = "SELECT t FROM Toa t WHERE t.maToa = :maToa";
            TypedQuery<Toa> query = em1.createQuery(jpql, Toa.class);
            query.setParameter("maToa", maToa);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    /**
//     * Tạo đối tượng Toa từ ResultSet.
//     * @param rs ResultSet chứa thông tin toa.
//     * @return đối tượng Toa.
//     * @throws SQLException nếu xảy ra lỗi khi đọc dữ liệu từ ResultSet.
//     */
//    private static Toa taoToaTuResultSet(ResultSet rs) throws SQLException {
//        String maToa = rs.getString("maToa");
//        String maTau = rs.getString("maTau");
//        String maLoaiToa = rs.getString("maLoaiToa");
//        int soLuongCho = rs.getInt("soLuongCho");
//
//        Tau tau = new Tau(maTau);
//        LoaiToa loaiToa = DAOLoaiToa.getLoaiToaTheoMa(maLoaiToa);
//        ArrayList<ChoNgoi> danhSachChoNgoi = DAOChoNgoi.getDSChoNgoiTheoToa(maToa);
//
//        return new Toa(maToa, tau, loaiToa, soLuongCho, danhSachChoNgoi);
//    }
//}

    /**
     * Tạo đối tượng Toa từ ResultSet.
     * @param rs ResultSet chứa thông tin toa.
     * @return đối tượng Toa.
     * @throws SQLException nếu xảy ra lỗi khi đọc dữ liệu từ ResultSet.
     */
    @Override
    public Toa taoToaTuResultSet(ResultSet rs) throws SQLException {
        String maToa = rs.getString("maToa");
        String maTau = rs.getString("maTau");
        String maLoaiToa = rs.getString("maLoaiToa");
        int soLuongCho = rs.getInt("soLuongCho");

        Tau tau = em1.find(Tau.class, maTau);
        LoaiToa loaiToa = em1.find(LoaiToa.class, maLoaiToa);
        ArrayList<ChoNgoi> danhSachChoNgoi = new ArrayList<>(em1.createQuery(
                        "SELECT cn FROM ChoNgoi cn WHERE cn.toa.maToa = :maToa", ChoNgoi.class)
                .setParameter("maToa", maToa)
                .getResultList());

        return new Toa(maToa, tau, loaiToa, soLuongCho, danhSachChoNgoi);
    }
}

