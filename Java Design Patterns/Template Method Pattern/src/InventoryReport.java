public class InventoryReport extends ReportTemplate {

    @Override
    protected void retrieveData() {
        System.out.println("Retrieving inventory data from warehouse database...");
    }

    @Override
    protected void processData() {
        System.out.println("Processing inventory data and calculating stock levels...");
    }

    @Override
    protected void formatReport() {
        System.out.println("Formatting inventory report with detailed tables...");
    }
}
