package connectDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class connectDB_1 {

    private static EntityManagerFactory emf;

    public static void connect() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("mssql");
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }
}