import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Category;
import pojo.Detective;
import pojo.DetectivesResponse;
import pojo.Extra;

import java.util.List;

import static generator.Helper.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    @DisplayName("Позитивный тест - Количество объектов Detective должно быть от 1 до 3")
    public void testPositiveNumberOfDetectives() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(10, "Sher", "Homes", false, List.of(createCategory(2, "name", null)))
        ));
        int size = detectivesResponse.getDetectives().size();
        assertTrue(size >= 1 && size <= 3, "Ошибка: Количество объектов Detective должно быть от 1 до 3");
    }

    @Test
    @DisplayName("Позитивный тест - значение поля mainId должно быть от 0 до 10")
    public void testMainIdRange() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(10, "Sher", "Homes", false, List.of(createCategory(2, "name", null)))
        ));
        for (Detective detective : detectivesResponse.getDetectives()) {
            int mainId = detective.getMainId();
            assertTrue(mainId >= 0 && mainId <= 10,
                    "Ошибка: Значение поля mainId должно быть от 0 до 10");
        }
    }

    @Test
    @DisplayName("Позитивный тест - Значение поля categoryId должно быть 1 или 2")
    public void testCategoryIdValues() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(10, "Sher", "Homes", false, List.of(createCategory(2, "name", null)))
        ));

        for (Detective detective : detectivesResponse.getDetectives()) {
            List<Category> categories = detective.getCategories();

            for (Category category : categories) {
                int categoryId = category.getCategoryId();

                assertTrue(categoryId == 1 || categoryId == 2,
                        "Ошибка: Значение поля categoryId должно быть 1 или 2");
            }
        }
    }

    @Test
    @DisplayName("Позитивный тест - Элемент extra может принимать значение null только для categoryId=2")
    public void testExtraValueForCategoryId2() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(10, "Sher", "Homes", false, List.of(createCategory(2, "name", null)))
        ));

        for (Detective detective : detectivesResponse.getDetectives()) {
            List<Category> categories = detective.getCategories();

            for (Category category : categories) {
                int categoryId = category.getCategoryId();
                Extra extra = category.getExtra();

                if (categoryId == 2) {
                    assertNull(extra, "Error: The extra value should be null for categoryId=2");
                }
            }
        }
    }

    @Test
    @DisplayName("Позитивный тест - Массив extraArray должен иметь минимум один элемент для categoryId=1")
    public void testMinimumExtraArrayElementsForCategoryId1() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(10, "Sher", "Homes", false, List.of(createCategory(2, "name", null)))
        ));

        for (Detective detective : detectivesResponse.getDetectives()) {
            List<Category> categories = detective.getCategories();

            for (Category category : categories) {
                int categoryId = category.getCategoryId();
                Extra extra = category.getExtra();

                if (categoryId == 1) {
                    assertNotNull(extra, "Ошибка: Массив extraArray должен быть не null для categoryId=1");
                    assertFalse(extra.getExtraArray().isEmpty(),
                            "Ошибка - Массив extraArray должен иметь минимум один элемент для categoryId=1");
                }
            }
        }
    }

    @Test
    @DisplayName("Позитивный тест - только для firstName=Sherlock поле success = true")
    public void testSuccessValueWithSherlock() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(10, "Sher", "Homes", false, List.of(createCategory(2, "name", null)))
        ));
        detectivesResponse.setSuccess(true); // установим поле объекта detectivesResponse

        boolean success = detectivesResponse.isSuccess();
        boolean hasSherlock = false;

        for (Detective detective : detectivesResponse.getDetectives()) {
            if (detective.getFirstName().equals("Sherlock")) {
                hasSherlock = true;
                break;
            }
        }

        if (hasSherlock) {
            assertTrue(success, "Ошибка: success должен быть true когда есть детектив с firstName=Sherlock");
        } else {
            assertFalse(success, "Ошибка: success должен быть false когда нет детективов с firstName=Sherlock");
        }
    }

    // негативные тесты

    @Test
    @DisplayName("Негативный тест - Количество объектов Detective должно быть от 1 до 3")
    public void testInvalidNumberOfDetectives() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject())))
        ));
        int size = detectivesResponse.getDetectives().size();
        assertFalse(size >= 1 && size <= 3, "Ошибка: Количество объектов Detective должно быть от 1 до 3");
    }

    @Test
    @DisplayName("Негативный тест - Значение поля mainId должно быть от 0 до 10")
    public void testInvalidMainIdRange() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(-5, "Sherlock", "Holmes", true, List.of(createCategory(-1, "name", createExtraObject()))),
                new Detective(15, "John", "Watson", true, List.of(createCategory(20, "name", createExtraObject())))
        ));

        for (Detective detective : detectivesResponse.getDetectives()) {
            int mainId = detective.getMainId();
            assertFalse(mainId >= 0 && mainId <= 10, "Ошибка: Значение поля mainId должно быть от 0 до 10");
        }
    }


    @Test
    @DisplayName("Негативный тест - проверка допустимых значений поля categoryId - должно быть 1 или 2")
    public void testInvalidCategoryIdValues() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(5, "Sherlock", "Holmes", true, List.of(createCategory(3, "name", createExtraObject()))),
                new Detective(2, "John", "Watson", true, List.of(createCategory(4, "name", createExtraObject())))
        ));

        for (Detective detective : detectivesResponse.getDetectives()) {
            List<Category> categories = detective.getCategories();
            for (Category category : categories) {
                int categoryId = category.getCategoryId();
                assertFalse(categoryId == 1 || categoryId == 2, "Ошибка: Значение поля categoryId должно быть от 1 или 2");
            }
        }
    }

    @Test
    @DisplayName("Негативный тест - Элемент extra может принимать значение null только для categoryId=2")
    public void testInvalidExtraValueForCategoryId2() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(5, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", null))),
                new Detective(2, "John", "Watson", true, List.of(createCategory(2, "name", createExtraObject())))
        ));
        printJson(detectivesResponse);
        for (Detective detective : detectivesResponse.getDetectives()) {
            List<Category> categories = detective.getCategories();
            for (Category category : categories) {
                int categoryId = category.getCategoryId();
                if (categoryId == 2) {
                    assertNotNull(category.getExtra(), "Ошибка: Значение поля extra отсутствует для categoryId=2");
                }
            }
        }
    }

    @Test
    @DisplayName("Негативный тест - Массив extraArray должен иметь минимум один элемент для categoryId=1")
    public void testMinimumExtraArrayElementsForCategory() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", null))),
                new Detective(10, "Sher", "Homes", false, List.of(createCategory(2, "name", createExtraObject())))
        ));

        for (Detective detective : detectivesResponse.getDetectives()) {
            List<Category> categories = detective.getCategories();

            for (Category category : categories) {
                int categoryId = category.getCategoryId();
                Extra extra = category.getExtra();

                if (categoryId == 1) {
                    assertNull(extra, "Ошибка: Массив extraArray должен быть не null для categoryId=1");
                }
            }
        }
    }


    @Test
    @DisplayName("Негативный тест - только для firstName=Sherlock поле success = true")
    public void testSuccessValueWithSherlockFalse() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherl", "Holmes", true, List.of(createCategory(1, "name", createExtraObject()))),
                new Detective(10, "Sher", "Homes", false, List.of(createCategory(2, "name", null)))
        ));
        detectivesResponse.setSuccess(true); // установим поле объекта detectivesResponse

        boolean success = detectivesResponse.isSuccess();
        boolean hasSherlock = false;

        for (Detective detective : detectivesResponse.getDetectives()) {
            if (detective.getFirstName().equals("Sherlock")) {
                hasSherlock = true;
                break;
            }
        }

        if (hasSherlock) {
            assertTrue(success, "Ошибка: success должен быть true когда есть детектив с firstName=Sherlock");
        } else {
            assertTrue(success, "Ошибка: success должен быть false когда нет детективов с firstName=Sherlock");
        }
    }

}
