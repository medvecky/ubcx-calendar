package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MyCalendar {
    private MyDate currentDate;
    private String email;
    private List<Entry> entries;

    public MyCalendar(String email) {
        this.email = email;
        entries = new ArrayList<>();
        currentDate = new MyDate(
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    private Optional<Integer> getEntryPosition(Entry entry) {
        Optional<Entry> optionalEntry = entries.stream()
                .filter(e ->
                        (e.getTime().getTime().equals(entry.getTime().getTime()) &&
                                e.getDate().getShortDate().equals(entry.getDate().getShortDate())))
                .findFirst();
        return optionalEntry.map(value -> entries.indexOf(value));
    }

    public MyDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(MyDate currentDate) {
        this.currentDate = currentDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        if (!entries.contains(entry)) {
            entries.add(entry);
        }
    }

    public void removeEntry(Entry entry) {
        if (entries.contains(entry)) {
            entries.remove(entry);
        }
    }

    public void updateEntry(Entry oldEntry, Entry newEntry) {
        Optional<Integer> entryPosition = getEntryPosition(oldEntry);
        if (entryPosition.isPresent()) {
            updateEntry(entryPosition.get(), newEntry);
        } else {
            entries.add(newEntry);
        }
    }

    public void updateEntry(int entryPosition, Entry newEntry) {
       entries.set(entryPosition, newEntry);
    }

    public List<Entry> getEvents(List<Entry> entries) {
        return entries.stream()
                .filter(entry -> entry.getClass().equals(Event.class))
                .collect(Collectors.toList());
    }

    public List<Entry> getMeetings(List<Entry> entries) {
        return entries.stream()
                .filter(entry -> entry.getClass().equals(Meeting.class))
                .collect(Collectors.toList());
    }

    public List<Entry> getReminders(List<Entry> entries) {
        return entries.stream()
                .filter(entry -> entry.getClass().equals(Reminder.class))
                .collect(Collectors.toList());
    }

    public List<Entry> filterByYear(List<Entry> entries, int year) {
        return entries.stream()
                .filter(entry -> entry.getDate().getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Entry> filterByMonth(List<Entry> entries, int month) {
        return entries.stream()
                .filter(entry -> entry.getDate().getMonth() == month)
                .collect(Collectors.toList());
    }

    public List<Entry> filterByDay(List<Entry> entries, int day) {
        return entries.stream()
                .filter(entry -> entry.getDate().getDay() == day)
                .collect(Collectors.toList());
    }
}
