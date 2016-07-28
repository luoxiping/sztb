package com.sztb.socialsecurity.activity.person;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.utils.SharePreferenceUtil;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.SystemBarTintManager;
import com.sztb.socialsecurity.widget.TopBar;
import com.sztb.socialsecurity.widget.TopBar.BtnRightListener;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class AssetAct extends Activity implements BtnRightListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_asset);
		TopBar topBar = new TopBar(this, "资产宝");
		topBar.setBtnRightListener("", this);
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			setActionBarTitle(AssetAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void setActionBarTitle(Activity mActivity, LinearLayout linear_main) throws Exception {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window win = mActivity.getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			if (true) {
				winParams.flags |= bits;
			} else {
				winParams.flags &= ~bits;
			}
			win.setAttributes(winParams);

			int status = SharePreferenceUtil.getInstance(mActivity).getInt("status", 0);
			if (status == 0) {
				Class<?> c = Class.forName("com.android.internal.R$dimen");
				Object obj = c.newInstance();
				java.lang.reflect.Field field = c.getField("status_bar_height");
				int x = Integer.parseInt(field.get(obj).toString());
				status = mActivity.getResources().getDimensionPixelSize(x);// 获取状态栏的高度
				SharePreferenceUtil.getInstance(mActivity).setInt("status", status);
			}
			SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
			tintManager.setStatusBarTintEnabled(true);
			linear_main.setPadding(0, status, 0, 0);
			tintManager.setStatusBarTintResource(R.color.mine_bg);
		}
	}

	@Override
	public void btnRightLis() {
		Utils.startActivity(AssetAct.this, AssetSetAct.class);
	}
	
}
