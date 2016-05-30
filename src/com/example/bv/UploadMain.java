package com.example.bv;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import com.parse.ParseFile;
import com.parse.ParseObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.parse.ParseObject;
//;

public class UploadMain extends Activity implements OnClickListener{
	private Button FinishButton, BasketballButton, VolleyballButton, 
		SchoolButton, CommunityButton, ButtonNSand, ButtonSand,
		button1, button2, button3,EditTextCC,Area;
	private String KindsOfBall = "null", KindsOfArea = "null", str = "not chose";
	private String location = null;
	private EditText Lane, Num, Name;
	private TextView textView1, textView2, textView3, textView4
						, textView5, textView6, textView7, textView8;
	private Button up2_confirm;
	private int state = 0, statePic = 0, stateCC = -1;
	private UploadWebpage uploadWebpage = new UploadWebpage();
	
	private FrameLayout layout,layout_confirm;
	private int sh, sw;
	private float ScreenSize, SizeVar, ShiftVar;
	private float sw_last;
	//private float w;
	private float bmp_bw, bmp_bh;
	private int TWArea=0;
	private static Resources res;
	private Matrix matrix;
	private Bitmap bmp1,bmp2,bmp3,bmp4,bmp5,bmp6,bmp7,bmp8;
	private Bitmap bmpR1,bmpR2,bmpR3,bmpR4;
	private Bitmap bmpOther, bmpUpload;
	private BitmapDrawable bd;
	private Intent intent;
	private Bundle bData, argument;
	 private final static int PHOTO = 99 ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_main);
		res = getResources();
		intent = new Intent();
		
		BasketballButton = (Button)findViewById(R.id.BasketballButton);
		VolleyballButton = (Button)findViewById(R.id.VolleyballButton);
		SchoolButton = (Button)findViewById(R.id.SchoolButton);
		CommunityButton = (Button)findViewById(R.id.CommunityButton);
		FinishButton = (Button)findViewById(R.id.FinishButton);
		ButtonNSand = (Button)findViewById(R.id.ButtonNSand);
		ButtonSand = (Button)findViewById(R.id.ButtonSand);
		EditTextCC = (Button)findViewById(R.id.editTextCC);
		Area = (Button)findViewById(R.id.area);
		//TestButton = (Button)findViewById(R.id.test_button);
		
		BasketballButton.setOnClickListener(this);
		VolleyballButton.setOnClickListener(this);
		SchoolButton.setOnClickListener(this);
		CommunityButton.setOnClickListener(this);
		FinishButton.setOnClickListener(this);
		ButtonNSand.setOnClickListener(this);
		ButtonSand.setOnClickListener(this);
		EditTextCC.setOnClickListener(this);
		Area.setOnClickListener(this);
		//TestButton.setOnClickListener(this);
		
		Lane = (EditText)findViewById(R.id.lane);
		Num = (EditText)findViewById(R.id.num);
		Name = (EditText)findViewById(R.id.name);
		
		bData = this.getIntent().getExtras();
		sw = (int)bData.getFloat("sw");
		sh = (int)bData.getFloat("sh");
		sw_last = bData.getFloat("sw");
		ScreenSize = bData.getInt("screen_size");
		SizeVar = bData.getFloat("SizeVar");
		ShiftVar = bData.getInt("ShiftVar");
		layout = (FrameLayout)findViewById(R.id.up_layout);
		
		//layout shift
		LayoutParams params =  layout.getLayoutParams();
		params.height = (int)sh;
		params.width = (int)sw;
		layout.setLayoutParams(params);
		if(ScreenSize == 1){
			layout.setX(ShiftVar);
		}else if(ScreenSize == 2){
			layout.setY(ShiftVar);
		}
		
    	bmp1 = fitBitmapB(R.drawable.up_background);
		bd= new BitmapDrawable(getResources(), bmp1); 
		//layout.setScaleX(sw_last/sw);
		layout.setBackground(bd);
		
    	buttonSet();
    	print();
	}
	private void buttonSet(){
		//w =((sh * bmp_bw)/bmp_bh);
		BasketballButton.setX((int)(sw/20));
		VolleyballButton.setX((int)(sw/2.76));
		BasketballButton.setY((int)(sh/12));
		VolleyballButton.setY((int)(sh/12));
		SchoolButton.setY((int)(sh*0.246));
		CommunityButton.setX((int)(sw*0.324));
		CommunityButton.setY((int)(sh*0.246));
		
		Name.setY((int)(sh*0.365));
		EditTextCC.setY((int)(sh*0.497));//496
		Area.setY((int)(sh*0.497));
		Area.setX((int)(sw*0.55));
		Lane.setY((int)(sh*0.62));
		Num.setY((int)(sh*0.74));
		ButtonNSand.setY((int)(sh*0.74));
		ButtonNSand.setX((int)(sw*0.67));
		ButtonSand.setY((int)(sh*0.74));
		ButtonSand.setX((int)(sw*0.82));
		FinishButton.setY((int)(sh*0.88));
	}
	private void print(){
		bmp1 = fitBitmap(R.drawable.up_vball, (int)(sw*0.23), (int)(sh*0.15));
		bmp2 = fitBitmap(R.drawable.up_bball, (int)(sw*0.23), (int)(sh*0.15));
		bmp3 = fitBitmap(R.drawable.up_vballd, (int)(sw*0.23), (int)(sh*0.15));
		bd= new BitmapDrawable(getResources(), bmp1); 
		VolleyballButton.setBackground(bd);
		bmp4 = fitBitmap(R.drawable.up_bballd, (int)(sw*0.23), (int)(sh*0.15));
		bd= new BitmapDrawable(getResources(), bmp2); 
		BasketballButton.setBackground(bd);
		
		bmp5 = fitBitmap(R.drawable.up_school, (int)(sw*0.324), (int)(sh*0.088));
		bmp6 = fitBitmap(R.drawable.up_other, (int)(sw*0.324), (int)(sh*0.088));
		bmp7 = fitBitmap(R.drawable.upd_school, (int)(sw*0.324), (int)(sh*0.088));
		bd= new BitmapDrawable(getResources(), bmp5); 
		SchoolButton.setBackground(bd);
		bmp8 = fitBitmap(R.drawable.upd_other, (int)(sw*0.324), (int)(sh*0.088));
		bd= new BitmapDrawable(getResources(), bmp6); 
		CommunityButton.setBackground(bd);
		
		bmpOther = fitBitmap(R.drawable.up_name, (int)(sw*0.648), (int)(sh*0.088));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		Name.setBackground(bd);
		bmpOther = fitBitmap(R.drawable.whiteb, (int)(sw*0.29), (int)(sh*0.088));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		EditTextCC.setBackground(bd);
		bmpOther = fitBitmap(R.drawable.whiteb, (int)(sw*0.29), (int)(sh*0.088));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		Area.setBackground(bd);
		bmpOther = fitBitmap(R.drawable.up_name, (int)(sw*0.79), (int)(sh*0.088));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		Lane.setBackground(bd);
		bmpOther = fitBitmap(R.drawable.whiteb, (int)(sw*0.29), (int)(sh*0.088));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		Num.setBackground(bd);
		bmpR1 = fitBitmap(R.drawable.up_sand, (int)(sw*0.15), (int)(sh*0.088));
		bmpR2 = fitBitmap(R.drawable.up_nsand, (int)(sw*0.15), (int)(sh*0.088));
		bmpR3 = fitBitmap(R.drawable.upd_sand, (int)(sw*0.15), (int)(sh*0.088));
		bd= new BitmapDrawable(getResources(), bmpR3); 
		ButtonSand.setBackground(bd);
		bmpR4 = fitBitmap(R.drawable.upd_nsand, (int)(sw*0.15), (int)(sh*0.088));
		bd= new BitmapDrawable(getResources(), bmpR4); 
		ButtonNSand.setBackground(bd);
		bmpOther = fitBitmap(R.drawable.up_next, (int)(sw), (int)(sh*0.080));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		FinishButton.setBackground(bd);
	}
	private Bitmap fitBitmap(int resId, int Width, int height) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		int bmp_bw = temp.getWidth();
		int bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)Width / bmp_bw  ,(float)height / bmp_bh );
		return Bitmap.createBitmap(temp, 0, 0, bmp_bw, bmp_bh, matrix, true);
	}
	private Bitmap fitBitmap(Bitmap temp, int Width, int height) {
		int bmp_bw = temp.getWidth();
		int bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)Width / bmp_bw  ,(float)height / bmp_bh );
		return Bitmap.createBitmap(temp, 0, 0, bmp_bw, bmp_bh, matrix, true);
	}
	private Bitmap fitBitmapB(int resId) {
		Bitmap temp = BitmapFactory.decodeResource(res, resId);
		bmp_bw = temp.getWidth();
		bmp_bh = temp.getHeight();
		matrix = new Matrix();
		matrix.reset();
		matrix.postScale((float)sw_last / bmp_bw,(float)sh / bmp_bh);
		return Bitmap.createBitmap(temp, 0, 0, (int)bmp_bw, (int)bmp_bh, matrix, true);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.FinishButton: 
				bmpOther = fitBitmap(R.drawable.upd_next, (int)(sw), (int)(sh*0.080));
				bd= new BitmapDrawable(getResources(), bmpOther); 
				FinishButton.setBackground(bd);
				if(KindsOfBall.equals("null") || KindsOfArea.equals("null")){
					Toast.makeText(UploadMain.this, getString(R.string.confirm1),Toast.LENGTH_SHORT ).show();
				}else if(Name.getText().toString().equals("")){
					Toast.makeText(UploadMain.this, getString(R.string.confirm2),Toast.LENGTH_SHORT ).show();
				}else if(EditTextCC.getText().toString().equals("") || Area.getText().toString().equals("")){
					Toast.makeText(UploadMain.this, getString(R.string.confirm3),Toast.LENGTH_SHORT ).show();
				}else if(Lane.getText().toString().equals("") || Num.getText().toString().equals("")){
					Toast.makeText(UploadMain.this, getString(R.string.confirm4),Toast.LENGTH_SHORT ).show();
				}else{//****Lane
					str="";
					if(state==1){
						str=getString(R.string.NotSand);
					}else if(state==2){
						str=getString(R.string.Sand);
					}else if(state==3){
						str=getString(R.string.Sand) + " "+getString(R.string.NotSand);
					}
					location = EditTextCC.getText().toString() 
							+ Area.getText().toString()
							+ Lane.getText().toString()
							+ Num.getText().toString();
					bmpR1.recycle();bmp1.recycle();bmp5.recycle();
					bmpR2.recycle();bmp2.recycle();bmp6.recycle();
					bmpR3.recycle();bmp3.recycle();bmp7.recycle();
					bmpR4.recycle();bmp4.recycle();bmp8.recycle();
					uploadmain2();
					//MyAlertDialog();
				}
				bmpOther = fitBitmap(R.drawable.up_next, (int)(sw), (int)(sh*0.080));
				bd= new BitmapDrawable(getResources(), bmpOther); 
				FinishButton.setBackground(bd);
				//myClickHandler(location);
				//Toast.makeText(UploadMain.this, location,Toast.LENGTH_SHORT ).show();
				break;
			case R.id.BasketballButton:
				ButtonChose("basketball");
				break;
			case R.id.VolleyballButton:
				ButtonChose("volleyball");
				break;
			case R.id.SchoolButton:
				ButtonChose("school");
				break;
			case R.id.CommunityButton:
				ButtonChose("community");
				break;
			/*case R.id.test_button:
				MyAlertDialog();
				break;*/
			case R.id.ButtonNSand:
				ButtonChose("NSand");
				break;
			case R.id.ButtonSand:
				ButtonChose("Sand");
				break;
			case R.id.editTextCC:
				ButtonChose("CC");
				break;
			case R.id.area:
				ButtonChose("area");
				break;
			default:
				break;
		}
	}
	public void ButtonChose(String str){
		if(str.equals("basketball")){
			KindsOfBall = "basketball";
			bd= new BitmapDrawable(getResources(), bmp4); 
			BasketballButton.setBackground(bd);
			bd= new BitmapDrawable(getResources(), bmp1); 
			VolleyballButton.setBackground(bd);
		}else if(str.equals("volleyball")){
			KindsOfBall = "volleyball";
			bd= new BitmapDrawable(getResources(), bmp3); 
			VolleyballButton.setBackground(bd);
			bd= new BitmapDrawable(getResources(), bmp2); 
			BasketballButton.setBackground(bd);
		}else if(str.equals("school")){
			KindsOfArea = "school";
			bd= new BitmapDrawable(getResources(), bmp7); 
			SchoolButton.setBackground(bd);
			bd= new BitmapDrawable(getResources(), bmp6); 
			CommunityButton.setBackground(bd);
		}else if(str.equals("community")){
			KindsOfArea = "community";
			bd= new BitmapDrawable(getResources(), bmp5); 
			SchoolButton.setBackground(bd);
			bd= new BitmapDrawable(getResources(), bmp8); 
			CommunityButton.setBackground(bd);
		}else if(str.equals("NSand")){
			if(state == 0 || state == 2){
				state++;
				bd= new BitmapDrawable(getResources(), bmpR2); 
				ButtonNSand.setBackground(bd);
			}else{
				state--;
				bd= new BitmapDrawable(getResources(), bmpR4); 
				ButtonNSand.setBackground(bd);
			}
		}else if(str.equals("Sand")){
			if(state == 1 || state == 0){
				state=state+2;
				bd= new BitmapDrawable(getResources(), bmpR1); 
				ButtonSand.setBackground(bd);
			}else{
				state=state-2;
				bd= new BitmapDrawable(getResources(), bmpR3); 
				ButtonSand.setBackground(bd);
			}
		}else if(str.equals("CC")){
			CCorArarChick("CC");
		}else if(str.equals("area")){
			CCorArarChick("area");
		}
	}
	private void CCorArarChick(final String str) {
		final int[] LOOKUP_TABLE = new int[] {
			   R.string.TW1, R.string.TW2, R.string.TW3, R.string.TW4, R.string.TW5,
			   R.string.TW6, R.string.TW7, R.string.TW8, R.string.TW9, R.string.TW10,
			   R.string.TW11, R.string.TW12, R.string.TW13, R.string.TW14, R.string.TW15, 
			   R.string.TW16, R.string.TW17, R.string.TW18, R.string.TW19, R.string.TW20,
			   R.string.TW21, R.string.TW22, 
			};
		String stringXml = null;
		if(str.equals("CC"))stringXml = getString(R.string.TWarea);
		else if(str.equals("area")){
			if(stateCC==-1){
				return;
			}else{
				stringXml = getString(LOOKUP_TABLE[stateCC]);
			}
		}else return;
		final String[] tokens = stringXml.split(":");
		
		Builder MyListAlertDialog = new AlertDialog.Builder(this);
		MyListAlertDialog.setTitle(getString(R.string.CC));
		// 建立List的事件
		DialogInterface.OnClickListener ListClick = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//Toast.makeText(Basis_AlertDialogActivity.this, ListStr[which],// 顯示所點選的選項
				//		Toast.LENGTH_LONG).show();
				if(str.equals("CC")){
					EditTextCC.setText(tokens[which]);
					Area.setText("");
					stateCC=which;
				}else if(str.equals("area")){
					Area.setText(tokens[which]);
				}
			}
		};
		// 建立按下取消什麼事情都不做的事件
		DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		};
		MyListAlertDialog.setItems(tokens, ListClick);
		MyListAlertDialog.setNeutralButton("Cancel", OkClick);
		MyListAlertDialog.show();
	}
	public void MyAlertDialog(){
		//LayoutInflater inflater = LayoutInflater.from(UploadMain.this);
	    AlertDialog myDialog = new AlertDialog.Builder(UploadMain.this).create();
	    myDialog.show();
	    /***********/ 
	    //layout_confirm = (FrameLayout)findViewById(R.id.up_confirm);
	    Window dialogWindow = myDialog.getWindow();
	    float w = sw_last*4/5;
	    //Parse.enableLocalDatastore(this);
 		//Parse.initialize(this, "buX2uqJ5EkcNYOZYgDnZnPmGCBNhvKEjm4pxkaVc", "BfkGSk6Tg6VmSDfnoW8xwAqHrXe5EgiYJHWkm6uC");
	    myDialog.getWindow().setLayout((int)w, (int)(w/13*20));
	    myDialog.getWindow().setBackgroundDrawableResource(R.drawable.up_confirm_background);
	    myDialog.getWindow().setContentView(R.layout.upload_confirm); 
	   
		/***************/
	    textView1 = (TextView)dialogWindow.findViewById(R.id.textView1);
		textView2 = (TextView)dialogWindow.findViewById(R.id.textView2);
		textView3 = (TextView)dialogWindow.findViewById(R.id.textView3);
		textView4 = (TextView)dialogWindow.findViewById(R.id.textView4);
		textView5 = (TextView)dialogWindow.findViewById(R.id.textView5);
		textView6 = (TextView)dialogWindow.findViewById(R.id.textView6);
		textView7 = (TextView)dialogWindow.findViewById(R.id.textView7);
		textView8 = (TextView)dialogWindow.findViewById(R.id.textView8);
		textView1.setText(Name.getText().toString());
		if(KindsOfArea.equals("school")){
			textView2.setText(getString(R.string.School));
		}else{
			textView2.setText(getString(R.string.Community));
		}
		textView3.setText(EditTextCC.getText().toString() + Area.getText().toString() + Lane.getText().toString());
		textView4.setText(Num.getText().toString());
		textView5.setText(str);
		textView6.setText(timeChange(argument.getInt("HourBegin"),1) + ":" + timeChange(argument.getInt("MinBegin"),0) + 
				" ~ " + timeChange(argument.getInt("HourFinish"),1) + ":" + timeChange(argument.getInt("MinFinish"),0));
		
		textView7.setText(getString(R.string.unfinish));
		textView8.setText(getString(R.string.unfinish));
		button1 = (Button)dialogWindow.findViewById(R.id.button1);
		button2 = (Button)dialogWindow.findViewById(R.id.captureimage);
		button3 = (Button)dialogWindow.findViewById(R.id.button3);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0){
				Intent intent = new Intent();
		         intent.setType("image/*");
		         intent.setAction(Intent.ACTION_GET_CONTENT);
		         startActivityForResult(intent, PHOTO);
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			  public void onClick(View arg0){
				Intent intent_camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		    	startActivityForResult(intent_camera, 0);
			}
		});
		up2_confirm = (Button)dialogWindow.findViewById(R.id.up2_confirm);
		up2_confirm.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
				  int i=0;
		        	 if(KindsOfBall.equals("basketball")){
		     			i=i+8;
		     		 }else{}
		        	 if(KindsOfArea.equals("school")){
		     			i=i+4;
		     		 }else{}
		        	 if(str.equals(getString(R.string.Sand))){
		        		 i=i+1;
		        	 }else if(str.equals(getString(R.string.NotSand))){
		        		 i=i+2;
		        	 }else if(str.equals(getString(R.string.Sand) + " " + getString(R.string.NotSand))){
		        		 i=i+3;
		        	 }else{}
		        	 /*myClickHandler(Integer.toString(i)
		        			 	, Name.getText().toString()
		        			    , EditTextCC.getText().toString() 
								, Area.getText().toString()
								, Lane.getText().toString()
								, Num.getText().toString());*/
		        	
		     		ParseObject testObject = new ParseObject("imformation");
		     		testObject.put("kind", Integer.toString(i));
		     		testObject.put("name", Name.getText().toString());
		     		testObject.put("cc", EditTextCC.getText().toString() );
		     		testObject.put("area", Area.getText().toString());
		     		testObject.put("lane", Lane.getText().toString());
		     		testObject.put("number", Num.getText().toString());
		     		testObject.put("time", timeChange(argument.getInt("HourBegin"),1) + ":" + timeChange(argument.getInt("MinBegin"),0) + 
		    				" ~ " + timeChange(argument.getInt("HourFinish"),1) + ":" + timeChange(argument.getInt("MinFinish"),0));
		     		if(statePic == 1){
		     			// Convert it to byte
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						// Compress image to lower quality scale 1 - 100
						bmpUpload.compress(Bitmap.CompressFormat.PNG, 100, stream);
						byte[] image = stream.toByteArray();
		 
						// Create the ParseFile
						ParseFile file = new ParseFile("androidbegin.png", image);
						// Upload the image into Parse Cloud
						file.saveInBackground();
		     			
						testObject.put("pic", file);
		     		}
		     		testObject.saveInBackground();
		     		Toast.makeText(UploadMain.this, getString(R.string.success),Toast.LENGTH_SHORT ).show();
		        	UploadMain.this.finish();
			  }
		});
		 printDialogItem();

	    
	}
	private String timeChange(int time, int HourOrMin){
		if(HourOrMin == 1){
			if(time == 24){
				return "00";
			}
		}
		if(time <= 9){
			return "0" + time;
		}
		return ""+time;
		
	}
	private void printDialogItem(){
		float i = (float) 0.051;
		int alpha = 150;
		bmpOther = fitBitmap(R.drawable.whiteb2, (int)(sw_last*0.48), (int)(sh*0.038));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		textView1.setBackground(bd);
		textView1.getBackground().setAlpha(alpha);
		textView1.setX((int)(sw_last*0.263));//165
		textView1.setY((int)(sh*0.082));//80
		textView2.setBackground(bd);
		textView2.getBackground().setAlpha(alpha);
		textView2.setX((int)(sw_last*0.263));
		textView2.setY((int)(sh*(0.085+i)));
		textView4.setBackground(bd);
		textView4.getBackground().setAlpha(alpha);
		textView4.setX((int)(sw_last*0.263));
		textView4.setY((int)(sh*(0.085+i*3)));
		textView6.setBackground(bd);
		textView6.getBackground().setAlpha(alpha);
		textView6.setX((int)(sw_last*0.263));
		textView6.setY((int)(sh*(0.085+i*5)));
		bmpOther = fitBitmap(R.drawable.whiteb2, (int)(sw_last*0.566), (int)(sh*0.038));
		bd= new BitmapDrawable(getResources(), bmpOther); 
		textView3.setBackground(bd);
		textView3.getBackground().setAlpha(alpha);
		textView3.setX((int)(sw_last*0.177));
		textView3.setY((int)(sh*(0.085+i*2)));
		textView5.setBackground(bd);
		textView5.getBackground().setAlpha(alpha);
		textView5.setX((int)(sw_last*0.177));
		textView5.setY((int)(sh*(0.085+i*4)));
		bmpOther = fitBitmap(R.drawable.whiteb2, (int)(sw_last*0.566), (int)(sh*0.076));
		bd= new BitmapDrawable(getResources(), bmpOther);
		textView7.setBackground(bd);
		textView7.getBackground().setAlpha(alpha);
		textView7.setX((int)(sw_last*0.177));
		textView7.setY((int)(sh*(0.085+i*6)));
		bmpOther = fitBitmap(R.drawable.up_confirm_background2, (int)(sw_last*0.676), (int)(sw_last*0.330));
		bd= new BitmapDrawable(getResources(), bmpOther);
		textView8.setBackground(bd);
		textView8.getBackground().setAlpha(alpha);
		textView8.setX((int)(sw_last*0.065));
		textView8.setY((int)(sh*(0.085+i*7+0.04)));
		bmpOther = fitBitmap(R.drawable.up_confirmbutton, (int)(sw_last*0.19), (int)(sw_last*0.09));
		bd= new BitmapDrawable(getResources(), bmpOther);
		up2_confirm.setBackground(bd);
		up2_confirm.setX((int)(sw_last*0.3));
		up2_confirm.setY((int)((sh*(0.085+i*7))+(sw_last*0.4)));//sw== sw_last*1.254
		bmpOther = fitBitmap(R.drawable.up_capture, (int)(sw_last*0.125), (int)(sw_last*0.15));
		bd= new BitmapDrawable(getResources(), bmpOther);
		button1.setBackground(bd);
		button1.setX((int)(sw_last*0.179));
		button1.setY((int)(sh*(0.085+i*8+0.04+0.025)));
		bmpOther = fitBitmap(R.drawable.up_camera, (int)(sw_last*0.15), (int)(sw_last*0.119));
		bd= new BitmapDrawable(getResources(), bmpOther);
		button2.setBackground(bd);
		button2.setX((int)(sw_last*0.351));
		button2.setY((int)(sh*(0.085+i*8+0.04+0.035)));
		/*bmpOther = fitBitmap(R.drawable.up_no, (int)(sw_last*0.1), (int)(sh*0.05));
		bd= new BitmapDrawable(getResources(), bmpOther);
		button3.setBackground(bd);
		button3.setX((int)(sw_last*0.539));
		button3.setY((int)(sh*(0.085+i*8+0.08)));*/
		button3.setBackgroundColor(0);
	}
	public void myClickHandler(String... str) {
        // Gets the URL from the UI's text field.
        String stringUrl = "http://www.dodgeg.net84.net/appData/getData.php";
        //urlText.getText().toString();
        ConnectivityManager connMgr = (ConnectivityManager) 
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            uploadWebpage.execute(str);
        } else {
            //textView.setText("No network connection available.");
        }
    }
	private void uploadmain2(){
		/*setContentView(R.layout.upload_main2);
		Button finish;
		finish = (Button)findViewById(R.id.finish);
		layout = (FrameLayout)findViewById(R.id.up2_layout);
		
    	bmp1 = fitBitmapB(R.drawable.settime);
		bd= new BitmapDrawable(getResources(), bmp1); 
		layout.setScaleX(sw_last/sw);
		layout.setBackground(bd);
	    		
		finish.setOnClickListener(new OnClickListener() {
			  @Override
			  public void onClick(View arg0) {
				  MyAlertDialog();
			  }
		});*/
		intent.setClass(UploadMain.this, UploadTimeSelect.class);
		intent.putExtras( bData );
		UploadMain.this.startActivityForResult(intent, 0);
	}
	@Override // 覆寫 onActivityResult，傳值回來時會執行此方法。
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)return;
		Uri uri = data.getData();
        ContentResolver cr = this.getContentResolver();
        if(requestCode == PHOTO && data != null){
	        //Size=2為將原始圖片縮小1/2，Size=4為1/4，以此類推
			try {
				BitmapFactory.Options mOptions = new BitmapFactory.Options();
				mOptions.inSampleSize = 2; 
				Bitmap bmp = BitmapFactory.decodeStream(cr.openInputStream(uri),null,mOptions);
				bmpUpload = fitBitmap(bmp, (int)(sw_last*0.125), (int)(sw_last*0.15));
				statePic = 1;
		        button1.setBackground(new BitmapDrawable(getResources(), bmpUpload));
		      //清除圖片的圖
		        bmpOther = fitBitmap(R.drawable.up_camera, (int)(sw_last*0.15), (int)(sw_last*0.119));
				bd= new BitmapDrawable(getResources(), bmpOther);
				button2.setBackground(bd);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }else if (resultCode == Activity.RESULT_FIRST_USER) {
            //將包裹從 Intent 中取出。
        	argument = data.getExtras();
            if(argument.getInt("lock") == 1){
    			MyAlertDialog();
    		}
        }else if(resultCode == RESULT_OK && data != null){
        	try {
				BitmapFactory.Options mOptions = new BitmapFactory.Options();
				mOptions.inSampleSize = 2; 
				Bitmap bmp = BitmapFactory.decodeStream(cr.openInputStream(uri),null,mOptions);
				bmpUpload = fitBitmap(bmp, (int)(sw_last*0.15), (int)(sw_last*0.119));
				statePic = 1;
				button2.setBackground(new BitmapDrawable(getResources(), bmpUpload));
				//清除相機的圖
				bmpOther = fitBitmap(R.drawable.up_capture, (int)(sw_last*0.125), (int)(sw_last*0.15));
				bd= new BitmapDrawable(getResources(), bmpOther);
				button1.setBackground(bd);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
        
	     //覆蓋原來的Activity
	     super.onActivityResult(requestCode, resultCode, data);
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

