package com.liu.bstong.fragment;

import com.liu.bstong.base.BaseFragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 主界面Fragment之一
 *
 * @author JQ
 */
public class MainFragment3 extends BaseFragment{

    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container) {
        TextView textView = new TextView(mActivity);
        textView.setText("发现");
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    protected void initViews(View root) {
    }
}
