package com.example.secrettalk5;

import android.content.Context;
import android.util.DisplayMetrics;
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
	public int ScreenW=0;
	public String Level =null;
	public String NickName=null;
    
    public ArticleDynamicAdapter(Context ctxt,String nickname,String name, String time,String content,int who,String level,int screenW) {
		context2 = ctxt;
		myInflater = LayoutInflater.from(ctxt);
		
		ScreenW = screenW;
		Level = level;
		who_say = who;
		Name = name;
		Content = content;
		Time = time;
		NickName=nickname;
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
    	
		//自訂類別，表達個別listItem中的view物件集合。
    	ViewHolder_SendandReceive viewhoder;
 
        if(convertView == null){
            //取得listItem容器 view
        	if(who_say == 0){  //send自己
        		convertView = myInflater.inflate(R.layout.article_send, null);
        	}
            else if(who_say == 1){ //receive別人
            	convertView = myInflater.inflate(R.layout.article_reveive2, null);
            }
        	//建構listItem內容view
        	
        	viewhoder = new ViewHolder_SendandReceive(
        			(ImageView)convertView.findViewById(R.id.icon_imageview),		
        			(TextView)convertView.findViewById(R.id.name),
        			(TextView)convertView.findViewById(R.id.time),
        			(TextView)convertView.findViewById(R.id.content1)
             );
            //設置容器內容
            convertView.setTag(viewhoder);
        }
        else{
        	viewhoder = (ViewHolder_SendandReceive) convertView.getTag();
        }
         
        //設定內容圖案
        switch(Integer.valueOf(Level)){
        case 0:
        	viewhoder.usericon.setBackgroundResource(R.drawable.level_1_1);
            break;
        case 1:
        	viewhoder.usericon.setBackgroundResource(R.drawable.level_2_1);
            break;
        case 2:
        	viewhoder.usericon.setBackgroundResource(R.drawable.level_3_1);
            break;
        case 3:
        	viewhoder.usericon.setBackgroundResource(R.drawable.level_4_1);
            break;
        case 4:
        	viewhoder.usericon.setBackgroundResource(R.drawable.level_5_1);
        	break;
       }
        
        viewhoder.content.setMaxWidth(ScreenW-(ScreenW*3/10));

        viewhoder.time.setText(Time);
        viewhoder.name.setText(NickName);
        viewhoder.content.setText(Content);
        
         
        return convertView;
	}

}
