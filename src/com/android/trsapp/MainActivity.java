package com.android.trsapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fragment); 
		
		
		if (savedInstanceState == null) {
			Fragment newContent = new MainFragment();
			switchFragment(newContent);
		}
	}
		// TODO Auto-generated method stub
		private void switchFragment(Fragment fragment) {
			FragmentManager fragmentManager1 = getFragmentManager();
			fragmentManager1.beginTransaction().add(R.id.frame_container, fragment)
					.commit();
			 
			/* Fragment myfragment;
			 myfragment = new FragmentOne();
			 
			 FragmentManager fm = getFragmentManager();
		     FragmentTransaction fragmentTransaction = fm.beginTransaction();
		     fragmentTransaction.replace(R.id.fragment_switch, myfragment);
		     fragmentTransaction.commit();*/

	}
	@Override
	public void onBackPressed() {
		super.onBackPressed(); 
//		MainActivity.this.finish();
//		moveTaskToBack(true);
	}
}
