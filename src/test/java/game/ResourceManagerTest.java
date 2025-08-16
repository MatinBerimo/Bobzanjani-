package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceManagerTest {

    @Test
    void testCanAffordWithSufficientResources() {
        ResourceManager manager = new ResourceManager(100, 50, 75, 0);
        assertTrue(manager.canAfford(80, 40, 60));
    }

    @Test
    void testCanAffordWithInsufficientResources() {
        ResourceManager manager = new ResourceManager(100, 50, 75, 0);
        assertFalse(manager.canAfford(120, 40, 60));
        assertFalse(manager.canAfford(80, 60, 60));
        assertFalse(manager.canAfford(80, 40, 80));
    }

    @Test
    void testSpendResourcesDeductsValues() {
        ResourceManager manager = new ResourceManager(100, 50, 75, 0);
        manager.spendResources(30, 20, 25);
        assertEquals(70, manager.get(ResourceType.MONEY));
        assertEquals(30, manager.get(ResourceType.MATERIALS));
        assertEquals(50, manager.get(ResourceType.ENERGY));
    }

    @Test
    void testSpendResourcesDoesNotUnderflow() {
        ResourceManager manager = new ResourceManager(30, 20, 10, 0);
        manager.spendResources(30, 20, 10);
        assertEquals(0, manager.get(ResourceType.MONEY));
        assertEquals(0, manager.get(ResourceType.MATERIALS));
        assertEquals(0, manager.get(ResourceType.ENERGY));
        assertTrue(manager.get(ResourceType.MONEY) >= 0);
        assertTrue(manager.get(ResourceType.MATERIALS) >= 0);
        assertTrue(manager.get(ResourceType.ENERGY) >= 0);
    }
}
