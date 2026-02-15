package model;

// Represents the fixed set of spending categories that an expense can belong to
public enum Category {

    FOOD("Food");

    private final String displayName;

    // EFFECTS: constructs a Category with the given display name
    Category(String displayName) {
        this.displayName = null; // stub
    }

    // EFFECTS: returns the display name of a category
    public String getDisplayName() {
        return null; // stub
    }
}
