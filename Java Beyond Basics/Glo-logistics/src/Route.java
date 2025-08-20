class Route {
    private Location origin;
    private Location destination;
    private float distance;

    public Location getOrigin() { return origin; }
    public void setOrigin(Location origin) { this.origin = origin; }
    public Location getDestination() { return destination; }
    public void setDestination(Location destination) { this.destination = destination; }
    public float getDistance() { return distance; }
    public void setDistance(float distance) { this.distance = distance; }

    public LocalDateTime calculateETA() {
        // Simple ETA calculation based on distance
        int hoursToDeliver = (int) (distance / 50); // Assuming 50 km/h average speed
        return LocalDateTime.now().plusHours(hoursToDeliver);
    }
}