package com.liu.bstong.base;

import java.util.ArrayList;
import com.liu.bstong.R;
import com.umeng.analytics.MobclickAgent;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * 项目名称：BSTong<br>
 * 类名称：BaseActivity<br>
 * 类描述：activity的基类<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年6月29日下午3:06:11<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
@SuppressLint("NewApi")
public class BaseActivity extends FragmentActivity {
	
	public static ArrayList<BackPressHandler> mListeners = new ArrayList<BackPressHandler>();

	public static abstract interface BackPressHandler {

		public abstract void activityOnResume();

		public abstract void activityOnPause();
	}
	
	private ProgressDialog mPDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobclickAgent.setDebugMode(false);
		MobclickAgent.setCatchUncaughtExceptions(true);
	}
	
	
	/**
	 * 显示加载提示框(不能在子线程调用)
	 */
	public void showProgressDialog(String message) {
		mPDialog = new ProgressDialog(this);
		mPDialog.setMessage(message);
		// 点击外部时不销毁
		mPDialog.setCanceledOnTouchOutside(false);

		// activity如果正在销毁，不能show Dialog，否则出错。
		if (!isFinishing())
			mPDialog.show();
	}

	/**
	 * 销毁加载提示框
	 */
	public void dismissProgressDialog() {
		if (mPDialog != null) {
			mPDialog.dismiss();
			mPDialog = null;
		}
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		if (mListeners.size() > 0) {
			for (BackPressHandler handler : mListeners) {
				handler.activityOnResume();
			}
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		if (mListeners.size() > 0) {
			for (BackPressHandler handler : mListeners) {
				handler.activityOnPause();
			}
		}
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.remain, R.anim.push_right_out);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		MobclickAgent.onPause(this);
		try {
			System.gc();
		} catch (Exception ex) {
		}
	}
	
	
}
