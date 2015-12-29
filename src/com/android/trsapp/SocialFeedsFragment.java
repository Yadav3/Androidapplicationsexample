package com.android.trsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.trsapp.appbean.TrsBean;
import com.android.trsapp.obj.ItemsObj;
import com.android.trsapp.util.Utility;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

@SuppressWarnings("deprecation")
public class SocialFeedsFragment extends Fragment {
	ListView social_feeds_facebook_listView,social_feeds_twitter_listView;
	public static ArrayList<ItemsObj> social_feesds_list_array;
	SocialFeeds_ListAdapter social_feeds_adapter;
	Utility util;
	Button btn_back,btn_home,btn_face_book,btn_twitter;
	String APP_ID = "352657494904748";
	String APP_SECRET = "8b0d5328657a65f80fd7f69258d6df0f";
	String OWNER_OF_FEED = "trspartyonline";
	RelativeLayout rrr_face_book,rrr_twitter;
	
	final static String SearchTerm = "trspartyonline";
	final static String LOG_TAG = "rnc";
	String Key = null;
	String Secret = null;
	String imgUrl="http://abs.twimg.com//images//themes//theme7//bg.gif";
	ArrayList<String> headlist=new ArrayList<String>();
	ArrayList<String> timelist=new ArrayList<String>();
	ArrayList<String> desclist=new ArrayList<String>();
	ArrayList<String> imageslist=new ArrayList<String>();
	CustomList adapter;
	TrsBean trsBean;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.social_feeds, container, false); 
		

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Key = "fZRZJcNrFbg55dmr4rLVA";
		Secret = "Xbxmn2hlT91mzydcmbnGRqOIYg8Ohipd9F8HfQYhHg";
		
		
		util = new Utility(getActivity());
//		trsBean = (TrsBean) getApplicationContext();
		
		social_feesds_list_array = new ArrayList<ItemsObj>();
		social_feeds_facebook_listView = (ListView)v. findViewById(R.id.social_feeds_facebook_listView);
		social_feeds_twitter_listView = (ListView) v.findViewById(R.id.social_feeds_twitter_listView);
		btn_back = (Button) v.findViewById(R.id.btn_back);
		btn_home = (Button) v.findViewById(R.id.btn_home);
		btn_home.setText(R.string.str_social_feeds); 
		
		btn_face_book = (Button) v.findViewById(R.id.btn_face_book);
		btn_twitter = (Button) v.findViewById(R.id.btn_twitter);
		
		rrr_face_book = (RelativeLayout) v.findViewById(R.id.rrr_face_book);
		rrr_twitter = (RelativeLayout) v.findViewById(R.id.rrr_twitter);
 
		social_feesds_list_array.clear();
		new FacebookFeeds().execute();
		
		social_feeds_facebook_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				ItemsObj itemsObj = social_feesds_list_array.get(arg2);
//				pack_title = itemsObj.getStr_dec();
				 
				final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
				// android.R.style.Theme_Translucent_NoTitleBar);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.readmores);

				TextView detail_texs =(TextView)dialog.findViewById(R.id.detail_tex);
				 Button closeBtN = (Button) dialog.findViewById(R.id.closeBtn);
				detail_texs
				.setText(Html
						.fromHtml("<html><body>"+itemsObj.getStr_dec()+"</body></html>"));
				 
				ImageView i_icon =(ImageView)dialog.findViewById(R.id.i_icon);
				if(itemsObj.getStr_picture().replace("null", "").length()>0)
				{
					Picasso.with(getActivity().getApplicationContext())
					.load(itemsObj.getStr_picture().replace("\'", "%20").replace(" ", "%20").trim())
					.placeholder(R.drawable.ic_launcher)
					.error(R.drawable.ic_launcher).fit().into(i_icon);   
				}
				
				
				
				
				closeBtN.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
				
				dialog.show();
			}
		});
