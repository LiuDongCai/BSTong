package com.liu.bstong.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liu.bstong.base.BaseFragment;


/**
 * 主界面Fragment之一
 *
 * @author JQ
 */
public class MoreFragment extends BaseFragment{

	@Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container) {
//        View view = inflater.inflate(R.layout.main_fragment_01, container, false);
    	TextView textView = new TextView(mActivity);
        textView.setText("我的");
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

	@Override
	protected void initViews(View root) {
		
	}
	
}
