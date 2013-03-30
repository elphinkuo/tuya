package com.xuan.tuya;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.tuya.R;
import com.xuan.tuya.common.Constants;
import com.xuan.tuya.utils.ToastUtils;
import com.xuan.tuya.view.TuyaPicView;

/**
 * Ϳѻ������
 * 
 * @author xuan
 */
public class MainTuYaActivity extends BasicActivity {

	@InjectView(R.id.tuYaPicView)
	private TuyaPicView tuyaPicView;// Ϳѻ��view

	@InjectView(R.id.backBtn)
	private Button backBtn;// ������һ����ť

	@InjectView(R.id.clearBtn)
	private Button clearBtn;// �������

	@InjectView(R.id.saveBtn)
	private Button saveBtn;// ����ͼƬ��ť

	@InjectView(R.id.brushBtn)
	private Button brushBtn;// �л�ѡ�񻭱���ɫ�ʹ�С

	@InjectView(R.id.penOperate)
	private RelativeLayout penOperate;// ��������

	// ��ɫ��ť
	@InjectView(R.id.colorBlue)
	private ImageButton colorBlue;

	@InjectView(R.id.colorGreen)
	private ImageButton colorGreen;

	@InjectView(R.id.colorYellow)
	private ImageButton colorYellow;

	@InjectView(R.id.colorOrange)
	private ImageButton colorOrange;

	@InjectView(R.id.colorRed)
	private ImageButton colorRed;

	@InjectView(R.id.colorBlack)
	private ImageButton colorBlack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initWidgits();
		initColorBtn();
	}

	private void initColorBtn() {
		colorBlue.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clearSelectedColorBtn();
				colorBlue.setImageDrawable(getResources().getDrawable(
						R.drawable.selected2));
			}
		});
		colorGreen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clearSelectedColorBtn();
				colorGreen.setImageDrawable(getResources().getDrawable(
						R.drawable.selected2));
			}
		});
		colorYellow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clearSelectedColorBtn();
				colorYellow.setImageDrawable(getResources().getDrawable(
						R.drawable.selected2));
			}
		});
		colorOrange.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clearSelectedColorBtn();
				colorOrange.setImageDrawable(getResources().getDrawable(
						R.drawable.selected2));
			}
		});
		colorRed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clearSelectedColorBtn();
				colorRed.setImageDrawable(getResources().getDrawable(
						R.drawable.selected2));
			}
		});
		colorBlack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clearSelectedColorBtn();
				colorBlack.setImageDrawable(getResources().getDrawable(
						R.drawable.selected2));
			}
		});
	}

	private void initWidgits() {
		// ����ǰһ��
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tuyaPicView.undo();
			}
		});

		// �������
		clearBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tuyaPicView.redo();
			}
		});

		// �л�ѡ�񻭱���ɫ�ʹ�С
		brushBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});

		// ��������
		brushBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (penOperate.getVisibility() == View.GONE) {
					penOperate.setVisibility(View.VISIBLE);
				} else {
					penOperate.setVisibility(View.GONE);
				}
			}
		});

		// ����ͼƬ
		saveBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String filePath = Constants.PIC_STORE_PATH + "test.jpg";
				boolean isSave = tuyaPicView.saveToSDCard(filePath);

				if (isSave) {
					ToastUtils.displayTextShort(MainTuYaActivity.this, "ͼƬ�Ѿ������ڣ�"
							+ filePath);
				} else {
					ToastUtils
							.displayTextShort(MainTuYaActivity.this, "������˼ͼƬ����ʧ��");
				}
			}
		});

	}

	// ���ѡ�еĹ���
	private void clearSelectedColorBtn() {
		colorBlue.setImageDrawable(null);
		colorGreen.setImageDrawable(null);
		colorYellow.setImageDrawable(null);
		colorOrange.setImageDrawable(null);
		colorRed.setImageDrawable(null);
		colorBlack.setImageDrawable(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
