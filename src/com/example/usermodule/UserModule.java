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
import com.example.secrettalk5.Setting_MyFragment;

public class UserModule extends AsyncTask<String,Void,String> {

	public Context context=null;
	private String url="http://140.116.234.174:8131/users/";
	private String RequestOption;
	public LoginActivity LA;
	public Setting_MyFragment SF;
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
			else if(RequestOption.equals("GetProfile")){
				String parameter =params[1];
				String requestURL;
				requestURL = url+"userprofile?request="+java.net.URLEncoder.encode(parameter,"UTF-8");
				Log.v("UserModule", requestURL);
				HttpRequest request=new HttpRequest();
				return request.GET(requestURL);
				
			}
			else if(RequestOption.equals("SetProfile")){
				String parameter =params[1];
				String requestURL;
				requestURL = url+"setprofile?request="+java.net.URLEncoder.encode(parameter,"UTF-8");
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
			Log.v("UserModule","Start LoginFinish");
			LoginFinish(resultObject);
		}
		
		if(RequestOption.equals("Register")){
			Log.v("UserModule","Start RegisterFinish");
			RegisterFinish(resultObject);
			
			
		}
		if(RequestOption.equals("GetProfile")){
			Log.v("UserModule","Start GetProfileFinish");
			GetProfileFinish(resultObject);
			
			
		}
		if(RequestOption.equals("SetProfile")){
			Log.v("UserModule","Start SetProfileFinish");
			SetProfileFinish(resultObject);
			
			
		}
		
	}
	
	private void SetProfileFinish(JSONObject resultObject){
		try {
			
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
						
			if(response.equals("0")){
				SF.SaveDataforSettingFragment();
				Toast.makeText(context, "成功修改設定囉!!" , Toast.LENGTH_LONG).show();
			}
			else{
				//Show Login Fail Message
				Toast.makeText(context, "錯誤!!沒有正確儲存，請再嘗試一次!!!" , Toast.LENGTH_LONG).show();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void GetProfileFinish(JSONObject resultObject){
		
		try {
			
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
						
			
			if(response.equals("0")){
				String userProfile=resultObject.getString("UserProfile");
				JSONObject userProfileJSONObject = new JSONObject(userProfile);
				
				UserInformation.BloodType=userProfileJSONObject.getString("BloodType");
				UserInformation.Birthday=userProfileJSONObject.getString("Birthday");
				UserInformation.CreatedTime=userProfileJSONObject.getString("created_at");
				UserInformation.Gender=userProfileJSONObject.getString("Gender");
				UserInformation.Interest=userProfileJSONObject.getString("Interest");
				UserInformation.Level=userProfileJSONObject.getString("Level");
				UserInformation.LoginDays = userProfileJSONObject.getString("LoginDays");
				UserInformation.Mood=userProfileJSONObject.getString("Mood");
				UserInformation.NickName=userProfileJSONObject.getString("Nickname");
				UserInformation.Personality=userProfileJSONObject.getString("Personality");
				UserInformation.Sign=userProfileJSONObject.getString("Sign");
				UserInformation.Score=userProfileJSONObject.getString("Score");
				
				LA.GetAnnouncement();	
				
			}
			
			else{
					
				
			}
			
			
		} catch (JSONException e) {

			e.printStackTrace();
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
				LA.GetInfoForSettingFragment();
				
				
				
			}
			
			else{
				//Show Login Fail Message
				//Toast.makeText(context, message , Toast.LENGTH_LONG).show();
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
			
//			//Toast.makeText(context, message , Toast.LENGTH_LONG).show();
			
			
			Log.v("UserModule",message);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			//Toast.makeText(context, e.toString() , Toast.LENGTH_LONG).show();
			
		}
		
		
	}
	
	
	
}
