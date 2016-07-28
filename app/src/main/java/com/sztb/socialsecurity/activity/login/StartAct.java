package com.sztb.socialsecurity.activity.login;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpConfig;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.MainActivity;
import com.sztb.socialsecurity.utils.SharePreferenceUtil;
import com.sztb.socialsecurity.utils.Utils;

/**
 * 应用的开始界面
 * 
 * @author admin
 *
 */
public class StartAct extends Activity {
	private static final String TAG = "StartAct";
	private KJHttp kjh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
		setContentView(R.layout.start_act);
		HttpConfig config = new HttpConfig();
		config.cacheTime = 0;
		config.TIMEOUT = 5 * 60 * 1000;
		kjh = new KJHttp(config);
		LinearLayout start_linear_main = (LinearLayout) findViewById(R.id.start_linear_main);
		setImmerseLayout(start_linear_main);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Utils.startActivity(StartAct.this, SplashActivity.class);
				finish();
			}
		}, 1000);
//		if (SharePreferenceUtil.getInstance(this).getBoolean("main_first", false)) {
////			if (!TextUtils.isEmpty(SharePreferenceUtil.getInstance(
////					getApplicationContext()).getString("qltoken", ""))) {
//////				Utils.startActivity(LoginActivity.this, MainActivity.class);
//////				finish();
//////				checkYz();
////				
////			} else {
//////				Utils.startActivity(StartAct.this, LoginActivity.class);
////				finish();
////			}
//			Utils.startActivity(StartAct.this, MainActivity.class);
//			finish();
//		} else {
//			new Handler().postDelayed(new Runnable() {
//				@Override
//				public void run() {
//					Utils.startActivity(StartAct.this, SplashActivity.class);
//					finish();
//				}
//			}, 1000);
//		}
	}
	
//	private void checkYz(){
//		HttpParams params = new HttpParams();
//		params.put("QLTOKEN", SharePreferenceUtil.getInstance(this).getString("qltoken", ""));
//		kjh.post(UriConfig.getCurrentId, params, new HttpCallBack() {
//
//			@Override
//			public void onFailure(int errorNo, String strMsg) {
//				super.onFailure(errorNo, strMsg);
//			}
//
//			@Override
//			public void onFinish() {
//				super.onFinish();
//			}
//
//			@Override
//			public void onSuccess(String t) {
//				super.onSuccess(t);
//				MakeLiveModel makeLiveModel = GsonUtil.fromJson(t,
//						MakeLiveModel.class);
//				if (makeLiveModel != null) {
//					if (makeLiveModel.getStatusCode().equals("200")) {
//						MakeLive models = makeLiveModel.getLiveEntityView();
//						if (models != null) {
//							if (!TextUtils.isEmpty(models.getId())) {
//								SharePreferenceUtil.getInstance(StartAct.this).setString("liveId", models.getId());
//							} else {
//								
//							}
//							Log.e("XX", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX:" + makeLiveModel.isAuth());
//							if (makeLiveModel.isAuth()) {//已经验证
//								Utils.startActivity(StartAct.this, MainActivity.class);
//								finish();
//							} else {
//								Bundle data = new Bundle();
//								data.putInt("index", 0);
//								Utils.startActivity(StartAct.this, LiveYanZhengAct.class, data);
//								finish();
//							}
//							
//						} else  {
//							//这是一个新用户
//							Utils.startActivity(StartAct.this, MainActivity.class);
//							finish();
//						}
//					}else if (makeLiveModel.getStatusCode().equals("110")){
//						Utils.startActivity(StartAct.this, LoginActivity.class);
//						finish();
//					} else {
//						Toast.makeText(StartAct.this, makeLiveModel.getMsg(), 2000).show();
//					}
//				} else {
//					Toast.makeText(StartAct.this, "异常", 2000).show();
//				}
//			}
//
//		});
//	}

	protected void setImmerseLayout(View view) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
}
