package nl.duckstudios.pintandpillage.entity.buildings;

import java.util.HashMap;
import java.util.Map;

public class Carpenter extends Building{
    private final String name = "Carpenter";

    public Carpenter() {

        Map<String, Integer> buildCost = new HashMap<>();

        buildCost.put("Wood", 45);
        buildCost.put("Stone", 25);
        this.setResourcesRequiredLevelUp(buildCost);

        this.setConstructionTimeSeconds(50);
    }

    @Override
    public void updateBuilding() {
        int level = this.getLevel();

        Map<String, Integer> cost = new HashMap<>();
        cost.put("Wood", level * 10 + 45);
        cost.put("Stone", level * 25 + 25);
        this.setResourcesRequiredLevelUp(cost);
        this.setConstructionTimeSeconds(20 * level + 50);
    }

    public String getName() {
        return name;
    }

    public int turnWoodIntoPlanks(int wood) {
        return (int) (wood * 0.75);
    }
}
