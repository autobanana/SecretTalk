package com.example.secrettalk5;

import java.util.ArrayList;

import com.example.articlemodule.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	public LayoutInflater myInflater;
	public ArrayList<Article> articleList=new ArrayList<Article>();
    Context context2;  
    
    public ImageAdapter(Context ctxt, ArrayList<Article> articleList) {
    	
		context2 = ctxt;
		myInflater = LayoutInflater.from(ctxt);
		this.articleList = articleList;

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
        
    	//自訂類別，表達個別listItem中的view物件集合。
    	ViewHolder viewTag;
 
        if(convertView == null){
            //取得listItem容器 view
            convertView = myInflater.inflate(R.layout.conversation, null);
             
            //建構listItem內容view
            viewTag = new ViewHolder(
            (ImageView)convertView.findViewById(R.id.level_author),
            (TextView)convertView.findViewById(R.id.Name),
            (TextView)convertView.findViewById(R.id.Time),
            (TextView)convertView.findViewById(R.id.content_talking)
            //(TextView)convertView.findViewById(R.id.NickName)
             );
             
            //設置容器內容
            convertView.setTag(viewTag);
        }
        else{
            viewTag = (ViewHolder) convertView.getTag();
        }
        
      //設定標題文字
        Article article=articleList.get(position);
        
        String author=article.author;
        viewTag.username.setText(author);
        
        //文章建立時間
        String time=article.created_Time;
        viewTag.time.setText(time);
        
        //填暱稱
        //String nickname=article.nickname;
        //viewTag.nickname.setText(nickname);
        
        //設定內容文字
        String content=article.content;
        viewTag.content.setText(content);
        
        String level = article.level;   
        //設定內容圖案
        switch(Integer.valueOf(level)){
           case 0:
                viewTag.author_level.setBackgroundResource(R.drawable.level_1);
                break;
            case 1:
                viewTag.author_level.setBackgroundResource(R.drawable.level_2);
                break;
            case 2:
                viewTag.author_level.setBackgroundResource(R.drawable.level_3);
                break;
            case 3:
            	viewTag.author_level.setBackgroundResource(R.drawable.level_4);
                break;
            case 4:
                viewTag.author_level.setBackgroundResource(R.drawable.level_5);
            	break;
       }
       return convertView;
    	
    } 
}

