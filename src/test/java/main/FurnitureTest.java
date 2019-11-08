package main;

import enums.ColorType;
import enums.CoverType;
import enums.FurnitureType;
import enums.HandleType;
import enums.MountType;
import enums.RailType;
import java.util.Arrays;
import java.util.HashSet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * test class for Furniture.
 * @author Chang Zhou
 */
public class FurnitureTest {
  private Furniture furniture;

  @Before
  public void setUp() throws Exception {
    final JSONParser jsonParser = new JSONParser();
    final JSONObject jsonObject = (JSONObject) jsonParser.parse(" {"
        + "    \"name\": \"LuthienHalf\","
        + "    \"price\": 40,"
        + "    \"furnitureType\": \"CABINET\","
        + "    \"width\": 36,"
        + "    \"height\": 36,"
        + "    \"depth\": 18,"
        + "    \"isWallFixtureNeeded\": false,"
        + "    \"availableColors\": \"BLACK, BONE\","
        + "    \"availableMountTypes\": \"FLOOR, WALL\""
        + "  }");
    furniture = new Furniture(jsonObject);
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(furniture, furniture);
  }

  @Test
  public void testEquals1() throws ParseException {
    final JSONParser jsonParser = new JSONParser();
    final JSONObject jsonObject = (JSONObject) jsonParser.parse(" {"
        + "    \"name\": \"LuthienHalf\","
        + "    \"price\": 23,"
        + "    \"furnitureType\": \"CABINET\","
        + "    \"width\": 36,"
        + "    \"height\": 36,"
        + "    \"depth\": 18,"
        + "    \"isWallFixtureNeeded\": false,"
        + "    \"availableColors\": \"BLACK, BONE\","
        + "    \"availableMountTypes\": \"FLOOR, WALL\""
        + "  }");
    final Furniture furniture1 = new Furniture(jsonObject);
    Assert.assertNotEquals(furniture1, furniture);
  }

  @Test
  public void testEquals2() {
    Assert.assertNotEquals(null, furniture);
  }

  @Test
  public void testEquals3() {
    Assert.assertNotEquals(furniture, null);
  }

  @Test
  public void testEquals4() throws ParseException {
    final JSONParser jsonParser = new JSONParser();
    final JSONObject jsonObject = (JSONObject) jsonParser.parse(" {"
        + "    \"name\": \"LuthienHalfs\","
        + "    \"price\": 40,"
        + "    \"furnitureType\": \"CABINET\","
        + "    \"width\": 36,"
        + "    \"height\": 36,"
        + "    \"depth\": 18,"
        + "    \"isWallFixtureNeeded\": false,"
        + "    \"availableColors\": \"BLACK, BONE\","
        + "    \"availableMountTypes\": \"FLOOR, WALL\""
        + "  }");
    final Furniture furniture1 = new Furniture(jsonObject);
    Assert.assertEquals(furniture1, furniture);
  }

  @Test
  public void testEquals5() throws ParseException {
    final JSONParser jsonParser = new JSONParser();
    final JSONObject jsonObject = (JSONObject) jsonParser.parse(" {"
        + "    \"name\": \"LuthienHalf\","
        + "    \"price\": 40,"
        + "    \"furnitureType\": \"OTHER\","
        + "    \"width\": 36,"
        + "    \"height\": 36,"
        + "    \"depth\": 18,"
        + "    \"isWallFixtureNeeded\": false,"
        + "    \"availableColors\": \"BLACK, BONE\","
        + "    \"availableMountTypes\": \"FLOOR, WALL\""
        + "  }");
    final Furniture furniture1 = new Furniture(jsonObject);
    Assert.assertNotEquals(furniture1, furniture);
  }

  @Test
  public void testEquals6() throws ParseException {
    final JSONParser jsonParser = new JSONParser();
    final JSONObject jsonObject = (JSONObject) jsonParser.parse(" {"
        + "    \"name\": \"LuthienHalf\","
        + "    \"price\": 40,"
        + "    \"furnitureType\": \"CABINET\","
        + "    \"width\": 363,"
        + "    \"height\": 36,"
        + "    \"depth\": 18,"
        + "    \"isWallFixtureNeeded\": false,"
        + "    \"availableColors\": \"BLACK, BONE\","
        + "    \"availableMountTypes\": \"FLOOR, WALL\""
        + "  }");
    final Furniture furniture1 = new Furniture(jsonObject);
    Assert.assertNotEquals(furniture1, furniture);
  }

  @Test
  public void testEquals7() throws ParseException {
    final JSONParser jsonParser = new JSONParser();
    final JSONObject jsonObject = (JSONObject) jsonParser.parse(" {"
        + "    \"name\": \"LuthienHalf\","
        + "    \"price\": 40,"
        + "    \"furnitureType\": \"CABINET\","
        + "    \"width\": 36,"
        + "    \"height\": 396,"
        + "    \"depth\": 18,"
        + "    \"isWallFixtureNeeded\": false,"
        + "    \"availableColors\": \"BLACK, BONE\","
        + "    \"availableMountTypes\": \"FLOOR, WALL\""
        + "  }");
    final Furniture furniture1 = new Furniture(jsonObject);
    Assert.assertNotEquals(furniture1, furniture);
  }

