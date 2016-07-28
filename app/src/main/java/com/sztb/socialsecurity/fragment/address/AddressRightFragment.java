package com.sztb.socialsecurity.fragment.address;

import java.util.ArrayList;
import java.util.List;
import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.adapter.address.AddressRightAdapter;
import com.sztb.socialsecurity.widget.xlist.XListView;
import com.sztb.socialsecurity.widget.xlist.XListView.IXListViewListener;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddressRightFragment extends Fragment implements IXListViewListener {
	private Activity mActivity;
	private AddressRightAdapter adapter;
	private XListView xListView;
	private List<String> list = new ArrayList<String>();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_address_right, null);
		list.add("北京");
		list.add("上海");
		list.add("深圳");
		list.add("广州");
		xListView = (XListView) view.findViewById(R.id.xListView);
		adapter = new AddressRightAdapter(mActivity, list);
		xListView.setAdapter(adapter);
		xListView.setPullLoadEnable(false);
		xListView.setPullRefreshEnable(false);// 下拉刷新
		xListView.setXListViewListener(this);// 给xListView设置监听
		return view;
	}

	@Override
	public void onRefresh() {
		
	}

	@Override
	public void onLoadMore() {
		
	}

}
