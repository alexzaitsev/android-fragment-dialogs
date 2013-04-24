package com.alex_zaitsev.fragmentdialogsexample.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;

import com.alex_zaitsev.fragmentdialogsexample.MainActivity;
import com.alex_zaitsev.fragmentdialogsexample.R;
import com.jayway.android.robotium.solo.Solo;

public class MainTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	private String mWaitMessage;

	@SuppressLint("NewApi")
	public MainTest() {
		super(MainActivity.class);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		mWaitMessage = solo.getString(R.string.dlg_wait);
	}

	@After
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	@Test
	public void testNonCancelableProgressDialog() {
		solo.clickOnButton(solo.getString(R.string.btn_progress_enable));
		assertTrue(solo.waitForText(mWaitMessage));
	}

	@Test
	public void testCancelableProgressDialog() {
		solo.clickOnButton(solo.getString(R.string.btn_progress_enable_cancel));
		assertTrue(solo.waitForText(mWaitMessage));
		solo.goBack();
		assertFalse(solo.searchText(mWaitMessage));
	}

	@Test
	public void testFinishDialog() {
		solo.clickOnButton(solo.getString(R.string.btn_finish_dialog));
		assertTrue(solo.waitForText(solo.getString(R.string.dlg_finish)));
		solo.clickOnButton(solo.getString(R.string.btn_back));
	}

	@Test
	public void testNoNetDialog() {
		solo.clickOnButton(solo.getString(R.string.app_name));
		assertTrue(solo.waitForText(solo
				.getString(R.string.dlg_internet_required)));
		solo.goBack();
		assertFalse(solo.searchText(solo
				.getString(R.string.dlg_internet_required)));
	}

	@Test
	public void testHorProgressDialog() {
		solo.clickOnButton(solo.getString(R.string.btn_progress_hor_dialog));
	}

	@Test
	public void testSingleChoiceDialog() {
		solo.clickOnButton(solo.getString(R.string.btn_single_choise_dialog));
		assertTrue(solo.waitForText(solo
				.getString(R.string.dlg_single_choise_title)));
		Resources resources = getInstrumentation().getTargetContext()
				.getResources();
		String third = resources
				.getStringArray(R.array.dlg_single_choise_items)[2];
		solo.clickOnText(third);
		assertTrue(solo.waitForText(solo
				.getString(R.string.dlg_single_choise_btn) + " " + third));
	}
	
	@Test
	public void testCustomDialog() {
		solo.clickOnButton(solo.getString(R.string.btn_custom_dialog));
		assertTrue(solo.waitForText(solo
				.getString(R.string.dlg_custom)));
		solo.clickOnButton(solo.getString(android.R.string.ok));
		assertTrue(solo.waitForText(solo.getString(R.string.toast_ok)));
		
		solo.clickOnButton(solo.getString(R.string.btn_custom_dialog));
		assertTrue(solo.waitForText(solo
				.getString(R.string.dlg_custom)));
		solo.clickOnButton(solo.getString(android.R.string.cancel));
		assertTrue(solo.waitForText(solo.getString(R.string.toast_cancel)));
	}
}