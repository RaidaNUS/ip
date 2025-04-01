package alo.manager;

import alo.task.*;

import java.io.*;
import java.util.*;


// Manages the user's task list: add, delete, mark/unmark, find, list, and file storage
public class TaskListManager {
    private static final String FILE_PATH = "data/tasks.txt";
    private static final String LINE = "____________________________________________________________";
    private static HashMap<Integer, Task> tasks = new HashMap<>();
    private static int taskCounter = 1;

    // Adds a task to the list and saves it to the file
    public static void addTask(Task task) {
        tasks.put(taskCounter, task);
        System.out.println(LINE);
        System.out.println("Aye Aye! I've added this task to the list:");
        System.out.println("    " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
        taskCounter++;
        saveTasksToFile();
    }

    // Deletes a task based on its task number and reindexes the task list
    public static void deleteTask(int number) {
        if (tasks.containsKey(number)) {
            Task removed = tasks.remove(number);
            reindex();
            System.out.println(LINE);
            System.out.println("Take note of the DELETED task:");
            System.out.println("    " + removed);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(LINE);
            saveTasksToFile();
        } else {
            System.out.println(LINE);
            System.out.println("Invalid task number! Please a Correct Task Number for me >.<");
            System.out.println(LINE);
        }
    }

    // Marks a task as completed and saves the updated list
    public static void markTask(int number) {
        if (tasks.containsKey(number)) {
            tasks.get(number).markAsDone();
            System.out.println(LINE);
            System.out.println("Cool! I've marked it as done! Congrats for finishing task:");
            System.out.println("    " + tasks.get(number));
            System.out.println(LINE);
            saveTasksToFile();
        } else {
            System.out.println(LINE);
            System.out.println("Invalid task number! Please a Correct Task Number for me >.<");
            System.out.println(LINE);
        }
    }

    // Marks a task as not done and saves the updated list
    public static void unmarkTask(int number) {
        if (tasks.containsKey(number)) {
            tasks.get(number).unmarkAsDone();
            System.out.println(LINE);
            System.out.println("Alrighty! I've marked it as not done.");
            System.out.println("    " + tasks.get(number));
            System.out.println(LINE);
            saveTasksToFile();
        } else {
            System.out.println(LINE);
            System.out.println("Invalid task number! Please a Correct Task Number for me >.<");
            System.out.println(LINE);
        }
    }

    // Displays all tasks in the list
    public static void listTasks() {
        System.out.println(LINE);
        System.out.println("Here ya are. This is your list. Enjoy!");
        if (tasks.isEmpty()) {
            System.out.println("Your List is empty darling |' v '|");
        } else {
            for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                System.out.println("      " + entry.getKey() + ". " + entry.getValue());
            }
        }
        System.out.println(LINE);
    }

    // Finds and displays tasks that match the given keyword
    public static void findTasks(String keyword) {
        System.out.println(LINE);
        System.out.println("Here are ALL the matching tasks in your list |^ . ^ | :");
        boolean found = false;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            if (entry.getValue().getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry NO matching tasks found |v . v|. Try another keyword!");
        }
        System.out.println(LINE);
    }

    // Loads tasks from the file during startup
    public static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task task = parseTaskFromFile(scanner.nextLine());
                if (task != null) {
                    tasks.put(taskCounter, task);
                    taskCounter++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(LINE);
            System.out.println("Error loading tasks: " + e.getMessage());
            System.out.println(LINE);
        }
    }

    // Saves all current tasks to the file
    private static void saveTasksToFile() {
        File file = new File(FILE_PATH);
        try {
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                String sanitized = formatTaskForFile(entry.getValue());
                writer.write(sanitized + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(LINE);
            System.out.println("Error saving tasks: " + e.getMessage());
            System.out.println(LINE);
        }
    }

    // Converts a task to its file format line
    private static String formatTaskForFile(Task task) {
        String type;
        if (task instanceof ToDo) type = "T";
        else if (task instanceof Deadline) type = "D";
        else if (task instanceof Event) type = "E";
        else return "";

        return type + " | " + (task.getIsDone() ? "X" : " ") + " | " + task.toFileString();
    }

    // Parses a single line from the file into a Task object
    private static Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isDone = parts[1].trim().equalsIgnoreCase("X");
        String description = parts[2];
        Task task = null;

        switch (type) {
            case "T" -> task = new ToDo(description);
            case "D" -> task = parts.length == 4 ? new Deadline(description, parts[3]) : null;
            case "E" -> task = parts.length == 5 ? new Event(description, parts[3], parts[4]) : null;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    // Re-indexes all tasks after deletion
    private static void reindex() {
        HashMap<Integer, Task> newMap = new HashMap<>();
        int newNum = 1;
        for (Task task : tasks.values()) {
            newMap.put(newNum, task);
            newNum++;
        }
        tasks = newMap;
        taskCounter = newNum;
    }
}
