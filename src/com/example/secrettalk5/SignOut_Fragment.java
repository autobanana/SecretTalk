package com.example.secrettalk5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return view;
    }

}