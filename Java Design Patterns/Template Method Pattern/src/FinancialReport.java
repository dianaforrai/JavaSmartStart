public class FinancialReport extends ReportTemplate {

    @Override
    protected void retrieveData() {
        System.out.println("Retrieving financial data from accounting system...");
    }

    @Override
    protected void processData() {
        System.out.println("Processing financial data and calculating profit/loss...");
    }

    @Override
    protected void formatReport() {
        System.out.println("Formatting financial report with charts and tables...");
    }
}
