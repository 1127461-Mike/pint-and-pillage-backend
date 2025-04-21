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
        assertEquals("Storage", storage.getName());
    }

    @Test
    public void shouldInitializeWithCorrectDescription() {
        assertEquals("A building that increases your storage capacity", storage.getDescription());
    }

    @Test
    public void shouldCalculateCorrectResourceCapacityAtLevel1() {
        storage.setLevel(1);
        storage.updateBuilding();
        assertEquals(600 + 2500 * 1 + 1, storage.getResourceCapacity());
    }

    @Test
    public void shouldCalculateCorrectResourceCapacityAtLevel5() {
        storage.setLevel(5);
        storage.updateBuilding();
        assertEquals(600 + 2500 * 5 + 1, storage.getResourceCapacity());
    }

    @Test
    public void shouldCalculateCorrectConstructionTimeAtLevel1() {
        storage.setLevel(1);
        storage.updateBuilding();
        assertEquals(5 + 30L * 1 + 1, storage.getConstructionTimeSeconds());
    }

    @Test
    public void shouldCalculateCorrectConstructionTimeAtLevel5() {
        storage.setLevel(5);
        storage.updateBuilding();
        assertEquals(5 + 30L * 5 + 1, storage.getConstructionTimeSeconds());
    }

    @Test
    public void shouldCalculateCorrectResourcesRequiredForLevelUpAtLevel1() {
        storage.setLevel(1);
        storage.updateBuilding();
        assertEquals(((1 + 1) * 25) * 25, storage.getResourcesRequiredLevelUp().get(ResourceType.Stone.name()));
    }

    @Test
    public void shouldCalculateCorrectResourcesRequiredForLevelUpAtLevel5() {
        storage.setLevel(5);
        storage.updateBuilding();
        assertEquals(((5 + 1) * 25) * 25, storage.getResourcesRequiredLevelUp().get(ResourceType.Stone.name()));
    }

    @Test
    public void shouldImplementIStorableInterface() {
        assertTrue(storage instanceof IStorable);
    }

    @Test
    public void shouldHaveCorrectResourceCapacityAfterLevelUp() {
        storage.setLevel(1);
        storage.updateBuilding();
        int initialCapacity = storage.getResourceCapacity();
        
        storage.setLevel(2);
        storage.updateBuilding();
        int newCapacity = storage.getResourceCapacity();
        
        assertTrue(newCapacity > initialCapacity);
    }
} 