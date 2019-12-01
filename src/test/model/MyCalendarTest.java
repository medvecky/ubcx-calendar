package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyCalendarTest {
    MyCalendar testCalendar;

    @BeforeEach
    void setUp() {
        testCalendar = new MyCalendar("test@gmail.com");
    }

    @Test
    void getCurrentDate() {
        MyDate currentDate = new MyDate(
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        assertEquals(currentDate.getLongDate(), testCalendar.getCurrentDate().getLongDate());
    }

    @Test
    void setCurrentDate() {
        testCalendar.setCurrentDate(new MyDate(10, 5, 2030));
        assertEquals("10-05-2030", testCalendar.getCurrentDate().getShortDate());
    }

    @Test
    void getEmail() {
        assertEquals("test@gmail.com", testCalendar.getEmail());
    }

    @Test
    void setEmail() {
        testCalendar.setEmail("tester@gmail.com");
        assertEquals("tester@gmail.com", testCalendar.getEmail());
    }

    @Test
    void getEntries() {
        assertEquals(0, testCalendar.getEntries().size());
    }

    @Test
    void addEntry() {
        Entry newEntry = new Event(
                new MyDate(10, 12, 2019),
                new MyTime(9, 0),
                "Test event");
        testCalendar.addEntry(newEntry);
        assertEquals(1, testCalendar.getEntries().size());
        assertEquals(newEntry, testCalendar.getEntries().get(0));

        testCalendar.addEntry(newEntry);
        assertEquals(1, testCalendar.getEntries().size());
    }

    @Test
    void removeEntry() {
        Entry newExistingEntry = new Event(
                new MyDate(10, 12, 2019),
                new MyTime(9, 0),
                "Test event");

        Entry newNotExistingEntry = new Event(
                new MyDate(10, 12, 2019),
                new MyTime(9, 0),
                "Test event not added");

        testCalendar.addEntry(newExistingEntry);
        testCalendar.removeEntry(newNotExistingEntry);

        assertEquals(1, testCalendar.getEntries().size());
        assertEquals(newExistingEntry, testCalendar.getEntries().get(0));

        testCalendar.removeEntry(newExistingEntry);
        assertEquals(0, testCalendar.getEntries().size());
    }

    @Test
    void updateEntry() {
        Entry newTestEntry = new Event(
                new MyDate(10, 12, 2019),
                new MyTime(9, 0),
                "Test new event");
        Entry newUpdatedEntrySameTime = new Event(
                new MyDate(11, 12, 2019),
                new MyTime(9, 0),
                "Test updated event same time");
        Entry newUpdatedEntrySameDate = new Event(
                new MyDate(10, 12, 2019),
                new MyTime(9, 15),
                "Test updated event same date");
        Entry newUpdatedEntry = new Event(
                new MyDate(10, 12, 2019),
                new MyTime(9, 0),
                "Test updated event");

        testCalendar.addEntry(newTestEntry);
        testCalendar.updateEntry(newUpdatedEntrySameTime, newUpdatedEntry);

        assertEquals(2, testCalendar.getEntries().size());

        testCalendar.updateEntry(newUpdatedEntrySameDate, newUpdatedEntry);

        assertEquals(3, testCalendar.getEntries().size());

        testCalendar.updateEntry(newTestEntry, newUpdatedEntry);

        assertEquals(3, testCalendar.getEntries().size());
        assertEquals("Test updated event", testCalendar.getEntries().get(0).getLabel());
    }

    @Test
    void updateEntryInTheMiddle() {
        Entry newTestEntry1 = new Event(
                new MyDate(10, 12, 2019),
                new MyTime(9, 0),
                "Test new event1");
        Entry newTestEntry2 = new Event(
                new MyDate(11, 12, 2019),
                new MyTime(9, 0),
                "Test new event2");
        Entry newTestEntry3 = new Event(
                new MyDate(10, 12, 2019),
                new MyTime(9, 15),
                "Test new event3");
        Entry newUpdatedEntry = new Event(
                new MyDate(10, 12, 2019),
                new MyTime(9, 0),
                "Test updated event");

        testCalendar.addEntry(newTestEntry1);
        testCalendar.addEntry(newTestEntry2);
        testCalendar.addEntry(newTestEntry3);

        testCalendar.updateEntry(newTestEntry2, newUpdatedEntry);

        assertEquals(3, testCalendar.getEntries().size());
        assertEquals("Test updated event", testCalendar.getEntries().get(1).getLabel());
    }

    @Test
    void getEventsEmpty() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Reminder(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Reminder 2");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        assertEquals(0, testCalendar.getEvents(testCalendar.getEntries()).size());
    }

    @Test
    void getEventsOne() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Reminder(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Reminder 2");
        Entry entry3 = new Event(new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Event 3");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry3);
        testCalendar.addEntry(entry2);

        List<Entry> foundEvents = testCalendar.getEvents(testCalendar.getEntries());
        assertEquals(1, foundEvents.size());
        assertEquals("Entry Event 3", foundEvents.get(0).getLabel());
    }

    @Test
    void getEventsFew() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Reminder(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Reminder 2");
        Entry entry3 = new Event(new MyDate(3, 2, 2030),
                new MyTime(21, 15),
                "Entry Event 3");
        Entry entry4 = new Event(new MyDate(4, 2, 2030),
                new MyTime(21, 15),
                "Entry Event 4");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry3);
        testCalendar.addEntry(entry2);
        testCalendar.addEntry(entry4);

        List<Entry> foundEvents = testCalendar.getEvents(testCalendar.getEntries());
        assertEquals(2, foundEvents.size());
        assertEquals("Entry Event 3", foundEvents.get(0).getLabel());
        assertEquals("Entry Event 4", foundEvents.get(1).getLabel());
    }

    @Test
    void getMeetingsEmpty() {
        Entry entry1 = new Event(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Event 1");
        Entry entry2 = new Reminder(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Reminder 2");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        assertEquals(0, testCalendar.getMeetings(testCalendar.getEntries()).size());
    }

    @Test
    void getMeetingsOne() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Reminder(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Reminder 2");
        Entry entry3 = new Event(new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Event 3");
        testCalendar.addEntry(entry3);
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);

        List<Entry> foundEvents = testCalendar.getMeetings(testCalendar.getEntries());
        assertEquals(1, foundEvents.size());
        assertEquals("Entry Meeting 1", foundEvents.get(0).getLabel());
    }

    @Test
    void getMeetingsFew() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Reminder(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Reminder 2");
        Entry entry3 = new Event(new MyDate(3, 2, 2030),
                new MyTime(21, 15),
                "Entry Event 3");
        Entry entry4 = new Meeting(new MyDate(4, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 4");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry3);
        testCalendar.addEntry(entry4);
        testCalendar.addEntry(entry2);

        List<Entry> foundEvents = testCalendar.getMeetings(testCalendar.getEntries());
        assertEquals(2, foundEvents.size());
        assertEquals("Entry Meeting 1", foundEvents.get(0).getLabel());
        assertEquals("Entry Meeting 4", foundEvents.get(1).getLabel());
    }

    @Test
    void getRemindersEmpty() {
        Entry entry1 = new Event(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Event 1");
        Entry entry2 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        assertEquals(0, testCalendar.getReminders(testCalendar.getEntries()).size());
    }

    @Test
    void getRemindersOne() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Reminder(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Reminder 2");
        Entry entry3 = new Event(new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Event 3");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        testCalendar.addEntry(entry3);

        List<Entry> foundEvents = testCalendar.getReminders(testCalendar.getEntries());
        assertEquals(1, foundEvents.size());
        assertEquals("Entry Reminder 2", foundEvents.get(0).getLabel());
    }

    @Test
    void getRemindersFew() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Reminder(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Reminder 2");
        Entry entry3 = new Event(new MyDate(3, 2, 2030),
                new MyTime(21, 15),
                "Entry Event 3");
        Entry entry4 = new Reminder(new MyDate(4, 2, 2030),
                new MyTime(21, 15),
                "Entry Reminder 4");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        testCalendar.addEntry(entry3);
        testCalendar.addEntry(entry4);

        List<Entry> foundEvents = testCalendar.getReminders(testCalendar.getEntries());
        assertEquals(2, foundEvents.size());
        assertEquals("Entry Reminder 2", foundEvents.get(0).getLabel());
        assertEquals("Entry Reminder 4", foundEvents.get(1).getLabel());
    }

    @Test
    void filterByYearEmpty() {

        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Meeting(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        assertEquals(0, testCalendar.filterByYear(testCalendar.getEntries(),2031).size());
    }

    @Test
    void filterByYearOne() {

        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Meeting(
                new MyDate(2, 2, 2031),
                new MyTime(21, 15),
                "Entry Meeting 2");
        Entry entry3 = new Meeting(
                new MyDate(3, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 3");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        testCalendar.addEntry(entry3);
        List<Entry> foundEntries = testCalendar.filterByYear(testCalendar.getEntries(), 2031);
        assertEquals(1, foundEntries.size());
        assertEquals("Entry Meeting 2", foundEntries.get(0).getLabel());
    }

    @Test
    void filterByYearFew() {

        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Meeting(
                new MyDate(2, 2, 2031),
                new MyTime(21, 15),
                "Entry Meeting 2");
        Entry entry3 = new Meeting(
                new MyDate(3, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 3");
        Entry entry4 = new Meeting(
                new MyDate(4, 2, 2031),
                new MyTime(21, 15),
                "Entry Meeting 4");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        testCalendar.addEntry(entry3);
        testCalendar.addEntry(entry4);
        List<Entry> foundEntries = testCalendar.filterByYear(testCalendar.getEntries(), 2031);
        assertEquals(2, foundEntries.size());
        assertEquals("Entry Meeting 2", foundEntries.get(0).getLabel());
        assertEquals("Entry Meeting 4", foundEntries.get(1).getLabel());
    }

    @Test
    void filterByMonthEmpty() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Meeting(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        assertEquals(0, testCalendar.filterByMonth(testCalendar.getEntries(),3).size());
    }

    @Test
    void filterByMonthOne() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Meeting(
                new MyDate(2, 3, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");
        Entry entry3 = new Meeting(
                new MyDate(3, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 3");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        testCalendar.addEntry(entry3);
        List<Entry> foundEntry = testCalendar.filterByMonth(testCalendar.getEntries(), 3);
        assertEquals(1, foundEntry.size());
        assertEquals("Entry Meeting 2", foundEntry.get(0).getLabel());
    }

    @Test
    void filterByMonthFew() {
        Entry entry1 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Meeting(
                new MyDate(2, 3, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");
        Entry entry3 = new Meeting(
                new MyDate(3, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 3");
        Entry entry4 = new Meeting(
                new MyDate(4, 3, 2030),
                new MyTime(21, 15),
                "Entry Meeting 4");

        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        testCalendar.addEntry(entry3);
        testCalendar.addEntry(entry4);
        List<Entry> foundEntry = testCalendar.filterByMonth(testCalendar.getEntries(), 3);
        assertEquals(2, foundEntry.size());
        assertEquals("Entry Meeting 2", foundEntry.get(0).getLabel());
        assertEquals("Entry Meeting 4", foundEntry.get(1).getLabel());
    }

    @Test
    void filterByDayEmpty() {
        Entry entry1 = new Meeting(
                new MyDate(1, 1, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Meeting(
                new MyDate(1, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");
        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        assertEquals(0, testCalendar.filterByDay(testCalendar.getEntries(),2).size());
    }

    @Test
    void filterByDayOne() {
        Entry entry1 = new Meeting(
                new MyDate(1, 1, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Meeting(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");
        Entry entry3 = new Meeting(
                new MyDate(1, 3, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");

        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        testCalendar.addEntry(entry3);
        List<Entry> foundEntries = testCalendar.filterByDay(testCalendar.getEntries(), 2);
        assertEquals(1, foundEntries.size());
        assertEquals("Entry Meeting 2", foundEntries.get(0).getLabel());
    }

    @Test
    void filterByDayFew() {
        Entry entry1 = new Meeting(
                new MyDate(1, 1, 2030),
                new MyTime(21, 15),
                "Entry Meeting 1");
        Entry entry2 = new Meeting(
                new MyDate(2, 2, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");
        Entry entry3 = new Meeting(
                new MyDate(3, 3, 2030),
                new MyTime(21, 15),
                "Entry Meeting 2");
        Entry entry4 = new Meeting(
                new MyDate(2, 4, 2030),
                new MyTime(21, 15),
                "Entry Meeting 4");


        testCalendar.addEntry(entry1);
        testCalendar.addEntry(entry2);
        testCalendar.addEntry(entry3);
        testCalendar.addEntry(entry4);
        List<Entry> foundEntries = testCalendar.filterByDay(testCalendar.getEntries(), 2);
        assertEquals(2, foundEntries.size());
        assertEquals("Entry Meeting 2", foundEntries.get(0).getLabel());
        assertEquals("Entry Meeting 4", foundEntries.get(1).getLabel());
    }
}