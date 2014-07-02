package com.example.secrettalk5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Setting_MyFragment extends Fragment {

	public View view;
	public LinearLayout personal_setting,personal_setting2,personal_setting3;
	public Mood_Choose_Fragment moodchoose;
	
	
	public RadioGroup radioGroup1,radioGroup2;
	public int selectedRadio1,selectedRadio2;
	public Button mood_button,interest_button,personality_button,checkbutton;
	private String[] sign_array;
	int mNum; //頁號
    public static Setting_MyFragment newInstance( int num) {
    	
    	Setting_MyFragment fragment = new Setting_MyFragment();
        
    	return fragment;
    
    }
    @Override
    
    
    public void onCreate(Bundle savedInstanceState) {
    
    	super .onCreate(savedInstanceState);
    
    }
    /**為Fragment加載佈局時調用**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                    
    	
        view = inflater.inflate(R.layout.fragment_setting_my2,  container,false );
        
        //調整layout
        adjustment_layout();
        initial_button();
       
        GlobalVariable globalVariable = (GlobalVariable)getActivity().getApplicationContext();
        Toast.makeText(getActivity(), Integer.toString(globalVariable.mood_choose), Toast.LENGTH_LONG).show();	
        
        return view;
        
    }
    private void initial_button() {
    	 checkbutton = (Button) view.findViewById(R.id.button1);
         mood_button = (Button)view.findViewById(R.id.Button1);
         interest_button = (Button)view.findViewById(R.id.Button2);
         personality_button = (Button)view.findViewById(R.id.Button3);
         
         
         mood_button.setBackgroundResource(R.drawable.setting_mood);
         mood_button.setOnTouchListener(new ImageButton.OnTouchListener(){ 
         	public boolean onTouch(View arg0, MotionEvent arg1) {
         		
         		switch (arg1.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	arg0.setBackgroundResource(R.drawable.setting_mood_press);
                    break;
                case MotionEvent.ACTION_MOVE:
                	arg0.setBackgroundResource(R.drawable.setting_mood); 
                    break;
                case MotionEvent.ACTION_UP:
                	arg0.setBackgroundResource(R.drawable.setting_mood); 
                	break;
                default:
                    break;
                }
                return false;
         	}	
         });
         
         mood_button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 moodchoose =  new  Mood_Choose_Fragment();
           		 moodchoose.mood_change = Setting_MyFragment.this;
                 FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                 transaction1.addToBackStack(null);   //保留先前的Fragment
                 transaction1.add(R.id.drawer_layout_second, moodchoose).commit();
                 getActivity().getActionBar().setTitle(R.string.Setting_moodchoose_title); 
             	             	
             }
         });
         
         
         /*
         interest_button.setOnTouchListener(new ImageButton.OnTouchListener(){ 
	        	public boolean onTouch(View arg0, MotionEvent arg1) {
	        		// TODO Auto-generated method stub 
	        		if(arg1.getAction() == MotionEvent.ACTION_DOWN){
	        			arg0.setBackgroundResource(R.drawable.setting_interest_press);
	        		}
	        		else if(arg1.getAction() == MotionEvent.ACTION_UP){
	        			arg0.setBackgroundResource(R.drawable.setting_interest); 
	        		}
	        		return false; 
	        	}	
	        });
         personality_button.setOnTouchListener(new ImageButton.OnTouchListener(){ 
	        	public boolean onTouch(View arg0, MotionEvent arg1) {
	        		// TODO Auto-generated method stub 
	        		if(arg1.getAction() == MotionEvent.ACTION_DOWN){
	        			arg0.setBackgroundResource(R.drawable.setting_personality_press);
	        		}
	        		else if(arg1.getAction() == MotionEvent.ACTION_UP){
	        			arg0.setBackgroundResource(R.drawable.setting_personality); 
	        		}
	        		return false; 
	        	}	
	        });
	        */
         /*
         mood_button.setOnClickListener( new OnClickListener(){      	
          	public void onClick(View view) {
          		
                
          	}     	        	
          });
         interest_button.setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
           		 
                 
           	}     	        	
           });
         personality_button.setOnClickListener( new OnClickListener(){      	
            	public void onClick(View view) {
            		 
                  
            	}     	        	
            });
         
         */
         checkbutton.setOnClickListener( new OnClickListener(){      	
         	public void onClick(View view) {
         		SaveData_Server();
         	}     	        	
         });
	}
    
	public void SaveData_Server() {
		Toast.makeText(view.getContext(), "小魏這裡給你存資料！！", Toast.LENGTH_LONG).show();
		
		
	}
	public void adjustment_layout() {
		personal_setting = (LinearLayout)view.findViewById(R.id.personal_set);
        personal_setting2 = (LinearLayout)view.findViewById(R.id.personal_set_up);
        personal_setting3=(LinearLayout)view.findViewById(R.id.personal_setting_now);
        DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		personal_setting3.setLayoutParams(new LinearLayout.LayoutParams(screenW,screenW/3));
		screenW = screenW*19/20;
        personal_setting.setLayoutParams(new LinearLayout.LayoutParams(screenW,screenW/4));
        personal_setting2.setLayoutParams(new LinearLayout.LayoutParams(screenW,screenW/4));
	
	}
	public void changeImage_mood(int n){
			int imagechage_id[] = {};
		if(n==1){
	        mood_button.setBackgroundResource(R.drawable.setting_occupation_press);
		}
	}
}

