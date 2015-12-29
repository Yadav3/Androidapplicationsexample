package com.android.trsapp.appbean;

import java.util.ArrayList;

import android.app.Application;

import com.android.trsapp.obj.ItemsObj;

public class TrsBean extends Application{
	  
	String str_list_title = "", str_list_id = "", str_list_image = "",
			str_list_description = "", str_about_list_heading = "";
	
	public String getStr_about_list_heading() {
		return str_about_list_heading;
	}

	public void setStr_about_list_heading(String str_about_list_heading) {
		this.str_about_list_heading = str_about_list_heading;
	}

	ArrayList<ItemsObj> facebook_obj;
	   
	
	public ArrayList<ItemsObj> getFacebook_obj() {
		return facebook_obj;
	}

	public void setFacebook_obj(ArrayList<ItemsObj> facebook_obj) {
		this.facebook_obj = facebook_obj;
	}

	public String getStr_list_title() {
		return str_list_title;
	}

	public void setStr_list_title(String str_list_title) {
		this.str_list_title = str_list_title;
	}

	public String getStr_list_id() {
		return str_list_id;
	}

	public void setStr_list_id(String str_list_id) {
		this.str_list_id = str_list_id;
	}

	public String getStr_list_image() {
		return str_list_image;
	}

	public void setStr_list_image(String str_list_image) {
		this.str_list_image = str_list_image;
	}

	public String getStr_list_description() {
		return str_list_description;
	}

	public void setStr_list_description(String str_list_description) {
		this.str_list_description = str_list_description;
	}
	
}
