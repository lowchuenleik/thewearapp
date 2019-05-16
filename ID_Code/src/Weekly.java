import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Weekly {
    private ArrayList<Summary> days;

    // TODO: Yulong here. Can you try including three getter methods, as described below?
    // Preferably String so I can just show them on the GUI
    // Otherwise, List<Integer> works, like you said, integer 0 means Sunday, integer 6 means Saturday
    public List<String> getDayNames(){
        return null;
    }
    // in Celsius
    public List<Integer> getTemperatures(){
        return null;
    }
    // in our mockup, we showed clothes icons I think, but I don't think it's too important to follow that if weather is much easier or something
    public List<File> getWeatherIcons(){
        return null;
    }

    public Weekly() {
        days = new ArrayList<>();

        for (int i=0; i<7; i++) {
            Summary dayi = new Summary(i);

            days.add(i, dayi);
        }
        this.days = days;
    }

    public ArrayList<Summary> getDays() {
        return days;
    }

    public void updateDays(ArrayList<Summary> days) {
        for (int i=0; i<7; i++) {
            Summary dayi = new Summary(i);

            days.set(i, dayi);
        }
    }

    public static void main(String[] args) {

    }
}
