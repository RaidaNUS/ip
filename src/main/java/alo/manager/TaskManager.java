package alo.manager;

import alo.exceptions.InvalidCommandExceptions;
import alo.exceptions.InvalidTaskNumExceptions;
import alo.exceptions.MissingTaskDescripExceptions;

// TaskManager handles all user input commands
public class TaskManager {

    private static final String LINE = "____________________________________________________________";

    // Processes the user's input command and performs the corresponding task operation
    public static void checkCommand(String input) {
        // Splits the input into command and arguments
        String[] words = input.trim().split(" ", 2);
        String command = words[0].toLowerCase();
        String arguments = words.length > 1 ? words[1].trim() : ""; // Extracts the rest


        try {
            switch (command) {
                // Handles "todo" command
                case "todo":
                    if (arguments.isEmpty()) {
                        throw new MissingTaskDescripExceptions("todo");
                    }
                    TaskListManager.addTask(new alo.task.ToDo(arguments));
                    break;

                // Handles "deadline" command
                case "deadline":
                    if (arguments.isEmpty()) {
                        throw new MissingTaskDescripExceptions("deadline");
                    }
                    TaskListManager.addTask(TaskParser.parseDeadline(arguments));
                    break;

                // Handles "event" command
                case "event":
                    if (arguments.isEmpty()){
                        throw new MissingTaskDescripExceptions("event");
                    }
                    TaskListManager.addTask(TaskParser.parseEvent(arguments));
                    break;

                // Handles task deletion
                case "delete":
                    TaskListManager.deleteTask(Integer.parseInt(arguments));
                    break;

                // Marks a task as done
                case "mark":
                    TaskListManager.markTask(Integer.parseInt(arguments));
                    break;

                // Unmarks a task as not done
                case "unmark":
                    TaskListManager.unmarkTask(Integer.parseInt(arguments));
                    break;

                // Displays the full task list
                case "list":
                    TaskListManager.listTasks();
                    break;

                // Searches for tasks containing a keyword
                case "find":
                    TaskListManager.findTasks(arguments);
                    break;

                // Catches missing task descriptions or unknown commands
                default:
                    throw new InvalidCommandExceptions();
            }
        } catch (MissingTaskDescripExceptions | InvalidCommandExceptions e) {
            System.out.println(LINE);
            System.out.println(e.getMessage());
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("Invalid task number! Please a Correct Task Number for me >.<");
            System.out.println(LINE);
        }
    }
}
