package control.impl;

import connectDB.connectDB_1;
import control.IDAOHoaDon;
import entity.HoaDon;
import entity.Ve;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import service.HoaDonService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOHoaDon extends UnicastRemoteObject implements IDAOHoaDon {

    private EntityManager em = connectDB_1.getEntityManager();

    public DAOHoaDon() throws RemoteException {
    }

    @Override
    public boolean themHoaDon(HoaDon hd)throws RemoteException {
        try {
                em.getTransaction().begin();
                HoaDonService hoaDonService = new HoaDonService(em);
                hoaDonService.persistHoaDon(hd);
                em.getTransaction().commit();
                System.out.println("Thêm hóa đơn thành công");
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<HoaDon> docHoaDonTheoNhanVien(String maNV)throws RemoteException {
        try {
            TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd WHERE hd.nhanVien.maNV = :maNV", HoaDon.class);
            query.setParameter("maNV", maNV);
            List<HoaDon> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách trống nếu có lỗi xảy ra
        }
    }

    @Override
    public ArrayList<HoaDon> docHoaDonTheoKhachHang(String maKH) throws RemoteException {
        String jpql = "SELECT h FROM HoaDon h WHERE h.khachHang.maKH = :maKH";
        TypedQuery<HoaDon> query = em.createQuery(jpql, HoaDon.class);
        query.setParameter("maKH", maKH);

        List<HoaDon> dsHoaDon = query.getResultList();
        DAOVe daoVe = new DAOVe();
        for (HoaDon hoaDon : dsHoaDon) {
            List<Ve> dsVe = daoVe.layDSVeTheoMaHD(hoaDon.getMaHD());
            ArrayList<Ve> dsVe1 = new ArrayList<>(dsVe);
            hoaDon.setDanhSachVe(dsVe1);
        }

        return new ArrayList<>(dsHoaDon);
    }


    @Override
    public ArrayList<HoaDon> docHoaDonTheoNgay(LocalDate ngay)throws RemoteException {
        try {
            TypedQuery<HoaDon> query = em.createQuery(
                    "SELECT hd FROM HoaDon hd WHERE hd.ngayGioLapHD = :ngay", HoaDon.class);
            query.setParameter("ngay", ngay);
            List<HoaDon> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách trống nếu có lỗi xảy ra
        }
    }


    @Override
    public ArrayList<HoaDon> layDanhSachHoaDon()throws RemoteException {
        try {
            TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd", HoaDon.class);
            List<HoaDon> resultList = query.getResultList();
            return new ArrayList<>(resultList); // Chuyển đổi List thành ArrayList
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách trống nếu có lỗi xảy ra
        }
    }


    @Override
    public HoaDon getHoaDonCuoiCung()throws RemoteException {
        try {
            TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd ORDER BY hd.ngayGioLapHD DESC", HoaDon.class);
            query.setMaxResults(1);

            List<HoaDon> resultList = query.getResultList();

            if (resultList.isEmpty()) {
                System.out.println("Danh sách hóa đơn trống.");
                return null; // Trả về null nếu danh sách rỗng
            }

            return resultList.get(0); // Trả về hóa đơn cuối cùng được tạo
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra
        }
    }


    @Override
    public HoaDon getHoaDon(String maHD)throws RemoteException {
        try {
            TypedQuery<HoaDon> query = em.createQuery("SELECT hd FROM HoaDon hd WHERE hd.maHD = :maHD", HoaDon.class);
            query.setParameter("maHD", maHD);
            List<HoaDon> resultList = query.getResultList();
            return resultList.get(0); // Trả về hóa đơn có mã tương ứng
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi xảy ra
        }
    }
}
