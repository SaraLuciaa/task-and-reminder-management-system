package model;
import java.util.Calendar;

public class Activity {
    private String tittle;
    private String description;
    private Calendar date;
    private Calendar dayTime;

    public Activity(String tittle, String description, Calendar date, Calendar dayTime){
        this.tittle=tittle;
        this.description=description;
        this.date=date;
        this.dayTime=dayTime;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDate() {
        return date;
    }

    public Calendar getDayTime() {
        return dayTime;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDayTime(Calendar dayTime) {
        this.dayTime = dayTime;
    }
}
