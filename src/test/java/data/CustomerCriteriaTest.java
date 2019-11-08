package data;

import common.FileHelper;
import enums.ColorType;
import enums.MountType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * test class for CustomerCriteria.
 * @author Chang Zhou
 */
public class CustomerCriteriaTest {
  private CustomerCriteria customerCriteria;

  @Before
  public void setUp() throws Exception {
    customerCriteria = new CustomerCriteria(FileHelper.readJsonFile(
        "src/main/resources/customer_criteria.json"));
  }

  @Test
  public void testToString() {
    Assert.assertEquals("CustomerCriteria{desiredWidth=355, desiredHeight=36,"
        + " desiredShelves=3, desiredDrawers=0, desiredColor=null, desiredMountType=FLOOR,"
        + " budget=2999.0}", customerCriteria.toString());
    final CustomerCriteria customerCriteria1 = new CustomerCriteria(FileHelper.readJsonFile(
        "src/main/resources/customer_criteria2.json"));
    Assert.assertEquals("CustomerCriteria{desiredWidth=355, desiredHeight=36,"
        + " desiredShelves=3, desiredDrawers=0, desiredColor=BLACK, desiredMountType=WALL,"
        + " budget=2999.0}", customerCriteria1.toString());
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(customerCriteria, customerCriteria);
  }

  @Test
  public void testEquals1() {
    Assert.assertNotEquals(customerCriteria, null);
  }

  @Test
  public void testEquals2() {
    Assert.assertNotEquals(null, customerCriteria);
  }

  @Test
  public void testEquals3() {
    Assert.assertNotEquals(customerCriteria, 1);
  }

  @Test
  public void testEquals4() {
    final CustomerCriteria tmpCustomerCriteria = new CustomerCriteria(FileHelper.readJsonFile(
        "src/main/resources/customer_criteria.json"));
    tmpCustomerCriteria.setDesiredHeight(22);
    Assert.assertNotEquals(customerCriteria, tmpCustomerCriteria);
  }

  @Test
  public void testEquals5() {
    final CustomerCriteria tmpCustomerCriteria = new CustomerCriteria(FileHelper.readJsonFile(
        "src/main/resources/customer_criteria.json"));
    tmpCustomerCriteria.setDesiredWidth(223);
    Assert.assertNotEquals(customerCriteria, tmpCustomerCriteria);
  }

  @Test
  public void testEquals6() {
    final CustomerCriteria tmpCustomerCriteria = new CustomerCriteria(FileHelper.readJsonFile(
        "src/main/resources/customer_criteria.json"));
    tmpCustomerCriteria.setBudget(99);
    Assert.assertNotEquals(customerCriteria, tmpCustomerCriteria);
  }

  @Test
  public void testEquals7() {
    final CustomerCriteria tmpCustomerCriteria = new CustomerCriteria(FileHelper.readJsonFile(
        "src/main/resources/customer_criteria.json"));
    tmpCustomerCriteria.setDesiredShelves(99);
    Assert.assertNotEquals(customerCriteria, tmpCustomerCriteria);
  }

  @Test
  public void testEquals8() {
    final CustomerCriteria tmpCustomerCriteria = new CustomerCriteria(FileHelper.readJsonFile(
        "src/main/resources/customer_criteria.json"));
    tmpCustomerCriteria.setDesiredDrawers(99);
    Assert.assertNotEquals(customerCriteria, tmpCustomerCriteria);
  }

  @Test
  public void testEquals9() {
    final CustomerCriteria tmpCustomerCriteria = new CustomerCriteria(FileHelper.readJsonFile(
        "src/main/resources/customer_criteria.json"));
    tmpCustomerCriteria.setDesiredColor(ColorType.OXBLOOD);
    Assert.assertNotEquals(customerCriteria, tmpCustomerCriteria);
  }

  @Test
  public void testEquals10() {
    final CustomerCriteria tmpCustomerCriteria = new CustomerCriteria(FileHelper.readJsonFile(
        "src/main/resources/customer_criteria.json"));
    tmpCustomerCriteria.setDesiredMountType(MountType.WALL);
    Assert.assertNotEquals(customerCriteria, tmpCustomerCriteria);
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(customerCriteria.hashCode(), customerCriteria.hashCode());
  }


  @Test
  public void convertJsonStr2CustomerCriteria() {
    Assert.assertNotNull(customerCriteria);
  }

  @Test
  public void getDesiredWidth() {
    Assert.assertEquals(355, customerCriteria.getDesiredWidth());
  }

  @Test
  public void setDesiredWidth() {
    customerCriteria.setDesiredWidth(345);
    Assert.assertEquals(345, customerCriteria.getDesiredWidth());
  }

  @Test
  public void getDesiredHeight() {
    Assert.assertEquals(36, customerCriteria.getDesiredHeight());
  }

  @Test
  public void setDesiredHeight() {
    customerCriteria.setDesiredHeight(72);
    Assert.assertEquals(72, customerCriteria.getDesiredHeight());
  }

  @Test
  public void getDesiredShelves() {
    Assert.assertEquals(3, customerCriteria.getDesiredShelves());
  }

  @Test
  public void setDesiredShelves() {
    customerCriteria.setDesiredShelves(4);
    Assert.assertEquals(4, customerCriteria.getDesiredShelves());
  }

  @Test
  public void getDesiredDrawers() {
    Assert.assertEquals(0, customerCriteria.getDesiredDrawers());
  }

  @Test
  public void setDesiredDrawers() {
    customerCriteria.setDesiredDrawers(3);
    Assert.assertEquals(3, customerCriteria.getDesiredDrawers());
  }

  @Test
  public void getDesiredColor() {
    Assert.assertNull(customerCriteria.getDesiredColor());
  }

  @Test
  public void setDesiredColor() {
    customerCriteria.setDesiredColor(ColorType.BROWN);
    Assert.assertEquals(ColorType.BROWN, customerCriteria.getDesiredColor());
  }

  @Test
  public void getDesiredMountType() {
    Assert.assertEquals(MountType.FLOOR, customerCriteria.getDesiredMountType());
  }

  @Test
  public void setDesiredMountType() {
    customerCriteria.setDesiredMountType(MountType.FLOOR);
    Assert.assertEquals(MountType.FLOOR, customerCriteria.getDesiredMountType());
  }

  @Test
  public void getBudget() {
    Assert.assertEquals("2999.0", customerCriteria.getBudget() + "");
  }

  @Test
  public void setBudget() {
    customerCriteria.setBudget(33333);
    Assert.assertEquals("33333.0", customerCriteria.getBudget() + "");
  }
}