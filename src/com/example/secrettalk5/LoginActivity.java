package com.example.secrettalk5;

import java.util.HashMap;

import org.json.JSONObject;







import com.example.usermodule.UserModule;

import android.app.Activity;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//Register Login Button OnClick Event
		Button LoginButton=(Button)findViewById(R.id.Login_LoginButton);
		LoginButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				LoginEvent();
			}
		});
		
		//Register Register Button OnClick Event
		Button RegisterButton=(Button)findViewById(R.id.Login_RegisterButton);
		RegisterButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				StartRegiserActivity();
			}
		});
	}
	
	private void LoginEvent(){
		//Get User Input
		String username=((EditText)findViewById(R.id.Login_UsernameEditText)).getText().toString();
		String password=((EditText)findViewById(R.id.Login_PasswordEditText)).getText().toString();
		
		//Initial HashMap 
		HashMap hm=new HashMap<String, String>();
		hm.put("username", username);
		hm.put("password", password);
		
		//Convert HashMap to JSONObject
		JSONObject jo=new JSONObject(hm);
		
		//Send Login Request
		UserModule um=new UserModule();
		um.LA=LoginActivity.this;
	    um.context=LoginActivity.this;
	    um.execute("Login",jo.toString());
		
	}
	
	public void StartMainActivity(){
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		intent.setClass(LoginActivity.this, MainActivity.class);
		
		//Close This Activity 
		finish();
		
		//Start new Activity
		startActivity(intent);
	}
	
	public void StartRegiserActivity(){
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		intent.setClass(LoginActivity.this, RegisterActivity.class);
		
		//Start new Activity
		startActivity(intent);
	}


}
