package nl.duckstudios.pintandpillage.entity.buildings;

import nl.duckstudios.pintandpillage.model.ResourceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private Storage storage;

    @BeforeEach
    public void setup() {
        this.storage = new Storage();
    }

    @Test
    public void shouldInitializeWithCorrectName() {
        final String expectedName = "Storage";
        assertEquals(expectedName, storage.getName());
    }

    @Test
    public void shouldInitializeWithCorrectDescription() {
        final String expectedDescription = "A building that increases your storage capacity";
        assertEquals(expectedDescription, storage.getDescription());
    }

    @Test
    public void shouldCalculateCorrectResourceCapacityAtLevel1() {
        final int baseCapacity = 600;
        final int capacityPerLevel = 2500;
        final int level = 1;
        final int additionalCapacity = 1;

        storage.setLevel(level);
        storage.updateBuilding();
        assertEquals(baseCapacity + capacityPerLevel * level + additionalCapacity, storage.getResourceCapacity());
    }

    @Test
    public void shouldCalculateCorrectResourceCapacityAtLevel5() {
        final int baseCapacity = 600;
        final int capacityPerLevel = 2500;
        final int level = 5;
        final int additionalCapacity = 1;

        storage.setLevel(level);
        storage.updateBuilding();
        assertEquals(baseCapacity + capacityPerLevel * level + additionalCapacity, storage.getResourceCapacity());
    }

    @Test
    public void shouldCalculateCorrectConstructionTimeAtLevel1() {
        final int baseConstructionTime = 5;
        final int constructionTimePerLevel = 30;
        final int level = 1;
        final int additionalTime = 1;

        storage.setLevel(level);
        storage.updateBuilding();
        assertEquals(baseConstructionTime + constructionTimePerLevel * level + additionalTime, storage.getConstructionTimeSeconds());
    }

    @Test
    public void shouldCalculateCorrectConstructionTimeAtLevel5() {
        final int baseConstructionTime = 5;
        final int constructionTimePerLevel = 30;
        final int level = 5;
        final int additionalTime = 1;

        storage.setLevel(level);
        storage.updateBuilding();
        assertEquals(baseConstructionTime + constructionTimePerLevel * level + additionalTime, storage.getConstructionTimeSeconds());
    }

    @Test
    public void shouldCalculateCorrectResourcesRequiredForLevelUpAtLevel1() {
        final int level = 1;
        final int stoneMultiplier = 25;
        final int stoneBaseMultiplier = 25;

        storage.setLevel(level);
        storage.updateBuilding();
        assertEquals(((level + 1) * stoneMultiplier) * stoneBaseMultiplier, storage.getResourcesRequiredLevelUp().get(ResourceType.Stone.name()));
    }

    @Test
    public void shouldCalculateCorrectResourcesRequiredForLevelUpAtLevel5() {
        final int level = 5;
        final int stoneMultiplier = 25;
        final int stoneBaseMultiplier = 25;

        storage.setLevel(level);
        storage.updateBuilding();
        assertEquals(((level + 1) * stoneMultiplier) * stoneBaseMultiplier, storage.getResourcesRequiredLevelUp().get(ResourceType.Stone.name()));
    }

    @Test
    public void shouldHaveCorrectResourceCapacityAfterLevelUp() {
        final int initialLevel = 1;
        final int newLevel = 2;

        storage.setLevel(initialLevel);
        storage.updateBuilding();
        int initialCapacity = storage.getResourceCapacity();
        
        storage.setLevel(newLevel);
        storage.updateBuilding();
        int newCapacity = storage.getResourceCapacity();
        
        assertTrue(newCapacity > initialCapacity);
    }
} 