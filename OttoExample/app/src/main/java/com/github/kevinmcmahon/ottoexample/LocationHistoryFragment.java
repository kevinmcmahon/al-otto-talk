package com.github.kevinmcmahon.ottoexample;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class LocationHistoryFragment extends ListFragment {

    private final List<String> locationEvents = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override public void onResume() {
        super.onResume();
    }

    @Override public void onPause() {
        super.onPause();
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, locationEvents);
        setListAdapter(adapter);
    }
}
