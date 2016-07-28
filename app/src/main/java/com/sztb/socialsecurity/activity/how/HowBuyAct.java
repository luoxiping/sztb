package com.sztb.socialsecurity.activity.how;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.calculate.CalculateCPFAct;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class HowBuyAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_how_buy);
		TopBar topBar = new TopBar(this, "如何购买");
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(HowBuyAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
