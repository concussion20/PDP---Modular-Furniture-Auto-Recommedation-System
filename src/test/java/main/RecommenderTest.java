package main;

import common.FileHelper;
import data.CustomerCriteria;
import data.Item;
import data.ShoppingList;
import enums.ColorType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * test class for Recommender.
 * @author Chang Zhou
 */
public class RecommenderTest {
  private Recommender recommender;

  @Before
  public void setUp() throws Exception {
   recommender = new Recommender("src/main/resources/furniture.json");
  }

  @Test
  public void getCabsAndCovers() {
    Assert.assertEquals(7, recommender.getCabsAndCovers().get("CABINET").size());
    Assert.assertEquals(8, recommender.getCabsAndCovers().get("COVER").size());
  }

  @Test
  public void setCabsAndCovers() {
    final Map<String, List<Furniture>> map = new HashMap<>();
    recommender.setCabsAndCovers(map);
    Assert.assertEquals(0, recommender.getCabsAndCovers().size());
  }

  @Test
  public void loadFurniture() {
    Assert.assertEquals(7, recommender.getCabsAndCovers().get("CABINET").size());
    Assert.assertEquals(8, recommender.getCabsAndCovers().get("COVER").size());
    Assert.assertEquals(8, recommender.getOtherFurniture().size());
  }

  @Test
  public void loadCustomerCriteria() {
    FileHelper.writeJsonFile("src/main/"
        + "resources/customer_criteria.json", "{  \"desiredWidth\": 355,  "
        + "\"desiredHeight\": 36,  "
        + "\"desiredShelves\": 3,  \"desiredDrawers\": 0,"
        + "  \"desiredColor\": null,  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}");
    final CustomerCriteria customerCriteria = recommender.loadCustomerCriteria("src/mai"
        + "n/resources/customer_criteria.json");
    Assert.assertEquals("CustomerCriteria{desiredWidth=355, desiredHeight=36, desiredSh"
        + "elves=3, desiredDrawers=0, desiredColor=null, desiredMountType=FLOOR, budget=2999.0}",
        customerCriteria.toString());
  }

