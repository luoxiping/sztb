package com.sztb.socialsecurity.activity.adapter;

import java.util.List;
import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.person.AssetAct;
import com.sztb.socialsecurity.activity.person.PersonActivity;
import com.sztb.socialsecurity.utils.Utils;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MineAdapter extends BaseAdapter {
	private Activity mActivity;
	private LayoutInflater inflater;
	private List<String> imageList;
	
	public MineAdapter(Activity mActivity, List<String> imageList){
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
			convertView = inflater.inflate(R.layout.fragment_mine, null);
			holder.rl_person = (RelativeLayout) convertView.findViewById(R.id.rl_person);
			holder.rl_head = (RelativeLayout) convertView.findViewById(R.id.rl_head);
			holder.ll_balance = (LinearLayout) convertView.findViewById(R.id.ll_balance);
			holder.ll_jifen = (LinearLayout) convertView.findViewById(R.id.ll_jifen);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.rl_person.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, PersonActivity.class);
			}
		});
		holder.ll_balance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Utils.startActivity(mActivity, AssetAct.class);
			}
		});
		holder.rl_head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Utils.startActivity(mActivity, PersonActivity.class);
			}
		});
		holder.ll_jifen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Utils.startActivity(mActivity, AssetAct.class);
			}
		});
		return convertView;
	}
	
	static class ViewHolder{
		RelativeLayout rl_person, rl_head;
		LinearLayout ll_balance, ll_jifen;
	}

}
