package main;

import common.FileHelper;
import data.CustomerCriteria;
import data.Item;
import data.ShoppingList;
import enums.ColorType;
import enums.FurnitureType;
import enums.MountType;
import enums.RailType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Provide the recommendation logic.
 * @author Chang Zhou
 */
public class Recommender {
  private Map<String, List<Furniture>> cabsAndCovers;
  private Map<String, Furniture> otherFurniture;
  private static final String FURNITURE_FILE_PATH = "src/main/resources/furniture.json";
  private static final String CC_FILE_PATH = "src/main/resources/customer_criteria.json";
  private static final String OUTPUT_FILE_PATH = "src/main/resources/shoppingLists.json";
  private static final String WALL_FIXTURE = "WallFixture";
  private static final String RAIL_STANDARD = "RailStandard";
  private static final String RAIL_ATREIDES = "RailAtreides";
  private static final String CORNER_FOOT = "CornerFoot";
  private static final String DRAWER = "Drawer";
  private static final String HANDLE_DRAWER = "HandleDrawer";
  private static final String GAGA_QUARTER_DRAWER_FRONT = "GagaQuarterDrawerFront";
  private static final String DOOR_HINGES = "DoorHinges";
  private static final String HANDLE_DOOR = "HandleDoor";
  private static final String LUTHIEN = "Luthien";

  /**
   * Constructor.
   * @param furnitureFilePath the path of the input file: prices
   * @throws ParseException Parse Json Exception
   */
  public Recommender(final String furnitureFilePath) throws ParseException {
    cabsAndCovers = new HashMap<>();
    otherFurniture = new HashMap<>();
    cabsAndCovers.put(FurnitureType.CABINET.toString(), new ArrayList<Furniture>());
    cabsAndCovers.put(FurnitureType.COVER.toString(), new ArrayList<Furniture>());
    loadFurniture(furnitureFilePath);
  }

  /**
   * getCabsAndCovers.
   * @return cabsAndCovers
   */
  public Map<String, List<Furniture>> getCabsAndCovers() {
    return cabsAndCovers;
  }

  /**
   * setCabsAndCovers.
   * @param cabsAndCovers cabsAndCovers
   */
  public void setCabsAndCovers(final Map<String, List<Furniture>> cabsAndCovers) {
    this.cabsAndCovers = cabsAndCovers;
  }

  /**
   * getOtherFurniture.
   * @return otherFurniture
   */
  public Map<String, Furniture> getOtherFurniture() {
    return otherFurniture;
  }

  /**
   * setOtherFurniture.
   * @param otherFurniture otherFurniture
   */
  public void setOtherFurniture(final Map<String, Furniture> otherFurniture) {
    this.otherFurniture = otherFurniture;
  }

  /**
   * invoked by constructor. read price file, set the price for every furniture
   * @param furnitureFilePath path of the price file
   * @throws ParseException Parse Json Exception
   */
  public void loadFurniture(final String furnitureFilePath) throws ParseException {
    final String furnitureJsonStr = FileHelper.readJsonFile(furnitureFilePath);
    //set the furniture map
    final JSONParser jsonParser = new JSONParser();
    final JSONArray jsonArray = (JSONArray) jsonParser.parse(furnitureJsonStr);
    for (final Object object: jsonArray.toArray()) {
      final JSONObject jsonObject = (JSONObject)object;
      final Furniture furniture = new Furniture(jsonObject);
      if (furniture.getFurnitureType().equals(FurnitureType.CABINET)
          || furniture.getFurnitureType().equals(FurnitureType.COVER)) {
        final List<Furniture> list = cabsAndCovers.get(furniture.getFurnitureType().toString());
        list.add(furniture);
      } else {
        otherFurniture.put(furniture.getName(), furniture);
      }
    }
  }

  /**
   * load CustomerCriteria from json file.
   * @param ccFilePath ccFilePath
   * @return CustomerCriteria obj
   */
  public CustomerCriteria loadCustomerCriteria(final String ccFilePath) {
    final String ccJsonStr = getCustomerCriteriaJsonStr(ccFilePath);
    return new CustomerCriteria(ccJsonStr);
  }

