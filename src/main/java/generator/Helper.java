package generator;

import pojo.*;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static void printJson(DetectivesResponse detectivesResponse) {
        List<Detective> detectives = detectivesResponse.getDetectives();
        boolean success = detectivesResponse.isSuccess();
        System.out.println();
        System.out.println("         Тестовые данные для файла json:              ");
        System.out.println("======================================================");
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
        System.out.println("======================================================");
    }


    public static Extra createExtraObject(int violin, int cap) {
        List<ExtraItem> extraItems = new ArrayList<>();
        extraItems.add(new ExtraItem(violin, cap));
        return new Extra(extraItems);
    }

    public static Category createCategory(int category, String categoryName, Extra extra) {
        return new Category(category, categoryName, extra);
    }

    public static Detective createDetective(int mainId, String firstName, String lastName, boolean violinPlayer, List<Category> categories) {
        return new Detective(mainId, firstName, lastName, violinPlayer, categories);

    }

    public static DetectivesResponse createDetectiveResponse(List<Detective> detectives, boolean success) {
        return new DetectivesResponse(detectives, success);
    }

    public static DetectivesResponse createTwoDetectiveResponse(int cat1, int cat2, String catName1, String catName2, int violin, int cap, int mainId1, int mainId2, String firstName1, String firstName2, String lastName1, String lastName2, boolean extra2IsNull, boolean violinPlayer1, boolean violinPlayer2, boolean success) {

        Extra extra = createExtraObject(violin, cap);
        Category category1 = createCategory(cat1, catName1, extra);
        Category category2 = createCategory(cat2, catName2, extra);
        if (extra2IsNull) {
            category2 = createCategory(cat2, catName2, null);
        }
        Detective detective1 = createDetective(mainId1, firstName1, lastName1, violinPlayer1, List.of(category1, category2));
        Detective detective2 = createDetective(mainId2, firstName2, lastName2, violinPlayer2, List.of(category1));
        return createDetectiveResponse(List.of(detective1, detective2), success);
    }
}

