/*
Restaurant.java
 
Created by Eduardo Cerna
 
Represents a single restaurant.
Each Restaurant contains many MenuItem objects stored in an ArrayList.
*/
 
import java.util.ArrayList;
 
public class Restaurant {
 
    // ==========================================================
    // Instance variables
    // ==========================================================
 
    private String name;                     // name of the restaurant
    private double rating;                    // customer rating (example: 4.5)
    private ArrayList<MenuItem> menuItems;    // every item on the menu
 
    // ==========================================================
    // Constructors
    // ==========================================================
 
    /*
    Default constructor.
    The ArrayList is created here so menu items can be added later.
    */
    public Restaurant() {
 
        this.name = "Unknown";
        this.rating = 0.0;
        this.menuItems = new ArrayList<MenuItem>();
    }
 
    /*
    Main constructor used by the program.
    The menu starts empty and is filled by addMenuItem().
    */
    public Restaurant(String name, double rating) {
 
        this.name = name;
        this.rating = rating;
        this.menuItems = new ArrayList<MenuItem>();
    }
 
    /*
    Overloaded constructor that accepts an existing menu.
    */
    public Restaurant(String name, double rating,
                      ArrayList<MenuItem> menuItems) {
 
        this.name = name;
        this.rating = rating;
        this.menuItems = menuItems;
    }
 
    // ==========================================================
    // Getters
    // ==========================================================
 
    public String getName() {
        return name;
    }
 
    public double getRating() {
        return rating;
    }
 
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }
 
    // ==========================================================
    // Setters
    // ==========================================================
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setRating(double rating) {
        this.rating = rating;
    }
 
    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
 
    // ==========================================================
    // Additional methods
    // ==========================================================
 
    /*
    Adds one MenuItem object to this restaurant's menu.
    Called by the main program while reading the CSV file.
    */
    public void addMenuItem(MenuItem menuItem) {
 
        menuItems.add(menuItem);
    }
 
    /*
    Returns how many items are on this restaurant's menu.
    */
    public int getMenuSize() {
 
        return menuItems.size();
    }
 
    // ==========================================================
    // toString
    // ==========================================================
 
    /*
    Returns a readable line describing the restaurant.
    */
    @Override
    public String toString() {
 
        return name
                + "  |  Rating: " + rating
                + "  |  Menu Items: " + menuItems.size();
    }
}
