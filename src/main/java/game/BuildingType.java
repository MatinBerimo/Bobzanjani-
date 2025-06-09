package game;

public enum BuildingType {
    HOUSE(500, 0, 0, 10),  // Cost, Energy Consumption, Daily Income, Population Increase
    FACTORY(1000, 50, 0, 50),
    PARK(200, 0, 0, 0),
    ROAD(50, 0, 0, 0);

    private final int buildCost;
    private final int energyConsumption;
    private final int dailyIncome;
    private final int populationIncrease;

    // Constructor
    BuildingType(int buildCost, int energyConsumption, int dailyIncome, int populationIncrease) {
        this.buildCost = buildCost;
        this.energyConsumption = energyConsumption;
        this.dailyIncome = dailyIncome;
        this.populationIncrease = populationIncrease;
    }

    public int getBuildCost() {
        return buildCost;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public int getDailyIncome() {
        return dailyIncome;
    }

    public int getPopulationIncrease() {
        return populationIncrease;
    }
}
