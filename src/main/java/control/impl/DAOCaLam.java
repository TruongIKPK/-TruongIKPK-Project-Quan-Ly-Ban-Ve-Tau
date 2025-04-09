package control.impl;

import connectDB.connectDB_1;
import control.IDAOCaLam;
import entity.CaLam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class DAOCaLam extends UnicastRemoteObject implements IDAOCaLam {
    private EntityManager em = connectDB_1.getEntityManager();

    protected DAOCaLam() throws RemoteException {
    }

    // lay danh sach ca lam
    @Override
    public ArrayList<CaLam> getDanhSachCaLam() throws RemoteException {
        ArrayList<CaLam> dsCaLam = new ArrayList<>();
        String jpql = "SELECT cl FROM CaLam cl";
        TypedQuery<CaLam> query = em.createQuery(jpql, CaLam.class);
        dsCaLam.addAll(query.getResultList());
        return dsCaLam;
    }
    // them ca lam
    @Override
    public boolean themCaLam(CaLam cl) throws RemoteException{
        try {
            em.getTransaction().begin();
            em.persist(cl);  // Thêm ca làm vào cơ sở dữ liệu
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    // sua ca lam tra ve doi tuong ca lam
    @Override
    public CaLam suaCaLam(CaLam cl)throws RemoteException {
        try {
            em.getTransaction().begin();
            CaLam existingCaLam = em.find(CaLam.class, cl.getMaCL());  // Tìm ca làm cũ theo mã
            if (existingCaLam != null) {
                existingCaLam.setTenCL(cl.getTenCL());
                existingCaLam.setGioBD(cl.getGioBD());
                existingCaLam.setGioKetThuc(cl.getGioKetThuc());
                em.getTransaction().commit();
                return existingCaLam;  // Trả về ca làm đã sửa
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
    // xoa ca lam
    @Override
    public boolean xoaCaLam(String maCL)throws RemoteException {
        try {
            em.getTransaction().begin();
            CaLam cl = em.find(CaLam.class, maCL);  // Tìm ca làm theo mã
            if (cl != null) {
                em.remove(cl);  // Xóa ca làm
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    // lay ca lam theo ma
    @Override
    public CaLam getCaLamTheoMa(String maCL)throws RemoteException {
        try {
            return em.find(CaLam.class, maCL);  // Tìm ca làm theo mã
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
