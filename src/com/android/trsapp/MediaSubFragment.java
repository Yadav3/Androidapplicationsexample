package com.android.trsapp;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
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

public class MediaSubFragment extends Fragment {
	ListView about_listView;
	public static ArrayList<ItemsObj> media_list_array;
	Medias_ListAdapter medias_adapter;
	Utility util;
	Button btn_back, btn_home;
	TrsBean beanObj;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.about, container, false);

		util = new Utility(getActivity());

		beanObj = (TrsBean) getActivity().getApplicationContext();

		media_list_array = new ArrayList<ItemsObj>();
		about_listView = (ListView) v.findViewById(R.id.about_listView);
		btn_back = (Button) v.findViewById(R.id.btn_back);
		btn_home = (Button) v.findViewById(R.id.btn_home);
		btn_home.setText(beanObj.getStr_about_list_heading());
		media_list_array.clear();

		if (util.aboutDataArray().size() > 0) {
			for (int i = 0; i < util.aboutDataArray().size(); i++) {
				ItemsObj objItem = new ItemsObj();

				objItem.setStr_title(("" + util.aboutDataArray().get(i)));
				objItem.setStr_id(("" + i));
				objItem.setStr_image((""));

				media_list_array.add(objItem);
			}

			medias_adapter = new Medias_ListAdapter(getActivity(),
					R.layout.about_list_row, media_list_array);

			medias_adapter.notifyDataSetChanged();
			about_listView.setAdapter(medias_adapter);
			about_listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
				}
			});
		}

		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// startActivity(new Intent(MediaListActivity.this,
				// MediaActivity.class));
				// MediaListActivity.this.finish();
			}
		});

		return v;
	}

	public class Medias_ListAdapter extends ArrayAdapter<ItemsObj> {

		private Activity activity;
		private List<ItemsObj> items;
		@SuppressWarnings("unused")
		private ItemsObj objBean;
		ViewHolder holder;

		public Medias_ListAdapter(Activity act, int resource,
				List<ItemsObj> arrayList) {
			super(act, resource, arrayList);
			this.activity = act;
			// this.row = resource;
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
			TextView item_list_text, item_list_txtDec;
			ImageView item_list_icon;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			LayoutInflater inflator = activity.getLayoutInflater();
			objBean = items.get(position);
			if (convertView == null) {
				holder = new ViewHolder();

				convertView = inflator.inflate(R.layout.about_list_row, null);

				holder.item_list_text = (TextView) convertView
						.findViewById(R.id.item_list_text);

				holder.item_list_txtDec = (TextView) convertView
						.findViewById(R.id.item_list_txtDec);

				holder.item_list_icon = (ImageView) convertView
						.findViewById(R.id.item_list_icon);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			/*
			 * holder.txt_category.setText(objBean.getStr_title().replace("null",
			 * ""));
			 */

			return convertView;
		}

	}
}
