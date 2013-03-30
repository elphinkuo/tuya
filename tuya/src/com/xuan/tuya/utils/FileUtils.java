package com.xuan.tuya.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import android.graphics.Bitmap;
import android.util.Log;

import com.xuan.tuya.common.Constants;

/**
 * �ļ������๤��
 * 
 * @author xuan
 */
public abstract class FileUtils {
	/**
	 * �����ֽڵ�ָ���ļ�
	 * 
	 * @param filePath
	 * @param bs
	 */
	public static void saveFileByBytes(String fileName, byte[] bs) {
		if (!ContextUtils.hasSdCard()) {
			return;
		}

		try {
			org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(
					fileName), bs);
		} catch (IOException e) {
			Log.e(Constants.TAG, "", e);
		}
	}

	/**
	 * ����bitmap��ָ���ļ�
	 * 
	 * @param fileName
	 * @param bs
	 */
	public static void saveFileByBitmap(String fileName, Bitmap bitmap) {
		if (!ContextUtils.hasSdCard()) {
			return;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

		saveFileByBytes(fileName, baos.toByteArray());
	}

}
