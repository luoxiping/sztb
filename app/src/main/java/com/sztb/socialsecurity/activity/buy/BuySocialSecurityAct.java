package com.sztb.socialsecurity.activity.buy;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.address.AddressSelectAct;
import com.sztb.socialsecurity.activity.order.OrderPayAct;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;
import com.sztb.socialsecurity.widget.TopBar.BtnRightListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BuySocialSecurityAct extends Activity implements BtnRightListener {
	private TextView text_buy;
	private RelativeLayout rl_city;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_buy_socail_security);
		TopBar topBar = new TopBar(this, "购买社保");
		topBar.setBtnRightListener("", this);
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(BuySocialSecurityAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
		initWidget();
		addListener();
	}

	private void initWidget() {
		text_buy = (TextView) findViewById(R.id.text_buy);
		rl_city = (RelativeLayout) findViewById(R.id.rl_city);
	}

	private void addListener() {
		text_buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(BuySocialSecurityAct.this, OrderPayAct.class);
			}
		});
		rl_city.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(BuySocialSecurityAct.this, AddressSelectAct.class);
			}
		});
	}

	@Override
	public void btnRightLis() {
		// TODO Auto-generated method stub
		
	}

}
