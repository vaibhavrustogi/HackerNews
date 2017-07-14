package com.demo.vaibhavrustogi.hackernews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.vaibhavrustogi.hackernews.R;

import org.json.JSONArray;

/**
 * Created by vaibhavrustogi on 14/07/17.
 */

public class NewsListingAdapter extends RecyclerView.Adapter {

    JSONArray itemIds;

    @Override
    public int getItemViewType(int position) {
        return R.layout.news_listing_item_layout;
    }

    public NewsListingAdapter(JSONArray newsIds) {
        this.itemIds = newsIds;
    }

    public NewsListingAdapter() {
    }

    public void setNewsIds(JSONArray newsIds) {
        this.itemIds = newsIds;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(viewType, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null)
            return;
        if (holder instanceof NewsItemViewHolder) {
            ((NewsItemViewHolder) holder).newsIdTV.setText("" + itemIds.optLong(position));
        }
    }

    @Override
    public int getItemCount() {
        return itemIds == null ? 0 : itemIds.length();
    }

    protected static class NewsItemViewHolder extends RecyclerView.ViewHolder {

        private TextView newsIdTV;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            newsIdTV = (TextView) itemView.findViewById(R.id.news_id);
        }
    }

}
