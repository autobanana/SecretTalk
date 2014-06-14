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
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button LoginButton=(Button)findViewById(R.id.Login_LoginButton);
		
		LoginButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				LoginEvent();
			}
		});
		
	}
	
	private void LoginEvent(){
		
		HashMap hm=new HashMap<String, String>();
		
		String username;
		String password;
		hm.put("username", "123");
		hm.put("password", "456");
		  	
		JSONObject jo=new JSONObject(hm);
		  	
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


}
