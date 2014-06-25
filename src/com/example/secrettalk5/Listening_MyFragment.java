package com.example.secrettalk5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Listening_MyFragment extends Fragment {

	int mNum; //頁號
	public Button test_for_notification;
	public TextView t1;
	public int count;
	public ListView listening_listView;
    public static Listening_MyFragment newInstance( int num) {
    	Listening_MyFragment fragment = new Listening_MyFragment();
        
    	return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        
    }
    
    /**為Fragment加載佈局時調用**/
    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	
    	count =0;
    	View view = inflater.inflate(R.layout.fragment_listening_my, container,false);
    	String[] mtitle = new String[]{"顆顆","XXX","OOO","HH"} ;
        String[] minfo =  new String[]{"唉呦凡呢","DDDDDDDD.高美濕地.大坑風景區","西SSSSSSSSSS子灣.英國領事館.愛河","a;dlkajsdkja;djadkja;dkja;dkja;sdkja;ldjkajd;alksdjsalkdj"};
        listening_listView = (ListView) view.findViewById(R.id.listening_ListView1);  
        listening_listView.setAdapter(new ImageAdapter(container.getContext(), mtitle, minfo));//getActivity().getApplicationContext()));
 
    	/*t1 = (TextView) view.findViewById(R.id.content_talking);
    	test_for_notification = (Button) view.findViewById(R.id.button1);
    	
    	test_for_notification.setOnClickListener(new View.OnClickListener() {
    		
			@Override
			public void onClick(View v) {
					//t1.setText("iiii");
				
					newMessage("今天我睡了一整天，感覺很空虛。",count);
					count++;
				
			}
		});
    	*/
    	
        return view;
    }
public void newMessage(String detail_of_message,int count){
    	
    	
    	NotificationCompat.Builder mBuilder =
    	        new NotificationCompat.Builder(getActivity())
    	        .setSmallIcon(R.drawable.ic_launcher)
    	        .setContentTitle("New Message!")
    	        .setContentText(detail_of_message);
    	mBuilder.setAutoCancel(true);  //點擊後自動不見
    	
    	
    	
    	Intent resultIntent = new Intent(getActivity(), MainActivity.class);
    	resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	
    	Bundle bundle = new Bundle();      //notificatioin會告訴 Mainactivity去哪一個viewpager
        bundle.putInt("viewpager_num",1);
        resultIntent.putExtras(bundle);
        
    	//resultIntent.putExtra("viewpager_num",);      //傳直告訴程式是哪一個viewpager 這裡假設為1 : listening
    	
    	
    	
    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
    	stackBuilder.addParentStack(MainActivity.class);
    	stackBuilder.addNextIntent(resultIntent);
    	
    	PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
    	mBuilder.setContentIntent(resultPendingIntent);
    	NotificationManager mNotificationManager =(NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
    	
    	// mId allows you to update the notification later on.
    	mNotificationManager.notify(count, mBuilder.build());
    
    }
}
