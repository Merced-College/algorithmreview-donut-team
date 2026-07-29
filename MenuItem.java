/*
MenuItem.java
 
Created by Mitchelle
 
Represents one item on a restaurant's menu.
Each MenuItem contains many Ingredient objects stored in an ArrayList.
*/
 
import java.util.ArrayList;
 
public class MenuItem {
 
    // ==========================================================
    // Instance variables
    // ==========================================================
 
    private String name;                        // name of the menu item
    private String description;                 // short description of the item
    private double price;                       // price in dollars
    private ArrayList<Ingredient> ingredients;  // all ingredients in this item
 
    // ==========================================================
    // Constructors
    // ==========================================================
 
    /*
    Default constructor.
    The ArrayList must still be created so ingredients can be added later.
    */
    public MenuItem() {
 
        this.name = "Unknown";
        this.description = "";
        this.price = 0.0;
        this.ingredients = new ArrayList<Ingredient>();
    }
 
    /*
    Main constructor used by the program.
    The ingredient list starts empty and is filled by addIngredient().
    */
    public MenuItem(String name, String description, double price) {
 
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients = new ArrayList<Ingredient>();
    }
 
    /*
    Overloaded constructor that accepts an existing list of ingredients.
    */
    public MenuItem(String name, String description, double price,
                    ArrayList<Ingredient> ingredients) {
 
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }
 
    // ==========================================================
    // Getters
    // ==========================================================
 
    public String getName() {
        return name;
    }
 
    public String getDescription() {
        return description;
    }
 
    public double getPrice() {
        return price;
    }
 
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
 
    // ==========================================================
    // Setters
    // ==========================================================
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
 
    // ==========================================================
    // Additional methods
    // ==========================================================
 
    /*
    Adds one Ingredient object to this menu item.
    Called by the main program while parsing the CSV file.
    */
    public void addIngredient(Ingredient ingredient) {
 
        ingredients.add(ingredient);
    }
 
    /*
    Returns the number of ingredients in this menu item.
    Useful for the "most ingredients" challenge algorithm.
    */
    public int getIngredientCount() {
 
        return ingredients.size();
    }
 
    /*
    Adds up the calories of every ingredient in this menu item.
    Loops through the nested ArrayList of Ingredient objects.
    */
    public int getTotalCalories() {
 
        int totalCalories = 0;
 
        for (int i = 0; i < ingredients.size(); i++) {
 
            totalCalories = totalCalories + ingredients.get(i).getCalories();
        }
 
        return totalCalories;
    }
 
    /*
    Returns true if any ingredient in this menu item is an allergen.
    Useful for the allergen search challenge algorithm.
    */
    public boolean hasAllergen() {
 
        for (int i = 0; i < ingredients.size(); i++) {
 
            if (ingredients.get(i).isAllergen()) {
                return true;
            }
        }
 
        return false;
    }
 
    // ==========================================================
    // toString
    // ==========================================================
 
    /*
    Returns a readable line describing the menu item.
    The price is formatted to two decimal places like real money.
    */
    @Override
    public String toString() {
 
        return name
                + " - $" + String.format("%.2f", price)
                + " - " + description
                + " (" + getTotalCalories() + " total cal)";
    }
}