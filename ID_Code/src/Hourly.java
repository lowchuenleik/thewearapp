import java.io.File;
import java.util.List;

public class Hourly {
    private int hour;
    private int temp;
    private File weathericon;
    private int weathercode;

    // TODO: Yulong here. Can you try including three getter methods, as described below? (@Chuen: you should be able to easily get the hours and temp from the hourly forecast)
    // preferably String so I can just show them e.g. "01:00" or "13:00"
    // Otherwise, List<Integer> also works, e.g. integer 1 means 1am, integer 23 means 11pm
    public List<String> getHours(){
        return null;
    }
    // in Celsius
    public List<Integer> getTemperatures(){
        return null;
    }
    public List<File> getWeatherIcons(){
        return null;
    }

    public Hourly(int hour) {
        this.hour = hour;
        this.temp = 0; //TODO: get temp for the hour that matches hour from API
        this.weathericon = null;    //TODO: get the weather icon for that hour (from API?)
        this.weathercode = 0;   //TODO: get the weather code for the hour from API
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
        this.temp = 0;  //TODO: get the temperature for that hour from API
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
        this.weathercode = 0; //TODO: update the weather code from the API
    }
}
