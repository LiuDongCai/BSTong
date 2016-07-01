package com.liu.bstong.util;


import com.liu.bstong.base.BSTongApplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 项目名称：BSTong<br>
 * 类名称：UIUtils<br>
 * 类描述：工具类<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年6月29日下午3:05:55<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class UIUtils {

	/**
	 * 获取上下文环境
	 * @return
	 */
	public static Context getContext(){
		return BSTongApplication.getmContext();
	}
	
	/**
	 * 获取handler对象
	 * @return
	 */
	public static Handler getHandler(){
		return BSTongApplication.getmHandler();
	}
	
	/**
	 * 获取主线程对象
	 * @return
	 */
	public static Thread getMainThread(){
		return BSTongApplication.getmMainThread();
	}
	
	public static int getMainThreadId(){
		return BSTongApplication.getmMainThreadId();
	}
	
	/**
	 * 返回布局对应的View对象
	 * @param layoutResId 布局的资源id
	 * @return
	 */
	public static View inflate(int layoutResId){
		return View.inflate(getContext(), layoutResId, null);
	}
	
	/**
	 * 获取资源对象
	 * @return
	 */
	public static Resources getResources(){
		return getContext().getResources();
	}
	
	/**
	 * 获取颜色对象
	 * @return
	 */
	public static int getColors(int colorsId){
		return getContext().getResources().getColor(colorsId);
	}
	
	/**
	 * 获取string.xml中某节点对应的值
	 * @param stringResId
	 * @return
	 */
	public static String getString(int stringResId){
		return getResources().getString(stringResId);
	} 
	
	/**
	 * 获取string.xml中某节点对应的内容
	 * @param stringArrayResId
	 * @return
	 */
	public static String[] getStringArray(int stringArrayResId){
		return getResources().getStringArray(stringArrayResId);
	}
	
	/**
	 * 根据资源id获取Drawable图片对象
	 * @param drawableResId
	 * @return
	 */
	public static Drawable getDrawable(int drawableResId){
		return getResources().getDrawable(drawableResId);
	}
	
	/**
	 * 将任务放在主线程执行
	 * @param runnable
	 */
	public static void runInMainThread(Runnable runnable){
		if(android.os.Process.myTid()==getMainThreadId()){
			//在主线程
			runnable.run();
		}else{
			//不是在主线程
			getHandler().post(runnable);
		}
	}
	
	/**
	 * 将px转为dp
	 * @return
	 */
	public static int px2dip(int px){
		float density = getResources().getDisplayMetrics().density;
		return (int) (px/density+0.5);
	}
	
	/**
	 * 将dp转为px
	 * @param dip
	 * @return
	 */
	public static int dip2px(int dip){
		float density = getResources().getDisplayMetrics().density;
		return (int) (dip*density+0.5);
	}
	
	/**
	 * 执行延时任务的操作
	 * @param runnableTask	延时任务
	 * @param delayedTime	延时时间
	 */
	public static void postDelayed(Runnable runnableTask, int delayedTime) {
		getHandler().postDelayed(runnableTask, delayedTime);
	}

	/**通过handler移除指定的任务
	 * @param runnableTask	需要移除的任务对象
	 */
	public static void removeCallBack(Runnable runnableTask) {
		getHandler().removeCallbacks(runnableTask);
	}
	
	private static Toast mToast;

    public static void showToast(final String msg) {
    	runInMainThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(getContext(), "", Toast.LENGTH_LONG);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }
    
    /** 显示输入法面板 */
	public static void showInputMethod(final EditText editText) {
		getHandler().post(new Runnable() {
			@Override
			public void run() {
				editText.requestFocus();
				InputMethodManager inputManager = (InputMethodManager) editText
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				inputManager.showSoftInput(editText, 0);
			}
		});
	}
	
	/** 隐藏输入法面板 */
	public static void hideInputMethod(View view) {
		Context context = view.getContext();
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}
