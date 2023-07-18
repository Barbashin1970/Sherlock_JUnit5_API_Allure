import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.Category;
import pojo.Detective;
import pojo.DetectivesResponse;
import pojo.Extra;

import java.util.List;

import static generator.Helper.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterTest {

    @Test
    @DisplayName("Позитивный тест - Количество объектов Detective должно быть от 1 до 3")
    public void testPositiveNumberOfDetectives() {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                1,
                2,
                "one",
                "two",
                1,
                2,
                1,
                2,
                "Sherlock",
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);
        int size = detectivesResponse.getDetectives().size();
        printJson(detectivesResponse);
        assertTrue(size >= 1 && size <= 3, "Ошибка: Количество объектов Detective должно быть от 1 до 3");
    }

    @ParameterizedTest
    @DisplayName("Позитивный тест - значение поля mainId должно быть от 0 до 10")
    @ValueSource(ints = {1, 5, 10})

    public void testPositiveMainIdRange(int mainId1) {

        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                1,
                2,
                "one",
                "two",
                1,
                2,
                mainId1,
                10,
                "Sherlock",
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);

        for (Detective detective : detectivesResponse.getDetectives()) {
            int mainId = detective.getMainId();
            assertTrue(mainId >= 0 && mainId <= 10,
                    "Ошибка: Значение поля mainId должно быть от 0 до 10");
        }
    }

    @ParameterizedTest
    @DisplayName("Позитивный тест - Значение поля categoryId должно быть 1 или 2")
    @ValueSource(ints = {1, 2})
    public void testPositiveCategoryIdValues(int cat1) {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                cat1,
                2,
                "one",
                "two",
                1,
                2,
                1,
                10,
                "Sherlock",
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);

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
    public void testPositiveExtraValueForCategoryId2() {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                1,
                2,
                "one",
                "two",
                1,
                2,
                1,
                10,
                "Sherlock",
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);

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
    public void testPositiveMinimumExtraArrayElementsForCategoryId1() {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                1,
                2,
                "one",
                "two",
                1,
                2,
                1,
                10,
                "Sherlock",
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);

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

    @ParameterizedTest
    @DisplayName("Позитивный тест - только для firstName=Sherlock поле success = true")
    @ValueSource(strings = {"Mister", "Twister", "Former", "Minister"})
    public void testPositiveSuccessValueWithSherlock(String firstName2) {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                1,
                2,
                "one",
                "two",
                1,
                2,
                1,
                10,
                "Sherlock",
                firstName2,
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);

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
    public void testNegativeOverfullNumberOfDetectives() {
        DetectivesResponse detectivesResponse = new DetectivesResponse();
        detectivesResponse.setDetectives(List.of(
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject(1, 2)))),
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject(0, 0)))),
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", null))),
                new Detective(1, "Sherlock", "Holmes", true, List.of(createCategory(1, "name", createExtraObject(1, 2))))
        ));
        int size = detectivesResponse.getDetectives().size();
        assertFalse(size >= 1 && size <= 3, "Ошибка: Количество объектов Detective должно быть от 1 до 3");
    }

    @ParameterizedTest
    @DisplayName("Негативный тест - Значение поля mainId должно быть от 0 до 10")
    @ValueSource(ints = {11, 33, 100, -1, -100})
    public void testNegativeInvalidMainIdRange(int mainId1) {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                1,
                2,
                "one",
                "two",
                1,
                2,
                mainId1,
                2,
                "Sherlock",
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);
        boolean mainIdOk = false;

        for (Detective detective : detectivesResponse.getDetectives()) {
            int mainId = detective.getMainId();
            if (mainId >= 0 && mainId <= 10) {
                mainIdOk = true;
                break;
            }
        }
        assertTrue(mainIdOk, "Ошибка: Значение поля mainId должно быть от 0 до 10");

    }


    @ParameterizedTest
    @DisplayName("Негативный тест - проверка допустимых значений поля categoryId - должно быть 1 или 2")
    @ValueSource(ints = {0, 3, 100, -1, -100})
    public void testNegativeInvalidCategoryId(int cat) {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                cat,
                cat,
                "one",
                "two",
                1,
                2,
                1,
                10,
                "Sherl",
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);
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
    public void testNegativeInvalidExtraValueForCategoryId2() {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                2,
                1,
                "one",
                "two",
                1,
                2,
                5,
                2,
                "Sherlock",
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);

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
    public void testNegativeMinimumExtraArrayElementsForCategory() {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                2,
                1,
                "one",
                "two",
                1,
                2,
                1,
                10,
                "Sherlock",
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);
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


    @ParameterizedTest
    @DisplayName("Негативный тест - только для firstName=Sherlock поле success = true")
    @ValueSource(strings = {"Sherlok", "Sherlock ", " Sherlock", "Sher lock"})

    public void testNegativeSuccessValueWithSherlockFalse(String name) {
        DetectivesResponse detectivesResponse = createTwoDetectiveResponse(
                1,
                2,
                "one",
                "two",
                1,
                2,
                1,
                10,
                name,
                "Tom",
                "Holmes",
                "Cat",
                true,
                true,
                false,
                true);

        boolean success = detectivesResponse.isSuccess();
        boolean hasSherlock = false;

        for (Detective detective : detectivesResponse.getDetectives()) {
            if (detective.getFirstName().equals("Sherlock")) {
                hasSherlock = true;
                break;
            }
        }

        if (hasSherlock) {
            assertFalse(success, "Ошибка: success должен быть true когда есть детектив с firstName=Sherlock");
        }
    }

}