  /**
   * implement the commendation logic, receive input and output a set of shoppingList.
   */
  public void recommend() {
    final CustomerCriteria customerCriteria = loadCustomerCriteria(CC_FILE_PATH);
    //calculate
    Set<ShoppingList> finalShoppingListsSet = new TreeSet<>(new Comparator<ShoppingList>() {
      @Override
      public int compare(final ShoppingList o1, final ShoppingList o2) {
        if (o1.getTotal() < o2.getTotal()) {
          return -1;
        } else if (o1.getTotal() > o2.getTotal()) {
          return 1;
        }
        return 0;
      }
    });

    //if user doesn't specify color, try every possible color
    final boolean isColorSpecified = customerCriteria.getDesiredColor() != null;
    for (final ColorType colorType: ColorType.values()) {
      final ColorType combinationColor = isColorSpecified ? customerCriteria.getDesiredColor() :
          colorType;
      final Set<ShoppingList> shoppingListsWithoutCover = getAllCabinets();
      for (final ShoppingList shoppingListWithoutCover: shoppingListsWithoutCover) {
        //get every cabinet, check if it matches customer criteria
        final Item itemCabinet = shoppingListWithoutCover.getList().get(0);
        final Furniture cabinet = itemCabinet.getFurniture();
        if (!cabinet.getAvailableColors().contains(combinationColor)
            || !cabinet.getAvailableMountTypes().contains(customerCriteria.getDesiredMountType())
            || cabinet.getHeight() != customerCriteria.getDesiredHeight()) {
          continue;
        }
        cabinet.setColorType(combinationColor);

        //if drawers or shelves don't match
        final int cabinetCnt = customerCriteria.getDesiredWidth() / cabinet.getWidth();
        itemCabinet.setCount(cabinetCnt);
        itemCabinet.setTotalPrice(cabinet.getPrice() * cabinetCnt);
        if (cabinet.getName().contains(LUTHIEN)) {
          boolean isFirstMatch = false;
          cabinet.change2WithDrawer();
          if (checkIsDrawersShelvesMatch(cabinet.getMaxShelves(), cabinet.getMaxDrawers(),
              cabinetCnt, customerCriteria.getDesiredShelves(),
              customerCriteria.getDesiredDrawers())) {
            isFirstMatch = true;
          }
          if (!isFirstMatch) {
            cabinet.change2WithoutDrawer();
          }
          if (!isFirstMatch && !checkIsDrawersShelvesMatch(cabinet.getMaxShelves(),
              cabinet.getMaxDrawers(), cabinetCnt, customerCriteria.getDesiredShelves(),
              customerCriteria.getDesiredDrawers())) {
            continue;
          }
        } else {
          if (!checkIsDrawersShelvesMatch(cabinet.getMaxShelves(), cabinet.getMaxDrawers(),
              cabinetCnt, customerCriteria.getDesiredShelves(),
              customerCriteria.getDesiredDrawers())) {
            continue;
          }
        }

        //add wallFixture if needed
        if (cabinet.isWallFixtureNeeded()) {
          shoppingListWithoutCover.addItem(getWallFixture(cabinetCnt));
        }

        //change to user specified mount type
        if (customerCriteria.getDesiredMountType().equals(MountType.WALL)) {
          cabinet.change2WallMounted();
        } else {
          cabinet.change2FloorMounted();
        }
        //add rails if needed
        if (customerCriteria.getDesiredMountType().equals(MountType.WALL)
            && cabinet.isRailRequired()) {
          if (RailType.STANDARD.equals(cabinet.getRailType())) {
            shoppingListWithoutCover.addItem(getStandardRail(cabinetCnt));
          } else {
            shoppingListWithoutCover.addItem(getAtreidesRail(cabinetCnt));
          }
        }

        //add corner feet
        if (customerCriteria.getDesiredMountType().equals(MountType.FLOOR)
            && cabinet.getIsFeetRequired() == 2) {
          final int feetNum = (cabinetCnt - 1) / 2 * 2 + 4;
          shoppingListWithoutCover.addItem(getCornerFeet(feetNum));
        }

        //add drawers if needed
        if (customerCriteria.getDesiredDrawers() > 0) {
          shoppingListWithoutCover.addItem(getDrawer(customerCriteria.getDesiredDrawers()));
          shoppingListWithoutCover.addItem(getHandleDrawer(customerCriteria.getDesiredDrawers()));
          shoppingListWithoutCover.addItem(getDrawerFront(customerCriteria.getDesiredDrawers()));
        }

        //add covers(doors/drawer fronts), one cabinet can match several covers
        finalShoppingListsSet.addAll(getShoppingListsWithCover(customerCriteria, cabinetCnt,
            shoppingListWithoutCover, combinationColor));
      }
      if (isColorSpecified) {
        break;
      }
    }
    //convert to jsonStr
    final String listsJsonStr = convertFinalSLs2JsonString(finalShoppingListsSet);
    outputShoppingListJsonStr(listsJsonStr, OUTPUT_FILE_PATH);
  }

