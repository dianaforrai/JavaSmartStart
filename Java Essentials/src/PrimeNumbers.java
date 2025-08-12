import java.util.Scanner;

public class PrimeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive integer n: ");
        int n = scanner.nextInt();

        int num = 2;  // Start checking from 2

        while (num <= n) {
            boolean isPrime = true;
            int divisor = 2;

            while (divisor * divisor <= num) {
                if (num % divisor == 0) {
                    isPrime = false;
                }
                divisor++;
            }

            if (isPrime) {
                System.out.println(num);
            }

            num++;
        }

        scanner.close();
    }
}
