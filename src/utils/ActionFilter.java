package utils;

import game.BattleAction;

/**
 * ממשק פונקציונלי לסינון פעולות קרב.
 * מאפשר להגדיר תנאים לחיפוש או סינון בתוך תור הפעולות.
 */
@FunctionalInterface
public interface ActionFilter {
    /**
     * בודק האם פעולת הקרב עומדת בתנאי מסוים.
     *
     * @param action הפעולה לבדיקה
     * @return true אם הפעולה עוברת את הסינון, אחרת false
     */
    boolean filter(BattleAction action);
}
