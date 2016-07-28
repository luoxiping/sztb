package com.sztb.socialsecurity.activity.how;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class HowQueryAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_how_query);
		TopBar topBar = new TopBar(this, "如何查询");
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(HowQueryAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
