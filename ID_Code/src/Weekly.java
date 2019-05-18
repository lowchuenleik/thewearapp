import java.io.File;
import java.util.*;

public class Weekly {
    private ArrayList<Summary> days;

    //CONSTRUCTOR: Weekly stores all of the days of the week as Summary objects
    public Weekly() {
        days = new ArrayList<>();

        //creates a summary for every day of the week
        for (int i=0; i<7; i++) {
            Summary dayi = new Summary(i);
            days.add(i, dayi);
        }

        this.days = days;
    }

    //ARGUMENTS: none
    //RETURNS: a list of all the days as Summary objects so that the main
    // screen can easily be rendered for any of the days
    public List<Summary> getDaySummaries() {
        return days;
    }

    //ARGUMENTS: none
    //RETURNS: none
    //This refreshes all the data held in the week object so that it is up-to-date
    public void updateWeek() {
        //creates a new summary for each day and replaces the old one
        for (int i=0; i<7; i++) {
            Summary dayi = new Summary(i);
            days.set(i, dayi);
        }
    }

    //ARGUMENTS: none
    //RETURNS: a list of the days of the week as strings
    public List<String> getDayNames(){
        List<String> daynames = new LinkedList<>();
        for (Summary d : days) {
            daynames.add(0, d.getDay()); //prepends the list
        }

        return daynames;
    }

    //ARGUMENTS: none
    //RETURNS: a list of all the temperatures as integers in degrees celsius for
    // each of the days of the week (corresponding to the getDayNames() list)
    public List<Integer> getTemperatures(){
        List<Integer> daytemps = new LinkedList<>();
        for (Summary d : days) {
            daytemps.add(0, d.getAverageTemp());
        }

        return daytemps;
    }

    //ARGUMENTS: none
    //RETURNS: a list of all the files of the icons that represent the weather
    // for each of the days of the week (corresponding to the getDayNames() list)
    public List<File> getWeatherIcons(){
        List<File> dayicons = new LinkedList<>();
        for (Summary d : days) {
            //gets the weathercode for the day and sets the icon accordingly
            int wc = d.getWeatherCode();

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

            dayicons.add(0, icon);
        }

        return dayicons;
    }
}