//		textView = (TextView) findViewById(R.id.text_view);
	
		
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
//				startActivity(new Intent(SocialFeedsActvity.this,
//						DashBoardActivity.class));
//				SocialFeedsActvity.this.finish();
				
				FragmentManager fm = getFragmentManager();
			    if (fm.getBackStackEntryCount() > 0) {
			        Log.i("MainActivity", "popping backstack");
			        fm.popBackStack();
			    } else {
			        Log.i("MainActivity", "nothing on backstack, calling super");
//			        super.onBackPressed();  
			    }
			}
		});
		
		btn_face_book.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				social_feeds_facebook_listView.setVisibility(View.VISIBLE);
				social_feeds_twitter_listView.setVisibility(View.GONE);
				
				rrr_face_book.setBackgroundColor(0);
				rrr_twitter.setBackgroundColor(0);
//				btn_face_book.setBackgroundColor(R.color.white_txt);
				rrr_face_book.setBackgroundColor(Color.WHITE);
				  
				rrr_twitter.setBackgroundColor(Color.parseColor("#DFDFDF"));
//				btn_twitter.setBackgroundColor(R.color.light_gray); 
			}
		});
		
		btn_twitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				downloadSearches();
				social_feeds_facebook_listView.setVisibility(View.GONE);
				social_feeds_twitter_listView.setVisibility(View.VISIBLE); 
				
				rrr_face_book.setBackgroundColor(0);
				rrr_twitter.setBackgroundColor(0);
				
				rrr_twitter.setBackgroundColor(Color.WHITE);
//				rrr_twitter.setBackgroundResource(R.color.white_txt); 
//				btn_twitter.setBackgroundResource(R.color.white_txt);
				 
