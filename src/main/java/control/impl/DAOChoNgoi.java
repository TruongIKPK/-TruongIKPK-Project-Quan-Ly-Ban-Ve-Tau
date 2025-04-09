package control.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import connectDB.connectDB_1;
import control.IDAOChoNgoi;
import entity.ChoNgoi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DAOChoNgoi extends UnicastRemoteObject implements IDAOChoNgoi {

    public DAOChoNgoi() throws RemoteException {}
    private ArrayList<ChoNgoi> dsChoNgoi;

    private EntityManager em = connectDB_1.getEntityManager();

    @Override
    public ArrayList<ChoNgoi> getDanhSachChoNgoi() throws RemoteException{
        if (dsChoNgoi != null) {
            return dsChoNgoi;
        }

        try {
            TypedQuery<ChoNgoi> query = em.createQuery("SELECT c FROM ChoNgoi c", ChoNgoi.class);
            dsChoNgoi = (ArrayList<ChoNgoi>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            dsChoNgoi = new ArrayList<>();
        }
        return dsChoNgoi;
    }

    @Override
    public ArrayList<ChoNgoi> getDSChoNgoiTheoToa(String maToa) throws RemoteException{
        TypedQuery<ChoNgoi> query = em.createQuery(
                "SELECT c FROM ChoNgoi c WHERE c.toa.maToa = :maToa", ChoNgoi.class);
        query.setParameter("maToa", maToa);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<ChoNgoi> getDSChoNgoiTheoToaTrangThai(String maToa, String trangThai) throws RemoteException{
        ArrayList<ChoNgoi> dsChoNgoi = new ArrayList<>();
        String jpql = "SELECT c FROM ChoNgoi c WHERE c.toa.maToa = :maToa AND c.trangThai = :trangThai";

        try {
            dsChoNgoi = (ArrayList<ChoNgoi>) em.createQuery(jpql, ChoNgoi.class)
                    .setParameter("maToa", maToa)
                    .setParameter("trangThai", trangThai)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsChoNgoi;
    }

    @Override
    public ChoNgoi getChoNgoiTheoMa(String maCho) throws RemoteException{
        try {
            TypedQuery<ChoNgoi> query = em.createQuery("SELECT c FROM ChoNgoi c WHERE c.maCho = :maCho", ChoNgoi.class);
            query.setParameter("maCho", maCho);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

