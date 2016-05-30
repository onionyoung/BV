package com.example.bv;



import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.Uri;
import android.os.AsyncTask;

public class UploadWebpage extends AsyncTask<String, Void, String> {
	private String string = null;
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			startUpload(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}  
	public void startUpload(String... str) throws Exception {
		//string = str;
		boolean doSuccess = false; 
	    BufferedWriter wr = null; 
	    try { 
	      //URL url = new URL("http://www.dodgeg.net84.net/appData/appDataOutput.php"); 
	      URL url = new URL("http://www.dodgeg.net84.net/appData/insertData.php");
	      HttpURLConnection URLConn = (HttpURLConnection) url 
	          .openConnection(); 
	      URLConn.setReadTimeout(10000);
	      URLConn.setConnectTimeout(15000);
	      URLConn.setDoOutput(true); 
	      URLConn.setDoInput(true); 
	      ((HttpURLConnection) URLConn).setRequestMethod("POST"); 
	      URLConn.setUseCaches(false); 
	      URLConn.setAllowUserInteraction(true); 
	      HttpURLConnection.setFollowRedirects(true); 
	      URLConn.setInstanceFollowRedirects(true); 
	      /*URLConn 
	          .setRequestProperty( 
	              "User-agent", 
	              "Mozilla/5.0 (Windows; U; Windows NT 6.0; zh-TW; rv:1.9.1.2) " 
	                  + "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)"); */
	      //URLConn 
	      //    .setRequestProperty("Accept", 
	      //        "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
	      /*URLConn.setRequestProperty("Accept-Language", 
	          "zh-tw,en-us;q=0.7,en;q=0.3"); */
	      /*URLConn.setRequestProperty("Accept-Charse", 
	          "utf-8"); */

	 
	      /*URLConn.setRequestProperty("Content-Type", 
	          "application/x-www-form-urlencoded"); */
	      /*URLConn.setRequestProperty("Content-Length", String.valueOf(data 
	          .getBytes().length)); */
	      /*DataOutputStream dos = new DataOutputStream(URLConn.getOutputStream());
	      dos.writeBytes(data.toString()); */
	      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(URLConn.getOutputStream(), "UTF-8"));
	      
	      Uri.Builder builder = new Uri.Builder()
	        .appendQueryParameter("kind", str[0])
	        .appendQueryParameter("name", str[1])
	        .appendQueryParameter("cc", str[2])
	      	.appendQueryParameter("location", str[3])
	      	.appendQueryParameter("area", str[4])
	      	.appendQueryParameter("number", str[5]);
	      String query = builder.build().getEncodedQuery();
	      bw.write(query);
	     /* bw.write(data2);
	      bw.write(data3);
	      bw.write(data4);
	      bw.write(data5);
	      bw.write(data6);*/
	      bw.flush();
	      bw.close();
	      java.io.BufferedReader rd = new java.io.BufferedReader( 
	          new java.io.InputStreamReader(URLConn.getInputStream(), 
	        		  "utf-8")); 
	      String line; 
	      while ((line = rd.readLine()) != null) { 
	        System.out.println(line); 
	      }
	 
	      rd.close(); 
	    } catch (java.io.IOException e) { 
	      doSuccess = false; 
	      //logger.info(e); 
	 
	    } finally { 
	      if (wr != null) { 
	        try { 
	          wr.close(); 
	        } catch (java.io.IOException ex) { 
	          //logger.info(ex); 
	        } 
	        wr = null; 
	      } 
	    } 
	 
	}  
	}
