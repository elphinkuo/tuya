/* 
 * @(#)ContextUtils.java    Created on 2012-5-7
 * Copyright (c) 2012 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package com.xuan.tuya.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import com.xuan.tuya.common.Constants;

/**
 * �ж��ֻ����磬SD�ȹ�����
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-5-7 ����02:58:39 $
 */
public class ContextUtils {
	private ContextUtils() {
	}

	/**
	 * �ж��Ƿ������������
	 * 
	 * @param context
	 * @return
	 */
	public static boolean hasNetwork(Context context) {
		ConnectivityManager connectManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
		if (networkInfo == null
				|| !connectManager.getActiveNetworkInfo().isAvailable()
				|| !connectManager.getActiveNetworkInfo().isConnected()) {
			return false;
		}
		return true;
	}

	/**
	 * �ж�GPS�Ƿ��
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager alm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (!alm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			return false;
		}
		return true;
	}

	/**
	 * ����λdpת��Ϊpx
	 * 
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(float dpValue, Context context) {
		final float scale = context.getResources().getDisplayMetrics().density;
		int px = (int) (dpValue * scale + 0.5f);
		Log.d(Constants.TAG, "from " + dpValue + "dp to:" + px + "px");
		return px;
	}

	/**
	 * SD���Ƿ����
	 * 
	 * @return
	 */
	public static boolean hasSdCard() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// sdcard ������
			return false;
		}
		return true;
	}
}
