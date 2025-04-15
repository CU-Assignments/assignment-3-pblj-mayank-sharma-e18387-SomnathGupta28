import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ATMWithdrawalSystem {
    private static final String CORRECT_PIN = "1234";
    private static double balance = 3000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();
        
        try {
            validatePin(enteredPin);
            System.out.print("Withdraw Amount: ");
            double withdrawAmount = scanner.nextDouble();
            withdraw(withdrawAmount);
        } catch (InvalidPinException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage() + " Current Balance: " + balance);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Remaining Balance: " + balance);
        }
        
        scanner.close();
    }

    private static void validatePin(String pin) throws InvalidPinException {
        if (!pin.equals(CORRECT_PIN)) {
            throw new InvalidPinException("Invalid PIN.");
        }
    }

    private static void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }
        balance -= amount;
        System.out.println("Withdrawal successful! Current Balance: " + balance);
    }
}