package ui;

import model.MyCalendar;

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
                    System.out.println("Filter by type procedure...");
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
                case "1" :
                    System.out.println("Show Events procedure...");
                    break;
                case "2":
                    System.out.println("Show Meetings procedure...");
                    break;
                case "3":
                    System.out.println("Show reminders procedure...");
                    break;
                case "r":
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (!choice.equals("r"));
    }
}
