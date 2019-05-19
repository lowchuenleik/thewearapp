import java.io.File;
import java.io.IOException;
import java.text.ParseException;
<<<<<<< HEAD
import java.util.List;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
=======
import java.util.*;
import java.text.SimpleDateFormat;
>>>>>>> ec7aa5ad08b8d99ecb4209fe1b524ee749685dad

public class Hourly {
    private int hour;
    private int temp;
    private File weathericon;
    private int weathercode;
<<<<<<< HEAD

    // TODO: Yulong here. Can you try including three getter methods, as described below?
    // (@Chuen: you should be able to easily get the hours and temp from the hourly forecast)
    // preferably String so I can just show them e.g. "01:00" or "13:00"
    // Otherwise, List<Integer> also works, e.g. integer 1 means 1am, integer 23 means 11pm
    public List<String> getHours(){
        List<Map<String,String>> data = hourlydata_augmented();//24 hour time
        List<String> output = new LinkedList<>();
        for (Map<String,String> entry: data){
            temp = (int)Double.parseDouble(entry.get("hour"));
            Integer twentyfourhtime = Integer.parseInt(new SimpleDateFormat("HH").format(temp));
            output.add(0,Integer.toString(twentyfourhtime));//preprends it!
        }
        return output;
    }
    // in Celsius
    public List<Integer> getTemperatures(){
        List<Map<String,String>> data = hourlydata_augmented();
        List<Integer> output = new LinkedList<>();
        for (Map<String,String> entry: data){
            output.add(0,(int)Double.parseDouble(entry.get("feelsLikeTemp")));//preprends it!
        }
        return output;
    }
    public List<File> getWeatherIcons(){//not sure what you mean by get weather icons?
        List<Map<String,String>> data = hourlydata_augmented();
        return null;
    }
=======
>>>>>>> ec7aa5ad08b8d99ecb4209fe1b524ee749685dad
    

