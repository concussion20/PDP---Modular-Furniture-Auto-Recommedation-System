package main;

import enums.ColorType;
import enums.CoverType;
import enums.FurnitureType;
import enums.HandleType;
import enums.MountType;
import enums.RailType;
import interfaces.CabinetWithDrawer;
import interfaces.MultiMountTypesCabinet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.json.simple.JSONObject;

/**
 * Represents the furniture.
 * @author Chang Zhou
 */
public class Furniture implements MultiMountTypesCabinet, CabinetWithDrawer {
  //general props
  private double price;
  private String name;
  private FurnitureType furnitureType;
  private ColorType colorType;
  private int width;
  private int height;

  //cover(door, drawer front) props
  private boolean isHandleNeeded;
  private CoverType coverType;

  //cabinet props
  private int depth;
  private boolean isFloorMounted;
  private int isFeetRequired;
  private boolean isWallFixtureNeeded;
  private boolean isRailRequired;
  private int maxShelves;
  private int maxDrawers;
  private Set<MountType> availableMountTypes;
  private static final int FEET_NOT_NEEDED = 0;
  private static final int FEET_OPTIONAL = 1;
  private static final int FEET_NEEDED = 2;

  //cover and cabinet props
  private Set<ColorType> availableColors;

  //rail and cabinet props
  private RailType railType;

  //handle props
  private HandleType handleType;
  private static final String PRICE = "price";
  private static final String NAME = "name";
  private static final String FURNITURE_TYPE = "furnitureType";
  private static final String COLOR_TYPE = "colorType";
  private static final String WIDTH = "width";
  private static final String HEIGHT = "height";
  private static final String IS_HANDLE_NEEDED = "isHandleNeeded";
  private static final String COVER_TYPE = "coverType";
  private static final String DEPTH = "depth";
  private static final String IS_FLOOR_MOUNTED = "isFloorMounted";
  private static final String IS_FEET_REQUIRED = "isFeetRequired";
  private static final String IS_WALL_FIXTURE_NEEDED = "isWallFixtureNeeded";
  private static final String IS_RAIL_REQUIRED = "isRailRequired";
  private static final String MAX_SHELVES = "maxShelves";
  private static final String MAX_DRAWERS = "maxDrawers";
  private static final String AVAILABLE_MOUNT_TYPES = "availableMountTypes";
  private static final String AVAILABLE_COLORS = "availableColors";
  private static final String RAIL_TYPE = "railType";
  private static final String HANDLE_TYPE = "handleType";
  private static final String ATREIDES = "Atreides";
  private static final String HALF = "Half";
  private static final String QUARTER = "Quarter";
  private static final String LUTHIEN_HALF = "LuthienHalf";

