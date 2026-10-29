import java.sql.Connection;
import java.sql.SQLException;


    public class ConnectionTest {
        public static void main(String[] args) {
            try (Connection connection = DBConnection.getConnection()) {
                System.out.println("Bağlantı başarılı!");
                System.out.println("Veritabanı: " + ((Connection) connection).getCatalog());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

