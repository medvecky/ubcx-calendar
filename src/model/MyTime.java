package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyTime {
    private LocalTime time;

    // REQUIRES: hours 0 - 23, minutes 0 - 59
    // MODIFIES: this
    // EFFECTS: creates new MyTime object
    public MyTime(int hours, int minutes) {
        time = LocalTime.of(hours, minutes);
    }

    // EFFECTS: returns time as example 06:35 PM
    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }
}
