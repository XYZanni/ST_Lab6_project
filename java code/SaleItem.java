public class SaleItem {
    private Product product;
    private int quantity;

    public SaleItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s | Quantity: %d | Price: %.2f | Total: %.2f", 
            product.getName(), quantity, product.getPrice(), getTotalPrice());
    }
}
