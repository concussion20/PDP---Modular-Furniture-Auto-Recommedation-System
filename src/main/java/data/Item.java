package data;

import java.util.Objects;
import main.Furniture;

/**
 * item can be stored in shopping list.
 * @author Chang Zhou
 */
public class Item {
  private String furnitureName;
  private Furniture furniture;
  private int count = 0;
  private double totalPrice = 0;

  /**
   * return this obj's string representation.
   * @return obj's string representation
   */
  @Override
  public String toString() {
    return "{" +
        "\"furnitureName\": " + "\"" + furnitureName + "\"" +
        ", \"count\": " + count +
        ", \"totalPrice\": " + totalPrice +
        "}";
  }

  /**
   * check if two objs are equals.
   * @param object object
   * @return true/false
   */
  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    final Item item = (Item) object;
    return getCount() == item.getCount() &&
        Double.compare(item.getTotalPrice(), getTotalPrice()) == 0 &&
        Objects.equals(getFurnitureName(), item.getFurnitureName()) &&
        Objects.equals(getFurniture(), item.getFurniture());
  }

  /**
   * calculate this obj's hashcode.
   * @return hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getFurnitureName(), getFurniture(), getCount(), getTotalPrice());
  }

  /**
   * get the furniture obj in this item data obj.
   * @return furniture obj
   */
  public Furniture getFurniture() {
    return furniture;
  }

  /**
   * set furniture obj.
   * @param furniture furniture obj
   */
  public void setFurniture(final Furniture furniture) {
    this.furniture = furniture;
  }

  /**
   * get the furniture obj's class name.
   * @return furniture obj's class name
   */
  public String getFurnitureName() {
    return furnitureName;
  }

  /**
   * set the furniture obj's class name.
   * @param furnitureName the furniture obj's class name
   */
  public void setFurnitureName(final String furnitureName) {
    this.furnitureName = furnitureName;
  }

  /**
   * get the num of this furniture obj.
   * @return num of this furniture obj
   */
  public int getCount() {
    return count;
  }

  /**
   * set the number of this furniture obj.
   * @param count the number of this furniture obj
   */
  public void setCount(final int count) {
    this.count = count;
  }

  /**
   * get the total price for these furniture.
   * @return total price
   */
  public double getTotalPrice() {
    return totalPrice;
  }

  /**
   * set the total price for these furniture.
   * @param totalPrice the total price
   */
  public void setTotalPrice(final double totalPrice) {
    this.totalPrice = totalPrice;
  }
}
