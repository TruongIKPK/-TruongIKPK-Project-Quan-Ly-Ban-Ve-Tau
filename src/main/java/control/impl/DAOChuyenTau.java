package control.impl;

import connectDB.connectDB_1;
import control.IDAOChuyenTau;
import entity.ChuyenTau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import service.ChuyenTauService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAOChuyenTau  extends UnicastRemoteObject implements IDAOChuyenTau {
    private  EntityManager em = connectDB_1.getEntityManager();
    public DAOChuyenTau() throws RemoteException {
    }
    @Override
    public ChuyenTau themChuyenTau(ChuyenTau chuyenTau) throws RemoteException{
        try {
            em.getTransaction().begin();
            ChuyenTauService chuyenTauService = new ChuyenTauService(em);
            chuyenTauService.persistChuyenTau(chuyenTau);
            em.getTransaction().commit();

            // Truy vấn lại ChuyenTau vừa thêm
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct WHERE ct.tau.maTau = :maTau AND ct.gaDi.maGa = :maGaDi AND ct.gaDen.maGa = :maGaDen AND ct.ngayGioDi = :ngayGioDi AND ct.ngayGioDen = :ngayGioDen",
                    ChuyenTau.class);
            query.setParameter("maTau", chuyenTau.getTau().getMaTau());
            query.setParameter("maGaDi", chuyenTau.getGaDi().getMaGa());
            query.setParameter("maGaDen", chuyenTau.getGaDen().getMaGa());
            query.setParameter("ngayGioDi", chuyenTau.getNgayGioDi());
            query.setParameter("ngayGioDen", chuyenTau.getNgayGioDen());

            return query.getSingleResult();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }


    }
    /**
     * Lấy danh sách tất cả các chuyến tàu từ cơ sở dữ liệu.
     *
     * @return Danh sách các chuyến tàu.
     */
    @Override
    public ArrayList<ChuyenTau> getDanhSachChuyenTau()throws RemoteException {
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            TypedQuery<ChuyenTau> query = em.createQuery("SELECT ct FROM ChuyenTau ct", ChuyenTau.class);
            dsChuyenTau = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả sang ArrayList
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsChuyenTau;
    }
    /**
     * Lấy danh sách chuyến tàu trong ngày hiện tại theo thứ tự ngày tăng dần.
     *
     * @return Danh sách chuyến tàu.
     */
    @Override
    public ArrayList<ChuyenTau> getDanhSachChuyenTauTrongNgay() throws RemoteException{
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct WHERE FUNCTION('DATE', ct.ngayGioDi) = FUNCTION('DATE', CURRENT_DATE) ORDER BY ct.ngayGioDi ASC",
                    ChuyenTau.class);
            dsChuyenTau = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả sang ArrayList
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }
    /**
     * Lấy danh sách 10 chuyến tàu sắp khởi hành.
     *
     * @return Danh sách chuyến tàu.
     */
    @Override
    public ArrayList<ChuyenTau> getDanhSachChuyenTauSapKhoiHanh()throws RemoteException {
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct WHERE ct.ngayGioDi > CURRENT_TIMESTAMP ORDER BY ct.ngayGioDi ASC",
                    ChuyenTau.class);
            query.setMaxResults(10); // Giới hạn kết quả trả về 10 chuyến tàu
            dsChuyenTau = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả sang ArrayList
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }
    /**
     * Lấy danh sách chuyến tàu theo ngày, ga đi và ga đến.
     *
     * @param ngayDi  Ngày khởi hành.
     * @param maGaDi  Mã ga đi.
     * @param maGaDen Mã ga đến.
     * @return Danh sách chuyến tàu.
     */
    @Override
    public ArrayList<ChuyenTau> getDanhSachChuyenTauTheoNgaymaGaDimaGaDen(LocalDate ngayDi, int maGaDi, int maGaDen)throws RemoteException {
        ArrayList<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            // Sử dụng hàm CAST để chuyển đổi giá trị ngayGioDi sang kiểu DATE trong truy vấn JPQL
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct WHERE CAST(ct.ngayGioDi AS date) = :ngayDi AND ct.gaDi.maGa = :maGaDi AND ct.gaDen.maGa = :maGaDen",
                    ChuyenTau.class);
            query.setParameter("ngayDi", ngayDi);
            query.setParameter("maGaDi", maGaDi);
            query.setParameter("maGaDen", maGaDen);

            dsChuyenTau = new ArrayList<>(query.getResultList()); // Chuyển đổi kết quả sang ArrayList
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsChuyenTau;
    }
    /**
     * Lấy chuyến tàu theo mã chuyến.
     *
     * @param maChuyen Mã chuyến cần tìm.
     * @return Chuyến tàu tìm được.
     */
    @Override
    public ChuyenTau getChuyenTauTheoMa(String maChuyen)throws RemoteException {
        ChuyenTau chuyenTau = null;
        try {
            TypedQuery<ChuyenTau> query = em.createQuery("SELECT ct FROM ChuyenTau ct WHERE ct.maChuyen = :maChuyen", ChuyenTau.class);
            query.setParameter("maChuyen", maChuyen);
            chuyenTau = query.getSingleResult();
        } catch (NoResultException e) {
            //không tìm thấy chuyến tàu
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chuyenTau;

    }
    /**
     * Lấy số lượng chỗ của chuyến
     * @param maChuyen Mã chuyến cần tìm
     * @return Số lượng chỗ
     */
    @Override
    public int getTongSoLuongChoCuaChuyen(String maChuyen) throws RemoteException{
            String jpql = "SELECT COUNT(cn) " +
                    "FROM ChuyenTau ct " +
                    "JOIN ct.tau t " +
                    "JOIN t.danhSachToa toa " +
                    "JOIN toa.danhSachChoNgoi cn " +
                    "WHERE ct.maChuyen = :maChuyen";

            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("maChuyen", maChuyen);

            Long result = query.getSingleResult();
            return result != null ? result.intValue() : 0;
        }
    /**
     * Cập nhật chuyến tàu.
     *
     * @param chuyenTau Chuyến tàu cần cập nhật.
     * @return true nếu cập nhật thành công, ngược lại false.
     */
    @Override
    public boolean capNhatChuyenTau(ChuyenTau chuyenTau) throws RemoteException{
        try {
            //tìm chuyến tàu theo mã chuyến
            ChuyenTau ct = em.find(ChuyenTau.class, chuyenTau.getMaChuyen());
            if (ct != null) {
                // Cập nhật các trường dữ liệu của chuyến tàu
                ct.setTau(chuyenTau.getTau());
                ct.setGaDi(chuyenTau.getGaDi());
                ct.setGaDen(chuyenTau.getGaDen());
                ct.setNgayGioDi(chuyenTau.getNgayGioDi());
                ct.setNgayGioDen(chuyenTau.getNgayGioDen());
                ct.setTrangThai(chuyenTau.getTrangThai());

                // Lưu lại thay đổi
                em.getTransaction().begin();
                em.merge(ct); // Cập nhật chuyến tàu trong cơ sở dữ liệu
                em.getTransaction().commit();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }
    /**
     * Lấy chuyến tàu theo mã tàu, mã ga đi, mã ga đến, ngày giờ đi và ngày giờ đến.
     * @param maTau Mã tàu
     * @param maGaDi Mã ga đi
     * @param maGaDen Mã ga đến
     * @param ngayGioDi Ngày giờ đi
     * @param ngayGioDen Ngày giờ đến
     * @return Chuyến tàu tìm được.
    //     */
    @Override
    public ChuyenTau getChuyenTauTheoMaTauMaGaDiMaGaDenNgayGioDiNgayGioDen(String maTau, int maGaDi, int maGaDen, LocalDateTime ngayGioDi, LocalDateTime ngayGioDen)throws RemoteException {
        try {
            // Sử dụng JPQL để lấy chuyến tàu
            TypedQuery<ChuyenTau> query = em.createQuery(
                    "SELECT ct FROM ChuyenTau ct " +
                            "WHERE ct.tau.maTau = :maTau " +
                            "AND ct.gaDi.maGa = :maGaDi " +
                            "AND ct.gaDen.maGa = :maGaDen " +
                            "AND ct.ngayGioDi = :ngayGioDi " +
                            "AND ct.ngayGioDen = :ngayGioDen", ChuyenTau.class);

            query.setParameter("maTau", maTau);
            query.setParameter("maGaDi", maGaDi);
            query.setParameter("maGaDen", maGaDen);
            query.setParameter("ngayGioDi", ngayGioDi);
            query.setParameter("ngayGioDen", ngayGioDen);

            List<ChuyenTau> result = query.getResultList();
            if (!result.isEmpty()) {
                return result.get(0); // Nếu có kết quả, trả về chuyến tàu đầu tiên
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
