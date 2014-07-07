package com.example.secrettalk5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Mood_Choose_Fragment extends Fragment {

	public View view;
	//public int i;
	public Setting_MyFragment mood_change;
	public LinearLayout personal_setting_mood;
	public int mood_id[] = {R.drawable.mood_1,R.drawable.mood_2,R.drawable.mood_3,R.drawable.mood_4
			,R.drawable.mood_5,R.drawable.mood_6,R.drawable.mood_7,R.drawable.mood_8,R.drawable.mood_9
			,R.drawable.mood_10,R.drawable.mood_11,R.drawable.mood_12};
	public int mood_id_press[] = {R.drawable.mood_1_press,R.drawable.mood_2_press,R.drawable.mood_3_press,R.drawable.mood_4_press
			,R.drawable.mood_5_press,R.drawable.mood_6_press,R.drawable.mood_7_press,R.drawable.mood_8_press,R.drawable.mood_9_press
			,R.drawable.mood_10_press,R.drawable.mood_11_press,R.drawable.mood_12_press};
	public int mood_imageview_in_layout[] = {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4
			,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9,R.id.imageView10
			,R.id.imageView11,R.id.imageView12};
	
	public ImageView mood_imageview[]; 
	public static Mood_Choose_Fragment newInstance( int num) {
		Mood_Choose_Fragment fragment = new Mood_Choose_Fragment();
		
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	
    	mood_imageview = new ImageView[12];
        view = inflater.inflate(R.layout.fragment_choose_mood,  container,false );
        adjustment_layout1();
        initial_imageView();
       
        return view;
    }
    private void initial_imageView() {
		// TODO Auto-generated method stub
    	 for( int i =0;i<12;i++){
         	mood_imageview[i]= (ImageView)view.findViewById(mood_imageview_in_layout[i]);
         	mood_imageview[i].setBackgroundResource(mood_id[i]);
         }
    	 mood_imageview[0].setOnTouchListener(new ImageButton.OnTouchListener(){ 
          	public boolean onTouch(View view, MotionEvent arg1) {
          		
          		switch (arg1.getAction()) {
                 case MotionEvent.ACTION_DOWN:
                 	view.setBackgroundResource(mood_id_press[0]);
                     break;
                 case MotionEvent.ACTION_MOVE:
                 	view.setBackgroundResource(mood_id[0]); 
                     break;
                 case MotionEvent.ACTION_UP:
                 	view.setBackgroundResource(mood_id[0]); 
                 	break;
                 default:
                     break;
                 }
                 return false;
          	}	
          });
    	 mood_imageview[0].setOnClickListener( new OnClickListener(){      	
          	public void onClick(View view) {
           		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
           		mood_change.changeImage_mood(0);
           	}     	        	
          });
    	 mood_imageview[1].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[1]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[1]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[1]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[1].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(1);
            	}     	        	
           });
     	mood_imageview[2].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[2]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[2]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[2]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[2].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(2);
            	}     	        	
           });
     	mood_imageview[3].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[3]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[3]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[3]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[3].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(3);
            	}     	        	
           });
     	mood_imageview[4].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[4]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[4]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[4]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[4].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(4);
            	}     	        	
           });
     	mood_imageview[5].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[5]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[5]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[5]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[5].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(5);
            	}     	        	
           });
     	mood_imageview[6].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[6]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[6]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[6]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[6].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(6);
            	}     	        	
           });
     	mood_imageview[7].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[7]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[7]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[7]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[7].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(7);
            	}     	        	
           });
     	mood_imageview[8].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[8]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[8]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[8]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[8].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(8);
            	}     	        	
           });
     	mood_imageview[9].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[9]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[9]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[9]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[9].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(9);
            	}     	        	
           });
     	mood_imageview[10].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[10]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[10]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[10]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[10].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(10);
            	}     	        	
           });
     	mood_imageview[11].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(mood_id_press[11]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(mood_id[11]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(mood_id[11]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 mood_imageview[11].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		mood_change.changeImage_mood(11);
            	}     	        	
           });
     	
	}


	public void adjustment_layout1() {
    	personal_setting_mood = (LinearLayout)view.findViewById(R.id.mood_all);
        DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		personal_setting_mood.setLayoutParams(new LinearLayout.LayoutParams(screenW,screenW*4/3));
	}

}
