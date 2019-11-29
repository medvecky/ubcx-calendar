package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeetingTest {

    Meeting testMeeting;
    Meeting getTestMeetingWithReminder;

    @BeforeEach
    void setUp() {
        testMeeting = new Meeting(
                new MyDate(23, 12, 2025),
                new MyTime(21, 45),
                "Test meeting");
        getTestMeetingWithReminder = new Meeting(
                new MyDate(23, 12, 2025),
                new MyTime(21, 45),
                "Test meeting",
                new Reminder(
                        new MyDate(1, 1, 2020),
                        new MyTime(21, 45),
                        "Test"));
    }

    @Test
    void getAttendees() {
        assertEquals(0, testMeeting.getAttendees().size());
        assertEquals(0, getTestMeetingWithReminder.getAttendees().size());
    }

    @Test
    void addAttendee() {
        testMeeting.addAttendee("Tester");
        assertEquals(1, testMeeting.getAttendees().size());
        assertEquals("Tester", testMeeting.getAttendees().get(0));

        testMeeting.addAttendee("Tester1");
        assertEquals(2, testMeeting.getAttendees().size());
        assertEquals("Tester1", testMeeting.getAttendees().get(1));

        testMeeting.addAttendee("Tester");
        assertEquals(2, testMeeting.getAttendees().size());
        assertEquals("Tester1", testMeeting.getAttendees().get(1));
    }

    @Test
    void removeAttendee() {
        testMeeting.addAttendee("Tester");
        testMeeting.addAttendee("Tester1");

        testMeeting.removeAttendee("Tester10");

        assertEquals(2, testMeeting.getAttendees().size());

        testMeeting.removeAttendee("Tester");
        assertEquals(1, testMeeting.getAttendees().size());
        assertEquals("Tester1", testMeeting.getAttendees().get(0));
    }


}