package com.liu.bstong.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.liu.bstong.R;
import com.liu.bstong.base.BaseActivity;
import com.liu.bstong.bean.IdentityBean;
import com.liu.bstong.config.Constants;
import com.liu.bstong.config.HttpApi;
import com.liu.bstong.util.ToastUtil;
import com.liu.bstong.util.UIUtils;
import com.liu.bstong.widget.CalendarView;
import com.liu.bstong.widget.CalendarView.OnCalendarClickListener;
import com.liu.bstong.widget.CalendarView.OnCalendarDateChangedListener;

/**
 * 项目名称：BSTong<br>
 * 类名称：IdentityActivity<br>
 * 类描述：老黄历查询界面<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年6月30日下午1:44:23<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * 
 * @version V1.0
 */
public class CalendarActivity extends BaseActivity implements OnClickListener {

	private Button bt_select_calendar;
	private String date = null;// 设置默认选中的日期 格式为 “2014-04-05” 标准DATE格式
	
	private TextView tv_one,tv_two,tv_three,tv_four,tv_five,tv_six,tv_seven,tv_eight,tv_nine;
	private LinearLayout ll_result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_calendar);

		initView();
		
		Date day=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd");
		date=sdf.format(day);
		queryCalendar();
		
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年7月1日 上午11:38:03<br>
	 * 方法描述：<br>
	 */
	private void initView() {
		RelativeLayout rl_left = (RelativeLayout) findViewById(R.id.rl_left);
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		RelativeLayout rl_right = (RelativeLayout) findViewById(R.id.rl_right);
		tv_title.setText("老黄历查询");
		rl_left.setVisibility(View.VISIBLE);
		rl_right.setVisibility(View.GONE);
		rl_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		bt_select_calendar = (Button) findViewById(R.id.bt_select_calendar);
		bt_select_calendar.setOnClickListener(this);
		
		ll_result = (LinearLayout) findViewById(R.id.ll_result);
		tv_one = (TextView) findViewById(R.id.tv_one);
		tv_two = (TextView) findViewById(R.id.tv_two);
		tv_three = (TextView) findViewById(R.id.tv_three);
		tv_four = (TextView) findViewById(R.id.tv_four);
		tv_five = (TextView) findViewById(R.id.tv_five);
		tv_six = (TextView) findViewById(R.id.tv_six);
		tv_seven = (TextView) findViewById(R.id.tv_seven);
		tv_eight = (TextView) findViewById(R.id.tv_eight);
		tv_nine = (TextView) findViewById(R.id.tv_nine);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_select_calendar:
			new PopupWindows(CalendarActivity.this, bt_select_calendar);
			break;
		}
	}

	/**
	 * 项目名称：BSTong<br>
	 * 类名称：PopupWindows<br>
	 * 类描述：弹出日历控件<br>
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年7月1日下午7:56:13<br>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注：
	 * @version V1.0
	 */
	public class PopupWindows extends PopupWindow {

		public PopupWindows(Context mContext, View parent) {

			View view = View.inflate(mContext, R.layout.popupwindow_calendar,
					null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_in));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_1));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();
			final TextView popupwindow_calendar_month = (TextView) view
					.findViewById(R.id.popupwindow_calendar_month);
			final CalendarView calendar = (CalendarView) view
					.findViewById(R.id.popupwindow_calendar);
			Button popupwindow_calendar_bt_enter = (Button) view
					.findViewById(R.id.popupwindow_calendar_bt_enter);
			popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
					+ calendar.getCalendarMonth() + "月");
			if (null != date) {
				int years = Integer.parseInt(date.substring(0,
						date.indexOf("-")));
				int month = Integer.parseInt(date.substring(
						date.indexOf("-") + 1, date.lastIndexOf("-")));
				popupwindow_calendar_month.setText(years + "年" + month + "月");
				calendar.showCalendar(years, month);
				calendar.setCalendarDayBgColor(date,
						R.drawable.calendar_date_focused);
			}
			List<String> list = new ArrayList<String>(); // 设置标记列表
			list.add("2014-04-01");
			list.add("2014-04-02");
			calendar.addMarks(list, 0);

			// 监听所选中的日期
			calendar.setOnCalendarClickListener(new OnCalendarClickListener() {

				public void onCalendarClick(int row, int col, String dateFormat) {
					int month = Integer.parseInt(dateFormat.substring(
							dateFormat.indexOf("-") + 1,
							dateFormat.lastIndexOf("-")));
					if (calendar.getCalendarMonth() - month == 1// 跨年跳转
							|| calendar.getCalendarMonth() - month == -11) {
						calendar.lastMonth();
					} else if (month - calendar.getCalendarMonth() == 1 // 跨年跳转
							|| month - calendar.getCalendarMonth() == -11) {
						calendar.nextMonth();
					} else {
						calendar.removeAllBgColor();
						calendar.setCalendarDayBgColor(dateFormat,
								R.drawable.calendar_date_focused);
						date = dateFormat;// 最后返回给全局 date
					}
				}
			});
			// 监听当前月份
			calendar.setOnCalendarDateChangedListener(new OnCalendarDateChangedListener() {
				public void onCalendarDateChanged(int year, int month) {
					popupwindow_calendar_month
							.setText(year + "年" + month + "月");
				}
			});
			// 上月监听按钮
			RelativeLayout popupwindow_calendar_last_month = (RelativeLayout) view
					.findViewById(R.id.popupwindow_calendar_last_month);
			popupwindow_calendar_last_month
					.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							calendar.lastMonth();
						}

					});
			// 下月监听按钮
			RelativeLayout popupwindow_calendar_next_month = (RelativeLayout) view
					.findViewById(R.id.popupwindow_calendar_next_month);
			popupwindow_calendar_next_month
					.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							calendar.nextMonth();
						}
					});
			// 关闭窗口
			popupwindow_calendar_bt_enter
					.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							dismiss();
							//查詢老黃歷
							if(date==null){
								Date day=new Date();
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								date=sdf.format(day);
							}
							queryCalendar();
						}
		   });
		}
	}
	
	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年7月4日 下午4:15:02<br>
	 * 方法描述：查詢老黃歷<br>
	 */
	private void queryCalendar() {
		HttpUtils httpUtils=new HttpUtils();
		httpUtils.send(HttpMethod.GET, HttpApi.IDENTITY_CALENDAR_URL+"?key="+Constants.CALENDAR_KEY
				+"&date="+date, new RequestCallBack<String>() {
					@Override
					public void onSuccess(
							ResponseInfo<String> responseInfo) {
						try{
							Gson gson=new Gson();
							IdentityBean identityBean = gson.fromJson(responseInfo.result, IdentityBean.class);
							if(identityBean!=null){
									ll_result.setVisibility(View.VISIBLE);
									tv_one.setText("阳历:"+identityBean.result.yangli);
									tv_two.setText("阴历:"+identityBean.result.yinli);
									tv_three.setText("五行:"+identityBean.result.wuxing);
									tv_four.setText("冲煞:"+identityBean.result.chongsha);
									tv_five.setText("拜祭:"+identityBean.result.baiji);
									tv_six.setText("吉神:"+identityBean.result.jishen);
									tv_seven.setText("凶神:"+identityBean.result.xiongshen);
									tv_eight.setText("宜:"+identityBean.result.yi);
									tv_nine.setText("忌:"+identityBean.result.ji);
							}
						}catch(Exception e){
						}
					}
					@Override
					public void onFailure(HttpException error,
							String msg) {
						ToastUtil.showBaseToast(UIUtils.getContext(), "网络连接失败");
					}
				});
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年7月1日 下午4:45:45<br>
	 * 方法描述：隐藏输入框<br>
	 */
	protected void hideInputMethod() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean isOpen = imm.isActive();
		if (isOpen) {
			imm.hideSoftInputFromWindow(CalendarActivity.this.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

}
