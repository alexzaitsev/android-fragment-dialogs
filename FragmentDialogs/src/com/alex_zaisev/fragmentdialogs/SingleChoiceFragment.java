package com.alex_zaisev.fragmentdialogs;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

/**
 * 
 * @author A.Zaitsev(madeinussr91@gmail.com)
 * 
 */
public class SingleChoiceFragment extends AbstractDialogFragment implements
		OnClickListener {

	public static final String TAG = SingleChoiceFragment.class.getSimpleName();
	private static final String BUNDLE_TITLE = "title";
	private static final String BUNDLE_ARRAY = "array";
	private static final String BUNDLE_SELECTED = "selected";

	public interface OnChoiceDialogClickListener {
		void onClick(DialogInterface dialog, int position);
	}

	private OnChoiceDialogClickListener listener;

	public void setOnClickListener(OnChoiceDialogClickListener listener) {
		this.listener = listener;
	}

	public static SingleChoiceFragment newInstance(int title, int itemsArray,
			int selectedItem) {
		SingleChoiceFragment frag = new SingleChoiceFragment();
		Bundle args = new Bundle();
		args.putInt(BUNDLE_TITLE, title);
		args.putInt(BUNDLE_ARRAY, itemsArray);
		args.putInt(BUNDLE_SELECTED, selectedItem);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int title = getArguments().getInt(BUNDLE_TITLE);
		int array = getArguments().getInt(BUNDLE_ARRAY);
		int selected = getArguments().getInt(BUNDLE_SELECTED);

		Builder dialog;
		dialog = new AlertDialog.Builder(getActivity()).setTitle(title)
				.setSingleChoiceItems(array, selected, this);

		return dialog.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		listener.onClick(dialog, which);
	}
}
