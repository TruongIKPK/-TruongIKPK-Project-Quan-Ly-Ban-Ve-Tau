package control.impl;

import connectDB.connectDB_1;
import control.IDAOKhuyenMai;
import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import service.KhuyenMaiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOKhuyenMai extends UnicastRemoteObject implements IDAOKhuyenMai {

    public DAOKhuyenMai () throws RemoteException {}
    private EntityManager em = connectDB_1.getEntityManager();

    @Override
    public boolean themKhuyenMai(KhuyenMai km) throws RemoteException{
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhuyenMaiService khuyenMaiService = new KhuyenMaiService(em);
            khuyenMaiService.persistKhuyenMai(km);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<String> getDSDoiTuongKhuyenMai() throws RemoteException{
        String jpql = "SELECT DISTINCT km.doiTuong FROM KhuyenMai km";

        TypedQuery<String> query = em.createQuery(jpql, String.class);

        List<String> dsDoiTuong = query.getResultList();

        return new ArrayList<>(dsDoiTuong);
    }

    @Override
    public KhuyenMai suaKhuyenMai(KhuyenMai km) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();

            String jpql = "UPDATE KhuyenMai km " +
                    "SET km.ngayBD = :ngayBD, " +
                    "km.ngayKT = :ngayKT, " +
                    "km.phanTramKM = :phanTramKM " +
                    "WHERE km.maKM = :maKM";

            Query query = em.createQuery(jpql);
            query.setParameter("ngayBD", km.getNgayBD());
            query.setParameter("ngayKT", km.getNgayKT());
            query.setParameter("phanTramKM", km.getPhanTramKM());
            query.setParameter("maKM", km.getMaKM());

            int kmToUpdated = query.executeUpdate();

            if (kmToUpdated > 0) {
                tr.commit();
                return km;
            } else {
                tr.rollback();
            }
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean xoaKhuyenMai(String maKM) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            KhuyenMai km = em.find(KhuyenMai.class, maKM);
            em.remove(km);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public ArrayList<KhuyenMai> getDSKhuyenMai() throws RemoteException{
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        String jpql = "SELECT km FROM KhuyenMai km";

        try {
            dsKhuyenMai = (ArrayList<KhuyenMai>) em.createQuery(jpql, KhuyenMai.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }

   @Override
   public ArrayList<KhuyenMai> getKhuyenMaiTheoMa(String maKM) throws RemoteException{
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        String jpql = "SELECT km FROM KhuyenMai km WHERE km.maKM = :maKM";

        try {
            dsKhuyenMai = (ArrayList<KhuyenMai>) em.createQuery(jpql, KhuyenMai.class)
                    .setParameter("maKM", maKM)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKhuyenMai;
    }

    @Override
    public ArrayList<KhuyenMai> getDSMaKhuyenMaiTheoMaLike(String maKM) throws RemoteException{
        ArrayList<KhuyenMai> dsMaKM = new ArrayList<>();
        String jpql = "SELECT km FROM KhuyenMai km WHERE km.maKM LIKE :maKM";
        try {
            dsMaKM = (ArrayList<KhuyenMai>) em.createQuery(jpql, KhuyenMai.class)
                    .setParameter("maKM", "%" + maKM + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsMaKM;
    }

    @Override
    public ArrayList<KhuyenMai> getKhuyenMaiTheoNgay(LocalDate ngay) throws RemoteException{
        ArrayList<KhuyenMai> dsKMTheoNgay = new ArrayList<>();
        String jdql = "SELECT km FROM KhuyenMai km WHERE :ngay BETWEEN km.ngayBD AND km.ngayKT";

        try {
            dsKMTheoNgay = (ArrayList<KhuyenMai>) em.createQuery(jdql, KhuyenMai.class)
                    .setParameter("ngay", ngay)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKMTheoNgay;
    }

    @Override
    public void capNhatTrangThaiDaGuiThongBao(String maKM) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        String jdql = "UPDATE KhuyenMai km SET km.daGuiThongBao = 1 WHERE km.maKM = :maKM";

        try {
            tr.begin();
            Query query = em.createQuery(jdql);
            query.setParameter("maKM", maKM);
            query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
}