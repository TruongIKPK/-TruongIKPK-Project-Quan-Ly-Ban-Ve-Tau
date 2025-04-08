package control.impl;

import control.IDAOLoaiVe;
import entity.LoaiVe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class DAOLoaiVe extends UnicastRemoteObject implements IDAOLoaiVe {

    public DAOLoaiVe() throws RemoteException {}
    private EntityManager em;
    public DAOLoaiVe(EntityManager em) throws RemoteException {
        super();

        this.em = em;
    }
    @Override
    public boolean themLoaiVe(LoaiVe lv) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(lv);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean xoaLoaiVe(String maLV) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiVe lv = em.find(LoaiVe.class, maLV);
            if (lv != null) {
                em.remove(lv);
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return false;
    }

    @Override
    public LoaiVe suaLoaiVe(LoaiVe lv) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiVe existingLoaiVe = em.find(LoaiVe.class, lv.getMaLV());
            if (existingLoaiVe != null) {
                existingLoaiVe.setTenLV(lv.getTenLV());
                em.merge(existingLoaiVe);
                tr.commit();
                return existingLoaiVe;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tr.isActive()) {
                tr.rollback();
            }
        }
        return null;
    }


    @Override
    public ArrayList<LoaiVe> layDSLoaiVe()  throws RemoteException{
        ArrayList<LoaiVe> dsLoaiVe = new ArrayList<>();
        String jpql = "SELECT lv FROM LoaiVe lv";
        try {
            dsLoaiVe = (ArrayList<LoaiVe>) em.createQuery(jpql, LoaiVe.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiVe;
    }


    @Override
    public LoaiVe layLoaiVeTheoMa(String maLV) throws RemoteException{
        LoaiVe loaiVe = null;
        String jpql = "SELECT lv FROM LoaiVe lv WHERE lv.maLV = :maLoai";
        try {
            loaiVe = em.createQuery(jpql, LoaiVe.class)
                    .setParameter("maLoai", maLV)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiVe;
    }
}
