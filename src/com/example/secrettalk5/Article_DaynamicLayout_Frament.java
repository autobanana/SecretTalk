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

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

	public View view;
	public LinearLayout a;
	public ScrollView dynmaic_scrollview;
	public Button reply_button;
	public EditText reply_edittext;
	public String article_id;
	public ArrayList<Reply> reply_ArrayList=new ArrayList<Reply>();
	private Handler handler = new Handler();
	
	public static Article_DaynamicLayout_Frament newInstance( int num) {
		Article_DaynamicLayout_Frament fragment = new Article_DaynamicLayout_Frament();
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    /**��Fragment�[���G���ɽե�**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_article_dynamiclayout,  container,false );
    	dynmaic_scrollview = (ScrollView)view.findViewById(R.id.scrollView1);
    	a = (LinearLayout)view.findViewById(R.id.insideScrollView);
    	reply_edittext = (EditText)view.findViewById(R.id.PostArticle_ContentEditText);
    	reply_button = (Button)view.findViewById(R.id.button3);
    	
    	reply_button.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		reply_specific_article();
        	}     	        	
        });
    	    	
    	//���ե�button /////////////////////////////////////////////
    	Button S = (Button)view.findViewById(R.id.PostArticle_PostButton);
    	
    	S.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		DynamicLayout("Vicky","2013.23.33 32:23:2","���ѤѬO�񪺡A�n�_�Ǧn�_�ǡI���ѤѬO�񪺡A�n�_�Ǧn�_�ǡI���ѤѬO�񪺦n�_�Ǧn�_�ǡI���ѤѬO�񪺡A�n�_�Ǧn�_�ǡI���ѤѬO�񪺡A�n�_�Ǧn�_�ǡI",1);
        	}     	        	
        });
    	Button S2 = (Button)view.findViewById(R.id.button2);
    	
    	S2.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		DynamicLayout("Mark","2013.23.33 32:23:2","ddsdlkjs;dk;klf;fljk;alkfja;kfja;lsjkfl;ajkfkajfl;anvnzmcnzowieupoqwueqiuyeufhslkjlksnc;zlckxnlk",0);
        	}     	        	
        });
    	////////////////////////////////////////////////////////
    	GetHistoricalDialogue();
        return view;
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
    		String created_Time=reply.created_Time.toString();
    		
    		int isAuthor=0;
    		Log.v("Test", author_id);
    		Log.v("Test", UserInformation.Username);
    		if(author_id.equals(UserInformation.Username)){
    			isAuthor=0; 
    		}
    		else{
    			isAuthor=1;
    		}
    		DynamicLayout(author_id,created_Time,content,isAuthor);	
    		
    	}
    	
    	SetCheckNewReplyRunnable();
    	
    }
    
	public void reply_specific_article() {
		//��o���e
		String replycontent = reply_edittext.getText().toString();
		
		CreateNewReply(replycontent);
	
		//���^��L+�M��Edittext
		reply_edittext.setText("");
		((InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
		
		
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
		
		DynamicLayout(author_id,created_Time,content,0);
	}
	
	private void SetCheckNewReplyRunnable(){
		Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				CheckNewReply();
				
			}
		};
		
		handler.postDelayed(runnable,10000);
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
    		String created_Time=reply.created_Time.toString();
    		
    		int isAuthor=0;
    		
    		if(author_id.equals(UserInformation.Username)){
    			isAuthor=0; 
    		}
    		else{
    			isAuthor=1;
    		}
    		DynamicLayout(author_id,created_Time,content,isAuthor);	
    		
    	}
		Log.v("ReplyModule", "CheckNewReplyFinish");
		SetCheckNewReplyRunnable();
	}
	
	public void DynamicLayout(String name,String time,String content ,int whotalk ) {
		
		
		ListView t = new ListView(getActivity());     
		t.setAdapter(new ArticleDynamicAdapter(getActivity(),name,time,content,whotalk));
		
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(     
				LinearLayout.LayoutParams.MATCH_PARENT,     
				LinearLayout.LayoutParams.MATCH_PARENT     
        );
        a.addView(t,p);   
        
        //��������ɶ�ScrollView�~�|�Ӫ��Ω��U��
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
}