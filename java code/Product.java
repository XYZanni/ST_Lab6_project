public class Product {
    private static int idCounter = 1;
    private int id;
    private String name;
    private double price;
    private int stock;
    private String category;

    public Product(String name, double price, int stock, String category) {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Price: %.2f | Stock: %d | Category: %s", id, name, price, stock, category);
    }
}
