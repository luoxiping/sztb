package com.sztb.socialsecurity.wxpay;

import com.sztb.socialsecurity.config.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.os.Bundle;

public class WxPayActivity extends Activity {
	private IWXAPI api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);  
		api.registerApp(Constants.APP_ID); 
		//从服务端拿到这些参数
//		if (api != null) {  
//		    if (isWXAppInstalled()) {  
//		        PayReq req = new PayReq();  
//		        req.appId = APP_ID;  
//		        req.partnerId = params.getPartnerId();  
//		        req.prepayId = params.getPrepayId();  
//		        req.packageValue = params.getPackageValue();  
//		        req.nonceStr = params.getNonceStr();  
//		        req.timeStamp = params.getTimeStamp();  
//		        req.sign = params.getSign();  
//		  
//		        api.sendReq(req);  
//		    }  
//		}  
	}

}
