package com.liu.bstong.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/**
 * 项目名称：AndroidUtil<br>
 * 类名称：NetWorkType<br>
 * 类描述：网络类型工具<br>
 * 创建人：劉棟財<br>
 * 创建时间：2015年5月29日下午2:52:18<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * 
 * @version V1.0
 */
public class NetWorkTypeUtil {

	/** 当前无网络 **/
	public final static int NONE = 0;
	/** 当前的网络不可用 **/
	public final static int VAIN = 1;
	/** 有WIFI **/
	public final static int WIFI = 2;
	/** 有3G、GPRS网络 **/
	public final static int MOBILE = 3;

	/**
	 * 
	 * 创建人：劉棟財<br>
	 * 创建时间：2015年5月29日 下午2:53:04<br>
	 * 方法描述：获取网络状态<br>
	 * 
	 * @param connManager
	 * @return
	 */
	public static int getNetworkState(ConnectivityManager connManager) {
		// 如果没有网络
		if (connManager == null) {
			return NONE;
		}
		// 如果网络不可用
		NetworkInfo in = connManager.getActiveNetworkInfo();
		if (in != null) {
			if (!in.isAvailable()) {
				return VAIN;
			}
		}
		// 手机网络判断
		State state = connManager.getNetworkInfo(
				ConnectivityManager.TYPE_MOBILE).getState();
		if (state == State.CONNECTED || state == State.CONNECTING) {
			return MOBILE;
		}
		// WIFI网络判断
		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		if (state == State.CONNECTED || state == State.CONNECTING) {
			return WIFI;
		}
		return NONE;
	}

	/**
	 * 
	 * 创建人：劉棟財<br>
	 * 创建时间：2015年11月11日 上午9:13:54<br>
	 * 方法描述：判断是否有网络<br>
	 * 最后修改人：
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();
			return (ni != null) && (ni.isConnectedOrConnecting());
		}
		return true;
	}

}
