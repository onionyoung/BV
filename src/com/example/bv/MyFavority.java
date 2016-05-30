package com.example.bv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import XListView.XListView;
import XListView.XListView.IXListViewListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MyFavority extends Activity  implements IXListViewListener{
	private static Resources res;
	private int sh, sw;
	private float sw_last;
	private float ScreenSize, SizeVar, ShiftVar;
	private FrameLayout layout;
	private Bitmap bmp1;
	private BitmapDrawable bd;
	private float bmp_bw, bmp_bh;
	private Matrix matrix;
	
	/////
	private Handler mHandler;
	private XListView listView;
	ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	private LinearLayout placeHolder;
	private SimpleAdapter adapter;
	private int ListSize = 10;
	
	/////
	private TextView textView1, textView2, textView3, textView4
	, textView5, textView6, textView7, textView8;
	private Button button1, button2, button3, up2_confirm;
	private DBHelper DH = null;
    private List<Item> items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.myfavority);
		///background
		res = getResources();
		Bundle bData = this.getIntent().getExtras();
		sw = (int)bData.getFloat("sw");
		sh = (int)bData.getFloat("sh");
		sw_last = bData.getFloat("sw");
		ScreenSize = bData.getInt("screen_size");
		SizeVar = bData.getFloat("SizeVar");
		ShiftVar = bData.getInt("ShiftVar");
		layout = (FrameLayout)findViewById(R.id.my_favority);
		LayoutParams params =  layout.getLayoutParams();
		params.height = (int)sh;
		params.width = (int)sw;
		if(ScreenSize == 1){
			layout.setX(ShiftVar);
		}else if(ScreenSize == 2){
			layout.setY(ShiftVar);
		}
		bmp1 = fitBitmapB(R.drawable.myfavority);
		bd= new BitmapDrawable(getResources(), bmp1); 
		//layout.setScaleX((sw_last)/sw);4/19
		layout.setBackground(bd);
		
		////SQlite
		openDB();
		items = DH.getAll();
		
	////listview
		listViewCreat();
	}
	private void listViewCreat(){
		list.removeAll(list);
		final int size = items.size();
		for(int i=0; i<items.size() && i<ListSize ; i++){
			HashMap<String,Object> item = new HashMap<String,Object>();
			item.put( "food", items.get(i).getTitle());
			item.put( "place",items.get(i).getCC() );
			list.add( item );
		}
		if(items.size() == 0){
			HashMap<String,Object> item = new HashMap<String,Object>();
			item.put( "food", "No Data");
			item.put( "place","Enter Data In ADD Function" );
			list.add( item );
		}
		if(items.size()<ListSize){
			int a=13;
			if(items.size()==0)a--;
			for(int i=a-items.size(); i!=0; i--){
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
	    
	    listView =(XListView) findViewById(R.id.listView1);
	    listView.setAdapter( adapter );
	    listView.setY((int)(sh*0.168));
	    listView.setOnItemClickListener(new OnItemClickListener(){
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	        	//scoreList.get(position);
	        	if(size>position){
	        		AlertDialog myDialog = new AlertDialog.Builder(MyFavority.this).create();
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
	        		PutDataInDialogItem(items,position);
	        	}
	        	
	        }
	    });
	    listView.setPullRefreshEnable(false);
	    listView.setPullLoadEnable(true);
	    listView.setXListViewListener(this);
		mHandler = new Handler();
	}
	
	private void printDialogItem(){
		float i = (float) 0.047;
		int alpha = 150;
		bmp1 = fitBitmap(R.drawable.white5, (int)(sw_last*0.55), (int)(sh*0.22));
		bd= new BitmapDrawable(getResources(), bmp1); 
		textView1.setBackground(bd);
		textView1.getBackground().setAlpha(alpha);
		textView1.setX((int)(sw_last*0.2));//165
		textView1.setY((int)(sh*0.125));//80
		bmp1 = fitBitmap(R.drawable.white5, (int)(sw_last*0.55), (int)(sh*0.038));
		bd= new BitmapDrawable(getResources(), bmp1); 
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
		bmp1 = fitBitmap(R.drawable.white5, (int)(sw_last*0.62), (int)(sh*0.038));
		bd= new BitmapDrawable(getResources(), bmp1); 
		textView4.setBackground(bd);
		textView4.getBackground().setAlpha(alpha);
		textView4.setX((int)(sw_last*0.13));
		textView4.setY((int)(sh*(0.365+i*2)));
		textView6.setBackground(bd);
		textView6.getBackground().setAlpha(alpha);
		textView6.setX((int)(sw_last*0.13));
		textView6.setY((int)(sh*(0.365+i*4)));
		
		bmp1 = fitBitmap(R.drawable.white5, (int)(sw_last*0.62), (int)(sh*0.114));
		bd= new BitmapDrawable(getResources(), bmp1);
		textView8.setBackground(bd);
		textView8.getBackground().setAlpha(alpha);
		textView8.setX((int)(sw_last*0.13));
		textView8.setY((int)(sh*(0.365+i*6)));
		
		bmp1 = fitBitmap(R.drawable.white5, (int)(sw_last*0.14), (int)(sw_last*0.14));
		bd= new BitmapDrawable(getResources(), bmp1);
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
	public void PutDataInDialogItem(List<Item> items2,int position){
		textView2.setText(items2.get(position).getName());
		String str = items2.get(position).getKind();
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
		textView4.setText(items2.get(position).getCC() + 
				items2.get(position).getTitle() + 	
				items2.get(position).getLane());
		textView5.setText(items2.get(position).getNumber());
		textView7.setText(items2.get(position).getTime());
		textView8.setText(getString(R.string.unfinish));
		//textView1.setText(getString(R.string.unfinish));
		//byte[] data = scoreList.get(position).getBytes("pic");
		
		/*Bitmap bmp = BitmapFactory
				.decodeByteArray(
						data, 0,
						data.length);*/
		//downloadPic(items2, position);
		if(items2.get(position).getBitmap()	!=	null){
			/////
			Bitmap temp = items2.get(position).getBitmap();
			int bmp_bw = temp.getWidth();
			int bmp_bh = temp.getHeight();
			/////
			BitmapDrawable bmpdraw = new BitmapDrawable(getResources(), temp);
			button2.setHeight((int)(sh*0.22));
			button2.setWidth((int)((sh*0.22) * bmp_bw )/bmp_bh);
			button2.setBackground(bmpdraw);
			textView1.setText("");
		}
		up2_confirm.setText("?");
	}
	private void openDB(){
    	DH = new DBHelper(this);  
    }
    private void closeDB(){
    	DH.close();    	
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
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
    private Bitmap fitBitmapB(int resId) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		bmp_bw = temp.getWidth();
		bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)(sw_last / bmp_bw),(float)sh / bmp_bh);
		return Bitmap.createBitmap(temp, 0, 0, (int)bmp_bw, (int)bmp_bh, matrix, true);
	}
    
    private void geneItems() {
    	if(items.size()<=ListSize)return;
    	ListSize=ListSize+10;
    	for(int i=ListSize-10; i<items.size() && i<ListSize; i++){
			HashMap<String,Object> item = new HashMap<String,Object>();
			item.put( "food", items.get(i).getTitle());
			item.put( "place",items.get(i).getCC() );
			list.add( item );
		}
    	if(items.size()<=ListSize){
			int a=ListSize;
			if(items.size()==0)a--;
			for(int i=a-items.size(); i!=0; i--){
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
	}

	private void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime("刚刚");
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		/*mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				// mAdapter.notifyDataSetChanged();
				adapter = new ArrayAdapter<String>(XListViewActivity.this, R.layout.list_item, items);
				listView.setAdapter(adapter);
				onLoad();
			}
		}, 2000);*/
	}
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				adapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
}
