public class FacadePatternDemo {
    public static void main(String[] args) {
        BankingFacade bankingFacade = new BankingFacade();

        // Perform banking operations using the facade
        bankingFacade.deposit("A123", 500);
        bankingFacade.withdraw("A123", 200);
        bankingFacade.transfer("A123", "B456", 150);

        double balance = bankingFacade.getBalance("A123");
        System.out.println("Balance for account A123: $" + balance);
    }
}
