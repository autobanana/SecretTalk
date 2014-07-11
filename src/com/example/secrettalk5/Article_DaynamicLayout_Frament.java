package com.example.secrettalk5;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;

import com.example.articlemodule.ArticleModule;
import com.example.articlemodule.Reply;
import com.example.articlemodule.ReplyModule;
import com.example.usermodule.UserInformation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Article_DaynamicLayout_Frament extends Fragment {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public View view;
	public LinearLayout a;
	public ScrollView dynmaic_scrollview;
	public Button reply_button;
	public EditText reply_edittext;
	public String article_id;
	public ArrayList<Reply> reply_ArrayList=new ArrayList<Reply>();
	private Handler handler = new Handler();
	public DisplayMetrics dm;
	public int screenW;
	public static Context test;
	public static Article_DaynamicLayout_Frament newInstance( int num) {
		Article_DaynamicLayout_Frament fragment = new Article_DaynamicLayout_Frament();
        return fragment;
    }
    
   
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_article_dynamiclayout,  container,false );
    	
    	Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa", "onCreateView");
    	
    	dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenW = dm.widthPixels;
		
		dynmaic_scrollview = (ScrollView)view.findViewById(R.id.scrollView1);
    	a = (LinearLayout)view.findViewById(R.id.insideScrollView);
    	reply_edittext = (EditText)view.findViewById(R.id.PostArticle_ContentEditText);
    	reply_button = (Button)view.findViewById(R.id.button3);
    	
    	
    	reply_button.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		reply_specific_article();
        	}     	        	
        });
    	
    	
    	SetCheckNewReplyRunnable();    	
    	//TestButton(); 現在不需要  
    	GetHistoricalDialogue();
        return view;
    }
    public static void onBackPressed()
    {
        //Pop Fragments off backstack and do your other checks
    }
   


	private void TestButton() {
    	//測試用button /////////////////////////////////////////////
    	Button S = (Button)view.findViewById(R.id.PostArticle_PostButton1);
    	
    	S.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		DynamicLayout("維奇","Vicky","2013.23.33 32:23:2","今天天是綠的，好奇怪好奇怪！今天天是綠的，好奇怪好奇怪！今天天是綠的好奇怪好奇怪！今天天是綠的，好奇怪好奇怪！今天天是綠的，好奇怪好奇怪！",1,"1");
        	}     	        	
        });
    	Button S2 = (Button)view.findViewById(R.id.PostArticle_PostButton2);
    	
    	S2.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		DynamicLayout("馬克","Mark","2013.23.33 32:23:2","ddsdlkjs;dk;klf;fljk;alkfja;kfja;lsjkfl;ajkfkajfl;anvnzmcnzowieupoqwueqiuyeufhslkjlksnc;zlckxnlk",0,"4");
        	}     	        	
        });
    	////////////////////////////////////////////////////////
		
	}


	public void GetHistoricalDialogue(){
    	//Initial HashMap 
    	HashMap<String,String> hm=new HashMap<String, String>();
    	hm.put("username", UserInformation.Username);
    	hm.put("article_id", article_id);
    		
    	//Convert HashMap to JSONObject
    	JSONObject jo=new JSONObject(hm);
    			
    	//Send Article Post Request
    	ReplyModule rm=new ReplyModule();
    	rm.context=getActivity();
    	rm.articleDynamicFragment=this;
    	rm.execute("Content",jo.toString());
    	
    }
    
    public void GetHistoricalDialogueFinish(ArrayList<Reply> reply_ArrayList){
    	this.reply_ArrayList=reply_ArrayList;
    	SortReplyList();
    	
    	for(int i=0;i<reply_ArrayList.size();i++){
    		Reply reply=reply_ArrayList.get(i);
    		
    		String author_id=reply.author;
    		String content=reply.content;
    		String created_Time=sdf.format(reply.created_Time);
    		String level=reply.level;
    		String nickname=reply.nickname;

    		
    		int isAuthor=0;
    		Log.v("level", level);
    		Log.v("Test", author_id);
    		Log.v("Test", UserInformation.Username);
    		if(author_id.equals(UserInformation.Username)){
    			isAuthor=0; 
    		}
    		else{
    			isAuthor=1;
    		}
    		
    		DynamicLayout(nickname,author_id,created_Time,content,isAuthor,level);	
    		
    	}
    	
    	SetCheckNewReplyRunnable();
    	
    }
    
	public void reply_specific_article() {
		//獲得內容
		String replycontent = reply_edittext.getText().toString();
		
		CreateNewReply(replycontent);
	
		//收回鍵盤+清空Edittext
		reply_edittext.setText("");
		//((InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
		
		
	}
	
	public void CreateNewReply(String content){
		//Initial HashMap 
    	HashMap<String,String> hm=new HashMap<String, String>();
    	hm.put("username", UserInformation.Username);
    	hm.put("article_id", article_id);
    	hm.put("content", content);
    		
    	//Convert HashMap to JSONObject
    	JSONObject jo=new JSONObject(hm);
    			
    	//Send Article Post Request
    	ReplyModule rm=new ReplyModule();
    	rm.context=getActivity();
    	rm.articleDynamicFragment=this;
    	rm.execute("Create",jo.toString());
		
	}
	
	public void CreateNewReplyFinish(Reply reply){
		
		reply_ArrayList.add(reply);
		
		String author_id=reply.author;
		String created_Time=reply.created_Time.toString();
		String content=reply.content;
		String level=reply.level;
		String nickname=reply.nickname;
		Log.v("ReplyModule",author_id+" "+created_Time+""+""+content);
		DynamicLayout(nickname,author_id,created_Time,content,0,level);
	}
	
	private void SetCheckNewReplyRunnable(){
		Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				if(getActivity()!=null){
					CheckNewReply();
				}
				
			}
		};
		handler.postDelayed(runnable,500);
	}
	
	public void CheckNewReply(){
		if(reply_ArrayList==null||reply_ArrayList.size()==0){
			GetHistoricalDialogue();
		}
		else{
			Date latestTime=GetLatestTime();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String latestTimeString=df.format(latestTime);
			
			//Initial HashMap 
	    	HashMap<String,String> hm=new HashMap<String, String>();
	    	hm.put("username", UserInformation.Username);
	    	hm.put("article_id", article_id);
	    	hm.put("timeStamps", latestTimeString);
	    		
	    	//Convert HashMap to JSONObject
	    	JSONObject jo=new JSONObject(hm);
	    			
	    	ReplyModule rm=new ReplyModule();
	    	rm.context=getActivity();
	    	rm.articleDynamicFragment=this;
	    	rm.execute("CheckNewReply",jo.toString());
		}
		
	}
	
	public void CheckNewReplyFinish(ArrayList<Reply> reply_ArrayList){
		
		this.reply_ArrayList.addAll(reply_ArrayList);
		for(int i=0;i<reply_ArrayList.size();i++){
    		Reply reply=reply_ArrayList.get(i);
    		
    		String author_id=reply.author;
    		String content=reply.content;
    		String created_Time=sdf.format(reply.created_Time);
    		String level=reply.level;
    		String nickname=reply.nickname;

    		int isAuthor=0;
    		
    		if(author_id.equals(UserInformation.Username)){
    			isAuthor=0; 
    			level = UserInformation.Level;
    		}
    		else{
    			isAuthor=1;
    			level=reply.level;
    		}
    		DynamicLayout(nickname,author_id,created_Time,content,isAuthor,level);	
    		
    	}
		Log.v("ReplyModule", "CheckNewReplyFinish");
		SetCheckNewReplyRunnable();
	}
	
	public void DynamicLayout(String nickname,String name,String time,String content ,int whotalk, String level ) {
		
		if(getActivity()!=null){
			ListView t = new ListView(getActivity());
			t.setAdapter(new ArticleDynamicAdapter(getActivity(),nickname,name,time,content,whotalk,level,screenW));
			
			LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(     
					LinearLayout.LayoutParams.MATCH_PARENT,     
					LinearLayout.LayoutParams.MATCH_PARENT     
	        );
	        a.addView(t,p);   
	        
	        //給予延遲時間ScrollView才會來的及往下推
	        final Handler handler = new Handler();
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                try {Thread.sleep(100);} catch (InterruptedException e) {}
	                handler.post(new Runnable() {
	                    @Override
	                    public void run() {
	                    	dynmaic_scrollview.fullScroll(View.FOCUS_DOWN);
	                    }
	                });
	            }
	        }).start();
		}
	}
	public void SortReplyList(){
		Collections.sort(reply_ArrayList, new Comparator<Reply>(){
			@Override
			public int compare(Reply lhs, Reply rhs) {
				if(lhs.created_Time==null||rhs.created_Time==null)
					return 0;
				else
					return lhs.created_Time.compareTo(rhs.created_Time);
			}
		});
		
	}
	
	public Date GetLatestTime(){
		Date latestTime=reply_ArrayList.get(0).created_Time;
		for(int i=1;i<reply_ArrayList.size();i++){
			Date date=reply_ArrayList.get(i).created_Time;
			if(latestTime.compareTo(date)<0)
				latestTime=date;
		}
		return latestTime;
		
	}
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        if (null != savedInstanceState) {
	            // Restore state here
	        }
	        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa", "onCreate");
	        
	        
	    }
	    public void onAttach(Activity activity) { 
	        super.onAttach(activity);
	        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa", "onAttach");
	    }
	    
	    public void onActivityCreated(Bundle saved) { 
	        super.onActivityCreated(saved);
	    	Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa", "onActivityCreated");
	    }
	    public void onStart() { 
	        super.onStart();
	        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa", "onStart");
	    }
	    public void onResume() { 
	        super.onResume();
	        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa", "onResume");
	    }
	    
	    public void onPause() { 
	        super.onPause();
	        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa","onPause");
	    }
	    public void onStop() { 
	        super.onStop();
	        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa", "onStop");
	    }
	    public void onSaveInstanceState(Bundle toSave) { 
	        super.onSaveInstanceState(toSave);
	    Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAa", "onSaveinstanceState");
	    }
	
}
