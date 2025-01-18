package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectDB {
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBanVeTauHoa;encrypt=false;";
    private static String user = "sa";
    private static String password = "123";
    public static Connection connect = null;
    private static final ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        return connect;
    }

    public static void connect() throws SQLException {
        connect = DriverManager.getConnection(url, user, password);
        System.out.println("Kết nối CSDL thành công!");
    }

    public static void disconnect() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

