import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Category;
import pojo.Detective;
import pojo.DetectivesResponse;
import pojo.Extra;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static random.TestObjectCreation.generateNegtiveDetectivesResponse;
import static random.TestObjectCreation.generatePositiveDetectivesResponse;

public class AppTest {


    @Test
    @DisplayName("Проверка позитивного сценария")
    public void testPositive() {
        DetectivesResponse detectivesResponse = generatePositiveDetectivesResponse();

        assertAll("Detectives",
                () -> {
                    List<Detective> detectives = detectivesResponse.getDetectives();

                    // Массив detectives может иметь не менее одного и не более 3-х объектов
                    assertTrue(detectives.size() >= 1 && detectives.size() <= 3,
                            "Ошибка: Массив detectives должен содержать от 1 до 3 объектов");

                    for (Detective detective : detectives) {
                        int mainId = detective.getMainId();
                        List<Category> categories = detective.getCategories();

                        for (Category category : categories) {
                            int categoryId = category.getCategoryId();
                            Extra extra = category.getExtra();

                            assertAll("Category",
                                    () -> {
                                        // Поле MainId должно быть между 0 и 10
                                        assertTrue(mainId >= 0 && mainId <= 10,
                                                "Ошибка: Поле MainId должно быть между 0 и 10");

                                        // CategoryID принимает значения 1 или 2
                                        assertTrue(categoryId == 1 || categoryId == 2,
                                                "Ошибка: Поле CategoryID должно быть 1 или 2");

                                        if (categoryId == 2) {
                                            // Элемент extra может принимать значение null только для CategoryID=2
                                            assertNull(extra,
                                                    "Ошибка: Элемент extra должен быть null для CategoryID=2");
                                        } else {
                                            // Массив extraArray должен иметь минимум один элемент для CategoryID=1
                                            assertNotNull(extra,
                                                    "Ошибка: Элемент extra не должен быть null для CategoryID=1");
                                            assertFalse(extra.getExtraArray().isEmpty(),
                                                    "Ошибка: Массив extraArray должен содержать минимум один элемент для CategoryID=1");
                                        }
                                    }
                            );
                        }
                    }

                    // Поле success принимает значение true только если в массиве detectives есть элемент с firstName ="Sherlock"
                    boolean hasSherlock = detectives.stream()
                            .anyMatch(detective -> detective.getFirstName().equals("Sherlock"));
                    assertEquals(hasSherlock, detectivesResponse.isSuccess(),
                            "Ошибка: Поле success должно быть true только если в массиве detectives есть элемент с firstName = Sherlock");
                }
        );
    }


    @Test
    @DisplayName("Негативный тест: Детектив с именем 'Sherlock' не существует")
    public void testNegative_SuccessIsFalse() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();
        assertFalse(detectivesResponse.isSuccess());
    }

    @Test
    @DisplayName("Негативный тест: Массив detectives может иметь не менее одного и не более 3-х объектов")
    public void testNegative_DetectivesListNotEmpty() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();
        assertFalse(detectivesResponse.getDetectives().isEmpty());
    }

    @Test
    @DisplayName("Негативный тест:  Detectives list should not exceed three detectives")
    public void testNegative_DetectivesListNotExceedThree() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();
        assertTrue(detectivesResponse.getDetectives().size() <= 3);
    }


    @Test
    @DisplayName("Негативный тест: Элемент extra может принимать значение null только для CategoryID=2")
    public void testNegative_ExtraFieldNullForCategoryID2() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();
        for (Detective detective : detectivesResponse.getDetectives()) {
            for (Category category : detective.getCategories()) {
                if (category.getCategoryId() == 2) {
                    assertNull(category.getExtra());
                }
            }
        }
    }

    @Test
    @DisplayName("Негативный тест: extraArray должен иметь хотя бы один элемент для CategoryID=1")
    public void testNegative_ExtraArrayHasAtLeastOneElementForCategoryOne() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();
        for (Detective detective : detectivesResponse.getDetectives()) {
            for (Category category : detective.getCategories()) {
                if (category.getCategoryId() == 1) {
                    assertFalse(category.getExtra().getExtraArray().isEmpty());
                }
            }
        }
    }


    @Test
    @DisplayName("Негативный тест: CategoryID принимает значения 1 или 2")
    public void testNegative_Category() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();

        assertAll("CategoryId",
                () -> {
                    for (Detective detective : detectivesResponse.getDetectives()) {
                        for (Category category : detective.getCategories()) {
                            int categoryId = category.getCategoryId();
                            assertFalse(categoryId == 1 || categoryId == 2);
                        }
                    }
                }
        );
    }

}
