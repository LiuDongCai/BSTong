package com.liu.bstong.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;

import com.liu.bstong.R;
import com.liu.bstong.base.BaseFragment;
import com.liu.bstong.config.Constants;
import com.liu.bstong.ui.CalendarActivity;
import com.liu.bstong.ui.IdentityActivity;
import com.liu.bstong.util.UIUtils;

/**
 * 项目名称：BSTong<br>
 * 类名称：MapFragment<br>
 * 类描述：搜索界面<br>
 * 创建人：刘栋财<br>
 * 创建时间：2016年6月30日下午4:14:42<br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：
 * @version V1.0
 */
public class SearchFragment extends BaseFragment  {
	
	private GridView gridView;

	@Override
	public View onCreateFragmentView(LayoutInflater inflater,
			ViewGroup container) {
		View view = inflater.inflate(R.layout.activity_search, container, false);
        return view;
	}

	@Override
	protected void initViews(View view) {
		gridView=(GridView) view.findViewById(R.id.gv_gridView);
		initData();
	}

	/**
	 * 创建人：刘栋财<br>
	 * 创建时间：2016年6月30日 下午7:01:32<br>
	 * 方法描述：初始化数据<br>
	 */
	private void initData() {
		//设置适配器
				gridView.setAdapter(adapter);
				
				//给九宫格的每个选项设置点击事件
				gridView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent=new Intent();
						switch(position){
							case 0:
								//身份证查询
								intent.setClass(UIUtils.getContext(), IdentityActivity.class);
								startActivity(intent);
								break;
							case 1:
								//老黄历查询
								intent.setClass(UIUtils.getContext(), CalendarActivity.class);
								startActivity(intent);
								break;
							case 2:
								//软件管理
//								intent.setClass(context, AppManagerActivity.class);
//								startActivity(intent);
								break;
							case 3:
								//进程管理
//								intent.setClass(context, ProcessManagerActivity.class);
//								startActivity(intent);
								break;
							case 4:
								//流量统计
//								intent.setClass(context, TrafficActivity.class);
//								startActivity(intent);
								break;
							case 5:
								//手机杀毒
//								intent.setClass(context, KillVirusActivity.class);
//								startActivity(intent);
								break;
							case 6:
								//缓存清理
//								intent.setClass(context, CacheTabActivity.class);
//								startActivity(intent);
								break;
							case 7:
								//高级工具
//								intent.setClass(context, SeniorToolsActivity.class);
//								startActivity(intent);
								break;
								//设置中心
							case 8:
//								intent.setClass(context, SettingActivity.class);
//								startActivity(intent);
								break;
						}
					}
				});
	}

	//适配器
	private BaseAdapter adapter=new BaseAdapter() {
		//返回视图
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//将布局文件转换为有层次结构的视图对象
			View view = View.inflate(UIUtils.getContext(), R.layout.home_item, null);
			//获取手机屏幕的宽
			int screenWidth=getResources().getDisplayMetrics().widthPixels;
			//设置对象的宽和高的参数
			LayoutParams layoutParams=new LayoutParams(screenWidth/3, screenWidth/3);
			view.setLayoutParams(layoutParams);
			ImageView imageHome=(ImageView) view.findViewById(R.id.iv_imageHome);
			TextView itemText=(TextView) view.findViewById(R.id.tv_itemText);
			imageHome.setBackgroundResource(Constants.SEARCH_ICON[position]);
			itemText.setText(Constants.SEARCH_NAME[position]);
			return view;
		}
		//返回个数
		@Override
		public int getCount() {
			return Constants.SEARCH_ICON.length;
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
	};
	
}