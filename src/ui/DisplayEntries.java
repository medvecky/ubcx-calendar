package ui;

import model.*;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Scanner;

public class DisplayEntries {
    public static void displayEntries(MyCalendar calendar) {
        String choice = null;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\t\t\t\tDISPLAY ENTRIES");
            System.out.println();
            System.out.println("\t\t1.\t\tFilter by type");
            System.out.println("\t\t2.\t\tFilter by date");
            System.out.println("\t\tr.\t\tReturn");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    filterEntriesByType(calendar);
                    break;
                case "2":
                    System.out.println("Filter by date procedure...");
                    break;
                case "r":
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!choice.equals("r"));
    }

    public static void filterEntriesByType(MyCalendar calendar) {
        String choice = null;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\t\t\t\tFILTER OPTIONS");
            System.out.println();
            System.out.println("\t\t1.\t\tShow Events");
            System.out.println("\t\t2.\t\tShow Meetings");
            System.out.println("\t\t3.\t\tShow Reminders");
            System.out.println("\t\tr.\t\tReturn");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showEvents(calendar);
                    break;
                case "2":
                    showMeeting(calendar);
                    break;
                case "3":
                    showReminder(calendar);
                    break;
                case "r":
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!choice.equals("r"));
    }

    public static void showEvents(MyCalendar calendar) {
        List<Entry> entries = calendar.getEvents(calendar.getEntries());
        int numberOfEntries = entries.size();
        System.out.println("\t\t\t\tEVENTS");

        if (numberOfEntries > 0) {
            for (int i = 0; i < numberOfEntries; i++) {
                Event event = (Event) entries.get(i);
                System.out.print("\t\t" + (i + 1) + ".\t\t");
                showEntryData(event);
                if (event.getReminder() != null) {
                    System.out.println("\t\t\t\tRemind about:");
                    System.out.println("\t\t\t\t\t" + event.getReminder().getDate().getShortDate());
                    System.out.println("\t\t\t\t\t" + event.getReminder().getNote());
                }
                System.out.println();
            }
        } else {
            System.out.println("No events found.");
        }
    }

    public static void showReminder(MyCalendar calendar) {
        List<Entry> entries = calendar.getReminders(calendar.getEntries());
        int numberOfEntries = entries.size();
        System.out.println("\t\t\t\tREMINDERS");
        if (numberOfEntries > 0) {
            for (int i = 0; i < numberOfEntries; i++) {
                System.out.print("\t\t" + (i + 1) + ".\t\t");
                Reminder reminder = (Reminder) entries.get(i);
                showEntryData(reminder);
                if (reminder.getNote() != null) {
                    System.out.print("\t\t\t\tNote: ");
                    System.out.println(reminder.getNote());
                }
                System.out.println();
            }
        } else {
            System.out.println("No reminders found.");
        }
    }

    public static void showMeeting(MyCalendar calendar) {
        List<Entry> entries = calendar.getMeetings(calendar.getEntries());
        int numberOfEntries = entries.size();
        System.out.println("\t\t\t\tMEETINGS");
        if (numberOfEntries > 0) {
            for (int i = 0; i < numberOfEntries; i++) {
                Meeting meeting = (Meeting) entries.get(i);
                System.out.print("\t\t" + (i + 1) + ".\t\t");
                showEntryData(meeting);
                if (meeting.getAttendees().size() > 0) {
                    System.out.println("\t\t\t\tAttendees");
                    meeting.getAttendees().forEach(System.out::println);
                }
                System.out.println();
            }
        } else {
            System.out.println("No meetings found.");
        }
    }

    static void showEntryData(Entry event) {
        System.out.println(event.getDate().getShortDate());
        System.out.println("\t\t\t\t" + event.getTime().getTime());
        System.out.println("\t\t\t\t" + event.getLabel());
        if (event.getIntervalOfRepetition() != null) {
            IntervalOfRepetition intervalOfRepetition = event.getIntervalOfRepetition();
            System.out.print("\t\t\t\tREPEAT AFTER: ");
            if (intervalOfRepetition.getYears() != 0) {
                System.out.println(intervalOfRepetition.getYears() + "years ");
            }
            if (intervalOfRepetition.getMonths() != 0) {
                System.out.println(intervalOfRepetition.getMonths() + "months ");
            }
            if (intervalOfRepetition.getDays() != 0) {
                System.out.println(intervalOfRepetition.getDays() + "days ");
            }
        }
    }
}
