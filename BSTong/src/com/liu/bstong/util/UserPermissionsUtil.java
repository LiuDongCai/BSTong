package com.liu.bstong.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * 项目名称：Family<br>
 * 类名称：UserPermissionsUtil<br>
 * 类描述：<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年5月4日上午11:00:40<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class UserPermissionsUtil {

	/** 照相設備權限 **/
	public final static int CODE_CAMERA = 0;
	/** 設備聯繫人權限 **/
	public final static int CODE_READ_CONTACTS = 1;
	/** GPS定位權限 **/
	public final static int CODE_ACCESS_FINE_LOCATION = 2;
	/** 設備錄製音頻權限 **/
	public final static int CODE_RECORD_AUDIO = 3;
	/** 獲取設備機型權限 **/
	public final static int CODE_READ_PHONE_STATE = 4;
	/** 寫入外部存儲器權限 **/
	public final static int CODE_WRITE_EXTERNAL_STORAGE = 5;
	/** 讀取外部存儲器權限 **/
	public final static int CODE_READ_EXTERNAL_STORAGE = 6;

	/** 權限提醒語 **/
	public final static String PERMISSION_REMIND = "请打开设置，到应用管理申请Family应用权限！";
	
	public final static String ARRAY_PERMISSION[] = new String[] {
			Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS,
			Manifest.permission.ACCESS_FINE_LOCATION,
			Manifest.permission.RECORD_AUDIO,
			Manifest.permission.READ_PHONE_STATE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE, 
			Manifest.permission.READ_EXTERNAL_STORAGE };

	@TargetApi(23)
	public static void requestUserPermissions(Activity context, int index) {
		try {
			if (index >= 0 && index < ARRAY_PERMISSION.length) {
				if (ContextCompat.checkSelfPermission(context,
						ARRAY_PERMISSION[index]) != PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(context,
							new String[] { ARRAY_PERMISSION[index] }, index);
				}
			}
		} catch (Exception ex) {
		}
	}

}
