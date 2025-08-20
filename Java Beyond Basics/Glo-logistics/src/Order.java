import java.util.ArrayList;
import java.util.List;

class Order {
    private String orderNumber;
    private Customer customer;
    private List<Item> items;
    private String status;

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void addItem(Item item) {
        if (items == null) items = new ArrayList<>();
        items.add(item);
    }

    public void removeItem(Item item) {
        if (items != null) items.remove(item);
    }
}