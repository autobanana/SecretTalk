package com.example.secrettalk5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Achievement_Fragment extends Fragment {
	int mNum; //頁號
    public static Achievement_Fragment newInstance( int num) {
    	Achievement_Fragment fragment = new Achievement_Fragment();
        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        args.putInt( "num" , num);s
//        fragment.setArguments(args);
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //這裡我只是簡單的用num區別標籤，其實具體應用中可以使用真實的fragment對象來作為葉片
       // mNum = getArguments() != null ? getArguments().getInt( "num" ) : 1 ;
    }
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        View view = inflater.inflate(R.layout.fragment_achievement,  container,false );
        //TextView tv = (TextView) view.findViewById(R.id.textView1);
        //tv.setText( "fragment+" + mNum);
        return view;
    }
}
