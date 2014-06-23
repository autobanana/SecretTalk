package com.example.secrettalk5;

import java.util.HashMap;

import org.json.JSONObject;

import com.example.usermodule.UserModule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		//Register Login Button OnClick Event
		Button RegisterButton=(Button)findViewById(R.id.Register_RegisterButton);
		RegisterButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				RegisterEvent();
			}
		});
		
	}
	
	private void RegisterEvent(){
		
		//Get All Content
		String username=((EditText)findViewById(R.id.Register_UsernameEditText)).getText().toString();
		String password=((EditText)findViewById(R.id.Register_PasswordEditText)).getText().toString();
		String nickname=((EditText)findViewById(R.id.Register_NicknameEditText)).getText().toString();
		String realname=((EditText)findViewById(R.id.Register_RealnameEditText)).getText().toString();
		String birthday=((EditText)findViewById(R.id.Register_BirthdayEditText)).getText().toString();
		
		if(username==null||password==null||
				nickname==null||realname==null||
				realname==null||birthday==null){
			
			Toast.makeText(this, "Please fill all content", Toast.LENGTH_LONG).show();
			
		}
		
		else{
			
			//Initial HashMap 
			HashMap<String, String> hm=new HashMap<String, String>();
			hm.put("username", username);
			hm.put("password", password);
			hm.put("nickname", nickname);
			hm.put("realname", realname);
			hm.put("birthday", birthday);
			
			//Convert HashMap to JSONObject
			JSONObject jo=new JSONObject(hm);
			
			//Send Login Request
			UserModule um=new UserModule();
		    um.context=this;
		    um.RA=this;
		    um.execute("Register",jo.toString());
			
		}
		
	}
	
	public void RegisterSuccessEvent(){
		finish();
	}
	
	
	
	
	
}
