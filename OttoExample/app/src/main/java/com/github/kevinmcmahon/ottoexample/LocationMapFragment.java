package com.github.kevinmcmahon.ottoexample;

import android.app.Fragment;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static android.widget.ImageView.ScaleType.CENTER_INSIDE;

public class LocationMapFragment extends Fragment {
    private static final String URL =
            "https://maps.googleapis.com/maps/api/staticmap?sensor=false&size=400x400&zoom=13&center=%s,%s";
    private static DownloadTask downloadTask;

    private ImageView imageView;

    @Override public void onResume() {
        super.onResume();
    }

    @Override public void onPause() {
        super.onPause();

        // Stop existing download, if it exists.
        if (downloadTask != null) {
            downloadTask.cancel(true);
            downloadTask = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageView = new ImageView(getActivity());
        imageView.setScaleType(CENTER_INSIDE);
        return imageView;
    }

    private static class DownloadTask extends AsyncTask<String, Void, Drawable> {
        @Override
        protected Drawable doInBackground(String... params) {
            try {
                return BitmapDrawable.createFromStream(new java.net.URL(params[0]).openStream(), "bitmap.jpg");
            } catch (Exception e) {
                Log.e("LocationMapFragment", "Unable to download image.", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            if (!isCancelled() && drawable != null) {
                // Write back to main thread to update UI
            }
        }
    }
}