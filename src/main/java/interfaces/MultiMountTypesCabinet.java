package interfaces;

/**
 * the interface to represents the cabinets who can be wall-mounted and floor mounted both.
 * @author Chang Zhou
 */
public interface MultiMountTypesCabinet {

  /**
   * the method should be implemented in subclasses.
   * change this cabinet to wall-mounted mode.
   */
  void change2WallMounted();

  /**
   * the method should be implemented in subclasses.
   * change this cabinet to floor-mounted mode.
   */
  void change2FloorMounted();
}
