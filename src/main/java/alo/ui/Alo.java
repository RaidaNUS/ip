
//Package Importing
package alo.ui;
import alo.exceptions.InvalidCommandExceptions;
import alo.exceptions.InvalidTaskNumExceptions;
import alo.exceptions.MissingTaskDescripExceptions;
import alo.exceptions.TaskExceptions;
import alo.task.Deadline;
import alo.task.Event;
import alo.task.Task;
import alo.task.ToDo;

import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Alo {

    private static final String LINE = "____________________________________________________________";

    private static final String FILE_PATH = "data/tasks.txt";

    // Save all tasks to file
    private static void saveTasksToFile() {
        File file = new File(FILE_PATH);

        try {
            // Ensure the directory exists
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);

            for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                writer.write(formatTaskForFile(entry.getKey(), entry.getValue()) + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    // Convert Task to file format
    private static String formatTaskForFile(int taskId, Task task) {
        String type;
        if (task instanceof ToDo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
        } else if (task instanceof Event) {
            type = "E";
        } else {
            return null;
        }

        return type + " | " + (task.getIsDone() ? "1" : "0") + " | " + task.toFileString();
    }


    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // No saved tasks, nothing to load
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                Task task = parseTaskFromFile(taskLine);
                if (task != null) {
                    tasks.put(taskCounter++, task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    // Parse a task from a file line
    private static Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| "); // Split by " | "

        if (parts.length < 3) {
            return null; // Corrupt line
        }

        String type = parts[0];  // Task type: T, D, or E
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        if (type.equals("T")) {
            task = new ToDo(description);
        } else if (type.equals("D") && parts.length == 4) { // Deadline Task
            task = new Deadline(description, parts[3]);
        } else if (type.equals("E") && parts.length == 5) { // Event Task
            task = new Event(description, parts[3], parts[4]);
        } else {
            return null; // Corrupt data
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }


    // Learnt now to make use of Switch Case to make code better in style from: https://github.com/nus-cs2113-AY2425S2/ip/pull/76/files


    private static HashMap<Integer, Task> tasks = new HashMap<>();
    private static int taskCounter = 1;

    //Methods for the ouput and task creation confimation prompts
    private static void Greeting() {
        System.out.println(LINE);
        System.out.println("Hi there! I'm Alo, my name means LIGHT!"); // Greet the user
        System.out.println("How may I be of assistance to you today?");
        System.out.println(LINE);
    }


    private static void exitProgram() {
        System.out.println(LINE);
        System.out.println("Great Helping You! Ciao ^_^");
        System.out.println(LINE);
    }

    private static void listTasks() {
        System.out.println(LINE);
        System.out.println("Here ya are. This is your list. Enjoy!");

        if (tasks.isEmpty()) {
            System.out.println("Your List is empty darling \' v '/");
        } else {
            for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                System.out.println("      " + entry.getKey() + ". " + entry.getValue().toString());
            }
        }
        System.out.println(LINE);
    }


    private static void markTask(String argument) {
        try {
            if (argument.isEmpty()) {
                System.out.println(LINE);
                System.out.println("Please enter a valid task number.");
                System.out.println(LINE);
                return;  // Just return instead of throwing an exception
            }

            int taskIndexNum = Integer.parseInt(argument);
            if (!tasks.containsKey(taskIndexNum)) {  // Task number does not exist
                System.out.println(LINE);
                System.out.println("OH NO! Invalid task number. Please enter a valid task number dear T_T.");
                System.out.println("Existing tasks: " + tasks.keySet());
                System.out.println(LINE);
                return;  // Return instead of throwing an exception
            }

            tasks.get(taskIndexNum).markAsDone();

            System.out.println(LINE);
            System.out.println("Cool! I've marked it as done! Congrats for finishing task: ");
            System.out.println("    " + tasks.get(taskIndexNum));
            System.out.println(LINE);
            saveTasksToFile();

        } catch (NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("You need to enter the correct Task Number you wish to Mark |;-;|");
            System.out.println(LINE);
        }
    }

    private static void unmarkTask(String argument) {
        try {
            if (argument.isEmpty()) {
                System.out.println(LINE);
                System.out.println("Please enter a valid task number, dear T_T.");
                System.out.println(LINE);
                return;  // Just return instead of throwing an exception
            }

            int taskIndexNum = Integer.parseInt(argument);
            if (!tasks.containsKey(taskIndexNum)) {  // Task number does not exist
                System.out.println(LINE);
                System.out.println("OH NO! Invalid task number. Please enter a valid task number dear T_T.");
                System.out.println("Existing tasks: " + tasks.keySet());
                System.out.println(LINE);
                return;  // Return instead of throwing an exception
            }

            tasks.get(taskIndexNum).unmarkAsDone();

            System.out.println(LINE);
            System.out.println("Alrighty! I've marked it as not done.");
            System.out.println("    " + tasks.get(taskIndexNum));
            System.out.println(LINE);
            saveTasksToFile();

        } catch (NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("You need to enter the correct Task Number you wish to UN-Mark |*-*|");
            System.out.println(LINE);
        }
    }

    private static void makeToDo(String job) {
        if (job.trim().isEmpty()) {
            System.out.println(LINE);
            System.out.println("Please enter a valid task to log a 'todo' task Command dear!");
            System.out.println(LINE);
            return;
        }

        // Create the task properly
        Task task = new ToDo(job);

        // Store it with correct ID
        tasks.put(taskCounter, task);

        System.out.println(LINE);
        System.out.println("Aye Aye! I've added this task to the list:");
        System.out.println("    " + tasks.get(taskCounter));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);

        taskCounter+=1; // Increment correctly
        saveTasksToFile();
    }


    private static void makeDeadline (String job){
        //add the talk into the array

        String[] deadlineDate = job.split("/by", 2);

        if(deadlineDate.length < 2){
            System.out.println(LINE);
            System.out.println("Invalid input format of the deadline. Please input the deadline as <TASK> /by <time/date>. Thanks love!");
            System.out.println(LINE);
            return;
        }

        if(job.trim().isEmpty()){
            System.out.println(LINE);
            System.out.println("Please enter a valid task to log a 'deadline' task Command Dear.");
            System.out.println(LINE);
            return;
        }

        tasks.put(taskCounter, new Deadline(deadlineDate[0], deadlineDate[1]));
        System.out.println(LINE);
        System.out.println("Take note of the deadline i've added to the task:");
        System.out.println("    " + tasks.get(taskCounter));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
        taskCounter+=1;
        saveTasksToFile();
    }
    private static void makeEvent(String job){
        String[] eventDate = job.split("/from | /to", 3);

        if(eventDate.length<3){
            System.out.println("Invalid input format of the event. Please input the event as <TASK> /from <time/date> /to <time/date>. Thanks love!");
            return;
        }

        if(job.trim().isEmpty()){
            System.out.println(LINE);
            System.out.println("Please enter a valid task to log a 'event' task Command Dear.");
            System.out.println(LINE);
            return;
        }

        tasks.put(taskCounter, new Event(eventDate[0], eventDate[1], eventDate[2]));
        System.out.println(LINE);
        System.out.println("I've added the event to the task list:");
        System.out.println("    " + tasks.get(taskCounter));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
        taskCounter+=1;
        saveTasksToFile();

    }

    private static void deleteTask(String argument) throws InvalidTaskNumExceptions {
        try {
            if (argument.isEmpty()) {
                throw new InvalidTaskNumExceptions();
            }

            int taskIndexNum = Integer.parseInt(argument);
            if (tasks.containsKey(taskIndexNum)) {
                Task removedTask = tasks.remove(taskIndexNum);
                System.out.println(LINE);
                System.out.println("Take note of the DELETED task:");
                System.out.println("    " + removedTask);
                System.out.println("Now you have " + tasks.size() + "  tasks in the list.");
                System.out.println(LINE);
            } else {
                throw new InvalidTaskNumExceptions();
            }
        } catch (NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("Invalid task number! Please a Correct Task Number for me >.<");
            System.out.println(LINE);
        }
    }

    //Method that takes care of adding tasks and if NO alo.task.Task Description is given

    private static void addTask(String inputs) throws MissingTaskDescripExceptions, InvalidCommandExceptions {
        String words[] = inputs.split(" ", 2);
        String command = words[0].toLowerCase();
        String job = words.length>1 ? words[1]: "";

        try{
            switch (command){

                case "todo":
                    if(job.isEmpty()){
                        throw new MissingTaskDescripExceptions(command);
                    }

                    makeToDo(job);
                    break;

                case "deadline":
                    if(job.isEmpty()){
                        throw new MissingTaskDescripExceptions(command);
                    }
                    makeDeadline(job);
                    break;

                case "event":
                    if(job.isEmpty()){
                        throw new MissingTaskDescripExceptions(command);
                    }
                    makeEvent(job);
                    break;

                case "delete":
                    if(job.isEmpty()){
                        throw new MissingTaskDescripExceptions(command);
                    }
                    deleteTask(job);
                    break;

                default:
                    throw new InvalidCommandExceptions();
            }
        }catch(MissingTaskDescripExceptions | InvalidCommandExceptions e){
            System.out.println(LINE);
            System.out.println(e.getMessage());
            System.out.println(LINE);
        }catch (InvalidTaskNumExceptions e) {
            throw new RuntimeException(e);
        }
    }

    private static void findTask(String argument) {
        System.out.println(LINE);
        System.out.println("Here are ALL the matching tasks in your list |^ . ^ | :");
        boolean found = false;
        int index = 1;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            if (entry.getValue().getDescription().toLowerCase().contains(argument.toLowerCase())) {
                System.out.println(index + ". " + entry.getValue());
                found = true;
                index++;
            }
        }

        if (!found) {
            System.out.println("Sorry NO matching tasks found |v . v|. Try another keyword!");
        }
        System.out.println(LINE);
    }

    public static void main(String[] args) throws InvalidTaskNumExceptions, InvalidCommandExceptions, MissingTaskDescripExceptions {
        loadTasksFromFile();
        //Greeting the user
        Greeting();

        //Inputting the user input
        Scanner scan = new Scanner(System.in);

        while (true) {
            String input = scan.nextLine().trim();
            String words[] = input.split(" ", 2);
            String command = words[0].toLowerCase();
            String arguments = words.length > 1 ? words[1] : ""; // This contains the number that i have to mark or unmark.
            //Tenery conditions implemented for practice

            switch (command) {

                case "bye":
                    exitProgram();
                    scan.close();
                    return;

                case "list":
                    listTasks();
                    break;

                case "mark":
                    markTask(arguments);
                    break;

                case "unmark":
                    unmarkTask(arguments);
                    break;

                case "find":
                    findTask(arguments);
                    break;

                default:
                    addTask(input);
                    break;

            }
        }
    }
}

