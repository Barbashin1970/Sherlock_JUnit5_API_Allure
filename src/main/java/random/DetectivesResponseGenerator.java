package random;

import com.google.gson.Gson;
import pojo.*;

import java.util.ArrayList;
import java.util.List;

public class DetectivesResponseGenerator {
    public static DetectivesResponse generatePositiveDetectivesResponse() {
        DetectivesResponse response = new DetectivesResponse();
        response.setSuccess(true);

        // Генерация детективов
        List<Detective> detectives = new ArrayList<>();
        detectives.add(generateDetective("Sherlock", "Homes", true, 1, "extras1"));
        detectives.add(generateDetective("James", "Watson", false, 2, "extras2"));
        response.setDetectives(detectives);

        return response;
    }

    public static DetectivesResponse generateNegativeDetectivesResponse() {
        DetectivesResponse response = new DetectivesResponse();
        response.setSuccess(false);

        // Генерация детективов
        List<Detective> detectives = new ArrayList<>();
        detectives.add(generateDetective("John", "Doe", true, 3, "extras3"));
        response.setDetectives(detectives);

        return response;
    }

    private static Detective generateDetective(String firstName, String lastName, boolean violinPlayer, int categoryId, String categoryName) {
        Detective detective = new Detective();
        detective.setFirstName(firstName);
        detective.setLastName(lastName);
        detective.setViolinPlayer(violinPlayer);

        // Генерация категории
        Category category = new Category();
        category.setCategoryID(categoryId);
        category.setCategoryName(categoryName);

        // Генерация дополнительной информации
        Extra extra = new Extra();
        if (categoryId == 1) {
            List<ExtraItem> extraArray = new ArrayList<>();
            extraArray.add(new ExtraItem(1, 1));
            extra.setExtraArray(extraArray);
        } else if (categoryId == 2) {
            extra.setExtraArray(null);
        }


        category.setExtra(extra);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        detective.setCategories(categories);

        return detective;
    }

    public static void main(String[] args) {
        // Пример использования генератора
        DetectivesResponse positiveResponse = generatePositiveDetectivesResponse();
        DetectivesResponse negativeResponse = generateNegativeDetectivesResponse();

        Gson gson = new Gson();
        String positiveJson = gson.toJson(positiveResponse);
        String negativeJson = gson.toJson(negativeResponse);

        System.out.println("Позитивный DetectivesResponse:");
        System.out.println(positiveJson);
        System.out.println();
        System.out.println("Негативный DetectivesResponse:");
        System.out.println(negativeJson);
    }
}
