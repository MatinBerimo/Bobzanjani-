package game;

public enum BuildingType {
    // buildCost, materialsCost, energyCost, moneyYield, materialsYield,
    // energyYield, populationYield, cycleTime (sec)
    HOUSE(400, 50, 5, 15, 0, -5, 20, 60),
    FACTORY(1200, 200, 40, 300, 150, -30, 0, 120),
    POWER_PLANT(1000, 150, 0, -40, 0, 50, 0, 120),
    PARK(300, 30, 2, -5, 0, -1, 5, 90),
    ROAD(100, 10, 0, -2, 0, 0, 0, 60);

    private final int buildCost;
    private final int materialsCost;
    private final int energyCost;
    private final int moneyYield;
    private final int materialsYield;
    private final int energyYield;
    private final int populationYield;
    private final int cycleTime;

    // Constructor Building
   BuildingType(int buildCost, int materialsCost, int energyCost,int moneyYield, int materialsYield, int energyYield,int populationYield, int cycleTime) {
        this.buildCost = buildCost;
        this.materialsCost = materialsCost;
        this.energyCost = energyCost;
        this.moneyYield = moneyYield;
        this.materialsYield = materialsYield;
        this.energyYield = energyYield;
        this.populationYield = populationYield;
        this.cycleTime = cycleTime;
    }

    public int getBuildCost() {
        return buildCost;
    }

    public int getMaterialsCost() {
        return materialsCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public int getMoneyYield() {
        return moneyYield;
    }

    public int getMaterialsYield() {
        return materialsYield;
    }

    public int getEnergyYield() {
        return energyYield;
    }

    public int getPopulationYield() {
        return populationYield;
    }

    public int getCycleTime() {
        return cycleTime;
    }
}
