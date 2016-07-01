package com.liu.bstong.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 项目名称：BSTong<br>
 * 类名称：BSTongApplication<br>
 * 类描述：全局的application<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年6月29日下午3:05:36<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class BSTongApplication extends Application {

	private static Context mContext;
	private static Handler mHandler;
	private static Thread mMainThread;
	private static int mMainThreadId;

	@Override
	public void onCreate() {

		mContext = getApplicationContext();

		mHandler = new Handler();
		// 主线程
		mMainThread = Thread.currentThread();
		// 主线程ID
		mMainThreadId = android.os.Process.myTid();

	}

	public static Context getmContext() {
		return mContext;
	}

	public static Handler getmHandler() {
		return mHandler;
	}

	public static Thread getmMainThread() {
		return mMainThread;
	}

	public static int getmMainThreadId() {
		return mMainThreadId;
	}

}
