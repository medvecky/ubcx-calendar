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
                    createEvent(calendar);
                    break;
                case "2":
                    System.out.println("Create meeting procedure");
                    break;
                case "3":
                    System.out.println("Create reminder procedure");
                    break;
                case "r":
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!choice.equals("r"));
    }

    public static void createEvent(MyCalendar calendar) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create event procedure...");
        MyDate date =  createDate();
        MyTime time =  createTime();
        System.out.println("Enter label text: ");
        String label = scanner.nextLine();
        calendar.addEntry(new Event(date, time, label));
    }

    public static MyDate createDate () {
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
        System.out.println("Enter minutes: ");
        int minutes =  scanner.nextInt();
        return new MyTime(hours, minutes);
    }
}
