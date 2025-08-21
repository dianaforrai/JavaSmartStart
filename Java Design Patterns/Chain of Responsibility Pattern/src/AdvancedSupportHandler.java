public class AdvancedSupportHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(SupportTicket ticket) {
        if (ticket.getComplexity() == SupportTicket.Complexity.ADVANCED) {
            System.out.println("AdvancedSupportHandler handled: " + ticket.getIssueDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(ticket);
        } else {
            System.out.println("No handler available for: " + ticket.getIssueDescription());
        }
    }
}
