import java.util.ArrayList;
import java.util.List;

public class Sale {
    private List<SaleItem> items = new ArrayList<>();
    private double total;

    public void addItem(SaleItem item) {
        items.add(item);
        total += item.getTotalPrice();
    }

    public double getTotal() {
        return total;
    }

    public void printReceipt() {
        System.out.println("\n----- Receipt -----");
        for (SaleItem item : items) {
            System.out.println(item);
        }
        System.out.printf("Total: %.2f\n", total);
    }
}
