package model;

import java.util.Calendar;

public class Reminder extends Activity implements Cloneable, Comparable<Activity> {
    public Reminder(String tittle, String description, Calendar date) {
        super(tittle, description, date);
    }


}
