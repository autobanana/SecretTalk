package com.example.secrettalk5;

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
	public View view;//����
    public static PostActicle_Fragment newInstance( int num) {
    	PostActicle_Fragment fragment = new PostActicle_Fragment();
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
    }
    
    /**��Fragment�[���G���ɽե�**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        view = inflater.inflate(R.layout.fragment_postacticle_my,  container,false );
        Button cancel = (Button)view.findViewById(R.id.button2);
        Button post = (Button)view.findViewById(R.id.button1);
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
    	
    	//�o��{���X
		Toast.makeText(view.getContext(), "�p�Q�o�����A�o��", Toast.LENGTH_LONG).show();	
    	//
    	
    	remove_Framgement_itself();
    }
    
    
    
    public void remove_Framgement_itself(){
    	FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
        // transaction1.addToBackStack(null);   //�O�d���e��Fragment
        transaction1.remove(this).commit();
        //���^��L 
        ((InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
        
        
    }
}
