package th.ac.kmitl.it.nextstop.Model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import th.ac.kmitl.it.nextstop.Activity.ReviewActivity;
import th.ac.kmitl.it.nextstop.Activity.TravelActivity;
import th.ac.kmitl.it.nextstop.Fragment.ShopFragment;

/**
 * Created by The9uide on 11-Apr-17.
 */

public class FoursquareAsyncTask extends AsyncTask<String, Void, Boolean> {

    private String serverResponse;
    private int resultTime;
    private double resultDistance;
    private ShopFragment shopFragment;

    public FoursquareAsyncTask(ShopFragment fragment) {
        this.shopFragment = fragment;
    }

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

                JSONObject resultJson = new JSONObject(serverResponse);
                JSONArray resultArray = resultJson.getJSONArray("routes");
                resultJson = resultArray.getJSONObject(0);
                resultArray = resultJson.getJSONArray("legs");
                resultJson = resultArray.getJSONObject(0);
                String tmp = String.valueOf(resultJson.getJSONObject("duration").get("text")).split(" ")[0];
                resultTime = Integer.parseInt(tmp);
                tmp = String.valueOf(resultJson.getJSONObject("distance").get("text")).split(" ")[0];
                resultDistance = Double.parseDouble(tmp);

                return true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return false;
    }

    protected void onPostExecute(Boolean result) {

        Log.e("Response", "" + serverResponse);
        if (shopFragment != null) {
            shopFragment.setShop(new Shop("ร้านขายของ1", "ร้านสะดวกซื้อ", "makkasan"));
            shopFragment.setShop(new Shop("ร้านขายของ2", "ร้านสะดวกซื้อ", "makkasan"));
            shopFragment.setShop(new Shop("ร้านขายของ3", "ร้านสะดวกซื้อ", "makkasan"));
            shopFragment.setShop(new Shop("ร้านขายของ4", "ร้านสะดวกซื้อ", "makkasan"));
            shopFragment.setShop(new Shop("ร้านขายของ5", "ร้านสะดวกซื้อ", "makkasan"));
            shopFragment.setShop(new Shop("ร้านขายของ6", "ร้านสะดวกซื้อ", "makkasan"));


        } else if (shopFragment != null) {
//            shopFragment.updateArriveTime(resultTime);

        } else if (shopFragment != null){
            Log.e("Distance to NextStation",resultDistance+"");
        }
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