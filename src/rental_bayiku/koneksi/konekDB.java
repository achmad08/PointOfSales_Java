package rental_bayiku.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class konekDB {

    public static Connection mysqlKonek;

    public static Connection getDB() throws SQLException {
        if (mysqlKonek == null) {
            try {
                String url_DB = "jdbc:mysql://localhost:3306/gudang_rental";
                String user = "root";
                String pw = "achmad";

                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                mysqlKonek = DriverManager.getConnection(url_DB, user, pw);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak dapat terhubung ke Database", "Peringatan", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        return mysqlKonek;
    }
}
