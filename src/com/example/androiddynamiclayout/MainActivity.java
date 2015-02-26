package com.example.androiddynamiclayout;

import java.util.LinkedList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	// ��Χ��LinearLayout����
	private LinearLayout llContentView;
	
	private EditText etContent1;
	
	// ��+����ť�ؼ�List
	private LinkedList<ImageButton> listIBTNAdd;
	// ��+����ťID����
	private int btnIDIndex = 1000;
	// ��-����ť�ؼ�List
	private LinkedList<ImageButton> listIBTNDel;
	
	private int iETContentHeight = 0;	// EditText�ؼ��߶�
	private float fDimRatio = 1.0f; // �ߴ������ʵ�ʳߴ�/xml�ļ���ߴ磩
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initCtrl();
	}
	
	/**
	 * ��ʼ���ؼ�
	 */
	private void initCtrl()
	{
		llContentView = (LinearLayout) this.findViewById(R.id.content_view);
		etContent1 = (EditText) this.findViewById(R.id.et_content1);
		listIBTNAdd = new LinkedList<ImageButton>();
		listIBTNDel = new LinkedList<ImageButton>();
		
		// ��+����ť����һ����
		ImageButton ibtnAdd1 = (ImageButton) this.findViewById(R.id.ibn_add1);
		ibtnAdd1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡ�ߴ�仯����
				iETContentHeight = etContent1.getHeight();
				fDimRatio = iETContentHeight / 80;

				addContent(v);
			}
		});

		listIBTNAdd.add(ibtnAdd1);
		listIBTNDel.add(null);	// ��һ�������ˡ�-����ť������Ϊnull
	}
	
	/**
	 * ���һ���¿ؼ�
	 * @param v	�¼������ؼ�����ʵ���Ǵ�������¼���Ӧ�ġ�+����ť
	 */
	private void addContent(View v) {
		if (v == null) {
			return;
		}
		
		// �жϵڼ�����+����ť�������¼�
		int iIndex = -1;
		for (int i = 0; i < listIBTNAdd.size(); i++) {
			if (listIBTNAdd.get(i) == v) {
				iIndex = i;
				break;
			}
		}
		
		if (iIndex >= 0) {
			// �ؼ�ʵ�����λ��Ϊ��ǰ����λ�õ���һλ
			iIndex += 1;
			
			// ��ʼ��ӿؼ�
			
			// 1.������ΧLinearLayout�ؼ�
			LinearLayout layout = new LinearLayout(MainActivity.this);
			LinearLayout.LayoutParams lLayoutlayoutParams = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			// ����margin
			lLayoutlayoutParams.setMargins(0, (int) (fDimRatio * 5), 0, 0);
			layout.setLayoutParams(lLayoutlayoutParams);
			// ��������
			layout.setBackgroundColor(Color.argb(255, 162, 205, 90));	// #FFA2CD5A
			layout.setPadding((int) (fDimRatio * 5), (int) (fDimRatio * 5),
					(int) (fDimRatio * 5), (int) (fDimRatio * 5));
			layout.setOrientation(LinearLayout.VERTICAL);
			
			// 2.�����ڲ�EditText�ؼ�
			EditText etContent = new EditText(MainActivity.this);
			LinearLayout.LayoutParams etParam = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, iETContentHeight);
			etContent.setLayoutParams(etParam);
			// ��������
			etContent.setBackgroundColor(Color.argb(255, 255, 255, 255));	// #FFFFFFFF
			etContent.setGravity(Gravity.LEFT);
			etContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
			etContent.setPadding((int) (fDimRatio * 5), 0, 0, 0);
			etContent.setTextSize(16);
			// ��EditText�ŵ�LinearLayout��
			layout.addView(etContent);
			
			// 3.������+���͡�-����ť��Χ�ؼ�RelativeLayout
			RelativeLayout rlBtn = new RelativeLayout(MainActivity.this);
			RelativeLayout.LayoutParams rlParam = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
//			rlParam.setMargins(0, (int) (fDimRatio * 5), 0, 0);
			rlBtn.setPadding(0, (int) (fDimRatio * 5), 0, 0);
			rlBtn.setLayoutParams(rlParam);
			
			// 4.������+����ť
			ImageButton btnAdd = new ImageButton(MainActivity.this);
			RelativeLayout.LayoutParams btnAddParam = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			// ���ҷ���
			btnAddParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			btnAdd.setLayoutParams(btnAddParam);
			// ��������
			btnAdd.setBackgroundResource(R.drawable.ic_add);
			btnAdd.setId(btnIDIndex);
			// ���õ������
			btnAdd.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					addContent(v);
				}
			});
			// ����+����ť�ŵ�RelativeLayout��
			rlBtn.addView(btnAdd);
			listIBTNAdd.add(iIndex, btnAdd);
			
			// 5.������-����ť
			ImageButton btnDelete = new ImageButton(MainActivity.this);
			btnDelete.setBackgroundResource(R.drawable.ic_delete);
			RelativeLayout.LayoutParams btnDeleteAddParam = new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			btnDeleteAddParam.setMargins(0, 0, (int) (fDimRatio * 5), 0);
			// ��-����ť���ڡ�+����ť���
			btnDeleteAddParam.addRule(RelativeLayout.LEFT_OF, btnIDIndex);
			btnDelete.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					deleteContent(v);
				}
			});
			// ����-����ť�ŵ�RelativeLayout��
			rlBtn.addView(btnDelete, btnDeleteAddParam);
			listIBTNDel.add(iIndex, btnDelete);
			
			// 6.��RelativeLayout�ŵ�LinearLayout��
			layout.addView(rlBtn);
			
			// 7.��layoutͬ���ڲ������пؼ��ӵ�����Χ��llContentView������
			llContentView.addView(layout, iIndex);
			
			btnIDIndex++;
		}
	}
	
	/**
	 * ɾ��һ��ؼ�
	 * @param v	�¼������ؼ�����ʵ���Ǵ���ɾ���¼���Ӧ�ġ�-����ť
	 */
	private void deleteContent(View v) {
		if (v == null) {
			return;
		}

		// �жϵڼ�����-����ť�������¼�
		int iIndex = -1;
		for (int i = 0; i < listIBTNDel.size(); i++) {
			if (listIBTNDel.get(i) == v) {
				iIndex = i;
				break;
			}
		}
		if (iIndex >= 0) {
			listIBTNAdd.remove(iIndex);
			listIBTNDel.remove(iIndex);
			
			// ����ΧllContentView������ɾ����iIndex�ؼ�
			llContentView.removeViewAt(iIndex);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
