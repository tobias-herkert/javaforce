package javaforce.ui;

/** Row - lays out components in a row.
 *
 * @author pquiring
 */

import javaforce.*;

public class Row extends Container {

  public Dimension getMinSize() {
    int width = 0;
    int height = 0;
    for(Component child : children) {
      Dimension size = child.getMinSize();
      width += size.width;
      if (size.height > height) height = size.height;
    }
    minSize.width = width;
    minSize.height = height;
    return minSize;
  }

  /** Lay out components in a row. */
  public void layout(LayoutMetrics metrics) {
    int min_x = 0;
    int flex_count = 0;
    int flex_size = 0;
    for(Component child : children) {
      if (child instanceof FlexBox) {
        flex_count++;
      } else if (child instanceof Container) {
        min_x += child.getMinWidth();
      } else {
        min_x += child.getMinWidth();
      }
    }
    if (flex_count > 0) {
      flex_size = (metrics.size.width - min_x) / flex_count;
      if (debug) JFLog.log("Row:flex_size=" + flex_size + ",min_x=" + min_x);
    }
    for(Component child : children) {
      child.setPosition(metrics.pos);
      if (child instanceof FlexBox) {
        metrics.pos.x += flex_size;
      } else if (child instanceof Container) {
        int org_width = metrics.size.width;
        int org_height = metrics.size.height;
        int org_x = metrics.pos.x;
        int org_y = metrics.pos.y;
        metrics.size.width = child.getMinWidth();
        child.layout(metrics);
        metrics.size.width = org_width;
        metrics.size.height = org_height;
        metrics.pos.x = org_x;
        metrics.pos.y = org_y;
        metrics.pos.x += child.getMinWidth();
      } else {
        child.layout(metrics);
        metrics.pos.x += child.getMinWidth();
      }
    }
  }
}