package com.taboola.sdk3example.sdk_classic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.taboola.sdk3example.R;
import com.taboola.android.TBLClassicPage;
import com.taboola.android.TBLClassicUnit;
import com.taboola.android.Taboola;
import com.taboola.android.annotations.TBL_PLACEMENT_TYPE;
import com.taboola.android.listeners.TBLClassicListener;


public class PullToRefreshFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private TBLClassicUnit tblClassicUnit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_pull_to_refresh, container, false);
        tblClassicUnit = view.findViewById(R.id.taboola_view);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        TBLClassicPage tblClassicPage=Taboola.getClassicPage("https://blog.taboola.com", "article");
        tblClassicPage.addUnitToPage(tblClassicUnit, "Below Article", "alternating-widget-without-video-1x4", TBL_PLACEMENT_TYPE.FEED, new TBLClassicListener() {
            @Override
            public void onUpdateContentCompleted() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        tblClassicUnit.fetchContent();
        return view;
    }

    @Override
    public void onRefresh() {
        tblClassicUnit.fetchContent();
        swipeRefreshLayout.setRefreshing(false);
        //Ask Taboola to update its content (refresh)
    }


}
