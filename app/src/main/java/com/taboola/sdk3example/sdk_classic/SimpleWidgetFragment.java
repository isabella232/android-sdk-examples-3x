package com.taboola.sdk3example.sdk_classic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.taboola.sdk3example.R;
import com.taboola.android.TBLClassicPage;
import com.taboola.android.TBLClassicUnit;
import com.taboola.android.Taboola;
import com.taboola.android.annotations.TBL_PLACEMENT_TYPE;
import com.taboola.android.listeners.TBLClassicListener;


import java.util.HashMap;

public class SimpleWidgetFragment extends Fragment {
    TBLClassicListener tblClassicListener;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_widget, container, false);
        TBLClassicUnit tblClassicUnit =view.findViewById(R.id.taboola_view);
        TBLClassicPage tblClassicPage=Taboola.getClassicPage("https://blog.taboola.com", "article");
        tblClassicPage.addUnitToPage(tblClassicUnit,"Below Article","alternating-widget-without-video-1x4", TBL_PLACEMENT_TYPE.FEED,tblClassicListener);
        HashMap<String, String> extraProperties = new HashMap<>();
        extraProperties.put("useOnlineTemplate", "true");
        tblClassicUnit.setUnitExtraProperties(extraProperties);
        tblClassicListener=new TBLClassicListener() {
            @Override
            public boolean onItemClick(String placementName, String itemId, String clickUrl, boolean isOrganic, String customData) {
                return super.onItemClick(placementName, itemId, clickUrl, isOrganic, customData);
            }
        };
        tblClassicUnit.fetchContent();



        return view;
    }
}
