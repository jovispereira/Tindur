package com.example.tindur.services;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
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
    public String description, city, currently, temp;
    public String JSON;


    private String getParameterJSON(String parameter) {
        return JSON.split("\"".concat(parameter).concat("\":"))[1].split(",")[0].replaceAll("\"", "");
    }


    private void readResponse(InputStream response) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response));
        StringBuilder stringBuilder = new StringBuilder();

        String line = bufferedReader.readLine();
        while(line != null) {
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        this.JSON = stringBuilder.toString();
        description = getParameterJSON("description");
        city = getParameterJSON("city");
        temp = getParameterJSON("temp");
        currently = getParameterJSON("currently");


    }

    public ClimateAPI(String query) {
        try {
            URL api = new URL(API.concat(API_KEY).concat(query));
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
