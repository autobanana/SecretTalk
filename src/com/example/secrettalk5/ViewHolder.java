package com.example.secrettalk5;

import android.widget.ImageView;
import android.widget.TextView;


public class ViewHolder {
	 
		ImageView author_level;  
        TextView username;  
        TextView time;  
        TextView content;  
        TextView nickname;
        public ViewHolder(ImageView Author_level,TextView Username, TextView Time, TextView Content,TextView Nickname){
            this.author_level = Author_level;
        	this.username = Username;
            this.time = Time;
            this.content = Content;
            this.nickname= Nickname;
        }
}
