package com.example.usermodule;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.connection.HttpRequest;
import com.example.secrettalk5.LoginActivity;
import com.example.secrettalk5.RegisterActivity;

public class UserModule extends AsyncTask<String,Void,String> {

	public Context context=null;
	private String url="http://140.116.234.174:8131/users/";
	private String RequestOption;
	public LoginActivity LA;
	public RegisterActivity RA;
	
	@Override
	protected String doInBackground(String... params) {
		try {
			
			//Get Request Option
			RequestOption=params[0];
			
			//Register Option
			if(RequestOption.equals("Register")){
				String parameter =params[1];
				String requestURL=url+"create?request="+java.net.URLEncoder.encode(parameter,"UTF-8");
				HttpRequest request=new HttpRequest();
				Log.v("UserModule","Send Request:"+requestURL);
				
				return request.GET(requestURL);
			}
			
			//Login Option
			else if(RequestOption.equals("Login")){	
				String parameter =params[1];
				String requestURL;
				requestURL = url+"login?request="+java.net.URLEncoder.encode(parameter,"UTF-8");
				Log.v("UserModule", requestURL);
				HttpRequest request=new HttpRequest();
				return request.GET(requestURL);
				
			}
			else
			{
				return "123";
			}
		}
		
		catch (UnsupportedEncodingException e) {
			
			return e.toString();
			
		}
				
	}
	
	@Override
    protected void onPostExecute(String result) {
		 
		try {
			
			JSONObject resultObject = new JSONObject(result);
			Log.v("UserModule","Get result:"+result.toString());
			
			ExecuteResult(resultObject);
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
			Log.v("UserModule","Get result:"+result.toString());
			
			
		}
		
	}
	
	private void ExecuteResult(JSONObject resultObject){
		
		if(RequestOption.equals("Login")){
			LoginFinish(resultObject);
		}
		
		if(RequestOption.equals("Register")){
			Log.v("UserModule","Start RegisterFinish");
			RegisterFinish(resultObject);
			
			
		}
		
	}
	
	private void LoginFinish(JSONObject resultObject){
		
		try {
			
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
						
			//Change Page To MainActivity
			if(response.equals("0")){
				UserInformation.Username=resultObject.getString("Username");
				LA.StartMainActivity();	
			}
			
			else{
				//Show Login Fail Message
				Toast.makeText(context, message , Toast.LENGTH_LONG).show();
			}
			
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		
		
	}
	
	private void RegisterFinish(JSONObject resultObject){
		try {
			
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
			
			if(response.equals("0")){
				RA.RegisterSuccessEvent();
			}
			
			Toast.makeText(context, message , Toast.LENGTH_LONG).show();
			
			
			Log.v("UserModule",message);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(context, e.toString() , Toast.LENGTH_LONG).show();
			
		}
		
		
	}
	
	
	
}
