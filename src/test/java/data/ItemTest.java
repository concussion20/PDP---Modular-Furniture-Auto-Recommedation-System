package data;

import main.Furniture;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * test class for Item.
 * @author Chang Zhou
 */
public class ItemTest {
  private Item item;
  private Furniture drawer;

  @Before
  public void setUp() throws Exception {
    final JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "Drawer");
    jsonObject.put("price", 15);
    jsonObject.put("furnitureType", "OTHER");
    jsonObject.put("depth", 18);
    drawer = new Furniture(jsonObject);
    item = new Item();
    item.setFurnitureName("Drawer");
    item.setFurniture(drawer);
    item.setCount(1);
    item.setTotalPrice(item.getFurniture().getPrice());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("{\"furnitureName\": \"Drawer\", \"count\": 1, \"totalPrice\": "
        + "15.0}", item.toString());
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(item, item);
  }

  @Test
  public void testEquals2() {
    Assert.assertNotEquals(item, null);
  }

  @Test
  public void testEquals3() {
    Assert.assertNotEquals(null, item);
  }

  @Test
  public void testEquals4() {
    Assert.assertNotEquals(item, 1);
  }

  @Test
  public void testEquals5() {
    final JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "Drawer");
    jsonObject.put("price", 2222);
    jsonObject.put("furnitureType", "OTHER");
    jsonObject.put("depth", 18);
    final Furniture drawer = new Furniture(jsonObject);
    final Item item1 = new Item();
    item1.setFurnitureName("Drawer");
    item1.setFurniture(drawer);
    item1.setCount(1);
    item1.setTotalPrice(item.getFurniture().getPrice());
    Assert.assertNotEquals(item1, item);
  }
  @Test
  public void testEquals6() {
    final JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "Drawer");
    jsonObject.put("price", 15);
    jsonObject.put("furnitureType", "OTHER");
    jsonObject.put("depth", 18);
    final Furniture drawer = new Furniture(jsonObject);
    final Item item1 = new Item();
    item1.setFurnitureName("Drawers");
    item1.setFurniture(drawer);
    item1.setCount(1);
    item1.setTotalPrice(item.getFurniture().getPrice());
    Assert.assertNotEquals(item1, item);
  }

  @Test
  public void testEquals7() {
    final JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "Drawer");
    jsonObject.put("price", 15);
    jsonObject.put("furnitureType", "OTHER");
    jsonObject.put("depth", 23);
    final Furniture drawer = new Furniture(jsonObject);
    final Item item1 = new Item();
    item1.setFurnitureName("Drawer");
    item1.setFurniture(drawer);
    item1.setCount(1);
    item1.setTotalPrice(item.getFurniture().getPrice());
    Assert.assertNotEquals(item1, item);
  }

  @Test
  public void testEquals8() {
    final JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "Drawer");
    jsonObject.put("price", 15);
    jsonObject.put("furnitureType", "OTHER");
    jsonObject.put("depth", 18);
    final Furniture drawer = new Furniture(jsonObject);
    final Item item1 = new Item();
    item1.setFurnitureName("Drawer");
    item1.setFurniture(drawer);
    item1.setCount(9);
    item1.setTotalPrice(item.getFurniture().getPrice());
    Assert.assertNotEquals(item1, item);
  }

  @Test
  public void testEquals9() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "Drawer");
    jsonObject.put("price", 15);
    jsonObject.put("furnitureType", "OTHER");
    jsonObject.put("depth", 18);
    final Furniture drawer = new Furniture(jsonObject);
    final Item item1 = new Item();
    item1.setFurnitureName("Drawer");
    item1.setFurniture(drawer);
    item1.setCount(1);
    item1.setTotalPrice(6);
    Assert.assertNotEquals(item1, item);
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(item.hashCode(), item.hashCode());
  }

  @Test
  public void getFurniture() {
    Assert.assertEquals(drawer, item.getFurniture());
  }

  @Test
  public void setFurniture() {
    final JSONObject jsonObject = new JSONObject();
    final Furniture furniture = new Furniture(jsonObject);
    item.setFurniture(furniture);
    Assert.assertEquals(furniture, item.getFurniture());
  }

  @Test
  public void getFurnitureName() {
    Assert.assertEquals("Drawer", item.getFurnitureName());
  }

  @Test
  public void setFurnitureName() {
    item.setFurnitureName("name");
    Assert.assertEquals("name", item.getFurnitureName());
  }

  @Test
  public void getCount() {
    Assert.assertEquals(1, item.getCount());
  }

  @Test
  public void setCount() {
    item.setCount(4);
    Assert.assertEquals(4, item.getCount());
  }

  @Test
  public void getTotalPrice() {
    Assert.assertEquals("15.0", item.getTotalPrice() + "");
  }

  @Test
  public void setTotalPrice() {
    item.setTotalPrice(13);
    Assert.assertEquals("13.0", item.getTotalPrice() + "");
  }
}