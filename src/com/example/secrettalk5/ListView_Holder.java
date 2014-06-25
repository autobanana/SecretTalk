package com.example.secrettalk5;

import android.widget.ImageView;
import android.widget.TextView;

public class ListView_Holder {
	ImageView icon;
	TextView listname;  
    public ListView_Holder(ImageView IconImage, TextView ListName){
        this.icon = IconImage;
        this.listname = ListName;
    }
}
