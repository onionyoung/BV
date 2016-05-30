package com.example.bv;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import DownloadWebpageTask.DownloadWebpageTask;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FindCourtMain1 extends Activity implements OnClickListener{
	private ListView listView;
	//private static final String[] mNames = new String[20];
	//private static final String[] mmPlaces = new String[20] ;
	//private ArrayAdapter<String> listAdapter;
	ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	private SimpleAdapter adapter;
	private static final String[] mPlaces = new String[] {
		 "基隆市", "新北市", "台北市", "桃園縣", "新竹縣", "新竹市", "宜蘭縣", 
		 "苗栗縣", "台中市", "彰化縣", "南投縣", "雲林縣",
		 "嘉義縣", "嘉義市", "台南市", "高雄市", "屏東縣",
		 "花蓮縣", "台東市" };
	private ProgressDialog progressDialog;
	private String  KindsOfBall, KindsOfArea;
	private Button Button1, Button2, Button3, Button4, Button5, Button6, Button7
				, button1, button2, button3;
	private int sh, sw;
	private float sw_last;
	private static Resources res;
	private Matrix matrix;
	private Bitmap bmp1,bmpOther,bmp;
	private BitmapDrawable bd;
	private FrameLayout layout;
	private float bmp_bw, bmp_bh;
	private LinearLayout placeHolder;
	private Button up2_confirm;
	private TextView textView1, textView2, textView3, textView4
	, textView5, textView6, textView7, textView8;
	private float ScreenSize, SizeVar, ShiftVar;
	private HorizontalScrollView horizontalScrollView1;
	//private Button button[] = new Button[21];
	private DownloadWebpageTask downloadWebpageTask= new DownloadWebpageTask();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_court_main1);
		res = getResources();
		Bundle bData = this.getIntent().getExtras();
		KindsOfBall = bData.getString( "KindsOfBall" );
	    KindsOfArea = bData.getString( "KindsOfArea" );
	    sw = (int)bData.getFloat("sw");
		sh = (int)bData.getFloat("sh");
		sw_last = bData.getFloat("sw");
		ScreenSize = bData.getInt("screen_size");
		SizeVar = bData.getFloat("SizeVar");
		ShiftVar = bData.getInt("ShiftVar");
		layout = (FrameLayout)findViewById(R.id.find_background);
		LayoutParams params =  layout.getLayoutParams();
		params.height = (int)sh;
		params.width = (int)sw;
		if(ScreenSize == 1){
			layout.setX(ShiftVar);
		}else if(ScreenSize == 2){
			layout.setY(ShiftVar);
		}
		bmp1 = fitBitmapB(R.drawable.background2);
		bd= new BitmapDrawable(getResources(), bmp1); 
		//layout.setScaleX((sw_last)/sw);4/19
		layout.setBackground(bd);
		placeHolder = (LinearLayout) findViewById(R.id.listview_layout);
		placeHolder.setY(sh/20);
		
		horizontalScrollView1 = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		params =  horizontalScrollView1.getLayoutParams();
		params.width = sw;
		params.height = sh/10;
		horizontalScrollView1.setBackgroundResource(R.drawable.findcountb);
		setAreaButton();
		
		//ParseGetData();
		//putLayoutAndData();
	   
		//downloadWebpageTask.defineListView((ListView) findViewById(R.id.listView1),this);
        //myClickHandler();
	}
	
	public void setAreaButton(){
		Button1 =(Button)findViewById(R.id.button1);
		Button2 =(Button)findViewById(R.id.button2);
		Button3 =(Button)findViewById(R.id.button3);
		Button4 =(Button)findViewById(R.id.button4);
		Button5 =(Button)findViewById(R.id.button5);
	    Button6 =(Button)findViewById(R.id.button6);
	    Button7 =(Button)findViewById(R.id.button7);
		if(KindsOfArea.equals("North")){
			Button1.setText(mPlaces[0]);
			Button1.setOnClickListener(this);
			Button2.setText(mPlaces[1]);
			Button2.setOnClickListener(this);
			Button3.setText(mPlaces[2]);
			Button3.setOnClickListener(this);
			Button4.setText(mPlaces[3]);
			Button4.setOnClickListener(this);
			Button5.setText(mPlaces[4]);
			Button5.setOnClickListener(this);
			Button6.setText(mPlaces[5]);
			Button6.setOnClickListener(this);
			Button7.setText(mPlaces[6]);
			Button7.setOnClickListener(this);
		}else if(KindsOfArea.equals("Mid")){
			Button1.setText(mPlaces[7]);
			Button1.setOnClickListener(this);
			Button2.setText(mPlaces[8]);
			Button2.setOnClickListener(this);
			Button3.setText(mPlaces[9]);
			Button3.setOnClickListener(this);
			Button4.setText(mPlaces[10]);
			Button4.setOnClickListener(this);
			Button5.setText(mPlaces[11]);
			Button5.setOnClickListener(this);
			Button6.setBackgroundColor(0);
			Button7.setBackgroundColor(0);
		}else if(KindsOfArea.equals("South")){
			Button1.setText(mPlaces[12]);
			Button1.setOnClickListener(this);
			Button2.setText(mPlaces[13]);
			Button2.setOnClickListener(this);
			Button3.setText(mPlaces[14]);
			Button3.setOnClickListener(this);
			Button4.setText(mPlaces[15]);
			Button4.setOnClickListener(this);
			Button5.setText(mPlaces[16]);
			Button5.setOnClickListener(this);
			Button6.setBackgroundColor(0);
			Button7.setBackgroundColor(0);
		}else if(KindsOfArea.equals("East")){
			Button1.setText(mPlaces[17]);
			Button1.setOnClickListener(this);
			Button2.setText(mPlaces[18]);
			Button2.setOnClickListener(this);
			Button3.setBackgroundColor(0);
			Button4.setBackgroundColor(0);
			Button5.setBackgroundColor(0);
			Button6.setBackgroundColor(0);
			Button7.setBackgroundColor(0);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button1: 
				if(KindsOfArea.equals("North")){
					ParseGetData((String) getText(R.string.N1));
				}else if(KindsOfArea.equals("Mid")){
					ParseGetData((String) getText(R.string.M1));
				}else if(KindsOfArea.equals("South")){
					ParseGetData((String) getText(R.string.S1));
				}else if(KindsOfArea.equals("East")){
					ParseGetData((String) getText(R.string.E1));
				}
				break;
			case R.id.button2: 
				if(KindsOfArea.equals("North")){
					ParseGetData((String) getText(R.string.N2));
				}else if(KindsOfArea.equals("Mid")){
					ParseGetData((String) getText(R.string.M2));
				}else if(KindsOfArea.equals("South")){
					ParseGetData((String) getText(R.string.S2));
				}else if(KindsOfArea.equals("East")){
					ParseGetData((String) getText(R.string.E2));
				}
				break;
			case R.id.button3: 
				if(KindsOfArea.equals("North")){
					ParseGetData((String) getText(R.string.N3));
				}else if(KindsOfArea.equals("Mid")){
					ParseGetData((String) getText(R.string.M3));
				}else if(KindsOfArea.equals("South")){
					ParseGetData((String) getText(R.string.S3));
				}
				break;
			case R.id.button4: 
				if(KindsOfArea.equals("North")){
					ParseGetData((String) getText(R.string.N4));
				}else if(KindsOfArea.equals("Mid")){
					ParseGetData((String) getText(R.string.M4));
				}else if(KindsOfArea.equals("South")){
					ParseGetData((String) getText(R.string.S4));
				}
				break;
			case R.id.button5: 
				if(KindsOfArea.equals("North")){
					ParseGetData((String) getText(R.string.N5));
				}else if(KindsOfArea.equals("Mid")){
					ParseGetData((String) getText(R.string.M5));
				}else if(KindsOfArea.equals("South")){
					ParseGetData((String) getText(R.string.S5));
				}
				break;
			case R.id.button6: 
				if(KindsOfArea.equals("North")){
					ParseGetData((String) getText(R.string.N6));
				}
				break;
			case R.id.button7: 
				if(KindsOfArea.equals("North")){
					ParseGetData((String) getText(R.string.N7));
				}
				break;
			default:
				break;
		}
	}
	private Bitmap fitBitmapB(int resId) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		bmp_bw = temp.getWidth();
		bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)(sw_last / bmp_bw),(float)sh / bmp_bh);
		return Bitmap.createBitmap(temp, 0, 0, (int)bmp_bw, (int)bmp_bh, matrix, true);
	}
	private Bitmap fitBitmap(int resId, int Width, int height) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		int bmp_bw = temp.getWidth();
		int bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)Width / bmp_bw,(float)height/ bmp_bh);
		return Bitmap.createBitmap(temp, 0, 0, bmp_bw, bmp_bh, matrix, true);
	}
	public void ParseGetData(String str){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("imformation");
		query.whereEqualTo("cc", str);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
		    public void done(List<ParseObject> scoreList, ParseException e) {
		        if (e == null) {
		        	putLayoutAndData(scoreList);
		        } else {
		        	putLayoutAndData(scoreList);
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    }
		});
	}
	public void putLayoutAndData(final List<ParseObject> scoreList){
		list.removeAll(list);
		final int size = scoreList.size();
		for(int i=0; i<scoreList.size(); i++){
			HashMap<String,Object> item = new HashMap<String,Object>();
			item.put( "food", scoreList.get(i).getString("name"));
			item.put( "place",scoreList.get(i).getString("cc") );
			list.add( item );
		}
		if(scoreList.size() == 0){
			HashMap<String,Object> item = new HashMap<String,Object>();
			item.put( "food", "No Data");
			item.put( "place","Enter Data In ADD Function" );
			list.add( item );
		}
		if(scoreList.size()<=13){
			int a=13;
			if(scoreList.size()==0)a--;
			for(int i=a-scoreList.size(); i!=0; i--){
			    HashMap<String,Object> item = new HashMap<String,Object>();
			    item.put( "food", "");
			    item.put( "place","" );
			    list.add( item );
			}
		} 
		adapter = new SimpleAdapter( 
			this, 
			list,
			R.layout.listview_layout,
			new String[] { "food" ,"place"},
			new int[] {R.id.textView1, R.id.textView2} );
	    getLayoutInflater().inflate(R.layout.listview_layout, placeHolder);
	    
	    listView =(ListView) findViewById(R.id.listView1);
	    listView.setAdapter( adapter );
	    
	    listView.setOnItemClickListener(new OnItemClickListener(){
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	        	//scoreList.get(position);
	        	if(size>position){
	        		progressDialog = ProgressDialog.show(FindCourtMain1.this, "",
							"Downloading Image...", true);
	        		AlertDialog myDialog = new AlertDialog.Builder(FindCourtMain1.this).create();
	        	    myDialog.show();
	        	    
	        	    Window dialogWindow = myDialog.getWindow();
	        	    float w = sw_last*4/5;
	        	    myDialog.getWindow().setLayout((int)w, (int)(w/13*20));
	        	    myDialog.getWindow().setBackgroundDrawableResource(R.drawable.search_confirm_bg);
	        	    myDialog.getWindow().setContentView(R.layout.upload_confirm);
	        	    
	        	    textView1 = (TextView)dialogWindow.findViewById(R.id.textView1);
	        		textView2 = (TextView)dialogWindow.findViewById(R.id.textView2);
	        		textView3 = (TextView)dialogWindow.findViewById(R.id.textView3);
	        		textView4 = (TextView)dialogWindow.findViewById(R.id.textView4);
	        		textView5 = (TextView)dialogWindow.findViewById(R.id.textView5);
	        		textView6 = (TextView)dialogWindow.findViewById(R.id.textView6);
	        		textView7 = (TextView)dialogWindow.findViewById(R.id.textView7);
	        		textView8 = (TextView)dialogWindow.findViewById(R.id.textView8);
	        		up2_confirm = (Button)dialogWindow.findViewById(R.id.up2_confirm);
	        		button1 =(Button)dialogWindow.findViewById(R.id.button1);
	        		button2 =(Button)dialogWindow.findViewById(R.id.captureimage);
	        		button3 =(Button)dialogWindow.findViewById(R.id.button3);
	        		printDialogItem();
	        		PutDataInDialogItem(scoreList,position);
	        	}
	        }
	    });
	    listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // TODO Auto-generated method stub
            	byte[] blob = null;
				try {
					if(scoreList.get(position).getParseFile("pic")!=null)
						blob = scoreList.get(position).getParseFile("pic").getData();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(blob != null){
					System.out.println("~!*");
					if(size>position){
	            		UploadSQlite uploadSQlite = new UploadSQlite(FindCourtMain1.this);
	            		uploadSQlite.add(scoreList.get(position).getString("area")
	            				, scoreList.get(position).getString("cc")
	            				, scoreList.get(position).getString("kind")
	            				, scoreList.get(position).getString("lane")
	            				, scoreList.get(position).getString("name")
	            				, scoreList.get(position).getString("number")
	            				, scoreList.get(position).getString("time")
	            				, BitmapFactory.decodeByteArray(
	            						blob, 0, blob.length)
	            				);
	            		
	            		Toast.makeText(FindCourtMain1.this, getString(R.string.confirm5),Toast.LENGTH_SHORT ).show();
	            	}
				}else {
					System.out.println("***");
	            	if(size>position){
	            		UploadSQlite uploadSQlite = new UploadSQlite(FindCourtMain1.this);
	            		uploadSQlite.add(scoreList.get(position).getString("area")
	            				, scoreList.get(position).getString("cc")
	            				, scoreList.get(position).getString("kind")
	            				, scoreList.get(position).getString("lane")
	            				, scoreList.get(position).getString("name")
	            				, scoreList.get(position).getString("number")
	            				, scoreList.get(position).getString("time")
	            				);
	            		
	            		Toast.makeText(FindCourtMain1.this, getString(R.string.confirm5),Toast.LENGTH_SHORT ).show();
	            	}
				}
                return true;
            }
        }); 
	}
	private void printDialogItem(){
		float i = (float) 0.047;
		int alpha = 150;
		bmpOther = fitBitmap(R.drawable.white5, (int)(sw_last*0.55), (int)(sh*0.22));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		textView1.setBackground(bd);
		textView1.getBackground().setAlpha(alpha);
		textView1.setX((int)(sw_last*0.2));//165
		textView1.setY((int)(sh*0.125));//80
		bmpOther = fitBitmap(R.drawable.white5, (int)(sw_last*0.55), (int)(sh*0.038));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		textView2.setBackground(bd);
		textView2.getBackground().setAlpha(alpha);
		textView2.setX((int)(sw_last*0.2));
		textView2.setY((int)(sh*(0.365)));
		textView3.setBackground(bd);
		textView3.getBackground().setAlpha(alpha);
		textView3.setX((int)(sw_last*0.2));
		textView3.setY((int)(sh*(0.365+i)));
		textView5.setBackground(bd);
		textView5.getBackground().setAlpha(alpha);
		textView5.setX((int)(sw_last*0.2));
		textView5.setY((int)(sh*(0.365+i*3)));
		textView7.setBackground(bd);
		textView7.getBackground().setAlpha(alpha);
		textView7.setX((int)(sw_last*0.2));
		textView7.setY((int)(sh*(0.365+i*5)));
		bmpOther = fitBitmap(R.drawable.white5, (int)(sw_last*0.62), (int)(sh*0.038));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		textView4.setBackground(bd);
		textView4.getBackground().setAlpha(alpha);
		textView4.setX((int)(sw_last*0.13));
		textView4.setY((int)(sh*(0.365+i*2)));
		textView6.setBackground(bd);
		textView6.getBackground().setAlpha(alpha);
		textView6.setX((int)(sw_last*0.13));
		textView6.setY((int)(sh*(0.365+i*4)));
		
		bmpOther = fitBitmap(R.drawable.white5, (int)(sw_last*0.62), (int)(sh*0.114));
		bd= new BitmapDrawable(getResources(), bmpOther);
		textView8.setBackground(bd);
		textView8.getBackground().setAlpha(alpha);
		textView8.setX((int)(sw_last*0.13));
		textView8.setY((int)(sh*(0.365+i*6)));
		
		bmpOther = fitBitmap(R.drawable.white5, (int)(sw_last*0.14), (int)(sw_last*0.14));
		bd= new BitmapDrawable(getResources(), bmpOther);
		up2_confirm.setBackground(bd);
		up2_confirm.getBackground().setAlpha(alpha);
		up2_confirm.setX((int)(sw_last*0.04));
		up2_confirm.setY((int)(sh*(0.15)));//sw== sw_last*1.254
		
		button2.setX((int)(sw_last*0.2));//165
		button2.setY((int)(sh*0.125));//80
		button1.getBackground().setAlpha(0);
		button2.getBackground().setAlpha(0);
		button3.getBackground().setAlpha(0);
		
	}
	public void PutDataInDialogItem(List<ParseObject> scoreList,int position){
		textView2.setText(scoreList.get(position).getString("name"));
		String str = scoreList.get(position).getString("kind");
		if(str.equals("4") || str.equals("5") || str.equals("6") || str.equals("7") || 
				str.equals("12") || str.equals("13") || str.equals("14") || str.equals("15")){
			textView3.setText(getString(R.string.School));
		}else{
			textView3.setText(getString(R.string.Community));
		}
		int num = Integer.valueOf(str).intValue();
		if(num % 2 ==1){
			num = num/2;
			if(num % 2 == 1){
				textView6.setText(getString(R.string.NotSand)+" "+ getString(R.string.Sand));
			}else{
				textView6.setText(getString(R.string.Sand));
			}
		}else{
			num = num/2;
			if(num % 2 == 1){
				textView6.setText(getString(R.string.NotSand));
			}else{
				textView6.setText("No data");
			}
		}
		textView4.setText(scoreList.get(position).getString("cc") + 
				scoreList.get(position).getString("area") + 	
				scoreList.get(position).getString("lane"));
		textView5.setText(scoreList.get(position).getString("number"));
		textView7.setText(scoreList.get(position).getString("time"));
		textView8.setText(getString(R.string.unfinish));
		//textView1.setText(getString(R.string.unfinish));
		//byte[] data = scoreList.get(position).getBytes("pic");
		
		/*Bitmap bmp = BitmapFactory
				.decodeByteArray(
						data, 0,
						data.length);*/
		downloadPic(scoreList, position);
		/*BitmapDrawable bmpdraw = new BitmapDrawable(this.getResources(), bmp);

		textView1.setBackground(bmpdraw);*/
		up2_confirm.setText("?");
	}
	private void downloadPic(List<ParseObject> scoreList,int position){
		if(scoreList.get(position).get("pic") == null){
			progressDialog.dismiss();
			return;
		}
		ParseFile fileObject = (ParseFile) scoreList.get(position).get("pic");
			fileObject.getDataInBackground(new GetDataCallback() {
				public void done(byte[] data,
						ParseException e) {
					if (e == null) {
						// Decode the Byte[] into
						// Bitmap
						if(data != null){
							bmp = BitmapFactory
									.decodeByteArray(
											data, 0,
											data.length);
							BitmapDrawable bmpdraw = new BitmapDrawable(getResources(), bmp);
							
							int bmp_bw = bmp.getWidth();
							int bmp_bh = bmp.getHeight();
							textView1.setText("");
							button2.setHeight((int)(sh*0.22));
							button2.setWidth((int)((sh*0.22) * bmp_bw )/bmp_bh);
							button2.setBackground(bmpdraw);
							// Close progress dialog
							//   textH : x = bh : bw
							progressDialog.dismiss();
						}
					} else {
						Log.d("test",
								"There was a problem downloading the data.");
					}
				}
			});
			
	}
	/*public void myClickHandler() {
        // Gets the URL from the UI's text field.
        String stringUrl = "http://www.dodgeg.net84.net/appData/getData2.php";
        //urlText.getText().toString();
        ConnectivityManager connMgr = (ConnectivityManager) 
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            downloadWebpageTask.execute(stringUrl);
        } else {
            //textView.setText("No network connection available.");
        }
    }*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
