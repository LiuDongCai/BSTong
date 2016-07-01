package com.liu.bstong.ui;

import com.liu.bstong.R;
import com.liu.bstong.base.BaseActivity;
import com.liu.bstong.util.SharedPreferencesUtil;
import com.liu.bstong.util.UIUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 项目名称：BSTong<br>
 * 类名称：SplashActivity<br>
 * 类描述：啟動頁<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年6月29日下午3:06:42<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class SplashActivity extends BaseActivity {

	/** 跳过按钮 **/
	private Button ivJump;
	/** 倒计时3秒 **/
	private int count = 3;
	/** 时间倒计时 **/
	private TextView tv_time;
	private RelativeLayout rl_start;
	/** 消息码 **/
	private final static int cur_what = 1;

	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case cur_what:
				if (!isFinishing()) {
					if (count > 0) {
						count--;
						tv_time.setText(count + "秒");
						tv_time.setVisibility(View.VISIBLE);
						if (count > 0) {
							handler.sendEmptyMessageDelayed(cur_what, 1000);
						} else {
							handler.sendEmptyMessageDelayed(cur_what, 200);
						}
					} else {
						tv_time.setVisibility(View.GONE);
							startActivity(new Intent(getApplication(),
									MainActivity.class));
						finish();
					}
				}
				break;
			case 100:
				enterHome();
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题
		setContentView(R.layout.activity_splash);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		onCreate();
	}

	private void onCreate() {
		initData();
		handler.sendEmptyMessageDelayed(cur_what, 200);
	}

	private void initData() {
		SharedPreferencesUtil sp=new SharedPreferencesUtil("LOCATION", UIUtils.getContext());
        sp.setBoolean("LOCATION", false);
		// 成員變量
		tv_time = (TextView) findViewById(R.id.iv_time);
		tv_time.setText(count + "秒");
		tv_time.setVisibility(View.VISIBLE);
		ivJump = (Button) findViewById(R.id.iv_jump);
		ivJump.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isFinishing()) {
					handler.removeMessages(cur_what);
					startActivity(new Intent(getApplication(),
							MainActivity.class));
					finish();
				}
			}
		});
		
		rl_start=(RelativeLayout) findViewById(R.id.rl_start);
		//进入start界面,使用渐变动画,透明度动画
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.3F, 1);
        alphaAnimation.setDuration(2000);
        rl_start.startAnimation(alphaAnimation);
        
        alphaAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				//动画结束后跳转【2秒】
				handler.sendEmptyMessageDelayed(100,0);
			}
		});
	}
	
	/**
     * 进入主界面
     */
    protected void enterHome() {
		Intent intent=new Intent(UIUtils.getContext(), MainActivity.class);
		startActivity(intent);
		finish();
	}

}
