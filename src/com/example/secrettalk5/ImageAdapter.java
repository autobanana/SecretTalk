package com.example.secrettalk5;

import java.util.ArrayList;

import com.example.articlemodule.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	public LayoutInflater myInflater;
	public ArrayList<Article> articleList=new ArrayList<Article>();
    Context context2;  
    
    public ImageAdapter(Context ctxt, ArrayList<Article> articleList) {

		context2 = ctxt;
		myInflater = LayoutInflater.from(ctxt);
		this.articleList=articleList;

	}
    @Override  
    public int getCount() {  
        return articleList.size();  
    }  
    @Override  
    public Object getItem(int position) {  
        return articleList.get(position);  
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
            (TextView)convertView.findViewById(R.id.Time),
            (TextView)convertView.findViewById(R.id.content_talking)
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
        Article article=articleList.get(position);
        
        String author=article.author;
        viewTag.username.setText(author);
        
        //設定內容文字
        String content=article.content;
        viewTag.content.setText(content);
         
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

