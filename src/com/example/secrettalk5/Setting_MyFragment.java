package com.example.secrettalk5;

import java.util.HashMap;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_MyFragment extends Fragment {

	public View view;
	public int choose_interest=0,choose_personality=0,choose_mood=0;
	public LinearLayout personal_setting,personal_setting2,personal_setting3;
	public Mood_Choose_Fragment moodchoose;
	public Interest_Choose_Fragment interestchoose;
	public Personality_Choose_Fragment personalitychoose;
	
	private TextView birthday_year,birthday_month,birthday_day;
	private TextView user_loginday,user_nickname,user_id;
	private ImageView user_bloodtype,user_constellation,user_gender,user_level;
	
	
	public View backView;
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
        initial_personal_from_sever_data();
        adjustment_layout();
        initial_button();
        return view;
        
    }
    private void initial_personal_from_sever_data(){
    	
    	int level_drawableiD[] = {R.drawable.level_1,R.drawable.level_2,R.drawable.level_3,R.drawable.level_4,R.drawable.level_5};
    	int bloodtype_drawableiD[] = {R.drawable.bloodtype_a,R.drawable.bloodtype_b,R.drawable.bloodtype_ab,R.drawable.bloodtype_o};
    	int constellatioin_drawable[] = {R.drawable.constellation_aries,R.drawable.constellation_taurus,R.drawable.constellation_gemini
    			,R.drawable.constellation_cancer,R.drawable.constellation_leo,R.drawable.constellation_virgo,R.drawable.constellation_libra
    			,R.drawable.constellation_scorpio,R.drawable.constellation_sagittarius,R.drawable.constellation_aquarius,R.drawable.constellation_pisces};
    	
    	Toast.makeText(view.getContext(), "由UserModule得到資料！！", Toast.LENGTH_LONG).show();
    	
    	//initial String of Setting
    	String username;
    	String nickname;
    	String level;
    	String bloodType;
    	String sign;
    	String gender;
    	
    	//Set Textview and Button for user's old setting
    	user_nickname = (TextView)view.findViewById(R.id.user_nickname);
    	user_loginday = (TextView)view.findViewById(R.id.user_loginday);
    	user_id = (TextView)view.findViewById(R.id.user_id);
    	user_level = (ImageView)view.findViewById(R.id.user_level);
    	birthday_year = (TextView)view.findViewById(R.id.birthday_year);
    	birthday_month = (TextView)view.findViewById(R.id.birthday_month);
    	birthday_day = (TextView)view.findViewById(R.id.birthday_day);
    	user_bloodtype = (ImageView)view.findViewById(R.id.user_bloodtype);
    	user_constellation = (ImageView)view.findViewById(R.id.user_constellation);
    	user_gender = (ImageView)view.findViewById(R.id.user_gender);
    	
    	
    	
    }
    private void initial_button() {
    	 
    	checkbutton = (Button) view.findViewById(R.id.button1);
    	checkbutton.setOnClickListener( new OnClickListener(){      	
          	public void onClick(View view) {
          		SaveData_Server();
          	}     	        	
          });
    	 
    	 mood_button = (Button)view.findViewById(R.id.user_mood);
         interest_button = (Button)view.findViewById(R.id.user_interest);
         personality_button = (Button)view.findViewById(R.id.user_personality);
         interest_button.setBackgroundResource(R.drawable.setting_interest);
         interest_button.setOnTouchListener(new ImageButton.OnTouchListener(){ 
         	public boolean onTouch(View arg0, MotionEvent arg1) {
         		
         		switch (arg1.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	arg0.setBackgroundResource(R.drawable.setting_interest_press);
                    break;
                case MotionEvent.ACTION_MOVE:
                	arg0.setBackgroundResource(R.drawable.setting_interest); 
                    break;
                case MotionEvent.ACTION_UP:
                	arg0.setBackgroundResource(R.drawable.setting_interest); 
                	break;
                default:
                    break;
                }
                return false;
         	}	
         });
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
         personality_button.setBackgroundResource(R.drawable.setting_personality);
         personality_button.setOnTouchListener(new ImageButton.OnTouchListener(){ 
         	public boolean onTouch(View arg0, MotionEvent arg1) {
         		
         		switch (arg1.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	arg0.setBackgroundResource(R.drawable.setting_personality_press);
                    break;
                case MotionEvent.ACTION_MOVE:
                	arg0.setBackgroundResource(R.drawable.setting_personality); 
                    break;
                case MotionEvent.ACTION_UP:
                	arg0.setBackgroundResource(R.drawable.setting_personality); 
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
         
         interest_button.setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
           		interestchoose =  new  Interest_Choose_Fragment();
           		interestchoose.interest_change = Setting_MyFragment.this;
                FragmentTransaction transaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction2.addToBackStack(null);   //保留先前的Fragment
                transaction2.add(R.id.drawer_layout_second, interestchoose).commit();
                getActivity().getActionBar().setTitle(R.string.Setting_interestchoose_title); 
                 
           	}     	        	
           }); 
        
         personality_button.setOnClickListener( new OnClickListener(){      	
            	public void onClick(View view) {
            		personalitychoose =  new  Personality_Choose_Fragment();
            		personalitychoose.personality_change = Setting_MyFragment.this;
                    FragmentTransaction transaction3 = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction3.addToBackStack(null);   //保留先前的Fragment
                    transaction3.add(R.id.drawer_layout_second, personalitychoose).commit();
                    getActivity().getActionBar().setTitle(R.string.Setting_personalitychoose_title); 
                  
            	}     	        	
            });
         
         
	}
    
	public void SaveData_Server() {
		
		//Initial HashMap 
		HashMap<String, String> hm=new HashMap<String, String>();
		
		//這三種傳遞數字 0  表示未選擇 1~12為有選擇之義
		hm.put("personality",   Integer.toString(choose_personality));
		hm.put("interest",   Integer.toString(choose_interest));
		hm.put("mood",   Integer.toString(choose_mood));
		
		
		
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
	public void Hide_BackView(){
		backView = getActivity().findViewById(R.id.L_main);
        backView.setVisibility(View.GONE);

	}
	public void Show_BackView(){
		backView = getActivity().findViewById(R.id.L_main);
		 backView.setVisibility(View.VISIBLE);
	}
	
	
	
	//個人即時設定
	public void changeImage_mood(int n){
		int imagechage_id[] = {R.drawable.mood_1,R.drawable.mood_2,R.drawable.mood_3,R.drawable.mood_4
				,R.drawable.mood_5,R.drawable.mood_6,R.drawable.mood_7,R.drawable.mood_8,R.drawable.mood_9
				,R.drawable.mood_10,R.drawable.mood_11,R.drawable.mood_12};
	
        mood_button.setBackgroundResource(imagechage_id[n]);
        choose_mood=n;
        Show_BackView();
		
	}
	public void changeImage_interest(int n){
		int imagechage_id2[] =  {R.drawable.interest_1,R.drawable.interest_2,R.drawable.interest_3,R.drawable.interest_4
				,R.drawable.interest_5,R.drawable.interest_6,R.drawable.interest_7,R.drawable.interest_8,R.drawable.interest_9
				,R.drawable.interest_10,R.drawable.interest_11,R.drawable.interest_12};
		interest_button.setBackgroundResource(imagechage_id2[n]);
		choose_interest = n;
        Show_BackView();
		
	}
	public void changeImage_personality(int n){
		int imagechage_id3[] = {R.drawable.personality_1,R.drawable.personality_2,R.drawable.personality_3,R.drawable.personality_4
				,R.drawable.personality_5,R.drawable.personality_6,R.drawable.personality_7,R.drawable.personality_8,R.drawable.personality_9
				,R.drawable.personality_10,R.drawable.personality_11,R.drawable.personality_12};
	
		personality_button.setBackgroundResource(imagechage_id3[n]);
		choose_personality = n;
        Show_BackView();
		
	}
	
}

