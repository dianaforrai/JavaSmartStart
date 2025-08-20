public class AdapterPatternDemo {
    public static void main(String[] args) {
        // Legacy system instance
        LegacyReportGenerator legacyGenerator = new LegacyReportGenerator();

        // Adapter instance to use legacy generator with new system
        NewReportSystem reportAdapter = new LegacyReportAdapter(legacyGenerator);

        // Using adapter to generate and save report
        reportAdapter.generateReport();
        reportAdapter.saveReport();
    }
}
