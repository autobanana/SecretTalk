package com.example.secrettalk5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Begin_Prefence_Register_Anim extends Activity {
	
	public LinearLayout layouta;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//setTheme(android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		setContentView(R.layout.begin_frefence_register_anim);
		
		//開場動畫
        layouta=(LinearLayout)findViewById(R.id.begin);
        ImageView myImageView= (ImageView)findViewById(R.id.imageView1);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        myImageView.startAnimation(myFadeInAnimation); 
        
        //跳轉ACTIVITY
        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable,2000);
        
	}
	private Runnable mMyRunnable = new Runnable()
    {
        public void run()
        {
        	Intent intent = new Intent();
      	  	intent.setClass(Begin_Prefence_Register_Anim.this,MainActivity.class);
      	  	startActivity(intent);
      	  	finish(); 
        	//Change state here
        }
     };

}
