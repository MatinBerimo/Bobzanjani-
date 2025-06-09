package game;

import java.util.EnumMap;

public class ResourceManager {
    private EnumMap<ResourceType, Integer> resources = new EnumMap<>(ResourceType.class);

    public ResourceManager(int money, int materials, int energy, int population) {
        resources.put(ResourceType.MONEY, money);
        resources.put(ResourceType.MATERIALS, materials);
        resources.put(ResourceType.ENERGY, energy);
        resources.put(ResourceType.POPULATION, population);
    }

    public int get(ResourceType type) {
        return resources.getOrDefault(type, 0);
    }

    public void set(ResourceType type, int value) {
        resources.put(type, value);
    }

    public void add(ResourceType type, int amount) {
        resources.put(type, get(type) + amount);
    }

    public boolean canAfford(int buildCost, int energyConsumption) {
        return get(ResourceType.MONEY) >= buildCost && get(ResourceType.ENERGY) >= energyConsumption;
    }

    public void spendResources(int buildCost, int energyConsumption) {
        add(ResourceType.MONEY, -buildCost);
        add(ResourceType.ENERGY, -energyConsumption);
    }

    // Add more methods as needed
} 