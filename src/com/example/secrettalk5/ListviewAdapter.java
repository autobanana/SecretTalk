package com.example.secrettalk5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListviewAdapter extends BaseAdapter {

	public LayoutInflater myInflater;
	//CharSequence[] title ={"回到首頁","偏好設定", "程式導覽", "成就系統介紹", "關於本程式","登出", "結束程式"};
	CharSequence[] title ={"回到首頁", "程式導覽", "成就系統介紹", "關於本程式","登出", "結束程式"};
	//int[] iconName = {R.drawable.list_home,R.drawable.list_preference,R.drawable.list_navi,R.drawable.list_achiment,R.drawable.list_about,R.drawable.list_signout,R.drawable.list_leave};
	int[] iconName = {R.drawable.list_home,R.drawable.list_navi,R.drawable.list_achiment,R.drawable.list_about,R.drawable.list_signout,R.drawable.list_leave};
	Context context2;  
    
	public ListviewAdapter(Context ctxt) {
		// TODO Auto-generated constructor stub
		context2 = ctxt;
		myInflater = LayoutInflater.from(ctxt);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return title.length;  
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		 return title[position];  
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		 return position;  
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListView_Holder listTag;
		if(convertView == null){
            //取得listItem容器 view
            convertView = myInflater.inflate(R.layout.custom_listview, null);
             
            //建構listItem內容view
            listTag = new ListView_Holder(
            	(ImageView) convertView.findViewById(R.id.icon),
            	(TextView)convertView.findViewById(R.id.list_content)
       		);
             
            //設置容器內容
            convertView.setTag(listTag);
        }
        else{
        	listTag = (ListView_Holder) convertView.getTag();
        }
		//設定標題文字
		listTag.listname.setText(title[position]);
        //設定內容文字
		listTag.icon.setImageResource(iconName[position]);
         
        return convertView;
		
		
		
	}

}
