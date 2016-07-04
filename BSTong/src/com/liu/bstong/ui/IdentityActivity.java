package com.liu.bstong.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

/**
 * 项目名称：BSTong<br>
 * 类名称：IdentityActivity<br>
 * 类描述：身份证查询界面<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年6月30日下午1:44:23<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class IdentityActivity extends BaseActivity implements OnClickListener{

	private EditText et_identity;
	private LinearLayout ll_result;
	private TextView tv_one,tv_two,tv_three;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_identity);
		
		initView();
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年7月1日 上午11:38:03<br>
	 * 方法描述：<br>
	 */
	private void initView() {
		RelativeLayout rl_left=(RelativeLayout) findViewById(R.id.rl_left);
		TextView tv_title=(TextView) findViewById(R.id.tv_title);
		RelativeLayout rl_right=(RelativeLayout) findViewById(R.id.rl_right);
		tv_title.setText("身份证查询");
		rl_left.setVisibility(View.VISIBLE);
		rl_right.setVisibility(View.GONE);
		rl_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		Button bt_msg_query=(Button) findViewById(R.id.bt_msg_query);
		Button bt_reveal_query=(Button) findViewById(R.id.bt_reveal_query);
		Button bt_lose_query=(Button) findViewById(R.id.bt_lose_query);
		et_identity = (EditText) findViewById(R.id.et_identity);
		ll_result = (LinearLayout) findViewById(R.id.ll_result);
		tv_one = (TextView) findViewById(R.id.tv_one);
		tv_two = (TextView) findViewById(R.id.tv_two);
		tv_three = (TextView) findViewById(R.id.tv_three);
		
		bt_msg_query.setOnClickListener(this);
		bt_reveal_query.setOnClickListener(this);
		bt_lose_query.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String identity_num = et_identity.getText().toString().trim();
		switch (v.getId()) {
		case R.id.bt_msg_query:
			if(TextUtils.isEmpty(identity_num)){
				ToastUtil.showBaseToast(UIUtils.getContext(), "请输入身份证号码");
				//抖动效果
				Animation loadAnimation = AnimationUtils.loadAnimation(UIUtils.getContext(), R.anim.shake);
				et_identity.startAnimation(loadAnimation);
			}else{
				hideInputMethod();
				HttpUtils httpUtils=new HttpUtils();
				httpUtils.send(HttpMethod.GET, HttpApi.IDENTITY_MSG_URL+"?key="+Constants.IDENTITY_KEY
						+"&cardno="+identity_num, new RequestCallBack<String>() {
							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								try{
									Gson gson=new Gson();
									IdentityBean identityBean = gson.fromJson(responseInfo.result, IdentityBean.class);
									if(identityBean!=null){
										if(identityBean.resultcode.equals("200")){
											ll_result.setVisibility(View.VISIBLE);
											tv_one.setText("地址:"+identityBean.result.area);
											tv_two.setText("性别:"+identityBean.result.sex);
											tv_three.setVisibility(View.VISIBLE);
											tv_three.setText("生日:"+identityBean.result.birthday);
										}else if(identityBean.resultcode.equals("203")){
											ll_result.setVisibility(View.VISIBLE);
											tv_one.setText("地址:"+identityBean.result.area);
											tv_two.setText("性别:"+identityBean.result.sex);
											tv_three.setVisibility(View.VISIBLE);
											tv_three.setText("生日:"+identityBean.result.birthday);
											ToastUtil.showBaseToast(UIUtils.getContext(), identityBean.reason);
										}else{
											ToastUtil.showBaseToast(UIUtils.getContext(), identityBean.reason);
										}
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
			break;
		case R.id.bt_reveal_query:
			if(TextUtils.isEmpty(identity_num)){
				ToastUtil.showBaseToast(UIUtils.getContext(), "请输入身份证号码");
				//抖动效果
				Animation loadAnimation = AnimationUtils.loadAnimation(UIUtils.getContext(), R.anim.shake);
				et_identity.startAnimation(loadAnimation);
			}else{
				hideInputMethod();
				HttpUtils httpUtils=new HttpUtils();
				httpUtils.send(HttpMethod.GET, HttpApi.IDENTITY_REVEAL_URL+"?key="+Constants.IDENTITY_KEY
						+"&cardno="+identity_num, new RequestCallBack<String>() {
							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								try{
									Gson gson=new Gson();
									IdentityBean identityBean = gson.fromJson(responseInfo.result, IdentityBean.class);
									if(identityBean!=null){
										if(identityBean.resultcode.equals("200")){
											ll_result.setVisibility(View.VISIBLE);
											tv_one.setText("身份证号码:"+identityBean.result.cardno);
											tv_two.setText("结果:"+identityBean.result.tips);
											tv_three.setVisibility(View.GONE);
										}else{
											ToastUtil.showBaseToast(UIUtils.getContext(), identityBean.reason);
										}
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
			break;
		case R.id.bt_lose_query:
			if(TextUtils.isEmpty(identity_num)){
				ToastUtil.showBaseToast(UIUtils.getContext(), "请输入身份证号码");
				//抖动效果
				Animation loadAnimation = AnimationUtils.loadAnimation(UIUtils.getContext(), R.anim.shake);
				et_identity.startAnimation(loadAnimation);
			}else{
				hideInputMethod();
				HttpUtils httpUtils=new HttpUtils();
				httpUtils.send(HttpMethod.GET, HttpApi.IDENTITY_LOSE_URL+"?key="+Constants.IDENTITY_KEY
						+"&cardno="+identity_num, new RequestCallBack<String>() {
							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								try{
									Gson gson=new Gson();
									IdentityBean identityBean = gson.fromJson(responseInfo.result, IdentityBean.class);
									if(identityBean!=null){
										if(identityBean.resultcode.equals("200")){
											ll_result.setVisibility(View.VISIBLE);
											tv_one.setText("身份证号码:"+identityBean.result.cardno);
											tv_two.setText("结果:"+identityBean.result.tips);
											tv_three.setVisibility(View.GONE);
										}else{
											ToastUtil.showBaseToast(UIUtils.getContext(), identityBean.reason);
										}
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
			break;
		}
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
					imm.hideSoftInputFromWindow(IdentityActivity.this
							.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	
}
