package control.impl;

import connectDB.connectDB_1;
import control.IDAOChucVu;
import entity.ChucVu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DAOChucVu extends UnicastRemoteObject implements IDAOChucVu {

    private EntityManager em = connectDB_1.getEntityManager();

    public DAOChucVu() throws RemoteException {
    }

    // Lấy danh sách chức vụ
    @Override
    public ArrayList<ChucVu> getDanhSachChucVu() throws RemoteException  {
        ArrayList<ChucVu> dsChucVu = new ArrayList<>();
        String jpql = "SELECT cv FROM ChucVu cv";
        TypedQuery<ChucVu> query = em.createQuery(jpql, ChucVu.class);
        dsChucVu.addAll(query.getResultList());
        return dsChucVu;
    }

    // Lấy chức vụ theo mã
    @Override
    public ChucVu getChucVuTheoMa(String maCV) throws RemoteException  {
        String jpql = "SELECT cv FROM ChucVu cv WHERE cv.maCV = :maCV";
        TypedQuery<ChucVu> query = em.createQuery(jpql, ChucVu.class);
        query.setParameter("maCV", maCV);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Thêm chức vụ
    @Transactional
    @Override
    public boolean themChucVu(ChucVu cv) throws RemoteException  {
        try {
            em.persist(cv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sửa chức vụ, trả về đối tượng ChucVu đã cập nhật
    @Transactional
    @Override
    public ChucVu suaChucVu(ChucVu cv) throws RemoteException  {
        String jpql = "UPDATE ChucVu cv SET cv.tenCV = :tenCV WHERE cv.maCV = :maCV";
        int updatedCount = em.createQuery(jpql)
                .setParameter("tenCV", cv.getTenCV())
                .setParameter("maCV", cv.getMaCV())
                .executeUpdate();
        return updatedCount > 0 ? cv : null;
    }


}