package com.demo.vaibhavrustogi.hackernews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.vaibhavrustogi.hackernews.ContainerActivity;
import com.demo.vaibhavrustogi.hackernews.R;

/**
 * Created by vaibhavrustogi on 14/07/17.
 */

public abstract class BaseFragment extends DialogFragment {

    private Toolbar toolbar;
    private String title;

    protected int getToolBarTitleId() {
        return 0;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected boolean isToolBarExists() {
        return getView() != null && toolbar != null;
    }

    protected abstract int getFragmentLayout();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected int getToolBarId() {
        return R.id.tool_bar;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(getFragmentLayout(), container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        toolbar = (Toolbar) view.findViewById(getToolBarId());
        super.onViewCreated(view, savedInstanceState);
        initializeToolBar();
    }

    private void initializeToolBar() {
        changeTitle();
    }

    protected void setTitle(String title) {
        this.title = title;
        changeTitle();
    }

    private void changeTitle() {
        if (isToolBarExists()) {
            if (!TextUtils.isEmpty(title)) {
                if (getToolBarTitleId() == 0)
                    toolbar.setTitle(title);
                else {
                    TextView titleTV = (TextView) toolbar.findViewById(getToolBarTitleId());
                    if (titleTV != null)
                        titleTV.setText(title);
                }
            }
        }
    }

    protected void addToBackStack(BaseFragment fragment) {
        if (getActivity() != null && getActivity() instanceof ContainerActivity) {
            ((ContainerActivity) getActivity()).addToBackStack(fragment);
        }
    }

    public boolean onPopBackStack() {
        return false;
    }
}
