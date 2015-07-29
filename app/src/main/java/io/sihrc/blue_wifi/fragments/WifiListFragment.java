package io.sihrc.blue_wifi.fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.sihrc.blue_wifi.R;
import io.sihrc.blue_wifi.adapters.WifiAdapter;

/**
 * Created by Chris on 7/28/15.
 */
public class WifiListFragment extends WifiFragment {
    static WifiListFragment instance;

    // Binding Views
    @Bind(R.id.fragment_wifi_listview) ListView listView;
    View headerView;
    AbsListView.LayoutParams headerParams = new ListView.LayoutParams(
            ListView.LayoutParams.MATCH_PARENT,
            ListView.LayoutParams.WRAP_CONTENT
    );

    // List Adapter
    WifiAdapter wifiAdapter;

    public static WifiListFragment getInstance() {
        if (instance == null)
            instance = new WifiListFragment();

        return instance;
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wifi_list, container, false);
        ButterKnife.bind(this, rootView);

        headerView = inflater.inflate(R.layout.layout_wifilist_header, container, false);
        setupListView();

        return rootView;
    }

    View.OnClickListener headerClickListener = new View.OnClickListener() {
        @Override public void onClick(View view) {
            // TODO
        }
    };

    private void setupListView() {
        // Setup Header
        headerView.setLayoutParams(headerParams);
        headerView.setOnClickListener(headerClickListener);
        listView.addHeaderView(headerView);
        listView.setHeaderDividersEnabled(false);

        // Setup Overscroll behavior
        ColorDrawable overscroll = new ColorDrawable(getResources().getColor(R.color.theme_color));
        listView.setOverScrollMode(ListView.OVER_SCROLL_ALWAYS);
        listView.setOverscrollHeader(overscroll);
//        listView.setOverscrollFooter(overscroll);

        // List Content
        wifiAdapter = new WifiAdapter(activity);
        listView.setAdapter(wifiAdapter);
    }
}
