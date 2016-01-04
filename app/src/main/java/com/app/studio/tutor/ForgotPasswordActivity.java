package com.app.studio.tutor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.studio.tutor.utils.ValidationUtils;

public class ForgotPasswordActivity extends AppCompatActivity {

	private EditText emailEditText;
	private Button   submitButton;
	private Context  context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);

		emailEditText	=	(EditText)findViewById(R.id.editText_email);
		submitButton	=	(Button)findViewById(R.id.button_submit);
		context			=	this;

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String email	=	emailEditText.getText().toString();

				if(email.isEmpty()) {
					Toast.makeText(context, "Enter email", Toast.LENGTH_SHORT).show();
				} else {
					if (!ValidationUtils.emailValidator(email)) {
						Toast.makeText(context, "Enter valid email", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(context, "Password sent to your mail.", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
}
