package com.example.secrettalk5;

import android.util.Log;
import java.util.HashMap;
import org.json.JSONObject;
import com.example.usermodule.UserModule;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;


public class RegisterActivity extends Activity {

	public String[] sign;
	public RadioGroup GendermRadioGroup1; 
	public RadioButton mRadio1,mRadio2; 
	public String gender = "male";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_register_new);
		
		//Register Login Button OnClick Event
		Button RegisterButton=(Button)findViewById(R.id.PostArticle_PostButton2);
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
		
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
        cd.showConnction();		
        
           
		
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
		 
		
		if(CheckRegisterData(username,password,nickname,realname,birthday)){
					
//			Toast.makeText(this, "Please fill all content", Toast.LENGTH_LONG).show();
			
		}else{
			
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
		Toast.makeText(this.getApplicationContext(), "註冊成功，歡迎享受這美妙的app!", Toast.LENGTH_LONG).show();
		finish();
	}
	
	
	
	private boolean CheckRegisterData(String username,String password,String n_name,String r_name,String birthday){
		
		
		////////////ID Part
		
		if(username.isEmpty()){
			Toast.makeText(this.getApplicationContext(), "帳號不可以為空", Toast.LENGTH_LONG).show();
			return true;
		}
		
		if(username.length()<6 || password.length()>12){
			Toast.makeText(this.getApplicationContext(), "帳號需介於6至12碼", Toast.LENGTH_LONG).show();
			return true;			
		}
		
				
		if(!(username.charAt(0)<='z' && username.charAt(0)>='a')) {
			Toast.makeText(this.getApplicationContext(), "帳號字首需為英文小寫", Toast.LENGTH_LONG).show();
			return true;
		}
		
		if(!username.matches("[a-z0-9|\\.]*")) {
			Toast.makeText(this.getApplicationContext(), "帳號需為英文小寫與數字組合", Toast.LENGTH_LONG).show();
			return true;
		}
		
		
		
		
		//////////////// password Part
		if(password.isEmpty()){
			Toast.makeText(this.getApplicationContext(), "密碼不可以為空", Toast.LENGTH_LONG).show();
			return true;
		}
		
		if(password.length()<6 || password.length()>12){
			Toast.makeText(this.getApplicationContext(), "密碼需介於6至12碼", Toast.LENGTH_LONG).show();
			return true;			
		}
		
						
		if(!password.matches("[a-zA-Z0-9|\\.]*")){
			Toast.makeText(this.getApplicationContext(), "密碼需為英文與數字組合", Toast.LENGTH_LONG).show();
			return true;
		}

				
		//////////////// RealName Part
		if(r_name.isEmpty()){
			Toast.makeText(this.getApplicationContext(), "真實姓名不可為空", Toast.LENGTH_LONG).show();
			return true;
		}
		
		//////////////// nickName Part
		if(n_name.isEmpty()){
			Toast.makeText(this.getApplicationContext(), "暱稱不可為空", Toast.LENGTH_LONG).show();
			return true;
		}
		
		
		
		////////////////birthday Part
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		dateFormat.setLenient(false);
		
		try	{
			dateFormat.parse(birthday);
		} catch (Exception e) {
			Toast.makeText(this.getApplicationContext(), "請輸入正確的日期格式", Toast.LENGTH_LONG).show();
			return true;
		}
		
		
		return false;
	}
	
	
	
}