  /**
   * invoked by recommend method, to check if the drawers and shelves requirements from customer
   * can be met.
   * @param maxSelves maxSelves
   * @param maxDrawers maxDrawers
   * @param cabinetCnt cabinetCnt
   * @param desiredShelves desiredShelves
   * @param desiredDrawers desiredDrawers
   * @return true/false
   */
  public boolean checkIsDrawersShelvesMatch(final int maxSelves, final int maxDrawers,
      final int cabinetCnt, final int desiredShelves, final int desiredDrawers) {
    return maxDrawers * cabinetCnt >= desiredDrawers && maxSelves * cabinetCnt >= desiredShelves;
  }

  /**
   * get number of cnt wall fixtures.
   * @param cnt cnt
   * @return wallFixtures
   */
  public Item getWallFixture(final int cnt) {
    final Item itemWallFixture = new Item();
    itemWallFixture.setFurnitureName(WALL_FIXTURE);
    itemWallFixture.setFurniture(otherFurniture.get(WALL_FIXTURE));
    itemWallFixture.setCount(cnt);
    itemWallFixture.setTotalPrice(cnt * otherFurniture.get(WALL_FIXTURE).getPrice());
    return itemWallFixture;
  }

  /**
   * get number of cnt standard rails.
   * @param cnt cnt
   * @return standardRails
   */
  public Item getStandardRail(final int cnt) {
    final Item itemStandardRail = new Item();
    itemStandardRail.setFurnitureName(RAIL_STANDARD);
    itemStandardRail.setFurniture(otherFurniture.get(RAIL_STANDARD));
    itemStandardRail.setCount(cnt);
    itemStandardRail.setTotalPrice(cnt * otherFurniture.get(RAIL_STANDARD).getPrice());
    return itemStandardRail;
  }

  /**
   * get number of cnt Atreides Rails.
   * @param cnt cnt
   * @return Atreides Rails
   */
  public Item getAtreidesRail(final int cnt) {
    final Item itemAtreidesRail = new Item();
    itemAtreidesRail.setFurnitureName(RAIL_ATREIDES);
    itemAtreidesRail.setFurniture(otherFurniture.get(RAIL_ATREIDES));
    itemAtreidesRail.setCount(cnt);
    itemAtreidesRail.setTotalPrice(cnt * otherFurniture.get(RAIL_ATREIDES).getPrice());
    return itemAtreidesRail;
  }

  /**
   * get number of cnt Corner Feet.
   * @param cnt cnt
   * @return Corner Feet
   */
  public Item getCornerFeet(final int cnt) {
    final Item itemCornerFoot = new Item();
    itemCornerFoot.setFurnitureName(CORNER_FOOT);
    itemCornerFoot.setFurniture(otherFurniture.get(CORNER_FOOT));
    itemCornerFoot.setCount(cnt);
    itemCornerFoot.setTotalPrice(cnt * otherFurniture.get(CORNER_FOOT).getPrice());
    return itemCornerFoot;
  }