  @Test
  public void recommend() {
    FileHelper.writeJsonFile("src/main/resources/customer_criteria.json", "{"
        + "  \"desiredWidth\": 355,  \"desiredHeight\": 36,  \"desiredShelves\": 3,"
        + "  \"desiredDrawers\": 0,\n"
        + "  \"desiredColor\": null,  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}");
    recommender.recommend();
    Assert.assertEquals("{\"shoppingLists\": [{\"itemList\": [{\"furnitureName\": "
        + "\"LuthienHalf\", \"count\": 9, \"totalPrice\": 360.0}, {\"furnitureName\": "
        + "\"SlothropHalf\", \"count\": 9, \"totalPrice\": 108.0}, {\"furnitureName\": "
        + "\"DoorHinges\", \"count\": 9, \"totalPrice\": 22.5}], \"total\": 490.5,"
        + " \"combinationColor\": \"BLACK\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"LuthienHalf\", \"count\": 9, \"totalPrice\":"
        + " 360.0}, {\"furnitureName\": \"GagaHalf\", \"count\": 9, \"totalPrice\": 99.0},"
        + " {\"furnitureName\": \"DoorHinges\", \"count\": 9, \"totalPrice\": 22.5}, "
        + "{\"furnitureName\": \"HandleDoor\", \"count\": 9, \"totalPrice\": 36.0}],"
        + " \"total\": 517.5, \"combinationColor\": \"BLACK\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"LuthienHalf\", \"count\": 9, "
        + "\"totalPrice\": 360.0}, {\"furnitureName\": \"BelacquaHalf\", \"count\": 9, "
        + "\"totalPrice\": 144.0}, {\"furnitureName\": \"DoorHinges\", \"count\": 9, "
        + "\"totalPrice\": 22.5}], \"total\": 526.5, \"combinationColor\": \"BONE\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"AtreidesHalf\", \"count\": 9,"
        + " \"totalPrice\": 450.0}, {\"furnitureName\": \"SlothropHalf\", \"count\": 9, \""
        + "totalPrice\": 108.0}, {\"furnitureName\": \"DoorHinges\", \"count\": 9, \"total"
        + "Price\": 22.5}], \"total\": 580.5, \"combinationColor\": \"BROWN\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"AtreidesHalf\", \"count\": 9, \"t"
        + "otalPrice\": 450.0}, {\"furnitureName\": \"GagaHalf\", \"count\": 9, \"t"
        + "otalPrice\": 99.0}, {\"furnitureName\": \"DoorHinges\", \"count\": 9, \"t"
        + "otalPrice\": 22.5}, {\"furnitureName\": \"HandleDoor\", \"count\": 9, \"tot"
        + "alPrice\": 36.0}], \"total\": 607.5, \"combinationColor\": \"BROWN\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"AtreidesHalf\", \"count\": 9, "
        + "\"totalPrice\": 450.0}, {\"furnitureName\": \"BelacquaHalf\", \"count\": "
        + "9, \"totalPrice\": 144.0}, {\"furnitureName\": \"DoorHinges\", \"count\": "
        + "9, \"totalPrice\": 22.5}], \"total\": 616.5, \"combinationColor\": \"BONE\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"YossarianHalf\", \"count\": 9, "
        + "\"totalPrice\": 495.0}, {\"furnitureName\": \"SlothropHalf\", \"count\":"
        + " 9, \"totalPrice\": 108.0}, {\"furnitureName\": \"DoorHinges\", \"count\": "
        + "9, \"totalPrice\": 22.5}], \"total\": 625.5, \"combinationColor\": \"BROWN\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"YossarianHalf\", \"count\": 9, "
        + "\"totalPrice\": 495.0}, {\"furnitureName\": \"GagaHalf\", \"count\": 9, "
        + "\"totalPrice\": 99.0}, {\"furnitureName\": \"DoorHinges\", \"count\": 9, "
        + "\"totalPrice\": 22.5}, {\"furnitureName\": \"HandleDoor\", \"count\": 9,"
        + " \"totalPrice\": 36.0}], \"total\": 652.5, \"combinationColor\": \"BROWN\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"YossarianHalf\", \"count\": 9, "
        + "\"totalPrice\": 495.0}, {\"furnitureName\": \"BelacquaHalf\", \"count\":"
        + " 9, \"totalPrice\": 144.0}, {\"furnitureName\": \"DoorHinges\", \"count\":"
        + " 9, \"totalPrice\": 22.5}], \"total\": 661.5, \"combinationColor\": \"BONE\"}]}",
        FileHelper.readJsonFile("src/mai"
        + "n/resources/shoppingLists.json"));

    FileHelper.writeJsonFile("src/main/resources/customer_criteria.json", "{"
        + "  \"desiredWidth\": 355,  \"desiredHeight\": 36,  \"desiredShelves\": 3,"
        + "  \"desiredDrawers\": 0,\n"
        + "  \"desiredColor\": \"BLACK\",  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}");
    recommender.recommend();
    Assert.assertEquals("{\"shoppingLists\": [{\"itemList\": [{\"furnitureName\": \""
        + "LuthienHalf\", \"count\": 9, \"totalPrice\": 360.0}, {\"furnitureName\":"
        + " \"SlothropHalf\", \"count\": 9, \"totalPrice\": 108.0}, {\"furnitureName\": "
        + "\"DoorHinges\", \"count\": 9, \"totalPrice\": 22.5}], \"total\": 490.5,"
        + " \"combinationColor\": \"BLACK\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"LuthienHalf\", \"count\": 9, "
        + "\"totalPrice\": 360.0}, {\"furnitureName\": \"GagaHalf\", \"count\": 9,"
        + " \"totalPrice\": 99.0}, {\"furnitureName\": \"DoorHinges\", \"count\": 9, "
        + "\"totalPrice\": 22.5}, {\"furnitureName\": \"HandleDoor\", \"count\": 9, "
        + "\"totalPrice\": 36.0}], \"total\": 517.5, \"combinationColor\": \"BLACK\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"YossarianHalf\", \"count\": 9, "
        + "\"totalPrice\": 495.0}, {\"furnitureName\": \"SlothropHalf\", \"count\": 9, "
        + "\"totalPrice\": 108.0}, {\"furnitureName\": \"DoorHinges\", \"count\": 9, "
        + "\"totalPrice\": 22.5}], \"total\": 625.5, \"combinationColor\": \"BLACK\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"YossarianHalf\", \"count\": 9, "
        + "\"totalPrice\": 495.0}, {\"furnitureName\": \"GagaHalf\", \"count\": 9, "
        + "\"totalPrice\": 99.0}, {\"furnitureName\": \"DoorHinges\", \"count\": 9,"
        + " \"totalPrice\": 22.5}, {\"furnitureName\": \"HandleDoor\", \"count\": 9, "
        + "\"totalPrice\": 36.0}], \"total\": 652.5, \"combinationColor\": \"BLACK\"}]}",
        FileHelper.readJsonFile("src/mai"
        + "n/resources/shoppingLists.json"));

    FileHelper.writeJsonFile("src/main/resources/customer_criteria.json", "{"
        + "  \"desiredWidth\": 355,  \"desiredHeight\": 36,  \"desiredShelves\": 3,"
        + "  \"desiredDrawers\": 3,"
        + "  \"desiredColor\": \"BLACK\",  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}");
    recommender.recommend();
    Assert.assertEquals("{\"shoppingLists\": [{\"itemList\": [{\"furnitureName\": "
        + "\"LuthienHalf\", \"count\": 9, \"totalPrice\": 360.0}, {\"furnitureName\": \"Drawer\","
        + " \"count\": 3, \"totalPrice\": 45.0}, {\"furnitureName\": \"HandleDrawer\", \"count\":"
        + " 3, \"totalPrice\": 9.0}, {\"furnitureName\": \"GagaQuarterDrawerFront\", \"count\": "
        + "3, \"totalPrice\": 18.0}, {\"furnitureName\": \"SlothropHalf\", \"count\": 9,"
        + " \"totalPrice\": 108.0}, {\"furnitureName\": \"DoorHinges\", \"count\": 9,"
        + " \"totalPrice\": 22.5}], \"total\": 562.5, \"combinationColor\": \"BLACK\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"LuthienHalf\", \"count\": 9, \"totalPrice\":"
        + " 360.0}, {\"furnitureName\": \"Drawer\", \"count\": 3, \"totalPrice\": 45.0}, "
        + "{\"furnitureName\": \"HandleDrawer\", \"count\": 3, \"totalPrice\": 9.0},"
        + " {\"furnitureName\": \"GagaQuarterDrawerFront\", \"count\": 3, \"totalPrice\": "
        + "18.0}, {\"furnitureName\": \"GagaHalf\", \"count\": 9, \"totalPrice\": 99.0}, "
        + "{\"furnitureName\": \"DoorHinges\", \"count\": 9, \"totalPrice\": 22.5}, "
        + "{\"furnitureName\": \"HandleDoor\", \"count\": 9, \"totalPrice\": 36.0}], "
        + "\"total\": 589.5, \"combinationColor\": \"BLACK\"}]}",
        FileHelper.readJsonFile("src/mai"
        + "n/resources/shoppingLists.json"));

    FileHelper.writeJsonFile("src/main/resources/customer_criteria.json", "{"
        + "  \"desiredWidth\": 355,  \"desiredHeight\": 36,  \"desiredShelves\": 3,"
        + "  \"desiredDrawers\": 90,"
        + "  \"desiredColor\": \"BLACK\",  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}");
    recommender.recommend();
    Assert.assertEquals("No Available ShoppingList.",
        FileHelper.readJsonFile("src/mai"
            + "n/resources/shoppingLists.json"));

    FileHelper.writeJsonFile("src/main/resources/customer_criteria.json", "{"
        + "  \"desiredWidth\": 355,  \"desiredHeight\": 36,  \"desiredShelves\": 3,"
        + "  \"desiredDrawers\": 0,\n"
        + "  \"desiredColor\": null,  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}");
  }

