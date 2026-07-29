/*
======================================================
Group Members: Eduardo, Mitchelle, Steven


======================================================
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Restaurant> restaurants =
                new ArrayList<Restaurant>();

        try {

            File file = new File("Restaurants.csv");

            Scanner input = new Scanner(file);

            // Skip header row
            input.nextLine();

            while (input.hasNextLine()) {

                String line = input.nextLine();
                // Skip blank lines between restaurant groups
                if (line.trim().isEmpty()) {
                continue;
                }

                // Split CSV row
                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                String restaurantName = parts[0];

                double rating =
                        Double.parseDouble(parts[1]);

                String menuItemName = parts[2];

                String description = parts[3];

                double price =
                        Double.parseDouble(parts[4]);

                String ingredientsText = parts[5];

                // Remove quotation marks
                ingredientsText =
                        ingredientsText.replace("\"", "");

                /*
                ==============================================
                Find or create Restaurant object
                ==============================================
                */

                Restaurant restaurant =
                        findRestaurant(restaurants,
                                restaurantName);

                if (restaurant == null) {

                    restaurant =
                            new Restaurant(
                                    restaurantName,
                                    rating);

                    restaurants.add(restaurant);
                }

                /*
                ==============================================
                Create MenuItem object
                ==============================================
                */

                MenuItem menuItem =
                        new MenuItem(
                                menuItemName,
                                description,
                                price);

                /*
                ==============================================
                Create Ingredient objects
                ==============================================
                */

                String[] ingredientParts =
                        ingredientsText.split(";");

                for (int i = 0;
                     i < ingredientParts.length;
                     i++) {

                    String ingredientData =
                            ingredientParts[i];

                    String[] ingredientInfo =
                            ingredientData.split(":");

                    String ingredientName =
                            ingredientInfo[0];

                    int calories =
                            Integer.parseInt(
                                    ingredientInfo[1]);

                    boolean allergen =
                            Boolean.parseBoolean(
                                    ingredientInfo[2]);

                    Ingredient ingredient =
                            new Ingredient(
                                    ingredientName,
                                    calories,
                                    allergen);

                    menuItem.addIngredient(
                            ingredient);
                }

                /*
                ==============================================
                Add MenuItem to Restaurant
                ==============================================
                */

                restaurant.addMenuItem(menuItem);
            }

            input.close();

        } catch (FileNotFoundException e) {

            System.out.println(
                    "File not found.");
        }

        /*
        ======================================================
        TEST OUTPUT
        ======================================================
        */

        for (int i = 0;
             i < restaurants.size();
             i++) {

            Restaurant restaurant =
                    restaurants.get(i);

            System.out.println(
                    "================================");

            System.out.println(
                    restaurant);

            ArrayList<MenuItem> menuItems =
                    restaurant.getMenuItems();

            for (int j = 0;
                 j < menuItems.size();
                 j++) {

                MenuItem item =
                        menuItems.get(j);

                System.out.println(item);

                ArrayList<Ingredient> ingredients =
                        item.getIngredients();

                for (int k = 0;
                     k < ingredients.size();
                     k++) {

                    System.out.println(
                            "   " +
                            ingredients.get(k));
                }
            }
        }

        averageMenuPrice(restaurants);
        highestPricedItem(restaurants);
        lowestPricedItem(restaurants);
        averageCalories(restaurants);
    }

    /*
    ======================================================
    Find Restaurant Method
    ======================================================
    */

    public static Restaurant findRestaurant(
            ArrayList<Restaurant> restaurants,
            String name) {

        for (int i = 0;
             i < restaurants.size();
             i++) {

            if (restaurants.get(i)
                    .getName()
                    .equals(name)) {

                return restaurants.get(i);
            }
        }

        return null;
    }
    /*
    ==================================================
    ALGORITHM 1 - Completed by the group
    Average menu item price for each restaurant
    ==================================================
    */
    public static void averageMenuPrice(ArrayList<Restaurant> restaurants) {

        System.out.println();
        System.out.println("=== AVERAGE MENU ITEM PRICE ===");

        for (int i = 0; i < restaurants.size(); i++) {

            Restaurant restaurant = restaurants.get(i);
            ArrayList<MenuItem> menuItems = restaurant.getMenuItems();

            if (menuItems.size() == 0) {
                continue;
            }

            double totalPrice = 0.0;

            for (int j = 0; j < menuItems.size(); j++) {
                totalPrice = totalPrice + menuItems.get(j).getPrice();
            }

            double averagePrice = totalPrice / menuItems.size();

            System.out.println(restaurant.getName() + ": $"
                    + String.format("%.2f", averagePrice));
        }
    }

    /*
    ==================================================
    ALGORITHM 2 - Completed by the group
    Highest-priced menu item for each restaurant
    ==================================================
    */
    public static void highestPricedItem(ArrayList<Restaurant> restaurants) {

        System.out.println();
        System.out.println("=== HIGHEST-PRICED MENU ITEM ===");

        for (int i = 0; i < restaurants.size(); i++) {

            Restaurant restaurant = restaurants.get(i);
            ArrayList<MenuItem> menuItems = restaurant.getMenuItems();

            if (menuItems.size() == 0) {
                continue;
            }

            MenuItem highestItem = menuItems.get(0);

            for (int j = 1; j < menuItems.size(); j++) {

                if (menuItems.get(j).getPrice() > highestItem.getPrice()) {
                    highestItem = menuItems.get(j);
                }
            }

            System.out.println(restaurant.getName() + ": "
                    + highestItem.getName() + " - $"
                    + String.format("%.2f", highestItem.getPrice()));
        }
    }

    /*
    ==================================================
    ALGORITHM 3 - Completed by the group
    Lowest-priced menu item for each restaurant
    ==================================================
    */
    public static void lowestPricedItem(ArrayList<Restaurant> restaurants) {

        System.out.println();
        System.out.println("=== LOWEST-PRICED MENU ITEM ===");

        for (int i = 0; i < restaurants.size(); i++) {

            Restaurant restaurant = restaurants.get(i);
            ArrayList<MenuItem> menuItems = restaurant.getMenuItems();

            if (menuItems.size() == 0) {
                continue;
            }

            MenuItem lowestItem = menuItems.get(0);

            for (int j = 1; j < menuItems.size(); j++) {

                if (menuItems.get(j).getPrice() < lowestItem.getPrice()) {
                    lowestItem = menuItems.get(j);
                }
            }

            System.out.println(restaurant.getName() + ": "
                    + lowestItem.getName() + " - $"
                    + String.format("%.2f", lowestItem.getPrice()));
        }
    }

    /*
    ==================================================
    ALGORITHM 4 (CHALLENGE) - Completed by the group
    Average calories of menu items for each restaurant
    ==================================================
    */
    public static void averageCalories(ArrayList<Restaurant> restaurants) {

        System.out.println();
        System.out.println("=== AVERAGE CALORIES PER MENU ITEM ===");

        for (int i = 0; i < restaurants.size(); i++) {

            Restaurant restaurant = restaurants.get(i);
            ArrayList<MenuItem> menuItems = restaurant.getMenuItems();

            if (menuItems.size() == 0) {
                continue;
            }

            int totalCalories = 0;

            for (int j = 0; j < menuItems.size(); j++) {
                totalCalories = totalCalories + menuItems.get(j).getTotalCalories();
            }

            double average = (double) totalCalories / menuItems.size();

            System.out.println(restaurant.getName() + ": "
                    + String.format("%.1f", average) + " calories");
        }
    }
}
