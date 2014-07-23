package com.example.secrettalk5;

import com.example.globalcontainer.GlobalContainer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Bulletinboard_Fragment extends Fragment {
	public static Bulletinboard_Fragment newInstance( int num) {
		Bulletinboard_Fragment fragment = new Bulletinboard_Fragment();
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        View view = inflater.inflate(R.layout.fragment_bulletinboard,  container,false );
        TextView message1=(TextView)view.findViewById(R.id.BulltinBorad_message1);
        TextView message2=(TextView)view.findViewById(R.id.BulltinBoard_message2);
        TextView message3=(TextView)view.findViewById(R.id.BulltinBoard_message3);
        
        int announcement_count=0;
        if(GlobalContainer.announcemen_ArrayList.size()<3)
        	announcement_count=GlobalContainer.announcemen_ArrayList.size();
        else
        	announcement_count=3;
        
        for(int i=0;i<announcement_count;i++)
        {
        	if(i==0)
        		message1.setText(GlobalContainer.announcemen_ArrayList.get(GlobalContainer.announcemen_ArrayList.size()-1).content);
        	else if(i==1)
        		message2.setText(GlobalContainer.announcemen_ArrayList.get(GlobalContainer.announcemen_ArrayList.size()-2).content);
        	else
        		message3.setText(GlobalContainer.announcemen_ArrayList.get(GlobalContainer.announcemen_ArrayList.size()-3).content);
        }
        
        return view;
    }
}
