package com.android.trsapp;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.trsapp.appbean.TrsBean;
import com.android.trsapp.obj.ItemsObj;
import com.android.trsapp.util.Utility;

public class MediaFragment extends Fragment {
	ListView about_listView;
	public static ArrayList<ItemsObj> media_list_array;
	Media_ListAdapter media_adapter;
	Utility util;
	Button btn_back,btn_home;
	TrsBean beanObj;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.about, container, false); 
		
		util = new Utility(getActivity());
		beanObj = (TrsBean)getActivity().getApplicationContext();
		media_list_array = new ArrayList<ItemsObj>();
		about_listView = (ListView)v. findViewById(R.id.about_listView);
		btn_back = (Button) v.findViewById(R.id.btn_back);
		btn_home = (Button)v. findViewById(R.id.btn_home);
		btn_home.setText(R.string.str_media); 
		media_list_array.clear();

		if (util.mediaDataArray().size() > 0) {
			for (int i = 0; i < util.mediaDataArray().size(); i++) {
				ItemsObj objItem = new ItemsObj();

				objItem.setStr_title(("" + util.mediaDataArray().get(i)));
				objItem.setStr_id(("" + i));
				objItem.setStr_image((""));

				media_list_array.add(objItem);
			}
 
			media_adapter = new Media_ListAdapter(getActivity(),
					R.layout.item_layout, media_list_array);

			media_adapter.notifyDataSetChanged();
			about_listView.setAdapter(media_adapter);
			
			about_listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int pos, long arg3) {
					// TODO Auto-generated method stub
					if(util.mediaDataArray().get(pos).equalsIgnoreCase("News"))
					{
						beanObj.setStr_about_list_heading("News");
					}
					if(util.mediaDataArray().get(pos).equalsIgnoreCase("Photo gallery"))
					{
						beanObj.setStr_about_list_heading("Photo gallery");
					}
					if(util.mediaDataArray().get(pos).equalsIgnoreCase("Video gallery"))
					{
						beanObj.setStr_about_list_heading("Video gallery"); 
					}
//					startActivity(new Intent(MediaActivity.this, MediaListActivity.class)); 
					
					Fragment newContent = new MediaSubFragment(); 
					FragmentManager fragmentManager1 = getFragmentManager();
					fragmentManager1.beginTransaction().add(R.id.frame_container, newContent).addToBackStack("null")
							.commit();
				}
			}); 
		} 

		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				startActivity(new Intent(MediaActivity.this, DashBoardActivity.class));
//				MediaActivity.this.finish();
			}
		});
		
		return v;
	}
	
	
	public class Media_ListAdapter extends ArrayAdapter<ItemsObj> {

		private Activity activity;
		private List<ItemsObj> items;
		private ItemsObj objBean;
		ViewHolder holder;

		public Media_ListAdapter(Activity act, int resource,
				List<ItemsObj> arrayList) {
			super(act, resource, arrayList);
			this.activity = act;
//			this.row = resource;
			this.items = arrayList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.size();
		}

		@Override
		public ItemsObj getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public class ViewHolder {
			TextView txt_category;
			ImageView img_item_icon;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			LayoutInflater inflator = activity.getLayoutInflater();
			objBean = items.get(position);
			if (convertView == null) {
				holder = new ViewHolder();

				convertView = inflator.inflate(R.layout.item_layout, null);

				holder.txt_category = (TextView) convertView
						.findViewById(R.id.category);

				holder.img_item_icon = (ImageView) convertView
						.findViewById(R.id.item_icon);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.txt_category.setText(objBean.getStr_title().replace("null",
					""));

			return convertView;
		}

	}
}
