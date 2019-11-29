package model;

public class Reminder extends Entry {
    private String note;
    public Reminder(MyDate date, MyTime time, String label) {
        super(date, time, label);
    }

    public Reminder(MyDate date, MyTime time, String label, String note) {
        super(date, time, label);
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
