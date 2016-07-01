package com.liu.bstong.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名称：BSTong<br>
 * 类名称：BaseFragment<br>
 * 类描述：Fragment基类<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年6月30日上午10:56:06<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    protected View mRootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = onCreateFragmentView(inflater, container);
            initViews(mRootView);
        } else {
            unbindParent(mRootView);
        }

        return mRootView;
    }

    public void unbindParent(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
           parent.removeView(view);
        }
    }

    public abstract View onCreateFragmentView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initViews(View root);
    
}
