/* 
 * @(#)AnActivity.java    Created on 2012-11-29
 * Copyright (c) 2012 ZDSoft Networks, Inc. All rights reserved.
 * $Id: AnActivity.java 33154 2012-12-09 08:28:10Z xuan $
 */
package com.xuan.tuya;

import java.lang.reflect.Field;

import android.app.Activity;
import android.util.Log;

/**
 * ��׿IOC���ʹ�ü̳и���
 * 
 * @author xuan
 * @version $Revision: 33154 $, $Date: 2012-12-09 16:28:10 +0800 (����, 09 ʮ����
 *          2012) $
 */
public class AnActivity extends Activity {

	public static final String AN_SUMMER_LOG_TAG = "ansummer��ܴ�����Ϣ";// ��־���TAG
	public static final String THIS_OBJECT = "this.object";// this����

	@Override
	public void setContentView(int layout) {
		super.setContentView(layout);
		initAn();
	}

	/**
	 * ���ô˷������Ϳ��Խ�IOCע��
	 */
	private void initAn() {
		Class clazz = this.getClass();
		Field[] fileds = clazz.getDeclaredFields();

		for (int i = 0; i < fileds.length; i++) {
			initInjectView(fileds[i]);
		}
	}

	/**
	 * ע��InjectViewע��
	 * 
	 * @param field
	 */
	private void initInjectView(Field field) {
		InjectView injectView = field.getAnnotation(InjectView.class);

		if (null != injectView) {
			try {
				field.setAccessible(true);
				field.set(this, this.findViewById(injectView.value()));
			} catch (Exception e) {
				Log.e(AN_SUMMER_LOG_TAG, "ע��[" + field.getName() + "]����:" + e);
			}
		}
	}

}
