package control.impl;

import control.IDAOLoaiCho;
import entity.LoaiCho;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class DAOLoaiCho extends UnicastRemoteObject implements IDAOLoaiCho {

    public DAOLoaiCho() throws RuntimeException, RemoteException {
        super();

    }
    private EntityManager em;
    public DAOLoaiCho(EntityManager em) throws RemoteException {
        super();
        this.em = em;
    }

    @Override
    public boolean themLoaiCho(LoaiCho lc) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(lc);
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
    public ArrayList<LoaiCho> getDSLoaiCho() throws RemoteException{
        ArrayList<LoaiCho> dsLoaiCho = new ArrayList<>();
        String sqdl = "SELECT lc FROM LoaiCho lc";
        try {
            TypedQuery<LoaiCho> query = em.createQuery(sqdl, LoaiCho.class);
            dsLoaiCho = (ArrayList<LoaiCho>) query.getResultList();
            return dsLoaiCho;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public LoaiCho getLoaiChoByID(String maLC) throws RemoteException {
        String jqdl = "SELECT lc FROM LoaiCho lc WHERE lc.maLC = :maLC";
        try {
            TypedQuery<LoaiCho> query = em.createQuery(jqdl, LoaiCho.class);
            query.setParameter("maLC", maLC);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LoaiCho suaLoaiCho(LoaiCho lc) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiCho existingLoaiCho = em.find(LoaiCho.class, lc.getMaLC());
            if (existingLoaiCho != null) {
                existingLoaiCho.setTenLC(lc.getTenLC());
                existingLoaiCho.setGiaCho(lc.getGiaCho());
                existingLoaiCho.setMoTa(lc.getMoTa());
                em.merge(existingLoaiCho);
                tr.commit();
                return existingLoaiCho;
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
    public boolean xoaLoaiCho(String maLC) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LoaiCho existingLoaiCho = em.find(LoaiCho.class, maLC);
            if (existingLoaiCho != null) {
                em.remove(existingLoaiCho);
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
}
