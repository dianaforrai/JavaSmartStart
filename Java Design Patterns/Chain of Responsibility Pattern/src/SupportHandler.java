public interface SupportHandler {
    void setNextHandler(SupportHandler nextHandler);
    void handleRequest(SupportTicket ticket);
}