  /**
   * get number of cnt Corner Feet.
   * @param cnt cnt
   * @return Corner Feet
   */
  public Item getDrawer(final int cnt) {
    final Item itemDrawer  = new Item();
    itemDrawer.setFurnitureName(DRAWER);
    itemDrawer.setFurniture(otherFurniture.get(DRAWER));
    itemDrawer.setCount(cnt);
    itemDrawer.setTotalPrice(cnt * otherFurniture.get(DRAWER).getPrice());
    return itemDrawer;
  }

  /**
   * get number of cnt Handle Drawer.
   * @param cnt cnt
   * @return Handle Drawer
   */
  public Item getHandleDrawer(final int cnt) {
    final Item itemHandleDrawer  = new Item();
    itemHandleDrawer.setFurnitureName(HANDLE_DRAWER);
    itemHandleDrawer.setFurniture(otherFurniture.get(HANDLE_DRAWER));
    itemHandleDrawer.setCount(cnt);
    itemHandleDrawer.setTotalPrice(cnt * otherFurniture.get(HANDLE_DRAWER).getPrice());
    return itemHandleDrawer;
  }

  /**
   * get number of cnt Drawer Front.
   * @param cnt cnt
   * @return Drawer front
   */
  public Item getDrawerFront(final int cnt) {
    Furniture drawerFront = null;
    for (final Furniture furniture: cabsAndCovers.get("COVER")) {
      if (furniture.getName().equals("GagaQuarterDrawerFront")) {
        drawerFront = furniture;
      }
    }
    final Item itemDrawerFront  = new Item();
    itemDrawerFront.setFurnitureName(GAGA_QUARTER_DRAWER_FRONT);
    itemDrawerFront.setFurniture(drawerFront);
    itemDrawerFront.setCount(cnt);
    itemDrawerFront.setTotalPrice(cnt * drawerFront.getPrice());
    return itemDrawerFront;
  }

  /**
   * get ShoppingLists with both cover and cabinet in them.
   * @param customerCriteria customerCriteria
   * @param cabinetCnt cabinetCnt
   * @param shoppingListWithoutCover shoppingListWithoutCover
   * @param combinationColor combinationColor
   * @return a set of ShoppingLists
   */
  public Set<ShoppingList> getShoppingListsWithCover(final CustomerCriteria customerCriteria,
      final int cabinetCnt, final ShoppingList shoppingListWithoutCover,
      final ColorType combinationColor) {
    final Set<ShoppingList> shoppingListsWithCover = new HashSet<>();
    final Set<Furniture> covers = new HashSet<>(cabsAndCovers.get(FurnitureType.COVER.toString()));
    for (final Furniture cover: covers) {
      if (customerCriteria.getDesiredHeight() != cover.getHeight()
          || cover.getName().equals("GagaQuarterDrawerFront")
          || !cover.getAvailableColors().contains(combinationColor)) {
        continue;
      }

      //tmp shoppingList
      cover.setColorType(combinationColor);
      final ShoppingList shoppingListWithCover = new ShoppingList();
      shoppingListWithCover.setColorType(combinationColor);
      shoppingListWithCover.setList(new ArrayList<>(shoppingListWithoutCover.getList()));

      //add doors
      final Item itemDoor  = new Item();
      final String doorName = cover.getName();
      itemDoor.setFurnitureName(doorName);
      itemDoor.setFurniture(cover);
      itemDoor.setCount(cabinetCnt);
      itemDoor.setTotalPrice(cabinetCnt * cover.getPrice());
      shoppingListWithCover.addItem(itemDoor);

      //add door hinges
      shoppingListWithCover.addItem(getDoorHinges(cabinetCnt));

      //add door handles
      if (cover.isHandleNeeded()) {
        shoppingListWithCover.addItem(getHandleDoor(cabinetCnt));
      }
      //total price should be <= budget
      if (shoppingListWithCover.getTotal() <= customerCriteria.getBudget()) {
        shoppingListsWithCover.add(shoppingListWithCover);
      }
    }
    return shoppingListsWithCover;
  }

