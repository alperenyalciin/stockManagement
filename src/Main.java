import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProductDao productDAO = new ProductDao();

        Scanner scanner = new Scanner(System.in);
        int secim;

        do {
            System.out.println("\n=== STOK YÖNETİM SİSTEMİ ===");
            System.out.println("1 - Ürün Ekle");
            System.out.println("2 - Ürünleri Listele");
            System.out.println("3 - Stok Güncelle");
            System.out.println("4 - Stok Hareketlerini Listele");
            System.out.println("0 - Çıkış");

            System.out.print("Seçiminiz: ");
            secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    System.out.println("Ürün adı:");
                    scanner.nextLine(); // Önceki girişten kalan Enter'ı temizler
                    String name = scanner.nextLine();

                    System.out.println("Ürün fiyatı:");
                    double price = scanner.nextDouble();

                    System.out.println("Ürün miktarı:");
                    int quantity = scanner.nextInt();

                    Product product = new Product(0, name, price, quantity);
                    productDAO.addProduct(product);
                    break;

                case 2:
                    List<Product> products = productDAO.getAllProducts();

                    if (products.isEmpty()) {
                        System.out.println("Kayıtlı ürün bulunamadı.");
                    } else {
                        for (Product p : products) {
                            System.out.println(
                                    p.getId() + " | " +
                                            p.getName() + " | " +
                                            p.getPrice() + " | " +
                                            p.getQuantity()
                            );
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ürün ID: ");
                    int productId = scanner.nextInt();

                    System.out.println("1 - Stok Girişi");
                    System.out.println("2 - Stok Çıkışı");
                    System.out.print("İşlem seçimi: ");
                    int movementChoice = scanner.nextInt();

                    System.out.print("Miktar: ");
                    int StockQuantity = scanner.nextInt();

                    if (movementChoice == 1) {
                        productDAO.updateStock(productId, "IN2", StockQuantity);
                    } else if (movementChoice == 2) {
                        productDAO.updateStock(productId, "OUT", StockQuantity);                    } else {
                        System.out.println("Geçersiz işlem seçimi.");
                    }
                    break;
                case 4:
                    productDAO.getAllStockMovements();
                    break;
                case 0:
                    System.out.println("Program kapatılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim yaptınız.");
            }

        } while (secim != 0);

        scanner.close();



    }
}