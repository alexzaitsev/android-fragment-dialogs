package com.alex_zaisev.fragmentdialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * 
 * @author A.Zaitsev(madeinussr91@gmail.com)
 * 
 */
public class ProgressDialogFragment extends AbstractDialogFragment implements
		android.content.DialogInterface.OnCancelListener {

	public static final String TAG = ProgressDialogFragment.class
			.getSimpleName();

	public interface OnCancelListener {
		void OnCancelDialog();
	}

	private static final String DIALOG_MESSAGE = "dialog_message";

	private ProgressDialog mProgressDialog;

	private OnCancelListener mCancelListener;

	public ProgressDialogFragment() {
	}

	public static ProgressDialogFragment newInstance(int stringId) {
		ProgressDialogFragment f = new ProgressDialogFragment();
		Bundle args = new Bundle();
		args.putInt(DIALOG_MESSAGE, stringId);
		f.setArguments(args);
		return f;
	}

	public void setOnCancelListener(OnCancelListener listener) {
		mCancelListener = listener;
	}

	public int getMessageId() {
		return getArguments() == null ? 0 : getArguments().getInt(
				DIALOG_MESSAGE, 0);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mProgressDialog = new ProgressDialog(getActivity());
		if (getMessageId() != 0) {
			mProgressDialog.setMessage(getString(getMessageId()));
		}
		mProgressDialog.setOnCancelListener(this);
		return mProgressDialog;
	}

	public void setMessage(int messageId) {
		mProgressDialog.setMessage(getString(messageId));
	}

	public void setMessage(String message) {
		mProgressDialog.setMessage(message);
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		mCancelListener.OnCancelDialog();
	}
}