  @Test
  public void checkIsDrawersShelvesMatch() {
    Assert.assertTrue(recommender.checkIsDrawersShelvesMatch(4, 0,
        5, 14, 0));
  }

  @Test
  public void getWallFixture() {
    final Item item = recommender.getWallFixture(5);
    Assert.assertEquals(5, item.getCount());
    Assert.assertEquals("WallFixture", item.getFurnitureName());
    Assert.assertEquals("30.0", item.getTotalPrice() + "");
  }

  @Test
  public void getStandardRail() {
    final Item item = recommender.getStandardRail(5);
    Assert.assertEquals(5, item.getCount());
    Assert.assertEquals("RailStandard", item.getFurnitureName());
    Assert.assertEquals("35.0", item.getTotalPrice() + "");
  }

  @Test
  public void getAtreidesRail() {
    final Item item = recommender.getAtreidesRail(5);
    Assert.assertEquals(5, item.getCount());
    Assert.assertEquals("RailAtreides", item.getFurnitureName());
    Assert.assertEquals("50.0", item.getTotalPrice() + "");
  }

  @Test
  public void getCornerFeet() {
    final Item item = recommender.getCornerFeet(5);
    Assert.assertEquals(5, item.getCount());
    Assert.assertEquals("CornerFoot", item.getFurnitureName());
    Assert.assertEquals("20.0", item.getTotalPrice() + "");
  }

