package com.example.announcement;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.articlemodule.Article;
import com.example.connection.HttpRequest;
import com.example.globalcontainer.GlobalContainer;
import com.example.secrettalk5.LoginActivity;

import android.os.AsyncTask;
import android.util.Log;

public class AnnouncementModule extends AsyncTask<String,Void,String> {
	
	private String url="http://140.116.234.174:8131/announcement/";
	private String RequestOption;
	public  LoginActivity LA;
	@Override
	protected String doInBackground(String... params) {
		
		try {
			RequestOption=params[0];
			String parameterEncode=java.net.URLEncoder.encode(params[1],"UTF-8");
			HttpRequest request=new HttpRequest();
			String requestURL=null;
			if(RequestOption.equals("Retrieve")){
				requestURL=url+"retrieve?request="+ parameterEncode;
				
			}
			Log.v("AnnouncementModule","Send Request:"+requestURL);
			return request.GET(requestURL);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();	
		}
		
		
	}
	
	@Override
    protected void onPostExecute(String result) {
		try {
			JSONObject resultObject = new JSONObject(result);
			Log.v("AnnouncementModule","Get result:"+result.toString());
		
			ExecuteResult(resultObject);
		
		
	} catch (JSONException e) {
		
//		Toast.makeText(context, "Error In Post Execute", Toast.LENGTH_LONG).show();
		e.printStackTrace();
		Log.v("AnnouncementModule","Get result:"+result.toString());
	}
	}
	
	private void ExecuteResult(JSONObject resultObject){
		if(RequestOption.equals("Retrieve")){
			RetrieveFinish(resultObject);
		}
		
	}
	
	private void RetrieveFinish(JSONObject resultObject){
		try {
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
			if(response.equals("0")){
				String getAnnouncementList=resultObject.getString("AnnouncementList");
				JSONArray announcementList=new JSONArray(getAnnouncementList);
				ArrayList<Announcement> announcemen_ArrayList=new ArrayList<Announcement>();
				for(int i=0;i<announcementList.length();i++){
					JSONObject announcement =announcementList.getJSONObject(i);
					
					String content=announcement.getString("content");
					
					String created_Time=announcement.getString("created_at");
					
					Announcement announcementInstance=new Announcement();
					
					announcementInstance.content=content;
					announcementInstance.created_at=created_Time;
					
					announcemen_ArrayList.add(announcementInstance);

				}
	
				
				Log.v("AnnouncementModule", getAnnouncementList);
				GlobalContainer.announcemen_ArrayList=announcemen_ArrayList;

				LA.StartMainActivity();
			}
			
			
			
		}
		catch(JSONException e){
			
			
		}
	}
	

}
