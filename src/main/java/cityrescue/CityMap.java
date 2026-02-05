package cityrescue;

import cityrescue.exceptions.*;

public class CityMap {
    private int mapHeight;
    private int mapWidth;
    private boolean[][] blocked;
    private Station[] stations;
    private int stationCount;

    public CityMap(int mapHeight, int mapWidth) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.blocked = new boolean[mapHeight][mapWidth];
        this.stations = new Station[CityRescue.MAX_STATIONS];
        this.stationCount = 0;
    }

    public int[] getGridSize() {
        return new int[] {mapHeight, mapWidth};
    }

    public boolean areCoordsInBounds(int x, int y) {
        return (x >= 0 && x <= mapWidth - 1) && (y >= 0 && x <= mapHeight - 1);
    }
 
    public void setBlocked(int x, int y, boolean isBlocked) throws InvalidLocationException {
        if (areCoordsInBounds(x, y)) {
            blocked[y][x] = isBlocked;
        } else {
            throw new InvalidLocationException("Coordinates are out of bounds.");
        }
    }

    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        if (name.isBlank()) {
            throw new InvalidNameException("Station name is empty.");
        } else if (!areCoordsInBounds(x, y)) {
            throw new InvalidLocationException("Coordinates are out of bounds.");
        } else if (blocked[y][x] == true) {
            throw new InvalidLocationException("Coordinates are blocked.");
        } else {
            Station newStation = new Station(name, CityRescue.DEFAULT_STATION_CAPACITY, x, y, stationCount);
            stations[stationCount] = newStation;
            stationCount++;

            return newStation.stationID;
        }
    }

    void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        if (stations[stationId] == null) {
            throw new IDNotRecognisedException("Station ID is not valid.");
        } else if (stations[stationId].unitCount > 0) {
            throw new IllegalStateException("Station contains units.");
        } else {
            stations[stationId] = null;
        }
    }

    void setStationCapacity(int stationId, int capacity) throws IDNotRecognisedException, InvalidCapacityException {
        if (stations[stationId] == null) {
            throw new IDNotRecognisedException("Station ID is not valid.");
        } else {
            stations[stationId].setCapacity(capacity);;
        }
    }
}
