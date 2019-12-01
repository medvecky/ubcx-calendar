package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MyDate {
    private Date date;

    //    REQUIRES: day 1 - 31, month 1 12, year in format YYYY
    //    MODIFIES: this
    //    EFFECTS: creates new MyDate object
    public MyDate(int day, int month, int year) {
        date = new GregorianCalendar(year, month - 1, day).getTime();
    }

    // EFFECTS: returns date in format dd-MM-YYYY
    public String getShortDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
        return format.format(date);
    }

    // EFFECTS: returns date as example Tuesday January 01, 2019
    public String getLongDate() {
        SimpleDateFormat format = new SimpleDateFormat("EEEEE MMMMM dd, yyyy");
        return format.format(date);
    }

    public int getYear() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Riga"));
        cal.setTime(date);
        return  cal.get(Calendar.YEAR);
    }

    public int getMonth() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Riga"));
        cal.setTime(date);
        return  cal.get(Calendar.MONTH) + 1;
    }

    public int getDay() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Riga"));
        cal.setTime(date);
        return  cal.get(Calendar.DAY_OF_MONTH);
    }
}
