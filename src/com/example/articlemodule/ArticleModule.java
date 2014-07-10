package com.example.articlemodule;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.connection.HttpRequest;
import com.example.secrettalk5.Listening_MyFragment;
import com.example.secrettalk5.PostActicle_Fragment;
import com.example.secrettalk5.Talking_MyFragment;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ArticleModule extends AsyncTask<String,Void,String> {
	
	private String url="http://140.116.234.174:8131/article/";
	private String RequestOption;
	public Context context=null;
	public Talking_MyFragment talkingFragment;
	public Listening_MyFragment listeningFragment;
	public PostActicle_Fragment postArticleFragment;
	
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
			
//			Toast.makeText(context, "Error In Send", Toast.LENGTH_LONG).show();
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
			
//			Toast.makeText(context, "Error In Post Execute", Toast.LENGTH_LONG).show();
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
				postArticleFragment.remove_Framgement_itself();
			}
			else{
				
				
			}
			
//			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		}
		catch(JSONException e){
			
//			Toast.makeText(context, "Error In Create Finish", Toast.LENGTH_LONG).show();
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
				String getArticleList=resultObject.getString("ArticleList");
				JSONArray articleList=new JSONArray(getArticleList);
				
				ArrayList<Article> article_ArrayList=new ArrayList<Article>();
				for(int i=0;i<articleList.length();i++){
					//Get Article From Article List
					JSONObject article =articleList.getJSONObject(i);
					
					//Get Article ID
					String article_id=article.getString("id");
					//Get Author ID
					String author_id=article.getString("author_id");
					//Get Content
					String content=article.getString("content");
					//Get Create Time
					String created_Time=article.getString("created_at");
					//Get Author Level
					String level = article.getString("level");
					//Get Nickname
					//String nickname=article.getString("nickname");
					
					Article articleInstance=new Article();
					
					articleInstance.article_id=article_id;
					articleInstance.author=author_id;
					articleInstance.content=content;
					articleInstance.created_Time=created_Time;
					articleInstance.level=level;
					//articleInstance.nickname=nickname;
					
					article_ArrayList.add(articleInstance);
				}
				
				Log.v("ArticleModule", getArticleList);
				talkingFragment.SetArticleList(article_ArrayList);
			}
			else{
				
				
			}
//			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		}
		catch(JSONException e){
			
//			Toast.makeText(context, "Error In Get List Finish", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		
	}
	
	//ť Listening
	private void GetNewArticleFinish(JSONObject resultObject){
		try {
			Log.v("ArticleModule","Get New Article Finish Start");
			//Get Response
			String response=resultObject.getString("Response");
			
			//Get Server Message
			String message=resultObject.getString("Message");
			
			//If Success
			if(response.equals("0")){
				String articlelistString=resultObject.getString("Article");
				JSONArray articleListJSONArray=new JSONArray(articlelistString);
				
				ArrayList<Article> articleArrayList=new ArrayList<Article>();
				for(int i=0;i<articleListJSONArray.length();i++){
					
					String s_article=articleListJSONArray.getString(i);
					Log.v("ArticleModule", s_article);
					
					//Get Article From Article List
					JSONObject article =new JSONObject(s_article);
					
					//Get Author ID
					String author_id=article.getString("author_id");
					//Get Content
					String content=article.getString("content");
					//Get Create Time
					String created_Time=article.getString("created_at");
					//Get Level
					String level=article.getString("level");
					//Get Nickname
					//String nickname=article.getString("nickname");
					
					Article articleInstance=new Article();
					
					articleInstance.author=author_id;
					articleInstance.content=content;
					articleInstance.created_Time=created_Time;
					articleInstance.level=level;
					//articleInstance.nickname=nickname;
					articleArrayList.add(articleInstance);
				}
				Log.v("ArticleModule","Send Set New Article");
				listeningFragment.SetNewArticleListView(articleArrayList);
			}
			else{
				
				
			}
			
//			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		}
		catch(JSONException e){
			
			e.printStackTrace();
			
		}
		
	}

}
