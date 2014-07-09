package com.example.secrettalk5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;


public class SignOut_Fragment extends Fragment {
	
	int mNum; //����
    public static SignOut_Fragment newInstance( int num) {
    	SignOut_Fragment fragment = new SignOut_Fragment();
        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        args.putInt( "num" , num);
//        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        //�o�̧ڥu�O²�檺��num�ϧO���ҡA���������Τ��i�H�ϥίu�ꪺfragment��H�ӧ@������
       // mNum = getArguments() != null ? getArguments().getInt( "num" ) : 1 ;
    }
    /**��Fragment�[���G���ɽե�**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        View view = inflater.inflate(R.layout.fragment_signout_my,  container,false );

        
        Button signourButton=(Button)view.findViewById(R.id.signout_signoutButton);
        
        signourButton.setOnClickListener(new OnClickListener(){      	
        	public void onClick(View v) {
        		
        		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        		
        		alertDialogBuilder.setTitle("�A�T�w�n�n�X��?");
        		
    			alertDialogBuilder.setMessage("�� YES �n�X").setCancelable(false)
				.setPositiveButton("NO",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				  })
				.setNegativeButton("YES",new DialogInterface.OnClickListener() {
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
    
    
    private void Signout(){
    	
    	SharedPreferences account = this.getActivity().getSharedPreferences("ACCOUNT", 0);	
    	
		account.edit().putString("USERNAME", "").commit();
		account.edit().putString("PASSWORD", "").commit();
    	
    	
	    Intent intent = new Intent();
	    intent.setClass(this.getActivity(),LoginActivity.class);
	  	//	intent.setClass(Begin_Prefence_Register_Anim.this,MainActivity.class);
		startActivity(intent);
		this.getActivity().finish();
   	
    }
}
