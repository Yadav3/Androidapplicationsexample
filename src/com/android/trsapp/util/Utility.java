package com.android.trsapp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@SuppressLint("SimpleDateFormat")
public class Utility {
	public Context mContext;
	public static ArrayList<String> about_array = new ArrayList<String>();
	public static ArrayList<String> media_array = new ArrayList<String>();
	public Utility(Context context) {
		mContext = context;
	}
	String baseUrl1 = "http://enowdev.etg.net/Webservices2/";
	String baseUrl2 = "http://enowdev.etg.net/WebServices3/";

	public boolean IsNetConnected(Context context) {
		boolean isConnected = false;
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++) {

					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						isConnected = true;
					}
				}
		}
		System.out.println("Internet Connection is: " + isConnected);
		return isConnected;
	}

	
	public void showAlertNoInternetService(Context context) {
		AlertDialog.Builder altDialog = new AlertDialog.Builder(context);
		altDialog
				.setMessage("Sorry, Network is not available. Please try again later"); 
		
		altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		altDialog.show();
	}

	


@SuppressLint("SimpleDateFormat")
public Boolean current_dat(String Lst_updated,String Current_date_tme)
{ 
Boolean session_tim=false;
	try {
//		String lst_updated = "08/19/2015 11:5:00";
		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
 
		Date lst_updated_time = null;
		Date current_date_tme = null;
		
		@SuppressWarnings("unused")
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//		String current_date_time = df.format(Calendar.getInstance().getTime());
		current_date_tme = format.parse(Current_date_tme);
		
		lst_updated_time = format.parse(Lst_updated);

		//in milliseconds
		long diff = current_date_tme.getTime() - lst_updated_time.getTime();
		
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

	
		
//		System.out.print( date + " date,\n ");
//		
//		System.out.print(c.getTime() + " time, ");
		System.out.print(diffDays + "days, \n");
		System.out.print(diffHours + "hours,\n");
		System.out.print(diffMinutes + "minutes\n");
		System.out.print(diffSeconds + "seconds.");
		
		/* if (diffDays <=0) {
			 
			 if(diffHours<=0)
			 {
				 System.err.println("Difference in number of days (2) : " + diffDays);
		            if (diffMinutes <=5) {
		            	session_tim=true;
		            	System.err.println(diffMinutes+"if");
		            }
		            else
		            {
		            	session_tim=false;
		            	System.err.println(diffMinutes+"else");
		            }
			 }
			 else
			 {
				 session_tim=false;
			 }
	           
	        } else {
	        	session_tim=false;
	            System.err.println(">24");
	        } */
		
		if (diffDays <=0) {
			 System.err.println(diffDays+" diffDays if");
			 if(diffHours>=0)
			 {
				 System.err.println(diffHours+" diffHours if");
		            if (diffMinutes <=3) {
		            	session_tim=true;
		            	System.err.println(diffMinutes+" diffMinutes if");
		            }
		            else
		            {
		            	session_tim=false;
		            	System.err.println(diffMinutes+" diffMinutes else");
		            }
			 }
			 else
			 {
				 System.err.println(diffHours+" diffHours else");
				 session_tim=false;
			 }
	           
	        } else {
	        	session_tim=false;
	            System.err.println(diffDays+"  diffDays else");
	        } 
 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return session_tim;
	
}



 
	public void inputValidation(String sss) {
		AlertDialog.Builder altDialog = new AlertDialog.Builder(mContext);
		 altDialog.setMessage(sss); 
		altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		altDialog.show();
	}

	public void dialogExample() {
		/*
		 * AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		 * builder.setMessage("No data from server!"); builder.show();
		 */

		AlertDialog.Builder altDialog = new AlertDialog.Builder(mContext);
		altDialog.setMessage("No data found in server!"); // here add your message
		altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		altDialog.show();
	}
	// validating email id
			public boolean isValidEmail(String email) {
				String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

				Pattern pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher matcher = pattern.matcher(email);
				return matcher.matches();
			}
			
			public ArrayList<String> aboutDataArray() {
				about_array.clear();
				about_array.add("History");
				about_array.add("Timeline");
				about_array.add("Leadership"); 
				return about_array;
			}
			
			public ArrayList<String> mediaDataArray() {
				media_array.clear();
				media_array.add("News");
				media_array.add("Photo gallery");
				media_array.add("Video gallery"); 
				return media_array;
			}
}
