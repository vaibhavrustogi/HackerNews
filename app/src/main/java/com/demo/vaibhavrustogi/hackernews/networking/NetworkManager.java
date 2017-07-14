package com.demo.vaibhavrustogi.hackernews.networking;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.demo.vaibhavrustogi.hackernews.networking.customrequest.GsonRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by vaibhavrustogi on 14/07/17.
 */

public class NetworkManager {
    private static NetworkManager sInstance;
    private RequestQueue mRequestQueue;


    public static NetworkManager getsInstance(Context context) {
        if (sInstance == null)
            sInstance = new NetworkManager(context);
        return sInstance;
    }

    private NetworkManager(Context context) {
        mRequestQueue = Volley.newRequestQueue
                (context.getApplicationContext(), new OkHttpStack());
        mRequestQueue.start();
    }

    public Request<?> makeGsonRequestGet(String url, Class clazz, final Map<String, String> body, final Map<String, String> headers, Response.Listener successListener, Response.ErrorListener errorListener) {
        return makeGsonRequest(url, clazz, body, headers, Request.Method.GET, successListener, errorListener, null);
    }

    public Request<?> makeJsonRequestGet(String url, final Map<String, String> headers, Response.Listener successListener, Response.ErrorListener errorListener) {
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null, successListener,
                errorListener);
        mRequestQueue.add(getRequest);
        return getRequest;
    }

    public Request<?> makeGsonRequest(String url, Class clazz, Map<String, String> params, Map<String, String> headers,
                                      int method, Response.Listener successListener, Response.ErrorListener errorListener, String tag) {
        if (method == Request.Method.GET) {
            url = generateGetUrl(url, params);
        }
        GsonRequest gsonRequest = new GsonRequest(url, method, clazz, headers, params == null ? null : new JSONObject(params).toString(), successListener, errorListener);
        gsonRequest.setTag(tag);
        mRequestQueue.add(gsonRequest);
        return gsonRequest;
    }

    public static String generateGetUrl(String url, Map<String, String> params) {
        Uri.Builder uriBuilder = Uri.parse(url).buildUpon();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                if (TextUtils.isEmpty(value)) {
                    value = "";
                }
                uriBuilder.appendQueryParameter(entry.getKey(), value);
            }
        }
        return uriBuilder.toString();
    }

}
