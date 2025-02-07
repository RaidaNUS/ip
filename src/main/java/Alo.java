import java.util.Scanner;
import java.util.ArrayList;

public class Alo {

    // Learnt from: https://github.com/nus-cs2113-AY2425S2/ip/pull/76/files

    private static ArrayList<Task> tasks = new ArrayList<>();

    //Methods for the ouput and task creation confimation prompts
    private static void Greeting() {
        System.out.println("____________________________________________________________");
        System.out.println("Hi there! I'm Alo, my name means LIGHT!"); // Greet the user
        System.out.println("How may I be of assistance to you today?");
        System.out.println("____________________________________________________________");
    }


    private static void exitProgram() {
        System.out.println("____________________________________________________________");
        System.out.println("Great Helping You! Ciao ^_^");
        System.out.println("____________________________________________________________");
    }

    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here ya are. This is your list. Enjoy!");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println("      " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTask(String argument) {
        try {
            int taskIndexNum = Integer.parseInt(argument) - 1;
            if (taskIndexNum >= 0 && taskIndexNum < tasks.size()) {
                tasks.get(taskIndexNum).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Cool! I've marked it as done! Congrats for finishing task: ");
                System.out.println("    " + tasks.get(taskIndexNum));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Task you want to mark down as done is out of range Dear.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number my dear.");
        }
    }

    private static void unmarkTask(String argument) {
        try {
            int taskIndexNum = Integer.parseInt(argument) - 1;
            if (taskIndexNum >= 0 && taskIndexNum < tasks.size()) {
                tasks.get(taskIndexNum).unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Alrighty! I've marked it as not done.");
                System.out.println("    " + tasks.get(taskIndexNum));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Task you want to unmark as done is out of range Dear.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number my dear.");
        }
    }

    private static void makeToDo (String job){
        //add the talk into the array
        tasks.add(new ToDo(job));
        System.out.println("____________________________________________________________");
        System.out.println("Aye Aye! I've added this task to the list:");
        System.out.println("    " + tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void makeDeadline (String job){
        //add the talk into the array

        String[] deadlineDate = job.split("/by", 2);
        if(deadlineDate.length < 2){
            System.out.println("Invalid input format of the deadline. Please input the deadline as <TASK> /by <time/date>. Thanks love!");
            return;
        }

        tasks.add(new Deadline(deadlineDate[0], deadlineDate[1]));
        System.out.println("____________________________________________________________");
        System.out.println("Take note of the deadline i've added to the task:");
        System.out.println("    " + tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    private static void makeEvent(String job){
        String[] eventDate = job.split("/from | /to", 3);

        if(eventDate.length<3){
            System.out.println("Invalid input format of the event. Please input the event as <TASK> /from <time/date> /to <time/date>. Thanks love!");
            return;
        }

        tasks.add(new Event(eventDate[0], eventDate[1], eventDate[2]));
        System.out.println("____________________________________________________________");
        System.out.println("I've added the event to the task list:");
        System.out.println("    " + tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }

    //Method that takes care of adding tasks

    private static void addTask(String inputs) {
        String words[] = inputs.split(" ", 2);
        String command = words[0].toLowerCase();
        String job = words.length>1 ? words[1]: "";

        switch (command){

            case "todo":
                makeToDo(job);
                break;

            case "deadline":
                makeDeadline(job);
                break;

            case "event":
                makeEvent(job);
                break;

            default:
                System.out.println("Invalid Command Word. Try using deadline, event, todo as initiators for tasks! ^_^");
        }
    }


    public static void main(String[] args) {

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

