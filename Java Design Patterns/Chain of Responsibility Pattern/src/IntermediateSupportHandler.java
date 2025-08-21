public class IntermediateSupportHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(SupportTicket ticket) {
        if (ticket.getComplexity() == SupportTicket.Complexity.INTERMEDIATE) {
            System.out.println("IntermediateSupportHandler handled: " + ticket.getIssueDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(ticket);
        }
    }
}
