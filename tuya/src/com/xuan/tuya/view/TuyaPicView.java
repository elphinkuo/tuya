package com.xuan.tuya.view;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.tuya.R;
import com.xuan.tuya.common.Constants;
import com.xuan.tuya.utils.ContextUtils;

/**
 * ͿѻͼƬview
 * 
 * @author xuan
 */
public class TuyaPicView extends View {
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;// ������ͼ�εģ��������������һ�������εȵ�
	private Paint mBitmapPaint;// �����Ļ���
	private Paint mPaint;// ��ʵ�Ļ���
	private float mX, mY;// ��ʱ������
	private static final float TOUCH_TOLERANCE = 4;// ·����¼���ȣ������ڻ����У���xy�����곬����ʱ����������ֵ���ͻ���

	// ����Path·���ļ���,��List������ģ��ջ
	private static List<DrawPath> savePath;
	// ��¼Path·���Ķ���
	private DrawPath dp;

	private boolean isInit = false;// ������Ǳ�ֻ֤����ʼ��һ��

	// ���ָߺͿ�
	private int screenWidth = 500, screenHeight = 500;

	public TuyaPicView(Context context) {
		super(context);
	}

	public TuyaPicView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TuyaPicView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void init(int w, int h) {
		// ��ֻ֤����ʼ��һ�ξ͹���
		if (!isInit) {
			screenWidth = w;
			screenHeight = h;

			mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
					Bitmap.Config.ARGB_8888);

			// ����һ��һ�λ��Ƴ�����ͼ��
			mCanvas = new Canvas(mBitmap);

			mBitmapPaint = new Paint(Paint.DITHER_FLAG);
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setStrokeJoin(Paint.Join.ROUND);// �������Ե
			mPaint.setStrokeCap(Paint.Cap.ROUND);// ��״
			mPaint.setStrokeWidth(5);// ���ʿ��

			savePath = new ArrayList<DrawPath>();
			isInit = true;
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		int color = getResources().getColor(R.color.color_white);
		canvas.drawColor(color);

		// ��ǰ���Ѿ���������ʾ����
		canvas.drawBitmap(mBitmap, 0, 0, null);
		if (mPath != null) {
			// ʵʱ����ʾ
			canvas.drawPath(mPath, mPaint);
		}
	}

	// ���ֵĴ�С�ı�ʱ���ͻ���ø÷���
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// ���ֵĴ�С�ı�ʱ���ͻ���ø÷������������ȡ��view�ĸߺͿ�
		screenWidth = w;
		screenHeight = h;

		// view��ʼ��
		init(screenWidth, screenHeight);
		super.onSizeChanged(w, h, oldw, oldh);
	}

	/**
	 * ������һ������<br />
	 * �����ĺ���˼����ǽ�������գ�������������Path·�����һ���Ƴ��������½�·�����ڻ������档
	 */
	public void undo() {
		if (savePath != null && savePath.size() > 0) {
			savePath.remove(savePath.size() - 1);
			redrawOnBitmap();
		}
	}

	/**
	 * ����,����������еĻ���<br />
	 * ����˼����ǣ����Path·���󣬽������»���
	 */
	public void redo() {
		if (savePath != null && savePath.size() > 0) {
			savePath.clear();
			redrawOnBitmap();
		}
	}

	// ����
	private void touch_start(float x, float y) {
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	// ���ڻ�����
	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(mY - y);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			// �ӣ�mX,mY������x,y����һ�����������ߣ���ƽ��(ֱ����mPath.lineToҲ�ǿ��Ե�)
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	// ����̫��
	private void touch_up() {
		mPath.lineTo(mX, mY);
		mCanvas.drawPath(mPath, mPaint);
		// ��һ��������·����������(�൱����ջ����)
		savePath.add(dp);
		mPath = null;// �����ÿ�
	}

	// ���»���Path�еĻ���·��
	private void redrawOnBitmap() {
		mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
				Bitmap.Config.ARGB_8888);
		mCanvas.setBitmap(mBitmap);// �������û������൱����ջ���

		for (DrawPath drawPath : savePath) {
			mCanvas.drawPath(drawPath.path, drawPath.paint);
		}

		invalidate();// ˢ��
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ÿ��down��ȥ����newһ��Path
			mPath = new Path();
			// ÿһ�μ�¼��·�������ǲ�һ����
			dp = new DrawPath();
			dp.path = mPath;
			dp.paint = mPaint;
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}

		return true;
	}

	/**
	 * ����ͼƬ
	 */
	public boolean saveToSDCard(String filePath) {
		if (!ContextUtils.hasSdCard()) {
			return false;
		}

		try {
			File file = new File(filePath);
			createParentDirs(file);

			FileOutputStream fos = new FileOutputStream(file);
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
			// FileUtils.saveFileByBitmap(filePath, mBitmap);
		} catch (Exception e) {
			Log.e(Constants.TAG, "", e);
			return false;
		}

		return true;
	}

	// �����Ŀ¼�����ڣ��򴴽�֮
	private static void createParentDirs(File file) {
		File parentPath = file.getParentFile();
		if (!parentPath.exists() || !parentPath.isDirectory()) {
			parentPath.mkdirs();
		}
	}

	/**
	 * ÿ��·����װ��
	 * 
	 * @author xuan
	 */
	private class DrawPath {
		public Path path;// ·��
		public Paint paint;// ����
	}

}
