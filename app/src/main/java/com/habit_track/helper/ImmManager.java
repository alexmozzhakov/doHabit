package com.habit_track.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class ImmManager {
    private static ImmManager ourInstance = null;
    private boolean immOpened;

    public static ImmManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new ImmManager();
        }
        return ourInstance;
    }

    private ImmManager() {
    }

    public boolean isImmOpened() {
        return immOpened;
    }

    public void closeImm(Activity activity) {
        final InputMethodManager imm = (InputMethodManager) activity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
        setImmClosed();
        Log.i("IMM", "Closed imm");
    }

    public void setImmOpened() {
        Log.i("IMM", "Opened imm");
        this.immOpened = true;
    }

    private void setImmClosed() {
        this.immOpened = false;
    }
}