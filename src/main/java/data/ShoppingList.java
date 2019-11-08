package data;

import enums.ColorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the shopping list obj which hold a list of items for customer to buy.
 * @author Chang Zhou
 */
public class ShoppingList {
  private List<Item> list = new ArrayList<>();
  private double total;
  private ColorType colorType;

  @Override
  public String toString() {
    return "ShoppingList{" +
        "list=" + list +
        ", total=" + total +
        ", colorType=" + colorType +
        '}';
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    final ShoppingList that = (ShoppingList) object;
    return Double.compare(that.getTotal(), getTotal()) == 0 &&
        Objects.equals(getList(), that.getList()) &&
        getColorType() == that.getColorType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getList(), getTotal(), getColorType());
  }

  /**
   * convert this obj to a json string for the output file.
   * @return json string
   */
  public String convertShoppingList2JsonStr() {
    String jsonStr = "{"
        + "\"itemList\": [";
    for (final Item item: list) {
      jsonStr += item.toString();
      jsonStr += ", ";
    }
    jsonStr = jsonStr.substring(0, jsonStr.length() - 2);
    calculateTotal();
    jsonStr += "], \"total\": " + total + ", \"combinationColor\": \"" +
        colorType.toString() + "\"}";
    return jsonStr;
  }

  /**
   * calculate the total price for this shoppingList.
   * @return total price
   */
  public double calculateTotal() {
    total = 0;
    for (final Item item: list) {
      total += item.getTotalPrice();
    }
    return total;
  }

  /**
   * get color type.
   * @return color type
   */
  public ColorType getColorType() {
    return colorType;
  }

  /**
   * set color type.
   * @param colorType colorType
   */
  public void setColorType(final ColorType colorType) {
    this.colorType = colorType;
  }

  /**
   * get the item list.
   * @return the item list
   */
  public List<Item> getList() {
    return list;
  }

  /**
   * set the item list.
   * @param list the item list
   */
  public void setList(final List<Item> list) {
    this.list = list;
  }

  /**
   * add an item into the item list.
   * @param item the item need to be add in
   */
  public void addItem(final Item item) {
    this.list.add(item);
  }

  /**
   * get total price for this shopping list.
   * @return total price
   */
  public double getTotal() {
    return calculateTotal();
  }
}
