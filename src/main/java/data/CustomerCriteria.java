package data;

import enums.ColorType;
import enums.MountType;
import java.util.Objects;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * data object. store the data from customerCriteria.
 * @author Chang Zhou
 */
public class CustomerCriteria {
  private int desiredWidth;
  private int desiredHeight;
  private int desiredShelves;
  private int desiredDrawers;
  private ColorType desiredColor;
  private MountType desiredMountType;
  private double budget;

  /**
   * constructor.
   * @param jsonStr the input json str from file
   */
  public CustomerCriteria(final String jsonStr) {
    this.convertJsonStr2CustomerCriteria(jsonStr);
  }

  /**
   * convert this obj to string.
   * @return string
   */
  @Override
  public String toString() {
    return "CustomerCriteria{" +
        "desiredWidth=" + desiredWidth +
        ", desiredHeight=" + desiredHeight +
        ", desiredShelves=" + desiredShelves +
        ", desiredDrawers=" + desiredDrawers +
        ", desiredColor=" + desiredColor +
        ", desiredMountType=" + desiredMountType +
        ", budget=" + budget +
        '}';
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
    final CustomerCriteria that = (CustomerCriteria) object;
    return getDesiredWidth() == that.getDesiredWidth() &&
        getDesiredHeight() == that.getDesiredHeight() &&
        getDesiredShelves() == that.getDesiredShelves() &&
        getDesiredDrawers() == that.getDesiredDrawers() &&
        Double.compare(that.getBudget(), getBudget()) == 0 &&
        getDesiredColor() == that.getDesiredColor() &&
        getDesiredMountType() == that.getDesiredMountType();
  }

  /**
   * calculate this obj's hashcode.
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(getDesiredWidth(), getDesiredHeight(), getDesiredShelves(), getDesiredDrawers(),
            getDesiredColor(), getDesiredMountType(), getBudget());
  }

  /**
   * invoked by constructor. convert the input string to javabean.
   * @param jsonStr input json string
   */
  public void convertJsonStr2CustomerCriteria(final String jsonStr) {
    final JSONParser jsonParser = new JSONParser();
    try {
      final JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonStr);
      this.desiredWidth = ((Number) jsonObject.get("desiredWidth")).intValue();
      this.desiredHeight = ((Number) jsonObject.get("desiredHeight")).intValue();
      this.desiredShelves = ((Number) jsonObject.get("desiredShelves")).intValue();
      this.desiredDrawers = ((Number) jsonObject.get("desiredDrawers")).intValue();
      this.desiredColor = jsonObject.get("desiredColor") == null ? null :
          ColorType.valueOf((String) jsonObject.get("desiredColor"));
      this.desiredMountType = MountType.valueOf((String) jsonObject.get("desiredMountType"));
      this.budget = ((Number)jsonObject.get("budget")).doubleValue();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * return desiredWidth.
   * @return desiredWidth
   */
  public int getDesiredWidth() {
    return desiredWidth;
  }

  /**
   * set desiredWidth.
   * @param desiredWidth desiredWidth
   */
  public void setDesiredWidth(final int desiredWidth) {
    this.desiredWidth = desiredWidth;
  }

  /**
   * return desiredHeight.
   * @return desiredHeight
   */
  public int getDesiredHeight() {
    return desiredHeight;
  }

  /**
   * set desiredHeight.
   * @param desiredHeight desiredHeight
   */
  public void setDesiredHeight(final int desiredHeight) {
    this.desiredHeight = desiredHeight;
  }

  /**
   * return desiredShelves.
   * @return desiredShelves
   */
  public int getDesiredShelves() {
    return desiredShelves;
  }

  /**
   * set desiredShelves.
   * @param desiredShelves desiredShelves
   */
  public void setDesiredShelves(final int desiredShelves) {
    this.desiredShelves = desiredShelves;
  }

  /**
   * return desiredDrawers.
   * @return desiredDrawers
   */
  public int getDesiredDrawers() {
    return desiredDrawers;
  }

  /**
   * set desiredDrawers.
   * @param desiredDrawers desiredDrawers
   */
  public void setDesiredDrawers(final int desiredDrawers) {
    this.desiredDrawers = desiredDrawers;
  }

  /**
   * return desiredColor.
   * @return desiredColor
   */
  public ColorType getDesiredColor() {
    return desiredColor;
  }

  /**
   * set desiredColor.
   * @param desiredColor desiredDrawers
   */
  public void setDesiredColor(final ColorType desiredColor) {
    this.desiredColor = desiredColor;
  }

  /**
   * return desiredMountType.
   * @return desiredMountType
   */
  public MountType getDesiredMountType() {
    return desiredMountType;
  }

  /**
   * set desiredMountType.
   * @param desiredMountType desiredMountType
   */
  public void setDesiredMountType(final MountType desiredMountType) {
    this.desiredMountType = desiredMountType;
  }

  /**
   * return budget.
   * @return budget
   */
  public double getBudget() {
    return budget;
  }

  /**
   * set budget.
   * @param budget budget
   */
  public void setBudget(final double budget) {
    this.budget = budget;
  }
}
