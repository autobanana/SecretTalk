package com.example.secrettalk5;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder_SendandReceive {

        ImageView usericon;
        TextView name;  
        TextView time;  
        TextView content;  
        public ViewHolder_SendandReceive(ImageView UserIcon, TextView Name, TextView Time, TextView Content){
            this.usericon = UserIcon;
            this.name= Name;
            this.time = Time;
            this.content = Content;
        }

}
