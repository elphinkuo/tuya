package com.xuan.tuya.common;

import android.os.Environment;

/**
 * ������
 * 
 * @author xuan
 */
public abstract class Constants {

	// ��־�����־
	public static final String TAG = "tuya";

	// �ֻ�SD����·��
	public static final String SDCARD = Environment
			.getExternalStorageDirectory().getPath();

	// ����apkʱ������������ŵ��ļ���·��
	public static final String PIC_STORE_PATH = SDCARD + "/tuya/";

}
