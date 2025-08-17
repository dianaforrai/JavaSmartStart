import java.util.*;
import java.util.stream.*;

public class Exercise3 {

    public static double getTotalValue(List<Product> products) {
        return products.stream()
                .mapToDouble(p -> p.price() * p.quantity())
                .sum();
    }

    public static Optional<Product> getMostExpensiveProduct(List<Product> products) {
        return products.stream()
                .max(Comparator.comparingDouble(Product::price));
    }
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Laptop", 1200.00, 5),
                new Product("Smartphone", 800.00, 10),
                new Product("Tablet", 300.00, 15)
        );

        double totalValue = getTotalValue(products);
        System.out.println("Total value of products: " + totalValue);

        Optional<Product> mostExpensiveProduct = getMostExpensiveProduct(products);
        mostExpensiveProduct.ifPresent(p ->
            System.out.println("Most expensive product: " + p.name() + " at $" + p.price()));
    }
}
