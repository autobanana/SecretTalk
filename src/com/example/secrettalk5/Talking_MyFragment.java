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

	int mNum; //頁號
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
    
    /**為Fragment加載佈局時調用**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
    	String[] mtitle = new String[]{"台北","台中","高雄","hhhhhhhhhh"} ;
        String[] minfo =  new String[]{"台北101.故公博物院.中正記念堂","都會公園.高美濕地.大坑風景區","西子灣.英國領事館.愛河","a;dlkajsdkja;djadkja;dkja;dkja;sdkja;ldjkajd;alksdjsalkdj"};
         
        View view = inflater.inflate(R.layout.fragment_talking_my, container,false);
        //a = ;
        //mainLayout = (FrameLayout)view.findViewById(R.id.a);  
        
        talking_listView = (ListView) view.findViewById(R.id.Talking_ListView1);  
        talking_listView.setAdapter(new ImageAdapter(container.getContext(), mtitle, minfo));//getActivity().getApplicationContext()));

        
        
        return view;
    }
    
}

