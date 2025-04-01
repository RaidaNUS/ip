package alo.ui;

import java.util.Scanner;

import alo.manager.TaskManager;
import alo.manager.TaskListManager;

public class Alo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskListManager.loadTasksFromFile();
        Ui.printGreeting();
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine().trim();
            if (!input.equalsIgnoreCase("bye")) {
                TaskManager.checkCommand(input);
            }
        }
        Ui.printExit();
    }
}
