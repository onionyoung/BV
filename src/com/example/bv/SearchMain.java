package com.example.bv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchMain extends Activity implements OnClickListener{
	private Button confirm1,confirm2;
	private Intent intent;
	private String KindsOfBall = "null", KindsOfArea = "null";
	private Button BasketballButton, VolleyballButton, NButton, MButton, 
					SButton, EButton;
	private  Bundle bData = new Bundle();
	private static Resources res;
	private Matrix matrix;
	private Bitmap bmp1,bmp2,bmp3,bmp4,bmp5,bmp6
				,bmp7,bmp8,bmp9,bmp10,bmp11,bmp12,bmpOther;
	private BitmapDrawable bd;
	private FrameLayout layout;
	private float sw,sh,sw_last;
	private float bmp_bw, bmp_bh;
	private float ScreenSize, SizeVar, ShiftVar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_main);
		intent = new Intent();
		res = getResources();
		
		BasketballButton = (Button)findViewById(R.id.Bbutton);
		VolleyballButton = (Button)findViewById(R.id.Vbutton);
		NButton = (Button)findViewById(R.id.NorthButton);
		MButton = (Button)findViewById(R.id.MidButton);
		SButton = (Button)findViewById(R.id.SouthButton);
		EButton = (Button)findViewById(R.id.EastButton);
		//confirm1 = (Button)findViewById(R.id.confirm1);
		//confirm2 = (Button)findViewById(R.id.confirm2);
		
		BasketballButton.setOnClickListener(this);
		VolleyballButton.setOnClickListener(this);
		NButton.setOnClickListener(this);
		MButton.setOnClickListener(this);
		SButton.setOnClickListener(this);
		EButton.setOnClickListener(this);
		//confirm1.setOnClickListener(this);
		//confirm2.setOnClickListener(this);
		bData = this.getIntent().getExtras();
		sw = (int)bData.getFloat("sw");
		sh = (int)bData.getFloat("sh");
		sw_last = bData.getFloat("sw");
		ScreenSize = bData.getInt("screen_size");
		SizeVar = bData.getFloat("SizeVar");
		ShiftVar = bData.getInt("ShiftVar");
		layout = (FrameLayout)findViewById(R.id.search_layout);
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
		//layout.setScaleX(((sh * bmp_bw)/bmp_bh)/sw);
		layout.setBackground(bd);
		
		bmp1 = fitBitmap(R.drawable.up_bball,(int)(sw*0.264),(int)(sh*0.18));
		bd= new BitmapDrawable(getResources(), bmp1);
		BasketballButton.setBackground(bd);
		BasketballButton.setX((int)(sw*0.20));
		BasketballButton.setY((int)(sh*(0.15)));
		bmp2 = fitBitmap(R.drawable.up_vball,(int)(sw*0.264),(int)(sh*0.18));
		bd= new BitmapDrawable(getResources(), bmp2);
		VolleyballButton.setBackground(bd);
		VolleyballButton.setX((int)(sw*0.546));
		VolleyballButton.setY((int)(sh*(0.15)));
		bmp3 = fitBitmap(R.drawable.north,(int)(sw*0.345),(int)(sh*0.225));
		bd= new BitmapDrawable(getResources(), bmp3);
		NButton.setBackground(bd);
		NButton.setX((int)(sw*0.141));
		NButton.setY((int)(sh*(0.35)));
		bmp4 = fitBitmap(R.drawable.mid,(int)(sw*0.345),(int)(sh*0.225));
		bd= new BitmapDrawable(getResources(), bmp4);
		MButton.setBackground(bd);
		MButton.setX((int)(sw*0.524));
		MButton.setY((int)(sh*(0.35)));
		bmp5 = fitBitmap(R.drawable.south,(int)(sw*0.345),(int)(sh*0.225));
		bd= new BitmapDrawable(getResources(), bmp5);
		SButton.setBackground(bd);
		SButton.setX((int)(sw*0.141));
		SButton.setY((int)(sh*(0.595)));
		bmp6 = fitBitmap(R.drawable.other,(int)(sw*0.345),(int)(sh*0.225));
		bd= new BitmapDrawable(getResources(), bmp6);
		EButton.setBackground(bd);
		EButton.setX((int)(sw*0.524));
		EButton.setY((int)(sh*(0.595)));
		bmp7 = fitBitmap(R.drawable.up_bballd,(int)(sw*0.264),(int)(sh*0.18));
		bmp8 = fitBitmap(R.drawable.up_vballd,(int)(sw*0.264),(int)(sh*0.18));
		bmp9 = fitBitmap(R.drawable.northd,(int)(sw*0.345),(int)(sh*0.225));
		bmp10 = fitBitmap(R.drawable.midd,(int)(sw*0.345),(int)(sh*0.225));
		bmp11 = fitBitmap(R.drawable.southd,(int)(sw*0.345),(int)(sh*0.225));
		bmp12 = fitBitmap(R.drawable.otherd,(int)(sw*0.345),(int)(sh*0.225));
		bmpOther = fitBitmap(R.drawable.up_confirmbutton,(int)(sw*0.2),(int)(sw*0.1));
		bd= new BitmapDrawable(getResources(), bmpOther);
		/*confirm1.setBackground(bd);
		confirm1.setX((int)(sw*0.405));
		confirm1.setY((int)(sh*(0.86)));*/
		bmpOther = fitBitmap(R.drawable.upd_confirmbutton,(int)(sw*0.2),(int)(sw*0.1));
	    		
	}
	private Bitmap fitBitmapB(int resId) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		bmp_bw = temp.getWidth();
		bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)(((sh * bmp_bw)/bmp_bh) / bmp_bw),(float)sh / bmp_bh);
		return Bitmap.createBitmap(temp, 0, 0, (int)bmp_bw, (int)bmp_bh, matrix, true);
	}
	private Bitmap fitBitmap(int resId, int Width, int height) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		int bmp_bw = temp.getWidth();
		int bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)Width / bmp_bw,(float)height / bmp_bh);
		return Bitmap.createBitmap(temp, 0, 0, bmp_bw, bmp_bh, matrix, true);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.Bbutton: 
				ButtonChose("basketball");
				break;
			case R.id.Vbutton:
				ButtonChose("volleyball");
				break;
			case R.id.NorthButton:
				ButtonChose("North");
				break;
			case R.id.MidButton:
				ButtonChose("Mid");
				break;
			case R.id.SouthButton:
				ButtonChose("South");
				break;
			case R.id.EastButton:
				ButtonChose("East");
				break;
			case R.id.confirm1:
				/*if(KindsOfBall.equals("null") || KindsOfArea.equals("null")){
					Toast.makeText(SearchMain.this, "選擇按鈕",Toast.LENGTH_SHORT ).show();
				}else{
					bd= new BitmapDrawable(getResources(), bmpOther);
		    		confirm1.setBackground(bd);
					intent.setClass(SearchMain.this, FindCourtMain1.class);
					bData.putString( "KindsOfBall", KindsOfBall);
					bData.putString( "KindsOfArea", KindsOfArea);
					bData.putFloat("sw", sw);
					bData.putFloat("sh", sh);
					bData.putFloat("sw_last", sw_last);
					intent.putExtras( bData );
					SearchMain.this.startActivity(intent);
				}*/
				break;
			//case R.id.confirm2:
				//intent.setClass(SearchMain.this, FindCourtMain2.class);
				//SearchMain.this.startActivity(intent);
			//	break;
			default:
				break;
		}
	}
	
	public void ButtonChose(String str){
		
		if(str.equals("basketball")){
			KindsOfBall = "basketball";
			bd= new BitmapDrawable(getResources(), bmp2);
			VolleyballButton.setBackground(bd);
			bd= new BitmapDrawable(getResources(), bmp7);
			BasketballButton.setBackground(bd);
		}else if(str.equals("volleyball")){
			KindsOfBall = "volleyball";
			bd= new BitmapDrawable(getResources(), bmp1);
			BasketballButton.setBackground(bd);
			bd= new BitmapDrawable(getResources(), bmp8);
			VolleyballButton.setBackground(bd);
		}else{
			if(KindsOfBall.equals("null") ){
				Toast.makeText(SearchMain.this, "先選擇球類按鈕",Toast.LENGTH_SHORT ).show();
				return;
			}else if(str.equals("North")){
				KindsOfArea = "North";
				bd= new BitmapDrawable(getResources(), bmp9);
				NButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp4);
				MButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp5);
				SButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp6);
				EButton.setBackground(bd);
			}else if(str.equals("Mid")){
				KindsOfArea = "Mid";
				bd= new BitmapDrawable(getResources(), bmp3);
				NButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp10);
				MButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp5);
				SButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp6);
				EButton.setBackground(bd);
			}else if(str.equals("South")){
				KindsOfArea = "South";
				bd= new BitmapDrawable(getResources(), bmp3);
				NButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp4);
				MButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp11);
				SButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp6);
				EButton.setBackground(bd);
			}else if(str.equals("East")){
				KindsOfArea = "East";
				bd= new BitmapDrawable(getResources(), bmp3);
				NButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp4);
				MButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp5);
				SButton.setBackground(bd);
				bd= new BitmapDrawable(getResources(), bmp12);
				EButton.setBackground(bd);
			}
			intent.setClass(SearchMain.this, FindCourtMain1.class);
			bData.putString( "KindsOfBall", KindsOfBall);
			bData.putString( "KindsOfArea", KindsOfArea);
			bData.putFloat("sw", sw);
			bData.putFloat("sh", sh);
			bData.putFloat("sw_last", sw_last);
			intent.putExtras( bData );
			SearchMain.this.startActivity(intent);
		}
	}
	
	
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