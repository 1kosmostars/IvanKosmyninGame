package game;

import model.Exseptions.InvalidActionException;
import java.util.*;

public class DungeonMap {
    private Map<String, GameLocation> locations = new HashMap<>();
    private GameLocation currentLocation;
    private Set<GameLocation> visitedLocations = new HashSet<>();

    public void addLocation(GameLocation loc) {
        locations.put(loc.getName(), loc);
        if (currentLocation == null) currentLocation = loc;
    }

    public void connectLocations(String name1, String name2) {
        GameLocation loc1 = locations.get(name1);
        GameLocation loc2 = locations.get(name2);
        if (loc1 != null && loc2 != null) {
            loc1.addConnection(loc2);
            loc2.addConnection(loc1);
        }
    }

    public void moveTo(String targetName) throws InvalidActionException {
        GameLocation target = locations.get(targetName);
        if (target == null || !currentLocation.getConnections().contains(target)) {
            throw new InvalidActionException("Cannot enter" + targetName + " from there");
        }
        currentLocation = target;
        visitedLocations.add(target);
        System.out.println("Entered: " + target.getName());
    }

    public List<GameLocation> getAccessibleLocations() {
        return currentLocation.getConnections();
    }
}
