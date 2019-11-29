package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
                .filter(e -> (e.getLabel().equals(entry.getLabel()) && e.getDate().equals(entry.getDate())))
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
       if(!entries.contains(entry)) {
           entries.add(entry);
       }
    }

    public void removeEntry(Entry entry) {
        if(entries.contains(entry)) {
            entries.remove(entry);
        }
    }

    public void updateEntry(Entry oldEntry, Entry newEntry) {
        Optional<Integer> entryPosition = getEntryPosition(oldEntry);
        if(entryPosition.isPresent()) {
            entries.set(entryPosition.get(), newEntry);
        } else {
            entries.add(newEntry);
        }
    }
}