  @Test
  public void getDrawer() {
    final Item item = recommender.getDrawer(5);
    Assert.assertEquals(5, item.getCount());
    Assert.assertEquals("Drawer", item.getFurnitureName());
    Assert.assertEquals("75.0", item.getTotalPrice() + "");
  }

  @Test
  public void getHandleDrawer() {
    final Item item = recommender.getHandleDrawer(5);
    Assert.assertEquals(5, item.getCount());
    Assert.assertEquals("HandleDrawer", item.getFurnitureName());
    Assert.assertEquals("15.0", item.getTotalPrice() + "");
  }

  @Test
  public void getDrawerFront() {
    final Item item = recommender.getDrawerFront(5);
    Assert.assertEquals(5, item.getCount());
    Assert.assertEquals("GagaQuarterDrawerFront", item.getFurnitureName());
    Assert.assertEquals("30.0", item.getTotalPrice() + "");
  }

  @Test
  public void getShoppingListsWithCover() {
    FileHelper.writeJsonFile("src/main/resources/customer_criteria.json", "{"
        + "  \"desiredWidth\": 355,  \"desiredHeight\": 36,  \"desiredShelves\": 3,"
        + "  \"desiredDrawers\": 0,\n"
        + "  \"desiredColor\": null,  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}");
    final String ccJson = FileHelper.readJsonFile("src/main/resources/"
        + "customer_criteria.json");
    final CustomerCriteria customerCriteria = new CustomerCriteria(ccJson);
    final ShoppingList shoppingList = new ShoppingList();
    shoppingList.setColorType(ColorType.BLACK);

    final JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) jsonParser.parse(" {"
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
    } catch (ParseException e) {
      e.printStackTrace();
      Assert.fail();
    }
    final Furniture furniture1 = new Furniture(jsonObject);

    final Item item = new Item();
    item.setCount(5);
    item.setFurniture(furniture1);
    item.setFurnitureName("LuthienHalf");

