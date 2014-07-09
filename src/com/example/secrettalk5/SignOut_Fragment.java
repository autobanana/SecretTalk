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
import android.widget.Toast;

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
        //TextView tv = (TextView) view.findViewById(R.id.textView1);
        //tv.setText( "fragment+" + mNum);
        
        
        Button signourButton=(Button)view.findViewById(R.id.signout_signoutButton);
        
        signourButton.setOnClickListener(new OnClickListener(){      	
        	public void onClick(View v) {
        		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        		
        		alertDialogBuilder.setTitle("Are you sure to signout?");
        		
    			alertDialogBuilder.setMessage("Click yes to exit!").setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						Toast.makeText(getActivity(),"�p�Q�Q�o�n�A�[�F��@!", Toast.LENGTH_SHORT).show();
						
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
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

}
