package com.example.secrettalk5;


import java.util.HashMap;

import org.json.JSONObject;

import com.example.articlemodule.ArticleModule;
import com.example.usermodule.UserInformation;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends  FragmentActivity{

	public ViewPager mViewPager_main;
	public ImageView cursor;
	public TextView t1, t2, t3;
	public int offset = 0;
	public int currIndex = 0;
	public int bmpW;
    public int one,two;
    private static final int A=1,B=2,C=3,D=4,E=5;
	
	public MyFragmentPageAdapter mAdapter_main;
    public ListView myListview;
    public LinearLayout viewPager_layout;
    public DrawerLayout drawerLayout;  
    //public List<String> list;  
    public CharSequence title="";  
    public CharSequence mTitle;
    public CharSequence mDrawerTitle;
    public ActionBarDrawerToggle drawerToggle;
    public ArrayAdapter<String> adapter;
    public ActionBarDrawerToggle mDrawerToggle;
    private static final String[] viewpagertitle = new String[]{"說-Talking","聽-Listening","你 ? Who am I ?"};
    private static final String[] mStrings = new String[] {"回到首頁","偏好設定", "程式導覽", "成就系統介紹", "公告","關於本程式","登出", "結束程式"};
    private static final String[] mStrings_NoPreference = new String[] {"回到首頁", "程式導覽", "成就系統介紹","公告", "關於本程式","登出", "結束程式"};
        
    private ConnectionDetector cd;
    

    private boolean doublleBackCheck = false;
    
    public Talking_MyFragment TF;
    public Listening_MyFragment LF;

    //testforbutton 
    SpannableString viewpager_s1,viewpager_s2,viewpager_s3;
    SpannableString viewpager_s1_press,viewpager_s2_press,viewpager_s3_press;
    
    //開場動畫
    public DrawerLayout layouta;
	public int check;
    public ActionBar actionBar2;
	
    //ListView中的各個Fragmet宣告 用以幫助remove
    public AboutProgram_Fragment aboutprogram; //關於本程式
    public PreferenceSetting_Fragment preferencesetting; //偏好設定
    public Navigation_Fragment navigation;
    public Achievement_Fragment achievement;
    public SignOut_Fragment signout;
    public Bulletinboard_Fragment bulletinboard;
    
    
    public void onCreate(Bundle savedInstanceState) {
        
        
    	
    	super.onCreate(savedInstanceState);
    	
    	Log.d("TTTTTTTTTT","onCreate");
    	
    	setTheme(R.style.CustomActionBarTheme);
        setContentView(R.layout.fragment_main2);
        
        //fragment_main2 首頁
        myListview = (ListView)findViewById(R.id.listview_main);  
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_main);
        mViewPager_main = (ViewPager) findViewById(R.id.vPager_main);
        mAdapter_main = new MyFragmentPageAdapter(getSupportFragmentManager());
        mViewPager_main.setAdapter(mAdapter_main);
        
        //mAdapter_main.notifyDataSetChanged();  ///
        mViewPager_main.setOnPageChangeListener(new MyOnPageChangeListener());
        viewPager_layout = (LinearLayout)findViewById(R.id.L_main);
        
        //連線alert
        cd = new ConnectionDetector(this);
        cd.showConnction();

        /**初始化**/
        initial_TextView();
        initial_ImageView();
        initial_PageView();
        initial_ListView();
        fragment_handle();
        ShowBulletinBoard();
        
        t3.performClick();  //直接進入設定頁
        
        
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION); // hide nav bar
        Toast.makeText(this, "哈囉!"+",可以到『設定』修改設定喔~!",Toast.LENGTH_LONG).show();;
        //initial_Preference(); 保留 for偏好設定
    
    }

    public  void fragment_handle() {
    	getSupportFragmentManager().addOnBackStackChangedListener(new OnBackStackChangedListener() {    
            public void onBackStackChanged() {

            	doublleBackCheck = false;
            	
                int backCount = getSupportFragmentManager().getBackStackEntryCount();
                if (backCount == 0){
                	ViewPager_Show();
    	           	title = "SecretTalk"; 	// block where back has been pressed. since backstack is zero.
                }
                else{
	             	title = getSupportFragmentManager().getBackStackEntryAt(backCount-1).getBreadCrumbShortTitle();
                }
                getActionBar().setTitle(title); 
            }
        });
	}

	public void RefreshButton_Initial(){
    	
        
    	if(currIndex == 0){
    		mAdapter_main = new MyFragmentPageAdapter(getSupportFragmentManager());
    		mAdapter_main.notifyDataSetChanged();
            mViewPager_main.setAdapter(mAdapter_main);
    	}
    	else if(currIndex==1){
    		t1.performClick();
    		mAdapter_main = new MyFragmentPageAdapter(getSupportFragmentManager());
    		mAdapter_main.notifyDataSetChanged();
    		mViewPager_main.setAdapter(mAdapter_main);
    	}
    	else{
    		t1.performClick();
    		mAdapter_main = new MyFragmentPageAdapter(getSupportFragmentManager());
    		mAdapter_main.notifyDataSetChanged();
    		mViewPager_main.setAdapter(mAdapter_main);
    	}
    	
    	
    }


	//當街到notification後會直接進入 並跳轉調正確的頁面
    public void onResume() {   
        Bundle bundle = this.getIntent().getExtras();
        if( bundle != null) {
            int n = bundle.getInt("viewpager_num");
            mViewPager_main.setCurrentItem(n);
            
        }       
               
        super.onResume();
        Log.d("TTTTTTTTTT","onResume");
    }
    
  	public boolean onCreateOptionsMenu(Menu menu) {
  		getMenuInflater().inflate(R.menu.main, menu);
  		return true;
  	}
  	private void ShowBulletinBoard() {
		Builder MyAlertDialog = new AlertDialog.Builder(this);
		MyAlertDialog.setTitle("SecretTalk 公告");
		MyAlertDialog.setMessage("小魏內容給你放喔~~ 滴ShowBulletinBoard嘉 ~~~");
		DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		};
		;
		MyAlertDialog.setNeutralButton("確定", OkClick);
		MyAlertDialog.show();

	}
