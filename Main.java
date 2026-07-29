/*
======================================================
Group Members:


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

            File file = new File("restaurants.csv");

            Scanner input = new Scanner(file);

            // Skip header row
            input.nextLine();

            while (input.hasNextLine()) {

                String line = input.nextLine();

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

        /*
        ======================================================
        GROUP WORK SECTION
        ======================================================

        Complete the following algorithms together:

        1. Average menu item price for each restaurant
        2. Highest-priced menu item for each restaurant
        3. Lowest-priced menu item for each restaurant
        4. Average calories for each restaurant

        Each algorithm must be written in
        its own method.

        */
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
}
