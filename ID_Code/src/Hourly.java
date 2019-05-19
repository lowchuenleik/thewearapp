import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class Hourly {
    private int hour;
    private int temp;
    private File weathericon;
    private int weathercode;

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
    

    public Hourly(int hour) {
        API api_instance = new API();
        List<Map<String,String>> daily_data = null;
        String intime = "";
        Date temp = null;
        Map<String,String> selected_hour = new HashMap<String,String>();
        try{
            daily_data = api_instance.getDaily();//hourly, 24 elements
        } catch (IOException e){
            System.out.println("ERROR, PLEASE DEBUG");
        }
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
            }
        }
        
        this.temp = (int)Double.parseDouble(selected_hour.get("feelsLikeTemp"));
        this.hour = hour;
        this.weathericon = null;    //TODO: get the weather icon for that hour (from API?)
        this.weathercode = Integer.parseInt(selected_hour.get("weather_code"));
    }

    public int getHour() {
        return hour;
    }
    //do we need set hour???
    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getTemp() {
        return temp;
    }

    public void updateTemp() {
        Map<String,String> freshdata = refreshdata();
        this.temp = (int)Double.parseDouble(freshdata.get("feelsLikeTemp")); 
    }

    public File getWeathericon() {
        return weathericon;
    }
    public void updateWeathericon() {
        this.weathericon = null; //TODO: get the weather icon
    }

    public int getWeathercode() {
        return weathercode;
    }
    public void setWeathercode() {
        Map<String,String> freshdata = refreshdata();
        this.weathercode = (int)Double.parseDouble(freshdata.get("weather_code"));
    }

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
            }
        }
        return selected_hour;
    }

    public List<Map<String,String>> hourlydata_augmented(){
        API api_instance = new API();
        List<Map<String,String>> daily_data = new LinkedList<>();
        String intime = "";
        Date temp = null;
        try{
            daily_data = api_instance.getDaily();//hourly, 24 elements
        } catch (IOException e){
            System.out.println("ERROR, PLEASE DEBUG");
        }
        for (Map<String,String> entry: daily_data){
            intime = entry.get("localTime");
            try{
                temp = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss").parse(intime);
            } catch (ParseException e){
                System.out.println("ERROR, PLEASE DEBUG");
            }
            Integer twentyfourhtime = Integer.parseInt(new SimpleDateFormat("HH:mm").format(temp));
            entry.put("hour",twentyfourhtime.toString());
        }
        return daily_data;
    }
}
