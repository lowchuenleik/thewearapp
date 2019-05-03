// requires installation of JSON-java
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


public class API {

    private static String apiKey = "9b9468c55801419abe17b70d7acd5c6e";
    private static String apiURL = "http://api.weatherbit.io/v2.0/forecast/hourly";
    private JSONObject fullJSON;
    private LocalDateTime lastFetched = LocalDateTime.of(2000, Month.JANUARY, 01, 01, 01);
    private Map<String, String> params;
    private List<Map<String, String>> truncatedData;

    public void setFullJSON (JSONObject fullJSON){
        this.fullJSON = fullJSON;
    }

    public void setLastFetched (LocalDateTime lastFetched){
        this.lastFetched = lastFetched;
    }

    public void setParams (Map<String, String> params){
        this.params = params;
    }

    public void setTruncatedData (List<Map<String, String>> truncatedData){
        this.truncatedData = truncatedData;
    }

    // fetches json object from hourly api
    public static JSONObject getHourly(String baseURL, String API, Map<String, String> params) throws IOException {
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

    // sets parameters and fetches json data using getHourly(), then truncates it into the essential bits using truncateData
    public static void main(String[] args) throws IOException {

        API test = new API();

        // setting parameters
        Map params = new HashMap<>();

        // OPTION 1: by longitude and latitude
        params.put("lat", "52.21");
        params.put("lon", "0.091");

        // OPTION 2: by city and country
//        params.put("city", "Cambridge");
//        params.put("country", "UK");

        test.setParams(params);

        test.setFullJSON(getHourly(apiURL, apiKey, params));
        test.truncateData();
        test.setLastFetched(LocalDateTime.now());

        System.out.println("fullJSON: " + test.fullJSON);
        System.out.println("truncatedData: " + test.truncatedData);
    }

    // can be used to find specific pieces of data
    // TODO: fill in
    public void getXX() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(now, lastFetched).getSeconds() > 3600) {
            fullJSON = getHourly(apiURL, apiKey, params);
            truncateData();
            lastFetched = now;
        }
        // TODO: find weather forecast data
    }

    // truncates the fullJSON object that was fetched, into an hourly list of various attributes (local time, temp, wind speed, rain)
    public void truncateData() {
        List<Map<String, String>> essentialData = new ArrayList();

        JSONArray forecastData = fullJSON.getJSONArray("data");
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
            essentialData.add(conditions);
        }
        truncatedData = essentialData;
    }
}
