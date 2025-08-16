import java.util.*;
import java.util.function.Predicate;

public class RestaurantMenuSystem {

    public static void main(String[] args) {
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem("Caesar Salad", 12.99, "appetizer"),
                new MenuItem("Grilled Chicken", 18.50, "main course"),
                new MenuItem("Chocolate Cake", 8.75, "dessert"),
                new MenuItem("Fish and Chips", 16.25, "main course"),
                new MenuItem("Garlic Bread", 6.50, "appetizer"),
                new MenuItem("Ice Cream", 5.99, "dessert"),
                new MenuItem("Mystery Dish", 20.00, "unknown")
        );

        CategorizeMenuItem smartCategorizer = (item) -> {
            String name = item.getName().toLowerCase();
            if (name.contains("salad") || name.contains("bread")) {
                return "appetizer";
            } else if (name.contains("chicken") || name.contains("fish") || name.contains("steak")) {
                return "main course";
            } else if (name.contains("cake") || name.contains("ice cream") || name.contains("pie")) {
                return "dessert";
            } else if (name.contains("coffee") || name.contains("tea") || name.contains("juice")) {
                return "beverage";
            } else {
                return "unknown";
            }
        };

        ApplySpecialOffer specialOfferApplier = (item) -> {
            MenuItem discountedItem = new MenuItem(item.getName(), item.getPrice(), item.getCategory());

            switch (item.getCategory().toLowerCase()) {
                case "appetizer":
                    // 15% discount on appetizers
                    discountedItem.setPrice(item.getPrice() * 0.85);
                    break;
                case "main course":
                    // 10% discount on main courses
                    discountedItem.setPrice(item.getPrice() * 0.90);
                    break;
                case "dessert":
                    // Buy one get 20% off
                    discountedItem.setPrice(item.getPrice() * 0.80);
                    break;
                case "beverage":
                    // 5% discount on beverages
                    discountedItem.setPrice(item.getPrice() * 0.95);
                    break;
                default:
                    // Part 4: Handle unknown categories
                    System.out.println("Warning: Unknown category '" + item.getCategory() +
                            "' for item '" + item.getName() + "'. No discount applied.");
                    // Could also throw exception: throw new UnknownCategoryException("Unknown category: " + item.getCategory());
                    break;
            }

            return discountedItem;
        };

        System.out.println("=== Restaurant Menu Management System ===\n");

        System.out.println("Original Menu Items:");
        menuItems.forEach(System.out::println);

        System.out.println("\n=== Smart Categorization ===");
        menuItems.forEach(item -> {
            String smartCategory = smartCategorizer.categorize(item);
            System.out.printf("Item: %-15s | Original: %-12s | Smart Category: %s%n",
                    item.getName(), item.getCategory(), smartCategory);
        });

        System.out.println("\n=== Applying Special Offers ===");
        menuItems.forEach(item -> {
            MenuItem discountedItem = specialOfferApplier.applyOffer(item);
            double savings = item.getPrice() - discountedItem.getPrice();
            System.out.printf("%-15s: $%.2f -> $%.2f (Saved: $%.2f)%n",
                    item.getName(), item.getPrice(), discountedItem.getPrice(), savings);
        });

        System.out.println("\n=== Handling Unknown Categories with Exception ===");

        ApplySpecialOffer strictOfferApplier = (item) -> {
            MenuItem discountedItem = new MenuItem(item.getName(), item.getPrice(), item.getCategory());

            switch (item.getCategory().toLowerCase()) {
                case "appetizer":
                    discountedItem.setPrice(item.getPrice() * 0.85);
                    break;
                case "main course":
                    discountedItem.setPrice(item.getPrice() * 0.90);
                    break;
                case "dessert":
                    discountedItem.setPrice(item.getPrice() * 0.80);
                    break;
                case "beverage":
                    discountedItem.setPrice(item.getPrice() * 0.95);
                    break;
                default:
                    throw new UnknownCategoryException("Cannot apply offer to unknown category: " + item.getCategory());
            }

            return discountedItem;
        };

        MenuItem unknownItem = new MenuItem("Mystery Special", 25.00, "mystery");
        try {
            strictOfferApplier.applyOffer(unknownItem);
        } catch (UnknownCategoryException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("\n=== Additional Functional Operations ===");

        Predicate<MenuItem> isMainCourse = item -> "main course".equalsIgnoreCase(item.getCategory());
        Predicate<MenuItem> isExpensive = item -> item.getPrice() > 15.00;

        System.out.println("Main course items:");
        menuItems.stream()
                .filter(isMainCourse)
                .forEach(item -> System.out.println("  " + item));

        System.out.println("\nExpensive items (>$15):");
        menuItems.stream()
                .filter(isExpensive)
                .forEach(item -> System.out.println("  " + item));

        System.out.println("\nTotal value by category:");
        Map<String, Double> totalByCategory = new HashMap<>();
        menuItems.forEach(item -> {
            totalByCategory.merge(item.getCategory(), item.getPrice(), Double::sum);
        });
        totalByCategory.forEach((category, total) ->
                System.out.printf("  %s: $%.2f%n", category, total));
    }
}

@FunctionalInterface
interface MenuItemValidator {
    boolean validate(MenuItem item);
}

@FunctionalInterface
interface MenuItemTransformer {
    MenuItem transform(MenuItem item);
}


