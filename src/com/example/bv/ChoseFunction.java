package com.example.bv;


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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;

public class ChoseFunction extends Activity {
	private Button UploadButton, SearchButton, FavoriteButton, CountButton;
	private FrameLayout layout, layoutL;
	private Intent intent;
	private int height, Width, ScreenSize, ShiftVar;
	private static Resources res;
	private Matrix matrix;
	private Bitmap bmp1,bmp2,bmp3,bmp4,bmp5,bmp6,bmp7,bmp8;
	private BitmapDrawable bd;
	private float sw,sh;
	private float bmp_bw, bmp_bh;
	private float SizeVar;
	private  Bundle bData = new Bundle();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chose_function);
		res = getResources();

		intent = new Intent();
		layout = (FrameLayout)findViewById(R.id.cf_background);
		UploadButton = (Button)findViewById(R.id.UploadButton);
		SearchButton = (Button)findViewById(R.id.SearchButton);
		FavoriteButton = (Button)findViewById(R.id.FavoriteButton);
		CountButton = (Button)findViewById(R.id.CountButton);
		//screen catch
		/*DisplayMetrics displayMetrics = new DisplayMetrics(); 
		this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		sw = displayMetrics.widthPixels;
		sh = displayMetrics.heightPixels;*/


		//UploadButton.setText(Integer.toString((int)sw));
		//FavoriteButton.setText(Integer.toString((int)sh)); 
		bData = this.getIntent().getExtras();
		sw = bData.getFloat("sw");
		sh = bData.getFloat("sh");
		ScreenSize = bData.getInt("screen_size");
		SizeVar = bData.getFloat("SizeVar");
		ShiftVar = bData.getInt("ShiftVar");
		System.out.println(ScreenSize);
		System.out.println(ShiftVar);
		LayoutParams params =  layout.getLayoutParams();
		params.height = (int)sh;
		params.width = (int)sw;
		layout.setLayoutParams(params);
		if(ScreenSize == 1){
			layout.setX(ShiftVar);
		}else if(ScreenSize == 2){
			layout.setY(ShiftVar);
		}
		height = (int)((sh/4));
		Width = (int)((sw*4/10))+1;
		UploadButton.setHeight(height);
		UploadButton.setWidth(Width);
		UploadButton.setX(sw/10);
		SearchButton.setHeight(height);
		SearchButton.setWidth(Width);
		SearchButton.setX(sw/2);
		FavoriteButton.setHeight(height);
		FavoriteButton.setWidth(Width);
		FavoriteButton.setX(sw/10);
		CountButton.setHeight(height);
		CountButton.setWidth(Width);
		CountButton.setX(sw/2);
        UploadButton.setY(sh/4);
        SearchButton.setY(sh/4);
        FavoriteButton.setY(sh/2-1);
        CountButton.setY(sh/2-1);
        print();
		/*layout.post(new Runnable() {
	        @Override
	        public void run() {
	            //sh = layout.getHeight();
	            //sw = layout.getWidth();
	            //bData.putFloat("sw", sw);
				//bData.putFloat("sh", sh);
	            //System.out.println(SizeVar2);
	            
            ////http://www.cnblogs.com/superlcr/p/3940170.html
            ////http://www.eoeandroid.com/thread-240677-1-1.html
	        }
	    });*/
		/*intent.setClass(SearchMain.this, FindCourtMain1.class);
		bData.putString( "KindsOfBall", KindsOfBall);
		bData.putString( "KindsOfArea", KindsOfArea);
		intent.putExtras( bData );
		SearchMain.this.startActivity(intent);*/
		UploadButton.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
				  intent.setClass(ChoseFunction.this, UploadMain.class);
				  intent.putExtras( bData );
				  ChoseFunction.this.startActivity(intent);
			  }
		});
		SearchButton.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
				  intent.setClass(ChoseFunction.this, SearchMain.class);
				  intent.putExtras( bData );
				  ChoseFunction.this.startActivity(intent);
			  }
		});
		FavoriteButton.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
				  intent.setClass(ChoseFunction.this, MyFavority.class);
				  intent.putExtras( bData );
				  ChoseFunction.this.startActivity(intent);
			  }
		});
		CountButton.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
				  intent.setClass(ChoseFunction.this, ScoreBoard.class);
				  intent.putExtras( bData );
				  ChoseFunction.this.startActivity(intent);
			  }
		});
		UploadButton.setOnTouchListener(new Button.OnTouchListener(){
		    @Override
		   public boolean onTouch(View arg0, MotionEvent motionEvent) {
		        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {  //按下的時候改變背景及顏色
		        	bd= new BitmapDrawable(getResources(), bmp5); 
		    		UploadButton.setBackground(bd);
		        }  
		        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {  //起來的時候恢復背景與顏色
		        	bd= new BitmapDrawable(getResources(), bmp1); 
		        	UploadButton.setBackground(bd);
		        }  
		    return false;
		   }
		});
		SearchButton.setOnTouchListener(new Button.OnTouchListener(){
		    @Override
		   public boolean onTouch(View arg0, MotionEvent motionEvent) {
		        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {  //按下的時候改變背景及顏色
		        	bd= new BitmapDrawable(getResources(), bmp6); 
		        	SearchButton.setBackground(bd);
		        }  
		        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {  //起來的時候恢復背景與顏色
		        	bd= new BitmapDrawable(getResources(), bmp2); 
		        	SearchButton.setBackground(bd);
		        }  
		    return false;
		   }
		});
		FavoriteButton.setOnTouchListener(new Button.OnTouchListener(){
		    @Override
		   public boolean onTouch(View arg0, MotionEvent motionEvent) {
		        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {  //按下的時候改變背景及顏色
		        	bd= new BitmapDrawable(getResources(), bmp7); 
		        	FavoriteButton.setBackground(bd);
		        }  
		        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {  //起來的時候恢復背景與顏色
		        	bd= new BitmapDrawable(getResources(), bmp3); 
		        	FavoriteButton.setBackground(bd);
		        }  
		    return false;
		   }
		});
		CountButton.setOnTouchListener(new Button.OnTouchListener(){
		    @Override
		   public boolean onTouch(View arg0, MotionEvent motionEvent) {
		        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {  //按下的時候改變背景及顏色
		        	bd= new BitmapDrawable(getResources(), bmp8); 
		        	CountButton.setBackground(bd);
		        }  
		        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {  //起來的時候恢復背景與顏色
		        	bd= new BitmapDrawable(getResources(), bmp4); 
		        	CountButton.setBackground(bd);
		        }  
		    return false;
		   }
		});
	}
	private void print(){
		bmp1 = fitBitmapB(R.drawable.cf_background1);
		bd= new BitmapDrawable(getResources(), bmp1); 
		//layout.setScaleX(((sh * bmp_bw)/bmp_bh)/sw);
		layout.setBackground(bd);
		//bData.putFloat("sw_last", ((sh * bmp_bw)/bmp_bh));
		bmp1 = fitBitmap(R.drawable.cf_upload);
		bd= new BitmapDrawable(getResources(), bmp1); 
		UploadButton.setBackground(bd);
		bmp2 = fitBitmap(R.drawable.cf_search);
		bd= new BitmapDrawable(getResources(), bmp2); 
		SearchButton.setBackground(bd);
		bmp3 = fitBitmap(R.drawable.cf_favorute);
		bd= new BitmapDrawable(getResources(), bmp3); 
		FavoriteButton.setBackground(bd);
		bmp4 = fitBitmap(R.drawable.cf_count);
		bd= new BitmapDrawable(getResources(), bmp4); 
		CountButton.setBackground(bd);
		bmp5 = fitBitmap(R.drawable.cfd_upload);
        bmp6 = fitBitmap(R.drawable.cfd_search);
        bmp7 = fitBitmap(R.drawable.cfd_favorute);
        bmp8 = fitBitmap(R.drawable.cfd_count);
	}
	private Bitmap fitBitmap(int resId) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		int bmp_bw = temp.getWidth();
		int bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)Width / bmp_bw,(float)height / bmp_bh);
		return Bitmap.createBitmap(temp, 0, 0, bmp_bw, bmp_bh, matrix, true);
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