    shoppingList.addItem(item);
    final Set<ShoppingList> set = recommender.getShoppingListsWithCover(customerCriteria, 5,
         shoppingList, ColorType.BLACK);
    Assert.assertEquals(2, set.size());
    for (final ShoppingList shoppingList1: set) {
      if (shoppingList1.getTotal() == 72.5) {
        Assert.assertEquals("ShoppingList{list=[{\"furnitureName\": \"LuthienHalf\","
            + " \"count\": 5, \"totalPrice\": 0.0}, {\"furnitureName\": \"SlothropHalf\","
            + " \"count\": 5, \"totalPrice\": 60.0}, {\"furnitureName\": \"DoorHinges\","
            + " \"count\": 5, \"totalPrice\": 12.5}], total=72.5, colorType=BLACK}",
            shoppingList1.toString());
      } else {
        Assert.assertEquals("ShoppingList{list=[{\"furnitureName\": \"LuthienHalf\","
                + " \"count\": 5, \"totalPrice\": 0.0}, {\"furnitureName\": \"GagaHalf\", "
                + "\"count\": 5, \"totalPrice\": 55.0}, {\"furnitureName\": \"DoorHinges\", "
                + "\"count\": 5, \"totalPrice\": 12.5}, {\"furnitureName\": \"HandleDoor\", "
                + "\"count\": 5, \"totalPrice\": 20.0}], total=87.5, colorType=BLACK}",
            shoppingList1.toString());
      }
    }
  }

  @Test
  public void getDoorHinges() {
    final Item item = recommender.getDoorHinges(5);
    Assert.assertEquals(5, item.getCount());
    Assert.assertEquals("DoorHinges", item.getFurnitureName());
    Assert.assertEquals("12.5", item.getTotalPrice() + "");
  }

  @Test
  public void getHandleDoor() {
    final Item item = recommender.getHandleDoor(5);
    Assert.assertEquals(5, item.getCount());
    Assert.assertEquals("HandleDoor", item.getFurnitureName());
    Assert.assertEquals("20.0", item.getTotalPrice() + "");
  }

  @Test
  public void convertFinalSLs2JsonString() {
    FileHelper.writeJsonFile("src/main/resources/customer_criteria.json", "{"
        + "  \"desiredWidth\": 355,  \"desiredHeight\": 36,  \"desiredShelves\": 3,"
        + "  \"desiredDrawers\": 0,\n"
        + "  \"desiredColor\": null,  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}");
    final String ccJson = FileHelper.readJsonFile("src/main/resources/"
        + "customer_criteria.json");
    final CustomerCriteria customerCriteria = new CustomerCriteria(ccJson);
    final ShoppingList shoppingList = new ShoppingList();
    shoppingList.setColorType(ColorType.BLACK);

    final JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) jsonParser.parse(" {"
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
    } catch (ParseException e) {
      e.printStackTrace();
      Assert.fail();
    }
    final Furniture furniture1 = new Furniture(jsonObject);

    final Item item = new Item();
    item.setCount(5);
    item.setFurniture(furniture1);
    item.setFurnitureName("LuthienHalf");

    shoppingList.addItem(item);
    final Set<ShoppingList> set = recommender.getShoppingListsWithCover(customerCriteria, 5,
        shoppingList, ColorType.BLACK);
    Assert.assertTrue(recommender.convertFinalSLs2JsonString(set).contains("[{\"furnitureName\":"
            + " \"LuthienHalf\", \"count\": 5, \"totalPrice\": 0.0}, {\"furnitureName\": "
            + "\"SlothropHalf\", \"count\": 5, \"totalPrice\": 60.0}, {\"furnitureName\": "
            + "\"DoorHinges\", \"count\": 5, \"totalPrice\": 12.5}]"));
  }

  @Test
  public void getAllCabinets() {
    Assert.assertEquals(7, recommender.getAllCabinets().size());
  }

  @Test
  public void getCustomerCriteriaJsonStr() {
    FileHelper.writeJsonFile("src/main/"
        + "resources/customer_criteria.json", "{  \"desiredWidth\": 355,  "
        + "\"desiredHeight\": 36,  "
        + "\"desiredShelves\": 3,  \"desiredDrawers\": 0,"
        + "  \"desiredColor\": null,  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}");
    Assert.assertEquals("{  \"desiredWidth\": 355,  \"desiredHeight\": 36,  "
        + "\"desiredShelves\": 3,  \"desiredDrawers\": 0,"
        + "  \"desiredColor\": null,  \"desiredMountType\": \"FLOOR\",  \"budget\": 2999}",
        recommender.getCustomerCriteriaJsonStr("src/main/"
        + "resources/customer_criteria.json"));
  }

  @Test
  public void outputShoppingListJsonStr() {
    recommender.outputShoppingListJsonStr("{\"shoppingLists\": [{\"itemList\": "
        + "[{\"furnitureName\": \"LuthienHalf\", \"count\": 5, \"totalPrice\": 0.0}, "
        + "{\"furnitureName\": \"SlothropHalf\", \"count\": 5, \"totalPrice\": 60.0}, "
        + "{\"furnitureName\": "
        + "\"DoorHinges\", \"count\": 5, \"totalPrice\": 12.5}], \"total\": 72.5, \"com"
        + "binationColor\": \"BLACK\"}, "
        + "{\"itemList\": [{\"furnitureName\": \"LuthienHalf\", \"count\": 5, \"totalPrice\""
        + ": 0.0}, {\"furnitureName\": \"GagaHalf\", \"count\": 5, \"totalPrice\": 55.0}, "
        + "{\"furnitureName\": \"DoorHinges\", \"count\": 5, \"totalPrice\": 12.5}, {\"fu"
        + "rnitureName\": \"HandleDoor\", \"count\": 5, \"totalPrice\": 20.0}], \"total\": 87"
        + ".5, \"combinationColor\": \"BLACK\"}]}", "src/main/resources/output.json");
    Assert.assertEquals("{\"shoppingLists\": [{\"itemList\": "
            + "[{\"furnitureName\": \"LuthienHalf\", \"count\": 5, \"totalPrice\": 0.0}, "
            + "{\"furnitureName\": \"SlothropHalf\", \"count\": 5, \"totalPrice\": 60.0}, "
            + "{\"furnitureName\": "
            + "\"DoorHinges\", \"count\": 5, \"totalPrice\": 12.5}], \"total\": 72.5, \"com"
            + "binationColor\": \"BLACK\"}, "
            + "{\"itemList\": [{\"furnitureName\": \"LuthienHalf\", \"count\": 5, \"totalPrice\""
            + ": 0.0}, {\"furnitureName\": \"GagaHalf\", \"count\": 5, \"totalPrice\": 55.0}, "
            + "{\"furnitureName\": \"DoorHinges\", \"count\": 5, \"totalPrice\": 12.5}, {\"fu"
            + "rnitureName\": \"HandleDoor\", \"count\": 5, \"totalPrice\": 20.0}], \"total\": 87"
            + ".5, \"combinationColor\": \"BLACK\"}]}",
        FileHelper.readJsonFile("src/main/resources/output.json"));
  }
}