package com.example.secrettalk5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Mood_Choose_Fragment extends Fragment {

	public View view;
	public Setting_MyFragment mood_change;
	public LinearLayout personal_setting;
	public ImageView a;
	public static Mood_Choose_Fragment newInstance( int num) {
		Mood_Choose_Fragment fragment = new Mood_Choose_Fragment();
		
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        view = inflater.inflate(R.layout.fragment_choose_mood,  container,false );
        adjustment_layout();
        a = (ImageView)view.findViewById(R.id.imageView1);
        a.setOnClickListener( new OnClickListener(){      	
         	public void onClick(View view) {
         		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
         		mood_change.changeImage_mood(1);
         	}     	        	
         });
        
        return view;
    }
    public void adjustment_layout() {
		personal_setting = (LinearLayout)view.findViewById(R.id.mood_all);
        DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
        personal_setting.setLayoutParams(new LinearLayout.LayoutParams(screenW,screenW*4/3));
	}

}
