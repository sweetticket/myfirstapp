package com.example.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private String mPreInput = "";
	private double mLastInput = 0.0;
	private String mOp = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	/**
	 * Displays the digit clicked in text box. If digits are already displayed
	 * in the text box, add the digit to the end of the pre-displayed input.
	 */
	public void displayDigit(View view) {
		if (mPreInput.length() < 10) {
			Button b = (Button) view;
			String new_digit = b.getText().toString();
			String display_num = mPreInput + new_digit;
			mPreInput = display_num;
			EditText textbox = (EditText) findViewById(R.id.calc_input);
			textbox.setText(mPreInput);
		}
	}

	/**
	 * Clears preinput.
	 */
	public void clearInput(View view) {
		mPreInput = "";
		EditText textbox = (EditText) findViewById(R.id.calc_input);
		textbox.setText(mPreInput);

	}

	/**
	 * Changes sign of preinput
	 */
	public void changeSigns(View view) {
		if (mPreInput.length() > 0) {
			if (mPreInput.substring(0, 1).equals("-")) {
				mPreInput = mPreInput.substring(1);
			} else {
				mPreInput = "-" + mPreInput;
			}
			EditText textbox = (EditText) findViewById(R.id.calc_input);
			textbox.setText(mPreInput);
		}
	}

	/**
	 * Sets fields for add operation
	 */
	public void setOp(View view) {
		Button b = (Button) view;
		mOp = (String) b.getText();
		if (mPreInput.length() > 0) {
			mLastInput = Double.parseDouble(mPreInput);
			mPreInput = "";
			EditText textbox = (EditText) findViewById(R.id.calc_input);
			textbox.setText(mPreInput);
		}
	}

	/**
	 * Calculates the final value using operation op, lastinput, and preinput.
	 * Sets preinput to the final value. Displays final value in textbox.
	 */
	public void calculate(View view) {
		EditText textbox = (EditText) findViewById(R.id.calc_input);
		if (mOp.equals("+")) {
			mPreInput = (mLastInput + Double.parseDouble(mPreInput)) + "";
		} else if (mOp.equals("-")) {
			mPreInput = (mLastInput - Double.parseDouble(mPreInput)) + "";
		}
		mOp = "";
		mLastInput = 0.0;
		textbox.setText(mPreInput);
	}

}
