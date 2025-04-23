package nl.duckstudios.pintandpillage.entity.buildings;

import nl.duckstudios.pintandpillage.model.ResourceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefenceTowerTest {

    private DefenceTower defenceTower;

    @BeforeEach
    public void setup() {
        this.defenceTower = new DefenceTower();
    }

    @Test
    public void shouldInitializeWithCorrectName() {
        final String expectedName = "DefenceTower";
        assertEquals(expectedName, defenceTower.getName());
    }

    @Test
    public void shouldInitializeWithCorrectDescription() {
        final String expectedDescription = "A tower that will increase your village defence and help detect enemy scouts";
        assertEquals(expectedDescription, defenceTower.getDescription());
    }

    @Test
    public void shouldCalculateCorrectDefenceBonusAtLevel1() {
        final int baseDefenceBonus = 100;
        final int defenceBonusPerLevel = 75;
        final int level = 1;

        defenceTower.setLevel(level);
        defenceTower.updateBuilding();
        assertEquals(baseDefenceBonus + defenceBonusPerLevel * level, defenceTower.getDefenceBonus());
    }

    @Test
    public void shouldCalculateCorrectDefenceBonusAtLevel5() {
        final int baseDefenceBonus = 100;
        final int defenceBonusPerLevel = 75;
        final int level = 5;

        defenceTower.setLevel(level);
        defenceTower.updateBuilding();
        assertEquals(baseDefenceBonus + defenceBonusPerLevel * level, defenceTower.getDefenceBonus());
    }

    @Test
    public void shouldCalculateCorrectConstructionTimeAtLevel1() {
        final int baseConstructionTime = 12;
        final int baseConstructionTimePerLevel = 30;
        final int constructionTimeMultiplier = 2;
        final double constructionTimePower = 1.6;
        final int level = 1;

        defenceTower.setLevel(level);
        defenceTower.updateBuilding();
        assertEquals(baseConstructionTime + ((baseConstructionTimePerLevel + (level - 1) * constructionTimeMultiplier) * (long) Math.pow(level, constructionTimePower)), defenceTower.getConstructionTimeSeconds());
    }

    @Test
    public void shouldCalculateCorrectConstructionTimeAtLevel5() {
        final int baseConstructionTime = 12;
        final int baseConstructionTimePerLevel = 30;
        final int constructionTimeMultiplier = 2;
        final double constructionTimePower = 1.6;
        final int level = 5;

        defenceTower.setLevel(level);
        defenceTower.updateBuilding();
        assertEquals(baseConstructionTime + ((baseConstructionTimePerLevel + (level - 1) * constructionTimeMultiplier) * (long) Math.pow(level, constructionTimePower)), defenceTower.getConstructionTimeSeconds());
    }

    @Test
    public void shouldCalculateCorrectResourcesRequiredForLevelUpAtLevel1() {
        final int baseWoodRequirement = 300;
        final int baseStoneRequirement = 300;
        final int baseBeerRequirement = 100;
        final double resourcePowerMultiplier = 0.2;
        final int woodPowerBase = 50;
        final int stonePowerBase = 125;
        final int beerPowerBase = 25;
        final int level = 1;

        defenceTower.setLevel(level);
        defenceTower.updateBuilding();
        assertEquals(baseWoodRequirement + (int) Math.pow(woodPowerBase, level * resourcePowerMultiplier), defenceTower.getResourcesRequiredLevelUp().get(ResourceType.Wood.name()));
        assertEquals(baseStoneRequirement + (int) Math.pow(stonePowerBase, level * resourcePowerMultiplier), defenceTower.getResourcesRequiredLevelUp().get(ResourceType.Stone.name()));
        assertEquals(baseBeerRequirement + (int) Math.pow(beerPowerBase, level * resourcePowerMultiplier), defenceTower.getResourcesRequiredLevelUp().get(ResourceType.Beer.name()));
    }

    @Test
    public void shouldCalculateCorrectResourcesRequiredForLevelUpAtLevel5() {
        final int baseWoodRequirement = 300;
        final int baseStoneRequirement = 300;
        final int baseBeerRequirement = 100;
        final double resourcePowerMultiplier = 0.2;
        final int woodPowerBase = 50;
        final int stonePowerBase = 125;
        final int beerPowerBase = 25;
        final int level = 5;

        defenceTower.setLevel(level);
        defenceTower.updateBuilding();
        assertEquals(baseWoodRequirement + (int) Math.pow(woodPowerBase, level * resourcePowerMultiplier), defenceTower.getResourcesRequiredLevelUp().get(ResourceType.Wood.name()));
        assertEquals(baseStoneRequirement + (int) Math.pow(stonePowerBase, level * resourcePowerMultiplier), defenceTower.getResourcesRequiredLevelUp().get(ResourceType.Stone.name()));
        assertEquals(baseBeerRequirement + (int) Math.pow(beerPowerBase, level * resourcePowerMultiplier), defenceTower.getResourcesRequiredLevelUp().get(ResourceType.Beer.name()));
    }

    @Test
    public void shouldImplementIDefenceableInterface() {
        assertTrue(defenceTower instanceof IDefenceable);
    }

    @Test
    public void shouldHaveCorrectDefenceBonusAfterLevelUp() {
        final int level1 = 1;
        final int level2 = 2;

        defenceTower.setLevel(level1);
        defenceTower.updateBuilding();
        int initialBonus = defenceTower.getDefenceBonus();
        
        defenceTower.setLevel(level2);
        defenceTower.updateBuilding();
        int newBonus = defenceTower.getDefenceBonus();
        
        assertTrue(newBonus > initialBonus);
    }
} 