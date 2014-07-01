package com.example.secrettalk5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleDynamicAdapter extends BaseAdapter {

	public LayoutInflater myInflater;
    Context context2;  
    public int who_say;
    public String Name = null;
    public String Content = null;
    public String Time = null;
	
    public ArticleDynamicAdapter(Context ctxt, String name, String time,String content,int who) {
		context2 = ctxt;
		myInflater = LayoutInflater.from(ctxt);
		
		who_say = who;
		Name = name;
		Content = content;
		Time = time;
	}

	@Override
	public int getCount() {
		return  1;//Name.length;
	}

	@Override
	public Object getItem(int position) {
		return 1;//Name[position]; 
	}

	@Override
	public long getItemId(int position) {
		return position;  
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
    	
		//�ۭq���O�A���F�ӧOlistItem����view���󶰦X�C
    	ViewHolder_SendandReceive viewhoder;
 
        if(convertView == null){
            //���olistItem�e�� view
        	if(who_say == 0){  //send�ۤv
        		convertView = myInflater.inflate(R.layout.article_send, null);
        	}
            else if(who_say == 1){ //receive�O�H
            	convertView = myInflater.inflate(R.layout.article_receive, null);
            }
        	//�غclistItem���eview
        	
        	viewhoder = new ViewHolder_SendandReceive(
        			(ImageView)convertView.findViewById(R.id.icon_imageview),		
        			(TextView)convertView.findViewById(R.id.name),
        			(TextView)convertView.findViewById(R.id.time),
        			(TextView)convertView.findViewById(R.id.content1)
             );
            //�]�m�e�����e
            convertView.setTag(viewhoder);
        }
        else{
        	viewhoder = (ViewHolder_SendandReceive) convertView.getTag();
        }
         
        //�]�w���e�Ϯ�
        switch(position){
            case 0:
            	viewhoder.usericon.setImageResource(R.drawable.test_for_conversation);//.setResource(R.drawable.test_for_conversation);
                break;
            case 1:
            	viewhoder.usericon.setImageResource(R.drawable.test_for_conversation);
                break;
            case 2:
            	viewhoder.usericon.setImageResource(R.drawable.test_for_conversation);
                break;
       }
         
        viewhoder.time.setText(Time);
        viewhoder.name.setText(Name);
        viewhoder.content.setText(Content);
        
         
        return convertView;
	}

}