package com.example.secrettalk5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Navigation_Fragment extends Fragment {
	int mNum; //����
    public static Navigation_Fragment newInstance( int num) {
    	Navigation_Fragment fragment = new Navigation_Fragment();
        // Supply num input as an argument.
    	//Bundle args = new Bundle();
    	//args.putInt( "num" , num);
    	//fragment.setArguments(args);
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
    }
    
    /**��Fragment�[���G���ɽե�**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        View view = inflater.inflate(R.layout.fragment_navigation,  container,false );
        //TextView tv = (TextView) view.findViewById(R.id.textView1);
        //tv.setText( "fragment+" + mNum);
        return view;
    }
}