  @Test
  public void testEquals8() throws ParseException {
    final JSONParser jsonParser = new JSONParser();
    final JSONObject jsonObject = (JSONObject) jsonParser.parse(" {"
        + "    \"name\": \"LuthienHalf\","
        + "    \"price\": 40,"
        + "    \"furnitureType\": \"CABINET\","
        + "    \"width\": 36,"
        + "    \"height\": 36,"
        + "    \"depth\": 198,"
        + "    \"isWallFixtureNeeded\": false,"
        + "    \"availableColors\": \"BLACK, BONE\","
        + "    \"availableMountTypes\": \"FLOOR, WALL\""
        + "  }");
    final Furniture furniture1 = new Furniture(jsonObject);
    Assert.assertNotEquals(furniture1, furniture);
  }

  @Test
  public void testEquals9() throws ParseException {
    final JSONParser jsonParser = new JSONParser();
    final JSONObject jsonObject = (JSONObject) jsonParser.parse(" {"
        + "    \"name\": \"LuthienHalf\","
        + "    \"price\": 40,"
        + "    \"furnitureType\": \"CABINET\","
        + "    \"width\": 36,"
        + "    \"height\": 36,"
        + "    \"depth\": 18,"
        + "    \"isWallFixtureNeeded\": true,"
        + "    \"availableColors\": \"BLACK, BONE\","
        + "    \"availableMountTypes\": \"FLOOR, WALL\""
        + "  }");
    final Furniture furniture1 = new Furniture(jsonObject);
    Assert.assertNotEquals(furniture1, furniture);
  }

