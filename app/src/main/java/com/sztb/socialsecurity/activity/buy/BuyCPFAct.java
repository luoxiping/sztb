package com.sztb.socialsecurity.activity.buy;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;
import com.sztb.socialsecurity.widget.TopBar.BtnRightListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class BuyCPFAct extends Activity implements BtnRightListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_cpf);
		TopBar topBar = new TopBar(this, "购买公积金");
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(BuyCPFAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void btnRightLis() {
	}
}
