package com.example.secrettalk5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;

import com.example.usermodule.UserInformation;
import com.example.usermodule.UserModule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
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
	private ImageView user_bloodtype,user_constellation,user_gender,user_level,user_interest;
	
	
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
        
        adjustment_layout();
        initial_button();
        initial_personal_from_sever_data();
        return view;
        
    }
    private void initial_personal_from_sever_data(){
    	
    	
	    
    	int level_drawableiD[] = {R.drawable.level_1,R.drawable.level_2,R.drawable.level_3,R.drawable.level_4,R.drawable.level_5};
    	int constellatioin_drawable[] = {R.drawable.constellation_aries,R.drawable.constellation_taurus,R.drawable.constellation_gemini
    			,R.drawable.constellation_cancer,R.drawable.constellation_leo,R.drawable.constellation_virgo,R.drawable.constellation_libra
    			,R.drawable.constellation_scorpio,R.drawable.constellation_sagittarius,R.drawable.constellation_aquarius,R.drawable.constellation_pisces};
    	int interest_drawable[] = {R.drawable.setting_interest,R.drawable.interest_1,R.drawable.interest_2,R.drawable.interest_3,R.drawable.interest_4,R.drawable.interest_5
    			,R.drawable.interest_6,R.drawable.interest_7,R.drawable.interest_8,R.drawable.interest_9,R.drawable.interest_10
    			,R.drawable.interest_11,R.drawable.interest_12};
    	int mood_drawable[] = {R.drawable.setting_mood,R.drawable.mood_1,R.drawable.mood_2,R.drawable.mood_3,R.drawable.mood_4,R.drawable.mood_5
    			,R.drawable.mood_6,R.drawable.mood_7,R.drawable.mood_8,R.drawable.mood_9,R.drawable.mood_10
    			,R.drawable.mood_11,R.drawable.mood_12};
    	int personality_drawable[] = {R.drawable.setting_personality,R.drawable.personality_1,R.drawable.personality_2,R.drawable.personality_3
    			,R.drawable.personality_4,R.drawable.personality_5,R.drawable.personality_6,R.drawable.personality_7,R.drawable.personality_8
    			,R.drawable.personality_9,R.drawable.personality_10,R.drawable.personality_11,R.drawable.personality_12};
    	String SummitSign[] = {"Aries", "Taurus", "Gemini", "Cancer", "Leo","Virgo","Libra","Scorpio",
				"Sagittarius","Capricorn","Aquarius","Pisces"};
    	
    	
    	//initial String of Setting
    	String BloodType = UserInformation.BloodType;   //血型
    	String Birthday = UserInformation.Birthday;
    	String Gender =  UserInformation.Gender;	  //性別
    	String Interest =  UserInformation.Interest;    //興趣
    	String Sign =  UserInformation.Sign;        //星座
    	String Level =  UserInformation.Level;		  //等級
    	String LoginDays = UserInformation.LoginDays;//登入天數
    	String Mood =  UserInformation.Mood;        //心情
    	String NickName =  UserInformation.NickName;    //暱稱
    	String Personality =  UserInformation.Personality; //個性
    	String Username = UserInformation.Username;  //ID

    	
    	//Toast.makeText(view.getContext(), "由UserModule得到資料(UserInformation)！！", Toast.LENGTH_LONG).show();
    	Log.d("EEEEEEEEEEEEEEEE", BloodType.toString());
    	
    	//Constellatin 星座
    	user_constellation = (ImageView)view.findViewById(R.id.user_constellation);
    	for(int i =0 ;i<12;i++){
    		if(Sign.equals(SummitSign[i])){
    			user_constellation.setBackgroundResource(constellatioin_drawable[i]);
    		}
    	}
    	
    	
    	//Bloodtype 血型
    	user_bloodtype = (ImageView)view.findViewById(R.id.user_bloodtype);
    	if(BloodType.equals("A")) user_bloodtype.setBackgroundResource(R.drawable.bloodtype_a);
    	else if(BloodType.equals("B")) user_bloodtype.setBackgroundResource(R.drawable.bloodtype_b);
    	else if(BloodType.equals("O")) user_bloodtype.setBackgroundResource(R.drawable.bloodtype_o);
    	else if(BloodType.equals("AB")) user_bloodtype.setBackgroundResource(R.drawable.bloodtype_ab);
    	
    	//Gender 性別
    	user_gender = (ImageView)view.findViewById(R.id.user_gender);
    	if(Gender.equals("male"))user_gender.setBackgroundResource(R.drawable.male);
    	else if(Gender.equals("female"))user_gender.setBackgroundResource(R.drawable.female);
    	
    	//NickName 暱稱
    	user_nickname = (TextView)view.findViewById(R.id.user_nickname);
    	user_nickname.setText(NickName);
    	
    	//ID 
    	user_id = (TextView)view.findViewById(R.id.user_id);
    	user_id.setText(Username);
    	
    	//Level 等級
    	user_level = (ImageView)view.findViewById(R.id.user_level);
    	user_level.setBackgroundResource(level_drawableiD[Integer.valueOf(Level)]);
    	
    	//LoginDays
    	user_loginday = (TextView)view.findViewById(R.id.user_loginday);
    	user_loginday.setText(LoginDays);
    	
    	//Interest 興趣 (未填 = 0)
    	interest_button.setBackgroundResource(interest_drawable[Integer.valueOf(Interest)]);    	
    	
    	//Personality 
    	personality_button.setBackgroundResource(personality_drawable[Integer.valueOf(Personality)]);   
    	
    	//Mood
    	mood_button.setBackgroundResource(mood_drawable[Integer.valueOf(Mood)]);
    	
    	//Birthday 
    	birthday_year = (TextView)view.findViewById(R.id.birthday_year);
    	birthday_year.setText(Birthday);
    	
    	
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
		
		String P = Integer.toString(choose_personality);
		String I = Integer.toString(choose_interest);
		String M = Integer.toString(choose_mood);
		
	    
	    HashMap<String, String> hm=new HashMap<String, String>();
	    hm.put("username", UserInformation.Username);
	    hm.put("personality",P);
		hm.put("interest", I);
		hm.put("mood",M);
		
		JSONObject jo=new JSONObject(hm);
		
		//Send SetProfile Request
		UserModule um=new UserModule();
	    um.context= getActivity();
	    um.SF = this;
	    um.execute("SetProfile",jo.toString());
		
	}
	
	public void SaveDataforSettingFragment(){
		
		String P = Integer.toString(choose_personality);
		String I = Integer.toString(choose_interest);
		String M = Integer.toString(choose_mood);
		UserInformation.Mood = M;
	    UserInformation.Interest = I;
	    UserInformation.Personality = P;
	    
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
        choose_mood=n+1;
        Show_BackView();
		
	}
	public void changeImage_interest(int n){
		int imagechage_id2[] =  {R.drawable.interest_1,R.drawable.interest_2,R.drawable.interest_3,R.drawable.interest_4
				,R.drawable.interest_5,R.drawable.interest_6,R.drawable.interest_7,R.drawable.interest_8,R.drawable.interest_9
				,R.drawable.interest_10,R.drawable.interest_11,R.drawable.interest_12};
		interest_button.setBackgroundResource(imagechage_id2[n]);
		choose_interest = n+1;
        Show_BackView();
		
	}
	public void changeImage_personality(int n){
		int imagechage_id3[] = {R.drawable.personality_1,R.drawable.personality_2,R.drawable.personality_3,R.drawable.personality_4
				,R.drawable.personality_5,R.drawable.personality_6,R.drawable.personality_7,R.drawable.personality_8,R.drawable.personality_9
				,R.drawable.personality_10,R.drawable.personality_11,R.drawable.personality_12};
	
		personality_button.setBackgroundResource(imagechage_id3[n]);
		choose_personality = n+1;
        Show_BackView();
		
	}
	
}

