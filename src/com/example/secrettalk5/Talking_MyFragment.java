package com.example.secrettalk5;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.example.articlemodule.Article;
import com.example.articlemodule.ArticleModule;
import com.example.usermodule.UserInformation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Talking_MyFragment extends Fragment {

	int mNum; //頁號
	public Context a;
	private FrameLayout mainLayout; 
	public ListView talking_listView;
	private Context ct;
	private ArrayList<Article> article_ArrayList=new ArrayList<Article>();
	
    public static Talking_MyFragment newInstance( int num) {
    	Talking_MyFragment fragment = new Talking_MyFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt( "num" , num);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        //這裡我只是簡單的用num區別標籤，其實具體應用中可以使用真實的fragment對象來作為葉片
        //mNum = getArguments() != null ? getArguments().getInt( "num" ) : 0 ;
        GetArticle();
    }
    /**為Fragment加載佈局時調用**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
         
        View view = inflater.inflate(R.layout.fragment_talking_my, container,false);
        
        talking_listView = (ListView) view.findViewById(R.id.Talking_ListView1);

        return view;
    }
    
    
    private void GetArticle(){
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
    	this.article_ArrayList=article_ArrayList;
    	RefreshListView();
    	
    }
    
    private void RefreshListView(){
    	
    	talking_listView.setAdapter(new ImageAdapter(getActivity(),article_ArrayList));
    	talking_listView.invalidateViews();
    	
    }
    

}

