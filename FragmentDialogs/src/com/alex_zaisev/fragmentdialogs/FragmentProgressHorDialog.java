package com.alex_zaisev.fragmentdialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;

/**
 * 
 * @author A.Zaitsev(madeinussr91@gmail.com)
 * 
 */
public class FragmentProgressHorDialog extends DialogFragment {

	private static final String BUNDLE_MESSAGE = "dialog_horizontal_message";
	private static final String BUNDLE_MAX_PROGRESS = "max_progress";

	private ProgressDialog mProgressDialog;

	private Handler mHandler = new Handler();

	public FragmentProgressHorDialog() {
	}

	public static FragmentProgressHorDialog newInstance(int stringId,
			int maxProgress) {
		FragmentProgressHorDialog f = new FragmentProgressHorDialog();
		Bundle args = new Bundle();
		args.putInt(BUNDLE_MESSAGE, stringId);
		args.putInt(BUNDLE_MAX_PROGRESS, maxProgress);
		f.setArguments(args);
		return f;
	}

	public int getMessageId() {
		return getArguments() == null ? -1 : getArguments()
				.getInt(BUNDLE_MESSAGE, -1);
	}

	public int getMaxProgress() {
		return getArguments() == null ? -1 : getArguments()
				.getInt(BUNDLE_MAX_PROGRESS, -1);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mProgressDialog = new ProgressDialog(getActivity());
		if (getMessageId() != -1) {
			mProgressDialog.setMessage(getString(getMessageId()));
		}
		mProgressDialog.setCancelable(false);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setProgress(0);
		mProgressDialog.setMax(getMaxProgress());
		return mProgressDialog;
	}

	public void setMessage(int messageId) {
		mProgressDialog.setMessage(getString(messageId));
	}

	public void setMessage(String message) {
		mProgressDialog.setMessage(message);
	}

	public void setProgress(final int progress) {
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				if (progress >= mProgressDialog.getMax()) {
					mProgressDialog.dismiss();
				} else {
					mProgressDialog.setProgress(progress);
				}
			}
		});
	}
}