//    public void initial_ListView() {
//		    	
//    	
//    	myListview.setAdapter(new ListviewAdapter(this));
//    	//myListview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
//    	drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);
//        mDrawerToggle = new ActionBarDrawerToggle(
//                this,                  /* host Activity */
//                drawerLayout,         /* DrawerLayout object */
//                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
//                R.string.drawer_open,  /* "open drawer" description */
//                R.string.drawer_close  /* "close drawer" description */
//                ) {
//
//            /** Called when a drawer has settled in a completely closed state. */
//            public void onDrawerClosed(View view) {
//            	
//            	super .onDrawerClosed(view);  
//                getActionBar().setTitle(title);  
//                invalidateOptionsMenu();  
//            }
//
//            /** Called when a drawer has settled in a completely open state. */
//            public void onDrawerOpened(View drawerView) {
//            	super .onDrawerOpened(drawerView);  
//                invalidateOptionsMenu();
//            }
//        };
//
//        // Set the drawer toggle as the DrawerListener
//        drawerLayout.setDrawerListener(mDrawerToggle);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
//        
//        myListview.setOnItemClickListener( new  AdapterView.OnItemClickListener() {  
//            @Override  
//            public  void  onItemClick(AdapterView<?> adapterView, View view,  int  position,  long  l) {  
//                
//            	cd.showConnction();
//            	
//            	if(position == 0){
//            		//回到首頁
//            		//清空所有新加入的fragment
//            		ViewPager_Show();
//            		getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            		title = "SecretTalk";
//            	}
//            	else if(position == 1){
//            		//偏好設定
//            		//getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            		ViewPager_Hide();
//            		preferencesetting =  new  PreferenceSetting_Fragment();  
//                    FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
//                    transaction1.addToBackStack(null);   //保留先前的Fragment
//                    transaction1.add(R.id.drawer_layout_second, preferencesetting).commit();
//                    title = mStrings[position];
//            	
//            	}
//            	else if(position == 2){
//            		ViewPager_Hide();
//            		navigation = new Navigation_Fragment();
//            		FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
//                    transaction2.addToBackStack(null);   //保留先前的Fragment
//                    transaction2.add(R.id.drawer_layout_second, navigation).commit();
//                    title = mStrings[position]; 
//            	}
//            	else if(position ==3){
//            		//成就系統介紹		
//            		ViewPager_Hide();
//            		achievement = new Achievement_Fragment();
//            		FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
//                    transaction3.addToBackStack(null);   //保留先前的Fragment
//                    transaction3.add(R.id.drawer_layout_second, achievement).commit();
//                    title = mStrings[position];
//                    
//            	}
//            	else if(position == 4){
//            		//關於本程式
//            		ViewPager_Hide();
//            		aboutprogram =  new  AboutProgram_Fragment();  
//                    FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
//                    transaction4.addToBackStack(null);   //保留先前的Fragment
//                    transaction4.add(R.id.drawer_layout_second, aboutprogram).commit();
//                    title = mStrings[position];
//            	
//            	}
//            	else if(position == 5){
//            		ViewPager_Hide();
//            		signout =  new  SignOut_Fragment();  
//                    FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
//                    transaction4.addToBackStack(null);   //保留先前的Fragment
//                    transaction4.add(R.id.drawer_layout_second, signout).commit();
//                    title = mStrings[position];
//            	}
//            	else{
//            		//離開程式	
//            		Leave_program();
//            	}
//            	myListview.setItemChecked(position, true );  
//                drawerLayout.closeDrawer(myListview);  
//                
//            }
//
//        });  
//	}
  	
    public void initial_ListView() {
		    	
    	
    	myListview.setAdapter(new ListviewAdapter(this));
    	//myListview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
    	drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
            	
            	super .onDrawerClosed(view);  
                
                invalidateOptionsMenu();  
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
            	super .onDrawerOpened(drawerView);  
                invalidateOptionsMenu();
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(mDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        myListview.setOnItemClickListener( new  AdapterView.OnItemClickListener() {  
            @Override  
            public  void  onItemClick(AdapterView<?> adapterView, View view,  int  position,  long  l) {  
                
            	Fragment addFragment = new Fragment() ;
            	
            	cd.showConnction();
            	
            	if(position == 0){
            		//回到首頁
            		//清空所有新加入的fragment
            		ViewPager_Show();
            		getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
            		title = "SecretTalk";
            	}else{
            	            		
	            	if(position == 1){
	            		//程式導覽
	            		addFragment = new Navigation_Fragment();
	               	}
	            	else if(position == 2){
	            		//成就系統介紹		
	            		addFragment = new Achievement_Fragment();   
	            	}
	            	else if(position == 3){
	            		//公告
	            		addFragment = new Bulletinboard_Fragment();
	            	}
	            	else if(position ==4){
	            		//關於本程式
	            		addFragment =  new  AboutProgram_Fragment();  
	            	}
	            	else if(position == 5){
	            		//登出
	            		addFragment =  new  SignOut_Fragment();  
	            	}
	            	else {
	            		//離開程式	
	            		Leave_program();
	            	}
	            	
	            	title = mStrings_NoPreference[position];
	            	
	            	ViewPager_Hide();
	            	FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	            	transaction.addToBackStack(null);   //保留先前的Fragment
                    transaction.add(R.id.drawer_layout_second, addFragment).commit();
                    transaction.setBreadCrumbShortTitle(title);       
	            	 
            	}
            	            	
//            	Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            	myListview.setItemChecked(position, true );  
                drawerLayout.closeDrawer(myListview);  
                
            }

        });  
	}

 	
  	
	public void ViewPager_Hide() {
		viewPager_layout.setVisibility(View.INVISIBLE);
	
	}
	public void ViewPager_Show(){
		viewPager_layout.setVisibility(View.VISIBLE);
	}

	public void Leave_program() {
		
    	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setMessage(R.string.leavingprogram).setTitle(R.string.leave);
		builder.setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	       		ViewPager_Show();
	    		getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
	    		getActionBar().setTitle("SecretTalk");  
	           }
	       }).
		setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	        	   finish();
	           }
	       }).show();
		//AlertDialog dialog = builder.create();
    	
	}  
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(myListview);
        //menu.findItem(R.id.action_edit).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
    	Context context = getApplicationContext();
    	//CharSequence title = item.getTitle();
    	if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
    	if (item.getItemId() == R.id.action_home) {
//            Toast.makeText(context, "action_edit", Toast.LENGTH_SHORT).show();
    		ViewPager_Show();
    		getSupportFragmentManager().popBackStack( null , FragmentManager.POP_BACK_STACK_INCLUSIVE);
    		getActionBar().setTitle("SecretTalk");  
            return true;
        }
    	if (item.getItemId() == R.id.action_refresh) {
    		
    		//Toast.makeText(this, "do something", Toast.LENGTH_LONG).show();
    		RefreshButton_Initial();
    	}
    	
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

	public void initial_PageView() {
		// TODO Auto-generated method stub
		
	}

	//TextView 在本layout為Button用
	public void initial_TextView() {  
		// TODO Auto-generated method stub
		int n;
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int SW = dm.widthPixels;
		Log.d("PPPPPPPPPPPPPPPPPPPPPPPPP", "SW = "+SW);
		t1 = (TextView) findViewById(R.id.content_talking);
		t2 = (TextView) findViewById(R.id.Time);
		t3 = (TextView) findViewById(R.id.about_version);

		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1));
		t3.setOnClickListener(new MyOnClickListener(2));
		
		
		
		//###########  t1
		int NewW1 = SW*92/480;
		int NewH1 = NewW1*50/92;
		Log.d("PPPPPPPPPPPPPPPPPPPPPPPPP", "NewW1 = "+NewW1);
		viewpager_s1 = new SpannableString("abc");
		Drawable img = getResources().getDrawable(R.drawable.viewpager_talking2);
		img.setBounds(0, 0, NewW1, NewH1);
		//img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
		viewpager_s1.setSpan(new ImageSpan(img, ImageSpan.ALIGN_BASELINE), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		
		viewpager_s1_press = new SpannableString("abc");
		Drawable img1_1 = getResources().getDrawable(R.drawable.viewpager_talking_press2);
		img1_1.setBounds(0, 0, NewW1, NewH1);
		//img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
		viewpager_s1_press.setSpan(new ImageSpan(img1_1, ImageSpan.ALIGN_BASELINE), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		
		t1.setText(viewpager_s1_press); 
		
		//###########  t2
		int NewW2 = SW*90/480;
		int NewH2 = NewW2*57/90;
		
		viewpager_s2 = new SpannableString("abc");
		Drawable img2 = getResources().getDrawable(R.drawable.viewpager_listening2);
		img2.setBounds(0, 0, NewW2, NewH2);
		//img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
		viewpager_s2.setSpan(new ImageSpan(img2, ImageSpan.ALIGN_BASELINE), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		
		viewpager_s2_press = new SpannableString("abc");
		Drawable img2_1 = getResources().getDrawable(R.drawable.viewpager_listening_press2);
		img2_1.setBounds(0, 0, NewW2, NewH2);
		//img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
		viewpager_s2_press.setSpan(new ImageSpan(img2_1, ImageSpan.ALIGN_BASELINE), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		
		t2.setText(viewpager_s2);
		
		//###########  t3
		int NewW3 = SW*95/480;
		int NewH3 = NewW3*53/95;
		
		viewpager_s3 = new SpannableString("abc");
		Drawable img3 = getResources().getDrawable(R.drawable.viewpager_setting);
		img3.setBounds(0, 0, NewW3, NewH3);
		//img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
		viewpager_s3.setSpan(new ImageSpan(img3, ImageSpan.ALIGN_BASELINE), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		
		viewpager_s3_press = new SpannableString("abc");
		Drawable img3_1 = getResources().getDrawable(R.drawable.viewpager_settinga_press);
		img3_1.setBounds(0, 0, NewW3, NewH3);
		//img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
		viewpager_s3_press.setSpan(new ImageSpan(img3_1, ImageSpan.ALIGN_BASELINE), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

		t3.setText(viewpager_s3);
		
		
	}
	private class MyOnClickListener implements View.OnClickListener {
		private int index = 2;
		
		public MyOnClickListener(int i) {
			index = i;
		}		
		public void onClick(View v) {
			title = viewpagertitle[index];
			mViewPager_main.setCurrentItem(index);
		}
	}

	public void initial_ImageView() {
		//mViewPager_main.setCurrentItem(0) ;
		//mViewPager_main.setOnPageChangeListener(new MyOnPageChangeListener());
		
		cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.slide)
				.getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / 3 - bmpW) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);
		one = offset * 2 + bmpW;
		two = one * 2;
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {		
		public void onPageSelected(int arg0) {
								
			cd.showConnction();
			doublleBackCheck = false;
			
			Animation animation = null;
			
			title = viewpagertitle[arg0];
			
			switch (arg0) {
			case 0:
				//t1.setTextColor(0x00000000);
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					t1.setText(viewpager_s1_press); 
					t2.setText(viewpager_s2);
					//mAdapter_main = new MyFragmentPageAdapter(getSupportFragmentManager());
					//mAdapter_main.notifyDataSetChanged();
			        //mViewPager_main.setAdapter(mAdapter_main);
			        
				
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					t1.setText(viewpager_s1_press); 
					t3.setText(viewpager_s3);
				}
				break;
			case 1:
				//t2.setTextColor(0x00000000);
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset,one, 0, 0);
					t1.setText(viewpager_s1);
					t2.setText(viewpager_s2_press);
					
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					t3.setText(viewpager_s3);
					t2.setText(viewpager_s2_press);
				}
				break;
			case 2:
				//t3.setTextColor(0x00000000);
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
					t1.setText(viewpager_s1);
					t3.setText(viewpager_s3_press);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					t2.setText(viewpager_s2);
					t3.setText(viewpager_s3_press);
				}
				break;
			}
			//t1.setTextColor(0x00000000);
			//currIndex2 = arg0;
			currIndex = arg0;
			animation.setFillAfter(true);
			getActionBar().setTitle(title);  
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	public void onStart()
    {
        super.onStart();
        Log.d("TTTTTTTTTT","onStart");
    }
    
    public void onPause()
    {
        super.onPause();
        Log.d("TTTTTTTTTT","onPause");
    }
    
    public void onStop()
    {
        super.onStop();
        Log.d("TTTTTTTTTT","onStop");
    }
    
    public void onRestart()
    {
        super.onRestart();
        Log.d("TTTTTTTTTT","onReStart");
    }
    public void onDestroy()
    {
        super.onDestroy();
        Log.d("TTTTTTTTTT","onDestroy");
    }
	

	@Override
	public void onBackPressed() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
			if (doublleBackCheck == true){
				super.onBackPressed();
			}else{
				
			Toast.makeText(this, "請在按一次 back 離開此程式", Toast.LENGTH_LONG).show();;
			doublleBackCheck = true;
			}
		}
		else{
			super.onBackPressed();
		}
	}

}
	
