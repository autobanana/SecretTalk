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
import android.widget.TextView;

public class Listening_MyFragment extends Fragment {

	int mNum; //����
	public Button test_for_notification;
	public TextView t1;
	public int count;
    public static Listening_MyFragment newInstance( int num) {
    	Listening_MyFragment fragment = new Listening_MyFragment();
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
        //mNum = getArguments() != null ? getArguments().getInt( "num" ) : 1 ;
        
    }
    //�إ߷s�� �q�� 
    public void newMessage(String detail_of_message,int count){
    	
    	
    	NotificationCompat.Builder mBuilder =
    	        new NotificationCompat.Builder(getActivity())
    	        .setSmallIcon(R.drawable.ic_launcher)
    	        .setContentTitle("New Message!")
    	        .setContentText(detail_of_message);
    	mBuilder.setAutoCancel(true);  //�I����۰ʤ���
    	
    	
    	
    	Intent resultIntent = new Intent(getActivity(), MainActivity.class);
    	resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	
    	Bundle bundle = new Bundle();      //notificatioin�|�i�D Mainactivity�h���@��viewpager
        bundle.putInt("viewpager_num",1);
        resultIntent.putExtras(bundle);
        
    	//resultIntent.putExtra("viewpager_num",);      //�Ǫ��i�D�{���O���@��viewpager �o�̰��]��1 : listening
    	
    	
    	
    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
    	stackBuilder.addParentStack(MainActivity.class);
    	stackBuilder.addNextIntent(resultIntent);
    	
    	PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
    	mBuilder.setContentIntent(resultPendingIntent);
    	NotificationManager mNotificationManager =(NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
    	
    	// mId allows you to update the notification later on.
    	mNotificationManager.notify(count, mBuilder.build());
    
    }
    /**��Fragment�[���G���ɽե�**/
    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	
    	count =0;
    	View view = inflater.inflate(R.layout.fragment_listening_my, container,false);
    	t1 = (TextView) view.findViewById(R.id.textView1);
    	test_for_notification = (Button) view.findViewById(R.id.button1);
    	
    	test_for_notification.setOnClickListener(new View.OnClickListener() {
    		
			@Override
			public void onClick(View v) {
					//t1.setText("iiii");
				
					newMessage("���ѧںΤF�@��ѡA�Pı�ܪŵ�C",count);
					count++;
				
			}
		});
    	
    	//btn = (Button) rootView.findViewById(R.id.myButton);
        //btn.setOnClickListener(this);
       
        
        //TextView tv = (TextView) view.findViewById(R.id.textView1);
        //tv.setText( "fragment+" + mNum);
        return view;
    }

}
