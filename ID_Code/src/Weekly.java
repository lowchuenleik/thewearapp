import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Weekly {
    private ArrayList<Summary> days;

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
