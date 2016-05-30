package DownloadWebpageTask;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
//Uses AsyncTask to create a task away from the main UI thread. This task takes a 
// URL string and uses it to create an HttpUrlConnection. Once the connection
// has been established, the AsyncTask downloads the contents of the webpage as
// an InputStream. Finally, the InputStream is converted into a string, which is
// displayed in the UI by the AsyncTask's onPostExecute method.
public class DownloadWebpageTask extends AsyncTask<String, Void, String> {
	private static final String DEBUG_TAG = "HttpExample";
	private TextView textView = null;
	private ListView listView = null;
	private String[] list;// = new String[6];
			/*{"¤j»æ¥]¤p»æ", "³H¥J·Î", "ªF¤sÀnÀY", "¯ä¨§»G", "¼í»æ",
			 "¨§ªá", "«Cµì¤U³J","½Þ¦å¿|", "¤j¸z¥]¤p¸z", "ÄÐ¤ôÂû",
			 "¯N­»¸z","¨®½ü»æ","¬Ã¯]¥¤¯ù","ÄÐ¶pÂû","¤j¼öª¯"};*/
	private ArrayAdapter<String> listAdapter;
	private Context activity = null;
    @Override
    protected String doInBackground(String... urls) {
          
        // params comes from the execute() call: params[0] is the url.
        try {
            return downloadUrl(urls[0]);
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
   protected void onPostExecute(String result) {
    	String[] tokens = result.split("<br>");
    	int i=0;
    	if(listView!=null){
    		for (String token:tokens) {
    			if(i==0)list = new String[tokens.length];
    			list[i]=token;
    			i++;
        	}
    		listAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1,list);
    		listView.setAdapter(listAdapter);
    	}
   }
   public void defineTextView(TextView a) {
	    textView = a;
   }
   public void defineListView(ListView  a,Context activity) {
	    listView = a;
	    this.activity = activity;
   }
   private String downloadUrl(String myurl) throws IOException {
	    InputStream is = null;
	    // Only display the first 500 characters of the retrieved
	    // web page content.
	    int len = 1000;
	        
	    try {
	        URL url = new URL(myurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        // Starts the query
	        conn.connect();
	        int response = conn.getResponseCode();
	        Log.d(DEBUG_TAG, "The response is: " + response);
	        is = conn.getInputStream();
	        // Convert the InputStream into a string
	        String contentAsString = readIt(is, len);
	        return contentAsString;
	    // Makes sure that the InputStream is closed after the app is
	    // finished using it.
	    } finally {
	        if (is != null) {
	            is.close();
	        } 
	    }
	}
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}
}