  /**
   * constructor, should be instantiates using a jsonObj.
   * @param jsonObject jsonObject
   */
  public Furniture(final JSONObject jsonObject) {
    //set general props
    if (jsonObject.get(PRICE) != null) {
      this.setPrice(Double.parseDouble(jsonObject.get(PRICE).toString()));
    }
    if (jsonObject.get(NAME) != null) {
      this.setName(jsonObject.get(NAME).toString());
    }
    if (jsonObject.get(FURNITURE_TYPE) != null) {
      this.setFurnitureType(FurnitureType.valueOf(jsonObject.get(FURNITURE_TYPE).toString()));
    }
    if (jsonObject.get(COLOR_TYPE) != null) {
      this.setColorType(ColorType.valueOf(jsonObject.get(COLOR_TYPE).toString()));
    }
    if (jsonObject.get(WIDTH) != null) {
      this.setWidth(Integer.parseInt(jsonObject.get(WIDTH).toString()));
    }
    if (jsonObject.get(HEIGHT) != null) {
      this.setHeight(Integer.parseInt(jsonObject.get(HEIGHT).toString()));
    }
    //set cover(door, drawer front) props
    if (jsonObject.get(IS_HANDLE_NEEDED) != null) {
      this.setHandleNeeded(Boolean.parseBoolean(jsonObject.get(IS_HANDLE_NEEDED).toString()));
    }
    if (jsonObject.get(COVER_TYPE) != null) {
      this.setCoverType(CoverType.valueOf(jsonObject.get(COVER_TYPE).toString()));
    }
    //set cabinet props
    if (jsonObject.get(DEPTH) != null) {
      this.setDepth(Integer.parseInt(jsonObject.get(DEPTH).toString()));
    }
    if (jsonObject.get(IS_FLOOR_MOUNTED) != null) {
      this.setFloorMounted(Boolean.parseBoolean(jsonObject.get(IS_FLOOR_MOUNTED).toString()));
    }
    if (jsonObject.get(IS_FEET_REQUIRED) != null) {
      this.setIsFeetRequired(Integer.parseInt(jsonObject.get(IS_FEET_REQUIRED).toString()));
    }
    if (jsonObject.get(IS_WALL_FIXTURE_NEEDED) != null) {
      this.setWallFixtureNeeded(Boolean.parseBoolean(jsonObject.
          get(IS_WALL_FIXTURE_NEEDED).toString()));
    }
    if (jsonObject.get(IS_RAIL_REQUIRED) != null) {
      this.setRailRequired(Boolean.parseBoolean(jsonObject.
          get(IS_RAIL_REQUIRED).toString()));
    }
    if (jsonObject.get(MAX_SHELVES) != null) {
      this.setMaxShelves(Integer.parseInt(jsonObject.get(MAX_SHELVES).toString()));
    }
    if (jsonObject.get(MAX_DRAWERS) != null) {
      this.setMaxDrawers(Integer.parseInt(jsonObject.get(MAX_DRAWERS).toString()));
    }
    if (jsonObject.get(AVAILABLE_MOUNT_TYPES) != null) {
      final String availableMountTypes = jsonObject.get(AVAILABLE_MOUNT_TYPES).toString();
      final String[] strings = availableMountTypes.split(", ");
      MountType[] mountTypes = new MountType[strings.length];
      for (int i = 0; i < strings.length; i++) {
        mountTypes[i] = MountType.valueOf(strings[i]);
      }
      final List<MountType> list = Arrays.asList(mountTypes);
      this.setAvailableMountTypes(new HashSet<MountType>(list));
    }
    //set cover and cabinet props
    if (jsonObject.get(AVAILABLE_COLORS) != null) {
      final String availableColors = jsonObject.get(AVAILABLE_COLORS).toString();
      final String[] strings = availableColors.split(", ");
      ColorType[] colorTypes = new ColorType[strings.length];
      for (int i = 0; i < strings.length; i++) {
        colorTypes[i] = ColorType.valueOf(strings[i]);
      }
      final List<ColorType> list = Arrays.asList(colorTypes);
      this.setAvailableColors(new HashSet<ColorType>(list));
    }
    //set handle type
    if (jsonObject.get(RAIL_TYPE) != null) {
      this.setRailType(RailType.valueOf(jsonObject.get(RAIL_TYPE).toString()));
    }
    if (jsonObject.get(HANDLE_TYPE) != null) {
      this.setHandleType(HandleType.valueOf(jsonObject.get(HANDLE_TYPE).toString()));
    }
  }

  /**
   * check if two objs are equal.
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
    final Furniture furniture = (Furniture) object;
    return Double.compare(furniture.getPrice(), getPrice()) == 0 &&
        getWidth() == furniture.getWidth() &&
        getHeight() == furniture.getHeight() &&
        isHandleNeeded() == furniture.isHandleNeeded() &&
        getDepth() == furniture.getDepth() &&
        isFloorMounted() == furniture.isFloorMounted() &&
        getIsFeetRequired() == furniture.getIsFeetRequired() &&
        isWallFixtureNeeded() == furniture.isWallFixtureNeeded() &&
        isRailRequired() == furniture.isRailRequired() &&
        getMaxShelves() == furniture.getMaxShelves() &&
        getMaxDrawers() == furniture.getMaxDrawers() &&
        getFurnitureType() == furniture.getFurnitureType() &&
        getColorType() == furniture.getColorType() &&
        getCoverType() == furniture.getCoverType() &&
        Objects.equals(getAvailableColors(), furniture.getAvailableColors()) &&
        Objects.equals(getAvailableMountTypes(), furniture.getAvailableMountTypes()) &&
        getHandleType() == furniture.getHandleType() &&
        getRailType() == furniture.getRailType();
  }

  /**
   * calculate the hashcode of the obj.
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(getPrice(), getFurnitureType(), getColorType(), getWidth(), getHeight(),
        isHandleNeeded(), getCoverType(), getAvailableColors(), getDepth(), isFloorMounted(),
        getIsFeetRequired(), isWallFixtureNeeded(), isRailRequired(), getMaxShelves(),
        getMaxDrawers(), getAvailableMountTypes(), getHandleType(), getRailType());
  }

  /**
   * convert this obj to string.
   * @return string
   */
  @Override
  public String toString() {
    return "Furniture{" +
        "price=" + price +
        ", furnitureType=" + furnitureType +
        ", colorType=" + colorType +
        ", width=" + width +
        ", height=" + height +
        ", isHandleNeeded=" + isHandleNeeded +
        ", coverType=" + coverType +
        ", availableColors=" + availableColors +
        ", depth=" + depth +
        ", isFloorMounted=" + isFloorMounted +
        ", isFeetRequired=" + isFeetRequired +
        ", isWallFixtureNeeded=" + isWallFixtureNeeded +
        ", isRailRequired=" + isRailRequired +
        ", maxShelves=" + maxShelves +
        ", maxDrawers=" + maxDrawers +
        ", availableMountTypes=" + availableMountTypes +
        ", handleType=" + handleType +
        ", railType=" + railType +
        '}';
  }

