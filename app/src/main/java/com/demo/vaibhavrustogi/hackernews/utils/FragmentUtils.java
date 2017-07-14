package com.demo.vaibhavrustogi.hackernews.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import fragments.BaseFragment;
import fragments.NewsDetailFragment;
import fragments.NewsListingFragment;

/**
 * Created by vaibhavrustogi on 14/07/17.
 */

public class FragmentUtils {
    public static final String FRAGMENT_NAME = "fragment_name";
    public static final String NEWS_LISTING_FRAGMENT = "news_listing_fragment";
    public static final String NEWS_DETAIL_FRAGMENT = "news_detail_fragment";

    public static void replace(AppCompatActivity activity, Fragment fragment, int layout) {
        if (activity == null)
            return;
        replace(activity.getSupportFragmentManager(), fragment, layout, 0, 0, 0, 0, true);
    }

    public static void replace(FragmentManager fragmentManager, Fragment fragment, int layout, int exitAnimation, int entryAnimation, int popEnterAnimation, int popExitAnimation, boolean addToBackStack) {
        if (fragment == null || fragmentManager == null)
            return;

        String tag = fragment.getClass().getSimpleName();
        Fragment currentTopFragment = fragmentManager.findFragmentById(layout);
        if (currentTopFragment != null && currentTopFragment.getClass().getSimpleName().equals(tag))
            return;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(layout, fragment);
        transaction.setCustomAnimations(entryAnimation, exitAnimation, popEnterAnimation, popExitAnimation);
        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }


    public static BaseFragment fragmentForName(String fragmentName) {
        if (fragmentName.equalsIgnoreCase(NEWS_LISTING_FRAGMENT))
            return new NewsListingFragment();
        if (fragmentName.equalsIgnoreCase(NEWS_DETAIL_FRAGMENT))
            return new NewsDetailFragment();
        return null;
    }
}
