package model;

import javafx.fxml.FXML;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Activity implements Cloneable {
    private String tittle;
    private String description;
    private Calendar date;
    public Activity(String tittle, String description, Calendar date){
        this.tittle=tittle;
        this.description=description;
        this.date=date;
    }

    @FXML
    public String getTittle() {
        return tittle;
    }

    @FXML
    public String getDescription() {
        return description;
    }

    @FXML
    public Calendar getDate() {
        return date;
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

    public String toString(){
        return tittle + " " + description + " " + date;
    }

    @Override
    public Activity clone() {
        try {
            Activity clonedActivity = (Activity) super.clone();

            Calendar clonedDate = new GregorianCalendar();
            clonedDate.setTimeInMillis(this.date.getTimeInMillis());
            clonedActivity.date = clonedDate;

            return clonedActivity;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
