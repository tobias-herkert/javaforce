package jfcontrols.logic;

/** Examine Off.
 *
 * @author pquiring
 */

import javaforce.controls.*;

public class XOFF extends Logic {

  public boolean isBlock() {
    return false;
  }

  public String getName() {
    return "xoff";
  }

  public String getCode(int types[]) {
    return "enabled &= !tags[1].getBoolean();\r\n";
  }

  public int getTagsCount() {
    return 1;
  }

  public int getTagType(int idx) {
    return TagType.bit;
  }
}
