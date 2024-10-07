import java.util.Scanner;
import java.util.List;

public class POSSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductCatalog productCatalog = new ProductCatalog();
    private static TransactionManager transactionManager = new TransactionManager();
    private static UserManager userManager = new UserManager();
    private static User currentUser;

    public static void main(String[] args) {
        if (!userManager.loadUsers()) {
            System.out.println("Error loading users. Exiting.");
            return;
        }
        
        // User login
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            currentUser = userManager.authenticate(username, password);

            if (currentUser != null) {
                System.out.println("Login successful!");
                break;
            } else {
                System.out.println("Invalid username or password.");
            }
        }

        // Main Menu
        while (true) {
            System.out.println("\n----- Point of Sale System -----");
            System.out.println("1. Manage Products");
            System.out.println("2. Start New Sale");
            System.out.println("3. View Sales Report");
            System.out.println("4. View Transaction History");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    startNewSale();
                    break;
                case 3:
                    viewSalesReport();
                    break;
                case 4:
                    viewTransactionHistory();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageProducts() {
        if (currentUser.getRole() != UserRole.ADMIN) {
            System.out.println("You do not have permission to manage products.");
            return;
        }

        while (true) {
            System.out.println("\n----- Product Management -----");
            System.out.println("1. Add Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. List Products");
            System.out.println("5. Search Products");
            System.out.println("6. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    listProducts();
                    break;
                case 5:
                    searchProducts();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product stock quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter product category: ");
        String category = scanner.nextLine();

        Product product = new Product(name, price, quantity, category);
        productCatalog.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void editProduct() {
        System.out.print("Enter product ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = productCatalog.getProductById(id);
        if (product != null) {
            System.out.print("Enter new product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new product price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter new product stock quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new product category: ");
            String category = scanner.nextLine();

            product.setName(name);
            product.setPrice(price);
            product.setStock(quantity);
            product.setCategory(category);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        if (productCatalog.removeProduct(id)) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void listProducts() {
        System.out.println("\n----- Product List -----");
        productCatalog.listProducts();
    }

    private static void searchProducts() {
        System.out.print("Enter product name or category to search: ");
        String query = scanner.nextLine();
        List<Product> results = productCatalog.searchProducts(query);
        System.out.println("Search Results:");
        for (Product product : results) {
            System.out.println(product);
        }
    }

    private static void startNewSale() {
        System.out.println("\n----- New Sale -----");
        Sale sale = new Sale();

        while (true) {
            System.out.print("Enter product ID to add to sale (or 0 to finish): ");
            int productId = scanner.nextInt();
            if (productId == 0) break;

            Product product = productCatalog.getProductById(productId);
            if (product != null) {
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                if (quantity <= product.getStock()) {
                    sale.addItem(new SaleItem(product, quantity));
                    product.setStock(product.getStock() - quantity); // Reduce stock
                    System.out.println("Added to sale.");
                } else {
                    System.out.println("Not enough stock.");
                }
            } else {
                System.out.println("Product not found.");
            }
        }

        System.out.println("\n----- Sale Summary -----");
        sale.printReceipt();
        transactionManager.addSale(sale);
    }

    private static void viewSalesReport() {
        System.out.println("\n----- Sales Report -----");
        transactionManager.printSalesReport();
    }

    private static void viewTransactionHistory() {
        System.out.println("\n----- Transaction History -----");
        transactionManager.printTransactionHistory();
    }
}
