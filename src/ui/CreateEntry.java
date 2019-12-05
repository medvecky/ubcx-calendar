package ui;

import model.*;

import java.util.Scanner;

public class CreateEntry {
    public static void createEntry(MyCalendar calendar) {
        String choice = null;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\t\t\t\t CREATE ENTRY");
            System.out.println();
            System.out.println("\t\t1.\t\tEvent");
            System.out.println("\t\t2.\t\tMeeting");
            System.out.println("\t\t3.\t\tReminder");
            System.out.println("\t\tr.\t\tReturn to main menu");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEvent(calendar);
                    break;
                case "2":
                    addMeeting(calendar);
                    break;
                case "3":
                    addReminder(calendar);
                    break;
                case "r":
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!choice.equals("r"));
    }

    public static void addEvent(MyCalendar calendar) {
        calendar.addEntry(createEvent());
    }

    public static Event createEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create event procedure...");
        MyDate date = createDate();
        MyTime time = createTime();
        System.out.print("Enter label text: ");
        String label = scanner.nextLine();
        System.out.print("Add reminder?(y/n): ");
        if (scanner.nextLine().equals("y")) {
            return new Event(date, time, label, createReminder());
        } else {
            return new Event(date, time, label);
        }
    }


    public static Reminder createReminder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create reminder procedure...");
        MyDate date = createDate();
        MyTime time = createTime();
        System.out.print("Enter label text: ");
        String label = scanner.nextLine();
        System.out.print("Enter note text (press enter for none): ");
        String note = scanner.nextLine();
        if (note.equals("")) {
            return new Reminder(date, time, label);
        }
        return new Reminder(date, time, label, note);
    }

    public static void addReminder(MyCalendar calendar) {
        calendar.addEntry(createReminder());
    }

    public static void addMeeting(MyCalendar calendar) {
        calendar.addEntry(createMeeting());
    }

    public static Meeting createMeeting() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create Meeting procedure...");
        MyDate date = createDate();
        MyTime time = createTime();
        System.out.print("Enter label text: ");
        String label = scanner.nextLine();
        System.out.print("Add reminder?(y/n): ");
        Meeting meeting = null;
        if (scanner.nextLine().equals("y")) {
            meeting = new Meeting(date, time, label, createReminder());
        } else {
            meeting = new Meeting(date, time, label);
        }
        System.out.print("Add attendees (just Enter for end): ");
        String attendee = null;
        while ((attendee = scanner.nextLine()).length() != 0) {
            System.out.println("Attendee: " + attendee + " " + attendee.length());
            meeting.addAttendee(attendee);
        }
        return meeting;
    }

    public static MyDate createDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter month: ");
        int month = scanner.nextInt();
        System.out.print("Enter date: ");
        int day = scanner.nextInt();

        return new MyDate(day, month, year);
    }

    public static MyTime createTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hours: ");
        int hours = scanner.nextInt();
        System.out.print("Enter minutes: ");
        int minutes = scanner.nextInt();
        return new MyTime(hours, minutes);
    }
}
