package com.example.secrettalk5;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.example.articlemodule.Article;
import com.example.articlemodule.ArticleModule;
import com.example.globalcontainer.GlobalContainer;
import com.example.usermodule.UserInformation;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class Listening_MyFragment extends Fragment {

	int mNum; //頁號
	public Button test_for_notification;
	public TextView t1;
	private ArrayList<Article> articleArrayList;
	private ListView listening_listView;
	public int count;
	public FrameLayout layoutA;
	public TextView servertalk;
	public View view;
	public Article_DaynamicLayout_Frament article_daynamicLayout;
    public static Listening_MyFragment newInstance( int num) {
    	Listening_MyFragment fragment = new Listening_MyFragment();
        
    	return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        
    }
    /**為Fragment加載佈局時調用**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	count =0;
    	
    	view = inflater.inflate(R.layout.fragment_listening_my, container,false);
    	t1 = (TextView) view.findViewById(R.id.content_talking);
    	listening_listView=(ListView)view.findViewById(R.id.listening_ListView1);
    	Initial_ListView();
    	GetNewArticle();
    	Initial_scrollanddisspear();
        return view;
    }
    
    public void Initial_scrollanddisspear() {
		layoutA=(FrameLayout)view.findViewById(R.id.aa);
		layoutA.getBackground().setAlpha(200);
		servertalk = (TextView)view.findViewById(R.id.server_talk);
		servertalk.setText(R.string.server_talk2);
		listening_listView.setOnScrollListener(new OnScrollListener() {
			public void onScrollStateChanged(AbsListView view, int scrollState){
    				switch (scrollState) {
    				case SCROLL_STATE_IDLE:
    					//layoutA.startAnimation(invisible_animation); 
    					layoutA.setVisibility(view.VISIBLE);
    					
    					break;
					default:
						layoutA.setVisibility(view.GONE);
						//layoutA.getBackground().setAlpha(0);
						break;
				}
				
			}
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				//layoutA.setVisibility(view.INVISIBLE);
			}
	});
	}

    
    
    public void Initial_ListView() {
		    	
    	
    	listening_listView.setOnItemClickListener( new  AdapterView.OnItemClickListener() {  
            @Override  
            public  void  onItemClick(AdapterView<?> adapterView, View view,  int  position,  long  l) {  
                
            	
            	Log.v("ReplyModule", String.valueOf(position));
            	Log.v("ReplyModule", GlobalContainer.article_ArrayList_Listening.toString());
            	Article artilce = GlobalContainer.article_ArrayList_Listening.get(position);
            	Log.v("ReplyModule", artilce.toString());
            	String article_id=artilce.article_id;
            	Log.v("ReplyModule", article_id);
            	article_daynamicLayout =  new  Article_DaynamicLayout_Frament();
            	article_daynamicLayout.article_id=article_id;
                FragmentTransaction transaction4 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction4.addToBackStack(null);   //保留先前的Fragment
                transaction4.add(R.id.drawer_layout_second, article_daynamicLayout).commit();
                
                View backView = getActivity().findViewById(R.id.L_main);
                backView.setVisibility(View.INVISIBLE);

                
            }

        });  
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
    
    public void GetNewArticle(){
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
    	GlobalContainer.article_ArrayList_Listening=articleArrayList;
    	//String count =String.valueOf(articleArrayList.size());
    	Log.v("ListeningFragment", "StarRefreshListView");
    	Log.v("ListeningFragment", "StarRefreshListView");
    	RefreshListView();
    	
    }
    
    private void RefreshListView(){
    	listening_listView.setAdapter(new ImageAdapter(getActivity(),GlobalContainer.article_ArrayList_Listening));
    	listening_listView.invalidateViews();
    }
    
    
    
    
    
}

