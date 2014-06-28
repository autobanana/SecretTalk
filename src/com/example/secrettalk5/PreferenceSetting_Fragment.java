package com.example.secrettalk5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PreferenceSetting_Fragment extends Fragment {
	
	int mNum; //頁號
    public static PreferenceSetting_Fragment newInstance( int num) {
    	PreferenceSetting_Fragment fragment = new PreferenceSetting_Fragment();
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
    }
    
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        View view = inflater.inflate(R.layout.fragment_preference_setting,  container,false );
        return view;
    }
}
