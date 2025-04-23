package nl.duckstudios.pintandpillage.entity.production;

import nl.duckstudios.pintandpillage.model.ResearchType;
import nl.duckstudios.pintandpillage.model.ResourceType;
import nl.duckstudios.pintandpillage.model.UnitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ShieldTest {

    private Shield shield;

    @BeforeEach
    public void setup() {
        this.shield = new Shield();
    }

    @Test
    public void shouldInitializeWithCorrectResearchRequirement() {
        assertEquals(ResearchType.Shield, shield.getResearchRequired());
    }

    @Test
    public void shouldInitializeWithCorrectBaseSecondsToProduce() {
        final long expectedBaseSecondsToProduce = 610;
        assertEquals(expectedBaseSecondsToProduce, shield.getBaseSecondsToProduce());
    }

    @Test
    public void shouldInitializeWithCorrectUnitType() {
        assertEquals(UnitType.Shield, shield.getUnitName());
    }

    @Test
    public void shouldInitializeWithCorrectStats() {
        final int expectedAttack = 5;
        final int expectedDefence = 50;
        final int expectedHealth = 40;
        final int expectedSpeed = 5;
        final int expectedPlunderAmount = 0;

        assertEquals(expectedAttack, shield.getAttack());
        assertEquals(expectedDefence, shield.getDefence());
        assertEquals(expectedHealth, shield.getHealth());
        assertEquals(expectedSpeed, shield.getSpeed());
        assertEquals(expectedPlunderAmount, shield.getPlunderAmount());
    }

    @Test
    public void shouldInitializeWithCorrectDescription() {
        final String expectedDescription = "Great defensive unit that carries a large rounded shield and a small axe";
        assertEquals(expectedDescription, shield.getDescription());
    }

    @Test
    public void shouldInitializeWithCorrectPopulationRequired() {
        final int expectedPopulationRequired = 1;
        assertEquals(expectedPopulationRequired, shield.getPopulationRequiredPerUnit());
    }

    @Test
    public void shouldInitializeWithCorrectBaseTimeToProduce() {
        final long baseSecondsToProduce = 610;
        LocalTime expectedTime = LocalTime.of(0, 0, 0).plusSeconds(baseSecondsToProduce);
        assertEquals(expectedTime, shield.getBaseTimeToProduce());
    }

    @Test
    public void shouldInitializeWithCorrectResourceRequirements() {
        final int expectedWoodRequired = 20;
        final int expectedBeerRequired = 50;
        final int expectedStoneRequired = 50;

        assertEquals(expectedWoodRequired, shield.getResourcesRequiredToProduce().get(ResourceType.Wood.name()));
        assertEquals(expectedBeerRequired, shield.getResourcesRequiredToProduce().get(ResourceType.Beer.name()));
        assertEquals(expectedStoneRequired, shield.getResourcesRequiredToProduce().get(ResourceType.Stone.name()));
    }

    @Test
    public void shouldHaveCorrectResourceRequirementsCount() {
        final int expectedResourceTypesCount = 3;
        assertEquals(expectedResourceTypesCount, shield.getResourcesRequiredToProduce().size());
    }
} 