  @Test
  public void testEquals10() throws ParseException {
    final JSONParser jsonParser = new JSONParser();
    final JSONObject jsonObject = (JSONObject) jsonParser.parse(" {"
        + "    \"name\": \"LuthienHalf\","
        + "    \"price\": 40,"
        + "    \"furnitureType\": \"CABINET\","
        + "    \"width\": 36,"
        + "    \"height\": 36,"
        + "    \"depth\": 18,"
        + "    \"isWallFixtureNeeded\": false,"
        + "    \"availableColors\": \"BLACK, BONE, OXBLOOD\","
        + "    \"availableMountTypes\": \"FLOOR, WALL\""
        + "  }");
    final Furniture furniture1 = new Furniture(jsonObject);
    Assert.assertNotEquals(furniture1, furniture);
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(furniture.hashCode(), furniture.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals(("Furniture{price=40.0, furnitureType=CABINET, colorType=null,"
        + " width=36, height=36, isHandleNeeded=false, coverType=null, availableColors=["
        + "BLACK, BONE], depth=18, isFloorMounted=false, isFeetRequired=0, isWallFixtureNeeded=false,"
        + " isRailRequired=false, maxShelves=0, maxDrawers=0, availableMountTypes=[WALL, FLOOR],"
        + " handleType=null, railType=null}").length(), furniture.toString().length());
  }

  @Test
  public void getPrice() {
    Assert.assertEquals("40.0", furniture.getPrice() + "");
  }

  @Test
  public void setPrice() {
    furniture.setPrice(30);
    Assert.assertEquals("30.0", furniture.getPrice() + "");
  }

  @Test
  public void getName() {
    Assert.assertEquals("LuthienHalf", furniture.getName());
  }

  @Test
  public void setName() {
    furniture.setName("LuthienHalfs");
    Assert.assertEquals("LuthienHalfs", furniture.getName());
  }

  @Test
  public void getFurnitureType() {
    Assert.assertEquals(FurnitureType.CABINET, furniture.getFurnitureType());
  }

  @Test
  public void setFurnitureType() {
    furniture.setFurnitureType(FurnitureType.COVER);
    Assert.assertEquals(FurnitureType.COVER, furniture.getFurnitureType());
  }

  @Test
  public void getColorType() {
    Assert.assertNull(furniture.getColorType());
  }

  @Test
  public void setColorType() {
    furniture.setColorType(ColorType.BLACK);
    Assert.assertEquals(ColorType.BLACK, furniture.getColorType());
  }

  @Test
  public void getWidth() {
    Assert.assertEquals(36, furniture.getWidth());
  }

  @Test
  public void setWidth() {
    furniture.setWidth(22);
    Assert.assertEquals(22, furniture.getWidth());
  }

  @Test
  public void getHeight() {
    Assert.assertEquals(36, furniture.getHeight());
  }

  @Test
  public void setHeight() {
    furniture.setHeight(33);
    Assert.assertEquals(33, furniture.getHeight());
  }

  @Test
  public void isHandleNeeded() {
    Assert.assertFalse(furniture.isHandleNeeded());
  }

  @Test
  public void setHandleNeeded() {
    furniture.setHandleNeeded(true);
    Assert.assertTrue(furniture.isHandleNeeded());
  }

  @Test
  public void getCoverType() {
    Assert.assertNull(furniture.getCoverType());
  }

  @Test
  public void setCoverType() {
    furniture.setCoverType(CoverType.DOOR);
    Assert.assertEquals(CoverType.DOOR, furniture.getCoverType());
  }

  @Test
  public void getAvailableColors() {
    Assert.assertEquals(2, furniture.getAvailableColors().size());
  }

  @Test
  public void setAvailableColors() {
    furniture.setAvailableColors(new HashSet<>(Arrays.asList(ColorType.BLACK)));
    Assert.assertEquals(1, furniture.getAvailableColors().size());
  }

  @Test
  public void getDepth() {
    Assert.assertEquals(18, furniture.getDepth());
  }

  @Test
  public void setDepth() {
    furniture.setDepth(12);
    Assert.assertEquals(12, furniture.getDepth());
  }

  @Test
  public void isFloorMounted() {
    Assert.assertFalse(furniture.isFloorMounted());
  }

  @Test
  public void setFloorMounted() {
    furniture.setFloorMounted(true);
    Assert.assertTrue(furniture.isFloorMounted());
  }

  @Test
  public void getIsFeetRequired() {
    Assert.assertEquals(0, furniture.getIsFeetRequired());
  }

  @Test
  public void setIsFeetRequired() {
    furniture.setIsFeetRequired(1);
    Assert.assertEquals(1, furniture.getIsFeetRequired());
  }

  @Test
  public void isWallFixtureNeeded() {
    Assert.assertFalse(furniture.isWallFixtureNeeded());
  }

  @Test
  public void setWallFixtureNeeded() {
    furniture.setWallFixtureNeeded(true);
    Assert.assertTrue(furniture.isWallFixtureNeeded());
  }

  @Test
  public void isRailRequired() {
    Assert.assertFalse(furniture.isRailRequired());
  }

  @Test
  public void setRailRequired() {
    furniture.setRailRequired(true);
    Assert.assertTrue(furniture.isRailRequired());
  }

  @Test
  public void getMaxShelves() {
    Assert.assertEquals(0, furniture.getMaxShelves());
  }

  @Test
  public void setMaxShelves() {
    furniture.setMaxShelves(2);
    Assert.assertEquals(2, furniture.getMaxShelves());
  }

  @Test
  public void getMaxDrawers() {
    Assert.assertEquals(0, furniture.getMaxDrawers());
  }

  @Test
  public void setMaxDrawers() {
    furniture.setMaxDrawers(2);
    Assert.assertEquals(2, furniture.getMaxDrawers());
  }

  @Test
  public void getAvailableMountTypes() {
    Assert.assertEquals(2, furniture.getAvailableMountTypes().size());
  }

  @Test
  public void setAvailableMountTypes() {
    furniture.setAvailableMountTypes(new HashSet<>(Arrays.asList(MountType.FLOOR)));
    Assert.assertEquals(1, furniture.getAvailableMountTypes().size());
  }

  @Test
  public void getHandleType() {
    Assert.assertNull(furniture.getHandleType());
  }

  @Test
  public void setHandleType() {
    furniture.setHandleType(HandleType.DOOR_HANDLE);
    Assert.assertEquals(HandleType.DOOR_HANDLE, furniture.getHandleType());
  }

  @Test
  public void getRailType() {
    Assert.assertNull(furniture.getRailType());
  }

  @Test
  public void setRailType() {
    furniture.setRailType(RailType.STANDARD);
    Assert.assertEquals(RailType.STANDARD, furniture.getRailType());
  }

  @Test
  public void change2WallMounted() {
    furniture.change2WallMounted();
    Assert.assertFalse(furniture.isFloorMounted());
    Assert.assertTrue(furniture.isRailRequired());
    Assert.assertEquals(0, furniture.getIsFeetRequired());
  }

  @Test
  public void change2FloorMounted() {
    furniture.change2FloorMounted();
    Assert.assertTrue(furniture.isFloorMounted());
    Assert.assertFalse(furniture.isRailRequired());
    Assert.assertNull(furniture.getRailType());
  }

  @Test
  public void change2WithDrawer() {
    furniture.change2WithDrawer();
    Assert.assertEquals(1, furniture.getMaxDrawers());
    Assert.assertEquals(2, furniture.getMaxShelves());
  }

  @Test
  public void change2WithoutDrawer() {
    furniture.change2WithoutDrawer();
    Assert.assertEquals(0, furniture.getMaxDrawers());
    Assert.assertEquals(3, furniture.getMaxShelves());
  }
}