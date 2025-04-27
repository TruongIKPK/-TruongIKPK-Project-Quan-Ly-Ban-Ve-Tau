package control.impl;

import connectDB.connectDB_1;
import control.IDAOLoaiToa;
import entity.LoaiToa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
public class DAOLoaiToa extends UnicastRemoteObject implements IDAOLoaiToa {
    private EntityManager em = connectDB_1.getEntityManager();
    public DAOLoaiToa() throws RemoteException {
    }
    @Override
    public boolean themLoaiToa(LoaiToa loaiToa)throws RemoteException{
        try {
            em.getTransaction().begin();
            em.persist(loaiToa);
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
    public LoaiToa suaLoaiToa(LoaiToa loaiToa) throws RemoteException {
        try {
            em.getTransaction().begin();
            LoaiToa updatedLoaiToa = em.merge(loaiToa);
            em.getTransaction().commit();
            return updatedLoaiToa;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return null;
    }
    @Override
    public boolean xoaLoaiToa(String maLT)throws RemoteException {
        try {
            em.getTransaction().begin();
            LoaiToa loaiToa = em.find(LoaiToa.class, maLT);
            if (loaiToa != null) {
                em.remove(loaiToa);
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
    @Override
    public ArrayList<LoaiToa> getDSLoaiToa() throws RemoteException{
            ArrayList<LoaiToa> dsLoaiToa = new ArrayList<>();
            try {
                String jpql = "SELECT lt FROM LoaiToa lt ORDER BY lt.tenLT";
                TypedQuery<LoaiToa> query = em.createQuery(jpql, LoaiToa.class);
                dsLoaiToa = new ArrayList<>(query.getResultList());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dsLoaiToa;
        }
    @Override
    public LoaiToa getLoaiToaTheoMa(String maLT)throws RemoteException {
        try {
            String jpql = "SELECT lt FROM LoaiToa lt WHERE lt.maLT = :maLT";
            TypedQuery<LoaiToa> query = em.createQuery(jpql, LoaiToa.class);
            query.setParameter("maLT", maLT);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}