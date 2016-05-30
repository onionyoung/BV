package com.example.bv;

import java.io.ByteArrayOutputStream;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.TextView;

public class UploadSQlite {
	private Button sample1;
    private Button viewcode1;
    private DBHelper DH = null;
    private List<Item> items;
    private TextView textView1;
    private Context context;
    public UploadSQlite(Context context){
    	this.context = context;
    	openDB();
    }
    private void openDB(){
    	DH = new DBHelper(context);  
    }
    private void closeDB(){
    	DH.close();    	
    }
    public void add(String Title, String CC, String Kind, String Lane, String Name, 
    		String Number, String Time){
        SQLiteDatabase db = DH.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_AREA", Title.toString());
        values.put("_CC", CC.toString());
        values.put("_KIND", Kind.toString());
        
        values.put("_LANE", Lane.toString());
        values.put("_NAME", Name.toString());
        values.put("_NUMBER", Number.toString());
        values.put("_TIME", Time.toString());
        db.insert("MyFavority", null, values);
        closeDB();
    }
    /////unfinished
    public void add(String Title, String CC, String Kind, String Lane, String Name, 
    		String Number, String Time, Bitmap bmp){
        SQLiteDatabase db = DH.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_AREA", Title.toString());
        values.put("_CC", CC.toString());
        values.put("_KIND", Kind.toString());
        
        values.put("_LANE", Lane.toString());
        values.put("_NAME", Name.toString());
        values.put("_NUMBER", Number.toString());
        values.put("_TIME", Time.toString());
        ////
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
        values.put(("_IMAGE"), os.toByteArray());
        ///
        db.insert("MyFavority", null, values);
        closeDB();
    }
}
