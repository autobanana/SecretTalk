package com.example.secrettalk5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class MyFragmentPageAdapter extends FragmentPagerAdapter {

	public MyFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }
	
	public Fragment getItem(int position) {
		
		switch (position) {
         	case 0 :
         		return Talking_MyFragment.newInstance(position);
            case 1 :
                return Listening_MyFragment.newInstance(position);
            default :
            	return Setting_MyFragment.newInstance(position);
            }
    }

	
	public int getCount() {
		// TODO Auto-generated method stub
		return 3; //原始設定為0
	}

}
