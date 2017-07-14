package com.demo.vaibhavrustogi.hackernews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.demo.vaibhavrustogi.hackernews.R;
import com.demo.vaibhavrustogi.hackernews.WebServiceConstants;
import com.demo.vaibhavrustogi.hackernews.adapters.NewsListingAdapter;
import com.demo.vaibhavrustogi.hackernews.networking.NetworkManager;

import org.json.JSONArray;

/**
 * Created by vaibhavrustogi on 14/07/17.
 */

public class NewsListingFragment extends BaseFragment implements Response.Listener, Response.ErrorListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NewsListingAdapter newsListingAdapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.news_listing_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews();
        fetchTopStories();
    }

    private void fetchTopStories() {
        progressBar(true);
        NetworkManager.getsInstance(getActivity()).makeJsonRequestGet(WebServiceConstants.getTopStoriesUrl(), null, this, this);
    }

    private void setAdapters(JSONArray jsonArray) {
        if (newsListingAdapter == null) {
            newsListingAdapter = new NewsListingAdapter(jsonArray);
            if (recyclerView != null)
                recyclerView.setAdapter(newsListingAdapter);
        } else {
            newsListingAdapter.setNewsIds(jsonArray);
        }
        progressBar(false);
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

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        if (response != null && response instanceof JSONArray) {
            setAdapters((JSONArray) response);
        }
    }
}
