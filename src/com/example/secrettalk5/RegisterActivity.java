package com.example.secrettalk5;

import java.util.HashMap;

import org.json.JSONObject;

import com.example.usermodule.UserModule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	public String[] sign;
	public RadioGroup GendermRadioGroup1; 
	public RadioButton mRadio1,mRadio2; 
	public String gender;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_register_new);
		
		//Register Login Button OnClick Event
		Button RegisterButton=(Button)findViewById(R.id.button2);
		RegisterButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				RegisterEvent();
			}
		});
		Button Cancel=(Button)findViewById(R.id.button1);
		Cancel.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		AddSpinnerChoice();
		AddBloodTypeChoice();
		AddGenderChoice();
	}
	
	private void AddSpinnerChoice(){
		Spinner spinner=(Spinner)findViewById(R.id.spinner1);
		sign =  getResources().getStringArray(R.array.Setting_sign_array);   
		
		ArrayAdapter<String> signList=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,sign);
		spinner.setAdapter(signList);
		
	}
	
	private void AddBloodTypeChoice(){
		Spinner spinner=(Spinner)findViewById(R.id.spinner2);
		String[] bloodType = {"A", "B", "AB", "O"};
		ArrayAdapter<String> bloodTypeList=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,bloodType);
		spinner.setAdapter(bloodTypeList);
	}
	
	private void AddGenderChoice(){
		
		GendermRadioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
		mRadio1 = (RadioButton) findViewById(R.id.radio0);
		mRadio2 = (RadioButton) findViewById(R.id.radio1); 
		      
		GendermRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
		    public void onCheckedChanged(RadioGroup group, int checkedId){
		    	if(checkedId== mRadio1.getId()){
		    		gender = "male";
		    	}
		    	else if(checkedId==mRadio2.getId()){ 
		    		gender = "female";
		    	}       
		    } 
		  }); 
		
		/*Spinner spinner=(Spinner)findViewById(R.id.Register_GenderSpinner);
		String[] gender = {"men", "female"};
		ArrayAdapter<String> genderList=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,gender);
		spinner.setAdapter(genderList);*/
	}
	
	
	
	
	private void RegisterEvent(){
		
		//Get All Content
		String username=((EditText)findViewById(R.id.register_Username)).getText().toString();
		String password=((EditText)findViewById(R.id.register_password)).getText().toString();
		String nickname=((EditText)findViewById(R.id.register_nickname)).getText().toString();
		String realname=((EditText)findViewById(R.id.register_realname)).getText().toString();
		
		String birthday_year =((EditText)findViewById(R.id.year)).getText().toString();
		String birthday_month = ((EditText)findViewById(R.id.month)).getText().toString();
		String birthday_day = ((EditText)findViewById(R.id.day)).getText().toString();
		String birthday = birthday_year+"."+birthday_month+"."+birthday_day;
		Spinner signSpinner=(Spinner)findViewById(R.id.spinner1);
		Spinner bloodTypeSpinner=(Spinner)findViewById(R.id.spinner2);
		String bloodType=bloodTypeSpinner.getSelectedItem().toString();
		String sign_get =signSpinner.getSelectedItem().toString();
		
		
		//處理星座字串 (顯示中文 英文 日期 ) 送出英文
		int num_of_constellatioin = 0;
		String SummitSign[] = {"Aries", "Taurus", "Gemini", "Cancer", "Leo","Virgo","Libra","Scorpio",
				"Sagittarius","Capricorn","Aquarius","Pisces"};
		for ( int i = 0; i<12;i++){
			if(sign[i].equals(sign_get)){
				num_of_constellatioin = i;
			}
		}
		 
		
		if(username==null||password==null||
				nickname==null||realname==null||
				realname==null||birthday_year ==null || birthday_month== null || birthday_day == null){
			
			Toast.makeText(this, "Please fill all content", Toast.LENGTH_LONG).show();
			
		}
		
		else{
			
			//Initial HashMap 
			HashMap<String, String> hm=new HashMap<String, String>();
			hm.put("username", username);
			hm.put("password", password);
			hm.put("nickname", nickname);
			hm.put("realname", realname);
			hm.put("birthday", birthday);
			hm.put("sign", SummitSign[num_of_constellatioin]);
			hm.put("gender", gender);
			hm.put("bloodType", bloodType);
			
			//Convert HashMap to JSONObject
			JSONObject jo=new JSONObject(hm);
			
			//Send Login Request
			UserModule um=new UserModule();
		    um.context=this;
		    um.RA=this;
		    um.execute("Register",jo.toString());
			
		}
		
	}
	
	public void RegisterSuccessEvent(){
		finish();
	}
	
	
	
	
	
}
