package common;

import org.junit.Assert;
import org.junit.Test;

/**
 * represents the test class for FileHelper.
 * @author Chang Zhou
 */
public class FileHelperTest {

  @Test
  public void readJsonFile() {
    Assert.assertEquals("{"
        + "  \"desiredWidth\": 355,"
        + "  \"desiredHeight\": 36,"
        + "  \"desiredShelves\": 3,"
        + "  \"desiredDrawers\": 0,"
        + "  \"desiredColor\": null,"
        + "  \"desiredMountType\": \"FLOOR\","
        + "  \"budget\": 2999"
        + "}", FileHelper.readJsonFile("src/main/resources/customer_criteria.json"));
  }

  @Test
  public void writeJsonFile() {
    FileHelper.writeJsonFile("src/main/resources/testWrite.txt", "1234");
    Assert.assertEquals("1234",
        FileHelper.readJsonFile("src/main/resources/testWrite.txt"));
  }
}