    //CONSTRUCTOR: Hourly is an object that allows the weather information about each
    // hour in the day to be stored in one object.
    public Hourly(int hour) {
        if (hour > 23) {
            System.out.println("NOT VALID HOUR REQUEST! Returning hour 00:00 instead.");
            hour = 0;
        }
        API api_instance = new API();
        List<Map<String,String>> daily_data = null;
        String intime = "";
        Date temp = null;
        Map<String,String> selected_hour = new HashMap<String,String>();
        try{
            daily_data = api_instance.getDaily();//hourly, 24 elements
        } catch (IOException e){
            System.out.println("ERROR, PLEASE DEBUG: " + e.getMessage());
        }
<<<<<<< HEAD
        for (Map<String,String> entry: daily_data){
            intime = entry.get("localTime");
            try{
                temp = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss").parse(intime);
            } catch (ParseException e){
                System.out.println("ERROR, PLEASE DEBUG");
            }
            Integer twentyfourhtime = Integer.parseInt(new SimpleDateFormat("HH").format(temp));
            entry.put("hour",twentyfourhtime.toString());
            if (twentyfourhtime==hour){
                selected_hour = entry;
=======
        try {
            for (Map<String, String> entry : daily_data) {
                intime = entry.get("localTime");
                temp = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss").parse(intime);
                Integer twentyfourhtime = Integer.parseInt(new SimpleDateFormat("HH").format(temp));
                entry.put("hour", twentyfourhtime.toString());
                if (twentyfourhtime == hour) {
                    selected_hour = entry;
                }
>>>>>>> ec7aa5ad08b8d99ecb4209fe1b524ee749685dad
            }
        } catch (ParseException pe) {
            System.out.println("PARSE ERROR! " + pe.getCause());
        }


        this.temp = (int)Double.parseDouble(selected_hour.get("feelsLikeTemp"));
        this.hour = hour;
        this.weathercode = Integer.parseInt(selected_hour.get("weather_code"));

        File icon = null;
        switch (this.weathercode) {
            case 0: icon = new File("src/data/hourlyicons/sunny.png");
            break;
            case 1: icon = new File("src/data/hourlyicons/windy.png");
            break;
            case 2: icon = new File("src/data/hourlyicons/cloudy.png");
            break;
            case 3: icon = new File("src/data/hourlyicons/rainy.png");
            break;
            case 4: icon = new File("src/data/hourlyicons/snowy.png");
            break;
        }
        this.weathericon = icon;
    }

    //ARGUMENTS: none
    //RETURNS: integer representation of the hour
    public int getHour() {
        return hour;
    }

    //ARGUMENTS: none
    //RETURNS: string representation of the hour
    public String getHourString() {
        String h = String.valueOf(this.hour);
        if (h.length() == 1) {
            h = "0"+h+":00";
        } else if (h.length() == 2) {
            h = h+":00";
        } else {
            System.out.println("ERROR IN HOUR OUTPUTS");
        }

        return h;
    }

    //ARGUMENTS: none
    //RETURNS: temperature for the hour as an int in degrees celsius
    public int getTemp() {
        return temp;
    }

    //ARGUMENTS: none
    //RETURNS: none
    //Makes another call to the API to refresh the data and update the temperature
    public void updateTemp() {
        Map<String,String> freshdata = refreshdata();
<<<<<<< HEAD
        this.temp = (int)Double.parseDouble(freshdata.get("feelsLikeTemp")); 
=======
        this.temp = Integer.parseInt(freshdata.get("feelsLikeTemp"));
>>>>>>> ec7aa5ad08b8d99ecb4209fe1b524ee749685dad
    }

    //ARGUMENTS: none
    //RETURNS: a file object for the icon corresponding to the weather for the hour
    public File getWeathericon() {
        return weathericon;
    }

    //ARGUMENTS: none
    //RETURNS: none
    //Makes another call to the API to refresh the data and update the weathericon.
    // In doing so, it also updates the weathercode.
    public void updateWeathericon() {
        updateWeathercode();

        File icon = null;
        switch (this.weathercode) {
            case 0: icon = new File("src/data/hourlyicons/sunny.png");
                break;
            case 1: icon = new File("src/data/hourlyicons/windy.png");
                break;
            case 2: icon = new File("src/data/hourlyicons/cloudy.png");
                break;
            case 3: icon = new File("src/data/hourlyicons/rainy.png");
                break;
            case 4: icon = new File("src/data/hourlyicons/snowy.png");
                break;
        }

        this.weathericon = icon;
    }

    //ARGUMENTS: none
    //RETURNS: the weathercode for that hour as an int
    public int getWeathercode() {
        return weathercode;
    }

    //ARGUMENTS: none
    //RETURNS: none
    //Makes another call to the API to refresh the data and update the weathercode
    public void updateWeathercode() {
        Map<String,String> freshdata = refreshdata();
<<<<<<< HEAD
        this.weathercode = (int)Double.parseDouble(freshdata.get("weather_code"));
=======
        this.weathercode = Integer.parseInt(freshdata.get("weather_code"));
>>>>>>> ec7aa5ad08b8d99ecb4209fe1b524ee749685dad
    }

    //ARGUMENTS: none
    //RETURNS: a map containing the new, updated API data
    public Map<String,String> refreshdata(){
        API api_instance = new API();
        List<Map<String,String>> daily_data = new LinkedList<>();
        Map<String,String> selected_hour = new HashMap<String,String>();
        String intime = "";
        Date temp = null;
        try{
            daily_data = api_instance.getDaily();//hourly, 24 elements
        } catch (IOException e){
            System.out.println("ERROR, PLEASE DEBUG");
        }
<<<<<<< HEAD
        for (Map<String,String> entry: daily_data){
            intime = entry.get("localTime");
            try{
                temp = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss").parse(intime);
            } catch (ParseException e){
                System.out.println("ERROR, PLEASE DEBUG");
            }
            Integer twentyfourhtime = Integer.parseInt(new SimpleDateFormat("HH").format(temp));
            entry.put("hour",twentyfourhtime.toString());
            if (twentyfourhtime==hour){
                selected_hour = entry;
=======
        try {
            for (Map<String, String> entry : daily_data) {
                intime = entry.get("localTime");
                temp = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss").parse(intime);
                Integer twentyfourhtime = Integer.parseInt(new SimpleDateFormat("HH").format(temp));
                entry.put("hour", twentyfourhtime.toString());
                if (twentyfourhtime == hour) {
                    selected_hour = entry;
                }
>>>>>>> ec7aa5ad08b8d99ecb4209fe1b524ee749685dad
            }
        } catch (ParseException pe) {
            System.out.println("PARSE ERROR: " + pe.getCause());
        }
        return selected_hour;
    }

    //TODO: THIS DOESN'T WORK!! CAN'T PARSE THE TIME FORMAT CORRECTLY!? Also is the returns info correct?
    //ARGUMENTS: none
    //RETURNS: a list containing the API data for the hours in the day
    public static List<Map<String,String>> hourlydata_augmented(){
        API api_instance = new API();
        List<Map<String,String>> daily_data = new LinkedList<>();
        String intime = "";
        Date temp = null;
        try{
            daily_data = api_instance.getDaily();//hourly, 24 elements
        } catch (IOException e){
            System.out.println("ERROR, PLEASE DEBUG");
        }
<<<<<<< HEAD
        for (Map<String,String> entry: daily_data){
            intime = entry.get("localTime");
            try{
                temp = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss").parse(intime);
            } catch (ParseException e){
                System.out.println("ERROR, PLEASE DEBUG");
            }
            Integer twentyfourhtime = Integer.parseInt(new SimpleDateFormat("HH:mm").format(temp));
            entry.put("hour",twentyfourhtime.toString());
=======
        try {
            for (Map<String, String> entry : daily_data) {
                intime = entry.get("localTime");
                temp = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss").parse(intime);
                Integer twentyfourhtime = Integer.parseInt(new SimpleDateFormat("HH:mm").format(temp));
                entry.put("hour", twentyfourhtime.toString());
            }
        } catch (ParseException pe) {
            System.out.println("PARSE ERROR: " + pe.getCause());
>>>>>>> ec7aa5ad08b8d99ecb4209fe1b524ee749685dad
        }
        return daily_data;
    }

    //ARGUMENTS: none
    //RETURNS: a list of all the hours as strings in the format 01:00 etc.
    public static List<String> getHours(){
        List<Map<String,String>> data = hourlydata_augmented();//24 hour time
        List<String> output = new LinkedList<>();
        for (Map<String,String> entry: data){
            String h = String.valueOf((int)Double.parseDouble(entry.get("hour")));
            if (h.length() == 1) {
                h = "0"+h+":00";
            } else if (h.length() == 2) {
                h = h+":00";
            } else {
                System.out.println("ERROR IN HOUR OUTPUTS");
            }
            output.add(0,h);  //prepends it!
        }

        return output;
    }

    //ARGUMENTS: none
    //RETURNS: list of ints of the temperature for that hour in degrees celsius
    // (corresponds to the getHours list)
    public static List<Integer> getTemperatures(){
        List<Map<String,String>> data = hourlydata_augmented();
        List<Integer> output = new LinkedList<>();
        for (Map<String,String> entry: data){
            output.add(0,(int)Double.parseDouble(entry.get("feelsLikeTemp")));//preprends it!
        }
        return output;
    }

    //ARGUMENTS: none
    //RETURNS: a list of the icons as File objects for each of the hours
    // (corresponds to the getHours list)
    public static List<File> getWeatherIcons(){//not sure what you mean by get weather icons?
        List<Map<String,String>> data = hourlydata_augmented();
        List<File> output = new LinkedList<>();
        for (Map<String, String> entry : data) {
            int wc = Integer.parseInt(entry.get("weather_code"));
            File icon = null;
            switch (wc) {
                case 0: icon = new File("src/data/hourlyicons/sunny.png");
                    break;
                case 1: icon = new File("src/data/hourlyicons/windy.png");
                    break;
                case 2: icon = new File("src/data/hourlyicons/cloudy.png");
                    break;
                case 3: icon = new File("src/data/hourlyicons/rainy.png");
                    break;
                case 4: icon = new File("src/data/hourlyicons/snowy.png");
                    break;
            }
            output.add(0, icon);    //prepends the image
        }

        return output;
    }
}
