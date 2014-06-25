package com.example.secrettalk5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Talking_MyFragment extends Fragment {

	int mNum; //����
	public Context a;
	private FrameLayout mainLayout; 
	public ListView talking_listView;
    public static Talking_MyFragment newInstance( int num) {
    	Talking_MyFragment fragment = new Talking_MyFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt( "num" , num);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
    }
    
    /**��Fragment�[���G���ɽե�**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
    	String[] mtitle = new String[]{"�x�_","�x��","����","hhhhhhhhhh"} ;
        String[] minfo =  new String[]{"�x�_101.�G���ժ��|.�����O����","���|����.������a.�j�|������","��l�W.�^�����].�R�e","a;dlkajsdkja;djadkja;dkja;dkja;sdkja;ldjkajd;alksdjsalkdj"};
         
        View view = inflater.inflate(R.layout.fragment_talking_my, container,false);
        //a = ;
        //mainLayout = (FrameLayout)view.findViewById(R.id.a);  
        
        talking_listView = (ListView) view.findViewById(R.id.Talking_ListView1);  
        talking_listView.setAdapter(new ImageAdapter(container.getContext(), mtitle, minfo));//getActivity().getApplicationContext()));

        
        
        return view;
    }
    
}

