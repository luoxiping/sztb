package com.sztb.socialsecurity.fragment;

import java.util.ArrayList;
import java.util.List;
import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.adapter.MineAdapter;
import com.sztb.socialsecurity.widget.xlist.XListView;
import com.sztb.socialsecurity.widget.xlist.XListView.IXListViewListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MineFragment extends Fragment implements IXListViewListener {
	private Activity mActivity;
	private MineAdapter adapter;
	private XListView xListView;
	private List<String> list = new ArrayList<String>();
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main_list, null);
		list.add("hello");
		xListView = (XListView) view.findViewById(R.id.xListView);
		adapter = new MineAdapter(mActivity, list);
		xListView.setAdapter(adapter);
		xListView.setPullLoadEnable(false);
		xListView.setPullRefreshEnable(true);// 下拉刷新
		xListView.setXListViewListener(this);// 给xListView设置监听
		return view;
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				xListView.stopRefresh();
			}
		}, 1000);
	}

	@Override
	public void onLoadMore() {
		
	}
}
