package com.Aditya;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;



/*******************************************************************************************************
 * The WeatherAPI class
 * This class contains all code in order for weatherAPI to run smoothly
 * Once class is instantiated, default data is presented.
 * Once token is authorized from server, requests are made back and forth between client and server
 *******************************************************************************************************/
public class WeatherAPI {
    private final String KEY = "5f7a027211e426d583d92fb83dcf2c29";
    private String longitude, latitude, countryCode, region;
    private double temperature;

    public WeatherAPI(String region, String countryCode)
    {
        this.region = region;
        this.countryCode = countryCode;
        getRegionCoordinates();
        getCityInfo();
    }


    public WeatherAPI()
    {
        this("Toronto", "+1"); //default assuming user from Toronto
        getRegionCoordinates();
        getCityInfo();
    }


    /********************************************************************
    * METHOD:  getRegionCoordinates()
    * @Purpose: Gets longitude & latitude of city specified
    *********************************************************************/
    private void getRegionCoordinates()
    {
        try
        {
            URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q=" + region + "," + countryCode + "&appid=" + KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int resp = conn.getResponseCode();
            System.out.println(resp);
            System.out.println(conn.getResponseMessage());

            if (resp == 200)
            {
                StringBuilder stringInfo = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext())
                    stringInfo.append(sc.nextLine());
                sc.close();

                JSONParser parse = new JSONParser();
                JSONArray data = (JSONArray) parse.parse(String.valueOf(stringInfo));

                JSONObject city = (JSONObject) data.get(0);
                longitude = city.get("lon").toString();
                latitude = city.get("lat").toString();
                System.out.println(city.toJSONString());
                System.out.println("Longitude: " + longitude + " latitude" + latitude);
            }
            else
                JOptionPane.showMessageDialog(null,"Could not find weather! Please makes sure you enter a valid city & country code is correct!", "Alert", JOptionPane.INFORMATION_MESSAGE);

        }
        catch (Exception e){e.printStackTrace();}
    }


    /********************************************************************
    * METHOD:  getCityInfo()
    * @Purpose: Gets info from city
    **********************************************************************/
    private void getCityInfo()
    {
        try
        {
            URL url2 = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + KEY);
            HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseMain = connection.getResponseCode();
            System.out.println("response main" + responseMain);
            System.out.println(connection.getResponseMessage());

            if (responseMain == 200)
            {
                System.out.println("SUCCESSFUL CONNECTION!");
                StringBuilder stringData = new StringBuilder();
                Scanner sc = new Scanner(url2.openStream());
                while (sc.hasNext())
                    stringData.append(sc.nextLine());
                sc.close();

                JSONParser parser = new JSONParser();
                JSONObject j = (JSONObject) parser.parse(String.valueOf(stringData));
                JSONObject second = (JSONObject) j.get("main");
                JSONObject o = (JSONObject) parser.parse(second.toJSONString());
                String stringTemp = o.get("temp").toString();
                this.temperature = Double.parseDouble(stringTemp) - 273.15;
                System.out.println("TEMPERATURE IS FROM WEATHER API FILE " + String.format("%.2f ", temperature));
            }
        }
        catch (Exception e ){e.printStackTrace();}
    }


    /********************************************************************
     * METHOD:  getTemperature()
     * @Purpose: Gets temperature from city
     **********************************************************************/
    public double getTemperature(){
        return temperature;
    }


    /********************************************************************
     * METHOD:  getRegion()
     * @Purpose: Gets region from city
     **********************************************************************/
    public String getRegion(){return region;}
}
