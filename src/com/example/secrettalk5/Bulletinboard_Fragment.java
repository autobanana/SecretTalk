package com.example.secrettalk5;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Bulletinboard_Fragment extends Fragment {
	public static Bulletinboard_Fragment newInstance( int num) {
		Bulletinboard_Fragment fragment = new Bulletinboard_Fragment();
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        View view = inflater.inflate(R.layout.fragment_bulletinboard,  container,false );
        return view;
    }
}
