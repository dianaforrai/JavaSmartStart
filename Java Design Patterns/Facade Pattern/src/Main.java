// Subsystem 1: Account Management
class AccountManager {
    public void createAccount(String accountId) {
        System.out.println("Account " + accountId + " created.");
    }

    public void deleteAccount(String accountId) {
        System.out.println("Account " + accountId + " deleted.");
    }

    public double getBalance(String accountId) {
        // Dummy balance for demonstration
        return 1000.0;
    }
}

// Subsystem 2: Transaction Processing
class TransactionProcessor {
    public void processTransaction(String transactionType, String fromAccount, String toAccount, double amount) {
        System.out.println(transactionType + " of $" + amount +
                " from " + fromAccount + " to " + toAccount + " processed.");
    }

    public void processTransaction(String transactionType, String accountId, double amount) {
        System.out.println(transactionType + " of $" + amount + " for account " + accountId + " processed.");
    }
}

// Facade Class
class BankingFacade {
    private AccountManager accountManager;
    private TransactionProcessor transactionProcessor;

    public BankingFacade() {
        accountManager = new AccountManager();
        transactionProcessor = new TransactionProcessor();
    }

    public void deposit(String accountId, double amount) {
        transactionProcessor.processTransaction("Deposit", accountId, amount);
    }

    public void withdraw(String accountId, double amount) {
        transactionProcessor.processTransaction("Withdrawal", accountId, amount);
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        transactionProcessor.processTransaction("Transfer", fromAccount, toAccount, amount);
    }

    public double getBalance(String accountId) {
        return accountManager.getBalance(accountId);
    }
}

