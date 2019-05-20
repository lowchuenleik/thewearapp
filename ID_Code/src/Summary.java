import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private String day_of_week;

    public Summary() {
        API api_instance = new API();
        List<Map<String,String>> daily_data = null;
        List<Map<String,String>> weekly_data = null;
        try{
            daily_data = api_instance.getDaily();//hourly, 24 elements
            weekly_data = api_instance.getWeekly();//daily
        } catch (IOException e){
            System.out.println("ERROR, PLEASE DEBUG");
        }
        //Days summary?
        Map<String,String> todays_data = weekly_data.get(0);
        this.rainProbability = Integer.parseInt(todays_data.get("probabilityOfRain"));
        this.weatherCode = Integer.parseInt(todays_data.get("weather_code"));
        this.averageTemp = (int)Double.parseDouble(todays_data.get("temp"));
        this.lowTemp = (int)Double.parseDouble(todays_data.get("tempMIN"));
        this.apparentTemp = (int)Double.parseDouble(daily_data.get(0).get("feelsLikeTemp"));
        this.cloudCoverage = Integer.parseInt(todays_data.get("cloud_coverage"));
        this.day_of_week = LocalDate.now().getDayOfWeek().name().substring(0,3);
    }

    public Summary(int day_of_week) {
        API api_instance = new API();
        List<Map<String,String>> daily_data = null;
        List<Map<String,String>> weekly_data = null;
        try{
            daily_data = api_instance.getDaily();//hourly, 24 elements
            weekly_data = api_instance.getWeekly();//daily
        } catch (IOException e){
            System.out.println("ERROR, PLEASE DEBUG");
        }
        //Days summary?
        Map<String,String> todays_data = weekly_data.get(day_of_week);
        System.out.println(todays_data);
        this.rainProbability = Integer.parseInt(todays_data.get("probabilityOfRain"));
        this.weatherCode = Integer.parseInt(todays_data.get("weather_code"));
        this.highTemp = (int)Double.parseDouble(todays_data.get("tempMAX"));
        this.averageTemp = (int)Double.parseDouble(todays_data.get("temp"));
        this.lowTemp = (int)Double.parseDouble(todays_data.get("tempMIN"));
        //Hacky fix due to conflicting API input
        Integer temp = (int)(Double.parseDouble(todays_data.get("feelsLikeTemp_MAX"))-Double.parseDouble(todays_data.get("feelsLikeTemp_MIN")));
        this.apparentTemp =  temp;
        this.cloudCoverage = Integer.parseInt(todays_data.get("cloud_coverage"));
        String weekDay = todays_data.get("date");
        System.out.println(weekDay);
        this.day_of_week = date_to_day(weekDay);
        System.out.println(this.day_of_week);
    }

    public String date_to_day(String in_date){
//        Calendar cal = Calendar.getInstance();
//        Date temp = cal.getTime();
        try{
            System.out.println("printing date_to_day");
            System.out.println("in_date: " + in_date);
            Date temp = new SimpleDateFormat("yyyy-MM-dd").parse(in_date);
            System.out.println(temp);
            String out = new SimpleDateFormat("EE").format(temp);
            System.out.println(out);
            return out;
        } catch (ParseException e){
            System.out.println("Error with date format...");
            return Calendar.getInstance().getTime().toString();
        }
        // Date temp = new SimpleDateFormat("yyyy-MM-dd").parse(in_date);
//
//        return out;
    }

    public int getRainProbability() {
        return this.rainProbability;
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

    public String getDay(){
        return day_of_week;
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

    @Override
    public String toString() {
        return rainProbability + " ,"
                + weatherCode + " ,"
                + highTemp + " ,"
                + averageTemp + " ,"
                + lowTemp + " ,"
                + apparentTemp + " ,"
                + partOfDay + " ,"
                + cloudCoverage + " ,";
    }

    public static void main(String[] args){
        Summary trial = new Summary();
        System.out.println(trial.toString());
    }
    
}
