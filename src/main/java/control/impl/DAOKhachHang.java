package control.impl;

import connectDB.connectDB_1;
import control.IDAOKhachHang;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import service.KhachHangService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DAOKhachHang extends UnicastRemoteObject implements IDAOKhachHang {

    private  EntityManager em = connectDB_1.getEntityManager();

    public DAOKhachHang() throws RemoteException {
    }

    @Override
    public  boolean themKhachHang(KhachHang kh)throws RemoteException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            KhachHangService khachHangService = new KhachHangService(em);
            khachHangService.persistKhachHang(kh);

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
    public  KhachHang suaKhachHang(KhachHang kh)throws RemoteException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            KhachHang existingKhachHang = em.find(KhachHang.class, kh.getMaKH());
            if (existingKhachHang != null) {
                existingKhachHang.setTenKH(kh.getTenKH());
                if (kh.getCCCD() == null || kh.getCCCD().trim().isEmpty()) {
                    existingKhachHang.setCCCD(null);
                } else {
                    existingKhachHang.setCCCD(kh.getCCCD());
                }
                existingKhachHang.setSdt(kh.getSdt());
                existingKhachHang.setEmail(kh.getEmail());
                existingKhachHang.setNgaySinh(kh.getNgaySinh());
                existingKhachHang.setDoiTuong(kh.getDoiTuong());
                em.merge(existingKhachHang);
            }
            transaction.commit();
            return existingKhachHang;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public  KhachHang timKhachHang(String CCCD, String sdt)throws RemoteException {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.CCCD = :CCCD AND kh.sdt = :sdt", KhachHang.class);
            query.setParameter("CCCD", CCCD);
            query.setParameter("sdt", sdt);

            List<KhachHang> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                return null; // Không có kết quả trả về
            } else {
                return resultList.get(0); // Trả về kết quả đầu tiên
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public  ArrayList<KhachHang> layDanhSachKhachHang()throws RemoteException {
        try {
            TypedQuery<KhachHang> query = em.createQuery("SELECT kh FROM KhachHang kh", KhachHang.class);
            List<KhachHang> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public  KhachHang layKhachHangTheoCCCD(String CCCD)throws RemoteException {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.CCCD = :CCCD", KhachHang.class);
            query.setParameter("CCCD", CCCD);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public  KhachHang layKhachHangTheoSdt(String sdt)throws RemoteException {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.sdt = :sdt", KhachHang.class);
            query.setParameter("sdt", sdt);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public  KhachHang layKhachHangTheoMa(String maKH)throws RemoteException {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.maKH = :maKH", KhachHang.class);
            query.setParameter("maKH", maKH);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public  KhachHang timKhachHangTheoEmail(String email)throws RemoteException {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.email = :email", KhachHang.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public  ArrayList<KhachHang> layKhachHangTheoDoiTuong(String doiTuong)throws RemoteException {
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT kh FROM KhachHang kh WHERE kh.doiTuong = :doiTuong", KhachHang.class);
            query.setParameter("doiTuong", doiTuong);
            List<KhachHang> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
