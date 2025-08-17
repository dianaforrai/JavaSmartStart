import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;


public class SalesDataAnalysis {

    private static List<Sale> generateSampleSalesData() {
        List<Sale> sales = new ArrayList<>();
        String[] products = {"Smartphone", "Laptop", "Tablet", "Headphones", "Smart Watch", "Camera", "Gaming Console"};
        String[] customers = {"CUST001", "CUST002", "CUST003", "CUST004", "CUST005", "CUST006", "CUST007", "CUST008", "CUST009", "CUST010"};

        LocalDate startDate = LocalDate.of(2022, 6, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        for (int i = 0; i < 100; i++) {
            String product = products[ThreadLocalRandom.current().nextInt(products.length)];
            double amount = ThreadLocalRandom.current().nextDouble(50.0, 2000.0);
            String customer = customers[ThreadLocalRandom.current().nextInt(customers.length)];

            long daysBetween = startDate.until(endDate).getDays();
            LocalDate randomDate = startDate.plusDays(ThreadLocalRandom.current().nextLong(daysBetween + 1));

            sales.add(new Sale(product, Math.round(amount * 100.0) / 100.0, customer, randomDate));
        }

        return sales;
    }

    public static void main(String[] args) {
        List<Sale> salesData = generateSampleSalesData();

        System.out.println("=== Sales Data Analysis using Java 8 Streams ===\n");
        System.out.printf("Total sales records: %d%n%n", salesData.size());

        System.out.println("Sample sales data:");
        salesData.stream().limit(5).forEach(System.out::println);
        System.out.println();

        // i) Filter out all sales that occurred after a certain date (January 1, 2023)
        System.out.println("=== i) Sales after January 1, 2023 ===");
        LocalDate cutoffDate = LocalDate.of(2023, 1, 1);
        List<Sale> salesAfterDate = salesData.stream()
                .filter(sale -> sale.getDateOfSale().isAfter(cutoffDate))
                .collect(Collectors.toList());

        System.out.printf("Sales after %s: %d records%n", cutoffDate, salesAfterDate.size());
        salesAfterDate.stream().limit(3).forEach(System.out::println);
        System.out.println();

        // ii) Map the sales to their corresponding product names
        System.out.println("=== ii) Product names from all sales ===");
        List<String> productNames = salesData.stream()
                .map(Sale::getProductName)
                .collect(Collectors.toList());

        System.out.printf("Total product entries: %d%n", productNames.size());
        System.out.println("First 10 product names: " +
                productNames.stream().limit(10).collect(Collectors.toList()));
        System.out.println();

        // iii) Calculate the total sales amount for a specific product (Smartphone)
        System.out.println("=== iii) Total sales for Smartphone ===");
        String targetProduct = "Smartphone";
        double totalSmartphoneSales = salesData.stream()
                .filter(sale -> sale.getProductName().equals(targetProduct))
                .mapToDouble(Sale::getSaleAmount)
                .sum();

        long smartphoneCount = salesData.stream()
                .filter(sale -> sale.getProductName().equals(targetProduct))
                .count();

        System.out.printf("Total %s sales: $%.2f from %d transactions%n",
                targetProduct, totalSmartphoneSales, smartphoneCount);
        System.out.println();

        // iv) Sort the sales by sale amount in descending order
        System.out.println("=== iv) Top 5 sales by amount (descending) ===");
        List<Sale> sortedSales = salesData.stream()
                .sorted(Comparator.comparing(Sale::getSaleAmount).reversed())
                .limit(5)
                .collect(Collectors.toList());

        sortedSales.forEach(System.out::println);
        System.out.println();

        // v) Collect the distinct customer IDs from all sales
        System.out.println("=== v) Distinct customer IDs ===");
        Set<String> distinctCustomers = salesData.stream()
                .map(Sale::getCustomerId)
                .collect(Collectors.toSet());

        System.out.printf("Total distinct customers: %d%n", distinctCustomers.size());
        System.out.println("Customer IDs: " + distinctCustomers);
        System.out.println();

        // vi) Group the sales by product name
        System.out.println("=== vi) Sales grouped by product name ===");
        Map<String, List<Sale>> salesByProduct = salesData.stream()
                .collect(Collectors.groupingBy(Sale::getProductName));

        salesByProduct.forEach((product, sales) -> {
            double productTotal = sales.stream().mapToDouble(Sale::getSaleAmount).sum();
            System.out.printf("%s: %d sales, Total: $%.2f%n",
                    product, sales.size(), productTotal);
        });
        System.out.println();

        // vii) Filter sales > $100 and calculate total
        System.out.println("=== vii) Sales > $100 and their total ===");
        List<Sale> highValueSales = salesData.stream()
                .filter(sale -> sale.getSaleAmount() > 100)
                .collect(Collectors.toList());

        double totalHighValueSales = highValueSales.stream()
                .mapToDouble(Sale::getSaleAmount)
                .sum();

        System.out.printf("Sales > $100: %d transactions%n", highValueSales.size());
        System.out.printf("Total amount of high-value sales: $%.2f%n", totalHighValueSales);
        System.out.println("Top 3 high-value sales:");
        highValueSales.stream()
                .sorted(Comparator.comparing(Sale::getSaleAmount).reversed())
                .limit(3)
                .forEach(System.out::println);
        System.out.println();

        // viii) Parallel stream processing for improved performance
        System.out.println("=== viii) Parallel stream processing comparison ===");

        long startTime = System.currentTimeMillis();
        double sequentialTotal = salesData.stream()
                .filter(sale -> sale.getSaleAmount() > 100)
                .mapToDouble(Sale::getSaleAmount)
                .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        double parallelTotal = salesData.parallelStream()
                .filter(sale -> sale.getSaleAmount() > 100)
                .mapToDouble(Sale::getSaleAmount)
                .sum();
        long parallelTime = System.currentTimeMillis() - startTime;

        System.out.printf("Sequential processing: $%.2f (Time: %dms)%n", sequentialTotal, sequentialTime);
        System.out.printf("Parallel processing: $%.2f (Time: %dms)%n", parallelTotal, parallelTime);
        System.out.printf("Results match: %b%n", Math.abs(sequentialTotal - parallelTotal) < 0.01);
        System.out.println();

        // ix) Map to SaleSummary objects
        System.out.println("=== ix) Sales mapped to SaleSummary objects ===");
        List<SaleSummary> saleSummaries = salesData.stream()
                .collect(Collectors.groupingBy(
                        Sale::getProductName,
                        Collectors.summingDouble(Sale::getSaleAmount)
                ))
                .entrySet().stream()
                .map(entry -> new SaleSummary(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(SaleSummary::getTotalSalesAmount).reversed())
                .collect(Collectors.toList());

        System.out.println("Product sales summaries:");
        saleSummaries.forEach(System.out::println);
        System.out.println();

        // x) Calculate the average sales amount across all sales
        System.out.println("=== x) Average sales amount ===");
        OptionalDouble averageSales = salesData.stream()
                .mapToDouble(Sale::getSaleAmount)
                .average();

        if (averageSales.isPresent()) {
            System.out.printf("Average sales amount: $%.2f%n", averageSales.getAsDouble());
        } else {
            System.out.println("No sales data available for average calculation");
        }

        System.out.println("\n=== Additional Statistics ===");
        DoubleSummaryStatistics stats = salesData.stream()
                .mapToDouble(Sale::getSaleAmount)
                .summaryStatistics();

        System.out.printf("Count: %d%n", stats.getCount());
        System.out.printf("Sum: $%.2f%n", stats.getSum());
        System.out.printf("Average: $%.2f%n", stats.getAverage());
        System.out.printf("Min: $%.2f%n", stats.getMin());
        System.out.printf("Max: $%.2f%n", stats.getMax());

        System.out.println("\n=== Advanced Stream Operations ===");

        Map<String, Double> customerTotals = salesData.stream()
                .collect(Collectors.groupingBy(
                        Sale::getCustomerId,
                        Collectors.summingDouble(Sale::getSaleAmount)
                ));

        System.out.println("Top 3 customers by total purchases:");
        customerTotals.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue()));

        System.out.println("\nMonthly sales summary:");
        Map<Month, Double> monthlySales = salesData.stream()
                .collect(Collectors.groupingBy(
                        sale -> sale.getDateOfSale().getMonth(),
                        Collectors.summingDouble(Sale::getSaleAmount)
                ));

        monthlySales.entrySet().stream()
                .sorted(Map.Entry.<Month, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue()));

        System.out.println("\nProduct performance (average sale amount per product):");
        Map<String, Double> productAverages = salesData.stream()
                .collect(Collectors.groupingBy(
                        Sale::getProductName,
                        Collectors.averagingDouble(Sale::getSaleAmount)
                ));

        productAverages.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s: $%.2f average%n", entry.getKey(), entry.getValue()));
    }
}