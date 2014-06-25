package com.example.secrettalk5;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.example.articlemodule.Article;
import com.example.articlemodule.ArticleModule;
import com.example.usermodule.UserInformation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Listening_MyFragment extends Fragment {

	int mNum; //����
	public Button test_for_notification;
	public TextView t1;
	private ArrayList<Article> articleArrayList;
	private ListView listening_listView;
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
    
    private void GetNewArticle(){
    	//Initial HashMap 
    	HashMap hm=new HashMap<String, String>();
    	hm.put("username", UserInformation.Username);
    			
    	//Convert HashMap to JSONObject
    	JSONObject jo=new JSONObject(hm);
    			
    	//Send Article Post Request
    	ArticleModule am=new ArticleModule();
    	am.context=getActivity();
    	am.listeningFragment=this;
    	am.execute("NewArticle",jo.toString());
    	
    }
    
    public void SetNewArticleListView(ArrayList<Article> articleArrayList){
    	this.articleArrayList=articleArrayList;
    	String count=String.valueOf(articleArrayList.size());
    	Log.v("ListeningFragment", "StarRefreshListView");
    	Log.v("ListeningFragment", "StarRefreshListView");
    	RefreshListView();
    	
    }
    
    private void RefreshListView(){
    	listening_listView.setAdapter(new ImageAdapter(getActivity(),articleArrayList));
    	listening_listView.invalidateViews();
    }
    
    /**��Fragment�[���G���ɽե�**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	count =0;
    	View view = inflater.inflate(R.layout.fragment_listening_my, container,false);
    	t1 = (TextView) view.findViewById(R.id.content_talking);
    	test_for_notification = (Button) view.findViewById(R.id.Login_PostButton);
    	test_for_notification.setOnClickListener(new View.OnClickListener() {
    		
			@Override
			public void onClick(View v) {
					//t1.setText("iiii");
					newMessage("���ѧںΤF�@��ѡA�Pı�ܪŵ�C",count);
					count++;
			}
		});
    
    	listening_listView=(ListView)view.findViewById(R.id.Listening_ListView);
    	GetNewArticle();
    	
        return view;
    }
    
    
    
    
}
