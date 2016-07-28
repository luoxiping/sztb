package com.sztb.socialsecurity.activity;

import org.kymjs.kjframe.KJActivity;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.fragment.MainFragment;
import com.sztb.socialsecurity.fragment.MineFragment;
import com.sztb.socialsecurity.utils.SharePreferenceUtil;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.CustomViewPager;
import com.sztb.socialsecurity.widget.SystemBarTintManager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends KJActivity {
	public static final int RESULT_CALL = 0X12;
	public static final int MAKE_LIVE = 0X13;
	private String[] fragmetns = new String[] {
			MainFragment.class.getName(),
			MainFragment.class.getName(),
			MineFragment.class.getName() };
	private CustomViewPager vpContainer;  
    private int mCurrentFragment;
    private RadioGroup rgTabButtons;
//    private TextView titleText;

	@Override
	public void widgetClick(View v) {
		super.widgetClick(v);
		switch (v.getId()) {
		case R.id.bottombar_content1:
			mCurrentFragment = 0;
			slideLayout();
			break;
		case R.id.bottombar_content2:
//			mCurrentFragment = 1;
			slideLayout();
			break;
		case R.id.bottombar_content3:
			mCurrentFragment = 2;
			slideLayout();
			break;

		default:
			break;
		}
	}
	
	private void slideLayout(){
		vpContainer.setCurrentItem(mCurrentFragment);
	}

	@Override
	public void setRootView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		setContentView(R.layout.activity_main);
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			setActionBarTitle(start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
		InitViewPager();
	}
	
	/**
	 * 设置系统状态栏的颜色
	 * Exception 
	 */
	@SuppressWarnings("unused")
	private void setActionBarTitle(LinearLayout linear_main) throws Exception{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window win = getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			if (true) {
				winParams.flags |= bits;
			} else {
				winParams.flags &= ~bits;
			}
			 win.setAttributes(winParams);
			 
			 int  status =  SharePreferenceUtil.getInstance(MainActivity.this).getInt("status", 0);
			 if(status == 0){
				 Class<?> c = Class.forName("com.android.internal.R$dimen");
			     Object  obj = c.newInstance();
			     java.lang.reflect.Field  field = c.getField("status_bar_height");
				 int  x = Integer.parseInt(field.get(obj).toString());
				 status = getResources().getDimensionPixelSize(x);//获取状态栏的高度
				 SharePreferenceUtil.getInstance(MainActivity.this).setInt("status", status);
			 }
			 SystemBarTintManager tintManager = new SystemBarTintManager(this);
	         tintManager.setStatusBarTintEnabled(true);
	         linear_main.setPadding(0,status, 0, 0);
	     	 tintManager.setStatusBarTintResource(R.color.mine_bg); 
		}
	}
	
//	protected void setImmerseLayout() {
//		//设定状态栏的颜色，当版本大于4.4时起作用
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(R.color.mine_bg);
//        }
//	}
	
	/* 
     * 初始化ViewPager 
     */  
    public void InitViewPager(){  
    	vpContainer = (CustomViewPager)findViewById(R.id.viewpager);
    	vpContainer.setOffscreenPageLimit(3);
    	rgTabButtons = (RadioGroup) findViewById(R.id.bottombar_group);
//    	titleText = (TextView) findViewById(R.id.top_title);
//    	titleText.setText("话题");
		KickerFragmentAdapter adpater = new KickerFragmentAdapter(getSupportFragmentManager(), this);
		vpContainer.setOnPageChangeListener(onPageChangeListener);
		vpContainer.setAdapter(adpater);
		vpContainer.setCurrentItem(mCurrentFragment);
		rgTabButtons.setOnCheckedChangeListener(onCheckedChangeListener);
		((RadioButton) rgTabButtons.getChildAt(0)).setChecked(true);
    }
    
    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int checkedItem = 0;
			switch (checkedId) {
			case R.id.bottombar_content1:
				checkedItem = 0;
				vpContainer.setCurrentItem(checkedItem);
				mCurrentFragment = checkedItem;
				break;
			case R.id.bottombar_content2:
//				checkedItem = 1;
				((RadioButton) rgTabButtons.getChildAt(mCurrentFragment)).setChecked(true);
				Utils.callPhone(MainActivity.this);
				break;
			case R.id.bottombar_content3:
				checkedItem = 2;
				vpContainer.setCurrentItem(checkedItem);
				mCurrentFragment = checkedItem;
				break;
			}
			
		}
	};
    
    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
		@Override
		public void onPageSelected(int arg0) {
//			switch (arg0) {
//			case 0:
//				titleText.setText("话题");
//				break;
//			case 1:
//				titleText.setText("直播间");
//				break;
//			case 2:
//				titleText.setText("我的");
//				break;
//			default:
//				break;
//			}
			mCurrentFragment = arg0;
			((RadioButton) rgTabButtons.getChildAt(arg0)).setChecked(true);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};
    
    class KickerFragmentAdapter extends FragmentPagerAdapter {

		private Context mContext;

		public KickerFragmentAdapter(FragmentManager fm, Context context) {
			super(fm);
			mContext = context;
		}

		@Override
		public Fragment getItem(int arg0) {
			return Fragment.instantiate(mContext, fragmetns[arg0]);
		}

		@Override
		public int getCount() {
			return fragmetns.length;
		}

	}
    
    private long mExitTime;

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				if (!SharePreferenceUtil.getInstance(MainActivity.this).getBoolean("main_first", false)) {
					SharePreferenceUtil.getInstance(MainActivity.this).setBoolean("main_first", true);
				}
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
    
}
