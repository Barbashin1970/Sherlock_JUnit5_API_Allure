import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.*;

import java.util.List;

import static generator.Helper.*;
import static org.junit.jupiter.api.Assertions.*;

public class PositiveAssertAllTest {

    @Test
    @DisplayName("Проверка всех условий валидности json в одном тесте")
    public void testPositiveAssertAll() {
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
                true
        );
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
}