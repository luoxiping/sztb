package com.sztb.socialsecurity.activity.how;

import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.utils.Utils;
import com.sztb.socialsecurity.widget.TopBar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WhyBuyAct extends Activity {
	private TextView text_call, text_buy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_why_buy);
		TopBar topBar = new TopBar(this, "为啥要买");
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		try {
			Utils.setActionBarTitle(WhyBuyAct.this, start_linear_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
		initWidget();
		addListener();
	}

	private void addListener() {
		text_call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Utils.callPhone(WhyBuyAct.this);
			}
		});
		text_buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
	}

	private void initWidget() {
		text_call = (TextView) findViewById(R.id.text_call);
		text_buy = (TextView) findViewById(R.id.text_buy);
	}

}
