package com.sztb.socialsecurity.activity.person;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.order.OrderPayAct;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class PersonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_person);
		TopBar topBar = new TopBar(this, "资料编辑");
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(PersonActivity.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
