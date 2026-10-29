import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT ProductID, NAME, PRICE, QUANTITY FROM Products";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("ProductID");
                String name = rs.getString("NAME");
                double price = rs.getDouble("PRICE");
                int quantity = rs.getInt("QUANTITY");

                Product product =
                        new Product(id, name, price, quantity);

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public void addProduct(Product product) {
        String sql = "INSERT INTO Products (NAME, PRICE, QUANTITY) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());

            ps.executeUpdate();

            System.out.println("Ürün başarıyla eklendi.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateStock(int productId, String movementType, int quantity) {
        if (quantity <= 0) {
            System.out.println("Miktar sıfırdan büyük olmalı.");
            return false;
        }

        if (!movementType.equals("IN") && !movementType.equals("OUT")) {
            System.out.println("Geçersiz stok hareket türü.");
            return false;
        }

        String updateSql;

        if (movementType.equals("IN")) {
            updateSql = "UPDATE Products SET QUANTITY = QUANTITY + ? WHERE ProductID = ?";
        } else {
            updateSql = "UPDATE Products SET QUANTITY = QUANTITY - ? WHERE ProductID = ? AND QUANTITY >= ?";
        }

        String insertSql =
                "INSERT INTO StockMovements (ProductId, MovementType, Quantity, MovementDate) " +
                        "VALUES (?, ?, ?, GETDATE())";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                int updatedRows;

                try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                    ps.setInt(1, quantity);
                    ps.setInt(2, productId);

                    if (movementType.equals("Çıkış")) {
                        ps.setInt(3, quantity);
                    }

                    updatedRows = ps.executeUpdate();
                }

                if (updatedRows == 0) {
                    conn.rollback();
                    System.out.println("Ürün bulunamadı veya stok yetersiz.");
                    return false;
                }

                try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                    ps.setInt(1, productId);
                    ps.setString(2, movementType);
                    ps.setInt(3, quantity);
                    ps.executeUpdate();
                }

                conn.commit();
                System.out.println("Stok güncellendi ve hareket kaydedildi.");
                return true;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void getAllStockMovements() {
        String sql = "SELECT Id, ProductId, MovementType, Quantity, MovementDate " +
                "FROM StockMovements ORDER BY MovementDate DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        "Hareket ID: " + rs.getInt("Id") +
                                " | Ürün ID: " + rs.getInt("ProductId") +
                                " | İşlem: " + rs.getString("MovementType") +
                                " | Miktar: " + rs.getInt("Quantity") +
                                " | Tarih: " + rs.getTimestamp("MovementDate")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
