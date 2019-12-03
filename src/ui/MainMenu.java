package ui;

import model.MyCalendar;

import java.util.Scanner;

import static ui.CreateEntry.createEntry;
import static ui.DisplayEntries.displayEntries;

public class MainMenu {
    public static void main(String[] args) {
        mainMenu(calendarInit());
    }

    public static void mainMenu(MyCalendar calendar) {
        String choice = null;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\t\t\t\tCALENDAR APP");
            System.out.println();
            System.out.println("\t\t1.\t\tCreate entry.");
            System.out.println("\t\t2.\t\tDisplay entries.");
            System.out.println("\t\tq.\t\tQuit.");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createEntry(calendar);
                    break;
                case "2":
                    displayEntries(calendar);
                    break;
                case "q":
                    break;
                default:
                    System.out.println("Invalid options");
            }
        } while (!choice.equals("q"));
    }

    public static MyCalendar calendarInit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email to initialize calendar: ");
        return new MyCalendar(scanner.nextLine());
    }
}
