public class Client {
    public static void main(String[] args) {
        ReportTemplate financialReport = new FinancialReport();
        ReportTemplate salesReport = new SalesReport();
        ReportTemplate inventoryReport = new InventoryReport();

        System.out.println("Generating Financial Report:");
        financialReport.generateReport();
        System.out.println("-----------------------------");

        System.out.println("Generating Sales Report:");
        salesReport.generateReport();
        System.out.println("-----------------------------");

        System.out.println("Generating Inventory Report:");
        inventoryReport.generateReport();
        System.out.println("-----------------------------");
    }
}
