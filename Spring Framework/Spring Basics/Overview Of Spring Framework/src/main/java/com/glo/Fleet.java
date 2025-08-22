package com.glo;

public class Fleet {
    private int fleetId;
    private String fleetName;

    public int getFleetId() {
        return fleetId;
    }

    public void setFleetId(int fleetId) {
        this.fleetId = fleetId;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    @Override
    public String toString() {
        return "Fleet [fleetId=" + fleetId + ", fleetName=" + fleetName + "]";
    }
}