//				btn_face_book.setBackgroundColor(R.color.light_gray);
				rrr_face_book.setBackgroundColor(Color.parseColor("#DFDFDF"));
			}
		});
		 
		return v; 
	}
	
	// download twitter searches after first checking to see if there is a network connection
		public void downloadSearches() {
			ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

			if (networkInfo != null && networkInfo.isConnected()) {
				new DownloadTwitterTask().execute(SearchTerm);
			} else {
				Log.v(LOG_TAG, "No network connection available.");
			}
		}  
		
		
		// Uses an AsyncTask to download data from Twitter
				private class DownloadTwitterTask extends AsyncTask<String, Void, String> {
					final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
					final static String TwitterSearchURL = "https://api.twitter.com/1.1/search/tweets.json?q=";

					private ProgressDialog progressDialog;

					@Override
					protected void onPreExecute() {

						super.onPreExecute(); 
						progressDialog = new ProgressDialog(getActivity());
						progressDialog.setMessage("Loading ...");
						progressDialog.setIndeterminate(false);
						progressDialog.setCancelable(false);
						progressDialog.show();

					}
					
					@Override
					protected String doInBackground(String... searchTerms) {
						String result = null;

						if (searchTerms.length > 0) {
							result = getSearchStream("trspartyonline");
//							result = getSearchStream("Telangana");
						}
						return result;
					}

					// onPostExecute convert the JSON results into a Twitter object (which is an Array list of tweets
					@SuppressWarnings("unused")
					@Override
					protected void onPostExecute(String result) {
						
						if (null == result || result.length() == 0
								|| TextUtils.isEmpty(result)) {

							util.dialogExample();

						} else {
							
							try {
								
								Searches searches = jsonToSearches(result);

								// lets write the results to the console as well
								for (Search search : searches) {
									Log.i(LOG_TAG, search.getText());
								}

								// send the tweets to the adapter for rendering
								
								System.out.println("result"+result);
								System.out.println("search"+searches);
								
								
								JSONObject objec=new JSONObject(result);
								JSONArray array=objec.optJSONArray("statuses");
								
								System.out.println("arraylength"+array.length());
								for(int i=0;i<array.length();i++)
								{
								JSONObject obj= array.getJSONObject(i);
									
								String str1=obj.getString("text");
//								headlist.add(str1);
								
								String str=obj.getString("created_at");
								
								
								
								/*if(str1.contains("RT @"))
								{
									String[] parts = str1.split(":");
//									String part1 = parts[0]; 
									timelist.add(parts[1]); 
								}
								else
								{*/
								timelist.add(str1); 
//								}
//								String strr=new MainActivity().getTime(str);
								
//								timelist.add(strr);
								
//								String str3=obj.getString("source");
//								desclist.add(str3);
								
								JSONObject userobj=obj.getJSONObject("user");
								
								String str4=userobj.getString("name");
								headlist.add(str4);
								
								String str5=userobj.getString("profile_image_url_https");
								imageslist.add(str5);

								String str6=userobj.getString("description");
								desclist.add(str6);
//								String str4=obj.getString("profile_image_url_https");
//								imageslist.add(str4);
								}
								System.out.println("imageurl"+imageslist.size());
								 adapter=new CustomList(getActivity(), headlist, imageslist, timelist,desclist);
								social_feeds_twitter_listView.setAdapter(adapter);
								if (null != progressDialog && progressDialog.isShowing()) {
									progressDialog.dismiss();
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								progressDialog.dismiss();
								e.printStackTrace();
							}
						}
						
//						ArrayAdapter<Search> adapter = new ArrayAdapter<Search>(activity, android.R.layout.simple_list_item_1, searches);
//						setListAdapter(adapter);
					}

					// converts a string of JSON data into a SearchResults object
					private Searches jsonToSearches(String result) {
						Searches searches = null;
						if (result != null && result.length() > 0) {
							try {
								Gson gson = new Gson();
								// bring back the entire search object
								SearchResults sr = gson.fromJson(result, SearchResults.class);
								// but only pass the list of tweets found (called statuses)
								searches = sr.getStatuses();
							} catch (IllegalStateException ex) {
								// just eat the exception for now, but you'll need to add some handling here
							}
						}
						return searches;
					}

					// convert a JSON authentication object into an Authenticated object
					private Authenticated jsonToAuthenticated(String rawAuthorization) {
						Authenticated auth = null;
						if (rawAuthorization != null && rawAuthorization.length() > 0) {
							try {
								Gson gson = new Gson();
								auth = gson.fromJson(rawAuthorization, Authenticated.class);
							} catch (IllegalStateException ex) {
								// just eat the exception for now, but you'll need to add some handling here
							}
						}
						return auth;
					}

					private String getResponseBody(HttpRequestBase request) {
						StringBuilder sb = new StringBuilder();
						try {

							DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
							HttpResponse response = httpClient.execute(request);
							int statusCode = response.getStatusLine().getStatusCode();
							String reason = response.getStatusLine().getReasonPhrase();

							if (statusCode == 200) {
 
								HttpEntity entity = response.getEntity();
								InputStream inputStream = entity.getContent();

								BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
								String line = null;
								while ((line = bReader.readLine()) != null) {
									sb.append(line);
								}
							} else {
								sb.append(reason);
							}
						} catch (UnsupportedEncodingException ex) {
						} catch (ClientProtocolException ex1) {
						} catch (IOException ex2) {
						}
						return sb.toString();
					}

					private String getStream(String url) {
						String results = null;

						// Step 1: Encode consumer key and secret
						try {
							// URL encode the consumer key and secret
							String urlApiKey = URLEncoder.encode(Key, "UTF-8");
							String urlApiSecret = URLEncoder.encode(Secret, "UTF-8");

							// Concatenate the encoded consumer key, a colon character, and the encoded consumer secret
							String combined = urlApiKey + ":" + urlApiSecret;

							// Base64 encode the string
							String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);

							// Step 2: Obtain a bearer token
							HttpPost httpPost = new HttpPost(TwitterTokenURL);
							httpPost.setHeader("Authorization", "Basic " + base64Encoded);
							httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
							httpPost.setEntity(new StringEntity("grant_type=client_credentials"));
							String rawAuthorization = getResponseBody(httpPost);
							Authenticated auth = jsonToAuthenticated(rawAuthorization);

							// Applications should verify that the value associated with the
							// token_type key of the returned object is bearer
							if (auth != null && auth.token_type.equals("bearer")) {

								// Step 3: Authenticate API requests with bearer token
								HttpGet httpGet = new HttpGet(url);

								// construct a normal HTTPS request and include an Authorization
								// header with the value of Bearer <>
								httpGet.setHeader("Authorization", "Bearer " + auth.access_token);
								httpGet.setHeader("Content-Type", "application/json");
								// update the results with the body of the response
								results = getResponseBody(httpGet);
							}
						} catch (UnsupportedEncodingException ex) {
						} catch (IllegalStateException ex1) {
						}
						return results;
					}

					private String getSearchStream(String searchTerm) {
						String results = null;
						try {
							String encodedUrl = URLEncoder.encode(searchTerm, "UTF-8");
							System.out.println("encodedUrl"+encodedUrl);
							results = getStream(TwitterSearchURL + encodedUrl + "&count=50" );
						} catch (UnsupportedEncodingException ex) {
						} catch (IllegalStateException ex1) {
						}
						return results;
					}
				}
				
				public class CustomList extends ArrayAdapter<String> {

					private final Activity context;
					private final ArrayList<String> time;
					private final ArrayList<String> image;
					private final ArrayList<String> head;
					@SuppressWarnings("unused")
					private final ArrayList<String> desc;
					
				
					
					public CustomList(Activity context,ArrayList<String> time,ArrayList<String> image,ArrayList<String> head,ArrayList<String> desc)
					{
						super(context, R.layout.custom_row, head);
						
						this.context=context;
						this.time=time;
						this.image=image;
						this.head=head;
						this.desc=desc;
						
					}
					
					@SuppressWarnings("unused")
					@Override
					public View getView(int position, View convertView, ViewGroup parent) {
						
						LayoutInflater inflater=context.getLayoutInflater();
						View rowView=inflater.inflate(R.layout.custom_row, null, true);
						
						TextView txt=(TextView)rowView.findViewById(R.id.txt_head);
						ImageView img=(ImageView)rowView.findViewById(R.id.img_user);
						TextView txt2=(TextView)rowView.findViewById(R.id.txt_time);
						TextView txt3=(TextView)rowView.findViewById(R.id.txt_desc);
						
//						String stt=new SocialFeedsActvity().getTime(time.get(position).toString());
//						txt.setText(time.get(position));
//						txt.setText(stt);
//						img.setImageResource(image[position]);
//						txt2.setText(head.get(position));
//						txt3.setText(URLDecoder.decode(head.get(position)));
						txt3.setText(head.get(position));
						txt.setText(time.get(position)); 
						
						Picasso.with(context)
					     .load(image.get(position).toString())        
					     .error(R.drawable.ic_launcher)      // optional        
					     .into(img);
						
						return rowView;
					}
				}
	
