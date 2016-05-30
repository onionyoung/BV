package com.example.bv;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	private final static int _DBVersion = 1; //<-- 版本
	private final static String _DBName = "SampleList.db";	//<-- db name
	private final static String _TableName = "MyFavority"; //<-- table name
	public DBHelper(Context context) {
		super(context, _DBName, null, _DBVersion);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		final String SQL = "CREATE TABLE IF NOT EXISTS " + _TableName + "( " +
		                       "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				               "_AREA VARCHAR(20), " +
		                       "_CC VARCHAR(20)," +
				               "_KIND VARCHAR(5)," +
				               "_LANE VARCHAR(30)," +
				               "_NAME VARCHAR(10)," +
				               "_NUMBER VARCHAR(5)," +
				               "_TIME VARCHAR(10)," +
				               "_IMAGE BLOB" +
				           ");";
        db.execSQL(SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		final String SQL = "DROP TABLE " + _TableName;
		db.execSQL(SQL); 
	}

	public List<Item> getAll() {
        List<Item> result = new ArrayList<Item>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
        		_TableName, null, null, null, null, null, null, null);
 
        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
 
        cursor.close();
        return result;
    }
	// 把Cursor目前的資料包裝為物件
    public Item getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Item result = new Item();
        
        result.setTitle(cursor.getString(1));
        result.setCC(cursor.getString(2));
        result.setKind(cursor.getString(3));
        result.setLane(cursor.getString(4));
        result.setName(cursor.getString(5));
        result.setNumber(cursor.getString(6));
        result.setTime(cursor.getString(7));
        if(cursor.getBlob(8)!=null){
        	result.setBitmap(cursor.getBlob(8));
        }
        // 回傳結果
        return result;
    }
}