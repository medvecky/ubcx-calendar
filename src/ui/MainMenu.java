package ui;

import java.util.Scanner;

import static ui.CreateEntry.createEntry;

public class MainMenu {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
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
                    createEntry();
                    break;
                case "2":
                    System.out.println("Display entries procedure");
                    break;
            }
        } while(!choice.equals("q"));
    }
}
