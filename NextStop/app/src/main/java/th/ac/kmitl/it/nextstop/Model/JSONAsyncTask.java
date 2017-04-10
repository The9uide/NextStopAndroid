package th.ac.kmitl.it.nextstop.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.loopj.android.http.HttpGet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by The9uide on 11-Apr-17.
 */

public class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

    String serverResponse;
    JSONObject jsono;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(String... urls) {
        try {

            URL urlObj = new URL(urls[0]);

            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();


            int status = urlConnection.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {
                serverResponse = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", serverResponse);
                return true;
            }
            jsono = new JSONObject(serverResponse);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return false;
    }

    protected void onPostExecute(Boolean result) {

        Log.e("Response", "" + serverResponse);
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}