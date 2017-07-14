package com.demo.vaibhavrustogi.hackernews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.demo.vaibhavrustogi.hackernews.R;

/**
 * Created by vaibhavrustogi on 14/07/17.
 */

public class NewsListingFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected int getFragmentLayout() {
        return R.layout.news_listing_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews();
    }

    private void initializeViews() {
        if (getView() == null)
            return;
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }
        progressBar = (ProgressBar) getView().findViewById(R.id.progress_bar);

    }

    private void progressBar(boolean show) {
        if (progressBar != null) {
            if (show)
                progressBar.setVisibility(View.VISIBLE);
            else
                progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
