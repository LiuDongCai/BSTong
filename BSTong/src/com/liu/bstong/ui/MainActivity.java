package com.liu.bstong.ui;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import com.liu.bstong.R;
import com.liu.bstong.base.BaseActivity;
import com.liu.bstong.fragment.HomeFragment;
import com.liu.bstong.fragment.SearchFragment;
import com.liu.bstong.fragment.MainFragment3;
import com.liu.bstong.fragment.MoreFragment;
import com.liu.bstong.util.ToastUtil;
import com.liu.bstong.util.UIUtils;
import com.liu.bstong.widget.GradientTab;
import com.liu.bstong.widget.NoScrollViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 项目名称：BSTong<br>
 * 类名称：MainActivity<br>
 * 类描述：主界面<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年6月30日下午1:44:23<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * 
 * @version V1.0
 */
public class MainActivity extends BaseActivity {

	private NoScrollViewPager viewPager;
	/**
	 * 底部Tab指示器容器
	 */
	private LinearLayout lltabs;

	/**
	 * 指示器名称
	 */
	private String[] TAB_ITEMS = new String[] { "首页", "查询", "发现", "更多" };

	/**
	 * 指示器图标
	 */
	private int[] TAB_ICONS = new int[] { R.drawable.icon_tab_1,
			R.drawable.icon_tab_2, R.drawable.icon_tab_3,
			R.drawable.icon_tab_4, };

	private Fragment[] fragments = new Fragment[4];

	/**
	 * 底部切换的四个指示器控件
	 */
	private GradientTab[] tabs = new GradientTab[4];

	private PagerAdapter mPagerAdapter = new FragmentPagerAdapter(
			getSupportFragmentManager()) {
		@Override
		public Fragment getItem(int i) {
			return fragments[i];
		}

		@Override
		public int getCount() {
			return fragments.length;
		}
	};

	private NoScrollViewPager.OnPageChangeListener mOnPageChangeListener = new NoScrollViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// 设置tab指示器切换时的渐变效果
			// positionOffset的值为： 0 --> 1
			if (positionOffset > 0) {
				// 按操作的两个指示器
				GradientTab left = tabs[position];
				GradientTab right = tabs[position + 1];
				// 更改指示器的透明度
				left.updateTabAlpha(1 - positionOffset);
				right.updateTabAlpha(positionOffset);
			}
		}

		@Override
		public void onPageSelected(int position) {
			onTabClick(position);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setAlwaysShowOverflowMenu();
		initDatas();
		initViews();

		initGlobalListener();
	}

	private void initGlobalListener() {

	}

	private void initViews() {
		RelativeLayout rl_left = (RelativeLayout) findViewById(R.id.rl_left);
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		RelativeLayout rl_right = (RelativeLayout) findViewById(R.id.rl_right);
		tv_title.setText("百事通");
		rl_left.setVisibility(View.GONE);
		rl_right.setVisibility(View.GONE);

		this.lltabs = (LinearLayout) findViewById(R.id.ll_tabs);
		this.viewPager = (NoScrollViewPager) findViewById(R.id.id_viewpager);
		this.viewPager.setAdapter(mPagerAdapter);
		this.viewPager.setOnPageChangeListener(mOnPageChangeListener);
		viewPager.setNoScroll(true);
		initTabs();
	}

	private void initDatas() {
		fragments[0] = new HomeFragment();
		fragments[1] = new SearchFragment();
		fragments[2] = new MainFragment3();
		fragments[3] = new MoreFragment();
	}

	/**
	 * 动态创建指示器控件，并添加到容器父控件中
	 */
	private void initTabs() {
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.MATCH_PARENT);
		param.weight = 1;
		int padding = UIUtils.dip2px(5);
		for (int i = 0; i < 4; i++) {
			GradientTab tab = new GradientTab(this);
			tabs[i] = tab; // 保存tab控件以便后面操作
			tab.setTag(i); // 设置标识以作区分
			tab.setTextAndIcon(TAB_ITEMS[i], TAB_ICONS[i]);
			tab.setPadding(0, padding, 0, padding);
			tab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int index = (Integer) v.getTag();
					onTabClick(index);
				}
			});
			lltabs.addView(tab, param);
		}

		// 默认让第一项选中
		tabs[0].setTabSelected(true);
	}

	/**
	 * 指示器切换
	 *
	 * @param index
	 *            当前显示位置
	 */
	private void onTabClick(int index) {
		resetAllTabs();
		tabs[index].setTabSelected(true);
		mCurrentTabIndex = index;
		// viewpager显示指定的子视图，false表示切换没有动画
		viewPager.setCurrentItem(index, false);

	}

	private int mCurrentTabIndex = 0;

	private void resetAllTabs() {
		for (int i = 0; i < tabs.length; i++) {
			tabs[i].setTabSelected(false);
		}
	}

	/**
	 * 总是显示actionbar右边的溢出菜单项，如果不做此设置， 默认按下菜单键时，会从界面底部弹出选项菜单
	 */
	private void setAlwaysShowOverflowMenu() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 默认情况下，ActionBar溢出按钮（overflow）弹出菜单项是不会显示左边的图标的，
	 * 如果要让他显示出来，得设置MenuBuilder类的setOptionalIconsVisible()方法，参数传true
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	/**
	 * 菜单、返回键响应
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // 调用双击退出函数
		}
		return false;
	}

	/**
	 * 双击退出函数
	 */
	private static Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			ToastUtil.showBaseToast(this, "再按一次退出程序");
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
		} else {
			finish();
			System.exit(0);
		}
	}

}
