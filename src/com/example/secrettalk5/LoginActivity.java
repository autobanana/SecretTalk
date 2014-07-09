package com.example.secrettalk5;

import java.util.HashMap;

import org.json.JSONObject;

import com.example.articlemodule.ArticleModule;
import com.example.usermodule.UserInformation;
import com.example.usermodule.UserModule;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

			
	private ConnectionDetector cd;
	
	private SharedPreferences account ;
	
	private String username,password;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_login);
		
		
		account = getSharedPreferences("ACCOUNT", 0);	
		
		username = account.getString("USERNAME", "");
		password = account.getString("PASSWORD", "");
		
		
		cd = new ConnectionDetector(getApplicationContext());
        cd.showConnction();		
			
        //未登入過,或沒有網路時,進入原本登入畫面
        if(username.isEmpty() || !cd.isConnectingToInternet()){   
	        setContentView(R.layout.activity_login_new);
	        
			//Register Login Button OnClick Event
			Button LoginButton=(Button)findViewById(R.id.Login_loginButton);
			LoginButton.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					if(cd.isConnectingToInternet()) {
						username=((EditText)findViewById(R.id.Login_UsernameEditText)).getText().toString();
						password=((EditText)findViewById(R.id.Login_PasswordEditText)).getText().toString();
						LoginEvent(username,password);
					}
					else
					{
						cd.showConnction();
					}
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
        else{
        	
        	setContentView(R.layout.begin_frefence_register_anim);
        	LoginEvent(username,password);
        	
	    }
        
        
		
		/*
		//Post Article Button OnClick Event
		Button PostButton=(Button)findViewById(R.id.Login_PostButton);
		PostButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String content="123";
				PostArticle(content);
			}
		});
		
		
		//Get Article Button OnClick Event
		Button GetArticleButton=(Button)findViewById(R.id.Login_GetArticleList);
		GetArticleButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GetArticle();
				
			}
		});
	
		//Get New Article OnClick Event
		Button GetNewArticleButton=(Button)findViewById(R.id.Login_GetNewArticle);
		GetNewArticleButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				GetNewArticle();
			}
			
		});
		*/	
	}
	
	private void LoginEvent(String username,String password){
		
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
	

		
	private void PostArticle(String content){
		//Initial HashMap 
		HashMap hm=new HashMap<String, String>();
		//hm.put("username", UserInformation.Username);
		hm.put("username", "123");
		hm.put("content", content);
		
		//Convert HashMap to JSONObject
		JSONObject jo=new JSONObject(hm);
		
		//Send Article Post Request
		ArticleModule am=new ArticleModule();
		am.context=this;
		am.execute("Create",jo.toString());
		
		
	} 
	
	private void GetArticle(){
		//Initial HashMap 
		HashMap hm=new HashMap<String, String>();
		//hm.put("username", UserInformation.Username);
		hm.put("username", "123");
		
		//Convert HashMap to JSONObject
		JSONObject jo=new JSONObject(hm);
		
		//Send Article Post Request
		ArticleModule am=new ArticleModule();
		am.context=this;
		am.execute("List",jo.toString());
		
	}
	
	private void GetNewArticle(){
		//Initial HashMap 
		HashMap hm=new HashMap<String, String>();
		//hm.put("username", UserInformation.Username);
		hm.put("username", "123");
		
		
		//Convert HashMap to JSONObject
		JSONObject jo=new JSONObject(hm);
		
		//Send Article Post Request
		ArticleModule am=new ArticleModule();
		am.context=this;
		am.execute("NewArticle",jo.toString());
		
	}
	
	public void StartMainActivity(){
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		intent.setClass(LoginActivity.this, MainActivity.class);
		
		account.edit().putString("USERNAME", username).commit();
		account.edit().putString("PASSWORD", password).commit();
		
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
