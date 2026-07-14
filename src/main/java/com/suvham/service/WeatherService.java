package com.suvham.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.suvham.model.Weather;

public class WeatherService {

    private static final String API_KEY = "9218a79d56bc58974a677b5cbc6352b6";

    private static final String BASE_URL =
            "https://api.openweathermap.org/data/2.5/weather";

    public Weather getWeather(String city) {

        try {

            // Final URL
            String urlString = BASE_URL
                    + "?q=" + city
                    + "&appid=" + API_KEY
                    + "&units=metric";

            URL url = new URL(urlString);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            InputStream stream;

            if (responseCode >= 200 && responseCode < 300) {
                stream = connection.getInputStream();
            } else {
                stream = connection.getErrorStream();
            }

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(stream));

            StringBuilder response = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            // JSON String -> JSONObject
            JSONObject jsonObject = new JSONObject(response.toString());

            // Main Object
            JSONObject mainObject = jsonObject.getJSONObject("main");

            // Wind Object
            JSONObject windObject = jsonObject.getJSONObject("wind");

            // Weather Array
            JSONArray weatherArray = jsonObject.getJSONArray("weather");

            // First Object of Array
            JSONObject weatherObject = weatherArray.getJSONObject(0);

            // Weather Object
            Weather weather = new Weather();

            // City
            weather.setCity(jsonObject.getString("name"));

            // Temperature
            weather.setTemperature(mainObject.getDouble("temp"));

            // Humidity
            weather.setHumidity(mainObject.getInt("humidity"));

            // Wind Speed
            weather.setWindSpeed(windObject.getDouble("speed"));

            // Condition
            weather.setCondition(weatherObject.getString("main"));

            // Finally Return
            return weather;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}