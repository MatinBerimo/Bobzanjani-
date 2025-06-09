package game;

public class Resources {
    private int money;
    private int materials;
    private int energy;
    private int population;

    // Constructor to initialize resources
    public Resources(int money, int materials, int energy, int population) {
        this.money = money;
        this.materials = materials;
        this.energy = energy;
        this.population = population;
    }

    // Getters and setters for resources
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMaterials() {
        return materials;
    }

    public void setMaterials(int materials) {
        this.materials = materials;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    // Method to check if there are enough resources for a building
    public boolean canBuild(int buildCost, int energyConsumption) {
        return money >= buildCost && energy >= energyConsumption;
    }

    // Method to spend resources when a building is constructed
    public void spendResources(int buildCost, int energyConsumption) {
        money -= buildCost;
        energy -= energyConsumption;
    }

    // Method to update resources daily
    public void updateDaily(int dailyIncome, int energyUsage) {
        money += dailyIncome;
        energy -= energyUsage; // Assume some energy consumption for all buildings
    }
}
