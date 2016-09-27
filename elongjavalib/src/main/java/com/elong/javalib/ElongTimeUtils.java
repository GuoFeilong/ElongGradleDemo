package com.elong.javalib;

import java.util.Calendar;

/**
 * Created by user on 16/9/27.
 */

public class ElongTimeUtils {
    public static String getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR) + "年--used-->>ElongTime.jar";
    }

}
