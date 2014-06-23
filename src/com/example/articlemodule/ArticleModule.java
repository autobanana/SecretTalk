package com.example.articlemodule;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.connection.HttpRequest;

import android.os.AsyncTask;
import android.util.Log;

public class ArticleModule extends AsyncTask<String,Void,String> {
	
	private String url="http://140.116.234.174:8131/article/";
	private String RequestOption;
	
	
	@Override
	protected String doInBackground(String... params) {
		try{
			RequestOption=params[0];
			String parameterEncode=java.net.URLEncoder.encode(params[1],"UTF-8");
			HttpRequest request=new HttpRequest();
			String requestURL=null;
			if(RequestOption.equals("Create")){
				requestURL=url+"create?request="+ parameterEncode;
			}
			else if(RequestOption.equals("List")){
				requestURL=url+"list?request="+ parameterEncode;
			}
			else if(RequestOption.equals("NewArticle")){
				requestURL=url+"newarticle?request="+ parameterEncode;
			}
			else{
				
				
			}
			
			Log.v("ArticleModule","Send Request:"+requestURL);
			return request.GET(requestURL);
		}
		catch(Exception ex){
			
			return ex.toString();	
		}
		
	}
	
	
	@Override
    protected void onPostExecute(String result) {
		try {
				JSONObject resultObject = new JSONObject(result);
				Log.v("ArticleModule","Get result:"+result.toString());
			
				ExecuteResult(resultObject);
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
			Log.v("ArticleModule","Get result:"+result.toString());
		}
	}
	
	private void ExecuteResult(JSONObject resultObject){
		
		if(RequestOption.equals("Create")){
			CreateFinish(resultObject);
		}
		else if(RequestOption.equals("List")){
			GetListFinish(resultObject);
		}
		else if(RequestOption.equals("NewArticle")){
			GetNewArticleFinish(resultObject);
			
		}
		else{}
		
		
		
	}
	
	private void CreateFinish(JSONObject resultObject){
		try {
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
			
			//If Success
			if(response.equals("0")){
			
			}
			else{
				
				
			}
		}
		catch(JSONException e){
			e.printStackTrace();
			
		}
		
	}
	
	private void GetListFinish(JSONObject resultObject){
		
		try {
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
			
			//If Success
			if(response.equals("0")){
			
			}
			else{
				
				
			}
		}
		catch(JSONException e){
			
			e.printStackTrace();
		}
		
		
	}
	
	private void GetNewArticleFinish(JSONObject resultObject){
		try {
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
			
			//If Success
			if(response.equals("0")){
			
			}
			else{
				
				
			}
		}
		catch(JSONException e){
			
			e.printStackTrace();
		}
		
	}

}
