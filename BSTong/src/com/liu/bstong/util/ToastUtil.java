package com.liu.bstong.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 项目名称：BSTong<br>
 * 类名称：ToastUtil<br>
 * 类描述：Toast多次點擊顯示一次<br>
 * 创建人：这里填开发者自己的姓名<br>
 * 创建时间：2016年7月1日上午10:07:22<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class ToastUtil {

	private static String oldMsg;
	protected static Toast toast = null;
	private static long oneTime = 0;
	private static long twoTime = 0;
	
	protected static BaseToast baseToast = null;

	public static void showToast(Context context, String s) {
		if (toast == null) {
			toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
			switch (2) {
			case 1:
				toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 300);
				break;
			case 2:
				toast.setGravity(Gravity.CENTER, 0, 0);
				break;
			}
			toast.show();
			oneTime = System.currentTimeMillis();
		} else {
			twoTime = System.currentTimeMillis();
			if (s.equals(oldMsg)) {
				if (twoTime - oneTime > Toast.LENGTH_SHORT) {
					toast.show();
				}
			} else {
				oldMsg = s;
				toast.setText(s);
				toast.show();
			}
		}
		oneTime = twoTime;
	}
	
	public static void showBaseToast(Context context, String s) {
		
		baseToast = BaseToast.makeText(context, s);
		if(baseToast!=null){
			baseToast.setDuration(1000);
			baseToast.show();
		}
	}
	
	public static void showBaseToastLong(Context context, String s) {
		
		baseToast = BaseToast.makeText(context, s);
		if(baseToast!=null){
			baseToast.setDuration(Toast.LENGTH_LONG);
			baseToast.show();
		}
	}
	
	public static void showBaseToast(Context context, int resId) {
		showBaseToast(context, context.getString(resId));
	}
}