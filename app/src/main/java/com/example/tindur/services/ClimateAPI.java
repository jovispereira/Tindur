package com.example.tindur.services;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ClimateAPI {

    private static final String API_KEY = "/?key=af2dd6bc";
    private static final String API = "https://api.hgbrasil.com/weather";
    private static HttpsURLConnection hgWeather;

    private void readResponse(InputStream response) throws IOException {
        InputStreamReader responseBodyReader = new InputStreamReader(response);
        JsonReader jsonReader = new JsonReader(responseBodyReader);
        jsonReader.beginObject();
        while(jsonReader.hasNext()) {

            String value = jsonReader.toString();
            System.out.println(jsonReader);
             // Log.i("api", value);
            jsonReader.skipValue();
        }
    }


    public ClimateAPI(String query) {
        try {
            URL api = new URL(API.concat(API_KEY));
            hgWeather = (HttpsURLConnection) api.openConnection();
            hgWeather.setRequestProperty("User-Agent", "tindur-app-v0.1");
            if(hgWeather.getResponseCode() == 200) {
                readResponse(hgWeather.getInputStream());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
