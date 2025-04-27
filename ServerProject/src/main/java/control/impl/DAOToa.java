package control.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.connectDB_1;
import control.IDAOToa;
import entity.ChoNgoi;
import entity.LoaiToa;
import entity.Tau;
import entity.Toa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DAOToa extends UnicastRemoteObject implements IDAOToa {
    private  EntityManager em = connectDB_1.getEntityManager();
    public DAOToa() throws RemoteException {
    }
    @Override
    public boolean xoaToa(String maToa) throws RemoteException{
        try {
            em.getTransaction().begin();
            Toa toa = em.find(Toa.class, maToa);
            if (toa != null) {
                em.remove(toa);
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
     * Sửa thông tin một toa trong cơ sở dữ liệu.
     * @param toa đối tượng toa chứa thông tin cần cập nhật.
     * @return đối tượng toa đã được sửa nếu thành công, ngược lại null.
     */
    @Override
    public Toa suaToa(Toa toa) throws RemoteException{
        try {
            em.getTransaction().begin();
            Toa existingToa = em.find(Toa.class, toa.getMaToa());
            if (existingToa != null) {
                existingToa.setSoLuongCho(toa.getSoLuongCho());
                existingToa.setTau(toa.getTau());
                existingToa.setLoaiToa(toa.getLoaiToa());
                em.merge(existingToa);
                em.getTransaction().commit();
                return existingToa;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return null;
    }
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
            TypedQuery<Toa> query = em.createQuery(jpql, Toa.class);
            query.setParameter("maTau", maTau);
            dsToaTheoTau = new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsToaTheoTau;
    }
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
            TypedQuery<Toa> query = em.createQuery(jpql, Toa.class);
            query.setParameter("maLoaiToa", maLoaiToa);
            dsToa = new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsToa;
    }
    /**
     * Lấy toa theo mã toa.
     * @param maToa mã toa cần tìm.
     * @return đối tượng toa nếu tìm thấy, ngược lại null.
     */
    @Override
    public Toa getToaTheoMa(String maToa) throws RemoteException{
        try {
            String jpql = "SELECT t FROM Toa t WHERE t.maToa = :maToa";
            TypedQuery<Toa> query = em.createQuery(jpql, Toa.class);
            query.setParameter("maToa", maToa);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Tạo đối tượng Toa từ ResultSet.
     * @param rs ResultSet chứa thông tin toa.
     * @return đối tượng Toa.
     * @throws SQLException nếu xảy ra lỗi khi đọc dữ liệu từ ResultSet.
     */
    @Override
    public Toa taoToaTuResultSet(ResultSet rs) throws RemoteException {
        String maToa = null;
        try {
            maToa = rs.getString("maToa");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String maTau = null;
        try {
            maTau = rs.getString("maTau");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String maLoaiToa = null;
        try {
            maLoaiToa = rs.getString("maLoaiToa");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int soLuongCho = 0;
        try {
            soLuongCho = rs.getInt("soLuongCho");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Tau tau = em.find(Tau.class, maTau);
        LoaiToa loaiToa = em.find(LoaiToa.class, maLoaiToa);
        ArrayList<ChoNgoi> danhSachChoNgoi = new ArrayList<>(em.createQuery(
                        "SELECT cn FROM ChoNgoi cn WHERE cn.toa.maToa = :maToa", ChoNgoi.class)
                .setParameter("maToa", maToa)
                .getResultList());

        return new Toa(maToa, tau, loaiToa, soLuongCho, danhSachChoNgoi);
    }
}

