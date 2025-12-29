package game;

import java.util.*;

public class GameLocation {
    private String name;
    private int dangerLevel;
    private List<GameLocation> connections;

    public GameLocation(String name, int dangerLevel) {
        this.name = name;
        this.dangerLevel = dangerLevel;
        this.connections = new ArrayList<>();
    }

    public void addConnection(GameLocation location) {
        if (!connections.contains(location)) {
            connections.add(location);
        }
    }

    public String getName() { return name; }
    public int getDangerLevel() { return dangerLevel; }
    public List<GameLocation> getConnections() { return connections; }

    @Override
    public String toString() { return name + " (Уровень опасности: " + dangerLevel + ")"; }
}
