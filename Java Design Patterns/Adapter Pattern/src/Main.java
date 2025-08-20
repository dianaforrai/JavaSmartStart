// Legacy Report Generator (existing system)
class LegacyReportGenerator {
    public String createLegacyReport() {
        return "Legacy Report Data: [Sales, Revenue, Profit]";
    }
}

// New Report System Interface
interface NewReportSystem {
    void generateReport();
    void saveReport();
}

// Adapter Class: Adapts LegacyReportGenerator to NewReportSystem
class LegacyReportAdapter implements NewReportSystem {
    private LegacyReportGenerator legacyReportGenerator;

    public LegacyReportAdapter(LegacyReportGenerator legacyReportGenerator) {
        this.legacyReportGenerator = legacyReportGenerator;
    }

    @Override
    public void generateReport() {
        // Translate legacy report into new format
        String legacyData = legacyReportGenerator.createLegacyReport();
        System.out.println("Adapter: Converting legacy report to new format...");
        System.out.println("Generated Report: " + legacyData.replace("Legacy Report Data", "New Report Format"));
    }

    @Override
    public void saveReport() {
        System.out.println("Adapter: Saving report in the new system format...");
    }
}

