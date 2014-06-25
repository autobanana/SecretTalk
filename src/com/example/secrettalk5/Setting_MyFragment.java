package com.example.secrettalk5;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_MyFragment extends Fragment {

	public View view;
	public RadioGroup radioGroup1,radioGroup2;
	public int selectedRadio1,selectedRadio2;
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
                    
    	
        view = inflater.inflate(R.layout.fragment_setting_my,  container,false );
        Button checkbutton = (Button) view.findViewById(R.id.button1);
        radioGroup1 = (RadioGroup) view.findViewById(R.id.radioGroup1);
    	radioGroup2 = (RadioGroup) view.findViewById(R.id.radioGroup2);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        spinner.setPrompt("請選擇你的星座...");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.Setting_sign_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.Setting_mood_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        checkbutton.setOnClickListener( new OnClickListener(){      	
        	public void onClick(View view) {
        		getSettingInfo();
        		Toast.makeText(view.getContext(), "儲存成功！", Toast.LENGTH_LONG).show();	
        	}     	        	
        });
        
        
        return view;
    }
    public void getSettingInfo(){
    	radioGroup1 = (RadioGroup) view.findViewById(R.id.radioGroup1);
    	radioGroup2 = (RadioGroup) view.findViewById(R.id.radioGroup2);
        selectedRadio1 = radioGroup1.getCheckedRadioButtonId();
        selectedRadio2 = radioGroup2.getCheckedRadioButtonId();
        
        switch(selectedRadio1) {
    	case R.id.radio0 :
    		break;
    	case R.id.radio1 :
    		break;
    	default :
    		Toast.makeText(view.getContext(), "請選擇性別。", Toast.LENGTH_LONG).show();	
        }
        switch(selectedRadio2) {
    	case R.id.radio2_0 :
    		break;
    	case R.id.radio2_1:
    		break;
    	case R.id.radio2_2:
    		break;	
    	case R.id.radio2_3:
    		break;	
    	default :
    		Toast.makeText(view.getContext(), "請選擇血型。", Toast.LENGTH_LONG).show();	
        }
    }

}

