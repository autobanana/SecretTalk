package com.example.secrettalk5;

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

public class Interest_Choose_Fragment extends Fragment {

	public View view;
	public Setting_MyFragment interest_change;
	public LinearLayout personal_setting_interest;
	public int interest_id[] = {R.drawable.interest_1,R.drawable.interest_2,R.drawable.interest_3,R.drawable.interest_4
			,R.drawable.interest_5,R.drawable.interest_6,R.drawable.interest_7,R.drawable.interest_8,R.drawable.interest_9
			,R.drawable.interest_10,R.drawable.interest_11,R.drawable.interest_12};
	public int interest_id_press[] = {R.drawable.interest_1_press,R.drawable.interest_2_press,R.drawable.interest_3_press,R.drawable.interest_4_press
			,R.drawable.interest_5_press,R.drawable.interest_6_press,R.drawable.interest_7_press,R.drawable.interest_8_press,R.drawable.interest_9_press
			,R.drawable.interest_10_press,R.drawable.interest_11_press,R.drawable.interest_12_press};
	public int interest_imageview_in_layout[] = {R.id.image1,R.id.image2,R.id.image3,R.id.image4
			,R.id.image5,R.id.image6,R.id.image7,R.id.image8,R.id.image9,R.id.image10
			,R.id.image11,R.id.image12};
	
	public ImageView interest_imageview[]; 
	public static Interest_Choose_Fragment newInstance( int num) {
		Interest_Choose_Fragment fragment = new Interest_Choose_Fragment();
		
        return fragment;
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**為Fragment加載佈局時調用**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                                                                                                                                                                                                                                                                                                                      
        view = inflater.inflate(R.layout.fragment_choose_interest,  container,false );
        interest_imageview = new ImageView[12];
        adjustment_layout2();
        initial_imageView();
        return view;
    }
    private void initial_imageView() {
		// TODO Auto-generated method stub
    	 for( int i =0;i<12;i++){
         	interest_imageview[i]= (ImageView)view.findViewById(interest_imageview_in_layout[i]);
         	interest_imageview[i].setBackgroundResource(interest_id[i]);
         }
    	 
    	 interest_imageview[0].setOnTouchListener(new ImageButton.OnTouchListener(){ 
          	public boolean onTouch(View view, MotionEvent arg1) {
          		
          		switch (arg1.getAction()) {
                 case MotionEvent.ACTION_DOWN:
                 	view.setBackgroundResource(interest_id_press[0]);
                     break;
                 case MotionEvent.ACTION_MOVE:
                 	view.setBackgroundResource(interest_id[0]); 
                     break;
                 case MotionEvent.ACTION_UP:
                 	view.setBackgroundResource(interest_id[0]); 
                 	break;
                 default:
                     break;
                 }
                 return false;
          	}	
          });
    	 interest_imageview[0].setOnClickListener( new OnClickListener(){      	
          	public void onClick(View view) {
           		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
           		interest_change.changeImage_interest(0);
           	}     	        	
          });
    	 interest_imageview[1].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[1]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[1]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[1]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[1].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(1);
            	}     	        	
           });
     	interest_imageview[2].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[2]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[2]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[2]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[2].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(2);
            	}     	        	
           });
     	interest_imageview[3].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[3]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[3]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[3]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[3].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(3);
            	}     	        	
           });
     	interest_imageview[4].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[4]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[4]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[4]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[4].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(4);
            	}     	        	
           });
     	interest_imageview[5].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[5]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[5]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[5]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[5].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(5);
            	}     	        	
           });
     	interest_imageview[6].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[6]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[6]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[6]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[6].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(6);
            	}     	        	
           });
     	interest_imageview[7].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[7]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[7]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[7]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[7].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(7);
            	}     	        	
           });
     	interest_imageview[8].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[8]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[8]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[8]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[8].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(8);
            	}     	        	
           });
     	interest_imageview[9].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[9]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[9]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[9]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[9].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(9);
            	}     	        	
           });
     	interest_imageview[10].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[10]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[10]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[10]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[10].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(10);
            	}     	        	
           });
     	interest_imageview[11].setOnTouchListener(new ImageButton.OnTouchListener(){ 
           	public boolean onTouch(View view, MotionEvent arg1) {
           		
           		switch (arg1.getAction()) {
                  case MotionEvent.ACTION_DOWN:
                  	view.setBackgroundResource(interest_id_press[11]);
                      break;
                  case MotionEvent.ACTION_MOVE:
                  	view.setBackgroundResource(interest_id[11]); 
                      break;
                  case MotionEvent.ACTION_UP:
                  	view.setBackgroundResource(interest_id[11]); 
                  	break;
                  default:
                      break;
                  }
                  return false;
           	}	
           });
     	 interest_imageview[11].setOnClickListener( new OnClickListener(){      	
           	public void onClick(View view) {
            		getActivity().getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		interest_change.changeImage_interest(11);
            	}     	        	
           });
         
     	
	}
    public void adjustment_layout2() {
    	personal_setting_interest = (LinearLayout)view.findViewById(R.id.interest_all);
        DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		personal_setting_interest.setLayoutParams(new LinearLayout.LayoutParams(screenW,screenW*4/3));
	}

}
