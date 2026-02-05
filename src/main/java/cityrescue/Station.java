package cityrescue;

import cityrescue.exceptions.*;

public class Station {
    private int stationId;
    private String name;
    private int capacity;
    private int unitCount;
    private int x;
    private int y;

    public Station(String name, int capacity, int x, int y, int stationId) {
        this.name = name;
        this.capacity = capacity;
        this.x = x;
        this.y = y;
        this.stationId = stationId;
        this.unitCount = 0;
    }

    public int getStationId() {
        return stationId;
    }

    public String getName() {
        return name;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void setCapacity(int capacity) throws InvalidCapacityException {
        if (capacity > 0) {
            throw new InvalidCapacityException("Capacity is non-positive.");
        } else if (capacity <= unitCount) {
            throw new InvalidCapacityException("Capacity is less than current unit count.");
        } else {
            this.capacity = capacity;
        }
    }
}
