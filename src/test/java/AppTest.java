import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Category;
import pojo.Detective;
import pojo.DetectivesResponse;
import pojo.Extra;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static random.TestObjectCreation.generateNegtiveDetectivesResponse;
import static random.TestObjectCreation.generatePositiveDetectivesResponse;

public class AppTest {

    @Test
    @DisplayName("Проверка позитивного сценария")
    public void testPositive() throws IOException {

        DetectivesResponse detectivesResponse = generatePositiveDetectivesResponse();

        // Проверка наличия хотя бы одного детектива
        int size = detectivesResponse.getDetectives().size();
        assertTrue(size >= 1 && size <= 3, "Вложено 0 или более 3 детективов");

        boolean hasSherlock = false;
        for (Detective detective : detectivesResponse.getDetectives()) {
            if (detective.getFirstName().equals("Sherlock")) {
                hasSherlock = true;
                break;
            }
        }

        // Проверка значения поля success
        assertEquals(hasSherlock, detectivesResponse.isSuccess(), "поле success = false - видимо это не Sherlock");
    }

    @Test
    @DisplayName("Детектив с именем 'Sherlock' существует")
    public void positiveTest_SuccessIsTrue() {
        DetectivesResponse detectivesResponse = generatePositiveDetectivesResponse();
        assertTrue(detectivesResponse.isSuccess());
    }

    @Test
    @DisplayName("Negative Test - Success is false when detective with firstName 'Sherlock' doesn't exist")
    public void negativeTest_SuccessIsFalse() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();
        assertFalse(detectivesResponse.isSuccess());
    }

    @Test
    @DisplayName("Negative Test - Detectives list should have at least one detective")
    public void negativeTest_DetectivesListNotEmpty() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();
        assertFalse(detectivesResponse.getDetectives().isEmpty());
    }

    @Test
    @DisplayName("Negative Test - Detectives list should not exceed three detectives")
    public void negativeTest_DetectivesListNotExceedThree() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();
        assertTrue(detectivesResponse.getDetectives().size() <= 3);
    }

    @Test
    @DisplayName("Negative Test - CategoryID should be 1 or 2")
    public void negativeTest_CategoryIDValidValues() {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();
        for (Detective detective : detectivesResponse.getDetectives()) {
            for (Category category : detective.getCategories()) {
                int categoryId = category.getCategoryId();
                assertTrue(categoryId == 1 || categoryId == 2);
            }
        }
    }

    @Test
    @DisplayName("Negative Test - extra field should be null for CategoryID=2")
    public void negativeTest_ExtraFieldNullForCategoryID2() {
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
    @DisplayName("Negative Test - extraArray should have at least one element for CategoryID=1")
    public void negativeTest_ExtraArrayHasAtLeastOneElementForCategoryID1() {
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
    @DisplayName("Проверка негативного сценария")
    public void testNegative() throws IOException {
        DetectivesResponse detectivesResponse = generateNegtiveDetectivesResponse();

        // Проверка наличия детективов с недопустимыми значениями полей
        for (Detective detective : detectivesResponse.getDetectives()) {
            int mainId = detective.getMainId();
            int categoryId = detective.getCategories().get(0).getCategoryId();
            Extra extra = detective.getCategories().get(0).getExtra();

            // Проверка значения поля MainId
            // assertFalse(mainId >= 0 && mainId <= 10);

            // Проверка значения поля CategoryID
            assertTrue(categoryId == 1 || categoryId == 2);

            // Проверка значения поля extra
            if (categoryId == 2) {
                assertNull(extra);
            } else {
                assertNotNull(extra);
                assertFalse(extra.getExtraArray().isEmpty());
            }
        }
    }
}
