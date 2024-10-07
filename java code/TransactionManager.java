import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Sale> sales = new ArrayList<>();

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    public void printSalesReport() {
        double totalSales = 0;
        for (Sale sale : sales) {
            totalSales += sale.getTotal();
        }
        System.out.printf("Total Sales: %.2f\n", totalSales);
    }

    public void printTransactionHistory() {
        System.out.println("\n----- Transaction History -----");
        for (Sale sale : sales) {
            sale.printReceipt();
        }
    }
}
