package cityrescue;

import cityrescue.exceptions.InvalidLocationException;

public class CityMap {
    private int mapHeight;
    private int mapWidth;
    private boolean[][] blocked;

    public CityMap(int mapHeight, int mapWidth) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.blocked = new boolean[mapHeight][mapWidth];
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
}