  /**
   * get number of cnt Door Hinges.
   * @param cnt cnt
   * @return Door Hinges
   */
  public Item getDoorHinges(final int cnt) {
    final Item itemDoorHinges  = new Item();
    itemDoorHinges.setFurnitureName(DOOR_HINGES);
    itemDoorHinges.setFurniture(otherFurniture.get(DOOR_HINGES));
    itemDoorHinges.setCount(cnt);
    itemDoorHinges.setTotalPrice(cnt * otherFurniture.get(DOOR_HINGES).getPrice());
    return itemDoorHinges;
  }

  /**
   * get number of cnt Handle Door.
   * @param cnt cnt
   * @return Handle Door
   */
  public Item getHandleDoor(final int cnt) {
    final Item itemHandleDoor  = new Item();
    itemHandleDoor.setFurnitureName(HANDLE_DOOR);
    itemHandleDoor.setFurniture(otherFurniture.get(HANDLE_DOOR));
    itemHandleDoor.setCount(cnt);
    itemHandleDoor.setTotalPrice(cnt * otherFurniture.get(HANDLE_DOOR).getPrice());
    return itemHandleDoor;
  }

  /**
   * covert the final list set to string in order to print to the res file.
   * @param finalShoppingListsSet finalShoppingListsSet
   * @return string representation of finalShoppingListsSet
   */
  public String convertFinalSLs2JsonString(final Set<ShoppingList> finalShoppingListsSet) {
    if (finalShoppingListsSet.isEmpty()) {
      return "No Available ShoppingList.";
    }
    String listsJsonStr = "{\"shoppingLists\": [";
    for (final ShoppingList shoppingList: finalShoppingListsSet) {
      listsJsonStr += shoppingList.convertShoppingList2JsonStr() + ", "
          + "\n";
    }
    listsJsonStr = listsJsonStr.substring(0, listsJsonStr.length() - 3);
    listsJsonStr += "]}";
    return listsJsonStr;
  }

  /**
   * return all cabinets in the system.
   * @return a set of cabinets(put in shoppingLists)
   */
  public Set<ShoppingList> getAllCabinets() {
    final Set<ShoppingList> availableCabinets = new HashSet<>();
    for (final Object object: cabsAndCovers.get("CABINET")) {
      final Furniture furniture = (Furniture) object;
      final Item item = new Item();
      item.setFurnitureName(furniture.getName());
      item.setCount(1);
      item.setTotalPrice(furniture.getPrice());
      item.setFurniture(furniture);
      final ShoppingList shoppingList = new ShoppingList();
      shoppingList.addItem(item);
      availableCabinets.add(shoppingList);
    }
    return availableCabinets;
  }

  /**
   * read from customer_criteria.json file and convert content into a String.
   * @param filePath customer_criteria file path
   * @return CustomerCriteriaJsonStr
   */
  public String getCustomerCriteriaJsonStr(final String filePath) {
    return FileHelper.readJsonFile(filePath);
  }

  /**
   * write shoppingListsJsonStr into shoppingList.json.
   * @param shoppingListsJsonStr the output of recommend method
   * @param filePath path of shoppingList.json
   */
  public void outputShoppingListJsonStr(final String shoppingListsJsonStr, final String filePath) {
    FileHelper.writeJsonFile(filePath, shoppingListsJsonStr);
  }

  /**
   * main program.
   * @param args args
   */
  public static void main(final String[] args) {
    Recommender recommender = null;
    try {
      recommender = new Recommender(FURNITURE_FILE_PATH);
    } catch (ParseException e) {
      e.printStackTrace();
      System.err.println("Error: Parsing input json file failed.");
      System.exit(1);
    }
    recommender.recommend();
  }
}
