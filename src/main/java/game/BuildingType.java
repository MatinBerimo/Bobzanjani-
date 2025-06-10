package game;

public enum BuildingType {
    HOUSE(100, 50, 0, 50, 10),  // Cost, Materials, Energy, Energy Generated per minute, Population Generated per minute
    FACTORY(350, 0, 0, 0, 0),
    PARK(200, 50, 0, 0, 0),
    ROAD(50, 20, 0, 0, 0);

    private final int buildCost;
    private final int materialsCost;
    private final int energyConsumption;
    private final int dailyIncome;
    private final int populationIncrease;

    // Constructor
    BuildingType(int buildCost, int materialsCost, int energyConsumption, int dailyIncome, int populationIncrease) {
        this.buildCost = buildCost;
        this.materialsCost = materialsCost;
        this.energyConsumption = energyConsumption;
        this.dailyIncome = dailyIncome;
        this.populationIncrease = populationIncrease;
    }

    public int getBuildCost() {
        return buildCost;
    }

    public int getMaterialsCost() {
        return materialsCost;
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
