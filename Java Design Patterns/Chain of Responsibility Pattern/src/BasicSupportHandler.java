public class BasicSupportHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(SupportTicket ticket) {
        if (ticket.getComplexity() == SupportTicket.Complexity.BASIC) {
            System.out.println("BasicSupportHandler handled: " + ticket.getIssueDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(ticket);
        }
    }
}
