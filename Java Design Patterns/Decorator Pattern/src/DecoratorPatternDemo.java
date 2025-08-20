public class DecoratorPatternDemo {
    public static void main(String[] args) {
        // Basic coffee
        Coffee myCoffee = new BasicCoffee();

        // Add milk
        myCoffee = new Milk(myCoffee);

        // Add sugar
        myCoffee = new Sugar(myCoffee);

        // Add flavorings
        myCoffee = new Flavorings(myCoffee);

        // Display final coffee description and cost
        System.out.println("Coffee Description: " + myCoffee.getDescription());
        System.out.println("Total Cost: $" + myCoffee.getCost());
    }
}
