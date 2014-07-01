package com.example.articlemodule;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.connection.HttpRequest;
import com.example.secrettalk5.Article_DaynamicLayout_Frament;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ReplyModule extends AsyncTask<String,Void,String> {
	
	private String url="http://140.116.234.174:8131/reply/";
	private String RequestOption;
	public Context context=null;
	public Article_DaynamicLayout_Frament articleDynamicFragment;
	@Override
	protected String doInBackground(String... params) {
		try{
			RequestOption=params[0];
			String parameterEncode=java.net.URLEncoder.encode(params[1],"UTF-8");
			HttpRequest request=new HttpRequest();
			String requestURL=null;
			if(RequestOption.equals("Content")){
				requestURL=url+"content?request="+ parameterEncode;
			}
			else
			{
				
				
			}
			
			Log.v("ReplyModule","Send Request:"+requestURL);
			return request.GET(requestURL);
		}
		catch(Exception ex){
		
		}
		return null;
	}
	
	@Override
    protected void onPostExecute(String result) {
		try {
				JSONObject resultObject = new JSONObject(result);
				Log.v("ReplyModule","Get result:"+result.toString());
			
				ExecuteResult(resultObject);
			
			
		} catch (JSONException e) {
			
			Toast.makeText(context, "Error In Post Execute", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			Log.v("ReplyModule","Get result:"+result.toString());
		}
	}
	
	private void ExecuteResult(JSONObject resultObject){
		
		if(RequestOption.equals("Content")){
			ContentFinish(resultObject);
		}
			
		else{
			
		}
		
		
		
	}
	
	private void ContentFinish(JSONObject resultObject)
	{
		try {
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
			
			//If Success
			if(response.equals("0")){
				String getReplyList=resultObject.getString("ReplyList");
				JSONArray replyList=new JSONArray(getReplyList);
				
				ArrayList<Article> reply_ArrayList=new ArrayList<Article>();
				
				for(int i=0;i<replyList.length();i++){
					JSONObject article =replyList.getJSONObject(i);
					
					String author_id=article.getString("author_id");
					String content=article.getString("content");
					String created_Time=article.getString("created_at");
					
					
					
					
				}
				
				
			}
			else{
				
				
			}
			
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		}
		catch(JSONException e){
			
			Toast.makeText(context, "Error In Create Finish", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			
		}
		
		
	}
	
	
}
