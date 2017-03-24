package com.doapps.habits.helper;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PicassoDrawableViewBackgroundTarget implements Target {
    /**
     * TAG is defined for logging errors and debugging information
     */
    private static final String TAG = PicassoDrawableViewBackgroundTarget.class.getSimpleName();
    private final ImageView mImageView;

    public PicassoDrawableViewBackgroundTarget(ImageView imageView) {
        mImageView = imageView;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        mImageView.setBackground(new BitmapDrawable(mImageView.getContext().getResources(), bitmap));
        mImageView.getBackground().invalidateSelf();
        mImageView.invalidate();
    }

    @Override
    public void onBitmapFailed(final Drawable errorDrawable) {
        Log.e(TAG, "Failed bitmap");
    }

    @Override
    public void onPrepareLoad(final Drawable placeHolderDrawable) {
        Log.d(TAG, "Prepare Load");
    }
}
