package model;

public abstract class Entry {
    private MyDate date;
    private MyDate time;
    private String label;
    private boolean isRepeating;
    private IntervalOfRepetition intervalOfRepetition;

    // REQUIRES: Date and/or time must be in thr future
    // MODIFIES: this
    // EFFECTS: creates new Entry object
    public Entry(MyDate date, MyDate time, String label) {
        this.date = date;
        this.time = time;
        this.label = label;
        isRepeating = false;
        intervalOfRepetition = null;
    }

    public MyDate getDate() {
        return date;
    }

    public MyDate getTime() {
        return time;
    }

    public String getLabel() {
        return label;
    }

    public boolean isRepeating() {
        return isRepeating;
    }

    public IntervalOfRepetition getIntervalOfRepetition() {
        return intervalOfRepetition;
    }

    public void setIntervalOfRepetition(IntervalOfRepetition intervalOfRepetition) {
        this.intervalOfRepetition = intervalOfRepetition;
        isRepeating = true;
    }
}
