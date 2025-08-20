import java.util.ArrayList;
import java.util.List;

class Warehouse {
    private Location location;
    private int capacity;
    private List<Item> inventory;

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public List<Item> getInventory() { return inventory; }
    public void setInventory(List<Item> inventory) { this.inventory = inventory; }

    public void addInventory(Item item) {
        if (inventory == null) inventory = new ArrayList<>();
        inventory.add(item);
    }

    public void removeInventory(Item item) {
        if (inventory != null) inventory.remove(item);
    }

    public void setInventory(ArrayList<Object> objects) {
    }
}