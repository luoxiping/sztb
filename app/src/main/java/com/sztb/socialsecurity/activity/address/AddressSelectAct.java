package com.sztb.socialsecurity.activity.address;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

public class AddressSelectAct extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.act_address);
		TopBar topBar = new TopBar(this, "参保城市"); 
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(AddressSelectAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
