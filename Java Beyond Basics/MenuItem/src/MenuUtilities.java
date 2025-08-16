class MenuUtilities {
    public static boolean isValidPrice(MenuItem item) {
        return item.getPrice() > 0;
    }

    public static boolean hasValidName(MenuItem item) {
        return item.getName() != null && !item.getName().trim().isEmpty();
    }

    public static MenuItem addTax(MenuItem item) {
        double taxRate = 0.08; // 8% tax
        MenuItem taxedItem = new MenuItem(item.getName(), item.getPrice() * (1 + taxRate), item.getCategory());
        return taxedItem;
    }
}