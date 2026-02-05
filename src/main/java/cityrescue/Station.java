package cityrescue;

import cityrescue.exceptions.*;

public class Station {
    int stationID;
    String name;
    int capacity;
    int unitCount;
    int x;
    int y;

    public Station(String name, int capacity, int x, int y, int stationID) {
        this.name = name;
        this.capacity = capacity;
        this.x = x;
        this.y = y;
        this.stationID = stationID;
        this.unitCount = 0;
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
