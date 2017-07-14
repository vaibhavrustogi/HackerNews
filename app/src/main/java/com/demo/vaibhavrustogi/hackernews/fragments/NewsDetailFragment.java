package com.demo.vaibhavrustogi.hackernews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.demo.vaibhavrustogi.hackernews.R;
import com.demo.vaibhavrustogi.hackernews.WebServiceConstants;
import com.demo.vaibhavrustogi.hackernews.model.Item;
import com.demo.vaibhavrustogi.hackernews.networking.NetworkManager;

/**
 * Created by vaibhavrustogi on 14/07/17.
 */

public class NewsDetailFragment extends BaseFragment implements Response.Listener, Response.ErrorListener {

    private long itemId;

    @Override
    protected int getFragmentLayout() {
        return R.layout.news_detail_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initializeViews();
        fetchItemDetail(itemId);
    }

    private void fetchItemDetail(long itemId) {
        NetworkManager.getsInstance(getActivity()).makeGsonRequestGet(WebServiceConstants.getItemUrl(itemId), Item.class, null, null, this, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            itemId = getArguments().getLong(WebServiceConstants.KEY_ITEM_ID);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        if (response != null && response instanceof Item) {
        }
    }
}
