package com.app.studio.tutor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.studio.tutor.utils.PreferenceUtils;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity {

	private RelativeLayout tutorTypeLayout;
	private RelativeLayout selectCountryLayout;
	private Button  btnSignUp;
	private Context mContext;
	private String[] country;
	private ArrayAdapter<String> adapter;
	private ListView listView;
	private TextView alreadyHaveTextView;
	private TextView selectCountryTextView;
	private TextView selectTypeTextView;
    private EditText userNameEditText;
    private EditText numberEditText;
	int radioButtonID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_screen);

		mContext	        =	this;
		btnSignUp	        =	(Button)findViewById(R.id.button_signup);
		selectCountryLayout	=	(RelativeLayout)findViewById(R.id.tutor_country_layout);
		tutorTypeLayout		=	(RelativeLayout)findViewById(R.id.tutor_select_layout);
		alreadyHaveTextView	=	(TextView)findViewById(R.id.textView_already_account);
		selectCountryTextView =	(TextView)findViewById(R.id.select_country_textView);
		selectTypeTextView	=	(TextView)findViewById(R.id.select_type_textView);
        userNameEditText    =   (EditText)findViewById(R.id.editText_userName);
        numberEditText      =   (EditText)findViewById(R.id.editText_number);
		country		        =	getResources().getStringArray(R.array.countries_array);

		alreadyHaveTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent	=	new Intent(mContext, LoginActivity.class);
				startActivity(intent);
			}
		});

		btnSignUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

                if(userNameEditText.getText().toString().isEmpty()) {
                    Toast.makeText(mContext, "Enter Username", Toast.LENGTH_SHORT).show();
                } else if(selectCountryTextView.getText().toString().isEmpty()) {
                    Toast.makeText(mContext, "Select Country", Toast.LENGTH_SHORT).show();
                } else if(selectTypeTextView.getText().toString().isEmpty()) {
                    Toast.makeText(mContext, "Select tutor type", Toast.LENGTH_SHORT).show();
                } else if(numberEditText.getText().toString().isEmpty()) {
                    Toast.makeText(mContext, "Enter number", Toast.LENGTH_SHORT).show();
                } else {
                    PreferenceUtils.setUserName(mContext, userNameEditText.getText().toString());
                    Intent intent	=	new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }
			}
		});

		tutorTypeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				selectTypeDialog();
			}
		});

		selectCountryLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				selectCountryDialog();
			}
		});
	}

	private void selectCountryDialog() {

		// custom dialog
		final Dialog dialog = new Dialog(mContext);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_select_country);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
		// set the custom dialog components
		listView	=	(ListView)dialog.findViewById(R.id.listView_country);
		adapter		=	new ArrayAdapter<String>(mContext, R.layout.dialog_country_list_item, country);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {

				dialog.dismiss();
                selectCountryTextView.setText(country[position]);
			}
		});
		dialog.show();
	}
	
	private void selectTypeDialog() {

		// custom dialog
		final Dialog dialog = new Dialog(mContext);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_select_tutor);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
		// set the custom dialog components

		RadioGroup radioGroup	=	(RadioGroup)dialog.findViewById(R.id.radioGroup);

		if(radioButtonID != 0) {
            RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
            //int idx 		  = group.indexOfChild(radioButton);
            radioButton.setChecked(true);
            selectTypeTextView.setText(radioButton.getText().toString());
        }

		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				radioButtonID 			= group.getCheckedRadioButtonId();
				RadioButton radioButton = (RadioButton) group.findViewById(radioButtonID);
				//int idx 		  = group.indexOfChild(radioButton);
				selectTypeTextView.setText(radioButton.getText().toString());
				dialog.dismiss();
			}
		});
		dialog.show();
	}
}

