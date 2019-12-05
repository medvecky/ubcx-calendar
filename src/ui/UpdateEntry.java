package ui;


import model.*;

import java.util.List;
import java.util.Scanner;

import static ui.CreateEntry.*;

public class UpdateEntry {
    public static void updateEntry(MyCalendar calendar, List<Entry> entries) {
        Scanner scanner = new Scanner(System.in);
        String choice = null;
        System.out.print("Are you want edit entries? (y/n): ");
        if (!scanner.nextLine().equals("y")) {
            return;
        }
        System.out.print("Enter entry number for update: ");
        int entryNumber = Integer.parseInt(scanner.nextLine());
        Entry entry = entries.get(entryNumber - 1);

        System.out.println("Entry -> : " + entry.getLabel());

        System.out.print("E - for edit D - for delete: ");


        choice = scanner.nextLine();

        switch (choice) {
            case "E":
            case "e":
                editEntry(calendar, entry);
                break;
            case "D":
            case "d":
                calendar.removeEntry(entry);
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    public static void editEntry(MyCalendar calendar, Entry entry) {
        if (entry.getClass().equals(Event.class)) {
            calendar.updateEntry(entry, createEvent());
        } else if(entry.getClass().equals(Reminder.class)) {
            calendar.updateEntry(entry, createReminder());
        } else if(entry.getClass().equals(Meeting.class)) {
            calendar.updateEntry(entry, createMeeting());
        }
    }
}
