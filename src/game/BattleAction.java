package game;

public abstract class BattleAction {
    protected int priority;
    protected String description;

    public BattleAction(int priority, String description) {
        this.priority = priority;
        this.description = description;
    }

    public abstract void execute();
    public int getPriority() { return priority; }
    public String getDescription()
    {
        return this.description;
    }
}
