package com.alex_zaitsev.fragmentdialogsexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.alex_zaisev.fragmentdialogs.AbstractDialogActivity;
import com.alex_zaisev.fragmentdialogs.AlertDialogFragment;
import com.alex_zaisev.fragmentdialogs.AlertDialogFragment.ConfirmationDialogFragmentListener;
import com.alex_zaisev.fragmentdialogs.ProgressDialogFragment;
import com.alex_zaisev.fragmentdialogs.ProgressDialogFragment.OnCancelListener;
import com.alex_zaisev.fragmentdialogs.SingleChoiceFragment.OnChoiceDialogClickListener;

public class MainActivity extends AbstractDialogActivity implements
		OnClickListener, OnCancelListener, ConfirmationDialogFragmentListener,
		OnChoiceDialogClickListener {

	private static final int DIALOG_CUSTOM = 322;

	Button mProgressEnable;
	Button mProgressEnableCancel;
	Button mFinishDialog;
	Button mNoNetDialog;
	Button mCustomDialog;
	Button mProgressHor;
	Button mSingleChoice;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mProgressEnable = (Button) findViewById(R.id.btn_progress_enable);
		mProgressEnableCancel = (Button) findViewById(R.id.btn_progress_enable_cancel);
		mFinishDialog = (Button) findViewById(R.id.btn_finish_dialog);
		mNoNetDialog = (Button) findViewById(R.id.btn_no_net);
		mCustomDialog = (Button) findViewById(R.id.btn_custom);
		mProgressHor = (Button) findViewById(R.id.btn_progress_hor_dialog);
		mSingleChoice = (Button) findViewById(R.id.btn_single_choise_dialog);

		mProgressEnable.setOnClickListener(this);
		mProgressEnableCancel.setOnClickListener(this);
		mFinishDialog.setOnClickListener(this);
		mNoNetDialog.setOnClickListener(this);
		mCustomDialog.setOnClickListener(this);
		mProgressHor.setOnClickListener(this);
		mSingleChoice.setOnClickListener(this);

		ProgressDialogFragment dialog = (ProgressDialogFragment) getSupportFragmentManager()
				.findFragmentByTag(ProgressDialogFragment.TAG);
		if (dialog != null) {
			dialog.setOnCancelListener(this);
		}

		updateSingleChoiseListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_progress_enable:
			createProgress();
			break;

		case R.id.btn_progress_enable_cancel:
			createCancelableProgress(this);
			break;

		case R.id.btn_finish_dialog:
			createFinishDialog(R.string.dlg_finish);
			break;

		case R.id.btn_no_net:
			createDialog(DIALOG_NO_NET);
			break;

		case R.id.btn_custom:
			createDialog(DIALOG_CUSTOM);
			break;

		case R.id.btn_progress_hor_dialog:
			Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT)
					.show();
			break;

		case R.id.btn_single_choise_dialog:
			createSingleChoise(R.string.dlg_single_choise_title,
					R.array.dlg_single_choise_items, 0, this);
			break;

		default:
			break;
		}
	}

	@Override
	public void createDialog(int id) {
		switch (id) {
		case DIALOG_CUSTOM:
			final AlertDialogFragment dialog = AlertDialogFragment.newInstance(
					android.R.string.dialog_alert_title, R.string.dlg_custom,
					true, true, 0);
			dialog.setConfirmationDialogFragmentListener(this);
			dialog.show(getSupportFragmentManager(), null);
			break;

		default:
			super.createDialog(id);
			break;
		}
	}

	@Override
	public void OnCancelDialog() {
		dismissProgress();
	}

	@Override
	public void onPositiveClick(DialogInterface dialog, int position) {
		Toast.makeText(this, R.string.toast_ok, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNegativeClick(DialogInterface dialog, int position) {
		Toast.makeText(this, R.string.toast_cancel, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(DialogInterface dialog, int position) {
		Toast.makeText(
				this,
				getString(R.string.dlg_single_choise_btn)
						+ " "
						+ getResources().getStringArray(
								R.array.dlg_single_choise_items)[position],
				Toast.LENGTH_SHORT).show();
		dialog.dismiss();
	}

}
