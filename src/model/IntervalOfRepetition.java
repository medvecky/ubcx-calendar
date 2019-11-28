package model;

public class IntervalOfRepetition {
    private MyDate date;
    private MyTime time;

    public IntervalOfRepetition(MyDate date) {
        this.date = date;
        this.time = null;
    }

    public IntervalOfRepetition(MyTime time) {
        this.time = time;
        this.date = null;
    }
    public IntervalOfRepetition(MyDate date, MyTime time) {
        this.date = date;
        this.time = time;
    }

    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate date) {
        this.date = date;
    }

    public MyTime getTime() {
        return time;
    }

    public void setTime(MyTime time) {
        this.time = time;
    }
}
