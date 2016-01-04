
package com.app.studio.tutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.studio.tutor.utils.PreferenceUtils;

public class LoginActivity extends ActionBarActivity {

	//UI Element
	private TextView txtViewForgotPass;
	private Button   btnLogin;
	private EditText edtTextUserName;
	private EditText edtTextPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		
		txtViewForgotPass	=	(TextView)findViewById(R.id.textView_forgot_password);
		btnLogin			=	(Button) findViewById(R.id.button_login);
        edtTextUserName     =   (EditText)findViewById(R.id.editText_userName);
        edtTextPassword     =   (EditText)findViewById(R.id.editText_password);
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                String userName =   edtTextUserName.getText().toString();
                String password =   edtTextPassword.getText().toString();

				if(userName.isEmpty()) {
					Toast.makeText(LoginActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
				} else if(password.isEmpty()) {
					Toast.makeText(LoginActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
				} else {
					if(userName.equals(PreferenceUtils.getUserName(LoginActivity.this)) && password.equals("123456")) {
						startActivity(new Intent(getApplicationContext(), MainActivity.class));
					} else {
						Toast.makeText(LoginActivity.this, "Enter Valid username or password", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		txtViewForgotPass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
			}
		});
	}

	
}
