package com.anwesome.games.leandrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.hardware.display.DisplayManager;
import android.view.Display;

/**
 * Created by anweshmishra on 19/03/17.
 */
public class DimensionsUtil {
    public static Point getDimensions(Activity activity) {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        Point point = new Point();
        display.getRealSize(point);
        return point;
    }
}
