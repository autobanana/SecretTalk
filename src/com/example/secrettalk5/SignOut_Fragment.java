package com.example.secrettalk5;

import com.example.usermodule.UserInformation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;


public class SignOut_Fragment extends Fragment {
	
	int mNum; //頁號
	public MainActivity MA;
    public static SignOut_Fragment newInstance( int num) {
    	SignOut_Fragment fragment = new SignOut_Fragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
    }
    /**為Fragment加載佈局時調用**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        View view = inflater.inflate(R.layout.fragment_signout_my,  container,false );

        
        Button signourButton=(Button)view.findViewById(R.id.signout_signoutButton);
        TextView NickName = (TextView)view.findViewById(R.id.signout_nickname_textView);
        TextView UserName = (TextView)view.findViewById(R.id.signout_username_textView);
        NickName.setText(UserInformation.NickName);
        UserName.setText(UserInformation.Username);
        
        signourButton.setOnClickListener(new OnClickListener(){      	
        	public void onClick(View v) {
        		
        		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        		
        		alertDialogBuilder.setTitle("登出 SecretTalk");
        		
    			alertDialogBuilder.setMessage("\n "+UserInformation.NickName+"你確定要登出 SecretTalk 嗎?\n").setCancelable(false)
				.setPositiveButton("取消",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				  })
				.setNegativeButton("登出",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						Signout();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
        	}
        });
        
        return view;
    }
    
    
    public void Signout(){
    	
    	SharedPreferences account = this.getActivity().getSharedPreferences("ACCOUNT", 0);	
    	
		account.edit().putString("USERNAME", "").commit();
		account.edit().putString("PASSWORD", "").commit();
    	
		
	    Intent intent = new Intent();
	    intent.setClass(this.getActivity(),LoginActivity.class);
		startActivity(intent);
		
		this.getActivity().finish();
		
		
   	
    }
}
