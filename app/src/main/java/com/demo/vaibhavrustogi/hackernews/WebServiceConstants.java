package com.demo.vaibhavrustogi.hackernews;

import android.net.Uri;

/**
 * Created by vaibhavrustogi on 14/07/17.
 */

public class WebServiceConstants {

    public static final String HACKER_NEWS_BASE_URL = "https://hacker-news.firebaseio.com/v0";
    public static final String TOP_STORIES = "topstories.json";

    public static final String KEY_ITEM_ID = "item_id";

    public static String getTopStoriesUrl() {
        Uri.Builder uriBuilder = Uri.parse(HACKER_NEWS_BASE_URL).buildUpon();
        uriBuilder.appendPath(TOP_STORIES);
        return uriBuilder.toString();
    }
}
