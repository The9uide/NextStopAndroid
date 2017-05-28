package th.ac.kmitl.it.nextstop.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it.nextstop.Activity.ReviewActivity;
import th.ac.kmitl.it.nextstop.Activity.TravelActivity;
import th.ac.kmitl.it.nextstop.Fragment.ShopFragment;
import th.ac.kmitl.it.nextstop.Model.FoursquareModel.Foursquare;
import th.ac.kmitl.it.nextstop.Model.FoursquareModel.Item;
import th.ac.kmitl.it.nextstop.Model.FoursquareModel.Item_;
import th.ac.kmitl.it.nextstop.Model.FoursquareModel.Item__;
import th.ac.kmitl.it.nextstop.Model.FoursquareModel.Venue;

/**
 * Created by The9uide on 11-Apr-17.
 */

public class FoursquareAsyncTask extends AsyncTask<String, Void, Boolean> {

    private String serverResponse;
    private ShopFragment shopFragment;
    private List<Shop> shops;

    public FoursquareAsyncTask(ShopFragment fragment) {
        this.shopFragment = fragment;
    }

    public FoursquareAsyncTask() {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... urls) {
        try {

            URL urlObj = new URL(urls[0]);
            Log.e("Response", "" + urlObj);
            shops = new ArrayList<>();

            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();


            int status = urlConnection.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {
                serverResponse = readStream(urlConnection.getInputStream());

                Log.e("Response Foursquare", "" + serverResponse);

                JsonParser parser = new JsonParser();
                JsonElement mJson = parser.parse(serverResponse);
                Gson gson = new Gson();
                Foursquare data = gson.fromJson(mJson, Foursquare.class);
                List<Item> venues = data.getResponse().getGroups().get(0).getItems();
                for (int index = 0; index < venues.size(); index++) {
                    try {
                        Venue venue = venues.get(index).getVenue();
                        Item__ photo = venue.getPhotos().getGroups().get(0).getItems().get(0);
                        String name = venue.getName();
                        String category = venue.getCategories().get(0).getName();
                        String url = photo.getPrefix() + "200x200" + photo.getSuffix();
                        Bitmap image = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());

                        shops.add(new Shop(name,category,image));

                        Log.e("Response", "DataRaw :" + name);
                        Log.e("Response", "DataRaw :" + category);
                        Log.e("Response", "PhotoRaw :" + url);
                        Log.e("Response", "ListRaw Size:" + shops.size()+"");



                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void onPostExecute(Boolean result) {

//        Log.e("Response", "" + serverResponse);
        if (shopFragment != null) {
            shopFragment.setShop(shops);
            Log.e("Response", "ListRaw addshops:" + shops.size()+"");
        } else if (shopFragment != null) {
//            shopFragment.updateArriveTime(resultTime);

        } else if (shopFragment != null) {
//            Log.e("Distance to NextStation", resultDistance + "");
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