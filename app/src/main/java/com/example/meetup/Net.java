package com.example.meetup;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Net {

    public static class JSONLoadTask extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Void... voids) {
            URL urlApi = null;
            Uri dad =  Uri.parse("https://api.themoviedb.org/3/discover/movie?api_key=b08f286c71e9d5bbaaa7508cc7701b41").buildUpon().build();
            try {
                 urlApi = new URL(dad.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = null;
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) urlApi.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null){
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
                jsonObject = new JSONObject(stringBuilder.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return jsonObject;
        }
    }

    public static String getMovieFromJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONLoadTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = null;
        if(jsonObject == null){
            return result;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            JSONObject objectMovie = jsonArray.getJSONObject(0);
            result = objectMovie.getString("original_title");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