private class FacebookFeeds extends AsyncTask<String, Void, String> {
		
		private ProgressDialog progressDialog;
		String responseBody="";

		@Override
		protected void onPreExecute() {

			super.onPreExecute(); 
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Loading ...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			
			
			
			try {
				

				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(
						"https://graph.facebook.com/oauth/access_token?client_id="
								+ APP_ID + "&client_secret=" + APP_SECRET
								+ "&grant_type=client_credentials");

				ResponseHandler<String> responseHandler = new BasicResponseHandler();

				String access_token = client.execute(get, responseHandler);
				// access_token contains sthing like
				// "access_token=XXXXXXXXXX|YYYYYY" ,
				// need to replace pipe (this is ugly!)
				String uri = "https://graph.facebook.com/" + OWNER_OF_FEED
						+ "/feed?" + access_token.replace("|", "%7C")+"&limit=50"; 

				get = new HttpGet(uri);
				 responseBody = client.execute(get, responseHandler);

				// responseBody contains JSON-encoded feed
				System.out.println(""+responseBody);
				
//				textView.setText(""+sb.toString());
	 
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			 
			return responseBody;

		}
 
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			System.out.println("result1" + result);
			if (null == result || result.length() == 0
					|| TextUtils.isEmpty(result)) {

				util.dialogExample();

			} else {
				try {
					JSONObject jObject = new JSONObject(responseBody);
					if (jObject.has("data")) {
						JSONArray facebook_jsonArrays = jObject.getJSONArray("data");
						
						if (facebook_jsonArrays.length() > 0) {
							for (int i = 0; i < facebook_jsonArrays.length(); i++) {
								ItemsObj objItem = new ItemsObj();
								
								if(facebook_jsonArrays.getJSONObject(i).has("message"))
								{
									objItem.setStr_dec(("" + facebook_jsonArrays.getJSONObject(i).getString("message"))); 
								}
								else
								{
									objItem.setStr_dec(("null" ));
								}
								
								if(facebook_jsonArrays.getJSONObject(i).has("picture"))
								{
									objItem.setStr_picture(("" + facebook_jsonArrays.getJSONObject(i).getString("picture"))); 
								}
								else
								{
									objItem.setStr_picture(("null" ));
								}
								
								
								
									if(facebook_jsonArrays.getJSONObject(i).has("from"))
									{
										JSONObject nam_json= facebook_jsonArrays.getJSONObject(i).getJSONObject("from");
										if(nam_json.has("name"))
										{
											objItem.setStr_title(("" +nam_json.get("name")));
										}
										else
										{
											objItem.setStr_title(("null"));
										}
										
									}
									else
									{
										objItem.setStr_title(("null"));
									}
									
									if(facebook_jsonArrays.getJSONObject(i).has("updated_time"))
									{
										objItem.setStr_times(("" + facebook_jsonArrays.getJSONObject(i).getString("updated_time"))); 
									}
									else
									{
										objItem.setStr_times(("null" ));
									}
								
//									trsBean.setFacebook_obj(social_feesds_list_array);
//								objItem.setStr_image((""));

								social_feesds_list_array.add(objItem);
							}
				  
							social_feeds_adapter = new SocialFeeds_ListAdapter(getActivity(),
									R.layout.social_feeds_row, social_feesds_list_array);

							social_feeds_adapter.notifyDataSetChanged();
							social_feeds_facebook_listView.setAdapter(social_feeds_adapter);
						}
					}

				} catch (Exception e) {

					progressDialog.dismiss();

				}

			}

			if (null != progressDialog && progressDialog.isShowing()) {
				progressDialog.dismiss();
			}

		}

	}