  /**
   * getPrice.
   * @return price
   */
  public double getPrice() {
    return price;
  }

  /**
   * set a new price.
   * @param price price
   */
  public void setPrice(final double price) {
    this.price = price;
  }

  /**
   * getName.
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * set a new name.
   * @param name name
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * getFurnitureType.
   * @return furnitureType
   */
  public FurnitureType getFurnitureType() {
    return furnitureType;
  }

  /**
   * set new furnitureType.
   * @param furnitureType furnitureType
   */
  public void setFurnitureType(final FurnitureType furnitureType) {
    this.furnitureType = furnitureType;
  }

  /**
   * getColorType.
   * @return ColorType
   */
  public ColorType getColorType() {
    return colorType;
  }

  /**
   * set new colorType.
   * @param colorType colorType
   */
  public void setColorType(final ColorType colorType) {
    this.colorType = colorType;
  }

  /**
   * getWidth
   * @return width
   */
  public int getWidth() {
    return width;
  }

  /**
   * set a new width.
   * @param width width
   */
  public void setWidth(final int width) {
    this.width = width;
  }

  /**
   * getHeight.
   * @return height
   */
  public int getHeight() {
    return height;
  }

  /**
   * set a new height.
   * @param height height
   */
  public void setHeight(final int height) {
    this.height = height;
  }

  /**
   * isHandleNeeded.
   * @return isHandleNeeded
   */
  public boolean isHandleNeeded() {
    return isHandleNeeded;
  }

  /**
   * set handleNeeded.
   * @param handleNeeded handleNeeded
   */
  public void setHandleNeeded(final boolean handleNeeded) {
    isHandleNeeded = handleNeeded;
  }

  /**
   * getCoverType.
   * @return coverType
   */
  public CoverType getCoverType() {
    return coverType;
  }

  /**
   * set a new cover type.
   * @param coverType coverType
   */
  public void setCoverType(final CoverType coverType) {
    this.coverType = coverType;
  }

  /**
   * getAvailableColors.
   * @return availableColors
   */
  public Set<ColorType> getAvailableColors() {
    return availableColors;
  }

  /**
   * set new availableColors.
   * @param availableColors availableColors
   */
  public void setAvailableColors(final Set<ColorType> availableColors) {
    this.availableColors = availableColors;
  }

  /**
   * getDepth.
   * @return depth
   */
  public int getDepth() {
    return depth;
  }

  /**
   * set a new depth.
   * @param depth depth
   */
  public void setDepth(final int depth) {
    this.depth = depth;
  }

  /**
   * isFloorMounted.
   * @return isFloorMounted
   */
  public boolean isFloorMounted() {
    return isFloorMounted;
  }

  /**
   * set isFloorMounted.
   * @param floorMounted floorMounted
   */
  public void setFloorMounted(final boolean floorMounted) {
    isFloorMounted = floorMounted;
  }

  /**
   * getIsFeetRequired.
   * @return isFeetRequired
   */
  public int getIsFeetRequired() {
    return isFeetRequired;
  }

