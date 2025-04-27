package control.impl;

import connectDB.connectDB_1;
import control.IDAOGa;
import entity.Ga;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
public class DAOGa extends UnicastRemoteObject implements IDAOGa {
    private EntityManager em = connectDB_1.getEntityManager();
    public DAOGa() throws RemoteException {
    }
    @Override
    public ArrayList<Ga> getDsGa() throws RemoteException {
        System.out.println("DAO: Get danh sách ga");
        ArrayList<Ga> dsGa = new ArrayList<>();
        try {
            // Tạo truy vấn JPQL để lấy tất cả Ga từ cơ sở dữ liệu
            String jpql = "SELECT g FROM Ga g"; // Truy vấn JPQL
            TypedQuery<Ga> query = em.createQuery(jpql, Ga.class);

            // Thực thi truy vấn và lấy kết quả
            dsGa = new ArrayList<>(query.getResultList()); // Danh sách Ga trả về từ truy vấn

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsGa;
    }
    @Override
    public Ga getGaTheoMaGa(int maGa) throws RemoteException{
        try {
            String jpql = "SELECT g FROM Ga g WHERE g.maGa = :maGa";
            TypedQuery<Ga> query = em.createQuery(jpql, Ga.class);
            query.setParameter("maGa", maGa);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean themGa(Ga ga) throws RemoteException{
        try {
            em.getTransaction().begin();
            em.persist(ga);
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
    public Ga suaGa(Ga ga) throws RemoteException{
        try {
            em.getTransaction().begin();
            String jpql = "UPDATE Ga g SET g.tenGa = :tenGa WHERE g.maGa = :maGa";
            int updatedCount = em.createQuery(jpql)
                    .setParameter("tenGa", ga.getTenGa())
                    .setParameter("maGa", ga.getMaGa())
                    .executeUpdate();
            em.getTransaction().commit();
            return updatedCount > 0 ? ga : null;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return null;
    }
}
