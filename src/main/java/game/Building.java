package game;

//jjjj

public class Building {
    private String name; // Name of the building (e.g., House, Road)
    private int cost; // Cost of building
    private String type; // Type of building (e.g., Residential, Commercial)
    private boolean canBuildOnGrass; // Can this building be built on grass?

    public Building(String name, int cost, String type, boolean canBuildOnGrass) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.canBuildOnGrass = canBuildOnGrass;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public boolean canBuildOnGrass() {
        return canBuildOnGrass;
    }
}
