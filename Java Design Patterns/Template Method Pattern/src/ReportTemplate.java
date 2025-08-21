public abstract class ReportTemplate {

    public final void generateReport() {
        retrieveData();
        processData();
        formatReport();
        System.out.println("Report generation completed.\n");
    }

    protected abstract void retrieveData();
    protected abstract void processData();
    protected abstract void formatReport();
}
