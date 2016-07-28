package com.sztb.socialsecurity.activity.adapter;

import java.util.List;
import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.buy.BuyCPFAct;
import com.sztb.socialsecurity.activity.buy.BuySocialSecurityAct;
import com.sztb.socialsecurity.activity.calculate.CalculateAct;
import com.sztb.socialsecurity.activity.calculate.CalculateCPFAct;
import com.sztb.socialsecurity.activity.how.HowBuyAct;
import com.sztb.socialsecurity.activity.how.HowQueryAct;
import com.sztb.socialsecurity.activity.how.WhyBuyAct;
import com.sztb.socialsecurity.activity.login.LoginActivity;
import com.sztb.socialsecurity.activity.query.QuerySocialAct;
import com.sztb.socialsecurity.utils.Utils;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
	private Activity mActivity;
	private LayoutInflater inflater;
	private List<String> imageList;
	
	public MainAdapter(Activity mActivity, List<String> imageList){
		this.mActivity = mActivity;
		inflater = LayoutInflater.from(mActivity);
		this.imageList = imageList;
	}
	
	@Override
	public int getCount() {
		return 1;
	}

	@Override
	public Object getItem(int position) {
		return imageList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.fragment_main, null);
			holder.ll_login = (LinearLayout) convertView.findViewById(R.id.ll_login);
			holder.ll_buysocial = (LinearLayout) convertView.findViewById(R.id.ll_buysocial);
			holder.ll_buy_cpf = (LinearLayout) convertView.findViewById(R.id.ll_buy_cpf);
			holder.ll_query = (LinearLayout) convertView.findViewById(R.id.ll_query);
			holder.ll_calculate = (LinearLayout) convertView.findViewById(R.id.ll_calculate);
			holder.ll_calculate_cpf = (LinearLayout) convertView.findViewById(R.id.ll_calculate_cpf);
			holder.ll_why_buy = (LinearLayout) convertView.findViewById(R.id.ll_why_buy);
			holder.ll_how_buy = (LinearLayout) convertView.findViewById(R.id.ll_how_buy);
			holder.ll_how_query = (LinearLayout) convertView.findViewById(R.id.ll_how_query);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.ll_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, LoginActivity.class);
			}
		});
		holder.ll_buysocial.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, BuySocialSecurityAct.class);
			}
		});
		holder.ll_buy_cpf.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, BuyCPFAct.class);
			}
		});
		holder.ll_query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, QuerySocialAct.class);
			}
		});
		holder.ll_calculate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, CalculateAct.class);
			}
		});
		holder.ll_calculate_cpf.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, CalculateCPFAct.class);
			}
		});
		holder.ll_why_buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, WhyBuyAct.class);
			}
		});
		holder.ll_how_buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, HowBuyAct.class);
			}
		});
		holder.ll_how_query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, HowQueryAct.class);
			}
		});
		return convertView;
	}
	
	static class ViewHolder{
		LinearLayout ll_login, ll_buysocial, ll_buy_cpf, ll_query, ll_calculate, ll_calculate_cpf, ll_why_buy, ll_how_buy, ll_how_query;
		TextView ms;
		View line1;
		TextView text_set;
		RelativeLayout rl_all;
	}

}
