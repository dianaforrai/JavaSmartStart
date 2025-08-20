class Shipment {
    private String trackingNumber;
    private Address sender;
    private Address receiver;
    private double weight;
    private Status status;
    private Carrier carrier;
    private Warehouse warehouse;
    private Route route;
    private String currentLocation;

    // Getters and setters
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public Address getSender() { return sender; }
    public void setSender(Address sender) { this.sender = sender; }
    public Address getReceiver() { return receiver; }
    public void setReceiver(Address receiver) { this.receiver = receiver; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Carrier getCarrier() { return carrier; }
    public void setCarrier(Carrier carrier) { this.carrier = carrier; }
    public Warehouse getWarehouse() { return warehouse; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }
    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }
    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }
}
