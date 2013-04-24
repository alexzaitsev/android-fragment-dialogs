package com.alex_zaisev.fragmentdialogs;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.alex_zaisev.fragmentdialogs.AlertDialogFragment.ConfirmationDialogFragmentListener;
import com.alex_zaisev.fragmentdialogs.ProgressDialogFragment.OnCancelListener;
import com.alex_zaisev.fragmentdialogs.SingleChoiceFragment.OnChoiceDialogClickListener;
import com.alex_zaitsev.fragmentdialogs.R;

/**
 * 
 * @author A.Zaitsev(madeinussr91@gmail.com)
 * 
 */
abstract public class AbstractDialogActivity extends FragmentActivity {

	public static final int DIALOG_WAITING = 2;
	public static final int DIALOG_NO_NET = 3;

	public void createDialog(int id) {
		if (isFinishing()) {
			return;
		}
		switch (id) {
		case DIALOG_WAITING:
			createProgress();
			break;

		case DIALOG_NO_NET:
			createOneShotDialog(R.string.dlg_internet_required);
			break;
		}
	}

	private ConfirmationDialogFragmentListener mOneShotDialogListener = new ConfirmationDialogFragmentListener() {

		@Override
		public void onPositiveClick(DialogInterface dialog, int position) {
			dialog.dismiss();
		}

		@Override
		public void onNegativeClick(DialogInterface dialog, int position) {
		}
	};

	public void createOneShotDialog(int message) {
		final AlertDialogFragment dialog = AlertDialogFragment.newInstance(
				android.R.string.dialog_alert_title, message, true, false,
				R.string.btn_back);
		dialog.setConfirmationDialogFragmentListener(mOneShotDialogListener);
		dialog.show(getSupportFragmentManager(), null);
	}

	public void createProgress() {
		createProgress(R.string.dlg_wait);
	}

	public void createProgress(int message) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment prev = getSupportFragmentManager().findFragmentByTag(
				ProgressDialogFragment.TAG);
		if (prev != null) {
			ft.remove(prev);
		}
		// Create and show the dialog.
		ProgressDialogFragment dialog = ProgressDialogFragment
				.newInstance(message);
		dialog.setCancelable(false);
		dialog.show(ft, ProgressDialogFragment.TAG);
	}

	public void createCancelableProgress(OnCancelListener listener) {
		createCancelableProgress(R.string.dlg_wait, listener);
	}

	public void createCancelableProgress(int message, OnCancelListener listener) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment prev = getSupportFragmentManager().findFragmentByTag(
				ProgressDialogFragment.TAG);
		if (prev != null) {
			ft.remove(prev);
		}
		// Create and show the dialog.
		ProgressDialogFragment dialog = ProgressDialogFragment
				.newInstance(message);
		dialog.setCancelable(true);
		dialog.setOnCancelListener(listener);
		dialog.show(ft, ProgressDialogFragment.TAG);
	}

	private ConfirmationDialogFragmentListener mFinishDialogListener = new ConfirmationDialogFragmentListener() {

		@Override
		public void onPositiveClick(DialogInterface dialog, int position) {
			dialog.dismiss();
			finish();
		}

		@Override
		public void onNegativeClick(DialogInterface dialog, int position) {
		}
	};

	public void createFinishDialog(int message) {
		createFinishDialog(getString(message));
	}

	public void createFinishDialog(String message) {
		final AlertDialogFragment dialog = AlertDialogFragment.newInstance(
				android.R.string.dialog_alert_title, message, true, false,
				R.string.btn_back);
		dialog.setConfirmationDialogFragmentListener(mFinishDialogListener);
		dialog.show(getSupportFragmentManager().beginTransaction(),
				AlertDialogFragment.TAG);
	}

	public void dismissProgress() {
		dismissProgress(R.string.dlg_wait);
	}

	public void dismissProgress(int tagRes) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment dialogFragment = getSupportFragmentManager()
				.findFragmentByTag(ProgressDialogFragment.TAG);
		if (dialogFragment != null) {
			ft.remove(dialogFragment).commitAllowingStateLoss();
		}
	}

	public void createSingleChoise(int title, int array, int selected,
			OnChoiceDialogClickListener listener) {
		final SingleChoiceFragment dialog = SingleChoiceFragment.newInstance(
				title, array, selected);
		dialog.setOnClickListener(listener);
		dialog.show(getSupportFragmentManager().beginTransaction(),
				SingleChoiceFragment.TAG);
	}

	public void dismissSingleChoise() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment dialogFragment = getSupportFragmentManager()
				.findFragmentByTag(SingleChoiceFragment.TAG);
		if (dialogFragment != null) {
			ft.remove(dialogFragment).commitAllowingStateLoss();
		}
	}

	public void updateSingleChoiseListener(OnChoiceDialogClickListener listener) {
		SingleChoiceFragment dialog = (SingleChoiceFragment) getSupportFragmentManager()
				.findFragmentByTag(SingleChoiceFragment.TAG);
		if (dialog != null) {
			dialog.setOnClickListener(listener);
		}
	}
}
