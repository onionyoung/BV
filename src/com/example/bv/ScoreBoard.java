package com.example.bv;


import java.lang.reflect.Field;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnScrollListener;
import android.widget.NumberPicker.OnValueChangeListener;

public class ScoreBoard extends Activity implements OnValueChangeListener, 
					Formatter, OnScrollListener{
	private Button VSButton;
	private FrameLayout layout;
	private LinearLayout layoutS_right, layoutS_left;
	private static Resources res;
	private Matrix matrix;
	private Bitmap bmp1;
	private BitmapDrawable bd;
	private float sw,sh;
	private float bmp_bw, bmp_bh;
	private int h,w;
	private LayoutParams params;
	private CustomNumberPicker mNumberPicker;  
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_board);
		
		res = getResources();
		
		layout = (FrameLayout)findViewById(R.id.score_background);
		layoutS_right = (LinearLayout)findViewById(R.id.layoutS_right);
		layoutS_left = (LinearLayout)findViewById(R.id.layoutS_left);
		params =  layout.getLayoutParams();
		
		numberPickerInit();
		layout.post(new Runnable() {
	        @Override
	        public void run() {
	        	sh = layout.getHeight();
	        	sw = layout.getWidth();
	        	print();
	        }
		});
		
		VSButton = (Button)findViewById(R.id.button_vs);
		VSButton.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
				  mNumberPicker = (CustomNumberPicker) findViewById(R.id.numberPicker1);
				  mNumberPicker.setValue(0);
				  mNumberPicker = (CustomNumberPicker) findViewById(R.id.numberPicker2); 
				  mNumberPicker.setValue(0);
			  }
		});
		//print();
	}

	private void numberPickerInit(){
		mNumberPicker = (CustomNumberPicker) findViewById(R.id.numberPicker1);  
        mNumberPicker.setFormatter(this);  
        mNumberPicker.setOnValueChangedListener(this);  
        mNumberPicker.setOnScrollListener(this);  
        mNumberPicker.setMaxValue(23);  
        mNumberPicker.setMinValue(0);  
        mNumberPicker.setValue(0);
        
        mNumberPicker.setBackgroundResource(R.drawable.numpickerb);
        //mNumberPicker.setBackgroundColor(Color.parseColor("#FFFFFF"));
        setNumberPickerTextColor(mNumberPicker,Color.parseColor("#000000"));
        
        mNumberPicker = (CustomNumberPicker) findViewById(R.id.numberPicker2);  
        mNumberPicker.setFormatter(this);  
        mNumberPicker.setOnValueChangedListener(this);  
        mNumberPicker.setOnScrollListener(this);  
        mNumberPicker.setMaxValue(23);  
        mNumberPicker.setMinValue(0);  
        mNumberPicker.setValue(0);  
        mNumberPicker.setBackgroundResource(R.drawable.numpickerb);
        setNumberPickerTextColor(mNumberPicker,Color.parseColor("#000000"));
	}
	public boolean setNumberPickerTextColor(CustomNumberPicker numberPicker, int color)
	{
	    final int count = numberPicker.getChildCount();
	    for(int i = 0; i < count; i++){
	        View child = numberPicker.getChildAt(i);
	        if(child instanceof EditText){
	            try{
	                Field selectorWheelPaintField = numberPicker.getClass()
	                    .getDeclaredField("mSelectorWheelPaint");
	                selectorWheelPaintField.setAccessible(true);
	                ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
	                ((EditText)child).setTextColor(color);
	                //bmp1 = fitBitmapB(R.drawable.scoreboard_background);
	        		//bd= new BitmapDrawable(getResources(), bmp1); 
	        		//((EditText)child).setBackground(bd);
	                numberPicker.invalidate();
	                return true;
	            }
	            catch(NoSuchFieldException e){
	                Log.w("setNumberPickerTextColor", e);
	            }
	            catch(IllegalAccessException e){
	                Log.w("setNumberPickerTextColor", e);
	            }
	            catch(IllegalArgumentException e){
	                Log.w("setNumberPickerTextColor", e);
	            }
	        }
	    }
	    return false;
	}
	public String format(int value) {  
        String tmpStr = String.valueOf(value);  
        if (value < 10) {  
            tmpStr = "0" + tmpStr;  
        }  
        return tmpStr;  
    }  
	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {  
        /*Log.i("tag", "oldValue:" + oldVal + "   ; newValue: " + newVal);  
        Toast.makeText(  
                this,  
                "number changed --> oldValue: " + oldVal + " ; newValue: "  
                        + newVal, Toast.LENGTH_SHORT).show();  */
    } 
    public void onScrollStateChange(NumberPicker view, int scrollState) {  
        switch (scrollState) {  
        case OnScrollListener.SCROLL_STATE_FLING:  
            //Toast.makeText(this, "scroll state fling", Toast.LENGTH_LONG).show();  
            break;  
        case OnScrollListener.SCROLL_STATE_IDLE:  
            //Toast.makeText(this, "scroll state idle", Toast.LENGTH_LONG).show();  
            break;  
        case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:  
            //Toast.makeText(this, "scroll state touch scroll", Toast.LENGTH_LONG).show();  
            break;  
        }  
  
    } 
	
    private void print(){
		params =  layout.getLayoutParams();
		bmp1 = fitBitmapB(R.drawable.scoreboard_background);
		bd= new BitmapDrawable(getResources(), bmp1); 
		w = (int)((sh * bmp_bw)/bmp_bh) ;
		h = (int)((sw * bmp_bh)/bmp_bw) ;
		if(w<sw ){
			params.height = (int)sh;
			params.width = (int)w;
			layout.setX((sw-w)/2);
		}else if(w>sw ){
			params.height = (int)h;
			params.width = (int)sw;
			layout.setY((sh-h)/2);
		}else{
			
		}
		layout.setBackground(bd);
		
		bmp1 = fitBitmap(R.drawable.vsbutton);
		bd= new BitmapDrawable(getResources(), bmp1);
		VSButton.setHeight((int)(params.width/5));
		VSButton.setWidth((int)(params.width/5));
		VSButton.setY(((params.height*170)/390));
		System.out.println(((params.height*203)/390) +"+"+VSButton.getY());
		VSButton.setBackground(bd);
		int h = params.height, w = params.width;
		params =  layoutS_left.getLayoutParams();
		params.height = (int)(h);
		params.width = (int)(w/3);
		layoutS_left.setX(20);
		layoutS_left.setY((int)(h*0.023));
		params =  layoutS_right.getLayoutParams();
		params.height = (int)(h);
		params.width = (int)(w/3);
		layoutS_right.setX(w-20-(w/3));
		layoutS_right.setY((int)(h*0.023));
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
	private Bitmap fitBitmap(int resId) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		int bmp_bw = temp.getWidth();
		int bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)params.width/5 / bmp_bw,(float)params.width/5 / bmp_bh);
		return Bitmap.createBitmap(temp, 0, 0, bmp_bw, bmp_bh, matrix, true);
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
