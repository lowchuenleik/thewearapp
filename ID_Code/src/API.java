// requires installation of JSON-java
// TODO: comment
// TODO: improve modularity, access modifiers
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//java -classpath "json-20180813.jar;./out" API

//javac -classpath json-20180813.jar -d ./out ID_CODE/src/API.java

//We want to return WEATHER CODES, 0:sunny 1:windy 2:cloudy 3:rainy 4:snowy

//getSummary, getHourly, getWeekly role
//getSummary has weather code (avg of the day) - dictionary for temp range etc, and rain chance, and weather code.

public class API {

    private static String apiKey = "9b9468c55801419abe17b70d7acd5c6e";
    private static String apiURL = "http://api.weatherbit.io/v2.0/forecast/hourly";
    private JSONObject fullJSON;
    private LocalDateTime lastFetched = LocalDateTime.of(2000, Month.JANUARY, 01, 01, 01);
    //private Map<String, String> params;
    private List<Map<String, String>> truncatedData;
    private static String CAM_LAT = "52.21";
    private static String CAM_LON = "0.091";

    public void setFullJSON (JSONObject fullJSON){
        this.fullJSON = fullJSON;
    }

    public void setLastFetched (LocalDateTime lastFetched){
        this.lastFetched = lastFetched;
    }

    // public void setParams (Map<String, String> params){
    //     this.params = params;
    // }

    public void setTruncatedData (List<Map<String, String>> truncatedData){
        this.truncatedData = truncatedData;
    }

    // fetches json object from hourly api
    public static JSONObject retrieve(String API, Map<String,String> params) throws IOException{
        
        String baseURL = "http://api.weatherbit.io/v2.0/forecast/";
        String type = "";
        switch (params.get("CUST_TYPE")){
            case "week":type="daily";
                break;
            case "day":type="hourly";
                break;
            default: type="hourly";
                break;
        }
        baseURL = baseURL + type;
        String urlWithParameters = String.format("%s?key=%s", baseURL, API);
        for (Map.Entry<String, String> p : params.entrySet()) {
            urlWithParameters += "&" + p.getKey() + "=" + p.getValue();
        }

        URL url = new URL(urlWithParameters);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        System.out.println(con.getResponseCode());
        System.out.println(con.getResponseMessage());

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return new JSONObject(content.toString());

    }

    public List<Map<String,String>> getWeekly() throws IOException{

        Map params = new HashMap<String,String>();
        params.put("lat", CAM_LAT);
        params.put("lon", CAM_LON);
        params.put("days","7");
        params.put("CUST_TYPE","week");

        JSONObject jsonobj = retrieve(apiKey, params);
        List<Map<String,String>> weekly_data = truncate_data_weekly(jsonobj);

        return weekly_data;
    }

    public List<Map<String,String>> getDaily() throws IOException{

        Map params = new HashMap<String,String>();
        params.put("lat", CAM_LAT);
        params.put("lon", CAM_LON);
        params.put("hours","24");
        params.put("CUST_TYPE","daily");

        JSONObject jsonobj = retrieve(apiKey, params);
        List<Map<String,String>> weekly_data = truncate_data_daily(jsonobj);

        return weekly_data;
    }

