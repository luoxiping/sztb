package com.sztb.socialsecurity.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sztb.socialsecurity.MyApplication;
import com.sztb.socialsecurity.R;
import com.sztb.socialsecurity.activity.MainActivity;
import com.sztb.socialsecurity.widget.SystemBarTintManager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class Utils {
	private static long lastClickTime = System.currentTimeMillis();

	private static boolean debug = true;

	public static final String DESCRIPTOR = "com.umeng.share";

	public static class InputLengthLimit {
		/**
		 * 短信验证码输入长度限制
		 */
		public static final int AUTHCODE = 6;
		/**
		 * 手机号输入长度限制
		 */
		public static final int MOBILE = 11;
		/**
		 * 登录、支付密码输入长度限制
		 */
		public static final int PASSWORD = 16;
		/**
		 * 商品描述输入长度限制
		 */
		public static final int PRODUCT_TITLE = 70;
		/**
		 * 商品描述输入长度限制
		 */
		public static final int PRODUCT_DESC = 150;
	}

	/**
	 * 限制EditText输入的文本长度
	 *
	 * @param editText
	 */
	public static void limitInputLength(EditText editText, int limit) {
		if (editText == null || limit <= 0) {
			return;
		}

		InputFilter[] filters = {new InputFilter.LengthFilter(limit)};
		editText.setFilters(filters);
	}

	/**
	 * 将光标置于EditText文本的末尾
	 *
	 * @param editText
	 */
	public static void moveCursorToLast(EditText editText) {
		if (editText == null) {
			return;
		}

		CharSequence cs = editText.getText();
		if (cs instanceof Spannable) {
			Spannable spanText = (Spannable) cs;
			Selection.setSelection(spanText, cs.length());
		}
	}

	/**
	 * 防止控件被重复点击
	 *
	 * @param distance
	 *            间隔 默认500毫秒
	 * @return
	 */
	public static boolean isFastDoubleClick(int distance) {
		long time = System.currentTimeMillis();
		long timeD = ((long) time - lastClickTime);
		if (0 < timeD && timeD < (long) distance) {
			Log.i("xdt", "++Double _timeD= " + timeD);
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * @param activity
	 * @param clazz
	 * @param resultCode
	 */
	public static void startActivityForResult(Activity activity, Class clazz, int resultCode) {
		startActivityForResult(activity, clazz, null, resultCode);
	}

	/**
	 * @param activity
	 * @param clazz
	 * @param bundle
	 * @param resultCode
	 */
	public static void startActivityForResult(Activity activity, Class clazz, Bundle bundle, int resultCode) {
		Intent intent = new Intent();
		intent.setClass(activity, clazz);

		if (bundle != null) {
			intent.putExtras(bundle);
		}

		activity.startActivityForResult(intent, resultCode);
	}

	/**
	 * @param context
	 * @param clazz
	 */
	public static void startActivity(Context context, Class clazz) {
		Intent intent = new Intent();
		intent.setClass(context, clazz);
		// 2012-05-15 如果主功能activity在task存在，
		// 将Activity之上的所有Activity结束掉.从而解决后退时出现查询框问题。
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
		// ((Activity) context).finish();

	}

	/**
	 * @param context
	 * @param clazz
	 * @param bundle
	 */
	public static void startActivity(Context context, Class clazz, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(context, clazz);
		// 2012-05-15 如果主功能activity在task存在，
		// 将Activity之上的所有Activity结束掉.从而解决后退时出现查询框问题。
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		if (bundle != null) {
			intent.putExtras(bundle);
		}

		context.startActivity(intent);
		// ((Activity) context).finish();
	}

	/**
	 * EditText的末端加上清空图标
	 *
	 * @param run
	 *            附加的处理方法
	 * @param
	 * @return
	 */
	public static void addClearToEditText(final Runnable run, final EditText... ets) {
		for (EditText mEditText : ets) {
			try {
				final EditText et = mEditText;
				final Drawable mIconClear = MyApplication.getInstance().getResources()
						.getDrawable(R.mipmap.share_contatclear);
				if (et.getPaddingRight() == 0)
					et.setPadding(et.getPaddingLeft(), et.getPaddingTop(), 30, et.getPaddingBottom());
				et.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						try {
							if (!isEmpty(et)) {
								if (et.getCompoundDrawables()[2] == null) {
									et.setCompoundDrawablesWithIntrinsicBounds(null, null, mIconClear, null);
								}
							} else {
								et.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
							}
							if (run != null)
								run.run();
						} catch (Exception e) {
							Log.e("Tools", e.getMessage(), e);
						}
					}
				});

				et.setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if (event.getAction() == MotionEvent.ACTION_UP) {
							try {
								int curX = (int) event.getX();
								if (curX > v.getWidth() - 48 && !TextUtils.isEmpty(et.getText())) {
									et.setText("");
									int cacheInputType = et.getInputType();
									et.setInputType(InputType.TYPE_NULL);
									et.onTouchEvent(event);
									et.setInputType(cacheInputType);
									return true;
								}
							} catch (Exception e) {
								Log.e("Tools", e.getMessage(), e);
							}
						}
						return false;
					}
				});
			} catch (Exception e) {
				Log.e("Tools", e.getMessage(), e);
			}
		}
	}

	/**
	 * 判断TextView是否为空
	 *
	 * @param tv
	 * @return
	 */
	public static boolean isEmpty(TextView tv) {
		if (tv == null)
			return true;
		return TextUtils.isEmpty(getText(tv));
	}

	/**
	 * 获取内容，并去左右空格
	 *
	 *
	 */
	public static String getText(TextView tv) {
		if (tv == null)
			return "";
		else
			return tv.getText().toString().trim();
	}

	public static boolean isNull(Object obj) {
		if (null == obj || obj == "" || obj.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 用于获取状态栏的高度。 使用Resource对象获取（推荐这种方式）
	 *
	 * @return 返回状态栏高度的像素值。
	 */
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	/**
	 * 得到设备屏幕的宽度
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 得到设备屏幕的高度
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 得到设备的密度
	 */
	public static float getScreenDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

	/**
	 * 把密度转换为像素
	 */
	public static int dip2px(Context context, float px) {
		final float scale = getScreenDensity(context);
		return (int) (px * scale + 0.5);
	}

	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
		String str = sdf.format(date);
		return str;
	}

	public static int getPhoneWidth(Activity mActivity) {
		Display display = mActivity.getWindowManager().getDefaultDisplay(); // Activity#getWindowManager()
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		return width;
	}

	public static int getPhoneHeight(Activity mActivity) {
		Display display = mActivity.getWindowManager().getDefaultDisplay(); // Activity#getWindowManager()
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		return height;
	}

	/**
	 * 检测是否有网络
	 */
	public static boolean isNetworkAvailable(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.getState() == NetworkInfo.State.CONNECTED)
			return true;
		return false;
	}

	// public static boolean isMobileNum(String mobiles) {
	// Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
	// Matcher m = p.matcher(mobiles);
	// System.out.println(m.matches() + "---");
	// return m.matches();
	// }

	public static String timeChange(long time) {
		Date date = new Date(time);
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Log.e("XX", df.format(date));
		return df.format(date);
	}

	public static String timeChange2(long time) {
		Date date = new Date(time);
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日 HH:mm");
		Log.e("XX", df.format(date));
		return df.format(date);
	}

	public static String timeChange3(long time) {
		Date date = new Date(time);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Log.e("XX", df.format(date));
		return df.format(date);
	}

	public static void imageZoom(Bitmap bitmap, double maxSize) {
		// 将bitmap放至数组中，意在获得bitmap的大小（与实际读取的原文件要大）
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 格式、质量、输出流
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		// 将字节换成KB
		double mid = b.length / 1024;
		// 获取bitmap大小 是允许最大大小的多少倍
		double i = mid / maxSize;
		// 判断bitmap占用空间是否大于允许最大空间 如果大于则压缩 小于则不压缩
		if (i > 1) {
			// 缩放图片 此处用到平方根 将宽带和高度压缩掉对应的平方根倍
			// （保持宽高不变，缩放后也达到了最大占用空间的大小）
			bitmap = scale(bitmap, bitmap.getWidth() / Math.sqrt(i), bitmap.getHeight() / Math.sqrt(i));
		}
	}

	/***
	 * 图片的缩放方法
	 *
	 * @param src
	 *            ：源图片资源
	 * @param newWidth
	 *            ：缩放后宽度
	 * @param newHeight
	 *            ：缩放后高度
	 */
	public static Bitmap scale(Bitmap src, double newWidth, double newHeight) {
		// 记录src的宽高
		float width = src.getWidth();
		float height = src.getHeight();
		// 创建一个matrix容器
		Matrix matrix = new Matrix();
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 开始缩放
		matrix.postScale(scaleWidth, scaleHeight);
		// 创建缩放后的图片
		return Bitmap.createBitmap(src, 0, 0, (int) width, (int) height, matrix, true);
	}

	public static Bitmap convertViewToBitmap(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}

	public static void callPhone(Activity activity) {
		/* 用intent启动拨打电话 */
		String phone = SharePreferenceUtil.getInstance(activity).getString("phone", "12345678910");
		Intent intent = new Intent();
		intent.setAction (Intent.ACTION_CALL);
		Uri data = Uri.parse("tel:" + phone);
		intent.setData(data);
		activity.startActivity(intent);
	}

	public static Bitmap getimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 1280f;// 这里设置高度为800f
		float ww = 720f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	public static Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 30) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			Log.e("XX", "daxiao:" + options + ":" + baos.toByteArray().length / 1024);
			if (options > 10) {
				options -= 10;// 每次都减少10
			} else if (options > 0) {
				options -= 1;
			} else {
				break;
			}
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	/** 检查SD卡是否存在 */
	public static boolean checkSdCard() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}

	/**
	 * 重新登陆
	 */
	public static void reLogin(Activity activity) {
		SharePreferenceUtil.getInstance(activity).setBoolean("main_first", false);
		Toast.makeText(activity, "登陆过期，请重新登陆！", Toast.LENGTH_LONG).show();
		// Utils.startActivity(activity, LoginActivity.class);
	}

	/**
	 * 判断该文件是否已经下载
	 * 
	 * @param pathSD
	 * @return
	 */
	public static boolean checkTargetPathExist(String pathSD) {
		File audioFile = new File(pathSD);
		if (audioFile.exists() && audioFile.isFile()) {
			return true;
		} else {
			return false;
		}
	}

	public static Bitmap Bytes2Bimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	/**
	 * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
	 * 
	 * A.网络路径: url="http://blog.foreverlove.us/girl2.png" ;
	 * 
	 * B.本地路径:url="file://mnt/sdcard/photo/image.png";
	 * 
	 * C.支持的图片格式 ,png, jpg,bmp,gif等等
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap GetLocalOrNetBitmap(String url) {
		Bitmap bitmap = null;
		InputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new URL(url).openStream(), 2 * 1024);
			final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
			out = new BufferedOutputStream(dataStream, 2 * 1024);
			copy(in, out);
			out.flush();
			byte[] data = dataStream.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			data = null;
			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] b = new byte[2 * 1024];
		int read;
		while ((read = in.read(b)) != -1) {
			out.write(b, 0, read);
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isMobileNum(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches() + "---");
		return m.matches();
	}

	/**
	 * 设置系统状态栏的颜色 Exception
	 */
	@SuppressWarnings("unused")
	public static void setActionBarTitle(Activity mActivity, LinearLayout linear_main) throws Exception {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window win = mActivity.getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			if (true) {
				winParams.flags |= bits;
			} else {
				winParams.flags &= ~bits;
			}
			win.setAttributes(winParams);

			int status = SharePreferenceUtil.getInstance(mActivity).getInt("status", 0);
			if (status == 0) {
				Class<?> c = Class.forName("com.android.internal.R$dimen");
				Object obj = c.newInstance();
				java.lang.reflect.Field field = c.getField("status_bar_height");
				int x = Integer.parseInt(field.get(obj).toString());
				status = mActivity.getResources().getDimensionPixelSize(x);// 获取状态栏的高度
				SharePreferenceUtil.getInstance(mActivity).setInt("status", status);
			}
			SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
			tintManager.setStatusBarTintEnabled(true);
			linear_main.setPadding(0, status, 0, 0);
			tintManager.setStatusBarTintResource(R.color.mine_bg);
		}
	}
}
