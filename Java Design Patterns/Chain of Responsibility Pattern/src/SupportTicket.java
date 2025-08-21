public class SupportTicket {
    public enum Complexity {
        BASIC, INTERMEDIATE, ADVANCED
    }

    private Complexity complexity;
    private String issueDescription;

    public SupportTicket(Complexity complexity, String issueDescription) {
        this.complexity = complexity;
        this.issueDescription = issueDescription;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public String getIssueDescription() {
        return issueDescription;
    }
}
