package rwt.kevin.memories;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.JsonObject;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 8/22/2016.
 */

class DownloadMemory extends AsyncTask<String, Void, JsonObject> {
    private String titleObject;
	private JSONArray jsonArray;
	private TextView locationTextView;
	private TextView memoryTextView;
	private TextView timestampTextView;
	private TextView usernameTextView;
	String id;

	protected void onPreExecute() {
        Log.d(null, "loading moment");
    }
    @Override
    protected JsonObject doInBackground(String... params) {
        final URLConnection conn;
        URL nUrl;
		id = params[0];
        String caccessKey = "c3b128b6-9890-11e6-9298-e0cb4ea6daff";
		try {
			URL url = new URL("http://web.webapps.centennialarts.com/page.php?command=getPage&");
			String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")
				+ "&" + URLEncoder.encode("accessKey", "UTF-8") + "=" + URLEncoder.encode(caccessKey, "UTF-8");
			Log.d(null, url.toString() + data);
			nUrl = new URL(url + data);
			conn = nUrl.openConnection();
			BufferedReader 	in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			Thread.sleep(100);
			String s;
			while ((s = in.readLine()) != null) {
				Log.d(null, "jArray" + s);
				jsonArray = new JSONArray(s);
			}
		} catch (UnsupportedEncodingException e1) {
			Log.d(null, e1.toString());
			e1.printStackTrace();
		} catch (IllegalStateException e3) {
			Log.d("IllegalStateException", e3.toString());
			e3.printStackTrace();
		} catch (IOException e4) {
			Log.d("IOException", e4.toString());
			e4.printStackTrace();
		} catch (Exception e) {
			Log.d(null, "StringBuilding & BufferedReader\", \"Error converting result ");
		}
        return null;
    }
    protected void onPostExecute(JsonObject obj) {
		Log.d(null, "postexecute");
		String timestamp= null;
		String location;
		LatLng latlngFinal = null;

		try {
			for(int n = 0; n < jsonArray.length(); n++) {
				JSONObject jsonObject = jsonArray.getJSONObject(n);
				if (jsonObject != null) {
					titleObject = jsonObject.getString("title");
					Log.d(null, "title: " + titleObject);

					JSONArray attrArray = jsonObject.getJSONArray("pageTypeValues");
					for (int i = 0; i < attrArray.length(); i++) {
						JSONObject attrObject = (JSONObject) attrArray.get(i);
						String title = attrObject.getString("title");
						if (title.equals("Location")) {
							location = (String) attrObject.get("value");
							String[] latlngArray = location.split(",");
							double latitude = Double.parseDouble(latlngArray[0]);
							double longitude = Double.parseDouble(latlngArray[1]);
							latlngFinal = new LatLng(latitude, longitude);
						}
						if (i == 3) {
							timestamp = (String) attrObject.get("value");
							Log.d(null, timestamp);
						}
					}
				} else {
					Log.d(null, "error");
				}
				String username = "username";
				ViewMemoryActivity view = new ViewMemoryActivity();
//				view.setId(id);
				view.setMemory(timestamp, timestampTextView, latlngFinal, locationTextView, titleObject, memoryTextView, username, usernameTextView);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d(null, "downloaded");
	}
	void setTextViews(TextView locationTextView, TextView memoryTextView, TextView timestampTextView, TextView usernameTextView){
		this.locationTextView = locationTextView;
		this.memoryTextView = memoryTextView;
		this.timestampTextView = timestampTextView;
		this.usernameTextView = usernameTextView;
	}
}