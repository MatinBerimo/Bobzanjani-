package game;

//jjjjlo

public class Building {
    BuildingType buildingType;
    private int customCost; // This could be modified during gameplay
    
    // Constructor
    public Building(BuildingType buildingType) {
        this.buildingType = buildingType;
        this.customCost = buildingType.getBuildCost(); // Initial cost from type
    }

    // Setters and getters
    public int getCost() {
        return customCost; // Get the cost of the specific building instance
    }

    // Example of cost modification (e.g., after upgrades)
    public void upgradeBuilding(int upgradeCost) {
        this.customCost += upgradeCost; // The cost could change based on upgrades
    }
}
