package model;

import javafx.fxml.FXML;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Activity implements Cloneable,  Comparable<Activity> {
    private String tittle;
    private String description;
    private Calendar date;
    private String formattedDate;

    public Activity(String tittle, String description, Calendar date){
        this.tittle=tittle;
        this.description=description;
        this.date=date;
        this.formattedDate = formatDate();
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


    @FXML
    public String getFormattedDate() {
        return formattedDate;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Calendar date) {
        this.date = date;
        this.formattedDate = formatDate();
    }

    public String toString(){
        return tittle + " " + description + " " + date;
    }

    public String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formattedDate = sdf.format(date.getTime());
        return formattedDate;
    }

    @Override
    public int compareTo(Activity other) {
        return this.date.compareTo(other.date);
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
