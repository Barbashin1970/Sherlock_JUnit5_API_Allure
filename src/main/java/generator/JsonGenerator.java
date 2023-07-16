package generator;

import pojo.*;

import java.util.ArrayList;
import java.util.List;

public class JsonGenerator {

    public static DetectivesResponse generatePositiveDetectivesResponse() {
        // Create a list of Detective objects
        List<Detective> detectives = new ArrayList<>();

        // Create a list of Category objects for each Detective
        List<Category> categories1 = new ArrayList<>();
        List<Category> categories2 = new ArrayList<>();

        // Create Extra objects for the categories
        Extra extra1 = createExtraObject();
        Extra extra2 = createExtraObject();

        // Create Category objects with the Extra objects
        Category category1 = new Category(1, "extras1", extra1);
        Category category2 = new Category(2, "extras2", null);

        // Add the Category objects to the respective lists
        categories1.add(category1);
        categories2.add(category2);

        // Create Detective objects with the categories
        Detective detective1 = new Detective(1, "Sherlock", "Holmes", true, categories1);
        Detective detective2 = new Detective(3, "John", "Watson", false, categories2);

        // Add the Detective objects to the list of detectives
        detectives.add(detective1);
        detectives.add(detective2);

        // Create the DetectivesResponse object with success set to true

        return new DetectivesResponse(detectives, true);
    }

    public static DetectivesResponse generateNegtiveDetectivesResponse() {
        // Create a list of Detective objects
        List<Detective> detectives = new ArrayList<>();

        // Create a list of Category objects for each Detective
        List<Category> categories1 = new ArrayList<>();
        List<Category> categories2 = new ArrayList<>();

        // Create Extra objects for the categories
        Extra extra1 = createExtraObject();
        Extra extra2 = createExtraObject();

        // Create Category objects with the Extra objects
        Category category1 = new Category(111, "extras1", null);
        Category category2 = new Category(222, "extras2", null);

        // Add the Category objects to the respective lists
        categories1.add(category1);
        categories2.add(category2);

        // Create Detective objects with the categories
        Detective detective1 = new Detective(12, "Sherlockk", "Holmes", true, categories1);
        Detective detective2 = new Detective(11, "John", "Watson", false, categories2);

        // Add the Detective objects to the list of detectives
        detectives.add(detective1);
        detectives.add(detective2);

        // Create the DetectivesResponse object with success set to true

        return new DetectivesResponse(detectives, false);
    }

    public static void main(String[] args) {
        // Generate the DetectivesResponse object
        DetectivesResponse detectivesResponse = generatePositiveDetectivesResponse();

        // Use the detectivesResponse object as needed
        List<Detective> detectives = detectivesResponse.getDetectives();
        boolean success = detectivesResponse.isSuccess();

        System.out.println("Detectives Response:");
        System.out.println("Success: " + success);
        System.out.println("Detectives:");
        for (Detective detective : detectives) {
            System.out.println("Main ID: " + detective.getMainId());
            System.out.println("First Name: " + detective.getFirstName());
            System.out.println("Last Name: " + detective.getLastName());
            System.out.println("Violin Player: " + detective.isViolinPlayer());
            System.out.println("Categories:");
            List<Category> categories = detective.getCategories();
            for (Category category : categories) {
                System.out.println("Category ID: " + category.getCategoryId());
                System.out.println("Category Name: " + category.getCategoryName());
                System.out.println("Extra Items:");
                List<ExtraItem> extraItems = category.getExtra().getExtraArray();
                for (ExtraItem item : extraItems) {
                    System.out.println("Violin: " + item.getViolin() + ", Cap: " + item.getCap());
                }
            }
        }
    }

    public static Extra createExtraObject() {
        List<ExtraItem> extraItems = new ArrayList<>();
        extraItems.add(new ExtraItem(1, 2));
        return new Extra(extraItems);
    }
}
