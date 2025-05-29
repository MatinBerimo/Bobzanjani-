package game;

public class Tile {
    private String type; // نوع زمین (خاکی، آب، جنگل و ...)
    private boolean isBuildable; // آیا قابل ساخت است؟
    private Building building; // The building placed on the tile (if any)

    public Tile(String type, boolean isBuildable) {
        this.type = type;
        this.isBuildable = isBuildable;
        this.building = null; // Initially no building is placed
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public boolean isBuildable() {
        return isBuildable;
    }

    public Building getBuilding() {
        return building;
    }

    public void placeBuilding(Building building) {
        if (isBuildable) {
            this.building = building;
        }
    }
}
