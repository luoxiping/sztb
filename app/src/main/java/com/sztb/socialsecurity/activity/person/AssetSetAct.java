package com.sztb.socialsecurity.activity.person;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class AssetSetAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_zcb_set);
		TopBar topBar = new TopBar(this, "资产宝设置");
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(AssetSetAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
