package utils;

import model.items.Item;

/**
 * ממשק פונקציונלי לסינון פריטים.
 * משמש במחלקות עזר (Utils) כדי לבדוק האם פריט עומד בתנאי מסוים.
 */
@FunctionalInterface
public interface ItemFilter {
    /**
     * בודק האם הפריט עומד בתנאי הסינון.
     *
     * @param item הפריט לבדיקה
     * @return true אם הפריט עובר את הסינון, אחרת false
     */
    boolean filter(Item item);
}
