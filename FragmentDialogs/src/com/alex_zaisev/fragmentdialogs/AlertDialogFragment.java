package com.alex_zaisev.fragmentdialogs;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

/**
 * Simple confirmation dialog with callback
 * 
 * @author http://marakana.com/s/post/1250/android_fragments_tutorial,
 *         A.Zaitsev(madeinussr91@gmail.com)
 * 
 */
public class AlertDialogFragment extends AbstractDialogFragment implements
		OnClickListener {

	public static final String TAG = AlertDialogFragment.class.getSimpleName();
	private static final String BUNDLE_TITLE = "title";
	private static final String BUNDLE_MESSAGE = "message";
	private static final String BUNDLE_MESSAGE_STRING = "message_s";
	private static final String BUNDLE_OK = "ok";
	private static final String BUNDLE_CANCEL = "cancel";
	private static final String BUNDLE_POSITIVE = "positive";

	private ConfirmationDialogFragmentListener listener;

	public interface ConfirmationDialogFragmentListener {
		public void onPositiveClick(DialogInterface dialog, int position);

		public void onNegativeClick(DialogInterface dialog, int position);
	}

	public void setConfirmationDialogFragmentListener(
			ConfirmationDialogFragmentListener listener) {
		this.listener = listener;
	}

	public static AlertDialogFragment newInstance(int title, int message,
			boolean isOk, boolean isCancel, int positive) {
		AlertDialogFragment frag = new AlertDialogFragment();
		Bundle args = new Bundle();
		args.putInt(BUNDLE_TITLE, title);
		args.putInt(BUNDLE_MESSAGE, message);
		args.putBoolean(BUNDLE_OK, isOk);
		args.putBoolean(BUNDLE_CANCEL, isCancel);
		args.putInt(BUNDLE_POSITIVE, positive);
		frag.setArguments(args);
		return frag;
	}

	public static AlertDialogFragment newInstance(int title, String message,
			boolean isOk, boolean isCancel, int positive) {
		AlertDialogFragment frag = new AlertDialogFragment();
		Bundle args = new Bundle();
		args.putInt(BUNDLE_TITLE, title);
		args.putString(BUNDLE_MESSAGE_STRING, message);
		args.putBoolean(BUNDLE_OK, isOk);
		args.putBoolean(BUNDLE_CANCEL, isCancel);
		args.putInt(BUNDLE_POSITIVE, positive);
		frag.setArguments(args);
		return frag;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int title = getArguments().getInt(BUNDLE_TITLE);
		int message = getArguments().getInt(BUNDLE_MESSAGE);
		String messageString = getArguments().getString(BUNDLE_MESSAGE_STRING);
		boolean isOk = getArguments().getBoolean(BUNDLE_OK);
		boolean isCancel = getArguments().getBoolean(BUNDLE_CANCEL);
		int positive = getArguments().getInt(BUNDLE_POSITIVE) == 0 ? android.R.string.ok
				: getArguments().getInt(BUNDLE_POSITIVE);

		if (messageString == null) {
			messageString = getString(message);
		}
		
		Builder dialog;
		dialog = new AlertDialog.Builder(getActivity())
				.setIcon(android.R.drawable.ic_dialog_alert).setTitle(title)
				.setMessage(messageString);
		if (isOk) {
			dialog.setPositiveButton(positive, this);
		}
		if (isCancel) {
			dialog.setNegativeButton(android.R.string.cancel, this);
		}

		return dialog.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (listener != null) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				listener.onPositiveClick(dialog, which);
				break;
			default:
				listener.onNegativeClick(dialog, which);
				break;
			}
		} else {
			throw new IllegalStateException("Не прикреплен слушатель!");
		}
	}
}
