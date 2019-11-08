package interfaces;

/**
 * the interface to represents the cabinets who can be with drawers.
 * @author Chang Zhou
 */
public interface CabinetWithDrawer {
  /**
   * the method should be implemented in subclasses.
   * change this cabinet to with-drawer mode.
   */
  void change2WithDrawer();

  /**
   * the method should be implemented in subclasses.
   * change this cabinet to without-drawer mode.
   */
  void change2WithoutDrawer();
}
