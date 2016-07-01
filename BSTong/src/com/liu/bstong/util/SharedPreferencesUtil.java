package com.liu.bstong.util;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名称：Family<br>
 * 类名称：SharedPreferencesUtil<br>
 * 类描述：sp存儲工具类<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年5月4日上午11:29:15<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class SharedPreferencesUtil {

	/** 声明一个SharedPreferences对象 **/
	private SharedPreferences sharedPref;
	/** 声明一个SharedPreferences.Editor对象，对数据进行修改操作 **/
	private SharedPreferences.Editor editor;
	/** 数值缺省码 **/
	private final static int DEFAULT_CODE = -1;

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:30:05 <br>
	 * 构造方法描述：
	 * @param name
	 * @param context
	 */
	@SuppressWarnings("deprecation")
	public SharedPreferencesUtil(String name, Context context) {
		sharedPref = context.getSharedPreferences(name,
				Context.MODE_WORLD_WRITEABLE);
		editor = sharedPref.edit();
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:30:27<br>
	 * 方法描述：设置Integer类型数据<br>
	 * @param key
	 * @param value
	 */
	public void setInt(String key, int value) {
		if (sharedPref.contains(key)) {
			editor.remove(key);
		}
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:31:05<br>
	 * 方法描述：返回Integer类型数据<br>
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		if (sharedPref.contains(key)) {
			return sharedPref.getInt(key, DEFAULT_CODE);
		} else {
			return DEFAULT_CODE;
		}
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:31:45<br>
	 * 方法描述：设置float类型数据<br>
	 * @param key
	 * @param value
	 */
	public void setFloat(String key, float value) {
		if (sharedPref.contains(key)) {
			editor.remove(key);
		}
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:31:56<br>
	 * 方法描述：获取float数据<br>
	 * @param key
	 * @return
	 */
	public float getFloat(String key) {
		if (sharedPref.contains(key)) {
			return sharedPref.getFloat(key, DEFAULT_CODE);
		} else {
			return DEFAULT_CODE;
		}
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:32:16<br>
	 * 方法描述：保存boolean数据<br>
	 * @param key
	 * @param value
	 */
	public void setBoolean(String key, boolean value) {
		if (sharedPref.contains(key)) {
			editor.remove(key);

		}
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:32:29<br>
	 * 方法描述：获取boolean数据<br>
	 * @param key
	 * @return
	 */
	public boolean getBoolean(String key) {
		if (sharedPref.contains(key)) {
			return sharedPref.getBoolean(key, false);
		} else {
			return false;
		}
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:32:49<br>
	 * 方法描述：设置long数据<br>
	 * @param key
	 * @param value
	 */
	public void setLong(String key, long value) {
		if (sharedPref.contains(key)) {
			editor.remove(key);
		}
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:33:02<br>
	 * 方法描述：获取long数据<br>
	 * @param key
	 * @return
	 */
	public long getLong(String key) {
		if (sharedPref.contains(key)) {
			return sharedPref.getLong(key, DEFAULT_CODE);
		} else {
			return DEFAULT_CODE;
		}
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:33:43<br>
	 * 方法描述：保存String数据<br>
	 * @param key
	 * @param value
	 */
	public void setString(String key, String value) {
		if (sharedPref.contains(key)) {
			editor.remove(key);
		}
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:33:55<br>
	 * 方法描述：获取String数据<br>
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		if (sharedPref.contains(key)) {
			return sharedPref.getString(key, "");
		} else {
			return "";
		}
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:34:07<br>
	 * 方法描述：获取所有数据集合<br>
	 * @return
	 */
	public Map<String, ?> getAll() {
		return sharedPref.getAll();
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:34:19<br>
	 * 方法描述：移除该值<br>
	 * @param key
	 */
	public void remove(String key) {
		if (sharedPref.contains(key)) {
			editor.remove(key);
			editor.commit();
		}
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年5月4日 上午11:34:32<br>
	 * 方法描述：清空数据<br>
	 */
	public void clear() {
		if (null != editor) {
			editor.clear();
			editor.commit();
		}
	}

}
