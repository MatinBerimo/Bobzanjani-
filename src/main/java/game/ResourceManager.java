package game;

import java.util.EnumMap;

public class ResourceManager {
    private final EnumMap<ResourceType, Integer> resources = new EnumMap<>(ResourceType.class);

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

    public boolean canAfford(int buildCost, int materialsCost, int energyConsumption) {
        return get(ResourceType.MONEY) >= buildCost &&
               get(ResourceType.MATERIALS) >= materialsCost &&
               get(ResourceType.ENERGY) >= energyConsumption;
    }

    public void spendResources(int buildCost, int materialsCost, int energyConsumption) {
        add(ResourceType.MONEY, -buildCost);
        add(ResourceType.MATERIALS, -materialsCost);
        add(ResourceType.ENERGY, -energyConsumption);
    }

    /**
     * Determines whether a resource has dropped below a given threshold.
     *
     * @param type the resource type to check
     * @param threshold the minimum acceptable amount
     * @return {@code true} if the resource amount is below the threshold
     */
    public boolean needsAlert(ResourceType type, int threshold) {
        return get(type) < threshold;
    }

    /**
     * Generates a warning message when a resource falls below a threshold.
     * If the resource level is acceptable, an empty string is returned.
     *
     * @param type the resource type to check
     * @param threshold the minimum acceptable amount
     * @return a warning message or an empty string
     */
    public String getAlert(ResourceType type, int threshold) {
        if (needsAlert(type, threshold)) {
            return type.name() + " is below the threshold of " + threshold;
        }
        return "";
    }

    // Add more methods as needed
}
