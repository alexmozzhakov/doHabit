package com.doapps.habits.helper;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.doapps.habits.BuildConfig;
import com.doapps.habits.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Observable;

public class AvatarManager extends Observable {
    private static final String TAG = AvatarManager.class.getSimpleName();
    private volatile boolean changed;
    private Uri mUri;
    private int version;
    public static final AvatarManager listener = new AvatarManager();

    public void setVersion(int newVersion, Activity activity) {
        version = newVersion;
        saveVersion(activity);
    }

    private void saveVersion(Activity activity) {
        activity.getSharedPreferences("pref", 0).edit().putInt("photo_version", version).apply();
    }

    public void invalidateUrl() {
        if (mUri == null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                mUri = user.getPhotoUrl();
            }
        }
    }

    public Uri getUri() {
        return mUri;
    }

    public Uri getLargeUri() {
        return mUri.toString().contains("graph") ? Uri.parse(mUri + "?type=large") : Uri.parse(mUri + "?version=" + version);
    }

    public Uri getMediumUri() {
        return mUri.toString().contains("graph") ? Uri.parse(mUri + "?type=normal") : Uri.parse(mUri + "?version=" + version);
    }

    public void setUri(Uri uri, Activity activity) {
        changed = true;
        version++;
        mUri = uri;
        Picasso.with(activity.getApplicationContext()).invalidate(getMediumUri());
        Picasso.with(activity.getApplicationContext()).invalidate(getLargeUri());
//        PicassoTools.clearCache(Picasso.with(activity));

        if (BuildConfig.DEBUG) {
            Log.i("AvatarManager", "Notifying " + countObservers()
                    + " observers about avatar change");
            Log.i(TAG, "New uri is " + uri);
        }
        notifyObservers();
    }

    public static void fixBackgroundSize(ImageView avatar) {
        avatar.setImageResource(R.drawable.fix);
    }

    @Override
    public synchronized boolean hasChanged() {
        return changed;
    }

    public int getVersion() {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "Current photo version = " + version);
        }
        return version;
    }
}