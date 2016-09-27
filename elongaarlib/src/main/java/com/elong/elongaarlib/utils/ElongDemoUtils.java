package com.elong.elongaarlib.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Calendar;

/**
 * Created by user on 16/9/27.
 */

public class ElongDemoUtils {
    public static View getInflateView(Context context, int layoutID) {
        return LayoutInflater.from(context).inflate(layoutID, null, false);
    }

    private ElongDemoUtils() {
    }
}
