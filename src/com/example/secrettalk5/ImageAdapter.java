package com.example.secrettalk5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	public LayoutInflater myInflater;
    CharSequence[] title = null;
    CharSequence[] info = null;
    
    Context context2;  
    //String [] str = new String[]{"NO.01","No.02","No.03"};  
	
    
    public ImageAdapter(Context ctxt, CharSequence[] title, CharSequence[] info) {
		// TODO Auto-generated constructor stub
		context2 = ctxt;
		myInflater = LayoutInflater.from(ctxt);
        this.title = title;
        this.info = info;
	}
    @Override  
    public int getCount() {  
        return title.length;  
    }  
    @Override  
    public Object getItem(int position) {  
        return title[position];  
    }  
    @Override  
    public long getItemId(int position) {  
        return position;  
    }  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        
    	///
    	//自訂類別，表達個別listItem中的view物件集合。
    	ViewHolder viewTag;
 
        if(convertView == null){
            //取得listItem容器 view
            convertView = myInflater.inflate(R.layout.conversation, null);
             
            //建構listItem內容view
            viewTag = new ViewHolder(
            (TextView)convertView.findViewById(R.id.Name),
            (TextView) convertView.findViewById(R.id.Time),
            (TextView) convertView.findViewById(R.id.content_talking)
             );
             
            //設置容器內容
            convertView.setTag(viewTag);
        }
        else{
            viewTag = (ViewHolder) convertView.getTag();
        }
         
        //設定內容圖案
//        switch(position){
//            case 0:
//                viewTag.icon.setBackgroundResource(R.drawable.taipei);
//                break;
//            case 1:
//                viewTag.icon.setBackgroundResource(R.drawable.taichung);
//                break;
//            case 2:
//                viewTag.icon.setBackgroundResource(R.drawable.kaohsiung);
//                break;
//       }
         
        //設定標題文字
        viewTag.username.setText(title[position]);
        //設定內容文字
        viewTag.content.setText(info[position]);
         
        return convertView;
    	///
    	
    	/*ViewHolder holder;  
        if(convertView == null){  
            //View的getTag()獲取viewHolder對象  
            holder = new ViewHolder();  
           // holder2 = new ViewHolder();
            convertView = View.inflate(context2, R.layout.conversation, null);  
            holder.username = (TextView) convertView.findViewById(R.id.Name);  
            holder.time = (TextView) convertView.findViewById(R.id.Time);  
            holder.content = (TextView) convertView.findViewById(R.id.content_talking);  
            convertView.setTag(holder);  
              
        
        }else {  
        
        	holder = (ViewHolder) convertView.getTag();  
        
        }  
        //holder.imageButton.setImageResource(imageid[position]);  
        //holder.username.setText(str[position]);  
        //holder.time.setText(time[position]);  
        //holder.content.setText(content[position]);  
        return convertView;  */
    } 
}

