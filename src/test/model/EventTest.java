package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class EventTest {
    MyDate testDate;
    MyTime testTime;
    Event testEvent;
    Event testEventWithReminder;
    Reminder testReminder;

    @BeforeEach
    void setUp() {
        testDate = new MyDate(29, 11, 2019);
        testTime = new MyTime(21, 45);
        testReminder = new Reminder(testDate, testTime, "Test reminder");
        testEvent = new Event(testDate, testTime, "Test Event");
        testEventWithReminder = new Event(testDate, testTime, "Test Event", testReminder);
    }

    @Test
    void entryGettersTest() {
        assertEquals(testDate, testEvent.getDate());
        assertEquals(testTime, testEvent.getTime());
        assertEquals("Test Event", testEvent.getLabel());
        assertFalse(testEvent.isRepeating());
    }

    @Test
    void entryIntervalOfRepetitionTest() {
        testEvent.setIntervalOfRepetition(new IntervalOfRepetition(3,2,1));
        assertEquals(1, testEvent.getIntervalOfRepetition().getYears());
        assertEquals(2, testEvent.getIntervalOfRepetition().getMonths());
        assertEquals(3, testEvent.getIntervalOfRepetition().getDays());
        assertTrue(testEvent.isRepeating());

        testEvent.getIntervalOfRepetition().setDays(4);
        testEvent.getIntervalOfRepetition().setMonths(3);
        testEvent.getIntervalOfRepetition().setYears(2);

        assertEquals(2, testEvent.getIntervalOfRepetition().getYears());
        assertEquals(3, testEvent.getIntervalOfRepetition().getMonths());
        assertEquals(4, testEvent.getIntervalOfRepetition().getDays());
        assertTrue(testEvent.isRepeating());
    }

    @Test
    void testGetTimeAndDate() {
        assertEquals("09:45 PM", testEvent.getTime().getTime());
        assertEquals("Friday November 29, 2019", testEvent.getDate().getLongDate());
        assertEquals("29-11-2019", testEvent.getDate().getShortDate());
    }

    @Test
    void setReminder() {
        testEvent.setReminder(new Reminder(testDate, testTime, "Reminder"));

        assertEquals(testDate, testEvent.getReminder().getDate());
        assertEquals(testTime, testEvent.getReminder().getTime());

        testEvent.getReminder().setNote("Remind about");

        assertEquals("Remind about", testEvent.getReminder().getNote());
    }

    @Test
    void setReminderWithNote() {
        testEvent.setReminder(new Reminder(testDate, testTime, "Reminder", "Remind me about"));
        assertEquals("Remind me about", testEvent.getReminder().getNote());
    }

    @Test
    void createEventWithReminder() {
        assertEquals(testReminder, testEventWithReminder.getReminder());
    }
}