package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

import static ui.DisplayEntries.*;
import static ui.UpdateEntry.updateEntry;

public class FilterByDate {
    public static void filterByDate(MyCalendar calendar) {
        Scanner scanner = new Scanner(System.in);
        String choice = null;
        do {
            System.out.println("\t\t\t\tFILTER BY DATE");
            System.out.println();
            System.out.println("\t\t1.\t\tCurrent date " + calendar.getCurrentDate().getDay());
            System.out.println("\t\t2.\t\tCurrent month " + (calendar.getCurrentDate().getMonth() + 1));
            System.out.println("\t\t3.\t\tCurrent year " + calendar.getCurrentDate().getYear());
            System.out.println("\t\tr.\t\tReturn");
            System.out.println();
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showCurrentDateEntries(calendar);
                    break;
                case "2":
                    showCurrentMonthEntries(calendar);
                    break;
                case "3":
                    showCurrentYearEntries(calendar);
                    break;
                case "r":
                    break;
                default:
                    System.out.println("Invalid option");
            }

        } while (!choice.equals("r"));
    }

    public static void showCurrentDateEntries(MyCalendar calendar) {
        List<Entry> entries = calendar.filterByDay(
                calendar.filterByMonth(
                        calendar.filterByYear(
                                calendar.getEntries(),
                                calendar.getCurrentDate().getYear()),
                        calendar.getCurrentDate().getMonth() + 1),
                calendar.getCurrentDate().getDay());

        if (entries.size() > 0) {
            displayEntries(entries);
            updateEntry(calendar, entries);
        } else {
            System.out.println("Entries not found");
        }
    }

    public static void showCurrentMonthEntries(MyCalendar calendar) {
        List<Entry> entries =
                calendar.filterByMonth(
                        calendar.filterByYear(
                                calendar.getEntries(),
                                calendar.getCurrentDate().getYear()),
                        calendar.getCurrentDate().getMonth() + 1);
        if (entries.size() > 0) {
            displayEntries(entries);
            updateEntry(calendar, entries);
        } else {
            System.out.println("Entries not found");
        }
    }

    public static void showCurrentYearEntries(MyCalendar calendar) {
        List<Entry> entries =
                calendar.filterByYear(
                        calendar.getEntries(),
                        calendar.getCurrentDate().getYear());
        if (entries.size() > 0) {
            displayEntries(entries);
            updateEntry(calendar, entries);
        } else {
            System.out.println("Entries not found");
        }
    }

    static void displayEntries(List<Entry> entries) {
        int numberOfEntries = entries.size();

        for (int i = 0; i < numberOfEntries; i++) {
            System.out.print("\t\t" + (i + 1) + ".\t\t");
            Entry entry = entries.get(i);
            if (entry.getClass().equals(Event.class)) {
                System.out.println("EVENT");
                displayEvent((Event) entry);
            } else if (entry.getClass().equals(Reminder.class)) {
                System.out.println("REMINDER");
                displayReminder((Reminder) entry);
            } else if (entry.getClass().equals(Meeting.class)) {
                System.out.println("MEETING");
                displayMeeting((Meeting) entry);
            }
            System.out.println();
        }
    }
}
