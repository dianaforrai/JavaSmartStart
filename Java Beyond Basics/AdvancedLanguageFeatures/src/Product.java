public record Product(String name, double price, int quantity) {
    public double totalValue() {
        return price * quantity;
    }
}
