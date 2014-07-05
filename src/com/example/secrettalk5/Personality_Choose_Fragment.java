package com.example.secrettalk5;
import com.example.secrettalk5.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Personality_Choose_Fragment extends Fragment {

	public View view;
	public Setting_MyFragment personality_change;
	public LinearLayout personal_setting_personality;
	
	public int personality_id[] = {R.drawable.personality_1,R.drawable.personality_2,R.drawable.personality_3,R.drawable.personality_4
			,R.drawable.personality_5,R.drawable.personality_6,R.drawable.personality_7,R.drawable.personality_8,R.drawable.personality_9
			,R.drawable.personality_10,R.drawable.personality_11,R.drawable.personality_12};
	public int personality_id_press[] = {R.drawable.personality_1_press,R.drawable.personality_2_press,R.drawable.personality_3_press,R.drawable.personality_4_press
			,R.drawable.personality_5_press,R.drawable.personality_6_press,R.drawable.personality_7_press,R.drawable.personality_8_press,R.drawable.personality_9_press
			,R.drawable.personality_10_press,R.drawable.personality_11_press,R.drawable.personality_12_press};
	public int personality_imageview_in_layout[] = {R.id.im1,R.id.im2,R.id.im3,R.id.im4
			,R.id.im5,R.id.im6,R.id.im7,R.id.im8,R.id.im9,R.id.im10
			,R.id.im11,R.id.im12};
	
	public ImageView personality_imageview[]; 
	
	
	public static Personality_Choose_Fragment newInstance( int num) {
		Personality_Choose_Fragment fragment = new Personality_Choose_Fragment();
		
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        view = inflater.inflate(R.layout.fragment_choose_personality,  container,false );
        personality_imageview = new ImageView[12];
        adjustment_layout3();
        initial_imageView();
        return view;
    }
    private void initial_imageView() {
		// TODO Auto-generated method stub
    	 for( int i =0;i<12;i++){
         	personality_imageview[i]= (ImageView)view.findViewById(personality_imageview_in_layout[i]);
         	personality_imageview[i].setBackgroundResource(personality_id[i]);
         }
    	 personality_imageview[0].setOnTouchListener(new ImageButton.OnTouchListener(){ 
          	public boolean onTouch(View view, MotionEvent arg1) {
          		
          		switch (arg1.getAction()) {
                 case MotionEvent.ACTION_DOWN:
                 	view.setBackgroundResource(personality_id_press[0]);
                     break;
                 case MotionEvent.ACTION_MOVE:
                 	view.setBackgroundResource(personality_id[0]); 
                     break;
                 case MotionEvent.ACTION_UP:
                 	view.setBackgroundResource(personality_id[0]); 
                 	break;
                 default:
                     break;
                 }
                 return false;
          	}	
          });
    	 personality_imageview[0].setOnClickListener( new OnClickListener(){      	
          	public void onClick(View view) {
           		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
           		personality_change.changeImage_personality(0);
           	}     	        	
          });
    	 personality_imageview[1].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[1]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[1]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[1]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[1].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(1);
            	}     	        	
           });
     	personality_imageview[2].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[2]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[2]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[2]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[2].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(2);
            	}     	        	
           });
     	personality_imageview[3].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[3]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[3]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[3]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[3].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(3);
            	}     	        	
           });
     	personality_imageview[4].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[4]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[4]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[4]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[4].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(4);
            	}     	        	
           });
     	personality_imageview[5].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[5]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[5]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[5]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[5].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(5);
            	}     	        	
           });
     	personality_imageview[6].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[6]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[6]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[6]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[6].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(6);
            	}     	        	
           });
     	personality_imageview[7].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[7]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[7]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[7]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[7].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(7);
            	}     	        	
           });
     	personality_imageview[8].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[8]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[8]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[8]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[8].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(8);
            	}     	        	
           });
     	personality_imageview[9].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[9]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[9]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[9]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[9].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(9);
            	}     	        	
           });
     	personality_imageview[10].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[10]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[10]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[10]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[10].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(10);
            	}     	        	
           });
     	personality_imageview[11].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(personality_id_press[11]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(personality_id[11]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(personality_id[11]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 personality_imageview[11].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		personality_change.changeImage_personality(11);
            	}     	        	
           });
     	
	}

    public void adjustment_layout3() {
    	personal_setting_personality = (LinearLayout)view.findViewById(R.id.personality_all);
        DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		personal_setting_personality.setLayoutParams(new LinearLayout.LayoutParams(screenW,screenW*4/3));
		
		
		
		
		
	}

}
