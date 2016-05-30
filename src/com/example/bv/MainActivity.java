package com.example.bv;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	private Button logoButton;
	private Intent intent;
	private FrameLayout  layout;
	private static Resources res;
	private Matrix matrix;
	private Bitmap bmp1;
	private BitmapDrawable bd;
	private float sw,sh;
	private float bmp_bw, bmp_bh;
	private int h,w;
	private  Bundle bData = new Bundle();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		intent = new Intent();
		res = getResources();
		
		//Parse.enableLocalDatastore(this);
		Parse.initialize(this, "N9usDyQcrGHg3uUOcy3mDdVks2ZUImV3fn5I83m7"
				, "tixW22LpIO10dr1fSeZfbFBo8Nn2GU23IpPLAvkQ");
		
		layout = (FrameLayout)findViewById(R.id.MainBackground);
		layout.post(new Runnable() {
	        @Override
	        public void run() {
	        	sh = layout.getHeight();
	        	sw = layout.getWidth();
	        	System.out.println(sh+" "+sw);
	        	print();
	        }
		});
		
		logoButton = (Button)findViewById(R.id.logoButton);
		logoButton.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
				  intent.setClass(MainActivity.this, ChoseFunction.class);
				  
				  MainActivity.this.startActivity(intent);
				  MainActivity.this.finish();
			  }
		});
		
	}

	private void print(){
		bmp1 = fitBitmapB(R.drawable.main_background);
		bd= new BitmapDrawable(getResources(), bmp1); 
		w = (int)((sh * bmp_bw)/bmp_bh) ;
		h = (int)((sw * bmp_bh)/bmp_bw) ;
		if(w<sw ){
			logoButton.setWidth(w);
			logoButton.setHeight((int)sh);
			logoButton.setX((sw-w)/2);
			bData.putInt( "screen_size", 1);
			bData.putFloat("SizeVar", w/sw);
			bData.putInt("ShiftVar", (int)(sw-w)/2);
			bData.putFloat("sw", w);
			bData.putFloat("sh", sh);
		}else if(w>sw ){
			logoButton.setWidth((int)sw);
			logoButton.setHeight(h);
			logoButton.setY((sh-h)/2);
			bData.putInt( "screen_size", 2);
			bData.putFloat("SizeVar", h/sh);
			bData.putInt("ShiftVar", (int)(sh-h)/2);
			bData.putFloat("sw", sw);
			bData.putFloat("sh", h);
		}else{
			
		}
		intent.putExtras( bData );
		logoButton.setBackground(bd);
	}

	private Bitmap fitBitmapB(int resId) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		bmp_bw = temp.getWidth();
		bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)(((sh * bmp_bw)/bmp_bh) / bmp_bw),(float)(sh / bmp_bh));
		return Bitmap.createBitmap(temp, 0, 0, (int)bmp_bw, (int)bmp_bh, matrix, true);
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
