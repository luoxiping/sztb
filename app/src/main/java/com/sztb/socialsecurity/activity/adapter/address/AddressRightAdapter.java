package com.sztb.socialsecurity.activity.adapter.address;

import java.util.List;
import com.sztb.socialsecurity.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddressRightAdapter extends BaseAdapter {
	private Activity mActivity;
	private LayoutInflater inflater;
	private List<String> imageList;
	
	public AddressRightAdapter(Activity mActivity, List<String> imageList){
		this.mActivity = mActivity;
		inflater = LayoutInflater.from(mActivity);
		this.imageList = imageList;
	}
	
	@Override
	public int getCount() {
		if (imageList == null || imageList.size() == 0) {
			return 0;
		} else {
			return imageList.size();
		}
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
			convertView = inflater.inflate(R.layout.adapter_address_right, null);
			holder.address_text = (TextView) convertView.findViewById(R.id.address_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (imageList != null && imageList.size() > 0) {
			holder.address_text.setText(imageList.get(position));
		}
		return convertView;
	}
	
	static class ViewHolder{
		TextView address_text;
	}

}
