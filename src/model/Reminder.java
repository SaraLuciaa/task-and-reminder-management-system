package model;

import java.util.Calendar;

public class Reminder extends Activity{
    public Reminder(String tittle, String description, Calendar date, Calendar dayTime) {
        super(tittle, description, date, dayTime);
    }
}
