package com.alex_zaisev.fragmentdialogs;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;

/**
 * 
 * @author A.Zaitsev(madeinussr91@gmail.com)
 * 
 */
public abstract class AbstractDialogFragment extends DialogFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public void onDestroyView() {
		if (getDialog() != null && getRetainInstance())
			getDialog().setOnDismissListener(null);
		super.onDestroyView();
	}

	@Override
	public int show(FragmentTransaction ft, String tag) {
		ft.add(this, tag);
		try {
			ft.commitAllowingStateLoss();
		} catch (IllegalStateException e) {
			// TODO sometimes dialog is missing
			return 0;
		}
		return new Random().nextInt();
	}
}
