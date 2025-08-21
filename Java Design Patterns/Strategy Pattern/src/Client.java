public class Client {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // Using Credit Card Payment
        processor.setPaymentStrategy(new CreditCardPaymentStrategy());
        processor.processPayment(100.50);
        System.out.println("-------------------");

        // Using PayPal Payment
        processor.setPaymentStrategy(new PayPalPaymentStrategy());
        processor.processPayment(250.75);
        System.out.println("-------------------");

        // Using Bank Transfer Payment
        processor.setPaymentStrategy(new BankTransferPaymentStrategy());
        processor.processPayment(500.00);
        System.out.println("-------------------");
    }
}
