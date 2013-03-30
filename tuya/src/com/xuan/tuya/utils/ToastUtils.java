/* 
 * @(#)ToastUtils.java    Created on 2011-5-31
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: ToastUtils.java 31799 2012-10-25 04:59:34Z xuan $
 */
package com.xuan.tuya.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * ��˾��Ϣ������
 * 
 * @author xuan
 * @version $Revision: 31799 $, $Date: 2012-10-25 12:59:34 +0800 (������, 25 ʮ��
 *          2012) $
 */
public class ToastUtils {
	/**
	 * ��ʾ��˾��Ϣ���ϳ�ʱ�䣩
	 * 
	 * @param context
	 * @param text
	 */
	public static void displayTextLong(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	/**
	 * ��ʾ��˾��Ϣ���϶�ʱ�䣩
	 * 
	 * @param context
	 * @param text
	 */
	public static void displayTextShort(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ��ʾ��˾��Ϣ����handler�����ϳ�ʱ�䣩
	 * 
	 * @param context
	 * @param text
	 * @param handler
	 */
	public static void displayTextLong2Handler(final Context context,
			final String text, Handler handler) {

		handler.post(new Runnable() {
			@Override
			public void run() {
				ToastUtils.displayTextLong(context, text);
			}
		});
	}

	/**
	 * ��ʾ��˾��Ϣ����handler�����϶�ʱ�䣩
	 * 
	 * @param context
	 * @param text
	 * @param handler
	 */
	public static void displayTextShort2Handler(final Context context,
			final String text, Handler handler) {

		handler.post(new Runnable() {

			@Override
			public void run() {
				ToastUtils.displayTextShort(context, text);
			}
		});
	}
}
