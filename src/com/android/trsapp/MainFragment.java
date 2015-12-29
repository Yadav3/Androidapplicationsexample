package com.android.trsapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainFragment extends Fragment implements View.OnClickListener  {
	
	Button btn_about, btn_media, btn_social_feed, btn_jon, btn_donate,
			btn_contact_us;
	RelativeLayout rrr_about, rrr_media, rrr_social_feed, rrr_jon, rrr_donate,
			rrr_contact_us;
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.dashboard, container, false);
		
		rrr_about = (RelativeLayout)v.findViewById(R.id.rrr_about);
		rrr_media = (RelativeLayout)v.findViewById(R.id.rrr_media);
		rrr_social_feed = (RelativeLayout)v.findViewById(R.id.rrr_social_feed);
		rrr_jon = (RelativeLayout)v.findViewById(R.id.rrr_jon);
		rrr_donate = (RelativeLayout)v.findViewById(R.id.rrr_donate);
		rrr_contact_us = (RelativeLayout)v.findViewById(R.id.rrr_contact_us);
		
		btn_about = (Button)v.findViewById(R.id.btn_about);
		btn_media = (Button)v.findViewById(R.id.btn_media);
		btn_social_feed = (Button)v.findViewById(R.id.btn_social_feed);
		btn_jon = (Button)v.findViewById(R.id.btn_jon);
		btn_donate = (Button)v.findViewById(R.id.btn_donate); 
		btn_contact_us = (Button)v.findViewById(R.id.btn_contact_us);
		
		btn_about.setOnClickListener(this);
		btn_media.setOnClickListener(this);
		btn_social_feed.setOnClickListener(this);
		btn_jon.setOnClickListener(this);
		btn_donate.setOnClickListener(this);
		btn_contact_us.setOnClickListener(this); 
		 
		return v;
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()) {
        case R.id.btn_about:
//        	startActivity(new Intent(getActivity(),AboutActivity.class));
        	
        	Fragment newContent = new AboutFragment();
			FragmentManager fragmentManager1 = getFragmentManager();
			fragmentManager1.beginTransaction().add(R.id.frame_container, newContent).addToBackStack("null")
					.commit();
        	
          break;
        case R.id.btn_media:
//        	startActivity(new Intent(getActivity(),MediaActivity.class));
        	Fragment mediaf = new MediaFragment();
			FragmentManager mediafragmentManager = getFragmentManager();
			mediafragmentManager.beginTransaction().add(R.id.frame_container, mediaf).addToBackStack("null")
		 			.commit(); 
        	
          break;
        case R.id.btn_social_feed:
//        	startActivity(new Intent(getActivity(),SocialFeedsActvity.class)); 
        	Fragment SocialFeedsf = new SocialFeedsFragment();
			FragmentManager SocialFeedsffragmentManager = getFragmentManager();
			SocialFeedsffragmentManager.beginTransaction().add(R.id.frame_container, SocialFeedsf).addToBackStack("null")
		 			.commit(); 
            break;
        case R.id.btn_jon:
          
            break;
        case R.id.btn_donate:
          
            break;
        case R.id.btn_contact_us:
          
            break;
      }
	}
	
	/*private void switchFragment(Fragment fragment) {
		FragmentManager fragmentManager1 = MainActivity.this
				.getSupportFragmentManager();
		fragmentManager1.beginTransaction().replace(R.id.events_now, fragment)
				.commit();

	}*/
	
}
