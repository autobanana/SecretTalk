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
	//CharSequence[] title ={"�^�쭺��","���n�]�w", "�{������", "���N�t�Τ���", "���󥻵{��","�n�X", "�����{��"};
	CharSequence[] title ={"�^�쭺��", "�{������", "���N�t�Τ���", "���󥻵{��","�n�X", "�����{��"};
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
            //���olistItem�e�� view
            convertView = myInflater.inflate(R.layout.custom_listview, null);
             
            //�غclistItem���eview
            listTag = new ListView_Holder(
            	(ImageView) convertView.findViewById(R.id.icon),
            	(TextView)convertView.findViewById(R.id.list_content)
       		);
             
            //�]�m�e�����e
            convertView.setTag(listTag);
        }
        else{
        	listTag = (ListView_Holder) convertView.getTag();
        }
		//�]�w���D��r
		listTag.listname.setText(title[position]);
        //�]�w���e��r
		listTag.icon.setImageResource(iconName[position]);
         
        return convertView;
		
		
		
	}

}
