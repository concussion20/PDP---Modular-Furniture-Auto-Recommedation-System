package data;

import enums.ColorType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * test class for ShoppingList.
 * @author Chang Zhou
 */
public class ShoppingListTest {
  private ShoppingList shoppingList;

  @Before
  public void setUp() throws Exception {
    shoppingList = new ShoppingList();
    shoppingList.setColorType(ColorType.BONE);
    final Item item = new Item();
    item.setTotalPrice(12);
    final Item item1 = new Item();
    item1.setTotalPrice(13);
    final List<Item> itemList = new ArrayList<>();
    itemList.add(item);
    itemList.add(item1);
    shoppingList.setList(itemList);
  }

  @Test
  public void testToString() {
    Assert.assertEquals("ShoppingList{list=[{\"furnitureName\": \"null\", \"count\": 0, "
        + "\"totalPrice\": 12.0}, {\"furnitureName\": \"null\", \"count\": 0, \"totalPrice\":"
        + " 13.0}], total=0.0, colorType=BONE}", shoppingList.toString());
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(shoppingList, shoppingList);
  }

  @Test
  public void testEquals1() {
    Assert.assertNotEquals(shoppingList, null);
  }

  @Test
  public void testEquals2() {
    Assert.assertNotEquals(null, shoppingList);
  }

  @Test
  public void testEquals3() {
    Assert.assertNotEquals(shoppingList, 1);
  }

  @Test
  public void testEquals4() {
    final ShoppingList shoppingList1 = new ShoppingList();
    shoppingList1.setColorType(ColorType.BONE);
    final Item item = new Item();
    item.setTotalPrice(22);
    final List<Item> itemList = new ArrayList<>();
    itemList.add(item);
    shoppingList1.setList(itemList);
    Assert.assertNotEquals(shoppingList, shoppingList1);
  }

  @Test
  public void testEquals5() {
    final ShoppingList shoppingList1 = new ShoppingList();
    shoppingList1.setColorType(ColorType.BONE);
    final Item item = new Item();
    item.setTotalPrice(22);
    final Item item1 = new Item();
    item1.setTotalPrice(13);
    final List<Item> itemList = new ArrayList<>();
    itemList.add(item);
    itemList.add(item1);
    shoppingList1.setList(itemList);
    Assert.assertNotEquals(shoppingList, shoppingList1);
  }

  @Test
  public void testEquals6() {
    final ShoppingList shoppingList1 = new ShoppingList();
    shoppingList1.setColorType(ColorType.BLACK);
    final Item item = new Item();
    item.setTotalPrice(12);
    final Item item1 = new Item();
    item1.setTotalPrice(13);
    final List<Item> itemList = new ArrayList<>();
    itemList.add(item);
    itemList.add(item1);
    shoppingList1.setList(itemList);
    Assert.assertNotEquals(shoppingList, shoppingList1);
  }

  @Test
  public void testEquals7() {
    Assert.assertNotEquals(shoppingList, null);
  }

  @Test
  public void testEquals8() {
    Assert.assertNotEquals(shoppingList, null);
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(shoppingList.hashCode(), shoppingList.hashCode());
  }

  @Test
  public void convertShoppingList2JsonStr() {
    Assert.assertEquals("{\"itemList\": [{\"furnitureName\": \"null\","
        + " \"count\": 0, \"totalPrice\": 12.0}, {\"furnitureName\": \"null\", "
        + "\"count\": 0, \"totalPrice\": 13.0}], \"total\": 25.0, \"combinationColor\":"
        + " \"BONE\"}", shoppingList.convertShoppingList2JsonStr());
  }

  @Test
  public void calculateTotal() {
    Assert.assertEquals("25.0", shoppingList.calculateTotal() + "");
  }

  @Test
  public void getColorType() {
    Assert.assertEquals(ColorType.BONE, shoppingList.getColorType());
  }

  @Test
  public void setColorType() {
    shoppingList.setColorType(ColorType.BLACK);
    Assert.assertEquals(ColorType.BLACK, shoppingList.getColorType());
  }

  @Test
  public void getList() {
    Assert.assertEquals(shoppingList.getList(), shoppingList.getList());
  }

  @Test
  public void setList() {
    final List<Item> list = new ArrayList<>();
    list.add(new Item());
    list.add(new Item());
    shoppingList.setList(list);
    Assert.assertEquals(list, shoppingList.getList());
  }

  @Test
  public void addItem() {
    final Item item = new Item();
    shoppingList.addItem(item);
    final List<Item> list = shoppingList.getList();
    Assert.assertEquals(item, list.get(list.size() - 1));
  }

  @Test
  public void getTotal() {
    Assert.assertEquals("25.0", shoppingList.getTotal() + "");
  }
}