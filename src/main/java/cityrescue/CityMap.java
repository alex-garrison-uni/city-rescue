package cityrescue;

import java.util.ArrayList;
import cityrescue.exceptions.*;

public class CityMap {
    private int mapHeight;
    private int mapWidth;
    private boolean[][] blocked;
    private ArrayList<Station> stations;
    private int currentStationId;

    public CityMap(int mapHeight, int mapWidth) throws InvalidGridException {
        if (mapWidth > 0 && mapHeight > 0) {
            this.mapHeight = mapHeight;
            this.mapWidth = mapWidth;
            this.blocked = new boolean[mapHeight][mapWidth];
            this.stations = new ArrayList<Station>();

            // Station IDs begin at 1
            this.currentStationId = 1;
        } else {
            throw new InvalidGridException("Invalid grid dimensions.");
        }
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

    public Station getStationById(int stationId) throws IDNotRecognisedException {
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);

            if (station.getStationId() == stationId) {
                return station;
            }
        }

        throw new IDNotRecognisedException("Station ID is not valid.");
    }

    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        if (name.isBlank()) {
            throw new InvalidNameException("Station name is empty.");
        } else if (!areCoordsInBounds(x, y)) {
            throw new InvalidLocationException("Coordinates are out of bounds.");
        } else if (blocked[y][x] == true) {
            throw new InvalidLocationException("Coordinates are blocked.");
        } else {
            Station newStation = new Station(name, CityRescue.DEFAULT_STATION_CAPACITY, x, y, currentStationId);
            stations.add(newStation);
            currentStationId++;

            return newStation.getStationId();
        }
    }

    void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        Station station = getStationById(stationId);

        if (station.getUnitCount() > 0) {
            throw new IllegalStateException("Station contains units.");
        } else {
            stations.remove(station);
        }
    }

    void setStationCapacity(int stationId, int capacity) throws IDNotRecognisedException, InvalidCapacityException {
        Station station = getStationById(stationId);

        station.setCapacity(capacity);
    }

    int[] getStationIds() {
        int[] stationIds = new int[stations.size()];

        for (int i = 0; i < stationIds.length; i++) {
            stationIds[i] = stations.get(i).getStationId();
        }

        return stationIds;
    }
}