    // can be used to find specific pieces of data
    // TODO: fill in
    public void getXX(Map<String, String> params) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(now, lastFetched).getSeconds() > 3600) {
            fullJSON = retrieve(apiKey, params);
            //truncateData();
            lastFetched = now;
        }
        // TODO: find weather forecast data
    }

    // truncates the fullJSON object that was fetched, into an hourly list of various attributes (local time, temp, wind speed, rain)
    public void truncateData() {
        List<Map<String, String>> essentialData = new ArrayList();
        //System.out.println(fullJSON);
        JSONArray forecastData = fullJSON.getJSONArray("data");
        for (int i = 0; i < forecastData.length(); i++) {
            JSONObject hour = forecastData.getJSONObject(i);
            Map<String, String> conditions = new HashMap<>();
            // can possible include more variables of interest
            // look at https://www.weatherbit.io/api/weather-forecast-120-hour
            conditions.put("temp", hour.get("temp").toString());
            conditions.put("windSpeed", hour.get("wind_spd").toString());
            if (!hour.isNull("app_temp")){
                conditions.put("feelsLikeTemp", hour.get("app_temp").toString());
            } else{
                conditions.put("feelsLikeTemp_MAX", hour.get("app_max_temp").toString());
                conditions.put("feelsLikeTemp_MIN", hour.get("app_min_temp").toString());
            }
            conditions.put("probabilityOfRain", hour.get("pop").toString());
            conditions.put("localTime", hour.get("timestamp_local").toString());

            //Messy casting...Accessing nested JSON objects 
            JSONObject weather_detail = (JSONObject)hour.get("weather");
            conditions.put("weather_code",weather_detail.get("code").toString());
            conditions.put("weather_icon",(String)weather_detail.get("icon"));
            conditions.put("weather_description",weather_detail.get("description").toString());
            
            essentialData.add(conditions);
        }
        truncatedData = essentialData;
        //System.out.println(truncatedData.size());
    }

    public List<Map<String, String>> truncate_data_daily(JSONObject injson) {
        List<Map<String, String>> essentialData = new ArrayList();
        //System.out.println(fullJSON);
        JSONArray forecastData = injson.getJSONArray("data");
        for (int i = 0; i < forecastData.length(); i++) {
            JSONObject hour = forecastData.getJSONObject(i);
            Map<String, String> conditions = new HashMap<>();
            // can possible include more variables of interest
            // look at https://www.weatherbit.io/api/weather-forecast-120-hour
            conditions.put("temp", hour.get("temp").toString());
            conditions.put("feelsLikeTemp", hour.get("app_temp").toString());
            conditions.put("windSpeed", hour.get("wind_spd").toString());
            conditions.put("probabilityOfRain", hour.get("pop").toString());
            conditions.put("localTime", hour.get("timestamp_local").toString());
            conditions.put("cloud_coverage",hour.get("clouds").toString());

            //Messy casting...Accessing nested JSON objects 
            JSONObject weather_detail = (JSONObject)hour.get("weather");
            Integer raw_weather_code = (Integer)weather_detail.get("code");
            Double windspeed = Double.parseDouble(conditions.get("windSpeed"));
            Integer weather_code = weatherCoder(raw_weather_code, windspeed);
            conditions.put("weather_code",weather_code.toString());
            conditions.put("weather_icon",weather_detail.get("icon").toString());
            conditions.put("weather_description",weather_detail.get("description").toString());
            
            essentialData.add(conditions);
        }
        return essentialData;
    }

    public List<Map<String, String>> truncate_data_weekly(JSONObject injson) {
        List<Map<String, String>> essentialData = new ArrayList();
        //System.out.println(fullJSON);
        JSONArray forecastData = injson.getJSONArray("data");
        for (int i = 0; i < forecastData.length(); i++) {
            JSONObject hour = forecastData.getJSONObject(i);
            Map<String, String> conditions = new HashMap<>();
            // can possible include more variables of interest
            // look at https://www.weatherbit.io/api/weather-forecast-120-hour
            conditions.put("temp", hour.get("temp").toString());
            conditions.put("feelsLikeTemp_MAX", hour.get("app_max_temp").toString());
            conditions.put("feelsLikeTemp_MIN", hour.get("app_min_temp").toString());
            conditions.put("tempMAX", hour.get("app_min_temp").toString());
            conditions.put("tempMIN", hour.get("app_min_temp").toString());
            conditions.put("windSpeed", hour.get("wind_spd").toString());
            conditions.put("probabilityOfRain", hour.get("pop").toString());
            conditions.put("date", hour.get("valid_date").toString());
            conditions.put("cloud_coverage",hour.get("clouds").toString());

            //Messy casting...Accessing nested JSON objects 
            JSONObject weather_detail = (JSONObject)hour.get("weather");
            Integer raw_weather_code = (Integer)weather_detail.get("code");
            Double windspeed = Double.parseDouble(conditions.get("windSpeed"));
            Integer weather_code = weatherCoder(raw_weather_code, windspeed);
            conditions.put("weather_code",weather_code.toString());
            conditions.put("weather_icon",weather_detail.get("icon").toString());
            conditions.put("weather_description",weather_detail.get("description").toString());
            
            essentialData.add(conditions);
        }
        return essentialData;
    }
    //, 0:sunny 1:windy 2:cloudy 3:rainy 4:snowy
    //This weather code is for weekly data, and we will create alternative logic for the 
    //DAILY SUMMARY WEATHERCODE
    public int weatherCoder(int weathercode,double windspeed){
        int answer = 0; //sunny is safe default
        switch (weathercode){
            case 800: weathercode+=100;
                break;
            //HACKY, CONSIDER REMOVING
            case 801: weathercode+=100;
                break;
            //HACKY, CONSIDER REMOVING
            default: break;
        }
        switch (weathercode/100){
            case 2:
            case 3:
            case 5: answer = 3;
                break;
            case 6: answer = 4;
                break;
            case 8: answer = 2;
                break;
            case 9: answer = 0 ;
                break;
            default: break;
        }
        //5m/s is the average yearly windspeed in m/s for Cambridge
        //which is slightly above the national average.
        if (windspeed > 6){
            answer = 1;
        }
        return answer;
    }

    // sets parameters and fetches json data using getHourly(), then truncates it into the essential bits using truncateData
    public static void main(String[] args) throws IOException {

        API test = new API();

        // Dictionary of parameters
        Map params = new HashMap<String,String>();

        // OPTION 1: by longitude and latitude
        params.put("lat", CAM_LAT);
        params.put("lon", CAM_LON);
        //Necessary for 5 DAY from HOURLY
        params.put("hours","48");        

        // OPTION 2: by city and country
        // params.put("city", "Cambridge");
        // params.put("country", "UK");

        // test.setParams(params);
        // test.setFullJSON(getHourly(apiKey, params));
        // test.truncateData();

        List<Map<String,String>> daily = test.getDaily();
        //System.out.println(daily);
        List<Map<String,String>> weekly = test.getWeekly();
        System.out.println(weekly);
        for (Map<String,String> xx:weekly){
            System.out.println("DATE TIME");
            System.out.println(xx.get("date_time"));
        }
        //test.setLastFetched(LocalDateTime.now());

        //System.out.println("fullJSON: " + test.fullJSON);
        //System.out.println("truncatedData: " + test.truncatedData);
        //{localTime=2019-05-09T17:00:00, temp=9.7, feelsLikeTemp=9.7, windSpeed=3.50521, probabilityOfRain=10}
    }
}


// {"moonrise_ts":1557862741,"wind_cdir":"NW","rh":49,"pres":1003.5,"sunset_ts":1557878809,
// "ozone":344.934,"moon_phase":0.920222,"wind_gust_spd":12.3,"snow_depth":0,"clouds":9,
// "ts":1557806460,"sunrise_ts":1557828382,"app_min_temp":12.7,"wind_spd":4.87802,"pop":0,
// "wind_cdir_full":"northwest","slp":1013.51,"app_max_temp":19.8,"vis":24.1,"dewpt":6.3,
// "snow":0,"uv":10.362,"valid_date":"2019-05-14","wind_dir":312,"max_dhi":null,"clouds_hi":1,
// "precip":0,"weather":{"icon":"c02d","code":801,"description":"Few clouds"},"max_temp":20.8,
// "moonset_ts":1557824053,"datetime":"2019-05-14","temp":17.6,"min_temp":12.7,"clouds_mid":0,"clouds_low":8},