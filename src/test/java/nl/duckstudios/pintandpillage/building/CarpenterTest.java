package nl.duckstudios.pintandpillage.building;

import nl.duckstudios.pintandpillage.entity.buildings.Building;
import nl.duckstudios.pintandpillage.entity.buildings.Carpenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarpenterTest {

    @Test
    void shouldBeAbleToInstantiate(){
        assertNotNull(new Carpenter());
    }

    @Test
    void carpenterShouldBeInstaneOfBuilding(){
        assertTrue(new Carpenter() instanceof Building);
    }

    @Test
    void shouldHaveCorrectName(){
        assertEquals("Carpenter", new Carpenter().getName());
    }

    @Test
    void shouldHaveBaseCostAtLevel0(){
        Carpenter carpenter = new Carpenter();
        assertEquals(45, carpenter.getResourcesRequiredLevelUp().get("Wood"));
        assertEquals(25, carpenter.getResourcesRequiredLevelUp().get("Stone"));
    }

    @Test
    void shouldHaveConstructionTimeAtInitialBuild(){
        assertEquals(50, new Carpenter().getConstructionTimeSeconds());
    }

    @Test
    void shouldCalculateCostOnUpdate(){
        Carpenter carpenter = new Carpenter();
        carpenter.setLevel(2);
        carpenter.updateBuilding();

        assertEquals(2 * 10 + 45, carpenter.getResourcesRequiredLevelUp().get("Wood"));
        assertEquals(2 * 25 + 25, carpenter.getResourcesRequiredLevelUp().get("Stone"));

    }

    @Test
    void shouldUpdateBuildTimeAccordingTolevel(){
        Carpenter carpenter = new Carpenter();
        carpenter.setLevel(3);

        carpenter.updateBuilding();
        int expectedTime = 20 * 3 + 50;
        assertEquals(expectedTime, carpenter.getConstructionTimeSeconds());
    }

    @Test
    void shouldConvertWoodToPlank(){
        Carpenter carpenter = new Carpenter();
        assertEquals(75, carpenter.turnWoodIntoPlanks(100));

    }

}
