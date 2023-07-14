import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import pojo.Detective;
import pojo.DetectivesResponse;
import pojo.Extra;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testPositive() throws IOException {
        String jsonFile = "/Users/olegbarbashin/IdeaProjects/Sherlok/src/main/resources/test.json";
        DetectivesResponse response = deserializeJson(jsonFile);

        // Проверка наличия хотя бы одного детектива
        assertTrue(response.getDetectives().size() >= 1 && response.getDetectives().size() <= 3);

        boolean hasSherlock = false;
        for (Detective detective : response.getDetectives()) {
            if (detective.getFirstName().equals("Sherlock")) {
                hasSherlock = true;
                break;
            }
        }

        // Проверка значения поля success
        assertEquals(hasSherlock, response.isSuccess());
    }

    @Test
    public void testNegative() throws IOException {
        String jsonFile = "/Users/olegbarbashin/IdeaProjects/Sherlok/src/main/resources/test.json";
        DetectivesResponse response = deserializeJson(jsonFile);

        // Проверка наличия детективов с недопустимыми значениями полей
        for (Detective detective : response.getDetectives()) {
            int mainId = detective.getMainId();
            int categoryId = detective.getCategories().get(0).getCategoryID();
            Extra extra = detective.getCategories().get(0).getExtra();

            // Проверка значения поля MainId
            assertTrue(mainId >= 0 && mainId <= 10);

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

    private DetectivesResponse deserializeJson(String jsonFile) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(jsonFile), DetectivesResponse.class);
    }
}
