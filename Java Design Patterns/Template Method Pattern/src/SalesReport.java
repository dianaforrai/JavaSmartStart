public class SalesReport extends ReportTemplate {

    @Override
    protected void retrieveData() {
        System.out.println("Retrieving sales data from CRM system...");
    }

    @Override
    protected void processData() {
        System.out.println("Processing sales data and calculating revenue metrics...");
    }

    @Override
    protected void formatReport() {
        System.out.println("Formatting sales report with graphs and tables...");
    }
}
