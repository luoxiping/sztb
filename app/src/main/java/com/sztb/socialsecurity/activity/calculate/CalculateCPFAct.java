package com.sztb.socialsecurity.activity.calculate;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class CalculateCPFAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_calculate_cpf);
		TopBar topBar = new TopBar(this, "公积金计算");
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(CalculateCPFAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
