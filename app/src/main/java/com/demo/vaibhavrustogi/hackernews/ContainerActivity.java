package com.demo.vaibhavrustogi.hackernews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.demo.vaibhavrustogi.hackernews.utils.FragmentUtils;

import com.demo.vaibhavrustogi.hackernews.fragments.BaseFragment;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            initialize();
    }

    @Override
    public void onBackPressed() {
        if (!onPopBackStack()) {
            finish();
        }
    }

    protected boolean onPopBackStack() {
        BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        boolean isEventHandledByFragment = fragment != null && fragment.onPopBackStack();

        if (getSupportFragmentManager().getBackStackEntryCount() > 1 && !isEventHandledByFragment) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return isEventHandledByFragment;
    }

    private void initialize() {

        BaseFragment fragment = null;
        if (getIntent() != null && getIntent().getExtras() != null && !TextUtils.isEmpty(getIntent().getStringExtra(FragmentUtils.FRAGMENT_NAME))) {
            fragment = FragmentUtils.fragmentForName(getIntent().getStringExtra(FragmentUtils.FRAGMENT_NAME));
            fragment.setArguments(getIntent().getExtras());
        } else {
            fragment = FragmentUtils.fragmentForName(FragmentUtils.NEWS_LISTING_FRAGMENT);
        }
        addToBackStack(fragment);
    }

    public void addToBackStack(BaseFragment fragment) {
        FragmentUtils.replace(this, fragment, R.id.fragment_container);
    }
}
