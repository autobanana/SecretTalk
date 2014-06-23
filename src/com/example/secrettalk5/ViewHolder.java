package com.example.secrettalk5;

import android.widget.TextView;

public class ViewHolder {
	 
        //public ImageButton imageButton;  
        TextView username;  
        TextView time;  
        TextView content;  
        public ViewHolder(TextView Username, TextView Time, TextView Content){
            this.username = Username;
            this.time = Time;
            this.content = Content;
        }
}
