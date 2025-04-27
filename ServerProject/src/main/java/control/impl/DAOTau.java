package control.impl;

import connectDB.connectDB_1;
import control.IDAOTau;
import entity.Tau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DAOTau extends UnicastRemoteObject implements IDAOTau {
    private  ArrayList<Tau> dsTau;


    private  EntityManager em = connectDB_1.getEntityManager();

    public DAOTau() throws RemoteException {
    }
    @Override
    public boolean themTau(Tau tau) throws RemoteException {
        try {
            em.getTransaction().begin();
            em.persist(tau);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }
    @Override
    public boolean capNhatTrangThaiTau(String maTau, String trangThai)throws RemoteException {
        try {
            em.getTransaction().begin();
            String jpql = "UPDATE Tau t SET t.trangThai = :trangThai WHERE t.maTau = :maTau";
            int updatedCount = em.createQuery(jpql)
                    .setParameter("trangThai", trangThai)
                    .setParameter("maTau", maTau)
                    .executeUpdate();
            em.getTransaction().commit();
            return updatedCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }
    @Override
    public boolean capNhatTau(Tau tau)  throws RemoteException{
        try {
            em.getTransaction().begin();
            String jpql = "UPDATE Tau t SET t.tenTau = :tenTau, t.trangThai = :trangThai WHERE t.maTau = :maTau";
            int updatedCount = em.createQuery(jpql)
                    .setParameter("tenTau", tau.getTenTau())
                    .setParameter("trangThai", tau.getTrangThai())
                    .setParameter("maTau", tau.getMaTau())
                    .executeUpdate();
            em.getTransaction().commit();
            return updatedCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;
    }
    @Override
    public ArrayList<Tau> getDSTau() throws RemoteException {
        if (dsTau != null) {
            // Nếu tàu đã được khởi tạo thì trả về danh sách tàu
            return dsTau;
        }
        dsTau = new ArrayList<>(); // Nếu tàu chưa được khởi tạo thì khởi tạo mới
        try {
            // Truy vấn JPQL lấy danh sách tàu
            String jpql = "SELECT t FROM Tau t";
            TypedQuery<Tau> query = em.createQuery(jpql, Tau.class);

            // Lấy danh sách tàu từ cơ sở dữ liệu
            List<Tau> tauList = query.getResultList();

            // Duyệt qua từng tàu và lấy thêm thông tin về các toa
            for (Tau tau : tauList) {
                tau.getDanhSachToa().size(); // Initialize the collection
            }

            // Thêm tất cả tàu vào danh sách dsTau
            dsTau.addAll(tauList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsTau;
    }
    @Override
    public Tau getTauTheoMa(String maTau)  throws RemoteException{
        try {
            //String jpql = "SELECT t FROM Tau t WHERE t.maTau = :maTau";
            String jpql = "SELECT t FROM Tau t LEFT JOIN FETCH t.danhSachToa WHERE t.maTau = :maTau";
            TypedQuery<Tau> query = em.createQuery(jpql, Tau.class);
            query.setParameter("maTau", maTau);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}