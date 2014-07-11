package com.example.articlemodule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.connection.HttpRequest;
import com.example.secrettalk5.Article_DaynamicLayout_Frament;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
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
			else if(RequestOption.equals("Create")){
				requestURL=url+"create?request="+ parameterEncode;
				
			}
			else if(RequestOption.equals("CheckNewReply")){
				requestURL=url+"content?request="+ parameterEncode;
				
			}
			else{
				
				
			}
			
			Log.v("ReplyModule","Send Request:"+requestURL);
			return request.GET(requestURL);
		}
		catch(Exception ex){
			
			ex.printStackTrace();
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
		else if(RequestOption.equals("Create")){
			CreateFinish(resultObject);
			
		}
		else if(RequestOption.equals("CheckNewReply")){
			CheckNewReplyFinish(resultObject);
			
		}
		else{
			
		}
	}
	
	
	private void CheckNewReplyFinish(JSONObject resultObject){
		try {
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
			
			//If Success
			if(response.equals("0")){
				String getReplyList=resultObject.getString("ReplyList");
				JSONArray replyList=new JSONArray(getReplyList);
				
				ArrayList<Reply> reply_ArrayList=new ArrayList<Reply>();
				
				for(int i=0;i<replyList.length();i++){
					JSONObject article =replyList.getJSONObject(i);
					
					//Get Author Id
					String author_id=article.getString("author_id");
					
					//Get Content
					String content=article.getString("content");
					
					//Get Time
					String created_Time=article.getString("created_at");
					
					//Get Level
					String level = article.getString("level");
					
					//Reply
					Reply reply=new Reply();
					
					reply.author=author_id;
					reply.content=content;
					reply.created_Time=ConvertToDate(created_Time);
					reply.level=level;
					
					reply_ArrayList.add(reply);
				}
				
				articleDynamicFragment.CheckNewReplyFinish(reply_ArrayList);
				
				
			}
			else{
				
				
			}
			Log.v("ReplyModule", message);
			if(context!=null){
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
			}
		}
		catch(JSONException e){
			Log.v("ReplyModule","Error In CheckNewReplyFinish");
			Toast.makeText(context, "Error In Check New Reply", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			
		}
		
	}
	
	
	private void CreateFinish(JSONObject resultObject){
		try {
			Log.v("ReplyModule", resultObject.toString());
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
			
			//If Success
			if(response.equals("0")){
				String replyMessage=resultObject.getString("Reply");
				JSONObject replyObject = new JSONObject(replyMessage);
				
				Reply reply=new Reply();
				
				//Get String
				String content=replyObject.getString("content");
				String article_id=replyObject.getString("article_id");
				String author_id=replyObject.getString("author_id");
				String created_at=replyObject.getString("created_at");
				String level=replyObject.getString("level");
				//Add to Reply Object
				reply.article_id=article_id;
				reply.author=author_id;
				reply.created_Time=ConvertToDate(created_at);
				reply.content=content;
				reply.level=level;
				
				articleDynamicFragment.CreateNewReplyFinish(reply);
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
				
				ArrayList<Reply> reply_ArrayList=new ArrayList<Reply>();
				
				for(int i=0;i<replyList.length();i++){
					JSONObject article =replyList.getJSONObject(i);
					
					//Get Author Id
					String author_id=article.getString("author_id");
					//Get Content
					String content=article.getString("content");
					//Get Time
					String created_Time=article.getString("created_at");
					// Get Level
					String level=article.getString("level");
					
					//Reply
					Reply reply=new Reply();
					
					reply.author=author_id;
					reply.content=content;
					reply.created_Time=ConvertToDate(created_Time);
					reply.level=level;
					reply_ArrayList.add(reply);
				}
				
				articleDynamicFragment.GetHistoricalDialogueFinish(reply_ArrayList);
				
				
			}
			else{
				
				
			}
			Log.v("ReplyModule", message);
			if(context!=null)
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		}
		catch(JSONException e){
			
			Toast.makeText(context, "Error In Create Finish", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			
		}
		
		
	}
	
	
	private Date ConvertToDate(String dateString){
		Date returnDate=null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			returnDate= sdf.parse(dateString);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(context, "Error In Create Finish Time Convert", Toast.LENGTH_LONG).show();
			Log.v("ReplyModule","ConvertDateTimeFail:"+dateString);
		}
			
		return returnDate;
			
	}
}
