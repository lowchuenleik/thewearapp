import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Weekly {
    private ArrayList<Summary> days;

    public Weekly() {
        days = new ArrayList<>();

        Date dt = new Date();

        for (int i=0; i<7; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, i);
            dt = c.getTime();
            Summary dayi = new Summary();
            //Summary dayi = new Summary(dt);

            //TODO: allow summary to take in a date to get the day's summary

            days.add(i, dayi);
        }
        this.days = days;
    }

    public ArrayList<Summary> getDays() {
        return days;
    }

    public void updateDays(ArrayList<Summary> days) {
        Date dt = new Date();
        for (int i=0; i<7; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, i);
            dt = c.getTime();
            Summary dayi = new Summary();
            //TODO: change to new summary constructor that takes in dt

            days.set(i, dayi);
        }
    }
}
