package com.liu.bstong.util;

import com.liu.bstong.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 项目名称：BSTong<br>
 * 类名称：BaseToast<br>
 * 类描述：自定義Toast控件<br>
 * 创建人：这里填开发者自己的姓名<br>
 * 创建时间：2016年7月1日上午10:08:28<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class BaseToast extends Toast{
	
	public BaseToast(Context context) 
	{
		super(context);
	}

	@Override
	public void show() 
	{
		super.show();
	}	
	
	/**
	 * 获取控件实例
	 */
	public static BaseToast makeText(Context context, CharSequence text) 
	{
		BaseToast result = new BaseToast(context);		
        LayoutInflater inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.layout_base_toast_view, null);//base_icon_toast_view
        TextView tv = (TextView)v.findViewById(R.id.toast_message);
        tv.setText(text);
        result.setView(v);
        result.setDuration(Toast.LENGTH_SHORT);
        result.setGravity(Gravity.CENTER, 0, 0);
        return result;
    }
	
	public static BaseToast makeText(Context context, int resId) {
		return makeText(context, context.getString(resId));
	}	
}