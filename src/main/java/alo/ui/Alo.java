
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
import java.util.ArrayList;

public class Alo {

    private static final String LINE = "____________________________________________________________";

    // Learnt now to make use of Switch Case to make code better in style from: https://github.com/nus-cs2113-AY2425S2/ip/pull/76/files


    private static HashMap<Integer, Task> tasks = new HashMap<>();
    private static int taskCounter = 1;

    //Methods for the ouput and task creation confimation prompts
    private static void Greeting() {
        System.out.println(LINE);
        System.out.println("Hi there! I'm alo.ui.Alo, my name means LIGHT!"); // Greet the user
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
        if(tasks.isEmpty()){
            System.out.println("Your List is empty darling \' v '/");
        }else{
            for (int i = 0; i < tasks.size(); i += 1) {
                System.out.println("      " + (i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(LINE);
    }

    private static void markTask(String argument) throws InvalidTaskNumExceptions {
        try {
            if (argument.isEmpty()) {
                throw new InvalidTaskNumExceptions();
            }

            int taskIndexNum = Integer.parseInt(argument);
            if (tasks.containsKey(taskIndexNum)) {
                tasks.get(taskIndexNum).markAsDone();
                System.out.println(LINE);
                System.out.println("Cool! I've marked it as done! Congrats for finishing task: ");
                System.out.println("    " + tasks.get(taskIndexNum));
                System.out.println(LINE);
            }else{
                throw new InvalidTaskNumExceptions();
            }
        } catch (NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("Invalid Task Number! You need to enter the correct Task Number you wish to Mark as done /;v;/ ");
            System.out.println(LINE);
        }
    }

    private static void unmarkTask(String argument) throws InvalidTaskNumExceptions {
        try {
            if (argument.isEmpty()) {
                throw new InvalidTaskNumExceptions();
            }

            int taskIndexNum = Integer.parseInt(argument);
            if (tasks.containsKey(taskIndexNum)) {
                tasks.get(taskIndexNum).unmarkAsDone();
                System.out.println(LINE);
                System.out.println("Alrighty! I've marked it as not done.");
                System.out.println("    " + tasks.get(taskIndexNum));
                System.out.println(LINE);
            }else{
                throw new InvalidTaskNumExceptions();
            }
        } catch (NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("Invalid Task Number! You need to enter the correct Task Number you wish to UN-Mark |*-*|");
            System.out.println(LINE);
        }
    }

    private static void makeToDo (String job){

        if(job.trim().isEmpty()){
            System.out.println(LINE);
            System.out.println("Please enter a valid task to log a 'todo' task Command Dear.");
            System.out.println(LINE);
            return;
        }
        //add the talk into the array
        tasks.put(taskCounter, new ToDo(job));
        System.out.println(LINE);
        System.out.println("Aye Aye! I've added this task to the list:");
        System.out.println("    " + tasks.get(tasks.get(taskCounter)));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
        taskCounter=+1;
    }

    private static void makeDeadline (String job){
        //add the talk into the array

        String[] deadlineDate = job.split("/by", 2);

        if(deadlineDate.length < 2){
            System.out.println("Invalid input format of the deadline. Please input the deadline as <TASK> /by <time/date>. Thanks love!");
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
        taskCounter=+1;
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
        taskCounter=+1;

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

    public static void main(String[] args) throws InvalidTaskNumExceptions, InvalidCommandExceptions, MissingTaskDescripExceptions {

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

                default:
                    addTask(input);
                    break;

            }
        }
    }
}

