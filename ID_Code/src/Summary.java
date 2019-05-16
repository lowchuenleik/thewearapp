import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Summary {
    private int rainProbability;
    private int weatherCode;
    private double highTemp;
    private int averageTemp;
    private double lowTemp;
    private int apparentTemp;
    private String partOfDay;
    private int cloudCoverage;
    private API api_instance;

    public Summary() throws IOException {
        API api_instance = new API();
        List<Map<String,String>> daily_data = api_instance.getDaily();//hourly, 24 elements
        List<Map<String,String>> weekly_data = api_instance.getWeekly();//daily
        //Days summary?
        Map<String,String> todays_data = weekly_data.get(0);
        this.rainProbability = Integer.parseInt(todays_data.get("probabilityOfRain"));
        this.weatherCode = Integer.parseInt(todays_data.get("weather_code"));
        this.highTemp = Double.parseDouble(todays_data.get("feelsLikeTemp_MAX"));
        this.averageTemp = (int)Double.parseDouble(todays_data.get("temp"));
        this.lowTemp = Double.parseDouble(todays_data.get("feelsLikeTemp_MIN"));
        this.apparentTemp = (int)Math.round(Double.parseDouble(daily_data.get(0).get("feelsLikeTemp")));
        this.cloudCoverage = 0;
    }

    public int getRain() { return rainProbability;}

    public int getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(int rainProbability) {
        this.rainProbability = rainProbability;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public double getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(int highTemp) {
        this.highTemp = highTemp;
    }

    public int getAverageTemp() {
        return averageTemp;
    }

    public void setAverageTemp(int averageTemp) {
        this.averageTemp = averageTemp;
    }

    public double getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(int lowTemp) {
        this.lowTemp = lowTemp;
    }

    public int getApparentTemp() {
        return apparentTemp;
    }

    public void setApparentTemp(int apparentTemp) {
        this.apparentTemp = apparentTemp;
    }

    public String getPartOfDay() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 19 && timeOfDay < 6){
            partOfDay = "n";
        }else{
            partOfDay = "d";
        }
        return partOfDay;
    }

    //Day or night?
    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }

    public int getCloudCoverage() {
        return cloudCoverage;
    }

    public void setCloudCoverage(int cloudCoverage) {
        this.cloudCoverage = cloudCoverage;
    }

    
}
