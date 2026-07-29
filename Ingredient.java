/*
Ingredient.java
 
Created by Steven
 
Represents a single ingredient used in a menu item.
This is the innermost object in the program:
 
    Restaurant -> MenuItem -> Ingredient
*/
 
public class Ingredient {
 
    // ==========================================================
    // Instance variables
    // ==========================================================
 
    private String name;        // name of the ingredient
    private int calories;       // calories contributed by this ingredient
    private boolean allergen;   // true if this ingredient is a common allergen
 
    // ==========================================================
    // Constructors
    // ==========================================================
 
    /*
    Default constructor.
    Sets the ingredient to safe starting values.
    */
    public Ingredient() {
 
        this.name = "Unknown";
        this.calories = 0;
        this.allergen = false;
    }
 
    /*
    Full constructor.
    Used by the main program when reading data from the CSV file.
    */
    public Ingredient(String name, int calories, boolean allergen) {
 
        this.name = name;
        this.calories = calories;
        this.allergen = allergen;
    }
 
    // ==========================================================
    // Getters
    // ==========================================================
 
    public String getName() {
        return name;
    }
 
    public int getCalories() {
        return calories;
    }
 
    /*
    Boolean getter.
    Named "isAllergen" to follow standard Java naming for booleans.
    */
    public boolean isAllergen() {
        return allergen;
    }
 
    // ==========================================================
    // Setters
    // ==========================================================
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setCalories(int calories) {
        this.calories = calories;
    }
 
    public void setAllergen(boolean allergen) {
        this.allergen = allergen;
    }
 
    // ==========================================================
    // toString
    // ==========================================================
 
    /*
    Returns a readable description of the ingredient.
    An allergen warning is added only when the ingredient is an allergen.
    */
    @Override
    public String toString() {
 
        String warning = "";
 
        if (allergen) {
            warning = "  ** ALLERGEN **";
        }
 
        return name + " (" + calories + " cal)" + warning;
    }
}