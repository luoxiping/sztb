package com.sztb.socialsecurity.activity.order;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class OrderPayAct extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_buy_order);
		TopBar topBar = new TopBar(this, "订单支付");
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(OrderPayAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_pay:
			
			break;

		default:
			break;
		}
	}

}
