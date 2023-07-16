package generator;

import pojo.*;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static void printJson(DetectivesResponse detectivesResponse) {
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
                Extra extra = category.getExtra();
                if (extra != null) {
                    List<ExtraItem> extraItems = extra.getExtraArray();
                    for (ExtraItem item : extraItems) {
                        System.out.println("Violin: " + item.getViolin() + ", Cap: " + item.getCap());
                    }
                }
            }
        }
    }


    public static Extra createExtraObject() {
        List<ExtraItem> extraItems = new ArrayList<>();
        extraItems.add(new ExtraItem(1, 2));
        return new Extra(extraItems);
    }

    public static Category createCategory(int category, String categoryName, Extra extra) {
        return new Category(category, categoryName, extra);
    }

}
