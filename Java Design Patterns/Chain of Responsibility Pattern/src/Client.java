public class Client {
    public static void main(String[] args) {
        // Create handlers
        SupportHandler basicHandler = new BasicSupportHandler();
        SupportHandler intermediateHandler = new IntermediateSupportHandler();
        SupportHandler advancedHandler = new AdvancedSupportHandler();

        // Setup chain: Basic -> Intermediate -> Advanced
        basicHandler.setNextHandler(intermediateHandler);
        intermediateHandler.setNextHandler(advancedHandler);

        // Create tickets
        SupportTicket ticket1 = new SupportTicket(SupportTicket.Complexity.BASIC, "Password reset request");
        SupportTicket ticket2 = new SupportTicket(SupportTicket.Complexity.INTERMEDIATE, "Software installation issue");
        SupportTicket ticket3 = new SupportTicket(SupportTicket.Complexity.ADVANCED, "Database connection error");

        // Pass tickets through the chain
        basicHandler.handleRequest(ticket1);
        basicHandler.handleRequest(ticket2);
        basicHandler.handleRequest(ticket3);
    }
}
