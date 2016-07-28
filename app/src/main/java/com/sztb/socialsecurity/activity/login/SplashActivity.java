package com.sztb.socialsecurity.activity.login;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.MainActivity;
import com.sztb.socialsecurity.utils.SharePreferenceUtil;
import com.sztb.socialsecurity.utils.Utils;

public class SplashActivity extends Activity {
	private static final String TAG = "SplashActivity";

	private ArrayList<ImageView> pointViews;
	private Integer[] imageArr = { R.mipmap.splash_bg_1,
			R.mipmap.splash_bg_2 };
	private LinearLayout point_linear;
	private ViewPager mViewPager;
//	private FrameLayout fl_splash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
//		getWindow().setFlags(WindowManager.LayoutParams.FILL_PARENT,
//				WindowManager.LayoutParams.FILL_PARENT); // 设置全屏
		setContentView(R.layout.activity_splash);
//		fl_splash = (FrameLayout) findViewById(R.id.fl_splash);
		FrameLayout fl_splash = (FrameLayout) findViewById(R.id.fl_splash);
		setImmerseLayout(fl_splash);
		mViewPager = (ViewPager) findViewById(R.id.splash_viewpager);
//		StatusBarUtil.setImmerseLayout(this, fl_splash);
		point_linear = (LinearLayout) findViewById(R.id.splash_point_linear);
		initPoint();
		setViewpagerData();
	}
	
	protected void setImmerseLayout(View view) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
	}

	/**
	 * 初始小圆点游标
	 */
	private void initPoint() {
		pointViews = new ArrayList<ImageView>();
		ImageView imageView;
		for (int i = 0; i < imageArr.length; i++) {
			imageView = new ImageView(this);
			imageView.setBackgroundResource(R.mipmap.splash_white_circle_normal);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					new LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 5;
			layoutParams.rightMargin = 5;
			layoutParams.width = 25;// 图片大小
			layoutParams.height = 25;
			point_linear.addView(imageView, layoutParams);
			if (i == 0) {
				imageView.setBackgroundResource(R.mipmap.splash_white_circle_press);
			}
			pointViews.add(imageView);
		}
	}

	/**
	 * 填充数据
	 */
	@SuppressWarnings("deprecation")
	private void setViewpagerData() {
		mViewPager.setAdapter(new SamplePagerAdapter());
		mViewPager.setCurrentItem(0);
		mViewPager.setOffscreenPageLimit(1);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				for (int i = 0; i < pointViews.size(); i++) {
					if (arg0 == i) {
						pointViews.get(i).setBackgroundResource(
								R.mipmap.splash_white_circle_press);
					} else {
						pointViews.get(i).setBackgroundResource(
								R.mipmap.splash_white_circle_normal);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	/**
	 * 适配器
	 * 
	 * @author Administrator
	 *
	 */
	private class SamplePagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageArr.length;
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			View view = LayoutInflater.from(SplashActivity.this).inflate(
					R.layout.splash_viewpager_item, null, false);
			ImageView iv = (ImageView) view
					.findViewById(R.id.splash_viewpager_item_iv);
			iv.setBackgroundResource(imageArr[position]);
			TextView btn = (TextView) view
					.findViewById(R.id.splash_viewpager_item_btn_start);
			if (imageArr.length - 1 == position) {
				btn.setVisibility(View.VISIBLE);
				btn.setOnClickListener(getOnClickListener());
			} else {
				btn.setVisibility(View.GONE);
			}
			container.addView(view, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			return view;
		}

		/**
		 * 点击事件
		 * 
		 * @return
		 */
		private OnClickListener getOnClickListener() {
			return new OnClickListener() {

				@Override
				public void onClick(View v) {
					new Handler().postDelayed(new Runnable() {
						
						@Override
						public void run() {
							// 第一次登陆，进入引导页
							SharePreferenceUtil.getInstance(SplashActivity.this).setBoolean(
									"main_first", true);
							Utils.startActivity(SplashActivity.this, MainActivity.class);
							finish();
						}
					}, 800);
				}
			};
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}