public class SocialFeeds_ListAdapter extends ArrayAdapter<ItemsObj> {

	private Activity activity;
	private List<ItemsObj> items;
	private ItemsObj objBean;
	ViewHolder holder;

	public SocialFeeds_ListAdapter(Activity act, int resource,
			List<ItemsObj> arrayList) {
		super(act, resource, arrayList);
		this.activity = act;
//		this.row = resource;
		this.items = arrayList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public ItemsObj getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public class ViewHolder {
		TextView txt_nam,txt_det,txt_tim;
		ImageView icon_img;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		LayoutInflater inflator = activity.getLayoutInflater();
		objBean = items.get(position);
		if (convertView == null) {
			holder = new ViewHolder();

			convertView = inflator.inflate(R.layout.social_feeds_row, null);

			holder.txt_nam = (TextView) convertView
					.findViewById(R.id.txt_nam); 
			
			holder.txt_det = (TextView) convertView
					.findViewById(R.id.txt_det);
			
			holder.txt_tim = (TextView) convertView
					.findViewById(R.id.txt_tim);

			holder.icon_img = (ImageView) convertView
					.findViewById(R.id.icon_img);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
//		URLDecoder.decode(objBean.getStr_title().replace("null",""))
		holder.txt_nam.setText(objBean.getStr_title().replace("null",""));
		holder.txt_det.setText(objBean.getStr_dec().replace("null",""));  

		return convertView;
	}

}
}
