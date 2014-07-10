package com.example.secrettalk5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Begin_Prefence_Register_Anim extends Activity {
	
	public LinearLayout layoutA;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.begin_frefence_register_anim); //原來
		
		//setContentView(R.layout.fragment_register_new);
		
		
		//開場動畫
        layoutA=(LinearLayout)findViewById(R.id.begin);
        ImageView myImageView= (ImageView)findViewById(R.id.icon_imageview);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        myImageView.startAnimation(myFadeInAnimation); 
        
        
        
        
        
        
        //跳轉ACTIVITY
        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable,2000);
        
        
        
	}
	private Runnable Menu = new Runnable()
    {
        public void run()
        {
        	setContentView(R.layout.begin_frefence_register_anim);

        }
     };
	private Runnable mMyRunnable = new Runnable()
    {
        public void run()
        {
        	Intent intent = new Intent();
        	intent.setClass(Begin_Prefence_Register_Anim.this,LoginActivity.class);
      	  //	intent.setClass(Begin_Prefence_Register_Anim.this,MainActivity.class);
      	  	startActivity(intent);
      	  	finish(); 
        	//Change state here
        }
     };

}
