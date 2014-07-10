package com.example.secrettalk5;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.example.articlemodule.Article;
import com.example.articlemodule.ArticleModule;
import com.example.usermodule.UserInformation;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Listening_MyFragment extends Fragment {

	int mNum; //頁號
	public Button test_for_notification;
	public TextView t1;
	private ArrayList<Article> articleArrayList;
	private ListView listening_listView;
	public int count;
    public static Listening_MyFragment newInstance( int num) {
    	Listening_MyFragment fragment = new Listening_MyFragment();
        
    	return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        
    }
    //建立新的 通知 
    public void newMessage(String detail_of_message,int count){
    	
    	
    	NotificationCompat.Builder mBuilder =
    	        new NotificationCompat.Builder(getActivity())
    	        .setSmallIcon(R.drawable.ic_launcher)
    	        .setContentTitle("New Message!")
    	        .setContentText(detail_of_message);
    	mBuilder.setAutoCancel(true);  //點擊後自動不見
    	
    	
    	
    	Intent resultIntent = new Intent(getActivity(), MainActivity.class);
    	resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	
    	Bundle bundle = new Bundle();      //notificatioin會告訴 Mainactivity去哪一個viewpager
        bundle.putInt("viewpager_num",1);
        resultIntent.putExtras(bundle);
        
    	//resultIntent.putExtra("viewpager_num",);      //傳直告訴程式是哪一個viewpager 這裡假設為1 : listening
    	
    	
    	
    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
    	stackBuilder.addParentStack(MainActivity.class);
    	stackBuilder.addNextIntent(resultIntent);
    	
    	PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
    	mBuilder.setContentIntent(resultPendingIntent);
    	NotificationManager mNotificationManager =(NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
    	
    	// mId allows you to update the notification later on.
    	mNotificationManager.notify(count, mBuilder.build());
    
    }
    
    private void GetNewArticle(){
    	//Initial HashMap 
    	HashMap hm=new HashMap<String, String>();
    	hm.put("username", UserInformation.Username);
    			
    	//Convert HashMap to JSONObject
    	JSONObject jo=new JSONObject(hm);
    			
    	//Send Article Post Request
    	ArticleModule am=new ArticleModule();
    	am.context=getActivity();
    	am.listeningFragment=this;
    	am.execute("NewArticle",jo.toString());
    	
    }
    
    public void SetNewArticleListView(ArrayList<Article> articleArrayList){
    	this.articleArrayList=articleArrayList;
    	String count=String.valueOf(articleArrayList.size());
    	Log.v("ListeningFragment", "StarRefreshListView");
    	Log.v("ListeningFragment", "StarRefreshListView");
    	RefreshListView();
    	
    }
    
    private void RefreshListView(){
    	listening_listView.setAdapter(new ImageAdapter(getActivity(),articleArrayList));
    	listening_listView.invalidateViews();
    }
    
    /**為Fragment加載佈局時調用**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	count =0;
    	
    	View view = inflater.inflate(R.layout.fragment_listening_my, container,false);
    	t1 = (TextView) view.findViewById(R.id.content_talking);
    	listening_listView=(ListView)view.findViewById(R.id.listening_ListView1);
    	GetNewArticle();
    	
        return view;
    }
    
    
    
    
}

