package com.example.bv;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnScrollListener;
import android.widget.NumberPicker.OnValueChangeListener;

public class UploadTimeSelect extends Activity implements OnValueChangeListener, 
			Formatter, OnScrollListener {
	private int height, Width, ScreenSize, ShiftVar;
	private static Resources res;
	private BitmapDrawable bd;
	private float sw,sh;
	private float bmp_bw, bmp_bh;
	private float SizeVar;
	private Matrix matrix;
	private Bitmap bmp1;
	private  Bundle bData = new Bundle();
	private FrameLayout layout;
	private CustomNumberPicker mNumberPicker1, mNumberPicker2, mNumberPicker3,
				mNumberPicker4, mNumberPicker5, mNumberPicker6;  
	private LayoutParams params;
	private Button NextPage;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_times_elect);
		res = getResources();
		
		intent = new Intent();
		bData = this.getIntent().getExtras();
		sw = bData.getFloat("sw");
		sh = bData.getFloat("sh");
		ScreenSize = bData.getInt("screen_size");
		SizeVar = bData.getFloat("SizeVar");
		ShiftVar = bData.getInt("ShiftVar");
		
		NextPage = (Button)findViewById(R.id.up_next_page);
		layout = (FrameLayout)findViewById(R.id.timeSelect);
		LayoutParams params =  layout.getLayoutParams();
		params.height = (int)sh;
		params.width = (int)sw;
		layout.setLayoutParams(params);
		if(ScreenSize == 1){
			layout.setX(ShiftVar);
		}else if(ScreenSize == 2){
			layout.setY(ShiftVar);
		}
		print();
		mNumberPicker1 = (CustomNumberPicker) findViewById(R.id.numberPicker1);
		numberPickerInit(mNumberPicker1, 1, 12);
		mNumberPicker2 = (CustomNumberPicker) findViewById(R.id.numberPicker2);
		numberPickerInit(mNumberPicker2, 0, 59);
		mNumberPicker3 = (CustomNumberPicker) findViewById(R.id.numberPicker4);
		numberPickerInit(mNumberPicker3, 1, 12);
		mNumberPicker4 = (CustomNumberPicker) findViewById(R.id.numberPicker5);
		numberPickerInit(mNumberPicker4, 0, 59);
		mNumberPicker5 = (CustomNumberPicker) findViewById(R.id.numberPicker3);
		numberPickerInit(mNumberPicker5);
		mNumberPicker6 = (CustomNumberPicker) findViewById(R.id.numberPicker6);
		numberPickerInit(mNumberPicker6);
		numberPickerSetXY();
		
		NextPage.setY((int)(sh*0.9));
		NextPage.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
				  
				  Bundle argument = new Bundle();
				  argument.putInt("lock", 1);
				  if(mNumberPicker5.getValue() == 0){
					  argument.putInt("HourBegin", mNumberPicker1.getValue());
				  }else{
					  argument.putInt("HourBegin", mNumberPicker1.getValue()+12);
				  }
				  argument.putInt("MinBegin", mNumberPicker2.getValue());

				  if(mNumberPicker6.getValue() == 0){
					  argument.putInt("HourFinish", mNumberPicker3.getValue());
				  }else{
					  argument.putInt("HourFinish", mNumberPicker3.getValue()+12);
				  }
				  argument.putInt("MinFinish", mNumberPicker4.getValue());
				  
				  intent = getIntent();
				  intent.putExtras(argument);
				  setResult(Activity.RESULT_FIRST_USER, intent);
				  UploadTimeSelect.this.finish();
			  }
			  
		});
	}
	private void numberPickerSetXY(){
		params = mNumberPicker1.getLayoutParams();
        params.height = (int)(sh * 0.182);
        params.width = (int)(sw * 0.225);
        mNumberPicker1.setX((int)(sw*0.1));
        mNumberPicker1.setY((int)(sh*0.214));
        
        params = mNumberPicker2.getLayoutParams();
        params.height = (int)(sh * 0.182);
        params.width = (int)(sw * 0.225);
        mNumberPicker2.setX((int)(sw*0.362));
        mNumberPicker2.setY((int)(sh*0.214));
        
        params = mNumberPicker3.getLayoutParams();
        params.height = (int)(sh * 0.182);
        params.width = (int)(sw * 0.225);
        mNumberPicker3.setX((int)(sw*0.1));
        mNumberPicker3.setY((int)(sh*0.443));
        
        params = mNumberPicker4.getLayoutParams();
        params.height = (int)(sh * 0.182);
        params.width = (int)(sw * 0.225);
        mNumberPicker4.setX((int)(sw*0.362));
        mNumberPicker4.setY((int)(sh*0.443));
        
        params = mNumberPicker5.getLayoutParams();
        params.height = (int)(sh * 0.182);
        params.width = (int)(sw * 0.225);
        mNumberPicker5.setX((int)(sw*0.678));
        mNumberPicker5.setY((int)(sh*0.214));
        
        params = mNumberPicker6.getLayoutParams();
        params.height = (int)(sh * 0.182);
        params.width = (int)(sw * 0.225);
        mNumberPicker6.setX((int)(sw*0.676));
        mNumberPicker6.setY((int)(sh*0.443));
	}
	
	
	
	private void numberPickerInit(CustomNumberPicker mNumberPicker, int count1, int count2){
		//mNumberPicker1 = (NumberPicker) findViewById(R.id.numberPickerHour1);  
        mNumberPicker.setFormatter(this);  
        mNumberPicker.setOnValueChangedListener(this);  
        mNumberPicker.setOnScrollListener(this);  
        mNumberPicker.setMaxValue(count2);  
        mNumberPicker.setMinValue(count1);  
        mNumberPicker.setValue(0);
        mNumberPicker.setBackgroundResource(R.drawable.timesele);
        setDividerColor(mNumberPicker);
	}
	private void numberPickerInit(CustomNumberPicker mNumberPicker){
		mNumberPicker.setFormatter(this);  
        mNumberPicker.setOnValueChangedListener(this);  
        mNumberPicker.setOnScrollListener(this);
		mNumberPicker.setMaxValue(1);  
        mNumberPicker.setMinValue(0);
        mNumberPicker.setValue(0);
		mNumberPicker.setDisplayedValues( new String[] { getString(R.string.morning), getString(R.string.night) } );
		mNumberPicker.setBackgroundResource(R.drawable.timesele);
        setDividerColor(mNumberPicker);
	}
	public boolean setDividerColor(NumberPicker numberPicker)
	{
		Field[] numberPickerFields = NumberPicker.class.getDeclaredFields();
	    for (Field field : numberPickerFields) {
            if (field.getName().equals("mSelectionDivider")) {
                field.setAccessible(true);
                try {
                    field.set(numberPicker, ContextCompat.getDrawable(getBaseContext(),
                        R.drawable.white1));
                } catch (Exception ignored) {
                }
                break;
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
		//System.out.println(picker +"oldValue:" + oldVal + "   ; newValue: " + newVal);  
        
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
		bmp1 = fitBitmapB(R.drawable.settime3);
		bd= new BitmapDrawable(getResources(), bmp1); 
		//layout.setScaleX(((sh * bmp_bw)/bmp_bh)/sw);
		layout.setBackground(bd);
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

