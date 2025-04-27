package control.impl;

import connectDB.connectDB_1;
import control.IDAOTaiKhoan;
import entity.TaiKhoan;
import enums.ETrangThaiTaiKhoan;
import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DAOTaiKhoan extends UnicastRemoteObject implements IDAOTaiKhoan {
    private EntityManager em = connectDB_1.getEntityManager();
    public DAOTaiKhoan() throws RemoteException {
    }
    @Override
    public TaiKhoan login(String maTK, String matKhau) throws RemoteException  {
        em.clear();
        String jpql = "SELECT tk FROM TaiKhoan tk WHERE tk.maTK = :maTK AND tk.trangThai = :trangThai";
        TypedQuery<TaiKhoan> query = em.createQuery(jpql, TaiKhoan.class);
        query.setParameter("maTK", maTK);
        query.setParameter("trangThai", ETrangThaiTaiKhoan.KICH_HOAT.getTrangThai());
        try {
            TaiKhoan tk = query.getSingleResult();
            System.out.println("Mã tài khoản lấy lên là"+tk.getMaTK()+" "+tk.getTrangThai()+" "+tk.getMatKhauHash());
            System.out.println("Mật khẩu người dùng nhập:"+ matKhau);
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
    @Override
    public boolean chuyenTrangThaiTaiKhoan(String maTK, String trangThai) throws RemoteException {
        String jpql = "UPDATE TaiKhoan tk SET tk.trangThai = :trangThai WHERE tk.maTK = :maTK";
        try {
            // Tạo query với JPQL
            Query query = em.createQuery(jpql);
            query.setParameter("trangThai", trangThai);
            query.setParameter("maTK", maTK);

            // Thực hiện truy vấn và kiểm tra số lượng bản ghi bị ảnh hưởng
            int result = query.executeUpdate();

            // Nếu có bản ghi nào bị ảnh hưởng, trả về true
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //get ds tai khoan
    @Override
    public ArrayList<TaiKhoan> getDSTaiKhoan() throws RemoteException {
        String jpql = "SELECT tk FROM TaiKhoan tk";
        TypedQuery<TaiKhoan> query = em.createQuery(jpql, TaiKhoan.class);
        return new ArrayList<>(query.getResultList());
    }
    //get tai khoan theo maTK
    @Override
    public TaiKhoan getTaiKhoanTheoMa(String maTK) throws RemoteException {
        String jpql = "SELECT tk FROM TaiKhoan tk WHERE tk.maTK = :maTK";
        TypedQuery<TaiKhoan> query = em.createQuery(jpql, TaiKhoan.class);
        query.setParameter("maTK", maTK);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy tài khoản!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //sua tai khoan
    @Override
//    public TaiKhoan suaTaiKhoan(TaiKhoan tk) throws RemoteException {
//        EntityTransaction transaction = em.getTransaction();
//        String jpql = "UPDATE TaiKhoan tk SET tk.matKhau = :matKhau, tk.trangThai = :trangThai WHERE tk.maTK = :maTK";
//        try {
//            transaction.begin();
//            // Tạo query với JPQL
//            System.out.println("Mat khau: " + tk);
//            Query query = em.createQuery(jpql);
//            query.setParameter("matKhau", tk.getMatKhauHash());
//            query.setParameter("trangThai", tk.getTrangThai());
//            query.setParameter("maTK", tk.getMaTK());
//
//            // Thực hiện truy vấn và kiểm tra số lượng bản ghi bị ảnh hưởng
//            int result = query.executeUpdate();
//            transaction.commit();
//            // Nếu có bản ghi nào bị ảnh hưởng, trả về true
//            if (result > 0) {
//                return tk;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public TaiKhoan suaTaiKhoan(TaiKhoan tk) throws RemoteException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            TaiKhoan updatedTk = em.merge(tk);
            transaction.commit();
            return updatedTk;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RemoteException("Lỗi khi cập nhật tài khoản", e);
        }
    }
}


