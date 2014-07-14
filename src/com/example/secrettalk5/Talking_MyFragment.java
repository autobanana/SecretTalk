package com.example.secrettalk5;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.example.articlemodule.Article;
import com.example.articlemodule.ArticleModule;
import com.example.globalcontainer.GlobalContainer;
import com.example.usermodule.UserInformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Talking_MyFragment extends Fragment {

	int mNum; //頁號
	public View view ;
	public Button talking_button;
	public ListView aa;
	public Context a;
	public ListView talking_listView;
	
	public PostActicle_Fragment postacticle;
	public Article_DaynamicLayout_Frament article_daynamicLayout;
	
    public static Talking_MyFragment newInstance( int num) {
    	Talking_MyFragment fragment = new Talking_MyFragment();
        // Supply num input as an argument.
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    }

    /**為Fragment加載佈局時調用**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
         
        view = inflater.inflate(R.layout.fragment_talking_my, container,false);
        talking_button = (Button)view.findViewById(R.id.button_talking);
        talking_listView = (ListView) view.findViewById(R.id.Talking_ListView1);
        
        talking_button.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		postacticle =  new  PostActicle_Fragment();  
                FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction1.addToBackStack(null);   //保留先前的Fragment
                transaction1.add(R.id.drawer_layout_second, postacticle).commit();
                getActivity().getActionBar().setTitle(R.string.PostActivity_dialog_title);  
                
        	}    	        	
        });
        
        Initial_ListView();
        GetArticle();
        return view;
    }

	//
    public void Initial_ListView() {
		    	
    	
    	talking_listView.setOnItemClickListener( new  AdapterView.OnItemClickListener() {  
            @Override  
            public  void  onItemClick(AdapterView<?> adapterView, View view,  int  position,  long  l) {  
                
            	
            	Log.v("ReplyModule", String.valueOf(position));
            	Log.v("ReplyModule", GlobalContainer.article_ArrayList.toString());
            	Article artilce = GlobalContainer.article_ArrayList.get(position);
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
    //
    public void GetArticle(){
		//Initial HashMap 
		HashMap hm=new HashMap<String, String>();
		hm.put("username", UserInformation.Username);
		
		//Convert HashMap to JSONObject
		JSONObject jo=new JSONObject(hm);
		
		//Send Article Post Request
		ArticleModule am=new ArticleModule();
		am.context=getActivity();
		am.talkingFragment=this;
		am.execute("List",jo.toString());
		
		
	}

    public void SetArticleList(ArrayList<Article> article_ArrayList){    	
    	
    	GlobalContainer.article_ArrayList = article_ArrayList;
    	//Bulletin_AddToArrayList();
    	RefreshListView();
    	
    }
    
    private void RefreshListView() {
    	
    	talking_listView.setAdapter(new ImageAdapter(getActivity(),GlobalContainer.article_ArrayList));
    	talking_listView.invalidateViews();
    	
    }
    
    public void Bulletin_AddToArrayList() {
    	
    	ArrayList<Article> article_ArrayList_for_TalkingFragment =new ArrayList<Article>();
    	Article Bulletin = new Article();
    	Bulletin.article_id = "";
		Bulletin.author = "我是SecretTelk";
		Bulletin.content = "TestTestTestTestTestTest";
		Bulletin.created_Time= "最新更新時間";
		article_ArrayList_for_TalkingFragment.add(Bulletin);
    	article_ArrayList_for_TalkingFragment.addAll(GlobalContainer.article_ArrayList);
    	GlobalContainer.article_ArrayList = article_ArrayList_for_TalkingFragment;
    	
	}

    
}


