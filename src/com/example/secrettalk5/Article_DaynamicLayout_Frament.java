package com.example.secrettalk5;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
	
	public static Article_DaynamicLayout_Frament newInstance( int num) {
		Article_DaynamicLayout_Frament fragment = new Article_DaynamicLayout_Frament();
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**為Fragment加載佈局時調用**/
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
    	
    	
    	
    	
    	//測試用button /////////////////////////////////////////////
    	Button S = (Button)view.findViewById(R.id.PostArticle_PostButton);
    	
    	S.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		DynamicLayout("Vicky","2013.23.33 32:23:2","今天天是綠的，好奇怪好奇怪！今天天是綠的，好奇怪好奇怪！今天天是綠的好奇怪好奇怪！今天天是綠的，好奇怪好奇怪！今天天是綠的，好奇怪好奇怪！",1);
        	}     	        	
        });
    	Button S2 = (Button)view.findViewById(R.id.button2);
    	
    	S2.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		DynamicLayout("Mark","2013.23.33 32:23:2","ddsdlkjs;dk;klf;fljk;alkfja;kfja;lsjkfl;ajkfkajfl;anvnzmcnzowieupoqwueqiuyeufhslkjlksnc;zlckxnlk",0);
        	}     	        	
        });
    	////////////////////////////////////////////////////////
    	
        return view;
    }

	public void reply_specific_article() {
		
		
		//獲得內容
		String replycontent = reply_edittext.getText().toString();
		
		
		Toast.makeText(view.getContext(), "小魏這塊給你寫回覆", Toast.LENGTH_LONG).show();
		
		
		
		//收回鍵盤+清空Edittext
		reply_edittext.setText("");
		((InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
		
		
	}

	public void DynamicLayout(String name,String time,String content ,int whotalk ) {
		
		
		ListView t = new ListView(getActivity());     
		t.setAdapter(new ArticleDynamicAdapter(getActivity(),name,time,content,whotalk));
		
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
