package io.sihrc.blue_wifi.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Chris on 7/28/15.
 */
public class WifiFragment extends Fragment {
    String FRAGMENT_NAME;
    Activity activity;

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public String getName() {
        return FRAGMENT_NAME == null ? getClass().getSimpleName() : FRAGMENT_NAME;
    }
}
