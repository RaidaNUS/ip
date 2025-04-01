package alo.manager;

import alo.task.Deadline;
import alo.task.Event;
import alo.task.Task;

// TaskParser handles parsing of task input formats for deadline and event types
public class TaskParser {

    // Parses a deadline command input and returns a Deadline task object
    public static Task parseDeadline(String input) {
        String[] parts = input.split("/by", 2);
        // If format is incorrect or description is missing
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            System.out.println("Invalid format used T_T. Use: deadline <task> /by <time>");
            return null;
        }
        // Create and return the Deadline task
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    // Parses an event command input and returns an Event task object
    public static Task parseEvent(String input) {
        String[] parts = input.split("/from | /to", 3);
        // If format is incorrect or description is missing
        if (parts.length < 3 || parts[0].trim().isEmpty()) {
            System.out.println("Invalid format used T_T. Use: event <task> /from <start> /to <end>");
            return null;
        }
        // Create and return the Event task
        return new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }
}
