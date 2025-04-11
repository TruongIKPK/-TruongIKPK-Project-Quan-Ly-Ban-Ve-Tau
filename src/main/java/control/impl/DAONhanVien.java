package control.impl;

import connectDB.connectDB_1;
import control.IDAONhanVien;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import service.NhanVienService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DAONhanVien extends UnicastRemoteObject implements IDAONhanVien {
    private EntityManager em = connectDB_1.getEntityManager();

    public DAONhanVien() throws RemoteException {
    }

    @Override
    public boolean themNhanVien(NhanVien nv)  throws RemoteException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            nv.setDuongDanAnh("images/employees/nv-3.jpg");
            NhanVienService nhanVienService = new NhanVienService(em);
            nhanVienService.persistNhanVien(nv);

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
    //get NV
    @Override
    public NhanVien getNhanVien(String maNV) throws RemoteException {
            String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNV";
            try {
                TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
                query.setParameter("maNV", maNV);
                NhanVien nv = query.getSingleResult();
                return nv;
            } catch (NoResultException e) {
                System.out.println("Không tìm thấy nhân viên với mã " + maNV);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;  // Trả về null nếu không tìm thấy hoặc có lỗi
        }
    //ds NV
    @Override
    public ArrayList<NhanVien> layDanhSachNhanVien() throws RemoteException {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        String jpql = "SELECT nv FROM NhanVien nv";
        try {
            TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
            List<NhanVien> resultList = query.getResultList();
            dsNhanVien.addAll(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }
    //get NV theo ten
    @Override
    public NhanVien getNhanVienTheoTen(String tenNV) throws RemoteException {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.tenNV LIKE :tenNV";
        try {
            TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
            query.setParameter("tenNV", "%" + tenNV + "%");
            NhanVien nv = query.getSingleResult();
            return nv;
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy nhân viên với tên: " + tenNV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //get NV theo sdt
    @Override
    public NhanVien getNhanVienTheoSDT(String sdt) throws RemoteException {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.sdt = :sdt";
        try {
            TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
            query.setParameter("sdt", sdt);
            NhanVien nv = query.getSingleResult();
            return nv;

        } catch (NoResultException e) {
            System.out.println("Không tìm thấy nhân viên với số điện thoại: " + sdt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //get NV theo CCCD
    @Override
    public NhanVien getNhanVienTheoCCCD(String CCCD) throws RemoteException {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.CCCD = :CCCD";
        try {
            TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
            query.setParameter("CCCD", CCCD);
            NhanVien nv = query.getSingleResult();
            return nv;
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy nhân viên với CCCD: " + CCCD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //get NV theo chuc vu
    @Override
    public NhanVien getNhanVienTheoChucVu(String maChucVu) throws RemoteException {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.chucVu.maCV = :maChucVu";
        try {
            TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
            query.setParameter("maChucVu", maChucVu);
            NhanVien nv = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy nhân viên với mã chức vụ: " + maChucVu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //get NV theo trang thai
    @Override
    public ArrayList<NhanVien> getNhanVienTheoTrangThai(String trangThai) throws RemoteException {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.trangThai = :trangThai";
        try {
            TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
            query.setParameter("trangThai", trangThai);
            dsNhanVien = new ArrayList<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }
    //sua NV
    @Override
    public NhanVien suaNhanVien(NhanVien nv) throws RemoteException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin(); // Bắt đầu giao dịch

            NhanVien existingNhanVien = em.find(NhanVien.class, nv.getMaNV());
            if (existingNhanVien != null) {
                // Cập nhật các thuộc tính của NhanVien
                existingNhanVien.setTenNV(nv.getTenNV());
                existingNhanVien.setGioiTinh(nv.getGioiTinh());
                existingNhanVien.setNgaySinh(nv.getNgaySinh());
                existingNhanVien.setNgayVaoLam(nv.getNgayVaoLam());
                existingNhanVien.setCCCD(nv.getCCCD());
                existingNhanVien.setSdt(nv.getSdt());
                existingNhanVien.setEmail(nv.getEmail());
                existingNhanVien.setDiaChi(nv.getDiaChi());
                existingNhanVien.setTrangThai(nv.getTrangThai());

                // Kiểm tra và cập nhật các thực thể liên quan
                if (nv.getCaLam() != null) {
                    existingNhanVien.setCaLam(em.merge(nv.getCaLam()));
                }
                if (nv.getTaiKhoan() != null) {
                    existingNhanVien.setTaiKhoan(em.merge(nv.getTaiKhoan()));
                }
                if (nv.getChucVu() != null) {
                    existingNhanVien.setChucVu(em.merge(nv.getChucVu()));
                }
                updateTaiKhoanStatus(existingNhanVien);
                em.merge(existingNhanVien); // Lưu các thay đổi vào cơ sở dữ liệu
            }
            transaction.commit(); // Commit giao dịch
            return existingNhanVien;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback giao dịch nếu có ngoại lệ
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateTaiKhoanStatus(NhanVien nhanVien) throws RemoteException {
        TaiKhoan taiKhoan = nhanVien.getTaiKhoan();
        if (taiKhoan != null) {
            if (nhanVien.getTrangThai().equals("Nghỉ làm")) {
                taiKhoan.setTrangThai("Bị khóa");
            } else if (nhanVien.getTrangThai().equals("Làm việc")) {
                taiKhoan.setTrangThai("Kích hoạt");
            }
            em.merge(taiKhoan);
        }
    }

    @Override
    public String getDuongDanAnh(String maNV) throws RemoteException {
        String jpql = "SELECT nv.duongDanAnh FROM NhanVien nv WHERE nv.maNV = :maNV";
        try {
            // Tạo query với JPQL và ánh xạ kiểu dữ liệu trả về là String
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("maNV", maNV);

            // Lấy kết quả đầu tiên (duongDanAnh)
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy đường dẫn ảnh cho nhân viên với mã " + maNV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
