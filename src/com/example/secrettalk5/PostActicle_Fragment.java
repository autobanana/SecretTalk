package com.example.secrettalk5;

import java.util.HashMap;

import org.json.JSONObject;

import com.example.articlemodule.ArticleModule;
import com.example.usermodule.UserInformation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostActicle_Fragment extends Fragment {

	int mNum; 
	public View view;//頁號
	
    public static PostActicle_Fragment newInstance( int num) {
    	PostActicle_Fragment fragment = new PostActicle_Fragment();
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
    }
    
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        view = inflater.inflate(R.layout.fragment_postacticle_my,  container,false );
        Button cancel = (Button)view.findViewById(R.id.PostArticle_PostButton_Cancel);
        Button post = (Button)view.findViewById(R.id.PostArticle_PostButton_Check);
        cancel.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		remove_Framgement_itself();
        	}     	        	
        });
        post.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		postarcticle();
        	}     	        	
        });
        return view;
    }
    public void postarcticle(){
    	
    	//發文程式碼

		//Toast.makeText(view.getContext(), "小魏這塊給你發文", Toast.LENGTH_LONG).show();
    	
    	EditText et=(EditText)view.findViewById(R.id.PostArticle_ContentEditText);
    	String content=et.getText().toString();
    	
    	HashMap hm=new HashMap<String, String>();
		hm.put("username", UserInformation.Username);
		hm.put("content", content);
		//Convert HashMap to JSONObject
		JSONObject jo=new JSONObject(hm);
		
		//Send Article Post Request
		ArticleModule am=new ArticleModule();
		am.context=getActivity();
		am.postArticleFragment=this;
		am.execute("Create",jo.toString());

    	
    	remove_Framgement_itself();
    }
    
    
    
    public void remove_Framgement_itself(){
    	FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
        // transaction1.addToBackStack(null);   //保留先前的Fragment
        transaction1.remove(this).commit();
        //收回鍵盤 
        ((InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
        
    }
}
