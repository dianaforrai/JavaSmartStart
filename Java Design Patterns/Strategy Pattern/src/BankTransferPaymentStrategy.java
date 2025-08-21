public class BankTransferPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing bank transfer payment of $" + amount);
        // Logic for bank transfer processing
        System.out.println("Bank transfer payment successful.");
    }
}
