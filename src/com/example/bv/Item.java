package com.example.bv;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

public class Item implements java.io.Serializable {

    // Title, CC, Kind, Lane, Name, Number, Time
    private long id;
    private long datetime;
    private String Title, CC, Kind, Lane, Name, Number, Time;
    private Bitmap bd;
    
    // 提醒日期時間
    private long alarmDatetime;

    public Item() {
    }
    
    public Item(String Title, String CC, String Kind, String Lane, String Name
    		, String Number, String Time, Bitmap bd) {
    	this.Title = Title;
    	this.CC = CC;
    	this.Kind = Kind;
    	this.Lane = Lane;
    	this.Name = Name;
    	this.Number = Number;
    	this.Time = Time;
    	this.bd = bd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDatetime() {
        return datetime;
    }
    /*
    // 裝置區域的日期時間
    public String getLocaleDatetime() {
        return String.format(Locale.getDefault(), "%tF  %<tR", new Date(datetime));
    }

    // 裝置區域的日期
    public String getLocaleDate() {
        return String.format(Locale.getDefault(), "%tF", new Date(datetime));
    }

    // 裝置區域的時間
    public String getLocaleTime() {
        return String.format(Locale.getDefault(), "%tR", new Date(datetime));
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
     */
    public String getTitle() {return Title;}
    public void setTitle(String Title) {this.Title = Title;}
    
    public String getCC() {return CC;}
    public void setCC(String CC) {this.CC = CC;}
    
    public String getKind(){return Kind;}
    public void setKind(String Kind) {this.Kind = Kind;}
    
    public String getLane(){return Lane;}
    public void setLane(String Lane) {this.Lane = Lane;}
    
    public String getName(){return Name;}
    public void setName(String Name) {this.Name = Name;}
    
    public String getNumber(){return Number;}
    public void setNumber(String Number) {this.Number = Number;}
    
    public String getTime(){return Time;}
    public void setTime(String Time) {this.Time = Time;}
    
    public Bitmap getBitmap(){return bd;}
    public void setBitmap(byte[] bd) {this.bd = BitmapFactory.decodeByteArray(bd, 0, bd.length);}
}