  /**
   * set isFeetRequired.
   * @param isFeetRequired isFeetRequired
   */
  public void setIsFeetRequired(final int isFeetRequired) {
    this.isFeetRequired = isFeetRequired;
  }

  /**
   * isWallFixtureNeeded.
   * @return isWallFixtureNeeded
   */
  public boolean isWallFixtureNeeded() {
    return isWallFixtureNeeded;
  }

  /**
   * set isWallFixtureNeeded.
   * @param wallFixtureNeeded isWallFixtureNeeded
   */
  public void setWallFixtureNeeded(final boolean wallFixtureNeeded) {
    isWallFixtureNeeded = wallFixtureNeeded;
  }

  /**
   * isRailRequired.
   * @return isRailRequired
   */
  public boolean isRailRequired() {
    return isRailRequired;
  }

  /**
   * set isRailRequired.
   * @param railRequired isRailRequired
   */
  public void setRailRequired(final boolean railRequired) {
    isRailRequired = railRequired;
  }

  /**
   * getMaxShelves.
   * @return maxShelves
   */
  public int getMaxShelves() {
    return maxShelves;
  }

  /**
   * set new maxShelves.
   * @param maxShelves maxShelves
   */
  public void setMaxShelves(final int maxShelves) {
    this.maxShelves = maxShelves;
  }

  /**
   * getMaxDrawers.
   * @return maxDrawers
   */
  public int getMaxDrawers() {
    return maxDrawers;
  }

  /**
   * set maxDrawers.
   * @param maxDrawers maxDrawers
   */
  public void setMaxDrawers(final int maxDrawers) {
    this.maxDrawers = maxDrawers;
  }

  /**
   * getAvailableMountTypes.
   * @return availableMountTypes
   */
  public Set<MountType> getAvailableMountTypes() {
    return availableMountTypes;
  }

  /**
   * setAvailableMountTypes.
   * @param availableMountTypes availableMountTypes
   */
  public void setAvailableMountTypes(final Set<MountType> availableMountTypes) {
    this.availableMountTypes = availableMountTypes;
  }

  /**
   * getHandleType.
   * @return handleType
   */
  public HandleType getHandleType() {
    return handleType;
  }

  /**
   * set a new handleType.
   * @param handleType handleType
   */
  public void setHandleType(final HandleType handleType) {
    this.handleType = handleType;
  }

  /**
   * getRailType.
   * @return railType
   */
  public RailType getRailType() {
    return railType;
  }

  /**
   * set a new railType.
   * @param railType railType
   */
  public void setRailType(final RailType railType) {
    this.railType = railType;
  }

  /**
   * change to wall-mounted-mode.
   */
  @Override
  public void change2WallMounted() {
    this.setFloorMounted(false);
    this.setRailRequired(true);
    //Rail Type:
    //Atreides: Atreides type
    //others: standard
    if (getName().contains(ATREIDES)) {
      this.setRailType(RailType.ATREIDES);
    } else {
      this.setRailType(RailType.STANDARD);
    }
    this.setIsFeetRequired(FEET_NOT_NEEDED);
  }

  /**
   * change to floor-mounted mode.
   */
  @Override
  public void change2FloorMounted() {
    this.setFloorMounted(true);
    this.setRailRequired(false);
    this.setRailType(null);
    if (getName().contains(HALF)) {
      this.setIsFeetRequired(FEET_OPTIONAL);
    } else if (getName().contains(QUARTER)) {
      this.setIsFeetRequired(FEET_NEEDED);
    } else {
      this.setIsFeetRequired(FEET_NOT_NEEDED);
    }
  }

  /**
   * change to with drawer mode.
   */
  @Override
  public void change2WithDrawer() {
    if (getName().equals(LUTHIEN_HALF)) {
      this.setMaxDrawers(1);
      this.setMaxShelves(2);
    } else {
      this.setMaxDrawers(1);
      this.setMaxShelves(0);
    }
  }

  /**
   * change to without drawer mode.
   */
  @Override
  public void change2WithoutDrawer() {
    if (getName().equals(LUTHIEN_HALF)) {
      this.setMaxDrawers(0);
      this.setMaxShelves(3);
    } else {
      this.setMaxDrawers(0);
      this.setMaxShelves(1);
    }
  }
}
