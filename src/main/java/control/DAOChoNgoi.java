package control;

import java.util.ArrayList;

import connectDB.connectDB_1;
import entity.ChoNgoi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import service.ChoNgoiService;

public class DAOChoNgoi {
//    private static ArrayList<ChoNgoi> dsChoNgoi;
//
//    public static ArrayList<ChoNgoi> getDanhSachChoNgoi() {
//        if (dsChoNgoi != null) {
//            return dsChoNgoi;
//        }
//
//        dsChoNgoi = new ArrayList<>();
//        String sql = "SELECT * FROM ChoNgoi";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String maCho = rs.getString("maCho");
//                String maLoaiCho = rs.getString("maLoaiCho");
//                String maToa = rs.getString("maToa");
//                LoaiCho loaiCho = DAOLoaiCho.getLoaiChoByID(maLoaiCho);
//                Toa toa = DAOToa.getToaTheoMa(maToa);
//                ChoNgoi choNgoi = new ChoNgoi(maCho, loaiCho, toa);
//                dsChoNgoi.add(choNgoi);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dsChoNgoi;
//    }

    private static ArrayList<ChoNgoi> dsChoNgoi;

    private static EntityManager em = connectDB_1.getEntityManager();

    public static ArrayList<ChoNgoi> getDanhSachChoNgoi() {
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

    // get ds cho ngoi theo ma toa
//    public static ArrayList<ChoNgoi> getDSChoNgoiTheoToa(String maToa) {
//        System.out.println("getDSChoNgoiTheoToa");
//        ArrayList<ChoNgoi> dsChoNgoi = getDanhSachChoNgoi();
//        ArrayList<ChoNgoi> dsChoNgoiTheoToa = new ArrayList<>();
//        for (ChoNgoi choNgoi : dsChoNgoi) {
//            if (choNgoi.getToa().getMaToa().equals(maToa)) {
//                dsChoNgoiTheoToa.add(choNgoi);
//            }
//        }
//
//        System.out.println("dsChoNgoiTheoToa: " + maToa + " - " + dsChoNgoiTheoToa.size());
//
//        return dsChoNgoiTheoToa;
//    }

    public static ArrayList<ChoNgoi> getDSChoNgoiTheoToa(String maToa) {
        TypedQuery<ChoNgoi> query = em.createQuery(
                "SELECT c FROM ChoNgoi c WHERE c.toa.maToa = :maToa", ChoNgoi.class);
        query.setParameter("maToa", maToa);
        return new ArrayList<>(query.getResultList());
    }


    //get ds cho ngoi theo ma toa va trang thai trong
//    public static ArrayList<ChoNgoi> getDSChoNgoiTheoToaTrangThai(String maToa, String trangThai) {
//        ArrayList<ChoNgoi> dsChoNgoi = new ArrayList<>();
//        String sql = "SELECT * FROM ChoNgoi WHERE maToa = ? AND trangThai = ?";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, maToa);
//            stmt.setString(2, trangThai);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    String maCho = rs.getString("maCho");
//                    String strMaLoaiCho = rs.getString("maLoaiCho");
//                    LoaiCho loaiCho = DAOLoaiCho.getLoaiChoByID(strMaLoaiCho);
//                    String maToa1 = rs.getString("maToa");
//                    Toa toa = new Toa(maToa1);//
//                    ChoNgoi choNgoi = new ChoNgoi(maCho, loaiCho, toa);
//                    dsChoNgoi.add(choNgoi);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return dsChoNgoi;
//    }

    public static ArrayList<ChoNgoi> getDSChoNgoiTheoToaTrangThai(String maToa, String trangThai) {
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


    // get cho ngoi theo ma
//    public static ChoNgoi getChoNgoiTheoMa(String maCho) {
//        ArrayList<ChoNgoi> dsChoNgoi = getDanhSachChoNgoi();
//        for (ChoNgoi choNgoi : dsChoNgoi) {
//            if (choNgoi.getMaCho().equals(maCho)) {
//                return choNgoi;
//            }
//        }
//        return null;
//    }

    public static ChoNgoi getChoNgoiTheoMa(String maCho) {
        try {
            TypedQuery<ChoNgoi> query = em.createQuery("SELECT c FROM ChoNgoi c WHERE c.maCho = :maCho", ChoNgoi.class);
            query.setParameter("maCho", maCho);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    // THEM CHO NGOI [maCho], [maloaiCho], [maToa], [trangThai]
//    public static boolean themChoNgoi(ChoNgoi choNgoi) {
//        String sql = "INSERT INTO ChoNgoi(maCho, maloaiCho, maToa) VALUES(?, ?, ?)";
//        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, choNgoi.getMaCho());
//            stmt.setString(2, choNgoi.getLoaiCho().getMaLC());
//            stmt.setString(3, choNgoi.getToa().getMaToa());
//
//            return stmt.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

//    public static boolean themChoNgoi(ChoNgoi choNgoi) {
//        try {
//            ChoNgoiService choNgoiService = new ChoNgoiService();
//            choNgoiService.persistChoNgoi(choNgoi);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}

